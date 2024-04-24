package assignment3.packages.src.packages;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

// Dialog for editing saved expenses
public class SavedExpensesEditDialog extends JDialog {
    private final JTextField amountField = new JTextField(10); // Text field for entering amount
    private final JComboBox<Currency> currencyComboBox = new JComboBox<>(Currency.values()); // Combo box for selecting currency
    private final JComboBox<Category> categoryComboBox = new JComboBox<>(Category.values()); // Combo box for selecting category
    private final JSpinner dateSpinner = new JSpinner(new SpinnerDateModel()); // Spinner for selecting date
    private boolean saved = false; // Flag to track if expense is saved
    private boolean deleted = false; // Flag to track if expense is deleted

    // Constructor
    public SavedExpensesEditDialog(Frame owner) {
        super(owner, "Edit Expense", true); // Set title and modal
        setLayout(new GridLayout(0, 2)); // Set layout

        // Add components to dialog
        add(new JLabel("Amount:"));
        add(amountField);

        add(new JLabel("Category:"));
        add(categoryComboBox);

        add(new JLabel("Date:"));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"); // Date editor format
        dateSpinner.setEditor(dateEditor);
        add(dateSpinner);

        JButton saveButton = new JButton("Save"); // Save button
        saveButton.addActionListener(e -> {
            saved = true; // Set saved flag to true
            setVisible(false); // Hide dialog
        });
        add(saveButton);

        JButton deleteButton = new JButton("Delete"); // Delete button
        deleteButton.addActionListener(e -> {
            deleted = true; // Set deleted flag to true
            setVisible(false); // Hide dialog
        });
        add(deleteButton);

        JButton cancelButton = new JButton("Cancel"); // Cancel button
        cancelButton.addActionListener(e -> setVisible(false)); // Hide dialog
        add(cancelButton);

        pack(); // Pack components
    }

    // Method to show dialog with expense details
    public boolean showDialog(Expense expense) {
        // Initialize dialog fields with expense details
        amountField.setText(String.valueOf(expense.amount()));
        currencyComboBox.setSelectedItem(expense.currency());
        categoryComboBox.setSelectedItem(expense.category());
        dateSpinner.setValue(java.sql.Date.valueOf(expense.date()));

        saved = false; // Reset saved state

        setLocationRelativeTo(getOwner()); // Set location relative to the owner frame
        setVisible(true); // Show dialog

        return saved; // Return true if saved, false otherwise
    }

    // Method to get edited expense
    public Expense getEditedExpense() {
        double amount = Double.parseDouble(amountField.getText()); // Parse amount field
        String currency = (String) currencyComboBox.getSelectedItem(); // Get selected currency
        String category = (String) categoryComboBox.getSelectedItem(); // Get selected category
        Date date = (Date) dateSpinner.getValue(); // Get selected date
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Convert date to LocalDate

        return new Expense(amount, currency, category, localDate); // Return edited expense
    }
}
