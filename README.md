# HOW TO RUN
1. Install XAMPP (https://www.apachefriends.org/)
2. Turn on "Apache" and "MySQL"
3. Create a new database schema named "order_manage_db" here (http://localhost/phpmyadmin/)
4. Open the project in IntelliJ and hit run!

# EXPLANATION
This is a web app made with JSP, JBDC, and MySQL made under the following assignment instructions: Develop the web application using  technologies "JSP + Servlet + JDBC + MySQL" or "Spring/Spring Boot", for the below scenario.

Order Management System

The web-based “Order management system” enables the customer to do the things such as view all available products, and products by category-wise distribution. The user can place the order, the delivery agent will get the order details and it will be delivered to the customer at their residence.  
The Order Management System provides a facility that enables users to view details about products without logging in. But if the user wants to place the order, then it is required to log in first. Users can view all available products with detailed descriptions, reviews, and ratings. Users can choose a particular product. The system checks for the availability of the quantity of the product. If the product is available then the system allows the customer to select the product and place an order. To order a product the system asks the customer to enter his details such as first name, last name, city, street, landmark, state, pin, phone number, etc.

# IMAGES
## CartDAO
![image](https://github.com/user-attachments/assets/d094788c-49c0-4b9f-8ff4-458ef2051311)

## OrderDAO
![image](https://github.com/user-attachments/assets/23ee9b39-9fdd-46c9-b401-9016fbfd5e47)
![image](https://github.com/user-attachments/assets/cf8138fe-1e36-46d7-882a-2acade202031)
![image](https://github.com/user-attachments/assets/4cb7de12-2f7f-4afe-94c5-15a4921cca2f)

## ProductDAO
![image](https://github.com/user-attachments/assets/c067f185-14ca-46e5-aec6-029b986d8f42)
![image](https://github.com/user-attachments/assets/b73de51f-106f-463a-8245-b8d198a9a4ad)

## UserDAO
![image](https://github.com/user-attachments/assets/d1ff7ff6-80dd-4562-879c-acd9772955dd)

## Order
![image](https://github.com/user-attachments/assets/fb7bded3-8477-448b-afa3-f67140c20561)
![image](https://github.com/user-attachments/assets/632f5042-6375-4bdf-b16c-182d2f50f760)

## OrderItem
![image](https://github.com/user-attachments/assets/27636801-5388-4a2b-bba4-ef628b5bd67f)

## Product
![image](https://github.com/user-attachments/assets/4043a1e6-5fa9-486e-8aff-15cb1f7efc71)

## User
![image](https://github.com/user-attachments/assets/34653b88-5691-4a01-8810-ddddc589d47c)

## DBConnection
![image](https://github.com/user-attachments/assets/c613ae30-4c3e-46c3-810a-de0fd2bf90ce)

## PasswordHasher
![image](https://github.com/user-attachments/assets/ab624244-c1a0-44c2-9a94-15f5aba76669)

## CartServlet
![image](https://github.com/user-attachments/assets/e7c2020f-c800-43f6-9165-09a09b2f0185)

## CheckoutServlet
![image](https://github.com/user-attachments/assets/4c8d602a-2c10-4da8-80e3-8e2a1be55835)

## LoginServlet
![image](https://github.com/user-attachments/assets/30e25b17-e93c-4bb7-9e76-149fcc880d84)

## OrderServlet
![image](https://github.com/user-attachments/assets/e883ea5d-382c-4fc3-bc43-bc38b1ab7c17)
![image](https://github.com/user-attachments/assets/1601781d-73aa-4413-95e4-385b1f947384)

## ProductServlet
![image](https://github.com/user-attachments/assets/6e9954f2-ac8f-464f-8476-4ad40ead6b22)

## RegisterServlet
![image](https://github.com/user-attachments/assets/32a49808-53e2-4494-b5e5-ffe201c9aa51)

## cart.jsp
![image](https://github.com/user-attachments/assets/50235ca6-0a39-4445-9925-03490e08bd7d)

## checkout.jsp
![image](https://github.com/user-attachments/assets/ae7f755a-ceac-4dbb-8f7b-6f3fd58859f4)
![image](https://github.com/user-attachments/assets/0ad1d3de-62f6-41d4-b117-9487616418e7)

## index.jsp
![image](https://github.com/user-attachments/assets/50d61130-f63c-42da-b1bb-31d74d5e83cb)
![image](https://github.com/user-attachments/assets/df1f795b-354d-4f92-8522-67fd38f0f9c6)
![image](https://github.com/user-attachments/assets/77b9033f-a669-4a07-b90b-080ee63850ef)

## login.jsp
![image](https://github.com/user-attachments/assets/235f18ef-916b-4082-831b-3100cbcf6f49)

## logout.jsp
![image](https://github.com/user-attachments/assets/17923492-bc56-47b2-9493-94d6e339a37a)

## orders.jsp
![image](https://github.com/user-attachments/assets/c02178d7-800e-4448-80ed-ae6a69063bb4)

## productdetails.jsp
![image](https://github.com/user-attachments/assets/fe4a1ed0-3b5b-4bd3-80ad-e307388b0a28)

## register.jsp
![image](https://github.com/user-attachments/assets/2051a908-7e11-4cfe-9f96-0a313af1c841)

## Cart Page
![image](https://github.com/user-attachments/assets/dd59d017-7ba1-4764-a0ea-f579ce2ce37c)

## Checkout Page
![image](https://github.com/user-attachments/assets/3990e342-90d4-4852-be77-b8a7892b81ac)

## Index Page
![image](https://github.com/user-attachments/assets/3c507626-9c67-4af3-b29c-4b34377eebaf)

## Login Page
![image](https://github.com/user-attachments/assets/9ac1151a-aa7e-4d44-b0b0-87548263f588)

## Logout Page
![image](https://github.com/user-attachments/assets/dc1e5784-256f-435c-ae26-d2df162d9788)

## Orders Page
![image](https://github.com/user-attachments/assets/dbb608e0-ee07-4c04-b1f7-54859f4f8d66)

## Product Details Page
![image](https://github.com/user-attachments/assets/9a505d7d-96ae-4900-aa2c-2744ed8a55f9)
