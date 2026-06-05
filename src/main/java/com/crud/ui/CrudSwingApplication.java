package com.crud.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

import com.crud.model.User;
import com.crud.repository.UserRepository;

public class CrudSwingApplication extends JFrame {
    private UserRepository repository;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField ageField;

    public CrudSwingApplication() {
        repository = new UserRepository();
        initializeUI();
        loadTableData();
    }

    private void initializeUI() {
        setTitle("CRUD Application - User Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(true);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(33, 150, 243));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("User Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel("Manage users with JSON file storage");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(224, 224, 224));

        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);

        // Main Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout(20, 0));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Left side - Table
        contentPanel.add(createTablePanel(), BorderLayout.CENTER);

        // Right side - Form
        contentPanel.add(createFormPanel(), BorderLayout.EAST);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(245, 245, 245));
        footerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(204, 204, 204)));
        footerPanel.setLayout(new BorderLayout());
        JLabel footerLabel = new JLabel("Data is automatically saved to users.json");
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        footerLabel.setForeground(new Color(102, 102, 102));
        footerPanel.add(footerLabel, BorderLayout.WEST);

        // Add panels to frame
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));

        JLabel label = new JLabel("Users List");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, BorderLayout.NORTH);

        // Create table model
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Age"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        userTable = new JTable(tableModel);
        userTable.setFont(new Font("Arial", Font.PLAIN, 11));
        userTable.setRowHeight(25);
        userTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && userTable.getSelectedRow() >= 0) {
                int row = userTable.getSelectedRow();
                idField.setText(tableModel.getValueAt(row, 0).toString());
                nameField.setText(tableModel.getValueAt(row, 1).toString());
                emailField.setText(tableModel.getValueAt(row, 2).toString());
                ageField.setText(tableModel.getValueAt(row, 3).toString());
            }
        });

        JScrollPane scrollPane = new JScrollPane(userTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setBorder(BorderFactory.createTitledBorder("User Form"));
        panel.setPreferredSize(new Dimension(300, 0));

        JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 5, 10));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        fieldsPanel.add(new JLabel("User ID:"));
        idField = new JTextField();
        idField.setEditable(false);
        fieldsPanel.add(idField);

        fieldsPanel.add(new JLabel("Full Name:"));
        nameField = new JTextField();
        fieldsPanel.add(nameField);

        fieldsPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        fieldsPanel.add(emailField);

        fieldsPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        fieldsPanel.add(ageField);

        JPanel buttonsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton createBtn = new JButton("Create");
        createBtn.setBackground(new Color(76, 175, 80));
        createBtn.setForeground(Color.WHITE);
        createBtn.setFont(new Font("Arial", Font.BOLD, 11));
        createBtn.addActionListener(e -> handleCreate());
        buttonsPanel.add(createBtn);

        JButton updateBtn = new JButton("Update");
        updateBtn.setBackground(new Color(33, 150, 243));
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setFont(new Font("Arial", Font.BOLD, 11));
        updateBtn.addActionListener(e -> handleUpdate());
        buttonsPanel.add(updateBtn);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBackground(new Color(244, 67, 54));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 11));
        deleteBtn.addActionListener(e -> handleDelete());
        buttonsPanel.add(deleteBtn);

        JButton clearBtn = new JButton("Clear");
        clearBtn.setBackground(new Color(117, 117, 117));
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFont(new Font("Arial", Font.BOLD, 11));
        clearBtn.addActionListener(e -> clearFields());
        buttonsPanel.add(clearBtn);

        JButton refreshBtn = new JButton("Refresh List");
        refreshBtn.setBackground(new Color(33, 150, 243));
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setFont(new Font("Arial", Font.BOLD, 11));
        refreshBtn.addActionListener(e -> loadTableData());

        panel.add(fieldsPanel, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.CENTER);

        JPanel refreshPanel = new JPanel();
        refreshPanel.add(refreshBtn);
        panel.add(refreshPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void handleCreate() {
        try {
            if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || ageField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields (except ID)", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = repository.readAll().stream().mapToInt(User::getId).max().orElse(0) + 1;
            String name = nameField.getText();
            String email = emailField.getText();
            int age = Integer.parseInt(ageField.getText());

            User user = new User(id, name, email, age);
            repository.create(user);

            JOptionPane.showMessageDialog(this, "User created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableData();
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age must be a number", "Validation Error", JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleUpdate() {
        try {
            if (idField.getText().isEmpty() || nameField.getText().isEmpty() ||
                emailField.getText().isEmpty() || ageField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a user and fill all fields", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String email = emailField.getText();
            int age = Integer.parseInt(ageField.getText());

            User updatedUser = new User(id, name, email, age);
            repository.update(id, updatedUser);

            JOptionPane.showMessageDialog(this, "User updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableData();
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age must be a number", "Validation Error", JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDelete() {
        try {
            if (idField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a user", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idField.getText());
            repository.delete(id);

            JOptionPane.showMessageDialog(this, "User deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadTableData();
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID", "Validation Error", JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error deleting user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        ageField.setText("");
        userTable.getSelectionModel().clearSelection();
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        for (User user : repository.readAll()) {
            tableModel.addRow(new Object[]{user.getId(), user.getName(), user.getEmail(), user.getAge()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CrudSwingApplication app = new CrudSwingApplication();
            app.setVisible(true);
        });
    }
}
