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

    //初始时候根节点是哨兵节点
    private RedBlackTreeNode root = NIL;
    private int count;
    //哨兵节点（虚节点，只是做标志位用，没有实际意义），是静态常量
    private static final RedBlackTreeNode NIL = new RedBlackTreeNode();

    //初始化哨兵节点
    static {
        NIL.setColor(TreeColor.Black);
        NIL.setData(-1);
        NIL.setParent(null);
        NIL.setLeft(null);
        NIL.setRight(null);
    }

    /**
     * 中序遍历二叉搜索树，结果是顺序的
     *
     * @param root 树的根节点
     */
    public void inorderTravral(RedBlackTreeNode root) {

        if (root == this.root) {
            System.out.print("中序遍历: ");
        }
        if (root != NIL) {
            inorderTravral(root.getLeft());
            System.out.print(root.getData() + "-" + root.getColor().toString() + "  ");
            inorderTravral(root.getRight());
        }
    }
    /**
     * 前序遍历二叉搜索树，结果是顺序的
     *
     * @param root 树的根节点
     */
    public void preorderTravral(RedBlackTreeNode root) {

        if (root == this.root) {
            System.out.print("前序遍历: ");
        }
        if (root != NIL) {
            System.out.print(root.getData() + "-" + root.getColor().toString() + "  ");
            preorderTravral(root.getLeft());
            preorderTravral(root.getRight());
        }
    }
    /**
     * 后序遍历二叉搜索树，结果是顺序的
     *
     * @param root 树的根节点
     */
    public void postorderTravral(RedBlackTreeNode root) {

        if (root == this.root) {
            System.out.print("后序遍历: ");
        }
        if (root != NIL) {

            postorderTravral(root.getLeft());
            postorderTravral(root.getRight());
            System.out.print(root.getData() + "-" + root.getColor().toString() + "  ");
        }
    }

    /**
     * 将x节点向左旋转，并不会改变节点值的数据大小关系，
     * 左旋和右旋很简单，不需要考虑颜色的变化，
     * 只是将节点断开，旋转，再连上。知道断开和连接的先后顺序就比较好理解了。
     * @param x 需要旋转的节点
     */
    public void left_rotate(RedBlackTreeNode x) {
        //记录x的右孩子节点
        RedBlackTreeNode y = x.getRight();
        //设置x的右孩子为y的左孩子
        x.setRight(y.getLeft());
        //如果y的左孩子不为哨兵节点，设置y左孩子的父亲为x
        if (y.getLeft() != NIL) {
            y.getLeft().setParent(x);
        }
        //设置y的父亲为x的父亲
        y.setParent(x.getParent());

        if (x.getParent() == NIL) {
            //如果x的父亲为哨兵节点，表明x是根节点，那么将y设置为根节点就好了
            this.setRoot(y);
        } else if (x == x.getParent().getLeft()) {
            //如果x是x父亲的左孩子，那么设置x父亲的左孩子为y
            x.getParent().setLeft(y);
        } else {
            //如果x是x父亲的右孩子，那么设置x父亲的右孩子为y
            x.getParent().setRight(y);
        }
        //设置y的左孩子为x
        y.setLeft(x);
        //设置x的父亲为y
        x.setParent(y);
    }
    /**
     * 将x节点向右旋转，并不会改变节点值的数据大小关系，
     * 左旋和右旋很简单，不需要考虑颜色的变化，
     * 只是将节点断开，旋转，再连上。知道断开和连接的先后顺序就比较好理解了。
     * @param x 需要旋转的节点
     */
    public void right_rotate(RedBlackTreeNode x) {
        //记录x的左孩子节点
        RedBlackTreeNode y = x.getLeft();
        //设置x的左孩子为y的右孩子
        x.setLeft(y.getRight());
        //如果y的右孩子不为哨兵节点，设置y右孩子的父亲为x
        if (y.getRight() != NIL) {
            y.getRight().setParent(x);
        }
        //设置y的父亲为x的父亲
        y.setParent(x.getParent());

        if (x.getParent() == NIL) {
            //如果x的父亲为哨兵节点，表明x是根节点，那么将y设置为根节点就好了
            this.setRoot(y);
        } else if (x == x.getParent().getLeft()) {
            //如果x是x父亲的左孩子，那么设置x父亲的左孩子为y
            x.getParent().setLeft(y);
        } else {
            //如果x是x父亲的右孩子，那么设置x父亲的右孩子为y
            x.getParent().setRight(y);
        }
        //设置y的右孩子为x
        y.setRight(x);
        //设置x的父亲为y
        x.setParent(y);
    }

    /**
     * 插入函数，用来构造一个插入节点。
     * @param k 插入的数据值
     */
    public void insert(int k) {
        RedBlackTreeNode node = new RedBlackTreeNode();
        node.setData(k);
        node.setColor(TreeColor.Red);
        //将需要插入的节点的所有元素都设置成哨兵节点
        node.setParent(NIL);
        node.setLeft(NIL);
        node.setRight(NIL);
        //调用红黑二叉树的插入方法
        rb_insert(node);
    }

    /**
     * 用于红黑二叉树的插入方法
     * @param z 插入的节点对象
     */
    public void rb_insert(RedBlackTreeNode z) {
        //y是待插入的节点位置，默认也是哨兵节点
        RedBlackTreeNode y = this.NIL;
        //x是，根节点，不断比较数据值，直到直到带插入的位置
        RedBlackTreeNode x = this.getRoot();
            while (x != this.NIL) {
                y = x;
                if (z.getData() < x.getData()) {
                    x = x.getLeft();
                } else {
                    x = x.getRight();
                }
            }
        //将z插入到y后面
        z.setParent(y);
        if (y == NIL) {
            //如果z是第一个插入的值,将z设置为根节点
            this.setRoot(z);
        } else if (z.getData() < y.getData()) {
            //将z插入到y的左边
            y.setLeft(z);
        } else {
            //z插入到y的右边
            y.setRight(z);
        }
        //插入节点可能破坏了红黑树性质，所以调用修补方法
        rb_insert_fixup(z);
        count++;
        System.out.println("insert："+z.getData());
    }

    /**
     *  红黑树插入修补方法，主要有三种情况需要修补
     *  情况1：z的叔叔节点y是红色的
     *  情况2：z的叔叔节点y是黑色的，且z是一个右孩子
     *  情况3：z的叔叔节点y是黑色的，且z是一个左孩子
     * @param z 引起了性质变化的节点z
     */
    public void rb_insert_fixup(RedBlackTreeNode z) {
        //while循环是说z 的父亲的颜色如果是红色，就需要进行修补。
        // 第一次插入元素的时候，z的父亲是哨兵节点，所以不用修补，直接执行while后面的设置根节点为黑色的语句
        // 之后每次插入都需要判断z节点的父亲节点的颜色进行判断，因为插入的z节点初始设置为红色的，如果z的父亲节点为黑色，就不用修补了，如果为红色，那么z的父节点和z都是红色，那么就需要进行修补
        while (z.getParent().getColor() == TreeColor.Red) {
            //如果z的父亲 是z的爷爷的左孩子，这里当z是插入的是第二节点的时候，z的父亲是根节点，z的父亲的父亲是一个哨兵节点，所以走了else区域
            if (z.getParent() == z.getParent().getParent().getLeft()) {
                //得到z的叔叔，叔叔是z的爷爷的右孩子，所有的叔叔都有可能是哨兵节点，如果是哨兵节点的话，那么叔叔的颜色就是黑色
                RedBlackTreeNode y = z.getParent().getParent().getRight();
                //判断z的叔叔的颜色，如果叔叔是红色，那么z的父亲一定是红色，又因为刚插入的z是红色，所以违背了性质，
                if (y.getColor() == TreeColor.Red) {
                    //情况1：z的叔叔节点y是红色的
                    //修改父亲的颜色为黑色
                    z.getParent().setColor(TreeColor.Black);
                    //叔叔的颜色也设置为黑色
                    y.setColor(TreeColor.Black);
                    //z的爷爷的颜色设置为红色，这样在z的爷爷为跟的子树上保持了性质
                    z.getParent().getParent().setColor(TreeColor.Red);
                    //开始从叶子节点往上走，可能z 的爷爷那么一辈的性质被破坏了，所以再次循环，去看z的爷爷的父亲的颜色是什么
                    z = z.getParent().getParent();
                } else {
                    //表明z的叔叔的颜色，是黑色，此时z的父亲的颜色不确定，

                    //如果z是z的父亲的右孩子
                    if (z == z.getParent().getRight()) {
                        //情况2：z的叔叔节点y是黑色的，且z是一个右孩子，将z的父亲左旋，保持性质
                        z = z.getParent();
                        left_rotate(z);
                    }
                    //情况3：z的叔叔节点y是黑色的，且z是一个左孩子

                    //将z的父亲的颜色设置为黑色
                    z.getParent().setColor(TreeColor.Black);
                    //将z的爷爷的颜色设置为红色
                    z.getParent().getParent().setColor(TreeColor.Red);
                    //将z的爷爷右旋
                    right_rotate(z.getParent().getParent());

                    //情况2后面紧跟情况3 ，是因为情况2并没有对颜色进行修改，只是调整了大小关系，通过情况3对颜色的修改，和旋转操作，才能达到平衡


                }
            } else {
                //类似于上面
                //如果z的父亲 是z的爷爷的右孩子
                //得到z的叔叔，叔叔是z爷爷的左孩子
                RedBlackTreeNode y = z.getParent().getParent().getLeft();
                //判断z的叔叔的颜色，如果叔叔是红色，那么z的父亲一定是红色，又因为刚插入的z是红色，所以违背了性质，
                if (y.getColor() == TreeColor.Red) {
                    //情况1：z的叔叔节点y是红色的
                    //修改父亲的颜色为黑色
                    z.getParent().setColor(TreeColor.Black);
                    //叔叔的颜色也设置为黑色
                    y.setColor(TreeColor.Black);
                    //z的爷爷的颜色设置为红色，这样在z的爷爷为跟的子树上保持了性质
                    z.getParent().getParent().setColor(TreeColor.Red);
                    //开始从叶子节点往上走，可能z 的爷爷那么一辈的性质被破坏了，所以再次循环，去看z的爷爷的父亲的颜色是什么
                    z = z.getParent().getParent();
                } else {
                    //表明z的叔叔的颜色，是黑色，此时z的父亲的颜色不确定，

                    //如果z是z的父亲的右孩子
                    if (z == z.getParent().getLeft()) {
                        //情况2：z的叔叔节点y是黑色的，且z是一个左孩子，将z的父亲右旋，保持性质
                        z = z.getParent();
                        right_rotate(z);
                    }
                    //情况3：z的叔叔节点y是黑色的，且z是一个右孩子
                    z.getParent().setColor(TreeColor.Black);
                    //将z的爷爷的颜色设置为红色
                    z.getParent().getParent().setColor(TreeColor.Red);
                    //将z的爷爷右旋
                    left_rotate(z.getParent().getParent());
                }
            }
        }
        //不管怎么样，将根节点设置为黑色
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
 * 红黑树节点类
 */
class RedBlackTreeNode {
    //数据
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
 * 数的颜色枚举类
 */
enum TreeColor {
    Red("red"), Black("black");
    TreeColor(String string) {
    }
}