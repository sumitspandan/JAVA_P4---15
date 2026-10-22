import java.io.*;
import java.util.Scanner;
public class LibraryTransactionSystem {
static final String FILE_NAME = "transactions.txt";
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
int choice;
do {
System.out.println("\n--- Library Management System ---");
System.out.println("1. Record Book Transaction");
System.out.println("2. View Transaction Records");
System.out.println("3. Exit");
System.out.print("Enter choice: ");
if (!sc.hasNextInt()) {
System.out.println("Invalid choice. Please select a valid option.");
sc.nextLine();
choice = 0;
continue;
}
choice = sc.nextInt();
sc.nextLine();
switch (choice) {
case 1:
try (FileWriter fw = new FileWriter(FILE_NAME, true);
BufferedWriter bw = new BufferedWriter(fw)) {
System.out.print("Enter Book ID: ");
String bookId = sc.nextLine().trim();
System.out.print("Enter Book Name: ");
String bookName = sc.nextLine().trim();
System.out.print("Enter Student/User Name: ");
String userName = sc.nextLine().trim();
System.out.print("Enter Issue Date (DD/MM/YYYY): ");
String issueDate = sc.nextLine().trim();
System.out.print("Enter Return Date (DD/MM/YYYY): ");
String returnDate = sc.nextLine().trim();
if (bookId.isEmpty() || bookName.isEmpty()
|| userName.isEmpty() || issueDate.isEmpty()
|| returnDate.isEmpty()) {
System.out.println("Transaction details cannot be empty.");
break;
}
bw.write("Book ID: " + bookId + ", Book Name: " + bookName
+ ", Student Name: " + userName
+ ", Issue Date: " + issueDate
+ ", Return Date: " + returnDate);
bw.newLine();
System.out.println("Transaction recorded successfully.");
} catch (IOException e) {
System.out.println("Error writing to file: " + e.getMessage());
}
break;
case 2:
File file = new File(FILE_NAME);
if (!file.exists()) {
System.out.println("No transaction records found.");
break;
}
try (BufferedReader br = new BufferedReader(
new FileReader(file))) {
String line;
boolean found = false;
while ((line = br.readLine()) != null) {
if (!line.trim().isEmpty()) {
System.out.println(line);
found = true;
}
}
if (!found) {
System.out.println("No transaction records found.");
}
} catch (IOException e) {
System.out.println("Error reading file: " + e.getMessage());
}
break;
case 3:
System.out.println("Exiting Library Management System.");
break;
default:
System.out.println("Invalid choice. Please select a valid option.");
}
} while (choice != 3);
sc.close();
}
}
/*
Sample Input:
1
B101
Java Programming
Rahul
10/07/2026
20/07/2026
2
3
Sample Output:
--- Library Management System ---
1. Record Book Transaction
2. View Transaction Records
3. Exit
Enter choice: 1
Enter Book ID: B101
Enter Book Name: Java Programming
Enter Student/User Name: Rahul
Enter Issue Date (DD/MM/YYYY): 10/07/2026
Enter Return Date (DD/MM/YYYY): 20/07/2026
Transaction recorded successfully.
Enter choice: 2
Book ID: B101, Book Name: Java Programming, Student Name: Rahul, Issue Date: 10/07/2026, Return Date: 20/07/2026
Enter choice: 3
Exiting Library Management System.
*/