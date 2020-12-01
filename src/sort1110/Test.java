package sort1110;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 关于在普利姆算法学习过程中的总结：
 *  1.循环的意义是重复做相同的事情，但不是同一件事情，所以需要注意在整个循环的过程中是什么在变化
 *
 *  2.在设计的过程中方法也是一种思路的过程，可以很好地帮我们理清思路
 *
 *  3.关于方法的设计一定要在设计之前考虑好方法是否需要返回值，如果不需要，
 *      则需要考虑在整个计算过程中是否有改变某个引用数据类型的值，间而达到某种预期效果
 *      例如数组，集合，对象中的某些值被改变了，所以并不是所有的方法均需返回值的
 */
public class Test {

    public static void main(String[] args){
        MapNode node1 = new MapNode(0,"A");
        MapNode node2 = new MapNode(1,"B");
        MapNode node3 = new MapNode(2,"C");
        MapNode node4 = new MapNode(3,"D");
        MapNode node5 = new MapNode(4,"E");
        MapNode node6 = new MapNode(5,"F");
        MapNode node7 = new MapNode(6,"G");
        MapNode node8 = new MapNode(7,"H");
        Set<MapNode> mapNodeSet = new HashSet<MapNode>();
        mapNodeSet.add(node1);
        mapNodeSet.add(node2);
        mapNodeSet.add(node3);
        mapNodeSet.add(node4);
        mapNodeSet.add(node5);
        mapNodeSet.add(node6);
        mapNodeSet.add(node7);
        mapNodeSet.add(node8);

        int max = Integer.MAX_VALUE;
        int[][] distance = {
                {0,15,max,max,max,max,8,2},
                {15,0,13,max,max,max,max,10},
                {max,13,0,10,max,max,max,11},
                {max,max,10,0,2,max,max,8},
                {max,max,max,2,0,7,max,6},
                {max,max,max,max,7,0,9,20},
                {8,max,max,max,max,9,0,13},
                {2,10,11,8,6,20,13,0}};

//        prim(node1,mapNodeSet,distance);
//        System.out.println(node1);
        

    }

    /**
     * 普利姆算法
     *  1.任意选一个点作为起点
     *  2.找到已连接部分的最短的路径连接点
     *  不能形成回路
     */
    //普利姆算法
    //参数说明；start为给定的起点 ，mapNodeSet 所有需要连接的点的集合 ， distance为存储两个地点之间距离的二维数组
    public static MapNode prim(MapNode start, Set<MapNode> mapNodeSet,int[][] distance){
        //创建一个集合，用来存储已经连接好的节点
        Set<MapNode> linkedNode = new HashSet<MapNode>();
        linkedNode.add(start);
        while(linkedNode.size() < mapNodeSet.size()){//表示还有节点没有连接起来
           getMinDis(mapNodeSet,distance,linkedNode);//一次找寻一个节点
        }
        return start;
    }

    //设计一个方法 ，用来找寻 一个 与给定集合(初始时只有一个节点)中的点的最近的节点,并将节点连接起来
    public static void getMinDis(Set<MapNode> mapNodeSet,int[][] distance,Set<MapNode> linkedNode){
        int minDis = Integer.MAX_VALUE;
        MapNode start = null;
        MapNode end = null;
        for(MapNode node : linkedNode){
            for(int i =0; i < distance[node.index].length; i++){
                if(distance[node.index][i] < minDis && distance[node.index][i] > 0 && !linkedNode.contains(get(mapNodeSet,i))){
                        minDis = distance[node.index][i];
                        start = node;
                        end = get(mapNodeSet,i);
                }
            }
        }
        start.neighborNode.add(end);
        end.neighborNode.add(start);
        linkedNode.add(end);
    }


    //通过给定的索引找到linkedNode中的节点
    public static MapNode get(Set<MapNode> mapNodeSet ,int index){
        for(MapNode mapNode : mapNodeSet){
            if(mapNode.index == index) return mapNode;
        }
        return null;
    }
}
