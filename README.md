Blog App Backend - Spring Boot & JWT 🔐
This is a RESTful backend for a Blog Application built using Spring Boot, secured with JWT Authentication. It includes features like user registration, login, role-based access, post and comment management, and more.

🚀 Features
JWT-based Authentication and Authorization

User registration & login with password hashing

Role-based access control (admin/user)

CRUD operations for:

Posts

Comments

Users

Secure password storage using BCrypt

Exception handling and proper status responses

🧑‍💻 Technologies Used
Java 17

Spring Boot

Spring Security

JWT (JSON Web Token)

MySQL / H2 Database

Maven

Lombok

Postman (for API testing)

📦 Project Structure
bash
Copy
Edit
com.nikstack.blogapp
│
├── controllers       # API endpoints
├── services          # Business logic
├── repositories      # Data access layer
├── entities          # JPA Entities
├── config            # Security and app configs
├── payloads          # Request & Response DTOs
└── utils             # Helper classes (e.g. JWT utils)

📲 API Endpoints
Method	Endpoint	Access	Description
POST	/api/auth/register	Public	Register a new user
POST	/api/auth/login	Public	Login with credentials
GET	/api/users	Admin Only	Fetch all users
POST	/api/posts	User/Admin	Create a blog post
PUT	/api/posts/{id}	Owner/Admin	Update post
DELETE	/api/comments/{id}	Owner/Admin	Delete comment

⚠️ For secured routes, include Authorization: Bearer <JWT Token> in headers.

🛠️ Setup Instructions
Clone the repo

bash
Copy
Edit
git clone https://github.com/NikStack/JAVA-Spring-Boot-Project.git
cd JAVA-Spring-Boot-Project
Update application.properties with your DB config

Run the app using:

bash
Copy
Edit
./mvnw spring-boot:run
Use Postman to test the API

📄 License
This project is licensed under MIT - feel free to use it for learning or as a base for your own app.

👨‍💻 Author
Nikhil Chauhan 📧 codingnik20@gmail.com 🔗 Git Hub User- NikStack
