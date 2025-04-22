/**
 * CPSC 240
 * Final Project
 * I pledge
 * John McCloskey, Timothy Moore
 * Expense class for budget management program.
 */

import java.time.LocalDate;


public class Expense {
    private String type;
    private double amount;
    private LocalDate due;

    public Expense(String type, double amount, LocalDate due) {
        this.type = type;
        this.amount = amount;
        this.due = due;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDue() {
        return due;
    }

    public boolean isDue() {
        return LocalDate.now().plusDays(7).isAfter(due);
    }

    @Override
    public String toString() {
        return "Expense for " + type + ": $" + amount + " due " + due;
    }

}
