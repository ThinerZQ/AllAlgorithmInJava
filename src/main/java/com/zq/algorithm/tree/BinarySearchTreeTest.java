package com.zq.algorithm.tree;

import java.util.Random;

/**
 * Created by zhengshouzi on 2015/11/14.
 */
public class BinarySearchTreeTest {

    public static void main(String[] args) {

        int[] a = {3, 6, 7, 19, 54, 23, 41, 66, 10, 30};
        System.out.println("------------------���ɶ�����------------------------------");
        //����������
        BinarySearchTree binarySearchTree = new BinarySearchTree(a);

        System.out.println("--------------------����ڵ�----------------------------");
        //����һ������
        binarySearchTree.insert(15);

        System.out.println("--------------------�������----------------------------");
        //�������
        binarySearchTree.inorderTravral(binarySearchTree.getRoot());
        System.out.println();
        //ǰ�����
        binarySearchTree.preorderTravral(binarySearchTree.getRoot());
        System.out.println();
        //�������
        binarySearchTree.postorderTravral(binarySearchTree.getRoot());
        System.out.println();


        System.out.println("--------------------���ҽڵ�----------------------------");
        //���ҽڵ�
        binarySearchTree.searchRecursion(binarySearchTree.getRoot(), 7);
        binarySearchTree.searchRecursion(binarySearchTree.getRoot(), 66);
        binarySearchTree.searchRecursion(binarySearchTree.getRoot(), 100);


        System.out.println("---------------------����ǰ���ڵ�---------------------------");
        //���ǰ���ڵ�
        binarySearchTree.predecessor(41);
        binarySearchTree.predecessor(3);
        binarySearchTree.predecessor(100);


        System.out.println("----------------------���Һ�̽ڵ�--------------------------");
        //��ú�̽ڵ�
        binarySearchTree.succeror(41);
        binarySearchTree.succeror(66);
        binarySearchTree.succeror(100);


        System.out.println("-----------------------���ֵ-------------------------");
        //���ֵ
        binarySearchTree.maxResursive(binarySearchTree.getRoot());
        System.out.println("------------------------��Сֵ------------------------");
        //��Сֵ
        binarySearchTree.minResursive(binarySearchTree.getRoot());


        System.out.println("------------------ɾ���ڵ�------------------------------");
        //ɾ���ڵ�
        binarySearchTree.delete(binarySearchTree.getRoot(), 15);


        System.out.println("----------------------�����ڵ�--------------------------");
        //�������
        binarySearchTree.inorderTravral(binarySearchTree.getRoot());
        System.out.println();
        //ǰ�����
        binarySearchTree.preorderTravral(binarySearchTree.getRoot());
        System.out.println();
        //�������
        binarySearchTree.postorderTravral(binarySearchTree.getRoot());
        System.out.println();


        System.out.println("------------------------------------------------");

    }

}

interface IBinarySearchTree {
    /**
     * ������������������������˳���
     *
     * @param root ���ĸ��ڵ�
     */
    void inorderTravral(BinarySearchTreeNode root);

    /**
     * ǰ����������������������˳���
     *
     * @param root ���ĸ��ڵ�
     */
    void preorderTravral(BinarySearchTreeNode root);

    /**
     * ������������������������˳���
     *
     * @param root ���ĸ��ڵ�
     */
    void postorderTravral(BinarySearchTreeNode root);

    /**
     * ����Ԫ�أ�k����Ҫ����Ĺؼ��֣�
     *
     * @param k
     */
    void insert(int k);

    /**
     * ɾ��Ԫ�أ����������
     * 1����Ҫɾ����Ԫ����Ҷ�ڵ�
     * 2����Ҫɾ����Ԫ����һ�����ӽڵ�
     * 3����Ҫɾ����Ԫ�����������ӽڵ�
     * ������������ֱ��Ӧ�˷����е�����if���
     *
     * @param root ���ڵ�
     * @param k    ��Ҫɾ����Ԫ��
     */
    void delete(BinarySearchTreeNode root, int k);

