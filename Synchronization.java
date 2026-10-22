class BankAccount {
private int accountNumber;
private String accountHolderName;
private double accountBalance;
BankAccount(int accountNumber, String accountHolderName, double accountBalance) {
this.accountNumber = accountNumber;
this.accountHolderName = accountHolderName;
this.accountBalance = accountBalance;
}
public synchronized void deposit(String user, double amount) {
if (amount <= 0) {
System.out.println(user + ": Invalid deposit amount.");
return;
}
accountBalance += amount;
System.out.printf("%s deposited Rs. %.2f. Balance: Rs. %.2f%n",
user, amount, accountBalance);
}
public synchronized void withdraw(String user, double amount) {
if (amount <= 0) {
System.out.println(user + ": Invalid withdrawal amount.");
} else if (amount > accountBalance) {
System.out.println(user + ": Insufficient Balance.");
} else {
accountBalance -= amount;
System.out.printf("%s withdrew Rs. %.2f. Balance: Rs. %.2f%n",
user, amount, accountBalance);
}
}
public synchronized void displayBalance() {
System.out.printf("Final balance for %s (Account %d): Rs. %.2f%n",
accountHolderName, accountNumber, accountBalance);
}
}
class UserTransaction extends Thread {
private BankAccount account;
private String user;
private String type;
private double amount;
UserTransaction(BankAccount account, String user, String type, double amount) {
this.account = account;
this.user = user;
this.type = type;
this.amount = amount;
}
public void run() {
if (type.equalsIgnoreCase("deposit")) {
account.deposit(user, amount);
} else {
account.withdraw(user, amount);
}
}
}
public class Synchronization {
/**
 * @param args
 * @throws InterruptedException
 */
public static void main(String[] args) throws InterruptedException {
BankAccount account = new BankAccount(1001, "Rahul", 10000);
UserTransaction t1 =
new UserTransaction(account, "User1", "deposit", 2000);
UserTransaction t2 =
new UserTransaction(account, "User2", "withdraw", 3000);
UserTransaction t3 =
new UserTransaction(account, "User3", "deposit", 1000);
UserTransaction t4 =
new UserTransaction(account, "User4", "withdraw", 2000);
t1.start();
t2.start();
