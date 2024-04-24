package assignment3.packages.src.packages.display_panels;

import assignment3.packages.src.packages.enums.Category;
import assignment3.packages.src.packages.enums.Currency;

import javax.swing.*;
import java.awt.*;
import java.util.stream.IntStream;
import java.time.LocalDate;

// Panel for entering new expenses
public class NewExpensesPanel extends JPanel {
    private final JTextField amountField; // Text field for entering amount
    private final JComboBox<Currency> currencyComboBox; // Combo box for selecting currency
    private final JComboBox<Category> categoryComboBox; // Combo box for selecting category
    private final JComboBox<Integer> dayComboBox; // Combo box for selecting day
    private final JComboBox<String> monthComboBox; // Combo box for selecting month
    private final JComboBox<Integer> yearComboBox; // Combo box for selecting year
    private final JButton saveButton; // Button for saving expense
    private final JButton clearButton; // Button for clearing fields
    private final JButton editButton; // Button for editing expense

    // Adding delete button
    private final JButton deleteButton;

    // Constructor
    public NewExpensesPanel() {
        setLayout(new GridLayout(6, 4)); // Set layout

        add(new JLabel("Amount:")); // Add label for amount
        amountField = new JTextField(); // Initialize amount text field
        add(amountField); // Add amount text field

        add(new JLabel("Currency:")); // Add label for currency
        currencyComboBox = new JComboBox<>(Currency.values()); // Initialize currency combo box
        add(currencyComboBox); // Add currency combo box

        add(new JLabel("Category:")); // Add label for category
        categoryComboBox = new JComboBox<>(Category.values()); // Initialize category combo box
        add(categoryComboBox); // Add category combo box

        add(new JLabel("Date:")); // Add label for date
        // Initialize day, month, and year combo boxes
        dayComboBox = new JComboBox<>(IntStream.rangeClosed(1, 31).boxed().toArray(Integer[]::new));
        monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        yearComboBox = new JComboBox<>(IntStream.rangeClosed(1900, 2100).boxed().toArray(Integer[]::new));
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(dayComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(yearComboBox);
        add(datePanel); // Add date panel

        saveButton = new JButton("Save"); // Initialize save button
        editButton = new JButton("Edit"); // Initialize edit button
        deleteButton = new JButton("Delete"); // Initialize delete button
        clearButton = new JButton("Clear"); // Initialize clear button

        // Add action listeners for buttons...

        add(saveButton); // Add save button
        add(editButton); // Add edit button
        add(deleteButton); // Add delete button
        add(clearButton); // Add clear button
    }

    // Method to get the save button
    public JButton getSaveButton() {
        return saveButton;
    }

    // Method to get the clear button
    public JButton getClearButton() {
        return clearButton;
    }

    // Method to get the edit button
    public JButton getEditButton() {
        return editButton;
    }

    // Method to get the delete button
    public JButton getDeleteButton() {
        return deleteButton;
    }

    // Method to get the entered amount
    public double getAmount() {
        try {
            return Double.parseDouble(amountField.getText()); // Parse amount field text to double
        } catch (NumberFormatException e) {
            return 0; // Return 0 if parsing fails
        }
    }

    // Method to get the selected expense category
    public Category getExpenseCategory() {
        return (Category) categoryComboBox.getSelectedItem(); // Return selected category from combo box
    }

    // Method to get the selected expense currency
    public Currency getExpenseCurrency() {
        return (Currency) currencyComboBox.getSelectedItem(); // Return selected currency from combo box
    }

    // Method to get the selected date
    public LocalDate getDate() {
        // Construct and return a LocalDate object from the selected date components
        Integer day = (Integer) dayComboBox.getSelectedItem();
        int month = monthComboBox.getSelectedIndex() + 1; // Since months are zero-based
        Integer year = (Integer) yearComboBox.getSelectedItem();

        if (day == null || year == null) {
            return null; // Return null if day or year is null
        }

        return LocalDate.of(year, month, day); // Return LocalDate object
    }
}
