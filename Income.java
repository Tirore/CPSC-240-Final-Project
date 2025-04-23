/**
 * CPSC 240
 * Final Project
 * I pledge
 * John McCloskey, Timothy Moore
 * Income class for budget management program.
 */
public class Income {
    private String type; //Stores description of what type of income is being recorded.
    private double amount; //Store monetary value of income.

    public Income (String type, double amount) { //Initializes new income with type and amount.
        this.type = type;
        this.amount = amount;
    }

    public String getType() { //Returns type of income.
        return type;
    }

    public double getAmount() {//Returns amount across all income instances.
        return amount;
    }

    @Override
    public String toString() {//Returns string representation of incomes.
        return "Income from " + type + ": $" + amount;
    }

}
