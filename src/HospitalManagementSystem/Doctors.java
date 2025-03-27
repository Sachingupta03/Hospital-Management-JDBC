package HospitalManagementSystem;

import com.mysql.cj.util.DnsSrv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctors {
        Scanner scanner = new Scanner (System.in);
    private Connection connection;


    public Doctors(Connection connection){
        this.connection = connection;
    }

    public void addDoctor() {
        System.out.println("Enter Doctor Name: ");
        String name = scanner.nextLine ();

        System.out.println("Enter Doctor Specialization: ");
        String spl=scanner.nextLine(); // Consume newline


        try {
            //  FIX: Corrected SQL Syntax
            String query = "INSERT INTO doctors (name, spl) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, spl);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println(" Doctor Added Successfully!");
            } else {
                System.out.println(" Failed to add Doctor!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void viewDoctors(){
        String query= "select * from Doctors";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement (query);
            ResultSet resultSet = preparedStatement.executeQuery ();
            System.out.println ("Doctors: ");
            System.out.println ("+---------------+---------------------+-------------------+");
            System.out.println ("| Doctor Id    | Name                 |  Specialization   |");
            System.out.println ("+---------------+---------------------+-------------------+");
            while (resultSet.next ()){
                int id = resultSet.getInt ("id");
                String name = resultSet.getString ("name");
                String  spl = resultSet.getString ("spl");
                System.out.printf("|%-16d|%-22s|%-20s|\n", id, name, spl);
                System.out.println ("+---------------+---------------------+-------------------+");

            }


        }catch (SQLException e){
            e.printStackTrace ();
        }
    }

    public boolean getDoctorById(int id) {
        String query = "SELECT * FROM doctor WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement (query);
            preparedStatement.setInt (1, id);
            ResultSet resultSet = preparedStatement.executeQuery ();
            if (resultSet.next ()){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace ();
        }
        return false;
    }
}
