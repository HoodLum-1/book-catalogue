# Book catalogue App

Welcome to the Book catalogue App repository! This repository contains the Management Service and UI Service for managing and interacting with a book catalogue.

## 📂 Repository Structure

- `management-service`: Contains the source code and configuration files for the Management Service.
- `ui-service`: Contains the source code and configuration files for the UI Service.
- `README.md`: Provides an overview of the repository and instructions for getting the services up and running.
- `run-book-catalogue-services.sh`: Script to execute the book catalogue application (Both `management-service` & `ui-service`)
  - Execution Permissions: `permission denied` when executing script on Linux run this command: `chmod +x run-book-catalogue-services.sh`
- `run-management-service.sh`: Script to execute the management-service
- `run-ui-service`: Script to execute the ui-service


# 📚 Management Service

The Management Service is a Spring Boot application that serves as the backend for managing the bookEntity catalogue. It provides a RESTful API for performing CRUD operations on books and utilizes an in-memory database.

# 🖥️ UI Service

The UI Service is a Spring Boot application that provides a user interface for managing the book catalogue.
It allows users to view, add, edit, and delete books. The UI Service communicates with the Management Service's
REST API using a JAX-RS Client and generates web pages using Thymeleaf.

## 🚀 Getting Started

### Prerequisites
- Java 1.8 or higher
- Maven

### Installation
1. Clone the repository: `git clone https://github.com/HoodLum-1/book-catalogue`
2. Navigate to the project directory: `cd management-service`
3. Execute the scripts for your operating system (Linux or Win)
4. Access the UI by opening your web browser and navigating to `http://localhost:8080`.

## 📖 API Documentation

The Management Service provides API documentation using Swagger and Swagger UI. You can access the Swagger UI by
visiting the following URL in your web browser: `http://localhost:5000/swagger-ui/
`

The Swagger UI provides an interactive interface where you can explore the available API endpoints, view request/response models, and even test the API directly.

## API Endpoints
The Management Service exposes the following RESTful endpoints:

- `GET /api/books` - Retrieve a list of all books

- `GET /api/books/{id}` - Retrieve a specific bookEntity by ID

- `POST /api/books` - Add a new bookEntity

- `PUT /api/books/{id}` - Update an existing bookEntity

- `DELETE /api/books/{id}` - Delete a bookEntity

## 📚 Book Data

The bookEntity data consists of the following attributes:

- Name
- ISBN Number
- Publish Date (dd/MM/yyyy)
- Price (ZAR)
- Book Type (Hard Cover, Soft Cover, eBook, etc.)

## 🌐 User Interface

The UI Service provides the following functionality:

- List all books
- Add a new book
- Edit an existing book
- Delete a book


## 💻 Technologies Used

- Java
- Spring Boot
- Maven
  - In-memory Database (H2)
- Swagger and Swagger UI
- Thymeleaf
- JAX-RS Client
