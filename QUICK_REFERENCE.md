# CRUD Application - Quick Reference Guide

## 🚀 Quick Start

### For GUI (Recommended)
```bash
mvn javafx:run
```

### For Command-Line
```bash
mvn exec:java -Dexec.mainClass="com.crud.Main"
```

---

## 📋 GUI Interface Overview

### Layout
```
┌─────────────────────────────────────────────────────────────────┐
│                    User Management System                       │
│        Manage users with JSON file storage                      │
├──────────────────────────────────────────────────────────────┼──┤
│                                                                │  │
│  ┌────────────────────────────────────┐  ┌────────────────┐  │  │
│  │ Users List                         │  │  User Form     │  │  │
│  ├────┬──────────┬──────────┬────────┤  ├────────────────┤  │  │
│  │ ID │ Name     │ Email    │ Age    │  │ User ID        │  │  │
│  ├────┼──────────┼──────────┼────────┤  ├────────────────┤  │  │
│  │ 1  │ John Doe │ john@... │ 30     │  │ Full Name      │  │  │
│  │ 2  │ Jane     │ jane@... │ 28     │  ├────────────────┤  │  │
│  │ 3  │ Charlie  │ charlie..│ 25     │  │ Email          │  │  │
│  │    │          │          │        │  ├────────────────┤  │  │
│  │    │          │          │        │  │ Age            │  │  │
│  │    │          │          │        │  ├────────────────┤  │  │
│  │    │          │          │        │  │ [Create] [Upd] │  │  │
│  │    │          │          │        │  │ [Delete] [Clr] │  │  │
│  │    │          │          │        │  ├────────────────┤  │  │
│  │    │          │          │        │  │ [Refresh List] │  │  │
│  └────┴──────────┴──────────┴────────┘  └────────────────┘  │  │
│                                                                │  │
├──────────────────────────────────────────────────────────────┼──┤
│ Data is automatically saved to users.json                    │  │
└─────────────────────────────────────────────────────────────┴──┘
```

---

## 🎯 Common Tasks

### Create a New User
1. Enter **ID** (number)
2. Enter **Name**
3. Enter **Email**
4. Enter **Age** (number)
5. Click **Create**

### Edit an Existing User
1. Click a user row in the table
2. Form auto-fills with user data
3. Modify any field
4. Click **Update**

### Delete a User
1. Click a user row in the table (or enter ID)
2. Click **Delete**
3. Confirm deletion

### View All Users
- Users appear in the table automatically
- Click column headers to sort

---

## 📁 File Locations

| File | Purpose |
|------|---------|
| `users.json` | Data storage (auto-created) |
| `src/main/java/com/crud/ui/CrudGuiApplication.java` | GUI Implementation |
| `src/main/java/com/crud/Main.java` | CLI Menu |
| `pom.xml` | Maven Configuration |

---

## ⚙️ System Requirements

- **Java**: 11 or higher
- **Maven**: 3.6+
- **RAM**: 512MB minimum
- **Disk**: 200MB minimum

---

## 🔧 Troubleshooting

| Problem | Solution |
|---------|----------|
| "Module not found" | Run `mvn clean install -U` |
| GUI won't launch | Ensure Java 11+ is installed |
| Data not saving | Check write permissions in current directory |
| Build fails | Run `mvn clean compile` first |

---

## 📚 Commands Reference

```bash
# Build
mvn clean install

# Run GUI
mvn javafx:run

# Run CLI
mvn exec:java -Dexec.mainClass="com.crud.Main"

# Run Example
mvn exec:java -Dexec.mainClass="com.crud.example.CrudExample"

# Verify Build
bash verify-build.sh

# Quick Start (Linux/macOS)
bash run-gui.sh

# Quick Start (Windows)
run-gui.bat
```

---

## 📊 Data Format

Users are stored in JSON format:

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

---

## 🎨 GUI Features

✅ Modern JavaFX Interface  
✅ Real-time Table Updates  
✅ Form Validation  
✅ Auto-populate on Selection  
✅ Keyboard Navigation  
✅ Professional Styling  
✅ Error Alerts  
✅ Success Confirmations

---

## 📞 Support

For detailed documentation, see:
- [GUI_USER_MANUAL.md](GUI_USER_MANUAL.md) - Complete GUI guide
- [README_CRUD.md](README_CRUD.md) - Full project documentation

