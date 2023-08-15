package com.seed.algorithm;

public class NodeReverse {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        showLink(node1);
        showLink(reverse(node1));

    }

    public static class Node{
        Node next;
        Integer value;
        public  Node(Integer value){
            this.value = value;
        }
    }

    public static void  showLink(Node node){

        while (node!=null){
            System.out.printf("%d------>",node.value);
            node = node.next;
        }
        System.out.println("null%n");
    }

    public static Node reverse(Node node){
        Node pre=null,next=null;
        while (node!=null){
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return  pre;
    }

}
