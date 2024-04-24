package assignment3.packages.src.packages;

import assignment3.packages.src.packages.Category;

import java.io.Serializable;
import java.time.LocalDate;

public record Expense(double amount, Category category, LocalDate date) implements Serializable { }