    /**
     * ��������Ԫ�أ�����ɾ����������
     *
     * @param root                        ���ڵ�
     * @param deleteBinarySearchTreeNode  ��Ҫɾ���Ľڵ�
     * @param replaceBinarySearchTreeNode �滻�Ľڵ�
     */
    void transplant(BinarySearchTreeNode root, BinarySearchTreeNode deleteBinarySearchTreeNode, BinarySearchTreeNode replaceBinarySearchTreeNode);

    /**
     * �ݹ����ĳһ��Ԫ��
     *
     * @param root ָ����ڵ�ģ����ã�
     * @param k    ��Ҫ���ҵ�ֵ
     * @return �����ҵ��Ľڵ㣬Ϊ����û�����ֵ��
     */
    BinarySearchTreeNode searchRecursion(BinarySearchTreeNode root, int k);

    /**
     * ѭ������ĳһ��Ԫ��
     *
     * @param root ָ����ڵ�ģ����ã�
     * @param k    ��Ҫ���ҵ�ֵ
     * @return �����ҵ��Ľڵ㣬Ϊ����û�����ֵ��
     */
    BinarySearchTreeNode searchCirculation(BinarySearchTreeNode root, int k);

    /**
     * �ݹ�����Сֵ
     *
     * @param root ָ����ڵ�ģ����ã�
     * @return �ҵ�����Сֵ����
     */
    BinarySearchTreeNode minResursive(BinarySearchTreeNode root);

    /**
     * ѭ������Сֵ
     *
     * @param root ָ����ڵ�ģ����ã�
     * @return �ҵ�����Сֵ����
     */
    BinarySearchTreeNode minCirculation(BinarySearchTreeNode root);

    /**
     * �ݹ������ֵ
     *
     * @param root ָ����ڵ�ģ����ã�
     * @return �ҵ�����Сֵ����
     */
    BinarySearchTreeNode maxResursive(BinarySearchTreeNode root);

    /**
     * ѭ�������ֵ
     *
     * @param root ָ����ڵ�ģ����ã�
     * @return �ҵ�����Сֵ����
     */
    BinarySearchTreeNode maxCirculation(BinarySearchTreeNode root);

    /**
     * ����ǰ�������ؼ��ֲ��ң����ҵ�����ؼ��֣��õ�����ؼ��ֶ�Ӧ�Ľڵ����Ȼ���ٷ���
     * ǰ����Ϊ���������
     * 1������ڵ�BinarySearchTreeNode ���������ǿգ���ôBinarySearchTreeNode��ǰ��ǡ����BinarySearchTreeNode�������е����ڵ�
     * 2������ڵ�BinarySearchTreeNode��������Ϊ�գ���ô��̾���BinarySearchTreeNode����ײ����ȣ����Һ�̵�����Ҳ��BinarySearchTreeNode�����ȡ�
     *
     * @param k �ؼ��֣��������ʹ��BinarySearchTreeNode�ڵ���Ϊ��Ҫ���ҵĽڵ㣬��Ϊ����ڵ��������ͬ��keyֵ
     * @return ���ҵ���ǰ���ڵ�
     */
    BinarySearchTreeNode predecessor(int k);

    /**
     * ���Һ�̣����ؼ��ֲ��ң����ҵ�����ؼ��֣��õ�����ؼ��ֶ�Ӧ�Ľڵ����Ȼ���ٷ���
     * ��̷�Ϊ���������
     * 1������ڵ�BinarySearchTreeNode ���������ǿգ���ôBinarySearchTreeNode�ĺ��ǡ����BinarySearchTreeNode�������е���С�ڵ�
     * 2������ڵ�BinarySearchTreeNode��������Ϊ�գ���ô��̾���BinarySearchTreeNode����ײ����ȣ����Һ�̵�����Ҳ��BinarySearchTreeNode�����ȡ�
     *
     * @param k �ؼ���
     * @return ���ҵ��ĺ�̽ڵ�
     */
    BinarySearchTreeNode succeror(int k);
}

/**
 * �����������
 */
class BinarySearchTree implements IBinarySearchTree {

