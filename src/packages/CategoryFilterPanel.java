package assignment3.packages.src.packages;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CategoryFilterPanel extends JPanel {
    private ExpensesManager expensesManager;
    private SavedExpensesPanel savedExpensesPanel;

    //Bug Fix
    private JComboBox<Category> filterComboBox;

    //Bug fix
    private JButton filterButton;

    private JButton restoreButton;

    private JButton sumButton;

    // Label to display total expense
    private JLabel totalExpenseLabel;


    public CategoryFilterPanel(ExpensesManager expensesManager, SavedExpensesPanel savedExpensesPanel) {
        this.expensesManager = expensesManager;
        this.savedExpensesPanel = savedExpensesPanel;

        //JComboBox<Category> filterComboBox;
        //JButton filterButton;

        setLayout(new FlowLayout(FlowLayout.LEFT));

        filterComboBox = new JComboBox<>(Category.values());
        add(filterComboBox);

        filterButton = new JButton("Filter");
        add(filterButton);

        restoreButton = new JButton("Restore");
        add(restoreButton);

        sumButton = new JButton("Sum");
        add(sumButton);

        // Initialize the totalExpenseLabel
        totalExpenseLabel = new JLabel("Total Expense: 0");
        add(totalExpenseLabel);

        // Add action listener for the sum button
        sumButton.addActionListener(e -> sumExpenses());

    }

    // Methods to get the selected category

    public JButton getFilterButton() {
        return filterButton;
    }

    public JButton getRestoreButton() {
        return restoreButton;
    }

    public JButton getSumButton(){
        return  sumButton;
    }



    public void applyFilter() {
        //Bug filterComboBox
        Category selectedCategory = (Category) filterComboBox.getSelectedItem();
        // Retrieve filtered expenses and update the table
        savedExpensesPanel.updateTable(expensesManager.getExpensesByCategory(selectedCategory));
    }

    public void restoreFilter(){

        List<Expense> previouslySavedExpenses = savedExpensesPanel.getpreviouslySavedExpenses();
        if (previouslySavedExpenses != null) {
            savedExpensesPanel.updateTable(previouslySavedExpenses);
        }
    }

    //sum all expenses
    public void sumExpenses(){
        // Get all expenses
        List<Expense> allExpenses = expensesManager.getAllExpenses();

        // Check if there are expenses with different currencies
        boolean hasDifferentCurrencies = allExpenses.stream()
                .map(Expense::Currency)
                .distinct()
                .count() > 1;

        if (hasDifferentCurrencies) {
            // Display an error message
            JOptionPane.showMessageDialog(null, "Cannot sum expenses with different currencies", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Calculate the sum
        double totalExpense = allExpenses.stream().mapToDouble(Expense::amount).sum();

        // Update the label to display total expense
        totalExpenseLabel.setText("Total Expense: " + totalExpense);
    }


}
