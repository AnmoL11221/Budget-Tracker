import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

 class BudgetTrackerGUI extends JFrame {
    private Map<String, Double> income = new HashMap<>();
    private Map<String, Double> expenses = new HashMap<>();
    private Map<String, Double> budgetGoals = new HashMap<>();
    private double walletAmount = 0;
    private double savingAmount = 0;
    private JLabel incomeLabel;
    private JLabel expensesLabel;
    private JLabel lossLabel;
    private JLabel walletLabel;
    private JLabel savingLabel;

    public BudgetTrackerGUI() {
        setTitle("Personal Budget Tracker");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JButton addIncomeButton = createStyledButton("Add Income");
        JButton addExpenseButton = createStyledButton("Add Expense");
        JButton setBudgetGoalButton = createStyledButton("Set Budget Goal");
        JButton viewBudgetButton = createStyledButton("View Budget");
        JButton setSavingButton = createStyledButton("Set Saving Amount");

        incomeLabel = createStyledLabel("Total Income: $0.0", Color.GREEN);
        expensesLabel = createStyledLabel("Total Expenses: $0.0", Color.RED);
        lossLabel = createStyledLabel("Loss: $0.0", Color.RED);
        walletLabel = createStyledLabel("Wallet: $0.0", Color.BLUE);
        savingLabel = createStyledLabel("Saving Amount: $0.0", Color.BLUE);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.setBackground(Color.WHITE);
        panel.add(addIncomeButton);
        panel.add(incomeLabel);
        panel.add(addExpenseButton);
        panel.add(expensesLabel);
        panel.add(setBudgetGoalButton);
        panel.add(lossLabel);
        panel.add(viewBudgetButton);
        panel.add(walletLabel);
        panel.add(setSavingButton);
        panel.add(savingLabel);

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

        setSavingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSaving();
            }
        });

        // Display the frame
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.DARK_GRAY);
        button.setFocusPainted(false);
        return button;
    }

    private JLabel createStyledLabel(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private void addIncome() {
        String source = JOptionPane.showInputDialog("Enter income source:");
        double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter income amount:"));
        income.put(source, amount);
        updateIncomeLabel();
        updateLossLabel();
        updateWalletLabel();
        JOptionPane.showMessageDialog(null, "Income added successfully.");
    }

    private void addExpense() {
        String category = JOptionPane.showInputDialog("Enter expense category:");
        double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter expense amount:"));
        expenses.put(category, amount);
        updateExpensesLabel();
        updateLossLabel();
        updateWalletLabel();
        JOptionPane.showMessageDialog(null, "Expense added successfully.");
    }

    private void setBudgetGoal() {
        String category = JOptionPane.showInputDialog("Enter budget goal category:");
        double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter budget goal amount:"));
        budgetGoals.put(category, amount);
        JOptionPane.showMessageDialog(null, "Budget goal set successfully.");
    }

    private void setSaving() {
        double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter saving amount:"));
        savingAmount = amount;
        updateSavingLabel();
        JOptionPane.showMessageDialog(null, "Saving amount set successfully.");
    }

    private void viewBudget() {
        StringBuilder message = new StringBuilder();
        message.append("<html><body style='width: 300px;'>");
        message.append("<h2 style='color: blue;'>Budget Summary</h2>");
        message.append("<h3>Income:</h3>");
        for (Map.Entry<String, Double> entry : income.entrySet()) {
            message.append(entry.getKey()).append(": $").append(entry.getValue()).append("<br>");
        }
        message.append("<h3>Expenses:</h3>");
        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            message.append(entry.getKey()).append(": $").append(entry.getValue()).append("<br>");
        }
        message.append("<h3>Budget Goals:</h3>");
        for (Map.Entry<String, Double> entry : budgetGoals.entrySet()) {
            message.append(entry.getKey()).append(": $").append(entry.getValue()).append("<br>");
        }

        double totalIncome = calculateTotal(income);
        double totalExpenses = calculateTotal(expenses);
        double loss = totalExpenses - totalIncome;
        double wallet = totalIncome - totalExpenses;

        message.append("<h3>Total Income: $").append(totalIncome).append("</h3>");
        message.append("<h3>Total Expenses: $").append(totalExpenses).append("</h3>");
        message.append("<h3>Loss: $").append(loss).append("</h3>");
        message.append("<h3>Wallet: $").append(wallet).append("</h3>");
        message.append("<h3>Saving Amount: $").append(savingAmount).append("</h3>");
        message.append("</body></html>");

        JOptionPane.showMessageDialog(null, message.toString());
    }

    private double calculateTotal(Map<String, Double> map) {
        double total = 0;
        for (double amount : map.values()) {
            total += amount;
        }
        return total;
    }

    private void updateIncomeLabel() {
        double totalIncome = calculateTotal(income);
        incomeLabel.setText("Total Income: $" + totalIncome);
    }

    private void updateExpensesLabel() {
        double totalExpenses = calculateTotal(expenses);
        expensesLabel.setText("Total Expenses: $" + totalExpenses);
    }

    private void updateLossLabel() {
        double totalIncome = calculateTotal(income);
        double totalExpenses = calculateTotal(expenses);
        double loss = totalExpenses - totalIncome;
        lossLabel.setText("Loss: $" + loss);
    }

    private void updateWalletLabel() {
        double totalIncome = calculateTotal(income);
        double totalExpenses = calculateTotal(expenses);
        double wallet = totalIncome - totalExpenses;
        walletLabel.setText("Wallet: $" + wallet);
    }

    private void updateSavingLabel() {
        savingLabel.setText("Saving Amount: $" + savingAmount);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BudgetTrackerGUI();
            }
        });
    }
}
