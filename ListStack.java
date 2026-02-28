/**
 * ListStack<T> - Stack implementation using IList (SinglyLinkedList or DoublyLinkedList)
 */
public class ListStack<T> extends AbstractStack<T> {
    private IList<T> list;

    public ListStack(IList<T> list) {
        this.list = list;
    }

    @Override
    public void push(T item) {
        list.add(item);
    }

    @Override
    public T pop() {
        return list.remove();
    }

    @Override
    public T peek() {
        return list.peek();
    }

    @Override
    public int size() {
        return list.size();
    }
}
