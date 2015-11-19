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


        // 1.����K��hashֵ
        // ��Ϊ�Լ�����д���Բ�ͬ�����Ͷ����õ�Hash�㷨���ʵ���JDK������hashCode()����������hashֵ
        int khash = k.hashCode();

        //��������Ϣ��װΪһ��Entry
        Entry<K, V> temp = new Entry(k, v, khash);

        if (setEntry(temp, hashtable)) {
            // ��С��һ
            size++;
            return true;
        }
        return false;

    }

    /**
     * ��ָ���Ľ��temp��ӵ�ָ����hash��table����
     * ���ʱ�жϸý���Ƿ��Ѿ�����
     * ����Ѿ����ڣ�����false
     * ��ӳɹ�����true
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
    Entry<K, V> next;// ��һ�����
    K key;// key
    V value;// value
    int hash;// ���key��Ӧ��hash�룬��Ϊһ����Ա���������´���Ҫ�õ�ʱ����Բ������¼���

    // ���췽��
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
