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


        System.out.println("------------------------------˫������--------------------------------------");
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

//��������
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
        System.out.println("�������ݣ�" + data);
        if (root != null) {
            this.root.addNode(node);
        } else {
            this.root = node;
        }
        this.count++;
    }

    public void delete(int k) {
        //�Ȳ��ҵ����Node
        Node currentNode = root;
        Node lastNode = currentNode;
        while (currentNode != null && currentNode.getData() != k) {
            lastNode = currentNode;
            currentNode = currentNode.getNext();
        }

        if (currentNode == root) {
            //Ҫɾ����Ԫ���ǵ�һ������������Ϊ��
            if (currentNode == null) {
                System.out.println("����Ϊ��");
            } else {
                //Ҫɾ�����ǵ�һ��
                root = root.getNext();
                System.out.println("ɾ����ͷ��" + k);
                count--;
            }
        } else {
            //�Ͽ�����
            System.out.println("ɾ����" + k);
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
            System.out.println("�ҵ���" + k);
        } else {
            System.out.println("û�� " + k + " ����");
        }
        return currentNode;
    }

    //����Ĭ�ϴ�С��������
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

            //�����ڵ�ֵ
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
        System.out.print("������ݣ�");
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
        System.out.println("�������");
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


//˫������
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
        System.out.println("�������ݣ�" + data);
        if (root != null) {
            this.root.addNode(node);
        } else {
            this.root = node;
        }
        this.count++;
    }

    public void delete(int k) {
        //�Ȳ��ҵ����Node
        Node currentNode = root;

        while (currentNode != null && currentNode.getData() != k) {
            currentNode = currentNode.getNext();
        }

        if (currentNode == root) {
            //Ҫɾ����Ԫ���ǵ�һ������������Ϊ��
            if (currentNode == null) {
                System.out.println("����Ϊ��");
            } else {
                //Ҫɾ�����ǵ�һ��
                root = root.getNext();
                System.out.println("ɾ����ͷ��" + k);
                count--;
            }
        } else {
            if (currentNode == null) {
                System.out.println("û�� " + k + " ����");
            } else {
                //�Ͽ�����
                System.out.println("ɾ����" + k);
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
            System.out.println("�ҵ���" + k);
        } else {
            System.out.println("û�� " + k + " ����");
        }
        return currentNode;
    }

    //����Ĭ�ϴ�С��������
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

            //�����ڵ�ֵ
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
        System.out.print("������ݣ�");
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
        System.out.println("�������");
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


