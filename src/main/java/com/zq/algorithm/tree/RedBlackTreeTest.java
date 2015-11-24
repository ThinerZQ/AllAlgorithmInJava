package com.zq.algorithm.tree;

/**
 * Created by zhengshouzi on 2015/11/20.
 */
public class RedBlackTreeTest {
    public static void main(String[] args) {
        int[] a= {3,6,9,100,1,45,23,19,20,10,8,7,110};
        RedBlackTree redBlackTree = new RedBlackTree();
        for (int i=0;i<a.length;i++){
            redBlackTree.insert(a[i]);
        }

        System.out.println();
        System.out.println("");
        redBlackTree.inorderTravral(redBlackTree.getRoot());
        System.out.println();
        redBlackTree.preorderTravral(redBlackTree.getRoot());
        System.out.println();
        redBlackTree.postorderTravral(redBlackTree.getRoot());

        redBlackTree.delete(6);
        System.out.println("ɾ��6֮��");
        System.out.println();
        redBlackTree.inorderTravral(redBlackTree.getRoot());
        System.out.println();
        redBlackTree.preorderTravral(redBlackTree.getRoot());
        System.out.println();
        redBlackTree.postorderTravral(redBlackTree.getRoot());

        redBlackTree.delete(100);

        System.out.println("ɾ��100֮��");
        System.out.println();
        redBlackTree.inorderTravral(redBlackTree.getRoot());
        System.out.println();
        redBlackTree.preorderTravral(redBlackTree.getRoot());
        System.out.println();
        redBlackTree.postorderTravral(redBlackTree.getRoot());

        redBlackTree.delete(20);

        System.out.println("ɾ��20֮��");
        System.out.println();
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
     *
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
     *
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
     *
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
     *
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
        System.out.println("insert��" + z.getData());
    }

    /**
     * ����������޲���������Ҫ�����������Ҫ�޲�
     * ���1��z������ڵ�y�Ǻ�ɫ��
     * ���2��z������ڵ�y�Ǻ�ɫ�ģ���z��һ���Һ���
     * ���3��z������ڵ�y�Ǻ�ɫ�ģ���z��һ������
     *
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

    /**
     * ��һ��Ԫ���ƶ�������һ��Ԫ�ص�λ���ϣ�����ɾ����������
     * ������̶Ͽ���ԭ����deleted�ڵ�͸��׵Ĺ�ϵ����replace��deleted�ĸ������ó��˸��ӹ�ϵ
     * @param root �����ڵ�
     * @param deleted ��ɾ����Ԫ��
     * @param replace �����ƶ�����ɾ��Ԫ��λ���ϵ�Ԫ��
     */
    public void rb_transplant(RedBlackTreeNode root,RedBlackTreeNode deleted,RedBlackTreeNode replace){

        if (deleted.getParent() == NIL){
            //��ɾ���Ľڵ��Ǹ��ڵ㣬��ôֱ����replace�ڵ�����Ϊ���ڵ�
            this.setRoot(replace);
        }else if (deleted == deleted.getParent().getLeft()){
            //��ɾ���Ľڵ��������׵����ӣ���replace����Ϊ�����׵�����
            deleted.getParent().setLeft(replace);
        }else {
            //��ɾ���Ľڵ��������׵��Һ��ӣ���replace����Ϊ�����׵��Һ���
            deleted.getParent().setRight(replace);
        }
        //������ô�����϶���Ҫ��replace�ĸ������ó�deleted�ĸ��׵�
        replace.setParent(deleted.getParent());
    }

    /**
     * �����ṩ��ɾ��ĳһ��Ԫ��֮�ĺ�����
     * @param k ��Ҫɾ����Ԫ��ֵ
     */
    public void delete(int k){
        //�ҵ���Ҫɾ����ֵ��Ӧ�ĺ���
        RedBlackTreeNode node = searchRecursion(this.getRoot(), k);
        //���ú�ڶ�������ɾ������
        rb_delete(this.getRoot(), node);
    }