    //���ĸ��ڵ�
    private BinarySearchTreeNode root;
    //�ڵ����
    private int count = 0;

    /**
     * ���ݴ���������һ��������й���һ��������
     *
     * @param a           һ����
     * @param randomValue ���һ��ֵ����Ϊ���ֵ���
     */
    public BinarySearchTree(int[] a, int randomValue) {
        System.out.println("�����������������");
        Random random = new Random(randomValue);
        int value = random.nextInt(a.length);
        //�õ�a�����һ���������
        for (int i = value; i < a.length + value; i++) {
            if (i >= a.length) {
                insert(a[i - a.length]);
            } else {
                insert(a[i]);
            }
        }
    }

    /**
     * �������һ��Ԫ�ؿ�ʼ����
     *
     * @param a
     */
    public BinarySearchTree(int[] a) {
        System.out.println("�������һ��Ԫ�ؿ�ʼ��������������");
        for (int i = 0; i < a.length; i++) {
            insert(a[i]);
        }
    }

    /**
     * ������������������������˳���
     *
     * @param root ���ĸ��ڵ�
     */
    public void inorderTravral(BinarySearchTreeNode root) {

        if (root == this.root) {
            System.out.print("�������: ");
        }
        if (root != null) {

            inorderTravral(root.getLchild());
            System.out.print(root.getData() + "  ");
            inorderTravral(root.getRchild());
        }
    }

    /**
     * ǰ����������������������˳���
     *
     * @param root ���ĸ��ڵ�
     */
    public void preorderTravral(BinarySearchTreeNode root) {
        if (root == this.root) {
            System.out.print("ǰ�����: ");
        }

        if (root != null) {

            System.out.print(root.getData() + "  ");
            preorderTravral(root.getLchild());
            preorderTravral(root.getRchild());
        }
    }

    /**
     * ������������������������˳���
     *
     * @param root ���ĸ��ڵ�
     */
    public void postorderTravral(BinarySearchTreeNode root) {
        if (root == this.root) {
            System.out.print("�������: ");
        }

        if (root != null) {

            postorderTravral(root.getLchild());
            postorderTravral(root.getRchild());
            System.out.print(root.getData() + "  ");
        }
    }

    /**
     * ����Ԫ�أ�k����Ҫ����Ĺؼ��֣�
     *
     * @param k
     */
    public void insert(int k) {

        BinarySearchTreeNode BinarySearchTreeNode = new BinarySearchTreeNode(k);
        if (root != null) {
            //ÿ�ν���Ҫ�����Ԫ�ؽ�����Ӧ�Ľڵ����ȥ������ӹ�ϵ
            this.root.addBinarySearchTreeNode(BinarySearchTreeNode);
        } else {
            //��һ������Ԫ�أ��������ڵ㴦��
            BinarySearchTreeNode.setParent(null);
            BinarySearchTreeNode.setLchild(null);
            BinarySearchTreeNode.setRchild(null);
            this.root = BinarySearchTreeNode;
        }
        this.count++;
    }

