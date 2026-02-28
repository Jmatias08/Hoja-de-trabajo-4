import java.util.Vector;

/**
 * VectorStack<T> - Stack implementation using Vector
 */
public class VectorStack<T> extends AbstractStack<T> {
    private Vector<T> elements;

    public VectorStack() {
        this.elements = new Vector<>();
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
