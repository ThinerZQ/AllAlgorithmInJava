package com.zq.algorithm.tree;

/**
 * Created by zhengshouzi on 2015/11/18.
 */
public class RedBlackTreeTest {
    public static void main(String[] args) {

        RedBlackTree redBlackTree = new RedBlackTree();
        redBlackTree.insert(3);
        redBlackTree.insert(6);
        redBlackTree.insert(9);
        redBlackTree.insert(100);
        redBlackTree.insert(1);
        redBlackTree.insert(45);
        redBlackTree.insert(23);
        redBlackTree.insert(19);
        redBlackTree.insert(20);
        redBlackTree.inorderTravral(redBlackTree.getRoot());
        System.out.println();
        redBlackTree.preorderTravral(redBlackTree.getRoot());
        System.out.println();
        redBlackTree.postorderTravral(redBlackTree.getRoot());
    }

}

class RedBlackTree {

    //��ʼʱ����ڵ����ڱ��ڵ�
    private RedBlackTreeNode root = NIL;
    private int count;
    //�ڱ��ڵ㣨��ڵ㣬ֻ������־λ�ã�û��ʵ�����壩���Ǿ�̬����
    private static final RedBlackTreeNode NIL = new RedBlackTreeNode();

    //��ʼ���ڱ��ڵ�
    static {
        NIL.setColor(TreeColor.Black);
        NIL.setData(-1);
        NIL.setParent(null);
        NIL.setLeft(null);
        NIL.setRight(null);
    }

    /**
     * ������������������������˳���
     *
     * @param root ���ĸ��ڵ�
     */
    public void inorderTravral(RedBlackTreeNode root) {

        if (root == this.root) {
            System.out.print("�������: ");
        }
        if (root != NIL) {
            inorderTravral(root.getLeft());
            System.out.print(root.getData() + "-" + root.getColor().toString() + "  ");
            inorderTravral(root.getRight());
        }
    }
    /**
     * ǰ����������������������˳���
     *
     * @param root ���ĸ��ڵ�
     */
    public void preorderTravral(RedBlackTreeNode root) {

        if (root == this.root) {
            System.out.print("ǰ�����: ");
        }
        if (root != NIL) {
            System.out.print(root.getData() + "-" + root.getColor().toString() + "  ");
            preorderTravral(root.getLeft());
            preorderTravral(root.getRight());
        }
    }
    /**
     * ������������������������˳���
     *
     * @param root ���ĸ��ڵ�
     */
    public void postorderTravral(RedBlackTreeNode root) {

        if (root == this.root) {
            System.out.print("�������: ");
        }
        if (root != NIL) {

            postorderTravral(root.getLeft());
            postorderTravral(root.getRight());
            System.out.print(root.getData() + "-" + root.getColor().toString() + "  ");
        }
    }

    /**
     * ��x�ڵ�������ת��������ı�ڵ�ֵ�����ݴ�С��ϵ��
     * �����������ܼ򵥣�����Ҫ������ɫ�ı仯��
     * ֻ�ǽ��ڵ�Ͽ�����ת�������ϡ�֪���Ͽ������ӵ��Ⱥ�˳��ͱȽϺ�����ˡ�
     * @param x ��Ҫ��ת�Ľڵ�
     */
    public void left_rotate(RedBlackTreeNode x) {
        //��¼x���Һ��ӽڵ�
        RedBlackTreeNode y = x.getRight();
        //����x���Һ���Ϊy������
        x.setRight(y.getLeft());
        //���y�����Ӳ�Ϊ�ڱ��ڵ㣬����y���ӵĸ���Ϊx
        if (y.getLeft() != NIL) {
            y.getLeft().setParent(x);
        }
        //����y�ĸ���Ϊx�ĸ���
        y.setParent(x.getParent());

        if (x.getParent() == NIL) {
            //���x�ĸ���Ϊ�ڱ��ڵ㣬����x�Ǹ��ڵ㣬��ô��y����Ϊ���ڵ�ͺ���
            this.setRoot(y);
        } else if (x == x.getParent().getLeft()) {
            //���x��x���׵����ӣ���ô����x���׵�����Ϊy
            x.getParent().setLeft(y);
        } else {
            //���x��x���׵��Һ��ӣ���ô����x���׵��Һ���Ϊy
            x.getParent().setRight(y);
        }
        //����y������Ϊx
        y.setLeft(x);
        //����x�ĸ���Ϊy
        x.setParent(y);
    }
    /**
     * ��x�ڵ�������ת��������ı�ڵ�ֵ�����ݴ�С��ϵ��
     * �����������ܼ򵥣�����Ҫ������ɫ�ı仯��
     * ֻ�ǽ��ڵ�Ͽ�����ת�������ϡ�֪���Ͽ������ӵ��Ⱥ�˳��ͱȽϺ�����ˡ�
     * @param x ��Ҫ��ת�Ľڵ�
     */
    public void right_rotate(RedBlackTreeNode x) {
        //��¼x�����ӽڵ�
        RedBlackTreeNode y = x.getLeft();
        //����x������Ϊy���Һ���
        x.setLeft(y.getRight());
        //���y���Һ��Ӳ�Ϊ�ڱ��ڵ㣬����y�Һ��ӵĸ���Ϊx
        if (y.getRight() != NIL) {
            y.getRight().setParent(x);
        }
        //����y�ĸ���Ϊx�ĸ���
        y.setParent(x.getParent());

        if (x.getParent() == NIL) {
            //���x�ĸ���Ϊ�ڱ��ڵ㣬����x�Ǹ��ڵ㣬��ô��y����Ϊ���ڵ�ͺ���
            this.setRoot(y);
        } else if (x == x.getParent().getLeft()) {
            //���x��x���׵����ӣ���ô����x���׵�����Ϊy
            x.getParent().setLeft(y);
        } else {
            //���x��x���׵��Һ��ӣ���ô����x���׵��Һ���Ϊy
            x.getParent().setRight(y);
        }
        //����y���Һ���Ϊx
        y.setRight(x);
        //����x�ĸ���Ϊy
        x.setParent(y);
    }