    /**
     * ɾ��Ԫ�أ����������
     * 1����Ҫɾ����Ԫ����Ҷ�ڵ�
     * 2����Ҫɾ����Ԫ����һ�����ӽڵ�
     * 3����Ҫɾ����Ԫ�����������ӽڵ�
     * ������������ֱ��Ӧ�˷����е�����if���
     *
     * @param root ���ڵ�
     * @param k    ��Ҫɾ����Ԫ��
     */
    public void delete(BinarySearchTreeNode root, int k) {

        BinarySearchTreeNode BinarySearchTreeNode = searchRecursion(this.root, k);
        System.out.println("ɾ���ڵ㣺" + k);
        if (BinarySearchTreeNode == null) {
            System.out.println("û������ڵ�: " + k + " ,����Ҫɾ��");
            return;
        }
        //�����Һ��Ӷ��ǿյģ�Ҳ����˵��Ҫɾ������Ҷ�ڵ㣬��ôֱ��ɾ����ֻ�޸ĸýڵ�ĸ��ڵ�ͺ���
        if (BinarySearchTreeNode.getLchild() == null && BinarySearchTreeNode.getRchild() == null) {
            //��null���滻BinarySearchTreeNode
            transplant(root, BinarySearchTreeNode, null);
        } else if (BinarySearchTreeNode.getRchild() == null || BinarySearchTreeNode.getLchild() == null) {
            if (BinarySearchTreeNode.getRchild() == null) {
                //�Һ���Ϊ�գ�ֱ���������滻����ڵ�ͺ���
                //��BinarySearchTreeNode���������滻BinarySearchTreeNode
                transplant(root, BinarySearchTreeNode, BinarySearchTreeNode.getLchild());
            } else {
                //����Ϊ�գ����Һ������滻����ڵ�ͺ���

                //�õ�BinarySearchTreeNode�ڵ�ĺ��ֵ��һ���������Һ���Ϊ���������ϡ�
                BinarySearchTreeNode tempSuccessor = minResursive(BinarySearchTreeNode.getRchild());

                //���BinarySearchTreeNode��̵Ĳ���BinarySearchTreeNode���Һ��ӣ������������ϵĽڵ㣬��ô����
                if (tempSuccessor.getParent() != BinarySearchTreeNode) {
                    //��BinarySearchTreeNode�ĺ��Ԫ�غͺ��Ԫ�ص��Һ��ӻ���
                    transplant(root, tempSuccessor, tempSuccessor.getRchild());
                    //���Ԫ�������Һ��ӣ�ΪBinarySearchTreeNode���Һ���
                    tempSuccessor.setRchild(BinarySearchTreeNode.getRchild());
                    //���Ԫ���Һ�����Ӹ��׹�ϵ
                    tempSuccessor.getRchild().setParent(tempSuccessor);
                }
                //ͨ������if����ĸı䣬�Ѿ���tempSuccessor ��������BinarySearchTreeNode�������Ķ��㣬���ǻ�û����BinarySearchTreeNode��tempsuccessor�м佨����ϵ������Ĵ�������֮�佨����ϵ��ͬʱ�������Ĵ��벻ִ�У���ô��ʾ
                //�����BinarySearchTreeNode���Һ��ӣ���ôֱ�Ӱ������̺�BinarySearchTreeNode�滻�����ˡ�
                transplant(root, BinarySearchTreeNode, tempSuccessor);
            }

        } else {
            //��������˵��ֻ�����Ӳ�Ϊ�գ��Һ��Ӳ�Ϊ�ա�
            //�õ�BinarySearchTreeNode�ڵ�ĺ��ֵ��һ���������Һ���Ϊ���������ϡ�
            BinarySearchTreeNode tempSuccessor = minResursive(BinarySearchTreeNode.getRchild());

            //���BinarySearchTreeNode��̵Ĳ���BinarySearchTreeNode���Һ��ӣ������������ϵĽڵ㣬��ô����
            if (tempSuccessor.getParent() != BinarySearchTreeNode) {
                //��BinarySearchTreeNode�ĺ��Ԫ�غͺ��Ԫ�ص��Һ��ӻ���
                transplant(root, tempSuccessor, tempSuccessor.getRchild());
                //���Ԫ�������Һ��ӣ�ΪBinarySearchTreeNode���Һ���
                tempSuccessor.setRchild(BinarySearchTreeNode.getRchild());
                //���Ԫ���Һ�����Ӹ��׹�ϵ
                tempSuccessor.getRchild().setParent(tempSuccessor);
            }
            //ͨ������if����ĸı䣬�Ѿ���tempSuccessor ��������BinarySearchTreeNode�������Ķ��㣬���ǻ�û����BinarySearchTreeNode��tempsuccessor�м佨����ϵ������Ĵ�������֮�佨����ϵ��ͬʱ�������Ĵ��벻ִ�У���ô��ʾ
            //�����BinarySearchTreeNode���Һ��ӣ���ôֱ�Ӱ������̺�BinarySearchTreeNode�滻�����ˡ�
            transplant(root, BinarySearchTreeNode, tempSuccessor);
            //��ԭ��BinarySearchTreeNode�����ӣ���Ӹ����������
            tempSuccessor.setLchild(BinarySearchTreeNode.getLchild());
            //��������Ӹ��׽ڵ�
            tempSuccessor.getLchild().setParent(tempSuccessor);
        }
    }

