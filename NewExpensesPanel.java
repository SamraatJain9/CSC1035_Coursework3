package assignment3.packages;

import javax.swing.*;
import java.awt.*;
import java.util.stream.IntStream;
import java.time.LocalDate;

public class NewExpensesPanel extends JPanel {
    private final JTextField amountField;
    private final JComboBox<Category> categoryComboBox;
    private final JComboBox<Integer> dayComboBox;
    private final JComboBox<String> monthComboBox;
    private final JComboBox<Integer> yearComboBox;
    private final JButton saveButton;
    private final JButton clearButton;
    private final JButton editButton;

    public NewExpensesPanel() {
        setLayout(new GridLayout(6, 4)); // Adjust layout as needed

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("Category:"));
        categoryComboBox = new JComboBox<>(Category.values());
        add(categoryComboBox);

        add(new JLabel("Date:"));
        // Day, month, and year combo boxes
        dayComboBox = new JComboBox<>(IntStream.rangeClosed(1, 31).boxed().toArray(Integer[]::new));
        monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        yearComboBox = new JComboBox<>(IntStream.rangeClosed(1900, 2100).boxed().toArray(Integer[]::new));
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(dayComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(yearComboBox);
        add(datePanel);

        saveButton = new JButton("Save");
        editButton = new JButton("Edit");
        clearButton = new JButton("Clear");

        // Add action listeners for buttons...

        add(saveButton);
        add(editButton);
        add(clearButton);

    }

    // Additional methods to get user input values...
    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getEditButton() {
        return editButton;
    }
    public double getAmount() {
        try {
            return Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public Category getExpenseCategory() {
        return (Category) categoryComboBox.getSelectedItem();
    }

    public LocalDate getDate() {
        // Construct and return a LocalDate object from the date components
        // with dayComboBox, monthComboBox, yearComboBox
        Integer day = (Integer) dayComboBox.getSelectedItem();
        int month = monthComboBox.getSelectedIndex() + 1; // since months are zero-based
        Integer year = (Integer) yearComboBox.getSelectedItem();

        if (day == null || year == null) {
            // Handle the case where day or year is null as they are objects ,
            return null;
        }

        return LocalDate.of(year, month, day);
    }
}