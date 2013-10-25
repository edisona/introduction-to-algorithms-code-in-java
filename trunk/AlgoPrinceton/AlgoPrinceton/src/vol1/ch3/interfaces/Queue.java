package vol1.ch3.interfaces;

/**
 * Queue API in Chapter 3
 * 
 * @author libojuve
 * 
 * @param <E>
 */
public interface Queue<E> extends Iterable<E> {
    void enqueue(E item);

    E dequeue();

    boolean isEmpty();

    int size();
}