    /**
     * ��������Ԫ�أ�����ɾ����������
     *
     * @param root                        ���ڵ�
     * @param deleteBinarySearchTreeNode  ��Ҫɾ���Ľڵ�
     * @param replaceBinarySearchTreeNode �滻�Ľڵ�
     */
    public void transplant(BinarySearchTreeNode root, BinarySearchTreeNode deleteBinarySearchTreeNode, BinarySearchTreeNode replaceBinarySearchTreeNode) {

        if (deleteBinarySearchTreeNode.getParent() == null) {
            //��ɾ�����Ǹ��ڵ�
            this.setRoot(replaceBinarySearchTreeNode);
        } else if (deleteBinarySearchTreeNode == deleteBinarySearchTreeNode.getParent().getLchild()) {
            //����ɾ���Ľڵ�ĸ��׵���������Ϊ�滻�ڵ�
            deleteBinarySearchTreeNode.getParent().setLchild(replaceBinarySearchTreeNode);
        } else if (deleteBinarySearchTreeNode == deleteBinarySearchTreeNode.getParent().getRchild()) {
            //����ɾ���Ľڵ�ĸ��׵��Һ�������Ϊ�滻�ڵ�
            deleteBinarySearchTreeNode.getParent().setRchild(replaceBinarySearchTreeNode);
        }
        //����滻�ڵ㲻Ϊ�գ���ô���滻�ڵ�ĸ�������Ϊɾ���ڵ�ĸ���
        if (replaceBinarySearchTreeNode != null) {
            replaceBinarySearchTreeNode.setParent(deleteBinarySearchTreeNode.getParent());
        }
    }

    /**
     * �ݹ����ĳһ��Ԫ��
     *
     * @param root ָ����ڵ�ģ����ã�
     * @param k    ��Ҫ���ҵ�ֵ
     * @return �����ҵ��Ľڵ㣬Ϊ����û�����ֵ��
     */
    public BinarySearchTreeNode searchRecursion(BinarySearchTreeNode root, int k) {

        //root ==null ,��ʾû���ҵ���Ԫ�أ�k==root.getData() ��ʾ�ҵ������Ԫ��
        if (root == null || k == root.getData()) {
            if (root == null) {
                System.out.println("û���ҵ� ��" + k);
            } else if (k == root.getData()) {
                System.out.println("�ҵ���" + k);
            }
            return root;
        }
        //�ݹ���ң������߲��ҿ���
        if (k < root.getData()) {
            return searchRecursion(root.getLchild(), k);
        } else {
            return searchRecursion(root.getRchild(), k);
        }
    }

    /**
     * ѭ������ĳһ��Ԫ��
     *
     * @param root ָ����ڵ�ģ����ã�
     * @param k    ��Ҫ���ҵ�ֵ
     * @return �����ҵ��Ľڵ㣬Ϊ����û�����ֵ��
     */
    public BinarySearchTreeNode searchCirculation(BinarySearchTreeNode root, int k) {

        while (root != null && k != root.getData()) {
            if (k < root.getData()) {
                root = root.getLchild();
            } else {
                root = root.getRchild();
            }
        }
        if (root == null) {
            System.out.println("û���ҵ� ��" + k);
        } else if (k == root.getData()) {
            System.out.println("�ҵ���" + k);
        }
        return root;
    }

    /**
     * �ݹ�����Сֵ
     *
     * @param root ָ����ڵ�ģ����ã�
     * @return �ҵ�����Сֵ����
     */
    public BinarySearchTreeNode minResursive(BinarySearchTreeNode root) {

        if (root.getLchild() == null) {
            System.out.println("��Сֵ��" + root.getData());
            return root;
        } else {
            return minResursive(root.getLchild());
        }
    }