    /**
     * ��ڶ�������ɾ���������������ֱ��ɾ��ĳһ��Ԫ�أ����ܵ������������
     * @param root ������ĸ��ڵ�
     * @param z ��Ҫɾ���Ľڵ�
     */
    public void rb_delete(RedBlackTreeNode root,RedBlackTreeNode z){
        //ά�ֽڵ� y Ϊ ������  ɾ���Ľڵ�  ���� ��Ҫ   ��ɾ���ڵ㻥��   �Ľڵ㡣
        RedBlackTreeNode y = z;
        //��¼�� y ���ʼ����ɫ
        TreeColor y_original_color = y.getColor();
        //���� x �ڵ�Ϊ ����������x �ڵ�Ϊ y�ڵ���Һ��ӣ���������
        RedBlackTreeNode x = null;

        //z������Ϊ��
        //���z ֻ��һ���Һ��ӽڵ㣬�������ֻ������ z ��ĳһ��Ҷ�ӽڵ����һ��ڵ㣬
        if (z.getLeft() == NIL){
            //���뵽������˵��z���Һ��ӿ��п��ޡ�
            // ��� z ���Һ��ӵĻ�����ô z һ���Ǻ�ɫ�ģ���
            // ��� z û���Һ��ӵĻ�����ô z �����Ǻ�ɫ�Ŀ����Ǻ�ɫ�ģ�һ����Ҷ�ӽڵ㡣

            //��Ϊ���z�Ǻ�ɫ�ڵ㣬���z����Ҷ�ӽڵ�Ļ�����ô��Ȼͬʱ����2 ����ɫҶ�ӽڵ㣨����3��
            // ��� z �Ǻ�ɫ�ڵ㣬��� z ����Ҷ�ӽڵ�Ļ���������ڵ��Һ���Ϊ��ɫ����������   ����Һ���Ϊ��ɫ��Υ������5����Ϊû�����Ӱ�

            //��¼x Ϊ z���Һ��ӽڵ㣬����  z ��û���Һ��ӣ����û���Һ��ӣ�z���� NIL �ڵ�
            x = z.getRight();
            //��  z �� z �� �Һ��ӻ��������� z ���Һ�����ʲô
            rb_transplant(root, z, z.getRight());
        }else if (z.getRight() == NIL){
            //���뵽������˵��z������һ�����еģ�����û���Һ��ӡ�
            //��ʱ��ֻ��һ����������� z �Ǻ�ɫ�ģ����������Ǻ�ɫ�ġ�����
            //��Ϊ��� z �Ǻ�ɫ�ڵ㣬����Ϊz �������ӵģ���ôz ��Ȼ���Һ��ӣ�����Υ������4�� ��������뵽�����
            // ��� z �Ǻ�ɫ�ڵ㣬����Ϊz �������ӵģ���������Ǻ�ɫ�ģ���ô ��Ȼ�����Һ������Ǻ�ɫ�ģ�����Υ������5����

            //��¼x Ϊ z�����ӽڵ㣬z һ���������ӵ�
            x= z.getLeft();
            //�� z��z�����ӻ���
            rb_transplant(root, z, z.getLeft());
        }else {
            //���뵽���˵�� z �������ӣ������Һ���

            //��¼�� z �ĺ�̽ڵ� y ���Ժ�Ὣ y �� z ����
            y= minResursive(z.getRight());
            //��������  y ��ԭʼ��ɫ
            y_original_color=y.getColor();
            // ��¼�� y  ���Һ��ӽڵ㣬x Ҫô��һ��NIL �ڵ����һ����ʵ��Ҷ�ڵ㡣
            // ��Ϊy ��ĳһ����������Сֵ����ô y ��Ȼ����������ߵ�Ҷ�ڵ㣬����Ҷ�ڵ����һ��ڵ㡣
            //��Ϊ ���y Ϊ��ɫ����ôy ��Ȼ��Ҷ�ڵ㣬��Ϊ��� y����Ҷ�ڵ㣬��ô y ��Ȼ���к�ɫ�������ӽڵ㣨����4��,��ôy �Ͳ�����������Сֵ
            //��� y Ϊ��ɫ����ô y ������Ҷ�ڵ㣬Ҳ���� y ��һ���Һ��ӽڵ㣨�����������ӽڵ㣬��Ȼy���ǲ�����Сֵ�ˣ�������Һ��ӽڵ�һ���Ǻ�ɫ����������Ǻ�ɫ����Υ��������5��
            x = y.getRight();
            if (y.getParent() == z){
                //��� y �ĸ����� z��Ҳ����˵��z�ĺ�̾��� z �ĺ��ӽڵ㣬����ʱ�� y ��Ȼû�к��ӽڵ㣬������� x ��NIL�ڵ�
                //��x �ĸ�������Ϊ y�����ƺ�����ʡ��������������
                x.setParent(y);
            }else {
                //y ����z ���Һ��ӣ���ô�Ƚ� y ��y ���Һ��ӻ�����
                rb_transplant(root,y,y.getRight());
                //��y���Һ������ó� z���Һ���
                y.setRight(z.getRight());
                //��y���Һ��Ӻ� y ���Ϲ�ϵ
                y.getRight().setParent(y);
            }
            //����������y �� z ����
            rb_transplant(root,z,y);
            //�� y �� z ��߲������Ϲ�ϵ
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            //�� y ����ɫ����Ϊ z ����ɫ
            y.setColor(z.getColor());
        }

        //��� y ����ɫԭ���Ǻ�ɫ����ô�����ƻ������ʣ���Ҫ�޲�
        //��Ϊ��� y ����ɫ����Ǻ�ɫ����ô �� y �� z ����λ�ã������ƻ���ϵ��
        // ��Ϊ���� y.setColor(z.getColor()); ��y����ɫ����Ϊ����ǰ z����ɫ������������1,2��3��4 ������y ���Ǻ�ɫ������ɫ�Ľڵ�ɾ�������ƻ�����5��
        if (y_original_color == TreeColor.Black){
            //����� x Ҫô��һ�� Ҷ�ڵ㣬Ҫô��һ��NIL�ڵ㡣
            //Ϊʲô��Ҫ��� x �����޲��� ��Ϊ���Կ��� x ����滻�� y��λ�á����ο�x �ĸ�ֵ��䣩��
            //������ռ��y ԭ��λ�õĽڵ� x ��Ϊ����һ�� ����ĺ�ɫ��
            rb_delete_fixup(root,x);
        }

    }

