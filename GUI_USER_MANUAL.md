# CRUD GUI Application - User Manual

## Features

The CRUD GUI provides an intuitive interface for managing users with JSON file persistence:

- ✅ **Modern JavaFX Interface** - Professional desktop application
- ✅ **User Table** - View all users in a structured table
- ✅ **Create Users** - Add new users with ID, name, email, and age
- ✅ **Update Users** - Select a user and modify their information
- ✅ **Delete Users** - Remove users from the system
- ✅ **Real-time Sync** - All changes automatically saved to `users.json`
- ✅ **Data Selection** - Click on table rows to auto-populate the form

## Layout

The application is divided into three sections:

### Header (Blue Bar)
- Application title and description

### Main Content Area
**Left Side - User Table**
- Displays all users in a sortable table
- Columns: ID, Name, Email, Age
- Click any row to auto-populate the form on the right

**Right Side - Form Panel**
- **User ID**: Auto-populated when selecting a table row (read-only for ID)
- **Full Name**: User's full name
- **Email**: User's email address
- **Age**: User's age

### Buttons
- **Create** (Green) - Add a new user
- **Update** (Blue) - Modify the selected user
- **Delete** (Red) - Remove the selected user
- **Clear** (Gray) - Reset all form fields
- **Refresh List** (Blue) - Reload data from users.json

### Footer
- Status information about data persistence

## Installation & Setup

### Prerequisites
- Java 11 or higher
- Maven 3.6+

### Build the Project

```bash
cd /workspaces/test-codespace
mvn clean install
```

## Running the GUI Application

### Option 1: Using Maven

```bash
mvn javafx:run
```

### Option 2: Direct Compilation & Execution

```bash
mvn clean compile
mvn javafx:run
```

### Option 3: Running with Explicit Main Class

```bash
mvn clean compile exec:java -Dexec.mainClass="com.crud.ui.CrudGuiApplication"
```

## Usage Workflow

### 1. Creating a New User
1. Enter a unique **User ID** (number)
2. Enter **Full Name**
3. Enter **Email**
4. Enter **Age** (number)
5. Click **Create** button
6. Confirm the success message
7. User appears in the table

### 2. Viewing All Users
- All users are displayed in the table on the left
- You can sort by any column by clicking the column header

### 3. Updating a User
1. Click on a user row in the table to select them
2. Form fields auto-populate with user data
3. Modify the desired fields (Name, Email, Age)
4. Click **Update** button
5. Changes are saved immediately

### 4. Deleting a User
1. Click on a user row in the table to select them
2. Click **Delete** button
3. User is removed from the table and JSON file

### 5. Clearing the Form
1. Click **Clear** button to reset all fields
2. Table selection is also cleared

### 6. Refreshing Data
1. Click **Refresh List** to reload data from the JSON file
2. Useful if data was modified externally

## Data Storage

All user data is stored in **users.json** in the working directory:

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

## Validation Rules

- **ID**: Must be a unique number
- **Name**: Required, any text
- **Email**: Required, any email format
- **Age**: Must be a number

## Error Handling

The application provides clear error messages for:
- Empty required fields
- Invalid number inputs
- File I/O errors
- Duplicate IDs

## Keyboard Shortcuts (JavaFX Standard)

- **Tab**: Navigate between fields
- **Enter**: Submit form (depends on context)
- **Ctrl+R**: Refresh (may require custom binding)

## System Requirements

- **OS**: Windows, macOS, or Linux
- **Java**: Java 11+
- **RAM**: 512MB minimum
- **Display**: 1024x768 minimum resolution

## Troubleshooting

### "Module not found" Error
```bash
mvn clean install -U
```

### GUI doesn't display
- Ensure Java 11+ is installed
- Try running with explicit display: `export DISPLAY=:0` (Linux)

### File not found error
- The application creates `users.json` automatically in the working directory
- Ensure write permissions in the current directory

### Data not persisting
- Check that the application closed properly
- Verify `users.json` exists in the working directory
- Check file permissions

## Technology Stack

- **JavaFX 21** - Modern GUI framework
- **Jackson 2.15.2** - JSON serialization/deserialization
- **Java 11** - Base language
- **Maven** - Build automation

## Project Structure

```
src/main/java/com/crud/
├── ui/
│   └── CrudGuiApplication.java    # GUI Implementation
├── model/
│   └── User.java                  # User data model
├── repository/
│   └── UserRepository.java        # CRUD operations & JSON handling
├── Main.java                      # Command-line interface
└── example/
    └── CrudExample.java           # Programmatic example
```