    /**
     * ���뺯������������һ������ڵ㡣
     * @param k ���������ֵ
     */
    public void insert(int k) {
        RedBlackTreeNode node = new RedBlackTreeNode();
        node.setData(k);
        node.setColor(TreeColor.Red);
        //����Ҫ����Ľڵ������Ԫ�ض����ó��ڱ��ڵ�
        node.setParent(NIL);
        node.setLeft(NIL);
        node.setRight(NIL);
        //���ú�ڶ������Ĳ��뷽��
        rb_insert(node);
    }

    /**
     * ���ں�ڶ������Ĳ��뷽��
     * @param z ����Ľڵ����
     */
    public void rb_insert(RedBlackTreeNode z) {
        //y�Ǵ�����Ľڵ�λ�ã�Ĭ��Ҳ���ڱ��ڵ�
        RedBlackTreeNode y = this.NIL;
        //x�ǣ����ڵ㣬���ϱȽ�����ֵ��ֱ��ֱ���������λ��
        RedBlackTreeNode x = this.getRoot();
            while (x != this.NIL) {
                y = x;
                if (z.getData() < x.getData()) {
                    x = x.getLeft();
                } else {
                    x = x.getRight();
                }
            }
        //��z���뵽y����
        z.setParent(y);
        if (y == NIL) {
            //���z�ǵ�һ�������ֵ,��z����Ϊ���ڵ�
            this.setRoot(z);
        } else if (z.getData() < y.getData()) {
            //��z���뵽y�����
            y.setLeft(z);
        } else {
            //z���뵽y���ұ�
            y.setRight(z);
        }
        //����ڵ�����ƻ��˺�������ʣ����Ե����޲�����
        rb_insert_fixup(z);
        count++;
        System.out.println("insert��"+z.getData());
    }

