package sort1105;

public class Test {
    public static void main(String[] args){
        Node node1 = new Node(1);
        Node node3 = new Node(3);
        Node node5 = new Node(5);
        Node node7 = new Node(7);
        Node node9 = new Node(9);

        node1.next = node3;
        node3.next = node5;
        node5.next = node7;
        node7.next = node9;
        node9.next = null;


        Node node2 = new Node(2);
        Node node4 = new Node(4);
        Node node6 = new Node(6);
        Node node8 = new Node(8);
        Node node10= new Node(10);

        node2.next = node4;
        node4.next = node6;
        node6.next = node8;
        node8.next = node10;
        node10.next = null;

        Test t = new Test();
        Node n = t.merge(node1,node2);
        System.out.println(n);

    }

    //设计一个方法对两个有序(从小到大)的单向链表进行合并
    public Node merge(Node a ,Node b){
        if(a == null) return b;
        if(b == null) return a;

        //创建返回两边的头和尾节点
        Node begin = null;
        Node last = null;

        //表示经过第一轮的比较，比较出返回链表的首节点
        if(a.value <= b.value){
           begin = a;
           a = a.next;
        }else{
            begin = b;
            b = b.next;
        }

        //至于一个节点是，头和尾是同一个节点
        last = begin;

        while(a != null || b != null){
            if(b == null || a!= null && a.value <= b.value){
                last.next = a ;
                last = a;
                a = a.next;
            }else{
                last.next = b ;
                last = b;
                b = b.next;
            }
        }
        return  begin;
    }



}
