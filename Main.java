
import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class Main {
    private List<Income> incomes = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();

    public void addIncome (Income income) {
        incomes.add(income);
    }

    public void addExpense (Expense expense) {
        expenses.add(expense);
    }

    public double getTotalIncome () {
        return incomes.stream().mapToDouble(Income::getAmount).sum();
    }

    public double getTotalExpenses () {
        return expenses.stream().mapToDouble(Income::getAmount).sum();
    }

    public double getBalance () {
        return getTotalIncome() - getTotalExpenses();
    }

    public List<Expense> getDueExpenses() {
        List<Expense> due = new ArrayList<>();
        for (Expense e : expenses) {
            if (e.isDue()) due.add(e);
        }
        return due;
    }

    public void genReport(String filePath) throws IOException {
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

}