    /**
     * ѭ������Сֵ
     *
     * @param root ָ����ڵ�ģ����ã�
     * @return �ҵ�����Сֵ����
     */
    public BinarySearchTreeNode minCirculation(BinarySearchTreeNode root) {

        while (root.getLchild() != null) {
            root = root.getLchild();
        }
        System.out.println("��Сֵ��" + root.getData());
        return root;
    }

    /**
     * �ݹ������ֵ
     *
     * @param root ָ����ڵ�ģ����ã�
     * @return �ҵ�����Сֵ����
     */
    public BinarySearchTreeNode maxResursive(BinarySearchTreeNode root) {

        if (root.getRchild() == null) {
            System.out.println("���ֵ��" + root.getData());
            return root;
        } else {
            return maxResursive(root.getRchild());
        }
    }

    /**
     * ѭ�������ֵ
     *
     * @param root ָ����ڵ�ģ����ã�
     * @return �ҵ�����Сֵ����
     */
    public BinarySearchTreeNode maxCirculation(BinarySearchTreeNode root) {

        while (root.getRchild() != null) {
            root = root.getRchild();
        }
        System.out.println("���ֵ��" + root.getData());
        return root;
    }

    /**
     * ����ǰ�������ؼ��ֲ��ң����ҵ�����ؼ��֣��õ�����ؼ��ֶ�Ӧ�Ľڵ����Ȼ���ٷ���
     * ǰ����Ϊ���������
     * 1������ڵ�BinarySearchTreeNode ���������ǿգ���ôBinarySearchTreeNode��ǰ��ǡ����BinarySearchTreeNode�������е����ڵ�
     * 2������ڵ�BinarySearchTreeNode��������Ϊ�գ���ô��̾���BinarySearchTreeNode����ײ����ȣ����Һ�̵�����Ҳ��BinarySearchTreeNode�����ȡ�
     *
     * @param k �ؼ��֣��������ʹ��BinarySearchTreeNode�ڵ���Ϊ��Ҫ���ҵĽڵ㣬��Ϊ����ڵ��������ͬ��keyֵ
     * @return ���ҵ���ǰ���ڵ�
     */

    public BinarySearchTreeNode predecessor(int k) {
        BinarySearchTreeNode BinarySearchTreeNode = searchRecursion(this.root, k);
        if (BinarySearchTreeNode == null) {
            System.out.println("û�� " + k + " ���Ԫ��");
            return null;
        }
        if (minResursive(this.root) == BinarySearchTreeNode) {
            System.out.println(k + " ����С��Ԫ�أ�û��ǰ��");
        }
        //��Ե�һ�����
        if (BinarySearchTreeNode.getLchild() != null) {
            BinarySearchTreeNode preBinarySearchTreeNode = minCirculation(BinarySearchTreeNode.getLchild());
            System.out.println(k + " ��ǰ���ǣ�" + preBinarySearchTreeNode.getData());
            return preBinarySearchTreeNode;
        }
        //��ʱtemp�ڵ�
        BinarySearchTreeNode temp = BinarySearchTreeNode.getParent();
        //���ϲ���BinarySearchTreeNode�ڵ��ǰ��ֵ,ֱ����Ҫ���ҵĽڵ�ĸ��ڵ㣬����temp�ڵ������
        while (temp != null && BinarySearchTreeNode == temp.getLchild()) {
            BinarySearchTreeNode = temp;
            temp = temp.getParent();
        }
        if (temp != null) {
            System.out.println(k + " ��ǰ���ǣ�" + temp.getData());
        }

        return temp;

    }

