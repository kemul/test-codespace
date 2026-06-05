# JSON CRUD Application

A complete Java CRUD (Create, Read, Update, Delete) application with two interfaces:
- **Modern JavaFX GUI** - Professional desktop application with table and form interface
- **Command-line CLI** - Interactive terminal-based interface

Both interfaces read and write data to a JSON file locally using Jackson.

## Project Structure

```
├── pom.xml                          # Maven configuration with JavaFX & Jackson
├── run-gui.sh                       # Quick-start script (Linux/macOS)
├── run-gui.bat                      # Quick-start script (Windows)
├── GUI_USER_MANUAL.md               # Detailed GUI documentation
├── README_CRUD.md                   # This file
└── src/main/java/com/crud/
    ├── ui/
    │   └── CrudGuiApplication.java  # JavaFX GUI Application
    ├── Main.java                    # CLI Interactive menu
    ├── model/
    │   └── User.java               # User data model
    ├── repository/
    │   └── UserRepository.java      # CRUD operations
    └── example/
        └── CrudExample.java         # Programmatic usage example
```

## Features

- **Create**: Add new users to the JSON file
- **Read**: Retrieve all users or find by ID
- **Update**: Modify existing user information
- **Delete**: Remove users from the JSON file
- **Persistence**: All data is saved to `users.json` in the local directory

## Prerequisites

- Java 11 or higher
- Maven 3.6+

## Build

```bash
mvn clean install
```

## Run the Application

### GUI Application (Recommended)

```bash
mvn javafx:run
```

Or use the quick-start script:

**Linux/macOS:**
```bash
bash run-gui.sh
```

**Windows:**
```bash
run-gui.bat
```

### Command-Line Interactive Menu

```bash
mvn exec:java -Dexec.mainClass="com.crud.Main"
```

### Programmatic Example

```bash
mvn exec:java -Dexec.mainClass="com.crud.example.CrudExample"
```

## Usage Example

### GUI Application Features

The modern JavaFX interface provides:
- **User Table** with sortable columns (ID, Name, Email, Age)
- **Form Panel** for creating, updating, and deleting users
- **Real-time Data Sync** - Changes automatically saved to JSON
- **Click-to-Edit** - Select table rows to populate the form
- **Validation** - Checks for required fields and data types

See [GUI_USER_MANUAL.md](GUI_USER_MANUAL.md) for detailed GUI documentation.

### Interactive Menu
The application provides a menu-driven interface:
```
=== CRUD Menu ===
1. Create User
2. Read All Users
3. Read User by ID
4. Update User
5. Delete User
6. Exit
```

### JSON File Format

After operations, data is saved in `users.json`:

```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "age": 30
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "email": "jane@example.com",
    "age": 28
  }
]
```

## Data Model - User Class

The User class has the following properties:
- `id` (int): Unique identifier
- `name` (String): User's full name
- `email` (String): User's email address
- `age` (int): User's age

## Technology Stack

- **JavaFX 21** - Modern GUI framework for desktop applications
- **Jackson 2.15.2** - JSON serialization and deserialization
- **Java 11+** - Programming language
- **Maven** - Build and dependency management
