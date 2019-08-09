package zjf;

import cn.hutool.core.util.ObjectUtil;

import java.util.HashMap;


 class Node {
    //键
    Object key;
    //值
    Object value;
    //上一个节点
    Node pre;
    //下一个节点
    Node next;

    public Node(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUDemo<K,V>{

    private int currentSize;
    private int capacity;
    private HashMap<K,Node> caches;
    private Node first;
    private Node last;

    public LRUDemo(int size){
        currentSize = 0;
        this.capacity = size;
        caches = new HashMap<K,Node>(size);

    }

    /**
     * 放入元素
     * @param key
     * @param value
     */
    public void put(K key,V value){
        Node node = caches.get(key);

        if(node == null){
            //如果超过元素容纳量
            if(caches.size()>=capacity){
                //移除最后一个节点
                caches.remove(last.key);

                removeLast();
            }

            //创建新节点
            node = new Node(key,value);

        }
        //已经存在的元素覆盖旧值
        node.value = value;

        //把元素移动到首部
        moveToHead(node);
        caches.put(key,node);

    }

    /**
     * 通过key获取元素
     * @param key
     * @return
     */
    public Object get(K key){
        Node node = caches.get(key);

        if(node == null){
            return null;

        }
        //把访问的节点移动到首部
        moveToHead(node);
        return node.value;

    }

    /**
     * 根据key移除节点
     * @param key
     * @return
     */
    public Object remove(K key) {
        Node node = caches.get(key);
        if (node != null) {
            if (node.pre != null) {
                node.pre.next = node.next;
            }
            if (node.next != null) {
                node.next.pre = node.pre;
            }
            if (node == first) {
                first = node.next;
            }
            if (node == last) {
                last = node.pre;
            }
        }
        return caches.remove(key);
    }

    /**
     * 清空
     */
    public void clear() {
        first = null;
        last = null;
        caches.clear();
    }

    /**
     * 把当前节点移动到首部
     * @param node
     */
    private void moveToHead(Node node) {
        if (first == node) {
            return;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node == last) {
            last = last.pre;
        }
        if (first == null || last == null) {
            first = last = node;
            return;
        }
        node.next = first;
        first.pre = node;
        first = node;
        first.pre = null;
    }

    /**
     * 移除最后一个节点
     */
    private void removeLast() {
        if (last != null) {
            last = last.pre;
            if (last == null) {
                first = null;
            } else {
                last.next = null;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = first;
        while (node != null) {
            sb.append(String.format("%s:%s ", node.key, node.value));
            node = node.next;
        }
        return sb.toString();
    }


}