    /**
     * ���Һ�̣����ؼ��ֲ��ң����ҵ�����ؼ��֣��õ�����ؼ��ֶ�Ӧ�Ľڵ����Ȼ���ٷ���
     * ��̷�Ϊ���������
     * 1������ڵ�BinarySearchTreeNode ���������ǿգ���ôBinarySearchTreeNode�ĺ��ǡ����BinarySearchTreeNode�������е���С�ڵ�
     * 2������ڵ�BinarySearchTreeNode��������Ϊ�գ���ô��̾���BinarySearchTreeNode����ײ����ȣ����Һ�̵�����Ҳ��BinarySearchTreeNode�����ȡ�
     *
     * @param k �ؼ���
     * @return ���ҵ��ĺ�̽ڵ�
     */
    public BinarySearchTreeNode succeror(int k) {
        BinarySearchTreeNode BinarySearchTreeNode = searchRecursion(this.root, k);

        if (BinarySearchTreeNode == null) {
            System.out.println("û�� " + k + " ���Ԫ��");
            return null;
        }
        if (maxResursive(this.root) == BinarySearchTreeNode) {
            System.out.println(k + " ������Ԫ�أ�û�к��");
        }
        //��Ե�һ�����
        if (BinarySearchTreeNode.getRchild() != null) {
            BinarySearchTreeNode postBinarySearchTreeNode = maxResursive(BinarySearchTreeNode.getRchild());
            System.out.println(k + " �ĺ���ǣ�" + postBinarySearchTreeNode.getData());
            return postBinarySearchTreeNode;
        }
        //��ʱtemp�ڵ�
        BinarySearchTreeNode temp = BinarySearchTreeNode.getParent();
        //���ϲ���BinarySearchTreeNode�ڵ�ĺ��ֵ,ֱ����Ҫ���ҵĽڵ�ĸ��ڵ㣬����temp�ڵ���Һ���
        while (temp != null && BinarySearchTreeNode == temp.getRchild()) {

            BinarySearchTreeNode = temp;
            temp = temp.getParent();
        }
        if (temp != null) {
            System.out.println(k + " �ĺ���ǣ�" + temp.getData());
        }
        return temp;
    }

    public BinarySearchTreeNode getRoot() {
        return root;
    }

    public void setRoot(BinarySearchTreeNode root) {
        this.root = root;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}


/**
 * �ڵ���
 */
class BinarySearchTreeNode {
    //���ӣ��Һ��ӣ����׽ڵ㣬�� key ���ݣ���Ȼ�����key�������κζ���Ҳ���Դ���������
    private BinarySearchTreeNode lchild;
    private BinarySearchTreeNode rchild;
    private BinarySearchTreeNode parent;

    private int data;

    /**
     * ��ӽڵ�ķ�����ÿ���ڵ�������� data ֵ�����ж��Ƿ񽫵�ǰ������ӵ�����ĺ��ӽڵ㡣
     *
     * @param newBinarySearchTreeNode ��Ҫ��ӵĽڵ�
     */
    public void addBinarySearchTreeNode(BinarySearchTreeNode newBinarySearchTreeNode) {

        if (this.getData() > newBinarySearchTreeNode.getData()) {
            //���뵽�˽ڵ�����
            if (this.getLchild() != null) {
                this.getLchild().addBinarySearchTreeNode(newBinarySearchTreeNode);
            } else {
                //��ʼ����
                newBinarySearchTreeNode.setParent(this);
                this.setLchild(newBinarySearchTreeNode);
                System.out.println("����ڵ㣺" + newBinarySearchTreeNode.getData());
            }
        } else {
            //���뵽�˽ڵ���ұ�
            if (this.getRchild() != null) {
                this.getRchild().addBinarySearchTreeNode(newBinarySearchTreeNode);
            } else {
                newBinarySearchTreeNode.setParent(this);
                this.setRchild(newBinarySearchTreeNode);
                System.out.println("����ڵ㣺" + newBinarySearchTreeNode.getData());
            }
        }
    }


    public BinarySearchTreeNode(int data) {
        this.data = data;
    }

    public BinarySearchTreeNode getRchild() {
        return rchild;
    }

    public void setRchild(BinarySearchTreeNode rchild) {
        this.rchild = rchild;
    }

    public BinarySearchTreeNode getParent() {
        return parent;
    }

    public void setParent(BinarySearchTreeNode parent) {
        this.parent = parent;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinarySearchTreeNode getLchild() {
        return lchild;
    }

    public void setLchild(BinarySearchTreeNode lchild) {
        this.lchild = lchild;
    }
}