    /**
     *  ����������޲���������Ҫ�����������Ҫ�޲�
     *  ���1��z������ڵ�y�Ǻ�ɫ��
     *  ���2��z������ڵ�y�Ǻ�ɫ�ģ���z��һ���Һ���
     *  ���3��z������ڵ�y�Ǻ�ɫ�ģ���z��һ������
     * @param z ���������ʱ仯�Ľڵ�z
     */
    public void rb_insert_fixup(RedBlackTreeNode z) {
        //whileѭ����˵z �ĸ��׵���ɫ����Ǻ�ɫ������Ҫ�����޲���
        // ��һ�β���Ԫ�ص�ʱ��z�ĸ������ڱ��ڵ㣬���Բ����޲���ֱ��ִ��while��������ø��ڵ�Ϊ��ɫ�����
        // ֮��ÿ�β��붼��Ҫ�ж�z�ڵ�ĸ��׽ڵ����ɫ�����жϣ���Ϊ�����z�ڵ��ʼ����Ϊ��ɫ�ģ����z�ĸ��׽ڵ�Ϊ��ɫ���Ͳ����޲��ˣ����Ϊ��ɫ����ôz�ĸ��ڵ��z���Ǻ�ɫ����ô����Ҫ�����޲�
        while (z.getParent().getColor() == TreeColor.Red) {
            //���z�ĸ��� ��z��үү�����ӣ����ﵱz�ǲ�����ǵڶ��ڵ��ʱ��z�ĸ����Ǹ��ڵ㣬z�ĸ��׵ĸ�����һ���ڱ��ڵ㣬��������else����
            if (z.getParent() == z.getParent().getParent().getLeft()) {
                //�õ�z�����壬������z��үү���Һ��ӣ����е����嶼�п������ڱ��ڵ㣬������ڱ��ڵ�Ļ�����ô�������ɫ���Ǻ�ɫ
                RedBlackTreeNode y = z.getParent().getParent().getRight();
                //�ж�z���������ɫ����������Ǻ�ɫ����ôz�ĸ���һ���Ǻ�ɫ������Ϊ�ղ����z�Ǻ�ɫ������Υ�������ʣ�
                if (y.getColor() == TreeColor.Red) {
                    //���1��z������ڵ�y�Ǻ�ɫ��
                    //�޸ĸ��׵���ɫΪ��ɫ
                    z.getParent().setColor(TreeColor.Black);
                    //�������ɫҲ����Ϊ��ɫ
                    y.setColor(TreeColor.Black);
                    //z��үү����ɫ����Ϊ��ɫ��������z��үүΪ���������ϱ���������
                    z.getParent().getParent().setColor(TreeColor.Red);
                    //��ʼ��Ҷ�ӽڵ������ߣ�����z ��үү��ôһ�������ʱ��ƻ��ˣ������ٴ�ѭ����ȥ��z��үү�ĸ��׵���ɫ��ʲô
                    z = z.getParent().getParent();
                } else {
                    //����z���������ɫ���Ǻ�ɫ����ʱz�ĸ��׵���ɫ��ȷ����

                    //���z��z�ĸ��׵��Һ���
                    if (z == z.getParent().getRight()) {
                        //���2��z������ڵ�y�Ǻ�ɫ�ģ���z��һ���Һ��ӣ���z�ĸ�����������������
                        z = z.getParent();
                        left_rotate(z);
                    }
                    //���3��z������ڵ�y�Ǻ�ɫ�ģ���z��һ������

                    //��z�ĸ��׵���ɫ����Ϊ��ɫ
                    z.getParent().setColor(TreeColor.Black);
                    //��z��үү����ɫ����Ϊ��ɫ
                    z.getParent().getParent().setColor(TreeColor.Red);
                    //��z��үү����
                    right_rotate(z.getParent().getParent());

                    //���2����������3 ������Ϊ���2��û�ж���ɫ�����޸ģ�ֻ�ǵ����˴�С��ϵ��ͨ�����3����ɫ���޸ģ�����ת���������ܴﵽƽ��


                }
            } else {
                //����������
                //���z�ĸ��� ��z��үү���Һ���
                //�õ�z�����壬������zүү������
                RedBlackTreeNode y = z.getParent().getParent().getLeft();
                //�ж�z���������ɫ����������Ǻ�ɫ����ôz�ĸ���һ���Ǻ�ɫ������Ϊ�ղ����z�Ǻ�ɫ������Υ�������ʣ�
                if (y.getColor() == TreeColor.Red) {
                    //���1��z������ڵ�y�Ǻ�ɫ��
                    //�޸ĸ��׵���ɫΪ��ɫ
                    z.getParent().setColor(TreeColor.Black);
                    //�������ɫҲ����Ϊ��ɫ
                    y.setColor(TreeColor.Black);
                    //z��үү����ɫ����Ϊ��ɫ��������z��үүΪ���������ϱ���������
                    z.getParent().getParent().setColor(TreeColor.Red);
                    //��ʼ��Ҷ�ӽڵ������ߣ�����z ��үү��ôһ�������ʱ��ƻ��ˣ������ٴ�ѭ����ȥ��z��үү�ĸ��׵���ɫ��ʲô
                    z = z.getParent().getParent();
                } else {
                    //����z���������ɫ���Ǻ�ɫ����ʱz�ĸ��׵���ɫ��ȷ����

                    //���z��z�ĸ��׵��Һ���
                    if (z == z.getParent().getLeft()) {
                        //���2��z������ڵ�y�Ǻ�ɫ�ģ���z��һ�����ӣ���z�ĸ�����������������
                        z = z.getParent();
                        right_rotate(z);
                    }
                    //���3��z������ڵ�y�Ǻ�ɫ�ģ���z��һ���Һ���
                    z.getParent().setColor(TreeColor.Black);
                    //��z��үү����ɫ����Ϊ��ɫ
                    z.getParent().getParent().setColor(TreeColor.Red);
                    //��z��үү����
                    left_rotate(z.getParent().getParent());
                }
            }
        }
        //������ô���������ڵ�����Ϊ��ɫ
        this.getRoot().setColor(TreeColor.Black);
    }


    public RedBlackTreeNode getRoot() {
        return root;
    }

    public void setRoot(RedBlackTreeNode root) {
        this.root = root;
    }
}

/**
 * ������ڵ���
 */
class RedBlackTreeNode {
    //����
    private RedBlackTreeNode parent;
    private RedBlackTreeNode left;
    private RedBlackTreeNode right;
    private int data;
    private TreeColor color;

    public RedBlackTreeNode() {
    }

    public RedBlackTreeNode(int data) {
        this.data = data;
    }

    public RedBlackTreeNode getParent() {
        return parent;
    }

    public void setParent(RedBlackTreeNode parent) {
        this.parent = parent;
    }

    public RedBlackTreeNode getLeft() {
        return left;
    }

    public void setLeft(RedBlackTreeNode left) {
        this.left = left;
    }

    public RedBlackTreeNode getRight() {
        return right;
    }

    public void setRight(RedBlackTreeNode right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeColor getColor() {
        return color;
    }

    public void setColor(TreeColor color) {
        this.color = color;
    }
}

/**
 * ������ɫö����
 */
enum TreeColor {
    Red("red"), Black("black");
    TreeColor(String string) {
    }
}