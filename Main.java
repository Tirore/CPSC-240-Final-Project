/**
 * CPSC 240
 * Final Project
 * I pledge
 * John McCloskey, Timothy Moore
 * Main class for budget management program.
 */
import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class Main { //Creates lists to dtores income and expense inputs.
    private List<Income> incomes = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();

    public void addIncome (Income income) {
        incomes.add(income);
    } //Adds income to list.

    public void addExpense (Expense expense) {
        expenses.add(expense);
    } //Adds new expense to list.

    public double getTotalIncome () {//Adds up all income entries to calculate total
        return incomes.stream().mapToDouble(Income::getAmount).sum();
    }

    public double getTotalExpenses () { //Finds sum of all expenses.
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public double getBalance () { //Finds balance by subtracting expenses from incomes.
        return getTotalIncome() - getTotalExpenses();
    }

    public List<Expense> getDueExpenses() {//Lists expenses due in next week.
        List<Expense> due = new ArrayList<>();
        for (Expense e : expenses) {
            if (e.isDue()) due.add(e);
        }
        return due;
    }

    public void genReport(String filePath) throws IOException {//Writes incomes, expenses, and balance to file.
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write("=== Budget Report ===\n");
        writer.write("Incomes:\n");
        for (Income i : incomes) writer.write(i + "\n");
        writer.write("\nExpenses:\n");
        for (Expense e : expenses) writer.write(e + "\n");
        writer.write("\nTotal Income: $" + getTotalIncome() + "\n");
        writer.write("Total Expenses: $" + getTotalExpenses() + "\n");
        writer.write("Balance: $" + getBalance() + "\n");
        writer.close();
    }

    public void importData( String filePath) throws IOException {//Imports data from .txt file
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            try {
                if (parts[0].equalsIgnoreCase("income")) {
                    addIncome(new Income(parts[1], Double.parseDouble(parts[2])));
                } else if (parts[0].equalsIgnoreCase("expense")) {
                    addExpense(new Expense(parts[1], Double.parseDouble(parts[2]), LocalDate.parse(parts[3])));
                }
            } catch (Exception e) {
                System.err.println("Skipping invalid line: " + line);
            }
        }
        reader.close();
    }

}
