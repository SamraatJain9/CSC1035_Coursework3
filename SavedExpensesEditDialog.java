package assignment3.packages;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class SavedExpensesEditDialog extends JDialog {
    private final JTextField amountField = new JTextField(10);
    private final JComboBox<Category> categoryComboBox = new JComboBox<>(Category.values());
    private final JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
    private boolean saved = false;

    public SavedExpensesEditDialog(Frame owner) {
        super(owner, "Edit Expense", true);
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Amount:"));
        add(amountField);

        add(new JLabel("Category:"));
        add(categoryComboBox);

        add(new JLabel("Date:"));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        add(dateSpinner);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            saved = true;
            setVisible(false);
        });
        add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));
        add(cancelButton);

        pack();
    }

    public boolean showDialog(Expense expense) {
        // Initialize dialog fields with expense details
        amountField.setText(String.valueOf(expense.amount()));
        categoryComboBox.setSelectedItem(expense.category());
        dateSpinner.setValue(java.sql.Date.valueOf(expense.date()));

        saved = false; // Reset saved state

        // Set location relative to the owner frame
        setLocationRelativeTo(getOwner());

        setVisible(true); // Show dialog

        return saved; // Return true if saved, false otherwise
    }

    public Expense getEditedExpense() {
        double amount = Double.parseDouble(amountField.getText());
        Category category = (Category) categoryComboBox.getSelectedItem();
        Date date = (Date) dateSpinner.getValue();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new Expense(amount, category, localDate);
    }
}
