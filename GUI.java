/**
 * CPSC 240
 * Final Project
 * I pledge
 * John McCloskey, Timothy Moore
 * GUI class for budget management program.
 */

import javax.swing.*;//Imports swing components.
import java.awt.*;// Imports layout and components.
import java.awt.event.*;// Imports event handling.
import java.io.IOException;//Imports input exceptions

public class GUI extends JPanel {
    private Manager manager = new Manager(); //Starts manager instance to handle data input and calculations.
    private JLabel incomeLabel, expenseLabel, balanceLabel; //Labels for expenses, income, and balance.
    private JTextArea remindersArea; //Displays reminder for due expenses.

    public GUI() { //Constructor for GUI.
        setLayout(new BorderLayout()); //Sets panel layout.


        JPanel summaryPanel = new JPanel(new GridLayout(3, 1));//Panel for expense, income, and balance display.
        incomeLabel = new JLabel("Total Income: $0.00");
        expenseLabel = new JLabel("Total Expenses: $0.00");
        balanceLabel = new JLabel("Balance: $0.00");
        summaryPanel.add(incomeLabel);
        summaryPanel.add(expenseLabel);
        summaryPanel.add(balanceLabel);
        add(summaryPanel, BorderLayout.NORTH); //Creates summary panels.

        remindersArea = new JTextArea(); //Displays reminders.
        remindersArea.setEditable(false);
        add(new JScrollPane(remindersArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(); // Creates button panel.
        JButton importBtn = new JButton("Import Data");
        JButton exportBtn = new JButton("Export Report");
        JButton refreshBtn = new JButton("Refresh Summary");

        importBtn.addActionListener(e -> importData());
        exportBtn.addActionListener(e -> exportReport());
        refreshBtn.addActionListener(e -> updateSummary());

        buttonPanel.add(importBtn);
        buttonPanel.add(exportBtn);
        buttonPanel.add(refreshBtn);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateSummary() { //Updates labels with formatted values.
        incomeLabel.setText("Total Income: $" + String.format("%.2f", manager.getTotalIncome()));
        expenseLabel.setText("Total Expenses: $" + String.format("%.2f", manager.getTotalExpenses()));
        balanceLabel.setText("Balance: $" + String.format("%.2f", manager.getBalance()));

        StringBuilder reminders = new StringBuilder("Upcoming Expenses (Next 7 Days):\n");
        for (Expense e : manager.getUpcomingExpenses()) {
        reminders.append(e.toString()).append("\n");
        }
        remindersArea.setText(reminders.toString());
    }


    private void importData() { //Opens file and imports data.
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                manager.importData(fileChooser.getSelectedFile().getAbsolutePath());
                updateSummary();
                JOptionPane.showMessageDialog(this, "Data imported successfully.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to read file.");
            }
        }
    }

    private void exportReport() { //exports report to a file.
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                manager.genReport(fileChooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Report saved successfully.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to write file.");
            }
        }
    }

    public static void main(String[] args) { //Launches application.
        JFrame frame = new JFrame("BudgetAide");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        GUI guiPanel = new GUI();
        frame.add(guiPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }
}


