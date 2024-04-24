package assignment3.packages.src.packages;

import java.io.Serializable;
import java.time.LocalDate;

//Bug
//Fix Category -> String
public record Expense(double amount, String Currency, String category, LocalDate date) implements Serializable {

}