import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/shopping")
public class ShoppingServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
@Override
public void init() throws ServletException {
System.out.println("ShoppingServlet initialized.");
}
@Override
protected void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
String action = request.getParameter("action");
try (var out = response.getWriter()) {
out.println("<html><body>");
out.println("<h2>Online Shopping Website</h2>");
if ("search".equalsIgnoreCase(action)) {
String product = request.getParameter("product");
out.println("<h3>Product Search</h3>");
if (product != null && !product.trim().isEmpty()) {
String item = product.trim().toLowerCase();
if (item.contains("laptop")) {
out.println("Product found: Laptop - Rs. 50000");
} else if (item.contains("phone")) {
out.println("Product found: Mobile Phone - Rs. 20000");
} else if (item.contains("headphone")) {
out.println("Product found: Headphones - Rs. 1500");
} else {
out.println("Product not found.");
}
} else {
out.println("Please enter a product name.");
}
} else {
out.println("<h3>Available Options</h3>");
out.println("<p>Use action=search to search for products.</p>");
out.println("<p>Use the login and order forms to submit POST requests.</p>");
}
out.println("</body></html>");
}
}
@Override
protected void doPost(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");
String action = request.getParameter("action");
try (var out = response.getWriter()) {
out.println("<html><body>");
out.println("<h2>Online Shopping Website</h2>");
if ("login".equalsIgnoreCase(action)) {
String username = request.getParameter("username");
String password = request.getParameter("password");
if ("customer".equals(username) && "1234".equals(password)) {
out.println("<h3>Login Successful!</h3>");
out.println("Welcome, " + escapeHtml(username) + ".");
} else {
out.println("<h3>Invalid Username or Password.</h3>");
}
} else if ("order".equalsIgnoreCase(action)) {
String customer = request.getParameter("customer");
String product = request.getParameter("product");
String quantityText = request.getParameter("quantity");
try {
int quantity = Integer.parseInt(quantityText);
if (customer == null || customer.trim().isEmpty()
|| product == null || product.trim().isEmpty()
|| quantity <= 0) {
out.println("Please enter valid customer, product, and quantity details.");
} else {
out.println("<h3>Order Placed Successfully!</h3>");
out.println("Customer: " + escapeHtml(customer) + "<br>");
out.println("Product: " + escapeHtml(product) + "<br>");
out.println("Quantity: " + quantity);
}
} catch (NumberFormatException e) {
out.println("Invalid quantity. Please enter a positive number.");
}
} else {
out.println("Invalid request. Please select a valid option.");
}
out.println("</body></html>");
}
}
private String escapeHtml(String value) {
if (value == null) return "";
return value.replace("&", "&amp;")
.replace("<", "&lt;")
.replace(">", "&gt;")
.replace("\"", "&quot;")
.replace("'", "&#39;");
}
@Override
public void destroy() {
System.out.println("ShoppingServlet destroyed.");
}
}
/*
SAMPLE INPUT / REQUESTS
1. Login (HTTP POST to /shopping)
action=login
username=customer
password=1234
2. Product Search (HTTP GET to /shopping)
action=search
product=laptop
3. Place Order (HTTP POST to /shopping)
action=order
customer=Rahul
product=Laptop
quantity=1
SAMPLE OUTPUT
1. Login:
Online Shopping Website
Login Successful!
Welcome, customer.
2. Product Search:
Online Shopping Website
Product Search
Product found: Laptop - Rs. 50000
3. Order Placement:
Online Shopping Website
Order Placed Successfully!
Customer: Rahul
Product: Laptop
Quantity: 1
4. Invalid Login:
Online Shopping Website
Invalid Username or Password.
5. Product Not Found:
Online Shopping Website
Product Search
Product not found.
Note: This code uses Jakarta Servlet API (Tomcat 10 or later).
For Tomcat 9 or older, replace jakarta.servlet imports with javax.servlet.
Deploy the compiled servlet in a web application and access /shopping.
*/