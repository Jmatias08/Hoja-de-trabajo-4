import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * CalculatorTest - JUnit 5 tests for Stack, List, and Calculator
 */
public class CalculatorTest {

    // ===== IStack Tests =====

    @Test
    public void testArrayListStackPushPop() {
        IStack<Integer> stack = new ArrayListStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void testVectorStackPushPop() {
        IStack<Integer> stack = new VectorStack<>();
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    public void testListStackSinglyPushPop() {
        IStack<String> stack = new ListStack<>(new SinglyLinkedList<>());
        stack.push("a");
        stack.push("b");
        assertEquals("b", stack.pop());
        assertEquals("a", stack.pop());
    }

    @Test
    public void testListStackDoublyPushPop() {
        IStack<String> stack = new ListStack<>(new DoublyLinkedList<>());
        stack.push("x");
        stack.push("y");
        assertEquals("y", stack.pop());
        assertEquals("x", stack.pop());
    }

    @Test
    public void testStackIsEmpty() {
        IStack<Integer> stack = new ArrayListStack<>();
        assertTrue(stack.isEmpty());
        stack.push(5);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testStackPeek() {
        IStack<Double> stack = new VectorStack<>();
        stack.push(3.14);
        assertEquals(3.14, stack.peek());
        assertEquals(1, stack.size()); // peek should not remove
    }

    @Test
    public void testStackPopEmptyThrows() {
        IStack<String> stack = new ArrayListStack<>();
        assertThrows(RuntimeException.class, stack::pop);
    }

    // ===== IList Tests =====

    @Test
    public void testSinglyLinkedListAddRemove() {
        IList<Integer> list = new SinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.remove()); // LIFO (head)
        assertEquals(2, list.remove());
        assertEquals(1, list.remove());
    }

    @Test
    public void testDoublyLinkedListAddRemove() {
        IList<Integer> list = new DoublyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.remove()); // LIFO (tail)
        assertEquals(2, list.remove());
        assertEquals(1, list.remove());
    }

    @Test
    public void testListIsEmpty() {
        IList<String> list = new SinglyLinkedList<>();
        assertTrue(list.isEmpty());
        list.add("test");
        assertFalse(list.isEmpty());
    }

    @Test
    public void testListSize() {
        IList<String> list = new DoublyLinkedList<>();
        assertEquals(0, list.size());
        list.add("a");
        list.add("b");
        assertEquals(2, list.size());
        list.remove();
        assertEquals(1, list.size());
    }

    // ===== InfixToPostfix Tests =====

    @Test
    public void testSimpleInfixToPostfix() {
        IStack<String> stack = new ArrayListStack<>();
        InfixToPostfix converter = new InfixToPostfix(stack);
        assertEquals("1 2 9 * +", converter.convert("1+2*9"));
    }

    @Test
    public void testParenthesesInfixToPostfix() {
        IStack<String> stack = new ArrayListStack<>();
        InfixToPostfix converter = new InfixToPostfix(stack);
        assertEquals("1 2 + 9 *", converter.convert("(1+2)*9"));
    }

    @Test
    public void testMultiDigitInfixToPostfix() {
        IStack<String> stack = new ArrayListStack<>();
        InfixToPostfix converter = new InfixToPostfix(stack);
        assertEquals("10 20 + 9 *", converter.convert("(10+20)*9"));
    }

    // ===== PostfixEvaluator Tests =====

    @Test
    public void testPostfixEvaluatorSimple() {
        IStack<Double> stack = new ArrayListStack<>();
        PostfixEvaluator evaluator = new PostfixEvaluator(stack);
        assertEquals(19.0, evaluator.evaluate("1 2 9 * +"));
    }

    @Test
    public void testPostfixEvaluatorParentheses() {
        IStack<Double> stack = new ArrayListStack<>();
        PostfixEvaluator evaluator = new PostfixEvaluator(stack);
        assertEquals(27.0, evaluator.evaluate("1 2 + 9 *"));
    }

    @Test
    public void testPostfixEvaluatorMultiDigit() {
        IStack<Double> stack = new ArrayListStack<>();
        PostfixEvaluator evaluator = new PostfixEvaluator(stack);
        assertEquals(270.0, evaluator.evaluate("10 20 + 9 *"));
    }

    // ===== Calculator (Full Integration) Tests =====

    @Test
    public void testCalculatorFullFlow() {
        IStack<String> stringStack = StackFactory.getStack("arraylist", null);
        IStack<Double> doubleStack = StackFactory.getStack("arraylist", null);
        Calculator calculator = new Calculator(stringStack, doubleStack);
        assertEquals(270.0, calculator.calculate("(10+20)*9"));
    }

    @Test
    public void testCalculatorWithVectorStack() {
        IStack<String> stringStack = StackFactory.getStack("vector", null);
        IStack<Double> doubleStack = StackFactory.getStack("vector", null);
        Calculator calculator = new Calculator(stringStack, doubleStack);
        assertEquals(19.0, calculator.calculate("1+2*9"));
    }

    @Test
    public void testCalculatorWithListStackSingly() {
        IStack<String> stringStack = StackFactory.getStack("list", "singly");
        IStack<Double> doubleStack = StackFactory.getStack("list", "singly");
        Calculator calculator = new Calculator(stringStack, doubleStack);
        assertEquals(270.0, calculator.calculate("(10+20)*9"));
    }

    @Test
    public void testCalculatorWithListStackDoubly() {
        IStack<String> stringStack = StackFactory.getStack("list", "doubly");
        IStack<Double> doubleStack = StackFactory.getStack("list", "doubly");
        Calculator calculator = new Calculator(stringStack, doubleStack);
        assertEquals(270.0, calculator.calculate("(10+20)*9"));
    }

    @Test
    public void testDivisionByZero() {
        IStack<String> stringStack = StackFactory.getStack("arraylist", null);
        IStack<Double> doubleStack = StackFactory.getStack("arraylist", null);
        Calculator calculator = new Calculator(stringStack, doubleStack);
        assertThrows(ArithmeticException.class, () -> calculator.calculate("5/0"));
    }
}
/* */