package vol1.ch3.exercises.ex37_josephus_problem;

import vol1.ch3.linked_list.MyLinkedListQueue;

/**
 * 1.3.37 Josephus problem. In the Josephus problem from antiquity, N people are in dire straits and agree to the
 * following strategy to reduce the population. They arrange themselves in a circle (at positions numbered from 0 to
 * N–1) and proceed around the circle, eliminating every Mth person until only one person is left. Legend has it that
 * Josephus figured out where to sit to avoid being eliminated. Write a Queue client Josephus that takes N and M from
 * the command line and prints out the order in which people are eliminated (and thus would show Josephus where to sit
 * in the circle).
 * 
 * @author libojuve@gmail.com
 * 
 */
public class JosephusProblem {
    private int totalPeople;
    private int everyMPeaple;
    private int eliminated;
    private MyLinkedListQueue<Integer> positionQueue1;
    private MyLinkedListQueue<Integer> positionQueue2;


    public JosephusProblem(int totalPeople, int everyMPeople) {
        this.totalPeople = totalPeople;
        everyMPeaple = everyMPeople;
        eliminated = 0;

        positionQueue1 = new MyLinkedListQueue<Integer>();
        // init queue
        for (int i = 0; i < totalPeople; ++i) {
            positionQueue1.enqueue(i);
        }

        positionQueue2 = new MyLinkedListQueue<Integer>();
    }

    /**
     * 在queue1中的元素全部移除时使用，queue1中的元素，有些被删掉，有些移到了queue2
     */
    private void swapQueue() {
        MyLinkedListQueue<Integer> queue3 = positionQueue1;
        positionQueue1 = positionQueue2;
        positionQueue2 = queue3;
    }

    // 因为Queue API所限，不能直接删除给定位置的元素，所以得用另一个queue来接收不删除的元素，这样子来回倒腾。
    public void eliminate() {
        int count = 0;
        while (eliminated < totalPeople) {
            // 用来储存数到第n个人
            while (positionQueue1.size() > 0) {
                ++count;
                // 记到第M个人，杀死此人，即把他移除出queue1
                if (count == everyMPeaple) {
                    int position = positionQueue1.dequeue();
                    System.out.print(position + " ");
                    ++eliminated;
                    // 重新开始计数
                    count = 0;

                }
                // 其余人在本轮中幸存，即移到queue2中
                else {
                    positionQueue2.enqueue(positionQueue1.dequeue());
                }
            }
            // queue1中的元素已经全部移出（一部分删掉，一部分转到了queue2）
            // 现在交换queue1和queue2
            swapQueue();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        JosephusProblem jp = new JosephusProblem(7, 8);
        jp.eliminate();
    }
}
