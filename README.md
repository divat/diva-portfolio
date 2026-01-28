# Expense Tracker Application

A backend-focused Expense Tracker REST API built using Spring Boot to manage personal expenses with clean architecture, proper validation, and scalable design.

This project demonstrates real-world backend development practices rather than just basic CRUD operations.

## Features

- Create, update, delete expenses
- Categorize expenses (Food, Travel, Bills, etc.)
- View expense history
- RESTful API design
- Input validation and centralized exception handling
- Layered architecture (Controller, Service, Repository)

## Tech Stack
 - Java 17
 - Spring Boot
 - Spring Web
 - Spring Data JPA
 - Hibernate
 - H2 / MySQL
 - Maven
 - Multi-Module Maven project

## Project Structure
com.example.expensetracker
│
├── controller      // REST controllers
├── service         // Business logic
├── domain      // Database access layer
├── persistence          // JPA entities
├── dto             // Request/Response DTOs
├── exception       // Custom exceptions & handlers
└── config          // Configuration classes

# API Endpoints
## Method	Endpoint	Description
- POST	/api/expenses	Create a new expense
- GET	/api/expenses	Get all expenses
- GET	/api/expenses/{id}	Get expense by ID
- PUT	/api/expenses/{id}	Update an expense
- DELETE	/api/expenses/{id}	Delete an expense

Sample Request
{
  "amount": 1200,
  "category": "Food",
  "description": "Lunch",
  "date": "2026-01-25"
}

## Validation & Error Handling

- Request validation using annotations
- Proper HTTP status codes
- Clean and readable error responses
  
Example error response:
{
  "message": "Amount must be greater than zero",
  "timestamp": "2026-01-26T10:30:00"
}

## How to Run the Project
git clone https://github.com/your-username/expense-tracker.git
cd expense-tracker
mvn spring-boot:run

Application runs at:
http://localhost:8080

# Future Enhancements
- User authentication (JWT)
- Monthly and yearly reports
- Category-wise analytics
- Budget limits and alerts
- Frontend integration (React)

Purpose of This Project

## This project is built to demonstrate:

- Backend system design
- Clean Spring Boot architecture
- API design best practices
- Real-world coding standards
- Author
