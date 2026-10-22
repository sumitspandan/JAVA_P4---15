class BookingRunnable implements Runnable {
private final String passengerName;
BookingRunnable(String passengerName) {
this.passengerName = passengerName;
}
@Override
public void run() {
System.out.println("Passenger: " + passengerName);
System.out.println("Thread ID: " + Thread.currentThread().getId());
System.out.println("Booking Status: Booked");
}
}
class BookingThread extends Thread {
private final String passengerName;
BookingThread(String passengerName) {
this.passengerName = passengerName;
}
@Override
public void run() {
System.out.println("Passenger: " + passengerName);
System.out.println("Thread ID: " + Thread.currentThread().getId());
System.out.println("Booking Status: Booked");
}
}
public class Multithreading {
public static void main(String[] args) {
Thread t1 = new BookingThread("Rahul");
Thread t2 = new BookingThread("Priya");
Thread t3 = new Thread(new BookingRunnable("Aman"));
Thread t4 = new Thread(new BookingRunnable("Neha"));
t1.start();
t2.start();
t3.start();
t4.start();
}
}
/*
Sample Output (order may vary):
Passenger: Rahul
Thread ID: 12
Booking Status: Booked
Passenger: Priya
Thread ID: 13
Booking Status: Booked
Passenger: Aman
Thread ID: 14
Booking Status: Booked
Passenger: Neha
Thread ID: 15
Booking Status: Booked
*/