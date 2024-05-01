package assignment3.packages.src.packages.constructor;

import assignment3.packages.src.packages.enums.Category;

import java.io.Serializable;
import java.time.LocalDate;

// Expense class representing an expense record
public record Expense(double amount, String currency, Category category, LocalDate date) implements Serializable {

}