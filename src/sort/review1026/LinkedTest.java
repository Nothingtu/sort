package sort.review1026;

import java.awt.desktop.SystemSleepEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedTest {

    static class Node{
        public Node next;
        public int value;

        public Node(){}
        public Node(int value){
            this.value = value;
        }
    }

    //链表的逆置即：a b c d e f g 变成 g f e d c b a
    //链表的逆置必须先找到尾结点，一旦非尾结点指向了上一个，则这个链表就断开了，无法在找到下一个了
    //找到了尾结点后，必须让尾结点的下一个指向原来的上一个，
    //一旦当前的节点是尾结点，则无法通过当前尾结点找到上一个节点，
    //所以尾结点还可以表示为尾结点的上一个节点的next节点
    public static Node change(Node node){
        //创建一个节点 表示逆置完成后的首节点
        Node result = null;
        //当前节点的下一个即是尾结点  递归的出口
        if(node.next.next == null){
            result = node.next;
            //把尾结点的下一个节点指向当前节点
            result.next = node;
            //把当前节点的下一个节点指为空
            node.next = null;
        }else{
            result = change(node.next);
            node.next.next = node;
            node.next = null;
        }

        return result;
    }

    public static Node change2(Node node){
        //创建一个节点 表示逆置完成后的首节点
        Node result = null;
        if(node.next.next == null){
            result = node.next;
        }else{
            result = change2(node.next);
        }
        node.next.next = node;
        node.next = null;
        return result;
    }

    //设计一个方法 来遍历打印节点
    public static void lookall(Node node){
        if(node != null){
            System.out.println(node.value);
            lookall(node.next);
        }else{
            return ;
        }

    }

    //阶乘factorial 假设阶乘的结果在int的取值范围内
    public int factorial(int x){
        //找到出口
        if(x == 1 ){
            return 1;
        }else{
            return x*factorial(x-1);
        }

    }

    public static void main(String args[]){
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        Node g = new Node(7);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;

        Node node = change(a);
        lookall(node);

//        String test = "test of java";
//        String[] strs = test.split("[^ ]|[ $$]");
//        for(int i= 0 ;i<strs.length ;i++){
//            System.out.println(i+"---"+strs[i]);
//        }
//
//        LinkedTest linkedTest = new LinkedTest();
//        linkedTest.lookall(linkedTest.change2(a));
//        System.out.println(linkedTest);


//        System.out.println(linkedTest.factorial(5));
//
//        LinkedList a2 = new LinkedList();
//        long t1 = System.currentTimeMillis();
//        for(int i = 0 ;i<200000; i ++){
//            a2.addFirst("a");
//        }
//        long t2 = System.currentTimeMillis();
//        System.out.println(t2-t1);

    }


}
