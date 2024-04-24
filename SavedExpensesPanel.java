package assignment3.packages;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SavedExpensesPanel extends JPanel {
    private SavedExpenses savedExpenses;
    private JTable jsavedExpenseTable;

    private List<Expense> previouslySavedExpenses;


    public SavedExpensesPanel() {
        setLayout(new BorderLayout());

        // Initially empty and ready to be added or exported
        savedExpenses = new SavedExpenses(new ArrayList<>());
        jsavedExpenseTable = new JTable(savedExpenses);

        JScrollPane scrollPane = new JScrollPane(jsavedExpenseTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateTable(List<Expense> expenses) {
        previouslySavedExpenses = savedExpenses.getExpenses();

        savedExpenses.setExpenses(expenses);
    }


    public List<Expense> getpreviouslySavedExpenses() {
        return previouslySavedExpenses;
    }


    // method to select the saved expense for further manipulation
    public int getSavedSelectedExpenseIndex() {
        return jsavedExpenseTable.getSelectedRow(); // Returns -1 if no row is selected
    }


}
