/**
 * DoublyLinkedList<T> - Doubly Linked List implementation
 * Operates as a stack (LIFO) - adds/removes from tail
 */
public class DoublyLinkedList<T> extends AbstractList<T> {
    private Node<T> head;
    private Node<T> tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T remove() {
        if (isEmpty()) throw new RuntimeException("List is empty");
        T data = tail.data;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return data;
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new RuntimeException("List is empty");
        return tail.data;
    }
}
