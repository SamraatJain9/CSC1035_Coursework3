package assignment3.packages.src.packages.constructor;

import java.io.Serializable;
import java.time.LocalDate;

// Expense class representing an expense record
public record Expense(double amount, String currency, String category, LocalDate date) implements Serializable {

}