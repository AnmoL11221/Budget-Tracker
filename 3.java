import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

class BudgetTrackerGUI extends JFrame {
    private Map<String, Double> income = new HashMap<>();
    private Map<String, Double> expenses = new HashMap<>();
    private Map<String, Double> budgetGoals = new HashMap<>();
    private JLabel incomeLabel;
    private JLabel expensesLabel;

    public BudgetTrackerGUI() {
        setTitle("Personal Budget Tracker");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JButton addIncomeButton = new JButton("Add Income");
        JButton addExpenseButton = new JButton("Add Expense");
        JButton setBudgetGoalButton = new JButton("Set Budget Goal");
        JButton viewBudgetButton = new JButton("View Budget");

        incomeLabel = new JLabel("Total Income: $0.0");
        expensesLabel = new JLabel("Total Expenses: $0.0");

        JPanel panel = new JPanel();
        panel.add(addIncomeButton);
        panel.add(addExpenseButton);
        panel.add(setBudgetGoalButton);
        panel.add(viewBudgetButton);
        panel.add(incomeLabel);
        panel.add(expensesLabel);

        // Add panel to frame
        add(panel);

        // Add action listeners
        addIncomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addIncome();
            }
        });

        addExpenseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });

        setBudgetGoalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBudgetGoal();
            }
        });

        viewBudgetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewBudget();
            }
        });

        // Display the frame
        setVisible(true);
    }

    private void addIncome() {
        String source = JOptionPane.showInputDialog("Enter income source:");
        double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter income amount:"));
        income.put(source, amount);
        updateIncomeLabel();
        JOptionPane.showMessageDialog(null, "Income added successfully.");
    }

    private void addExpense() {
        String category = JOptionPane.showInputDialog("Enter expense category:");
        double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter expense amount:"));
        expenses.put(category, amount);
        updateExpensesLabel();
        JOptionPane.showMessageDialog(null, "Expense added successfully.");
    }

    private void setBudgetGoal() {
        String category = JOptionPane.showInputDialog("Enter budget goal category:");
        double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter budget goal amount:"));
        budgetGoals.put(category, amount);
        JOptionPane.showMessageDialog(null, "Budget goal set successfully.");
    }

    private void viewBudget() {
        StringBuilder message = new StringBuilder();
        message.append("Income:\n");
        for (Map.Entry<String, Double> entry : income.entrySet()) {
            message.append(entry.getKey()).append(": $").append(entry.getValue()).append("\n");
        }
        message.append("\nExpenses:\n");
        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            message.append(entry.getKey()).append(": $").append(entry.getValue()).append("\n");
        }
        message.append("\nBudget Goals:\n");
        for (Map.Entry<String, Double> entry : budgetGoals.entrySet()) {
            message.append(entry.getKey()).append(": $").append(entry.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    private void updateIncomeLabel() {
        double totalIncome = 0;
        for (double amount : income.values()) {
            totalIncome += amount;
        }
        incomeLabel.setText("Total Income: $" + totalIncome);
    }

    private void updateExpensesLabel() {
        double totalExpenses = 0;
        for (double amount : expenses.values()) {
            totalExpenses += amount;
        }
        expensesLabel.setText("Total Expenses: $" + totalExpenses);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BudgetTrackerGUI();
            }
        });
    }
}
