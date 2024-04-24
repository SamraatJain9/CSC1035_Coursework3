package assignment3.packages.src;


import assignment3.packages.src.packages.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.lang.String;
public class Main {

    public static void main(String[] args) {
        createAndShowGUI();
    }

    private static void createAndShowGUI() {
        JFrame frame;
        frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the object of expenseManager
        ExpensesManager expensesManager = new ExpensesManager();

        // Create instances of relevant panels
        NewExpensesPanel newExpensesPanel = new NewExpensesPanel();
        SavedExpensesPanel savedExpensesPanel = new SavedExpensesPanel();
        CategoryFilterPanel categoryFilterPanel = new CategoryFilterPanel(expensesManager, savedExpensesPanel);

        // Add panels to the frame and specify their relative position in the frame
        frame.add(newExpensesPanel, BorderLayout.NORTH);
        frame.add(savedExpensesPanel, BorderLayout.CENTER);
        frame.add(categoryFilterPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);



        // Listener for clicking savebutton
        newExpensesPanel.getSaveButton().addActionListener(e ->  {
            double amount = newExpensesPanel.getAmount();
            //Adding panel to prevent saving expenses less equal to 0
            if (amount <= 0) {
                JOptionPane.showMessageDialog(frame, "Amount must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Stop further execution
            }

            String currency = String.valueOf(newExpensesPanel.getExpenseCurrency());
            String category = String.valueOf(newExpensesPanel.getExpenseCategory());

            LocalDate date = newExpensesPanel.getDate();

            Expense newExpense = new Expense(amount, currency, category, date);
            expensesManager.addExpense(newExpense);
            savedExpensesPanel.updateTable(expensesManager.getAllExpenses());
        });


        SavedExpensesEditDialog editDialog = new SavedExpensesEditDialog(frame);

        // Edit Button ActionListener
        newExpensesPanel.getEditButton().addActionListener(e -> {
            int selectedRow = savedExpensesPanel.getSavedSelectedExpenseIndex();
            if (selectedRow != -1) {
                // get the index
                Expense selectedExpense = expensesManager.getAllExpenses().get(selectedRow);
                boolean saved = editDialog.showDialog(selectedExpense);
                if (saved) {
                    Expense editedExpense = editDialog.getEditedExpense();
                    expensesManager.replaceExpense(selectedRow, selectedExpense);
                    savedExpensesPanel.updateTable(expensesManager.getAllExpenses());
                }
            }
        });

        //delete button
        newExpensesPanel.getDeleteButton().addActionListener(e -> {
            int selectedRow = savedExpensesPanel.getSavedSelectedExpenseIndex();
            if (selectedRow != -1) {
                // Get the selected expense
                Expense selectedExpense = expensesManager.getAllExpenses().get(selectedRow);
                // Remove the selected expense from the list
                expensesManager.removeExpense(selectedRow);
                // Update the table to reflect the changes
                savedExpensesPanel.updateTable(expensesManager.getAllExpenses());
            }
        });


        // Clear Button ActionListener
        newExpensesPanel.getClearButton().addActionListener(e -> {
            expensesManager.clearExpenses();
            savedExpensesPanel.updateTable(expensesManager.getAllExpenses());
        });


        // Filter Button ActionListener

        // Filter Button ActionListener
        //Bug
        //Fix: categoryFilterPanel.getFilterButton().addActionListener(e -> categoryFilterPanel.applyFilter());
        categoryFilterPanel.getFilterButton().addActionListener(e -> categoryFilterPanel.applyFilter());


        // Restore Button ActionListener
        categoryFilterPanel.getRestoreButton().addActionListener(e -> categoryFilterPanel.restoreFilter());

        // Sum button
        categoryFilterPanel.getSumButton().addActionListener(e -> categoryFilterPanel.sumExpenses());

    }
}
