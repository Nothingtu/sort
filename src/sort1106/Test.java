package sort1106;

import sort1105.Node;

import java.util.Arrays;

/**
 * 数组的归并排序
 * 将数组进行拆分 排序 在进行合并
 */
public class Test {

    public static void main(String[] args){

        int[] a = {1,9,5,6,7,2,3,4,8,15,200};
        quickSort(a);
        for(int val : a ){
            System.out.println(val);
        }






//        Node a = new Node(1);
//        Node b = new Node(2);
//        Node c = new Node(3);
//        Node d = new Node(4);
//        Node e = new Node(5);
//
//        a.next = b;
//        b.next = c;
//        c.next = d;
//        d.next = e;
//        e.next = null;
//
//
//        Node result = reverse(a);
//        System.out.println(result);




//        int[] a = {1,9,5,6,7,2,3,4,8,15,200};
//        int[] result = mergerSort(a);
//        for(int c : result){
//            System.out.println(c);
//        }




//        int[] a = {1,3,5,7};
//        int[] b = {2,4,6,8};
//        int[] s = mergerArray(a,b);
//        for(int c : s){
//            System.out.println(c);
//        }

    }


    //标准的快速排序

    //一次排序选出一个分割点
    //参数说明，low high是需要排序数组的起点和终点
    public static int partIndex(int[] arr,int low,int high){
        //先把起点位置的值单独拿出来作比较
        int leader = arr[low];
        while(low < high){
            while(low < high && arr[high] >=leader) high --;
            arr[low] = arr[high];
            while(low < high && arr[low] <= leader) low ++;
            arr[high] = arr[low];
        }
        //此时：low=high，即low和high重合了，应该把原来的leader放到此时low索引位置上
        arr[low] = leader;
        return low;
    }

    //参数说明，此时的begin和end是两个可以取到的端点
    public static int[] quickSort(int[] arr,int begin,int end){
        if(arr == null || arr.length <= 1 || begin >= end) return arr;
        //以0号索引进行的排序，比leader小的放在它的左边，比leader大的放在它的右边
        int center = partIndex(arr,begin,end);
        //此时的arr就变成了索引比center小的位置上的值均小于center索引上的值，
        //              索引比center大的位置上的值均大于center索引上的值，值和center位置上的值相同的索引和center相连
        //因为以上的排序不产生新的数组，而center两侧的数均为排好大小的，故center不用再次参与排序
        quickSort(arr,0,center - 1);
        quickSort(arr,center + 1,end);
        return arr;
    }

    public static int[] quickSort(int[] arr){
        return quickSort(arr,0,arr.length - 1);
    }





    //链表的逆置
    //递归方法一定有返回值
    public static  Node reverse(Node node){
        //0.确定参数的正确性
        if(node == null || node.next == null) return node;
        //1.确定返回值的形式
        Node result = null;//返回链表的首节点

        //2.递归的出口 当前节点的下一个的下一个为空时，表示当前节点为倒数的第二个节点 ，即为递归的出口
        if(node.next.next == null) result = node.next;
        //result.next = node;可以替代70行的代码，但通用性不强

        //3.递归循环体
        else result = reverse(node.next);

        //把当前节点变成当前节点的下一个节点的下一个节点，也就是把当前节点挂到链表的尾结点的后面
        node.next.next = node;
        node.next = null;

        return result;
    }




    //方法重载
    public static int[] mergerSort(int[] arr){
        if(arr == null || arr.length == 1) return arr;
        return mergerSort(arr,0,arr.length);
    }

    //mergerSort 归并排序
    //参数说明：start表示需要进行归并排序的数组arr的起点位置，end表示需要进行归并排序的数组arr的终点位置(取不到该位置)
    //         实例；想要对数组arr的0-6号索引进行归并排序，则参数为(arr,0,7)
    public static int[] mergerSort(int[] arr,int start ,int end){
        if(arr == null || arr.length == 1) return arr;
        int[] result = null;
        if(end -start > 2){
            //此处是按照从中间对半拆分的原则
            int[] a = mergerSort(arr,start,(end-start)/2 + start);
            int[] b = mergerSort(arr,(end-start)/2 + start,end);
            //将拆分好的有序数组进行合并
            return mergerArray(a,b);
        }else if(end - start == 2){//此处为递归的出口之一

            //因为此处的end位置是取不到的，所以不能写成arr[end]
            if(arr[start] <= arr[start + 1]) result = new int[]{arr[start],arr[start + 1]};
            if(arr[start] > arr[start + 1]) result = new int[]{arr[start + 1],arr[start]};
        }else if(end - start == 1){//此处为递归的出口之一

            //此处表示的是end-start==1,当长度为奇数是，对半拆分是有可能会出现数组长度为1的情况
            result = new int[]{arr[start]};
        }
        return result;
    }

    //设计一个方法，对两个有序数组进行合并
    public static int[] mergerArray(int[] a,int[] b){
        if(b == null)return a;
        if(a == null)return b;
        int[] result = new int[a.length + b.length];
        //创建三个指针分别用来指向result，a，b三个数组的当前索引位置
        int rPoint = 0;//表示result数组的当前索引
        int aPoint = 0;//表示a数组的当前索引
        int bPoint = 0;//表示b数组的当前索引
        while (aPoint != a.length || bPoint != b.length){
            // &&的运算优先级要高于|| ，所以下面的逻辑运算
            if(bPoint == b.length || aPoint != a.length && a[aPoint] <= b[bPoint]){
                result[rPoint] = a[aPoint];
                aPoint ++;
            }else{
                result[rPoint] = b[bPoint];
                bPoint ++;
            }
            rPoint ++ ;
        }
        return result;
    }

}
