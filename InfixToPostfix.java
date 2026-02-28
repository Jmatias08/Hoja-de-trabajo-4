/**
 * InfixToPostfix - Converts infix expressions to postfix using a stack
 */
public class InfixToPostfix {
    private IStack<String> stack;

    public InfixToPostfix(IStack<String> stack) {
        this.stack = stack;
    }

    public String convert(String infix) {
        StringBuilder postfix = new StringBuilder();
        // Remove all spaces from infix
        infix = infix.replaceAll("\\s+", "");

        int i = 0;
        while (i < infix.length()) {
            char ch = infix.charAt(i);

            // If alphanumeric (could be multi-digit number)
            if (Character.isLetterOrDigit(ch)) {
                StringBuilder token = new StringBuilder();
                while (i < infix.length() && (Character.isLetterOrDigit(infix.charAt(i)) || infix.charAt(i) == '.')) {
                    token.append(infix.charAt(i));
                    i++;
                }
                postfix.append(token).append(" ");
                continue;
            }

            if (ch == '(') {
                stack.push(String.valueOf(ch));
            } else if (ch == ')') {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.append(stack.pop()).append(" ");
                }
                if (!stack.isEmpty()) stack.pop(); // remove '('
            } else if (isOperator(ch)) {
                while (!stack.isEmpty() && !stack.peek().equals("(")
                        && getPrecedence(stack.peek().charAt(0)) >= getPrecedence(ch)) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(String.valueOf(ch));
            }
            i++;
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    public int getPrecedence(char op) {
        switch (op) {
            case '^': return 3;
            case '*':
            case '/': return 2;
            case '+':
            case '-': return 1;
            default:  return -1;
        }
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }
}
