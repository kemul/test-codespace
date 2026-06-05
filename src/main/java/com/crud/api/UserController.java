package com.crud.api;

import com.crud.model.User;
import com.crud.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserRepository userRepository;

    public UserController() {
        this.userRepository = new UserRepository();
    }

    /**
     * GET /api/users - Get all users
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.readAll();
        return ResponseEntity.ok(users);
    }

    /**
     * GET /api/users/{id} - Get user by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        Optional<User> user = userRepository.readById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + id + " not found");
        }
    }

    /**
     * POST /api/users - Create a new user
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userRepository.create(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User created successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating user: " + e.getMessage());
        }
    }

    /**
     * PUT /api/users/{id} - Update a user
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user) {
        try {
            Optional<User> existingUser = userRepository.readById(id);
            if (existingUser.isPresent()) {
                userRepository.update(id, user);
                return ResponseEntity.ok("User updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User with ID " + id + " not found");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating user: " + e.getMessage());
        }
    }

    /**
     * DELETE /api/users/{id} - Delete a user
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        try {
            Optional<User> user = userRepository.readById(id);
            if (user.isPresent()) {
                userRepository.delete(id);
                return ResponseEntity.ok("User deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User with ID " + id + " not found");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting user: " + e.getMessage());
        }
    }

    /**
     * GET /api/health - Health check
     */
    @GetMapping("/health/check")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok("CRUD API is running");
    }
}