    /**
     * �޲���Ϊɾ���˽ڵ��ƻ��˵ĺ��������
     * @param root ������ĸ�
     * @param x ��Ҫ�޲��Ľڵ�
     */
    public void rb_delete_fixup(RedBlackTreeNode root,RedBlackTreeNode x){

        while (x != root && x.getColor() == TreeColor.Black){
            if (x == x.getParent().getLeft()){
                RedBlackTreeNode w = x.getParent().getRight();
                if (w.getColor() == TreeColor.Red){
                    w.setColor( TreeColor.Black);
                    x.getParent().setColor(TreeColor.Red);
                    left_rotate(x.getParent());
                    w =x.getParent().getRight();
                }
                if (w.getLeft().getColor() == TreeColor.Black && w.getRight().getColor() ==TreeColor.Black){
                    w.setColor(TreeColor.Red);
                    x=x.getParent();
                }else {
                    if (w.getRight().getColor() == TreeColor.Black) {
                        w.getLeft().setColor(TreeColor.Black);
                        w.setColor(TreeColor.Red);
                        right_rotate(w);
                        w = x.getParent().getRight();
                    }
                    w.setColor( x.getParent().getColor());
                    x.getParent().setColor(TreeColor.Black);
                    w.getRight().setColor(TreeColor.Black);
                    left_rotate(x.getParent());
                    x=getRoot();
                }
            }else {
                RedBlackTreeNode w = x.getParent().getLeft();
                if (w.getColor() == TreeColor.Red){
                    w.setColor(TreeColor.Black);
                    x.getParent().setColor(TreeColor.Red);
                    right_rotate(x.getParent());
                    w =x.getParent().getLeft();
                }
                if (w.getRight().getColor() == TreeColor.Black && w.getLeft().getColor() ==TreeColor.Black){
                    w.setColor(TreeColor.Red);
                    x=x.getParent();
                }else {
                    if (w.getLeft().getColor() == TreeColor.Black) {
                        w.getRight().setColor(TreeColor.Black);
                        w.setColor(TreeColor.Red);
                        left_rotate(w);
                        w = x.getParent().getLeft();
                    }
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(TreeColor.Black);
                    w.getLeft().setColor(TreeColor.Black);
                    right_rotate(x.getParent());
                    x=getRoot();
                }
            }
        }
        x.setColor(TreeColor.Black);
    }


