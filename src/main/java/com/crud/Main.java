package com.crud;

import com.crud.model.User;
import com.crud.repository.UserRepository;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

/**
 * CLI-based CRUD Application
 * Pure command-line interface with no GUI dependencies
 * Works in headless environments
 */
public class Main {
    private static UserRepository repository;
    private static Scanner scanner;

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   CRUD Application - CLI Version      ║");
        System.out.println("║   User Management System              ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();

        repository = new UserRepository();
        scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Select an option (1-6): ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        createUser();
                        break;
                    case 2:
                        readAllUsers();
                        break;
                    case 3:
                        readUserById();
                        break;
                    case 4:
                        updateUser();
                        break;
                    case 5:
                        deleteUser();
                        break;
                    case 6:
                        running = false;
                        System.out.println("\n✓ Exiting application... Data saved to users.json");
                        break;
                    default:
                        System.out.println("✗ Invalid option. Please enter a number between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input. Please enter a valid number.");
            } catch (IOException e) {
                System.err.println("✗ Error: " + e.getMessage());
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("┌────────────────────────────────┐");
        System.out.println("│        CRUD Menu Options       │");
        System.out.println("├────────────────────────────────┤");
        System.out.println("│ 1. Create User                 │");
        System.out.println("│ 2. Read All Users              │");
        System.out.println("│ 3. Read User by ID             │");
        System.out.println("│ 4. Update User                 │");
        System.out.println("│ 5. Delete User                 │");
        System.out.println("│ 6. Exit                        │");
        System.out.println("└────────────────────────────────┘");
    }

    private static void createUser() throws IOException {
        System.out.println("\n--- Create New User ---");
        
        System.out.print("Enter User ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        
        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());

        User user = new User(id, name, email, age);
        repository.create(user);
        System.out.println("✓ User created successfully!");
    }

    private static void readAllUsers() {
        System.out.println("\n--- All Users ---");
        var users = repository.readAll();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println();
            System.out.printf("%-5s %-20s %-30s %-5s%n", "ID", "Name", "Email", "Age");
            System.out.println("─".repeat(62));
            for (User user : users) {
                System.out.printf("%-5d %-20s %-30s %-5d%n", 
                    user.getId(), 
                    user.getName(), 
                    user.getEmail(), 
                    user.getAge());
            }
        }
    }

    private static void readUserById() {
        System.out.println("\n--- Read User by ID ---");
        System.out.print("Enter User ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        Optional<User> user = repository.readById(id);
        if (user.isPresent()) {
            System.out.println();
            System.out.println("User Found:");
            System.out.println("  ID:    " + user.get().getId());
            System.out.println("  Name:  " + user.get().getName());
            System.out.println("  Email: " + user.get().getEmail());
            System.out.println("  Age:   " + user.get().getAge());
        } else {
            System.out.println("✗ User with ID " + id + " not found.");
        }
    }

    private static void updateUser() throws IOException {
        System.out.println("\n--- Update User ---");
        System.out.print("Enter User ID to update: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        Optional<User> existingUser = repository.readById(id);
        if (existingUser.isPresent()) {
            System.out.println("Current data for " + existingUser.get().getName());
            
            System.out.print("Enter new name: ");
            String name = scanner.nextLine().trim();
            
            System.out.print("Enter new email: ");
            String email = scanner.nextLine().trim();
            
            System.out.print("Enter new age: ");
            int age = Integer.parseInt(scanner.nextLine().trim());

            User updatedUser = new User(id, name, email, age);
            repository.update(id, updatedUser);
            System.out.println("✓ User updated successfully!");
        } else {
            System.out.println("✗ User with ID " + id + " not found.");
        }
    }

    private static void deleteUser() throws IOException {
        System.out.println("\n--- Delete User ---");
        System.out.print("Enter User ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        Optional<User> user = repository.readById(id);
        if (user.isPresent()) {
            System.out.print("Are you sure you want to delete " + user.get().getName() + "? (y/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            if (confirm.equals("y") || confirm.equals("yes")) {
                repository.delete(id);
                System.out.println("✓ User deleted successfully!");
            } else {
                System.out.println("✗ Deletion cancelled.");
            }
        } else {
            System.out.println("✗ User with ID " + id + " not found.");
        }
    }
}
