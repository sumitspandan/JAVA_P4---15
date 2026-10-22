import java.util.ArrayList;
import java.util.Scanner;
class Student {
int studentId;
String studentName;
String courseDepartment;
int semester;
Student(int studentId, String studentName,
String courseDepartment, int semester) {
this.studentId = studentId;
this.studentName = studentName;
this.courseDepartment = courseDepartment;
this.semester = semester;
}
void display() {
System.out.println("Student ID: " + studentId
+ ", Name: " + studentName
+ ", Course/Department: " + courseDepartment
+ ", Semester: " + semester);
}
}
public class ArrayListStudentManagement {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
ArrayList<Student> students = new ArrayList<>();
int choice;
do {
System.out.println("\n--- Student Record Management ---");
System.out.println("1. Add Student");
System.out.println("2. Remove Student");
System.out.println("3. Search Student");
System.out.println("4. Display All Students");
System.out.println("5. Exit");
System.out.print("Enter choice: ");
choice = sc.nextInt();
sc.nextLine();
switch (choice) {
case 1:
System.out.print("Enter Student ID: ");
int id = sc.nextInt();
sc.nextLine();
System.out.print("Enter Student Name: ");
String name = sc.nextLine();
System.out.print("Enter Course/Department: ");
String course = sc.nextLine();
System.out.print("Enter Semester: ");
int semester = sc.nextInt();
sc.nextLine();
students.add(new Student(id, name, course, semester));
System.out.println("Student record added successfully.");
break;
case 2:
System.out.print("Enter Student ID to remove: ");
int removeId = sc.nextInt();
sc.nextLine();
boolean removed = false;
for (int i = 0; i < students.size(); i++) {
if (students.get(i).studentId == removeId) {
students.remove(i);
removed = true;
break;
}
}
if (removed)
System.out.println("Student record removed successfully.");
else
System.out.println("Student record not found.");
break;
case 3:
System.out.print("Enter Student ID to search: ");
int searchId = sc.nextInt();
sc.nextLine();
boolean found = false;
for (Student s : students) {
if (s.studentId == searchId) {
s.display();
found = true;
break;
}
}
if (!found)
System.out.println("Student record not found.");
break;
case 4:
if (students.isEmpty()) {
System.out.println("No student records available.");
} else {
for (Student s : students)
s.display();
}
break;
case 5:
System.out.println("Exiting Student Record Management System.");
break;
default:
System.out.println("Invalid choice. Please try again.");
}
} while (choice != 5);
sc.close();
}
}
/*
Sample Input:
1
101
Rahul
CSE
3
4
3
101
2
101
4
5
Sample Output:
--- Student Record Management ---
1. Add Student
2. Remove Student
3. Search Student
4. Display All Students
5. Exit
Enter choice: 1
Enter Student ID: 101
Enter Student Name: Rahul
Enter Course/Department: CSE
Enter Semester: 3
Student record added successfully.
Enter choice: 4
Student ID: 101, Name: Rahul, Course/Department: CSE, Semester: 3
Enter choice: 3
Enter Student ID to search: 101
Student ID: 101, Name: Rahul, Course/Department: CSE, Semester: 3
Enter choice: 2
Enter Student ID to remove: 101
Student record removed successfully.
Enter choice: 4
No student records available.
Enter choice: 5
Exiting Student Record Management System.
*/