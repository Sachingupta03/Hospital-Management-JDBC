# Hospital-Management-JDBC
    🏥 Hospital Management System (JDBC, POJO)
📌 Project Overview
This Hospital Management System is a Java-based project that follows the POJO (Plain Old Java Object) approach and uses JDBC for database connectivity. It manages patients, doctors, appointments, and billing efficiently.

🛠 Technologies Used
Java 8+

JDBC (Java Database Connectivity)

MySQL / PostgreSQL (Database)

POJO (Plain Old Java Object) Design

CRUD Operations

Exception Handling

📂 Project Structure
pgsql
Copy
Edit
Hospital-Management-JDBC/
│── src/
│   ├── model/
│   │   ├── Patient.java
│   │   ├── Doctor.java
│   │   ├── Appointment.java
│   │   ├── Billing.java
│   │
│   ├── dao/
│   │   ├── PatientDAO.java
│   │   ├── DoctorDAO.java
│   │   ├── AppointmentDAO.java
│   │   ├── BillingDAO.java
│   │
│   ├── service/
│   │   ├── PatientService.java
│   │   ├── DoctorService.java
│   │   ├── AppointmentService.java
│   │   ├── BillingService.java
│   │
│   ├── exception/
│   │   ├── HospitalException.java
│   │
│   ├── main/
│   │   ├── HospitalManagementSystem.java
│
│── README.md
│── hospital.sql (Database Schema)
│── pom.xml (If using Maven)
📌 Features
✅ Patient Management (Add, Update, Delete, View Patients)
✅ Doctor Management (Assign Doctors, Schedule Timings)
✅ Appointment Booking (Schedule, Cancel, Reschedule)
✅ Billing System (Generate & Manage Bills)
✅ Exception Handling (Handles Errors Gracefully)
✅ Database Connectivity using JDBC

📖 How to Run the Project
1️⃣ Setup Database
Install MySQL or PostgreSQL.

Create a database and run the SQL script:

sql
Copy
Edit
CREATE DATABASE hospital_db;
USE hospital_db;
Import hospital.sql file.

2️⃣ Configure Database in Java
Update the database credentials in DBConnection.java:

java
Copy
Edit
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "yourpassword";
}
3️⃣ Compile & Run
sh
Copy
Edit
javac -cp .;mysql-connector-java.jar src/main/HospitalManagementSystem.java
java -cp .;mysql-connector-java.jar src/main/HospitalManagementSystem
📝 Example Code (POJO - Patient.java)
java
Copy
Edit
public class Patient {
    private int id;
    private String name;
    private int age;
    private String disease;
    
    // Constructor
    public Patient(int id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDisease() { return disease; }
}
🚀 Future Enhancements
🔹 GUI (Swing/JavaFX/Web UI)
🔹 Spring Boot Integration
🔹 Role-Based Access Control (Admin, Doctor, Patient)

🖥️ Author
👨‍💻 Sachin Kumar Gupta
📍 Full-Stack Developer | Java | JDBC | Spring Boot

