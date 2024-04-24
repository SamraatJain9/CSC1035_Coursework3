package assignment3.packages;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CategoryFilterPanel extends JPanel {
    private ExpensesManager expensesManager;
    private SavedExpensesPanel savedExpensesPanel;



    private JButton restoreButton;


    public CategoryFilterPanel(ExpensesManager expensesManager, SavedExpensesPanel savedExpensesPanel) {
        this.expensesManager = expensesManager;
        this.savedExpensesPanel = savedExpensesPanel;

        JComboBox<Category> filterComboBox;
        JButton filterButton;

        setLayout(new FlowLayout(FlowLayout.LEFT));

        filterComboBox = new JComboBox<>(Category.values());
        add(filterComboBox);

        filterButton = new JButton("Filter");
        add(filterButton);

        restoreButton = new JButton("Restore");
        add(restoreButton);

    }

    // Methods to get the selected category

    public JButton getFilterButton() {
        return filterButton;
    }

    public JButton getRestoreButton() {
        return restoreButton;
    }



    public void applyFilter() {
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



}
