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

        System.out.println("----------------------------����ƥ��Ӧ��------------------------------------------------");
        System.out.println("���ʽ��[(23+12)]*[2/(3-7)] is " + MathExpression.isLegel("[(23+12)]*[2/(3-7)]"));
    }
}

interface Stack<T> {
    /**
     * �ж�ջ�Ƿ�Ϊ��
     */
    boolean isEmpty();

    /**
     * ���ջ
     */
    void clear();

    /**
     * ջ�ĳ���
     */
    int length();

    /**
     * ������ջ
     */
    boolean push(T data);

    /**
     * ���ݳ�ջ
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
        // �������е�������Ϊnull, ����GC���л���
        for (int i = 0; i < size; i++) {
            data[size] = null;
        }
        size = 0;
    }

    public int length() {
        return size;
    }

    public boolean push(T k) {
        // �ж��Ƿ���Ҫ������������
        if (size >= data.length) {
            resize();
        }
        System.out.println("push: " + k);
        data[size++] = k;
        return true;
    }

    /**
     * ��������
     */
    private void resize() {
        System.out.println("����ռ䲻��������");
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
            //ֻ��ջ����ջ����
            if (c == '[' || c == ']' || c == '(' || c == ')') {
                Character temp = stack.pop();

                // ջΪ��ʱֻ��c��ջ
                if (temp == null) {
                    stack.push(c);
                } else if (temp == '[' && c == ']') {
                    // ���ʱc����ջ
                } else if (temp == '(' && c == ')') {
                    // ���ʱc����ջ
                } else {
                    // �����ʱc��ջ
                    stack.push(temp);
                    stack.push(c);
                }
            }
        }
        return stack.isEmpty();
    }
}
