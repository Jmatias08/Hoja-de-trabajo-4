/**
 * SinglyLinkedList<T> - Singly Linked List implementation
 * Operates as a stack (LIFO) - adds/removes from head
 */
public class SinglyLinkedList<T> extends AbstractList<T> {
    private Node<T> head;

    public SinglyLinkedList() {
        this.head = null;
    }

    @Override
    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = head;
        head = newNode;
        size++;
    }

    @Override
    public T remove() {
        if (isEmpty()) throw new RuntimeException("List is empty");
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new RuntimeException("List is empty");
        return head.data;
    }
}
