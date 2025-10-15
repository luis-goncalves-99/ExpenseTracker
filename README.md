# Kotlin Expense Tracker

A RESTful API application for managing personal expenses, built with Kotlin and Spring Boot.
This application allows users to track their expenses by category, providing a clean and efficient way to monitor spending habits.

## 🚀 Features

- **CRUD Operations**: Create, read, update, and delete expenses
- **Category Management**: Organize expenses by categories
- **RESTful API**: Clean REST endpoints for expense management
- **Data Persistence**: H2 in-memory database for development and testing
- **Validation**: Input validation for expense data
- **JSON Support**: Full JSON serialization/deserialization

## 🛠️ Technologies Used

### Backend Framework

- **Kotlin** - Modern JVM programming language
- **Spring Boot 3.2.0** - Application framework
- **Spring Web** - REST API development
- **Spring Data JPA** - Data persistence layer
- **Spring Validation** - Input validation

### Database

- **H2 Database** - In-memory database for development
- **Hibernate** - ORM framework

### Build & Dependencies

- **Gradle** - Build automation tool with Kotlin DSL
- **Jackson Kotlin Module** - JSON serialization support
- **JUnit 5** - Testing framework

### Development Environment

- **Java 17** - Runtime environment
- **Kotlin 1.9.21** - Language version

## 📋 Prerequisites

- Java 17 or higher
- Gradle (wrapper included)

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone <repository-url>
cd KotlinExpenseTracker
```

### 2. Build the project

```bash
./gradlew build
```

### 3. Run the application

```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080`

### 4. Access H2 Console (Optional)

Visit `http://localhost:8080/h2-console` to access the H2 database console:

- **JDBC URL**: `jdbc:h2:mem:expensetrackerdb`
- **Username**: `sa`
- **Password**: (leave empty)

## 📚 API Endpoints

### Home

- `GET /` - Welcome message and API info

### Expenses

- `GET /expenses` - Get all expenses
- `GET /expenses/{id}` - Get expense by ID
- `POST /expenses` - Create new expense
- `PUT /expenses/{id}` - Update expense
- `DELETE /expenses/{id}` - Delete expense
- `POST /expenses/batch` - Create multiple expenses

## 🧪 Running Tests

```bash
./gradlew test
```

## 📊 Project Structure

```
src/
├── main/
│   ├── kotlin/expense/tracker/
│   │   ├── ExpenseTracker.kt          # Main application class
│   │   ├── ExpenseController.kt       # REST controller
│   │   ├── ExpenseService.kt          # Business logic
│   │   ├── ExpenseRepository.kt       # Data access layer
│   │   ├── Expense.kt                 # Expense entity
│   │   ├── Category.kt                # Category entity
│   │   ├── ExpenseRequest.kt          # Request DTOs
│   │   └── ApiResponse.kt             # Response DTOs
│   └── resources/
│       └── application.properties     # Application configuration
└── test/
    └── kotlin/expense/tracker/
        ├── ExpenseTrackerTests.kt     # Integration tests
        └── ExpenseRepositoryTest.kt   # Repository tests
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## 📝 License

This project is open source and available under the [MIT License](LICENSE).
