Blog Application - Spring Boot + MySQL (CURRENTLY WORKING)

📌 Introduction

This is a Blog Application built using Spring Boot and MySQL. The app allows users to create, update, and manage blog posts securely using JWT authentication.

🔧 Tech Stack

Backend: Java, Spring Boot

Database: MySQL

Security: Spring Security, JWT Authentication

Build Tool: Maven

Version Control: Git & GitHub

📂 Project Structure

|--- src/main/java/com/codewithnik/blogapp
|   |-- controllers/   # Handles HTTP requests
|   |-- entities/      # Database entities
|   |-- repositories/  # JPA repositories
|   |-- services/      # Business logic
|   |-- config/        # Security & CORS configurations
|--- src/main/resources
|   |-- application.properties  # Database config
|--- pom.xml            # Maven dependencies
|--- README.md          # Project documentation

📑 Features

✔️ User Registration & Login (JWT Auth)
✔️ CRUD operations for Blog Posts
✔️ Commenting on Posts
✔️ User Roles & Authorization
✔️ Secure API Endpoints

⚙️ Setup & Installation

1️⃣ Clone Repository

git clone https://github.com/codewithnik20/JAVA-Spring-Boot-Project.git
cd JAVA-Spring-Boot-Project

2️⃣ Configure MySQL Database

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/blog_app_apis
spring.datasource.username=root
spring.datasource.password=202005
spring.jpa.hibernate.ddl-auto=update

3️⃣ Run the Application

mvn spring-boot:run

The backend will start at http://localhost:9091

🔗 API Endpoints

Method

Endpoint

Description

POST

/api/auth/signup

User Registration

POST

/api/auth/login

User Login (JWT)

GET

/api/posts

Get all posts

POST

/api/posts

Create new post

PUT

/api/posts/{id}

Update a post

DELETE

/api/posts/{id}

Delete a post

🚀 Future Enhancements

Implement Frontend using React/Angular

Add File Upload for blog images

Improve Commenting System

👨‍💻 Author

Nikhil Chauhan 📧 codingnik20@gmail.com 🔗 Git Hub User- codewithnik20
