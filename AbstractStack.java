/**
 * AbstractStack<T> - Abstract Stack Class
 */
public abstract class AbstractStack<T> implements IStack<T> {

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
