import java.io.*;
import java.nio.file.*;

/**
 * Calculator - Main class that orchestrates infix evaluation
 */
public class Calculator {
    private InfixToPostfix infixToPostfix;
    private PostfixEvaluator evaluator;

    public Calculator(IStack<String> stringStack, IStack<Double> doubleStack) {
        this.infixToPostfix = new InfixToPostfix(stringStack);
        this.evaluator = new PostfixEvaluator(doubleStack);
    }

    public double calculate(String infix) {
        String postfix = infixToPostfix.convert(infix);
        System.out.println("Postfix: " + postfix);
        return evaluator.evaluate(postfix);
    }

    public String readFromFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename))).trim();
    }
}
