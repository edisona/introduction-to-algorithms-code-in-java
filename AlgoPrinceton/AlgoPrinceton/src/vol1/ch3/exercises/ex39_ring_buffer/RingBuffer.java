package vol1.ch3.exercises.ex39_ring_buffer;

/**
 * 1.3.39 Ring buffer. A ring buffer, or circular queue, is a FIFO data structure of a fixed size N. It is useful for
 * transferring data between asynchronous processes or for storing log files. When the buffer is empty, the consumer
 * waits until data is deposited; when the buffer is full, the producer waits to deposit data. Develop an API for a
 * RingBuffer and an implementation that uses an array representation (with circular wrap-around).
 * 
 * @author libojuve@gmail.com
 * 
 * @param <E>
 */
public class RingBuffer<E> {
    private Object[] elements;
    private int first;
    private int last;
    private int size;


    /**
     * 创建一个容量为<code>capacity</code>的ring buffer
     * 
     * @param capacity
     */
    public RingBuffer(int capacity) {
        elements = new Object[capacity];
        first = -1;
        last = -1;
        size = 0;
    }

    /**
     * 返回ring buffer中的元素个数
     * 
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 返回ring buffer最多可以加入的元素个数（容量）
     * 
     * @return
     */
    public int capacity() {
        return elements.length;
    }


    /**
     * 返回ring buffer是否为空
     * 
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回ring buffer是否已满
     * 
     * @return
     */
    public boolean isFull() {
        return size == elements.length;
    }

    /**
     * 在ring buffer尾加入一个新元素e <br/>
     * 如果e成功加入，则返回<code>true</code> <br/>
     * 如果ring buffer已满，则返回<code>false</code> <br/>
     * 
     * @param e
     */
    public boolean enqueue(E e) {
        // 不满时可以加入
        if (!isFull()) {
            // last指向下一个空位
            ++last;
            // 如果超出界限，则从0开始
            if (last == elements.length) {
                last = 0;
            }
            elements[last] = e;
            ++size;

            // 特殊情况：如果e为ring buffer第一个元素，则还需要改动first
            if (size == 1) {
                first = last;
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 删除ring buffer头部第一个元素，并将其返回 <br/>
     * 如果ring buffer为空，则返回<code>null</code> <br/>
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public E dequeue() {
        if (!isEmpty()) {
            E elt = (E) elements[first];
            ++first;
            // 超过界限则归0
            if (first == elements.length) {
                first = 0;
            }
            --size;
            // 特殊情况：如果ring buffer为空，则需要改动last
            if (size == 0) {
                last = first;
            }
            return elt;
        }
        // 空则返回null
        return null;
    }
}
