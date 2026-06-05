package com.crud.repository;

import com.crud.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private static final String JSON_FILE = "users.json";
    private List<User> users;
    private ObjectMapper objectMapper;

    public UserRepository() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.users = new ArrayList<>();
        loadFromFile();
    }

    /**
     * CREATE - Add a new user
     */
    public void create(User user) throws IOException {
        users.add(user);
        saveToFile();
        System.out.println("User created: " + user.getName());
    }

    /**
     * READ - Get all users
     */
    public List<User> readAll() {
        return new ArrayList<>(users);
    }

    /**
     * READ - Get user by ID
     */
    public Optional<User> readById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    /**
     * UPDATE - Update an existing user
     */
    public void update(int id, User updatedUser) throws IOException {
        Optional<User> existingUser = readById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setAge(updatedUser.getAge());
            saveToFile();
            System.out.println("User updated with ID: " + id);
        } else {
            System.out.println("User with ID " + id + " not found");
        }
    }

    /**
     * DELETE - Delete a user by ID
     */
    public void delete(int id) throws IOException {
        boolean removed = users.removeIf(user -> user.getId() == id);
        if (removed) {
            saveToFile();
            System.out.println("User deleted with ID: " + id);
        } else {
            System.out.println("User with ID " + id + " not found");
        }
    }

    /**
     * Save all users to JSON file
     */
    private void saveToFile() throws IOException {
        File file = new File(JSON_FILE);
        objectMapper.writeValue(file, users);
    }

    /**
     * Load all users from JSON file
     */
    private void loadFromFile() {
        File file = new File(JSON_FILE);
        if (file.exists()) {
            try {
                User[] userArray = objectMapper.readValue(file, User[].class);
                users.clear();
                if (userArray != null) {
                    for (User user : userArray) {
                        users.add(user);
                    }
                }
                System.out.println("Data loaded from " + JSON_FILE);
            } catch (IOException e) {
                System.err.println("Error loading users from file: " + e.getMessage());
                users = new ArrayList<>();
            }
        } else {
            System.out.println("JSON file does not exist. Starting with empty list.");
            users = new ArrayList<>();
        }
    }

    /**
     * Display all users
     */
    public void displayAll() {
        System.out.println("\n=== All Users ===");
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            users.forEach(System.out::println);
        }
        System.out.println();
    }
}
