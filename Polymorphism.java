import java.util.Scanner;
class Payment {
void makePayment(double amount) {
System.out.println("Payment amount: Rs. " + amount);
}
void makePayment(double amount, String transactionId) {
System.out.println("Transaction ID: " + transactionId);
makePayment(amount);
}
}
class CreditCardPayment extends Payment {
@Override
void makePayment(double amount) {
System.out.println("Credit Card Payment: Rs. " + amount);
}
}
class UPIPayment extends Payment {
@Override
void makePayment(double amount) {
System.out.println("UPI Payment: Rs. " + amount);
}
}
class NetBankingPayment extends Payment {
@Override
void makePayment(double amount) {
System.out.println("Net Banking Payment: Rs. " + amount);
}
}
public class Polymorphism {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter payment type (1-Credit Card, 2-UPI, 3-Net Banking): ");
int type = sc.nextInt();
System.out.print("Enter payment amount: ");
double amount = sc.nextDouble();
sc.nextLine();
System.out.print("Enter transaction ID: ");
String transactionId = sc.nextLine();
Payment payment;
switch (type) {
case 1:
payment = new CreditCardPayment();
break;
case 2:
payment = new UPIPayment();
break;
case 3:
payment = new NetBankingPayment();
break;
default:
System.out.println("Invalid payment type");
sc.close();
return;
}
payment.makePayment(amount, transactionId);
sc.close();
}
}