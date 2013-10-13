package vol1.ch3.linked_list;

/**
 * 
 * @author libojuve
 * 
 */
public class MyLinkedList<Item> {

    /**
     * Node in a linked list
     * 
     * @author libojuve
     * 
     * @param <Item>
     */
    private static class Node<Item> {
        /**
         * 元素的"值"
         */
        private Item item;
        /**
         * 下一个元素
         */
        private Node<Item> next;
    }


    /**
     * 链表的第一个元素
     */
    private Node<Item> first;
    /**
     * 链表的最后一个元素
     */
    private Node<Item> last;
    /**
     * 链表的元素个数
     */
    private int size;


    public MyLinkedList() {
        // default value
        first = null;
        last = null;
        size = 0;
    }

    public void addFirst(Item item) {
        Node<Item> newItem = new Node<Item>();
        newItem.item = item;
        newItem.next = first;
        first = newItem;
        ++size;
        // 如果链表只有一个元素，则first和last指向同一个元素，
        // 除此之外，向链接的头加入元素不影响last的值
        if (size == 1) {
            last = first;
        }
    }

    public Item removeFirst() {
        if (size > 0) {
            // 把first先保留下来
            Item firstItem = first.item;
            // 把first指向first.next(第二个元素)
            first = first.next;
            --size;
            // 如果链表没有元素，则last也应为null
            if (size == 0) {
                last = null;
            }
            return firstItem;
        }
        return null;
    }

    public void addLast(Item item) {
        Node<Item> newNode = new Node<Item>();
        newNode.item = item;
        newNode.next = null;

        last.next = newNode;
        last = newNode;
        ++size;
        // 添加完成后，如果链表中只有一个元素，则first指向last
        if (size == 1) {
            first = last;
        }
    }
}
