# 🍬 Sweet Shop Management System

A full-stack **Sweet Shop Management System** built using **Spring Boot (Java)** for the backend and **React** for the frontend. The application supports **JWT-based authentication**, **role-based authorization**, inventory management, and follows **clean architecture and TDD principles**.

---

## 🚀 Features

### 🔐 Authentication & Authorization
- User registration and login
- Password encryption using BCrypt
- JWT-based stateless authentication
- Role-based access control (USER / ADMIN)

### 🍭 Sweet Management
- Add new sweets
- View all available sweets
- Search sweets by:
  - Name
  - Category
  - Price range
- Purchase sweets (quantity decreases automatically)
- Restock sweets (ADMIN only)
- Delete sweets (ADMIN only)

### 🧪 Quality & Architecture
- Layered architecture (Controller → Service → Repository)
- DTO-based API design
- Global exception handling
- Unit tests using **JUnit & Mockito** (TDD aligned)

---

## 🛠 Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT (JSON Web Token)
- Lombok
- JUnit & Mockito

### Frontend
- React
- Axios
- React Router
- HTML, CSS, JavaScript

### Tools
- IntelliJ IDEA (Backend)
- VS Code (Frontend)
- Postman (API Testing)
- pgAdmin (Database Management)

---

## 📂 Project Structure

```
sweet-shop-project
├── sweet-shop-backend
│   ├── controller
│   ├── service
│   ├── repository
│   ├── model
│   ├── dto
│   ├── exception
│   └── config
│
└── sweet-shop-frontend
    ├── api
    ├── auth
    ├── components
    ├── pages
    └── services
```

---

## ⚙️ Setup Instructions

### 1️⃣ Backend Setup (Spring Boot)

#### Prerequisites
- Java 17
- PostgreSQL
- Maven

#### Steps
1. Clone the repository
2. Create PostgreSQL database:
   ```sql
   CREATE DATABASE sweet_shop_db;
   ```
3. Update `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/sweet_shop_db
   spring.datasource.username=postgres
   spring.datasource.password=YOUR_PASSWORD
   ```
4. Run the application from IntelliJ:
   ```
   SweetShopApplication
   ```
5. Backend runs on:
   ```
   http://localhost:8080
   ```

---

### 2️⃣ Frontend Setup (React)

#### Prerequisites
- Node.js (v18+ recommended)
- npm

#### Steps
```bash
cd sweet-shop-frontend
npm install
npm start
```

Frontend runs on:
```
http://localhost:3000
```

---

## 🔑 API Endpoints

### Authentication
- `POST /api/auth/register`
- `POST /api/auth/login`

### Sweets
- `GET /api/sweets`
- `POST /api/sweets`
- `GET /api/sweets/search`
- `POST /api/sweets/{id}/purchase`

### Admin Only
- `DELETE /api/sweets/{id}`
- `POST /api/sweets/{id}/restock`

---

## 🧪 Running Tests

To run backend unit tests:

```bash
mvn test
```

✔ Tests cover:
- SweetService business logic
- UserService registration logic
- Failure scenarios (not found, insufficient stock)

---

## 📸 Screenshots (Add Before Submission)

Include screenshots in the repository:
- Login Page
- Dashboard
- Admin actions (Delete / Restock)
- Postman JWT testing
- Test execution results

---

## 🤖 My AI Usage (Mandatory)

### AI Tools Used
- ChatGPT (OpenAI)

### How AI Was Used
- Designing backend and frontend architecture
- Generating boilerplate Spring Boot and React code
- Writing and refining unit test cases
- Debugging JWT authentication and Spring Security issues
- Structuring frontend React components

### Reflection
AI significantly improved development speed by assisting with design decisions, boilerplate generation, and debugging. All AI-generated content was **reviewed, modified, and understood** before being integrated into the project.

---



---

## ✅ Submission Checklist

- [x] Backend API complete
- [x] PostgreSQL database integrated
- [x] JWT authentication implemented
- [x] Role-based authorization
- [x] Unit tests written
- [x] Frontend integrated
- [x] AI usage documented

---

## 🏁 Final Note

This project follows **clean coding practices**, **modern backend security standards**, and **test-driven development principles**, making it suitable for academic evaluation and technical interviews.

