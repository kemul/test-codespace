# Web UI - Testing CRUD Operations

## Quick Start

### 1. Start the REST API Server

```bash
cd /workspaces/test-codespace
mvn clean compile
mvn spring-boot:run
```

Or use exec:

```bash
mvn exec:java -Dexec.mainClass="com.crud.WebApplication"
```

The server will start on **http://localhost:8080**

### 2. Access the Web UI

Open your browser and navigate to:
- **Main UI**: http://localhost:8080/
- **API Endpoints**: http://localhost:8080/api/users

### 3. Test CRUD Operations

#### Create User
1. Fill in the form fields (Name, Email, Age)
2. Click **"➕ Create User"** button
3. User appears in the table

#### Read Users
- All users automatically load and display in the table
- The list refreshes every 5 seconds

#### Update User
1. Click on a user row in the table or click **"Edit"** button
2. Form auto-fills with user data
3. Modify the fields
4. Click **"✏️ Update"** button

#### Delete User
1. Click on a user row or click **"🗑️ Delete"** button
2. Confirm deletion
3. User is removed

### 4. API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| POST | `/api/users` | Create user |
| PUT | `/api/users/{id}` | Update user |
| DELETE | `/api/users/{id}` | Delete user |

### 5. Testing with cURL

```bash
# Get all users
curl http://localhost:8080/api/users

# Get user by ID
curl http://localhost:8080/api/users/1

# Create user
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"id":1,"name":"John Doe","email":"john@example.com","age":30}'

# Update user
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Jane Doe","email":"jane@example.com","age":31}'

# Delete user
curl -X DELETE http://localhost:8080/api/users/1
```

### 6. Testing with Postman

1. Import the endpoints into Postman
2. Set request type and URL
3. For POST/PUT, set Body as raw JSON
4. Send request and view response

### Features

✅ Real-time user list with auto-refresh  
✅ Create new users  
✅ View user details  
✅ Update existing users  
✅ Delete users  
✅ Responsive design  
✅ Form validation  
✅ Success/Error alerts  
✅ Click-to-select users from table  

### Troubleshooting

**Port 8080 already in use?**
```bash
# Change port in application.properties
# Edit: src/main/resources/application.properties
# Change: server.port=8080 to server.port=8081
```

**CORS errors?**
- CORS is already configured for all origins
- If still having issues, check browser console for details

**Users not showing?**
- Check if users.json exists in the working directory
- Refresh the page
- Check browser console for errors

### Data Storage

Users are stored in `users.json` in the working directory as JSON:

```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "age": 30
  }
]
```
