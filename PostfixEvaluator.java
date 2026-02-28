/**
 * PostfixEvaluator - Evaluates postfix expressions using a Double stack
 */
public class PostfixEvaluator {
    private IStack<Double> stack;

    public PostfixEvaluator(IStack<Double> stack) {
        this.stack = stack;
    }

    public double evaluate(String postfix) {
        String[] tokens = postfix.trim().split("\\s+");

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            if (isOperator(token)) {
                double b = stack.pop();
                double a = stack.pop();
                stack.push(applyOperator(a, b, token.charAt(0)));
            } else {
                stack.push(Double.parseDouble(token));
            }
        }

        return stack.pop();
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-")
                || token.equals("*") || token.equals("/")
                || token.equals("^");
    }

    private double applyOperator(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            case '^': return Math.pow(a, b);
            default: throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }
}
