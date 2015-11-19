package com.zq.algorithm.hashmethod;


/**
 * Created by zhengshouzi on 2015/11/14.
 */
public class HashMethod {
    public static void main(String[] args) {

    }


}

class DivideHash<K, V> {


    private static int capatiry = 20;
    private static float load_factory = 0.75f;
    private int max = (int) (capatiry * load_factory);
    private Entry<K, V>[] hashtable;
    private int size = 0;


    public DivideHash(int capaticy, float load_factor) {

        if (capaticy < 0)
            throw new IllegalArgumentException("Illegal initial capacity: "
                    + capaticy);
        if (load_factor <= 0 || Float.isNaN(load_factor))
            throw new IllegalArgumentException("Illegal load factor: "
                    + load_factor);

        this.load_factory = load_factor;
        this.max = (int) (capaticy * load_factor);
        this.hashtable = new Entry[capaticy];
    }

    public DivideHash() {
        this(capatiry, load_factory);
    }

    public boolean insert(K k, V v) {


        // 1.计算K的hash值
        // 因为自己很难写出对不同的类型都适用的Hash算法，故调用JDK给出的hashCode()方法来计算hash值
        int khash = k.hashCode();

        //将所有信息封装为一个Entry
        Entry<K, V> temp = new Entry(k, v, khash);

        if (setEntry(temp, hashtable)) {
            // 大小加一
            size++;
            return true;
        }
        return false;

    }

    /**
     * 将指定的结点temp添加到指定的hash表table当中
     * 添加时判断该结点是否已经存在
     * 如果已经存在，返回false
     * 添加成功返回true
     *
     * @param temp
     * @param table
     * @return
     */
    private boolean setEntry(Entry<K, V> temp, Entry[] table) {


        return true;
    }

    public int indexFor(int hashcode, int containerLength) {
        return hashcode & (containerLength - 1);

    }

    public void search(int a) {

    }

    public void delete(int a) {

    }
}

class Entry<K, V> {
    Entry<K, V> next;// 下一个结点
    K key;// key
    V value;// value
    int hash;// 这个key对应的hash码，作为一个成员变量，当下次需要用的时候可以不用重新计算

    // 构造方法
    Entry(K k, V v, int hash) {
        this.key = k;
        this.value = v;
        this.hash = hash;

    }

    public Entry<K, V> getNext() {
        return next;
    }

    public void setNext(Entry<K, V> next) {
        this.next = next;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }
}
