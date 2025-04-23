/**
 * CPSC 240
 * Final Project
 * I pledge
 * John McCloskey, Timothy Moore
 * Expense class for budget management program.
 */

import java.time.LocalDate; //Import local date.


public class Expense {
    private String type; //Stores description of what type of expense is being recorded.
    private double amount; //Stores monetary value of expense.
    private LocalDate due; //Due date for payment.

    public Expense(String type, double amount, LocalDate due) { //Constructor for each expense.
        this.type = type;
        this.amount = amount;
        this.due = due;
    }

    public String getType() {
        return type;
    } //Returns expense type.

    public double getAmount() {
        return amount;
    } //Returns expense value.

    public LocalDate getDue() {
        return due;
    }//Returns date due.

    public boolean isDue() { //Determines if an expense is due in seven a week.
        return LocalDate.now().plusDays(7).isAfter(due);
    }

    @Override //Returns expense info in string format.
    public String toString() {
        return "Expense for " + type + ": $" + amount + " due " + due;
    }

}
