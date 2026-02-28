/**
 * IList<T> - List Interface
 */
public interface IList<T> {
    void add(T item);
    T remove();
    T peek();
    boolean isEmpty();
    int size();
}
