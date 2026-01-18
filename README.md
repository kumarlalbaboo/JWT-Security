# 🔐 JWT Authentication & Authorization – Spring Boot 3

This project demonstrates **JWT-based authentication and authorization**
using **Spring Boot 3**, **Spring Security 6**, and **JPA**.

It includes:
- User Registration
- User Login
- JWT Token Generation & Validation
- Stateless Security Configuration
- Custom JWT Filter

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security 6
- Spring Data JPA
- JWT (jjwt 0.11+)
- H2 / MySQL (configurable)
- Maven

---

## 📁 Project Structure

        src/main/java/com/llb
        ├── configuration
        │ └── AppSecurityConfig.java
        ├── controller
        │ └── CustomerController.java
        ├── filter
        │ └── AppFilter.java
        ├── model
        │ └── Customer.java
        ├── repository
        │ └── CustomerRepository.java
        ├── service
        │ ├── CustomerService.java
        │ └── JwtService.java
        └── JwtSecurityApplication.java

🔐 Security Flow

        1. User registers (/api/register)
        
        2. User logs in (/api/login)
        
        3. JWT token is generated
        
        4. Client sends token in header:
                Authorization: Bearer <JWT_TOKEN>
        
        5. OncePerRequestFilter validates token
        
        6. SecurityContext is set
        
        7. Protected APIs are accessible

        🚀 API Endpoints
        Public APIs
                Method	Endpoint	Description
                
                POST	/api/register	Register user
                
                POST	/api/login	Login & get JWT
        
        Protected APIs
                Method	Endpoint	Description
                
                GET	/api/**	Requires JWT
