package com.zq.linkedlist;

import java.util.Arrays;

/**
 * Created by zhengshouzi on 2015/11/16.
 */
public class StackTest {
    public static void main(String[] args) {

        System.out.println("------------------------arrayStack-------------------------------------");
        ArrayStack arrayStack = new ArrayStack();

        arrayStack.push(22);
        arrayStack.push(32);
        arrayStack.pop();
        arrayStack.push(100);
        arrayStack.push(76);
        System.out.println(arrayStack.toString());
        arrayStack.push(444);
        System.out.println(arrayStack.toString());

        System.out.println("------------------------linkedListStack-------------------------------------");

        LinkedListStack linkedListStack = new LinkedListStack();

        linkedListStack.push(22);
        linkedListStack.push(32);
        linkedListStack.pop();
        linkedListStack.push(100);
        linkedListStack.push(76);
        System.out.println(linkedListStack.toString());
        linkedListStack.push(444);
        System.out.println(linkedListStack.toString());

        System.out.println("----------------------------括号匹配应用------------------------------------------------");
        System.out.println("表达式：[(23+12)]*[2/(3-7)] is " + MathExpression.isLegel("[(23+12)]*[2/(3-7)]"));
    }
}

interface Stack<T> {
    /**
     * 判断栈是否为空
     */
    boolean isEmpty();

    /**
     * 清空栈
     */
    void clear();

    /**
     * 栈的长度
     */
    int length();

    /**
     * 数据入栈
     */
    boolean push(T data);

    /**
     * 数据出栈
     */
    T pop();
}

class ArrayStack<T> implements Stack<T> {
    private Object[] data = new Object[3];
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        // 将数组中的数据置为null, 方便GC进行回收
        for (int i = 0; i < size; i++) {
            data[size] = null;
        }
        size = 0;
    }

    public int length() {
        return size;
    }

    public boolean push(T k) {
        // 判断是否需要进行数组扩容
        if (size >= data.length) {
            resize();
        }
        System.out.println("push: " + k);
        data[size++] = k;
        return true;
    }

    /**
     * 数组扩容
     */
    private void resize() {
        System.out.println("数组空间不够，扩容");
        Object[] temp = new Object[data.length * 2];
        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
            data[i] = 0;
        }
        data = temp;
    }


    public T pop() {
        if (size == 0) {
            return null;
        }
        System.out.println("pop:" + (T) data[--size]);
        return (T) data[size];
    }

    @Override
    public String toString() {
        return "ArrayStack{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }
}


class LinkedListStack<T> implements Stack<T> {

    private StackNode top;
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        top = null;
        size = 0;
    }

    public int length() {
        return size;
    }

    public boolean push(T k) {

        StackNode newNode = new StackNode();
        newNode.setData(k);
        newNode.setPre(top);
        top = newNode;
        size++;
        System.out.println("push: " + k);
        return true;
    }

    public T pop() {
        if (size == 0) {
            return null;
        }
        T data = top.getData();
        System.out.println("pop:" + data);
        top = top.getPre();
        size--;
        return data;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        StackNode temp = top;
        while (temp != null) {
            sb.append(temp.getData() + " ");
            temp = temp.getPre();
        }

        return "LinkedListStack{" +
                "stack=" + sb.toString() +
                ", size=" + size +
                '}';
    }

    private class StackNode {
        private T data;
        private StackNode pre;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public StackNode getPre() {
            return pre;
        }

        public void setPre(StackNode pre) {
            this.pre = pre;
        }
    }

}

class MathExpression {

    public static boolean isLegel(String str) {
        Stack<Character> stack = new LinkedListStack<Character>();
        char[] arr = str.toCharArray();
        for (char c : arr) {
            //只入栈，出栈括号
            if (c == '[' || c == ']' || c == '(' || c == ')') {
                Character temp = stack.pop();

                // 栈为空时只将c入栈
                if (temp == null) {
                    stack.push(c);
                } else if (temp == '[' && c == ']') {
                    // 配对时c不入栈
                } else if (temp == '(' && c == ')') {
                    // 配对时c不入栈
                } else {
                    // 不配对时c入栈
                    stack.push(temp);
                    stack.push(c);
                }
            }
        }
        return stack.isEmpty();
    }
}
