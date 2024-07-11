import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class BudgetTracker {
    private static Map<String, Double> income = new HashMap<>();
    private static Map<String, Double> expenses = new HashMap<>();
    private static Map<String, Double> budgetGoals = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isAuthenticated = false;

        // User Authentication
        isAuthenticated = authenticateUser(scanner);

        if (isAuthenticated) {
            while (true) {
                System.out.println("1. Add Income");
                System.out.println();
                System.out.println("2. Add Expense");
                System.out.println();
                System.out.println("3. Set Budget Goal");
                System.out.println();
                System.out.println("4. View Budget");
                System.out.println();
                System.out.println("5. Exit");
                System.out.println();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addIncome(scanner);
                        break;
                    case 2:
                        addExpense(scanner);
                        break;
                    case 3:
                        setBudgetGoal(scanner);
                        break;
                    case 4:
                        viewBudget();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        System.out.println();
                        return;
                    default:
                        System.out.println("Invalid choice! Please enter a valid option.");
                        System.out.println();
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting...");
            System.out.println();
        }
    }

    private static boolean authenticateUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.println();
        System.out.print("Enter password: ");
        String password = scanner.next();
        System.out.println();

        // Add authentication logic here, for simplicity, let's assume a hardcoded
        // username and password
        return username.equals("x") && password.equals("y");
    }

    private static void addIncome(Scanner scanner) {
        System.out.print("Enter income source: ");
        String source = scanner.next();
        System.out.println();
        System.out.print("Enter income amount: ");
        double amount = scanner.nextDouble();
        System.out.println();
        income.put(source, amount);
        System.out.println("Income added successfully.");
        System.out.println();
    }
    
    private static void addExpense(Scanner scanner) {
        System.out.print("Enter expense category: ");
        String category = scanner.next();
        System.out.println();
        System.out.print("Enter expense amount: ");
        double amount = scanner.nextDouble();
        System.out.println();
        expenses.put(category, amount);
        System.out.println("Expense added successfully.");
        System.out.println();
    }
    
    private static void setBudgetGoal(Scanner scanner) {
        System.out.print("Enter budget goal category: ");
        String category = scanner.next();
        System.out.println();
        System.out.print("Enter budget goal amount: ");
        double amount = scanner.nextDouble();
        System.out.println();
        budgetGoals.put(category, amount);
        System.out.println("Budget goal set successfully.");
        System.out.println();
    }
    
    private static void viewBudget() {
        System.out.println("Income:");
        System.out.println();
        for (Map.Entry<String, Double> entry : income.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
        
        System.out.println("Expenses:");
        System.out.println("Income:");
        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
        
        System.out.println("Budget Goals:");
        System.out.println("Income:");
        for (Map.Entry<String, Double> entry : budgetGoals.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }
}
