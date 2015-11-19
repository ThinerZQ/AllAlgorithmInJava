package com.zq.linkedlist;

/**
 * Created by zhengshouzi on 2015/10/1.
 */
public class LinkedTest {
    public static void main(String[] args) {

        SingleLink singleLink = new SingleLink();

        singleLink.insert(10);
        singleLink.insert(20);
        singleLink.insert(21);
        singleLink.insert(4);

        singleLink.print();

        System.out.println("----------------after sort -----------------");
        singleLink.sort();
        singleLink.print();

        singleLink.search(10);
        singleLink.delete(10);
        singleLink.print();
        singleLink.search(10);

        singleLink.min(singleLink.getRoot());

        System.out.println("size = " + singleLink.size());
        System.out.println("enpty = " + singleLink.isEmpty());


        System.out.println("------------------------------双向链表--------------------------------------");
        DoubleLink doubleLink = new DoubleLink();
        doubleLink.insert(30);
        doubleLink.insert(20);
        doubleLink.insert(21);
        doubleLink.insert(89);
        doubleLink.insert(8);

        doubleLink.print();

        System.out.println("----------------after sort -----------------");
        doubleLink.sort();
        doubleLink.print();

        doubleLink.search(10);
        doubleLink.delete(10);
        doubleLink.print();
        doubleLink.search(10);

        doubleLink.min(doubleLink.getRoot());

        System.out.println("size = " + doubleLink.size());
        System.out.println("enpty = " + doubleLink.isEmpty());
    }
}

//单向链表
class SingleLink {
    private Node root;
    private int count;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void insert(int data) {
        Node node = new Node(data);
        System.out.println("插入数据：" + data);
        if (root != null) {
            this.root.addNode(node);
        } else {
            this.root = node;
        }
        this.count++;
    }

    public void delete(int k) {
        //先查找到这个Node
        Node currentNode = root;
        Node lastNode = currentNode;
        while (currentNode != null && currentNode.getData() != k) {
            lastNode = currentNode;
            currentNode = currentNode.getNext();
        }

        if (currentNode == root) {
            //要删除的元素是第一个，或者链表为空
            if (currentNode == null) {
                System.out.println("链表为空");
            } else {
                //要删除的是第一个
                root = root.getNext();
                System.out.println("删除表头：" + k);
                count--;
            }
        } else {
            //断开连接
            System.out.println("删除：" + k);
            lastNode.setNext(currentNode.getNext());
            count--;
        }
    }

    public Node search(int k) {

        Node currentNode = root;
        while (currentNode != null && currentNode.getData() != k) {
            currentNode = currentNode.getNext();
        }
        if (currentNode != null) {
            System.out.println("找到：" + k);
        } else {
            System.out.println("没有 " + k + " 数据");
        }
        return currentNode;
    }

    //排序，默认从小到大排列
    public void sort() {

        Node node = root;

        for (int i = 1; i <= count; i++) {

            Node tempRoot = this.getRoot();
            int j = i;
            while (j > 1) {
                tempRoot = tempRoot.getNext();
                j--;
            }
            Node minNode = min(tempRoot);

            //交换节点值
            int temp = tempRoot.getData();
            tempRoot.setData(minNode.getData());
            minNode.setData(temp);
        }
    }

    public Node min(Node root) {
        Node node = root;

        if (root != null) {
            int min = root.getData();
            while (root != null) {
                if (root.getData() < min) {
                    min = root.getData();
                    node = root;
                }
                root = root.getNext();
            }
            // System.out.println("min = " + min);
        }
        return node;
    }

    public void print() {
        System.out.print("输出数据：");
        if (this.root != null) {
            this.root.printNode();
        }
        System.out.println();
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void clean() {
        this.root = null;
        this.count = 0;
        System.out.println("清空链表");
    }

    public Node get(int index) {

        Node currentNode = root;
        if (index <= count && index > 0) {
            index--;
            while (currentNode != null && index > 0) {
                currentNode = currentNode.getNext();
                index--;
            }
        }
        return currentNode;
    }

    private class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void addNode(Node newNode) {
            if (this.next == null) {
                this.next = newNode;
            } else {
                this.next.addNode(newNode);
            }
        }

        public void printNode() {
            System.out.print(this.getData() + " ");
            if (this.next != null) {
                this.next.printNode();
            }
        }

    }
}


//双向链表
class DoubleLink {
    private Node root;
    private int count;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void insert(int data) {
        Node node = new Node(data);
        System.out.println("插入数据：" + data);
        if (root != null) {
            this.root.addNode(node);
        } else {
            this.root = node;
        }
        this.count++;
    }

    public void delete(int k) {
        //先查找到这个Node
        Node currentNode = root;

        while (currentNode != null && currentNode.getData() != k) {
            currentNode = currentNode.getNext();
        }

        if (currentNode == root) {
            //要删除的元素是第一个，或者链表为空
            if (currentNode == null) {
                System.out.println("链表为空");
            } else {
                //要删除的是第一个
                root = root.getNext();
                System.out.println("删除表头：" + k);
                count--;
            }
        } else {
            if (currentNode == null) {
                System.out.println("没有 " + k + " 数据");
            } else {
                //断开连接
                System.out.println("删除：" + k);
                currentNode.getPrior().setNext(currentNode.getNext());
                currentNode.getNext().setPrior(currentNode.getPrior());
                count--;
            }

        }
    }

    public Node search(int k) {

        Node currentNode = root;
        while (currentNode != null && currentNode.getData() != k) {
            currentNode = currentNode.getNext();
        }
        if (currentNode != null) {
            System.out.println("找到：" + k);
        } else {
            System.out.println("没有 " + k + " 数据");
        }
        return currentNode;
    }

    //排序，默认从小到大排列
    public void sort() {

        Node node = root;

        for (int i = 1; i <= count; i++) {

            Node tempRoot = this.getRoot();
            int j = i;
            while (j > 1) {
                tempRoot = tempRoot.getNext();
                j--;
            }
            Node minNode = min(tempRoot);

            //交换节点值
            int temp = tempRoot.getData();
            tempRoot.setData(minNode.getData());
            minNode.setData(temp);
        }
    }

    public Node min(Node root) {
        Node node = root;

        if (root != null) {
            int min = root.getData();
            while (root != null) {
                if (root.getData() < min) {
                    min = root.getData();
                    node = root;
                }
                root = root.getNext();
            }
            //System.out.println("min = " + min);
        }
        return node;
    }

    public void print() {
        System.out.print("输出数据：");
        if (this.root != null) {
            this.root.printNode();
        }
        System.out.println();
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void clean() {
        this.root = null;
        this.count = 0;
        System.out.println("清空链表");
    }

    public Node get(int index) {

        Node currentNode = root;
        if (index <= count && index > 0) {
            index--;
            while (currentNode != null && index > 0) {
                currentNode = currentNode.getNext();
                index--;
            }
        }
        return currentNode;
    }

    private class Node {
        private int data;
        private Node prior;
        private Node next;

        public Node getPrior() {
            return prior;
        }

        public void setPrior(Node prior) {
            this.prior = prior;
        }

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void addNode(Node newNode) {
            if (this.next == null) {
                newNode.setPrior(this);
                this.next = newNode;
            } else {
                this.next.addNode(newNode);
            }
        }

        public void printNode() {
            System.out.print(this.getData() + " ");
            if (this.next != null) {
                this.next.printNode();
            }
        }

    }

}


