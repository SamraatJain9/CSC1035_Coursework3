package assignment3.packages.src.packages.display_table;

import assignment3.packages.src.packages.constructor.Expense;

import javax.swing.table.AbstractTableModel;
import java.util.List;

// Table model for displaying saved expenses
public class SavedExpenses extends AbstractTableModel {
    private final String[] columnNames = {"Amount", "Currency", "Category", "Date"}; // Column names
    private List<Expense> expenses; // List to hold expenses data

    // Constructor
    public SavedExpenses(List<Expense> expenses) {
        this.expenses = expenses; // Initialize expenses list
    }

    // Method to get the list of expenses
    public List<Expense> getExpenses() {
        return expenses; // Return expenses list
    }

    // Method to get the number of rows in the table
    @Override
    public int getRowCount() {
        return expenses.size(); // Return number of expenses
    }

    // Method to get the number of columns in the table
    @Override
    public int getColumnCount() {
        return columnNames.length; // Return number of columns
    }


    // Method to get the value at a specific row and column in the table
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Expense expense = expenses.get(rowIndex); // Get expense at specified row index

        if (expense != null) { // Check if expense is not null
            return switch (columnIndex) {
                case 0 -> expense.amount(); // Return amount for column 0
                case 1 -> expense.currency(); // Return currency for column 1
                case 2 -> expense.category(); // Return category for column 2
                case 3 -> expense.date().toString(); // Return date for column 3
                default -> null; // Return null for other columns
            };
        } else {
            return null; // Return null if expense is null
        }
    }


    // Method to get the column name at a specific index
    @Override
    public String getColumnName(int column) {
        return columnNames[column]; // Return column name
    }

    // Method to set the list of expenses and notify the table that data has changed
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses; // Set new list of expenses
        this.fireTableDataChanged(); // Notifies the table that the data has changed
    }
}