    /**
     * �жϺ�����Ƿ���������
     * @param root ����
     * @return
     */
    public boolean isRedBlackTree(RedBlackTreeNode root){

        if (root.getColor() != TreeColor.Black){
            return false;
        }else {
            //���������֤������1,3������ģ�ֻ��Ҫ�ж���������2,4,5�����뵽����˵������2��������������4��5�ͳ���
            return xingzhi4(root) && xingzhi5(root);
        }
    }

    /**
     * �жϺ����������4�Ƿ����
     * @param root ����
     * @return
     */
    public boolean xingzhi4(RedBlackTreeNode root){
        /*
        boolean flag=true;
        RedBlackTreeNode currentNode = root;

        while (currentNode != NIL) {
            if (currentNode.getColor() == TreeColor.Red) {
                if (currentNode.getLeft().getColor() == TreeColor.Black && currentNode.getRight().getColor() == TreeColor.Black) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            currentNode=root.getLeft();
        }
        while (currentNode != NIL) {
            if (currentNode.getColor() == TreeColor.Red) {
                if (currentNode.getLeft().getColor() == TreeColor.Black && currentNode.getRight().getColor() == TreeColor.Black) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            currentNode=root.getRight();
        }
        */
        return false;
    }

    /**
     * �жϺ��������5�Ƿ����
     * @param root
     * @return
     */
    public boolean xingzhi5(RedBlackTreeNode root){
        /*
        if (root==NIL){
            return true;
        }
        if (root.getColor() == TreeColor.Black && root.getLeft()==NIL && root.getRight()==NIL){
            return true;
        }else if (root.getColor() == TreeColor.Black && root.getLeft()==NIL && root.getRight().getColor()==TreeColor.Red){
            return true;
        }else if (root.getColor() == TreeColor.Black && root.getRight()==NIL && root.getLeft().getColor()==TreeColor.Red) {
            return true;
        }else {
            return xingzhi5(root.getRight()) && xingzhi5(root.getLeft());
        }
        */
        return false;
    }


    /**
     * �ݹ�����Сֵ
     *
     * @param root ָ����ڵ�ģ����ã�
     * @return �ҵ�����Сֵ����
     */
    public RedBlackTreeNode minResursive(RedBlackTreeNode root) {

        if (root.getLeft() == NIL) {
            System.out.println("��Сֵ��" + root.getData());
            return root;
        } else {
            return minResursive(root.getLeft());
        }
    }
    /**
     * �ݹ����ĳһ��Ԫ��
     *
     * @param root ָ����ڵ�ģ����ã�
     * @param k    ��Ҫ���ҵ�ֵ
     * @return �����ҵ��Ľڵ㣬Ϊ����û�����ֵ��
     */
    public RedBlackTreeNode searchRecursion(RedBlackTreeNode root, int k) {

        //root ==null ,��ʾû���ҵ���Ԫ�أ�k==root.getData() ��ʾ�ҵ������Ԫ��
        if (root == NIL || k == root.getData()) {
            if (root == NIL) {
                System.out.println("û���ҵ� ��" + k);
            } else if (k == root.getData()) {
                System.out.println("�ҵ���" + k);
            }
            return root;
        }
        //�ݹ���ң������߲��ҿ���
        if (k < root.getData()) {
            return searchRecursion(root.getLeft(), k);
        } else {
            return searchRecursion(root.getRight(), k);
        }
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
