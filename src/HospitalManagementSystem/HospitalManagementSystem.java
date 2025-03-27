package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "Sachin@10";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //  Fixed Driver Name
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        try (Connection connection = DriverManager.getConnection(url, username, password)) { // Close Connection after use
            Patient patient = new Patient(connection, scanner);
            Doctors doctors = new Doctors(connection);

            while (true) {
                System.out.println("Hospital Management System");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patient ");
                System.out.println("3. Book Appointment");
                System.out.println ("4. Add Doctors");
                System.out.println("5. View Doctors");
                System.out.println("6. Exit");
                System.out.println("Enter your choice");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        patient.addPatient();
                        System.out.println();
                        break;

                    case 2:
                        patient.viewPatient();
                        System.out.println();
                        break;

                    case 3:
                        bookAppointment(patient, doctors, connection, scanner);
                        System.out.println();
                        break;

                    case 4:
                        doctors.addDoctor ();
                        System.out.println();
                        break;

                    case 5:
                        doctors.viewDoctors();
                        System.out.println();
                        break;

                    case 6:
                        return;

                    default:
                        System.out.println("Enter valid choice!!!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void bookAppointment(Patient patient, Doctors doctors, Connection connection, Scanner scanner) {
        System.out.println("Enter Patient Id");
        int patientId = scanner.nextInt();
        System.out.println("Enter Doctor Id");
        int doctorId = scanner.nextInt();
        System.out.println("Enter appointment date (YYYY-MM-DD): ");
        String appointment = scanner.next(); // Correct format

        if (patient.getPatientById(patientId) && doctors.getDoctorById(doctorId)) {
            if (checkDoctorAvailability(doctorId, appointment, connection)) {
                try {
                    String appointmentQuery = "INSERT INTO appointment (patient_id, doctor_id, appointment_date) VALUES(?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patientId);
                    preparedStatement.setInt(2, doctorId);
                    preparedStatement.setString(3, appointment);
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Appointment Booked!");
                    } else {
                        System.out.println("Failed to Book Appointment!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Doctor is not available on this date!");
            }
        } else {
            System.out.println("Entered doctor or patient doesn't exist!!!");
        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection) {
        String query = "SELECT COUNT(*) FROM appointment WHERE doctor_id = ? AND appointment_date = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
