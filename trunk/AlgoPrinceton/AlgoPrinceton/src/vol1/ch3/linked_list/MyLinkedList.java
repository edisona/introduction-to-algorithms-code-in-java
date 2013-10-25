package vol1.ch3.linked_list;

import java.util.Random;


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


        @Override
        public String toString() {
            return item.toString();
        }
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

    public int size() {
        return size;
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

        // 如果list中还没有元素
        if (last == null) {
            last = newNode;
            first = last;
        } else {
            last.next = newNode;
            last = newNode;
        }
        ++size;
    }

    /**
     * Exercise 1.3.19 <br/>
     * Give a code fragment that removes the last node in a linked list whose
     * first node is first.
     */
    public Item removeLast() {
        // 如果只有一个元素，则first与last相等，直接删掉即可
        if (size == 1) {
            first = last = null;
            --size;
        }
        // 如果有超过两个元素，则需要先找到last之前的那个元素
        else if (size > 1) {
            Node<Item> node = first;
            while (node.next.next != null) {
                node = node.next;
            }
            // 这时，node.next.next == null，亦即node是倒数第二个元素
            // 删除最后一个元素
            Item item = node.next.item;
            last = node;
            node.next = null;
            --size;
            return item;
        }

        return null;
    }

    /**
     * 1.3.20 Write a method delete() that takes an int argument k and deletes
     * the kth element in a linked list, if it exists.
     * 
     * 这里，我假设k是从0开始的
     * 
     * @param k
     *            从0开始
     */
    public Item delete(int k) {
        // 特殊情况：
        // 如果k为0，则是删除第一个元素
        if (k == 0) {
            return removeFirst();
        }
        // 因为k从0开始，到size - 1结束
        else if (k == size - 1) {
            return removeLast();
        }
        // k在0到size - 1之间，两边都不包括
        else if (k > 0 && k < size - 1) {
            // 查找k元素之前的元素，才好将k元素删除
            Node<Item> node = first;
            int i = 0;
            while (i < k - 1) {
                ++i;
                node = node.next;
            }
            Item item = node.next.item;
            node.next = node.next.next;
            --size;
            return item;
        }
        // 没有kth element存在
        else {
            return null;
        }
    }

    /**
     * 1.3.21 Write a method find() that takes a linked list and a string key as
     * arguments and returns true if some node in the list has key as its item
     * field, false otherwise.
     * 
     * 我觉得这个方法不用static，然后不要接收一个list作为参数更好，所以没有按要求来做。另外，因为此类是泛型，
     * 所以接收的key也由String变成了Item
     */
    public boolean find(Item key) {
        boolean found = false;
        for (Node<Item> node = first; !found && node.next != null; node = node.next) {
            if (key.equals(node.item.toString())) {
                found = true;
            }
        }
        return found;
    }

    /**
     * 1.3.24 Write a method removeAfter() that takes a linked-list Node as
     * argument and removes the node following the given one (and does nothing
     * if the argument or the next field in the argument node is null).
     */
    private void removeAfter(Node<Item> node) {
        if (node != null && node.next != null) {
            node.next = node.next.next;
        }
    }

    /**
     * 1.3.25 Write a method insertAfter() that takes two linked-list Node
     * arguments and inserts the second after the first on its list (and does
     * nothing if either argument is null).
     * 
     * @param node
     *            node after which <code>nodeToInsert</code> is inserted
     * @param nodeToInsert
     *            node to insert after <code>node</code>
     */
    private void insertAfter(Node<Item> node, Node<Item> nodeToInsert) {
        if (node != null && nodeToInsert != null) {
            nodeToInsert.next = node.next;
            node.next = nodeToInsert;
        }
    }

    /**
     * 1.3.26 Write a method remove() that takes a linked list and a string key
     * as arguments and removes all of the nodes in the list that have key as
     * its item field. <br/>
     * 
     * 如果key为null，则什么都不做。<br/>
     * 
     * 此题的描述似乎是要用一个static的方法，不过我觉得做成不是static的方法也行。另外，因为此类是泛型，
     * 所以接收的key也由String变成了Item<br/>
     * 
     * 
     * @param key
     *            Item to delete
     */
    /*
     * 为了要删掉元素k，需要知道k之前的元素，所以要找等于key之前的那个元素，这样子就有一个特殊情况，
     * 即key元素是第一个元素
     * 
     * 这个特殊情况最后再处理，如果先处理，并把first删掉了，那么第二个元素则变成第一个，又要考虑first的特殊情况，
     * 以此类推，无穷无尽。而先处理first之后的部分，再处理first，就可以避免反复考虑特殊情况的问题。
     */
    public void remove(Item key) {
        if (key != null) {
            for (Node<Item> nodeBefore = first; nodeBefore != null && nodeBefore.next != null; nodeBefore = nodeBefore.next) {
                if (key.equals(nodeBefore.next.item)) {
                    removeAfter(nodeBefore);
                }
            }

            // 最后再处理key是否为first的情况
            if (first != null && key.equals(first.item)) {
                removeFirst();
            }
        }
    }

    /**
     * 1.3.27 Write a method max() that takes a reference to the first node in a linked list
     * as argument and returns the value of the maximum key in the list. Assume that all keys
     * are positive integers, and return 0 if the list is empty.
     * 
     * 为了实现方便，把注意力更集中在算法本身，所以这里把这个方法实现成static的，这样子就可以在参数中
     * 传入一个MyLinkedList<Integer>。
     */
    private static int max(Node<Integer> firstNode) {
        int max = 0;
        // 如果list中有元素
        if (firstNode != null) {
            max = firstNode.item;
            for (Node<Integer> node = firstNode.next; node != null; node = node.next) {
                if (node.item > max) {
                    max = node.item;
                }
            }
        }
        return max;
    }

    /**
     * 1.3.28 Develop a recursive solution to the previous question.
     */
    /*
     * 所有的list中的节点，都可以看作是一个list的起点。
     * 当节点为null时，max值为0
     * 当节点不为null时，max值为当前节点值与节点next为first的子list的max值中的较大者。
     */
    private static int maxRecursive(Node<Integer> firstNode) {
        // 如果list中没有元素，返回0，即最大值为0
        if (firstNode == null) {
            return 0;
        }
        // firstNode != null，list中有元素
        int value = firstNode.item;
        int subMax = maxRecursive(firstNode.next);
        if (value > subMax) {
            return value;
        } else {
            return subMax;
        }
    }

    /**
     * 1.3.30 Write a function that takes the first Node in a linked list as argument and (destructively) reverses the
     * list, returning the first Node in the result.
     */
    private Node<Item> reverse(Node<Item> firstNode) {
        // list至少有两个元素
        if (firstNode != null && firstNode.next != null) {
            Node<Item> node1 = firstNode;
            Node<Item> node2 = firstNode.next;
            node1.next = null;
            last = node1;
            while (node2 != null) {
                Node<Item> node2NextValue = node2.next;

                node2.next = node1;
                node1 = node2;
                node2 = node2NextValue;
            }
            first = node1;
            return node1;
        }
        // list只有一个元素
        else if (firstNode != null) {
            return firstNode;
        }
        // list为空
        return null;
    }

    /**
     * 1.3.30 Write a function that takes the first Node in a linked list as argument and (destructively) reverses the
     * list, returning the first Node in the result.
     */
    private Node<Item> reverseRecursive(Node<Item> firstNode) {
        // list为空
        if (firstNode == null) {
            return null;
        }

        // list只有一个元素，什么都不做，直接返回
        if (firstNode.next == null) {
            return firstNode;
        }

        Node<Item> second = firstNode.next;

        Node<Item> rest = reverseRecursive(second);
        firstNode.next = null;
        second.next = firstNode;

        return rest;

    }



    // --------------------- 测试用的方法 -----------------------
    private void printState() {
        System.out.println("---------------------------------");
        System.out.println("----------Linked State-----------");
        for (Node<Item> node = first; node != null; node = node.next) {
            System.out.print(node.item + " --> ");
        }
        System.out.println("null");
        System.out.println("---------------------------------");
    }


    private static <E> void testAddFirst(MyLinkedList<E> list, E item) {
        list.addFirst(item);
        list.printState();
    }

    private static <E> void testAddLast(MyLinkedList<E> list, E item) {
        list.addLast(item);
        list.printState();
    }

    private static void testRemoveFirst(MyLinkedList<?> list) {
        System.out.println("Remove " + list.removeFirst());
        list.printState();
    }

    private static void testRemoveLast(MyLinkedList<?> list) {
        System.out.println("Remove " + list.removeLast());
        list.printState();
    }

    private static void testDelete(MyLinkedList<?> list, int k) {
        System.out.println("Remove " + k + "th element: " + list.delete(k));
        list.printState();
    }

    private static MyLinkedList<String> initTest() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.printState();

        testAddFirst(list, "1");
        testAddFirst(list, "2");
        testAddFirst(list, "3");

        testAddLast(list, "4");
        testAddLast(list, "5");
        return list;
    }

    private static void testBasic() {
        MyLinkedList<String> list = initTest();

        for (int i = 0; i < 5; ++i) {
            testRemoveFirst(list);
        }
    }

    // removeLast
    private static void testEx1_3_19() {
        MyLinkedList<String> list = initTest();

        System.out.println("*******Test remove first*******");
        for (int i = 0; i < 5; ++i) {
            testRemoveLast(list);
        }
    }

    private static void testEx_1_3_20() {
        MyLinkedList<String> list = initTest();

        System.out.println("*******Test delete kth element*******");
        testDelete(list, 0);
        testDelete(list, list.size - 1);
        testDelete(list, 1);
    }

    private static void testEx_1_3_21() {
        MyLinkedList<String> list = initTest();
        System.out.println("*******Test ex 1.3.21*******");
        System.out.println("Finding 1 : " + list.find("1"));
        System.out.println("Finding 3 : " + list.find("3"));
        System.out.println("Finding 100 : " + list.find("100"));
    }

    private static void testEx_1_3_24() {
        MyLinkedList<String> list = initTest();
        System.out.println("*******Test ex 1.3.24*******");
        Node<String> node = list.first;
        for (int i = 0; i < 2; ++i) {
            node = node.next;
        }
        System.out.println("Node : " + node.item + ", removing " + node.next.item);
        list.removeAfter(node);
        list.printState();
    }

    private static void testEx_1_3_26() {
        MyLinkedList<String> list = initTest();
        list.addFirst("1");
        list.addFirst("1");
        list.addLast("1");
        list.printState();

        testRemove(list, "1");

        testRemove(list, "3");

        testRemove(list, "10");

        testRemove(list, "4");

        testRemove(list, "5");

        testRemove(list, "2");

        testRemove(list, "10");
    }

    private static MyLinkedList<Integer> initIntegerList() {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        Random random = new Random();

        for (int i = 0; i < 10; ++i) {
            list.addLast(random.nextInt(100));
        }

        list.printState();
        return list;
    }

    private static void testEx_1_3_27() {
        MyLinkedList<Integer> list = initIntegerList();

        System.out.println("Max value: " + max(list.first));
    }

    private static <E> void testRemove(MyLinkedList<E> list, E key) {
        System.out.println("Removing " + key);
        list.remove(key);
        list.printState();
    }

    private static void testEx_1_3_28() {
        MyLinkedList<Integer> list = initIntegerList();

        System.out.println("Max value: " + maxRecursive(list.first));

        list = new MyLinkedList<Integer>();
        System.out.println("Max value: " + maxRecursive(list.first));

        MyLinkedList.testAddLast(list, 15);
        System.out.println("Max value: " + maxRecursive(list.first));
    }

    private static <E> void testReverse(MyLinkedList<E> list) {
        System.out.println("List before reverse: ");
        list.printState();
        System.out.println("First element in reversed list: " + list.reverse(list.first));
        System.out.println("List after reverse: ");
        list.printState();
    }

    private static void testEx_1_3_30() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        testReverse(list);

        list.addFirst("2");
        testReverse(list);

        list = initTest();
        testReverse(list);
    }

    public static void main(String[] args) {
        // testBasic();
        // testEx1_3_19();
        // testEx_1_3_20();
        // testEx_1_3_21();
        // testEx_1_3_24();
        // testEx_1_3_26();
        // testEx_1_3_27();
        // testEx_1_3_28();
        testEx_1_3_30();
    }

}
