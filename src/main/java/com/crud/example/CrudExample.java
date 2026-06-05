package com.crud.example;

import com.crud.model.User;
import com.crud.repository.UserRepository;

import java.io.IOException;

/**
 * Example demonstrating programmatic usage of UserRepository CRUD operations
 */
public class CrudExample {
    public static void main(String[] args) throws IOException {
        UserRepository repository = new UserRepository();

        // CREATE - Add users
        System.out.println("=== CREATE Operations ===");
        repository.create(new User(1, "Alice Johnson", "alice@example.com", 25));
        repository.create(new User(2, "Bob Smith", "bob@example.com", 32));
        repository.create(new User(3, "Charlie Brown", "charlie@example.com", 28));

        // READ - Get all users
        System.out.println("\n=== READ All Operations ===");
        repository.displayAll();

        // READ - Get single user
        System.out.println("=== READ by ID Operations ===");
        repository.readById(1).ifPresent(user -> System.out.println("Found: " + user));
        repository.readById(999).ifPresentOrElse(
            user -> System.out.println("Found: " + user),
            () -> System.out.println("User with ID 999 not found")
        );

        // UPDATE - Modify a user
        System.out.println("\n=== UPDATE Operations ===");
        User updatedUser = new User(2, "Bob Wilson", "bob.wilson@example.com", 33);
        repository.update(2, updatedUser);
        repository.displayAll();

        // DELETE - Remove a user
        System.out.println("\n=== DELETE Operations ===");
        repository.delete(3);
        repository.displayAll();

        System.out.println("Data persisted in users.json");
    }
}
