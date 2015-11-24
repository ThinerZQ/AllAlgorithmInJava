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
        System.out.println("删除6之后");
        System.out.println();
        redBlackTree.inorderTravral(redBlackTree.getRoot());
        System.out.println();
        redBlackTree.preorderTravral(redBlackTree.getRoot());
        System.out.println();
        redBlackTree.postorderTravral(redBlackTree.getRoot());

        redBlackTree.delete(100);

        System.out.println("删除100之后");
        System.out.println();
        redBlackTree.inorderTravral(redBlackTree.getRoot());
        System.out.println();
        redBlackTree.preorderTravral(redBlackTree.getRoot());
        System.out.println();
        redBlackTree.postorderTravral(redBlackTree.getRoot());

        redBlackTree.delete(20);

        System.out.println("删除20之后");
        System.out.println();
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
     *
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
     *
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
     *
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
     *
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
        System.out.println("insert：" + z.getData());
    }

    /**
     * 红黑树插入修补方法，主要有三种情况需要修补
     * 情况1：z的叔叔节点y是红色的
     * 情况2：z的叔叔节点y是黑色的，且z是一个右孩子
     * 情况3：z的叔叔节点y是黑色的，且z是一个左孩子
     *
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

    /**
     * 将一个元素移动到另外一个元素的位置上，用于删除操作过程
     * 这个过程断开了原来的deleted节点和父亲的关系，将replace和deleted的父亲设置成了父子关系
     * @param root 树根节点
     * @param deleted 被删除的元素
     * @param replace 用来移动到被删除元素位置上的元素
     */
    public void rb_transplant(RedBlackTreeNode root,RedBlackTreeNode deleted,RedBlackTreeNode replace){

        if (deleted.getParent() == NIL){
            //被删除的节点是根节点，那么直接用replace节点来作为根节点
            this.setRoot(replace);
        }else if (deleted == deleted.getParent().getLeft()){
            //被删除的节点是它父亲的左孩子，将replace设置为他父亲的左孩子
            deleted.getParent().setLeft(replace);
        }else {
            //被删除的节点是它父亲的右孩子，将replace设置为他父亲的右孩子
            deleted.getParent().setRight(replace);
        }
        //不管怎么样，肯定是要把replace的父亲设置成deleted的父亲的
        replace.setParent(deleted.getParent());
    }

    /**
     * 对外提供的删除某一个元素之的函数，
     * @param k 需要删除的元素值
     */
    public void delete(int k){
        //找到需要删除的值对应的函数
        RedBlackTreeNode node = searchRecursion(this.getRoot(), k);
        //调用红黑二叉树的删除操作
        rb_delete(this.getRoot(), node);
    }

    /**
     * 红黑二叉树的删除操作，这个函数直观删除某一个元素，不管调整红黑树性质
     * @param root 红黑树的根节点
     * @param z 需要删除的节点
     */
    public void rb_delete(RedBlackTreeNode root,RedBlackTreeNode z){
        //维持节点 y 为 从树中  删除的节点  或者 需要   和删除节点互换   的节点。
        RedBlackTreeNode y = z;
        //记录下 y 的最开始的颜色
        TreeColor y_original_color = y.getColor();
        //声明 x 节点为 ，后面设置x 节点为 y节点的右孩子，或者左孩子
        RedBlackTreeNode x = null;

        //z的左孩子为空
        //如果z 只有一个右孩子节点，这种情况只可能是 z 是某一个叶子节点的上一层节点，
        if (z.getLeft() == NIL){
            //进入到这里面说明z的右孩子可有可无。
            // 如果 z 有右孩子的话，那么 z 一定是黑色的；；
            // 如果 z 没有右孩子的话，那么 z 可能是红色的可能是黑色的，一定是叶子节点。

            //因为如果z是红色节点，如果z存在叶子节点的话，那么必然同时存在2 个黑色叶子节点（性质3）
            // 如果 z 是黑色节点，如果 z 存在叶子节点的话，如果存在的右孩子为红色，则正常；   如果右孩子为黑色（违反性质5）因为没有左孩子啊

            //记录x 为 z的右孩子节点，不管  z 有没有右孩子，如果没有右孩子，z就是 NIL 节点
            x = z.getRight();
            //将  z 和 z 的 右孩子互换，不管 z 的右孩子是什么
            rb_transplant(root, z, z.getRight());
        }else if (z.getRight() == NIL){
            //进入到这里面说明z的左孩子一定是有的，但是没有右孩子。
            //这时候只有一种情况，就是 z 是黑色的，并且左孩子是红色的。。。
            //因为如果 z 是红色节点，又因为z 是有左孩子的，那么z 必然有右孩子（否则违反性质4） ，不会进入到这里的
            // 如果 z 是黑色节点，又因为z 是有左孩子的，如果左孩子是黑色的，那么 必然会有右孩子且是黑色的（否则违反性质5）。

            //记录x 为 z的左孩子节点，z 一定是有左孩子的
            x= z.getLeft();
            //将 z和z的左孩子互换
            rb_transplant(root, z, z.getLeft());
        }else {
            //进入到这里，说明 z 既有左孩子，又有右孩子

            //记录下 z 的后继节点 y ，以后会将 y 和 z 互换
            y= minResursive(z.getRight());
            //保存下来  y 的原始颜色
            y_original_color=y.getColor();
            // 记录下 y  的右孩子节点，x 要么是一个NIL 节点或者一个真实的叶节点。
            // 因为y 是某一颗子树的最小值，那么 y 必然是子树最左边的叶节点，或者叶节点的上一层节点。
            //因为 如果y 为红色，那么y 必然是叶节点，因为如果 y不是叶节点，那么 y 必然带有黑色的两个子节点（性质4）,那么y 就不会是子树最小值
            //如果 y 为黑色，那么 y 可能是叶节点，也可能 y 有一个右孩子节点（不可能有左孩子节点，不然y就是不是最小值了），这个右孩子节点一定是红色，（如果不是红色，就违反了性质5）
            x = y.getRight();
            if (y.getParent() == z){
                //如果 y 的父亲是 z，也就是说，z的后继就是 z 的孩子节点，，这时候 y 必然没有孩子节点，这里面的 x 是NIL节点
                //将x 的父亲设置为 y，，似乎可以省略这里，待会儿再试
                x.setParent(y);
            }else {
                //y 不是z 的右孩子，那么先将 y 和y 的右孩子互换，
                rb_transplant(root,y,y.getRight());
                //将y的右孩子设置成 z的右孩子
                y.setRight(z.getRight());
                //将y的右孩子和 y 续上关系
                y.getRight().setParent(y);
            }
            //接下来，将y 与 z 互换
            rb_transplant(root,z,y);
            //将 y 和 z 左边部分续上关系
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            //将 y 的颜色设置为 z 的颜色
            y.setColor(z.getColor());
        }

        //如果 y 的颜色原来是黑色，那么可能破坏了性质，需要修补
        //因为如果 y 的颜色如果是红色，那么 将 y 和 z 互换位置，不会破坏关系。
        // 因为上面 y.setColor(z.getColor()); 将y的颜色设置为了以前 z的颜色，保持了性质1,2，3，4 ，接着y 又是红色，将红色的节点删除不会破坏性质5，
        if (y_original_color == TreeColor.Black){
            //这里的 x 要么是一个 叶节点，要么是一个NIL节点。
            //为什么需要针对 x 进行修补？ 因为可以看做 x 最后替换了 y的位置。（参考x 的赋值语句），
            //将现在占有y 原来位置的节点 x 视为还有一重 额外的黑色。
            rb_delete_fixup(root,x);
        }

    }

    /**
     * 修补因为删除了节点破坏了的红黑树性质
     * @param root 红黑树的根
     * @param x 需要修补的节点
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
     * 判断红黑树是否满足性质
     * @param root 树根
     * @return
     */
    public boolean isRedBlackTree(RedBlackTreeNode root){

        if (root.getColor() != TreeColor.Black){
            return false;
        }else {
            //插入操作保证了性质1,3是满足的，只需要判断性质性质2,4,5，进入到这里说明性质2成立，返回性质4和5就成了
            return xingzhi4(root) && xingzhi5(root);
        }
    }

    /**
     * 判断红黑树的性质4是否成立
     * @param root 树根
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
     * 判断红黑树性质5是否成立
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
     * 递归求最小值
     *
     * @param root 指向根节点的（引用）
     * @return 找到的最小值对象
     */
    public RedBlackTreeNode minResursive(RedBlackTreeNode root) {

        if (root.getLeft() == NIL) {
            System.out.println("最小值：" + root.getData());
            return root;
        } else {
            return minResursive(root.getLeft());
        }
    }
    /**
     * 递归查找某一个元素
     *
     * @param root 指向根节点的（引用）
     * @param k    需要查找的值
     * @return 返回找到的节点，为空则没有这个值。
     */
    public RedBlackTreeNode searchRecursion(RedBlackTreeNode root, int k) {

        //root ==null ,表示没有找到此元素，k==root.getData() 表示找到了这个元素
        if (root == NIL || k == root.getData()) {
            if (root == NIL) {
                System.out.println("没有找到 ：" + k);
            } else if (k == root.getData()) {
                System.out.println("找到：" + k);
            }
            return root;
        }
        //递归查找，分两边查找咯。
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
 * 树的颜色枚举类
 */
enum TreeColor {
    Red("red"), Black("black");

    TreeColor(String string) {
    }
}
