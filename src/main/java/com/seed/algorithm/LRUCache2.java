package com.seed.algorithm;

import java.util.HashMap;

/**
 *
 */
public class LRUCache2 {


    public static void main(String[] args) {
        LRUCache2 cache = new LRUCache2(5);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        cache.put(5, 5);
        System.out.println(cache);
        System.out.println(cache.get(1));
        System.out.println(cache);
        cache.put(6, 6);
        System.out.println(cache.get(3));
        System.out.println(cache);

    }
    //双向链表节点定义
    class Node{
        int key;
        int value;
        Node prev;
        Node next;
    }

    private int capacity;
    //保存链表的头部节点和尾部节点
    private Node first;
    private Node last;

    private HashMap<Integer,Node> map;

    public LRUCache2(int capacity){
        this.capacity = capacity;
        map = new HashMap<>(capacity);
    }

    public int get(int key){
        Node node = map.get(key);
        //为空返回-1
        if(node==null){
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    private void moveToHead(Node node) {
        if(node==first){
            return;
        }else if(node==last){
            last.prev.next = null;
            last = last.prev;
        }
        else{
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        node.prev = first.prev;
        node.next = first;
        first.prev = node;
        first = node;
    }

    public void put(int key,int value){
        Node node = map.get(key);
        if(node==null){
            node = new Node();
            node.key = key;
            node.value = value;
            if(map.size()==capacity){
                removeLast();
            }
            addToHead(node);
            map.put(key,node);
        }else{
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(Node node) {
        if(map.isEmpty()){
            first = node;
            last = node;
        }else{
            node.next = first;
            first.prev = node;
            first = node;
        }
    }

    public void removeLast(){
        map.remove(last.key);
        Node prevNode = last.prev;
        if(prevNode!=null){
            prevNode.next = null;
            last = prevNode;
        }
    }

    @Override
    public String toString() {
        String str = "";
        Node cur = first;
        while (cur!=null){
            str+=cur.value;
            cur = cur.next;
        }
        return str;
    }
}