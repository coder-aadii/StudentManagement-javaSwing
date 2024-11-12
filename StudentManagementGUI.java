package com.manage_student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentManagementGUI extends JFrame {

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField cityField;
    private JTextField idField;

    public StudentManagementGUI() {
        setTitle("Student Management System");
        setSize(400, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null); // Using null layout for absolute positioning

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 30);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 20, 200, 30);
        panel.add(nameField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(20, 60, 100, 30);
        panel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(150, 60, 200, 30);
        panel.add(phoneField);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setBounds(20, 100, 100, 30);
        panel.add(cityLabel);

        cityField = new JTextField();
        cityField.setBounds(150, 100, 200, 30);
        panel.add(cityField);

        JButton addButton = new JButton("Add Student");
        addButton.setBounds(150, 140, 200, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        panel.add(addButton);

        JLabel idLabel = new JLabel("Student ID (for deletion):");
        idLabel.setBounds(20, 180, 200, 30);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(220, 180, 130, 30);
        panel.add(idField);

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.setBounds(150, 220, 200, 30);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
        panel.add(deleteButton);

        JButton displayButton = new JButton("Display Students");
        displayButton.setBounds(150, 260, 200, 30);
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStudents();
            }
        });
        panel.add(displayButton);

        add(panel);
    }

    private void addStudent() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String city = cityField.getText();
        Student student = new Student(0, name, phone, city); // ID is set to 0 assuming it's auto-generated in DB
        boolean isSuccess = StudentDao.insertStudentToDb(student);
        if (isSuccess) {
            JOptionPane.showMessageDialog(this, "Student added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Error adding student. Please try again.");
        }
        clearFields();
    }

    private void deleteStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            boolean isSuccess = StudentDao.deleteStudent(id);
            if (isSuccess) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting student. Please try again.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric value.");
        }
        clearFields();
    }

    private void displayStudents() {
        List<Student> students = StudentDao.getAllStudents();

        // Define column names for the table
        String[] columnNames = {"ID", "Name", "Phone", "City"};

        // Create a 2D array to hold the data
        Object[][] data = new Object[students.size()][4];

        // Populate the data array
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i][0] = student.getId();
            data[i][1] = student.getName();
            data[i][2] = student.getPhone();
            data[i][3] = student.getCity();
        }

        // Create the table with data and column names
        JTable table = new JTable(data, columnNames);

        // Set table properties (e.g., column width, row height)
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Display the scroll pane containing the table
        JOptionPane.showMessageDialog(this, scrollPane, "Student Records", JOptionPane.PLAIN_MESSAGE);
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        cityField.setText("");
        idField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagementGUI().setVisible(true);
            }
        });
    }
}
