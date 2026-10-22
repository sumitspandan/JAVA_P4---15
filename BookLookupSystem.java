import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
public class BookLookupSystem {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
Hashtable<Integer, String> books = new Hashtable<>();
int choice;
do {
System.out.println("\n--- Book Lookup System ---");
System.out.println("1. Add a Book");
System.out.println("2. Search a Book");
System.out.println("3. Remove a Book");
System.out.println("4. Display All Books");
System.out.println("5. Exit");
System.out.print("Enter choice: ");
choice = sc.nextInt();
sc.nextLine();
switch (choice) {
case 1:
System.out.print("Enter Book ID: ");
int id = sc.nextInt();
sc.nextLine();
System.out.print("Enter Book Title: ");
String title = sc.nextLine();
books.put(id, title);
System.out.println("Book added successfully.");
break;
case 2:
System.out.print("Enter Book ID to search: ");
int searchId = sc.nextInt();
sc.nextLine();
if (books.containsKey(searchId))
System.out.println("Book ID: " + searchId
+ ", Book Title: " + books.get(searchId));
else
System.out.println("Book not found.");
break;
case 3:
System.out.print("Enter Book ID to remove: ");
int removeId = sc.nextInt();
sc.nextLine();
if (books.containsKey(removeId)) {
books.remove(removeId);
System.out.println("Book removed successfully.");
} else {
System.out.println("Book not found. Unable to remove.");
}
break;
case 4:
if (books.isEmpty()) {
System.out.println("No books available in the library.");
} else {
for (Map.Entry<Integer, String> entry
: books.entrySet()) {
System.out.println("Book ID: " + entry.getKey()
+ ", Book Title: " + entry.getValue());
}
}
break;
case 5:
System.out.println("Exiting Book Lookup System.");
break;
default:
System.out.println("Invalid choice. Please select a valid option.");
}
} while (choice != 5);
sc.close();
}
}
/*
Sample Input:
1
101
Java Programming
1
102
Data Structures
4
2
101
3
101
2
101
5
Sample Output:
--- Book Lookup System ---
1. Add a Book
2. Search a Book
3. Remove a Book
4. Display All Books
5. Exit
Enter choice: 1
Enter Book ID: 101
Enter Book Title: Java Programming
Book added successfully.
Enter choice: 1
Enter Book ID: 102
Enter Book Title: Data Structures
Book added successfully.
Enter choice: 4
Book ID: 101, Book Title: Java Programming
Book ID: 102, Book Title: Data Structures
Enter choice: 2
Enter Book ID to search: 101
Book ID: 101, Book Title: Java Programming
Enter choice: 3
Enter Book ID to remove: 101
Book removed successfully.
Enter choice: 2
Enter Book ID to search: 101
Book not found.
Enter choice: 5
Exiting Book Lookup System.
*/