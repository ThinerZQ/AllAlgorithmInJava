package linkedlist;

import java.util.Arrays;

/**
 * Created by zhengshouzi on 2015/11/16.
 */
public class QueueTest {
    public static void main(String[] args) {
        Queue<Integer> queue = null;

        System.out.println("------------------------arrayLinerQueue-------------------------------------");

        queue = new ArrayLinerQueue();

        queue.enQueue(32);
        queue.enQueue(43);
        queue.deQueue();
        queue.enQueue(98);

        System.out.println(queue.toString());
        queue.enQueue(444);
        System.out.println(queue.toString());
        queue.enQueue(444);

        System.out.println(queue.toString());

        System.out.println("------------------------arrayCycleQueue-------------------------------------");

        queue = new ArrayCycleQueue<>();

        queue.enQueue(32);
        queue.enQueue(43);
        queue.deQueue();
        queue.enQueue(98);

        System.out.println(queue.toString());
        queue.enQueue(444);
        System.out.println(queue.toString());
        queue.enQueue(567);
        queue.enQueue(1200);

        System.out.println(queue.toString());


        System.out.println("------------------------linkedListQueue-------------------------------------");

        queue = new LinkedListQueue();

        queue.enQueue(32);
        queue.enQueue(43);
        queue.deQueue();
        queue.enQueue(98);

        System.out.println(queue.toString());
        queue.enQueue(444);

        System.out.println(queue.toString());
        queue.enQueue(567);
        queue.enQueue(1200);

        System.out.println(queue.toString());

    }
}

//队列接口
interface Queue<T> {
    boolean enQueue(T k);

    T deQueue();

    boolean isEmpty();

    void clear();

    int length();
}

//顺序队列
class ArrayLinerQueue<T> implements Queue<T> {

    private int size = 0;
    private int head = 0;
    private int tail = 0;
    private Object[] data = new Object[4];

    @Override
    public boolean enQueue(T k) {
        // 判断是否需要进行数组扩容
        if (tail >= data.length) {
            resize();
        }
        System.out.println("enQueue: " + k);
        data[tail++] = k;
        size++;
        return true;
    }

    /**
     * 数组扩容
     */
    private void resize() {
        System.out.println("数组空间不够，扩容");
        Object[] temp = new Object[data.length * 2];
        for (int i = head; i < tail; i++) {
            temp[i] = data[i];
            data[i] = 0;
        }
        data = temp;
    }

    @Override
    public T deQueue() {
        if (size == 0) {
            return null;
        }
        System.out.println("deQueue:" + (T) data[head]);
        size--;
        return (T) data[head++];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        // 将数组中的数据置为null, 方便GC进行回收
        for (int i = 0; i < size; i++) {
            data[size] = null;
        }
        size = 0;
        head = 0;
        tail = 0;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "size=" + size +
                ", head=" + head +
                ", tail=" + tail +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}

//顺序循环队列
class ArrayCycleQueue<T> implements Queue<T> {

    private int size = 0;
    private int head = 0;
    private int tail = 0;
    private Object[] data = new Object[4];

    @Override
    public boolean enQueue(T k) {
        // 判断是否需要进行数组扩容
        if (head == tail && head != 0) {
            resize();
        }
        if (tail >= data.length && head > 0) {
            tail = 0;
        }
        System.out.println("enQueue: " + k);
        data[tail++] = k;
        size++;
        return true;
    }

    /**
     * 数组扩容
     */
    private void resize() {
        System.out.println("数组空间不够，扩容");
        Object[] temp = new Object[data.length * 2];
        for (int i = head + data.length; i < temp.length; i++, head++) {
            temp[i] = data[head];
        }
        for (int i = 0; i < tail; i++) {
            temp[i] = data[i];
        }

        data = temp;
    }

    @Override
    public T deQueue() {
        if (size == 0) {
            return null;
        }
        System.out.println("deQueue:" + (T) data[head]);
        size--;
        return (T) data[head++];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        // 将数组中的数据置为null, 方便GC进行回收
        for (int i = 0; i < size; i++) {
            data[size] = null;
        }
        size = 0;
        head = 0;
        tail = 0;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "size=" + size +
                ", head=" + head +
                ", tail=" + tail +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}

//链表实现队列
class LinkedListQueue<T> implements Queue<T> {

    private int size = 0;
    private QueueNode head = null;
    private QueueNode tail = null;

    @Override
    public boolean enQueue(T k) {

        System.out.println("enQueue: " + k);
        QueueNode queueNode = new QueueNode();
        queueNode.setData(k);

        if (tail == null) {
            tail = queueNode;
            head = tail;
        } else {
            tail.setNext(queueNode);
            tail = queueNode;
        }
        size++;
        return true;
    }

    @Override
    public T deQueue() {
        if (size == 0) {
            return null;
        }
        T tempdata = head.getData();
        head = head.getNext();
        System.out.println("deQueue:" + tempdata);
        size--;
        return tempdata;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        QueueNode temp = head;
        while (temp != null) {
            sb.append(temp.getData() + " ");
            temp = temp.getNext();
        }
        return "LinkedListQueue{" +
                "size=" + size +
                ", head=" + head.getData() +
                ", tail=" + tail.getData() +
                ", queue=" + sb.toString() +
                '}';
    }

    private class QueueNode {
        private T data;
        private QueueNode next;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public QueueNode getNext() {
            return next;
        }

        public void setNext(QueueNode next) {
            this.next = next;
        }
    }

}