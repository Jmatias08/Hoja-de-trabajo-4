import java.util.ArrayList;

/**
 * ArrayListStack<T> - Stack implementation using ArrayList
 */
public class ArrayListStack<T> extends AbstractStack<T> {
    private ArrayList<T> elements;

    public ArrayListStack() {
        this.elements = new ArrayList<>();
    }

    @Override
    public void push(T item) {
        elements.add(item);
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return elements.remove(elements.size() - 1);
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return elements.get(elements.size() - 1);
    }

    @Override
    public int size() {
        return elements.size();
    }
}
