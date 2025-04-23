/**
 * CPSC 240
 * Final Project
 * I pledge
 * John McCloskey, Timothy Moore
 * GUI class for budget management program.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GUI extends JPanel {
    private Manager manager = new Manager();
    private JLabel incomeLabel, expenseLabel, balanceLabel;
    private JTextArea remindersArea;

    public GUI() {
        setLayout(new BorderLayout());


        JPanel summaryPanel = new JPanel(new GridLayout(3, 1));
        incomeLabel = new JLabel("Total Income: $0.00");
        expenseLabel = new JLabel("Total Expenses: $0.00");
        balanceLabel = new JLabel("Balance: $0.00");
        summaryPanel.add(incomeLabel);
        summaryPanel.add(expenseLabel);
        summaryPanel.add(balanceLabel);
        add(summaryPanel, BorderLayout.NORTH);

        remindersArea = new JTextArea();
        remindersArea.setEditable(false);
        add(new JScrollPane(remindersArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
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

    private void updateSummary() {
        incomeLabel.setText("Total Income: $" + String.format("%.2f", manager.getTotalIncome()));
        expenseLabel.setText("Total Expenses: $" + String.format("%.2f", manager.getTotalExpenses()));
        balanceLabel.setText("Balance: $" + String.format("%.2f", manager.getBalance()));

        StringBuilder reminders = new StringBuilder("Upcoming Expenses (Next 7 Days):\n");
        for (Expense e : manager.getUpcomingExpenses()) {
        reminders.append(e.toString()).append("\n");
        }
        remindersArea.setText(reminders.toString());
    }


    private void importData() {
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

    private void exportReport() {
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("BudgetAide");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        GUI guiPanel = new GUI();
        frame.add(guiPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }
}


