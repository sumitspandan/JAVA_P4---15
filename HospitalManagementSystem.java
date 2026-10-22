import java.sql.*;
import java.util.Scanner;
public class HospitalManagementSystem {
static final String URL = "jdbc:mysql://localhost:3306/HospitalDB";
static final String USER = "root";
static final String PASSWORD = "your_password";
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
try {
Class.forName("com.mysql.cj.jdbc.Driver");
try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
System.out.println("Connected to HospitalDB successfully.");
String createTable = "CREATE TABLE IF NOT EXISTS Patient ("
+ "patient_id INT PRIMARY KEY, "
+ "patient_name VARCHAR(100) NOT NULL, "
+ "age INT, gender VARCHAR(20), "
+ "disease VARCHAR(150), contact VARCHAR(20))";
try (Statement st = con.createStatement()) {
st.executeUpdate(createTable);
}
int choice;
do {
System.out.println("\\n--- Hospital Management System ---");
System.out.println("1. Insert Patient Record");
System.out.println("2. Update Patient Record");
System.out.println("3. Delete Patient Record");
System.out.println("4. Display Patient Records");
System.out.println("5. Exit");
System.out.print("Enter choice: ");
if (!sc.hasNextInt()) {
System.out.println("Invalid choice. Please select a valid option.");
sc.nextLine();
continue;
}
choice = sc.nextInt();
sc.nextLine();
switch (choice) {
case 1:
System.out.print("Patient ID: ");
int id = Integer.parseInt(sc.nextLine().trim());
System.out.print("Patient Name: ");
String name = sc.nextLine().trim();
System.out.print("Age: ");
int age = Integer.parseInt(sc.nextLine().trim());
System.out.print("Gender: ");
String gender = sc.nextLine().trim();
System.out.print("Disease/Diagnosis: ");
String disease = sc.nextLine().trim();
System.out.print("Contact Number: ");
String contact = sc.nextLine().trim();
String insert = "INSERT INTO Patient "
+ "(patient_id, patient_name, age, gender, disease, contact) "
+ "VALUES (?, ?, ?, ?, ?, ?)";
try (PreparedStatement ps = con.prepareStatement(insert)) {
ps.setInt(1, id);
ps.setString(2, name);
ps.setInt(3, age);
ps.setString(4, gender);
ps.setString(5, disease);
ps.setString(6, contact);
ps.executeUpdate();
System.out.println("Patient record inserted successfully.");
} catch (SQLIntegrityConstraintViolationException e) {
System.out.println("Patient ID already exists.");
}
break;
case 2:
System.out.print("Enter Patient ID to update: ");
int updateId = Integer.parseInt(sc.nextLine().trim());
System.out.print("New Patient Name: ");
String newName = sc.nextLine().trim();
System.out.print("New Age: ");
int newAge = Integer.parseInt(sc.nextLine().trim());
System.out.print("New Gender: ");
String newGender = sc.nextLine().trim();
System.out.print("New Disease/Diagnosis: ");
String newDisease = sc.nextLine().trim();
System.out.print("New Contact Number: ");
String newContact = sc.nextLine().trim();
String update = "UPDATE Patient SET patient_name=?, age=?, "
+ "gender=?, disease=?, contact=? WHERE patient_id=?";
try (PreparedStatement ps = con.prepareStatement(update)) {
ps.setString(1, newName);
ps.setInt(2, newAge);
ps.setString(3, newGender);
ps.setString(4, newDisease);
ps.setString(5, newContact);
ps.setInt(6, updateId);
int rows = ps.executeUpdate();
System.out.println(rows > 0
? "Patient record updated successfully."
: "Patient record not found.");
}
break;
case 3:
System.out.print("Enter Patient ID to delete: ");
int deleteId = Integer.parseInt(sc.nextLine().trim());
String delete = "DELETE FROM Patient WHERE patient_id=?";
try (PreparedStatement ps = con.prepareStatement(delete)) {
ps.setInt(1, deleteId);
int rows = ps.executeUpdate();
System.out.println(rows > 0
? "Patient record deleted successfully."
: "Patient record not found.");
}
break;
case 4:
String select = "SELECT * FROM Patient";
try (PreparedStatement ps = con.prepareStatement(select);
ResultSet rs = ps.executeQuery()) {
boolean found = false;
while (rs.next()) {
found = true;
System.out.println("\\nPatient ID: " + rs.getInt("patient_id"));
System.out.println("Name: " + rs.getString("patient_name"));
System.out.println("Age: " + rs.getInt("age"));
System.out.println("Gender: " + rs.getString("gender"));
System.out.println("Disease: " + rs.getString("disease"));
System.out.println("Contact: " + rs.getString("contact"));
}
if (!found) {
System.out.println("No patient records found.");
}
}
break;
case 5:
System.out.println("Exiting Hospital Management System.");
break;
default:
System.out.println("Invalid choice. Please select a valid option.");
}
} while (choice != 5);
}
} catch (ClassNotFoundException e) {
System.out.println("MySQL JDBC driver not found. Add MySQL Connector/J to the project.");
} catch (SQLException e) {
System.out.println("Database error: " + e.getMessage());
} catch (NumberFormatException e) {
System.out.println("Please enter valid numeric values for ID and age.");
} finally {
sc.close();
}
}
}
/*
Sample Input:
1
101
Rahul
25
Male
Fever
9876543210
4
5
Sample Output:
Connected to HospitalDB successfully.
--- Hospital Management System ---
1. Insert Patient Record
2. Update Patient Record
3. Delete Patient Record
4. Display Patient Records
5. Exit
Enter choice: 1
Patient ID: 101
Patient Name: Rahul
Age: 25
Gender: Male
Disease/Diagnosis: Fever
Contact Number: 9876543210
Patient record inserted successfully.
Enter choice: 4
Patient ID: 101
Name: Rahul
Age: 25
Gender: Male
Disease: Fever
Contact: 9876543210
Enter choice: 5
Exiting Hospital Management System.
Note: Create the HospitalDB database in MySQL first and replace USER/PASSWORD
with your MySQL credentials. Add MySQL Connector/J to the project.
*/