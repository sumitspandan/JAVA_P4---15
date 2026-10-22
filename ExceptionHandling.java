import java.util.Scanner;
class InsufficientBalanceException extends Exception {
InsufficientBalanceException(String message) {
super(message);
}
}
class Account {
String accountHolderName;
double accountBalance;
Account(String name, double balance) {
accountHolderName = name;
accountBalance = balance;
}
void withdraw(double amount) throws InsufficientBalanceException {
if (amount <= 0) {
throw new IllegalArgumentException("Withdrawal amount must be positive.");
}
if (amount > accountBalance) {
throw new InsufficientBalanceException("Insufficient funds. Withdrawal denied.");
}
accountBalance -= amount;
System.out.println("Withdrawal successful.");
System.out.println("Remaining balance: Rs. " + accountBalance);
}
}
public class ExceptionHandling {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter account holder name: ");
String name = sc.nextLine();
System.out.print("Enter account balance: ");
double balance = sc.nextDouble();
System.out.print("Enter withdrawal amount: ");
double amount = sc.nextDouble();
Account account = new Account(name, balance);
System.out.println("Account Holder: " + account.accountHolderName);
System.out.println("Available Balance: Rs. " + account.accountBalance);
try {
account.withdraw(amount);
} catch (InsufficientBalanceException e) {
System.out.println("Error: " + e.getMessage());
System.out.println("Remaining balance: Rs. " + account.accountBalance);
} catch (IllegalArgumentException e) {
System.out.println("Error: " + e.getMessage());
System.out.println("Remaining balance: Rs. " + account.accountBalance);
}
sc.close();
}
}
/*
Sample Input:
Rahul
8000
3000
Sample Output:
Enter account holder name: Enter account balance: Enter withdrawal amount: Account Holder: Rahul
Available Balance: Rs. 8000.0
Withdrawal successful.
Remaining balance: Rs. 5000.0
*/