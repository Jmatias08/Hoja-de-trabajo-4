import java.util.Scanner;
import java.io.IOException;

/**
 * Main - Entry point, asks user for stack implementation choice
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Infix Expression Calculator ===");
        System.out.println("Choose stack implementation:");
        System.out.println("  1. ArrayList");
        System.out.println("  2. Vector");
        System.out.println("  3. List (Linked List)");
        System.out.print("Enter choice (1-3): ");

        String stackType = "arraylist";
        String listType = "singly";

        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1": stackType = "arraylist"; break;
            case "2": stackType = "vector";    break;
            case "3":
                stackType = "list";
                System.out.println("Choose list implementation:");
                System.out.println("  1. Singly Linked List");
                System.out.println("  2. Doubly Linked List");
                System.out.print("Enter choice (1-2): ");
                String listChoice = scanner.nextLine().trim();
                listType = listChoice.equals("2") ? "doubly" : "singly";
                break;
            default:
                System.out.println("Invalid choice. Defaulting to ArrayList.");
        }

        System.out.println("\nUsing: " + stackType.toUpperCase()
                + (stackType.equals("list") ? " (" + listType + ")" : ""));

        IStack<String> stringStack = StackFactory.getStack(stackType, listType);
        IStack<Double> doubleStack = StackFactory.getStack(stackType, listType);
        Calculator calculator = new Calculator(stringStack, doubleStack);

        // Read expression from file or manual input
        String expression = "";
        try {
            expression = calculator.readFromFile("datos.txt");
            System.out.println("Expression from file: " + expression);
        } catch (IOException e) {
            System.out.print("datos.txt not found. Enter infix expression manually: ");
            expression = scanner.nextLine().trim();
        }

        try {
            double result = calculator.calculate(expression);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Error evaluating expression: " + e.getMessage());
        }

        scanner.close();
    }
}
