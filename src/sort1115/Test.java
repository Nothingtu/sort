package sort1115;

import jdk.swing.interop.SwingInterOpUtils;
import sort1110.MapNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 克鲁斯卡尔算法
 */
public class Test {
    public static void main(String[] args) {
        MapNode node1 = new MapNode(0, "A");
        MapNode node2 = new MapNode(1, "B");
        MapNode node3 = new MapNode(2, "C");
        MapNode node4 = new MapNode(3, "D");
        MapNode node5 = new MapNode(4, "E");
        MapNode node6 = new MapNode(5, "F");
        MapNode node7 = new MapNode(6, "G");
        MapNode node8 = new MapNode(7, "H");
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
                {0, 15, max, max, max, max, 8, 2},
                {15, 0, 13, max, max, max, max, 10},
                {max, 13, 0, 10, max, max, max, 11},
                {max, max, 10, 0, 2, max, max, 8},
                {max, max, max, 2, 0, 7, max, 6},
                {max, max, max, max, 7, 0, 9, 20},
                {8, max, max, max, max, 9, 0, 13},
                {2, 10, 11, 8, 6, 20, 13, 0}};

        kruskar(mapNodeSet,distance);

    }

    //克鲁斯卡尔算法
    public static MapNode kruskar(Set<MapNode> mapNodeSet,int[][] distance){
        //创建一个集合用来存储已连接的点  下面List中的泛型List<MapNode>可以理解为部落
        List<List<MapNode>> linkedMapNodes = new ArrayList<>();

        //用循环来想连接的点的集合里添加点，直到所有的点全部都添加到了linkedMapNodes集合里
        while(!(linkedMapNodes.size() == 1 && linkedMapNodes.get(0).size() == mapNodeSet.size())){
            getMinDis(mapNodeSet,distance,linkedMapNodes);
            System.out.println("linkedMapNodes.size :"+linkedMapNodes.size()+"---"+"mapNodeSet.size:"+mapNodeSet.size());
        }
        //这里暂且先返回第一个连接的点
        return linkedMapNodes.get(0).get(0);
    }

    //找寻距离最小的点，并将它们连接起来，放入linkedMapNodes中
    public static void getMinDis(Set<MapNode> mapNodeSet,int[][] distance,List<List<MapNode>> linkedMapNodes){
        MapNode begin = null;
        MapNode end = null;
        List<MapNode> beginList = null;
        List<MapNode> endList = null;
        int minDis = Integer.MAX_VALUE;
        //循环遍历找到distance中的最小距离,并将给上面的变量赋值
        for(int i = 0; i < distance.length; i ++){
            for(int j = 0; j < distance[i].length; j ++){
                if(distance[i][j] < minDis && i != j){//i != j表示不能自己和自己连接
                    MapNode a = getMapNodeByIndex(mapNodeSet,i);
                    MapNode b = getMapNodeByIndex(mapNodeSet,j);
                    //在判断这两个节点，是否都为未连接的点，或者其中有一个是已连接的的，或两个来自与不同的部落
                    // 即只要这两个节点不是来自于同一个部落，就可以连接
                    List<MapNode> aMapNodeList = getMapNodeList(linkedMapNodes,a);
                    List<MapNode> bMapNodeList = getMapNodeList(linkedMapNodes,b);

                    if(!(aMapNodeList != null && bMapNodeList !=null && aMapNodeList == bMapNodeList)){
                        //aMapNodeList == bMapNodeList 表示a ，b节点来自于同一个部落
                        minDis = distance[i][j];//当内层的循环结束时，minDis一定是最小的
                        begin = a;
                        end = b;
                        beginList = aMapNodeList;
                        endList = bMapNodeList;
                    }
                }
            }
        }

        //将两个节点连接起来
        begin.neighborNode.add(end);
        end.neighborNode.add(begin);
        System.out.println("此次连接的点是："+begin.val+","+end.val);
        System.out.println("minDis = "+minDis);

        //将连接好的点形成的部落放入已连接的点的即合里
        if(beginList == null && endList == null){
            //表示两个点均是未连接的点
            List<MapNode> mapNodeList = new ArrayList<>();
            mapNodeList.add(begin);
            mapNodeList.add(end);
            linkedMapNodes.add(mapNodeList);
        }else if(beginList != null && endList == null){
            //表示两个点中有一个是未连接的点
            beginList.add(end);
        }else if(beginList == null && endList != null){
            //表示两个点中有一个是未连接的点
            endList.add(begin);
//            linkedMapNodes.add(endList);             //需要注意此处的endList已经添加到了linkedMapNodes 故此处无需再次添加
        }else if(beginList != null && endList != null){
            //表示两个点是来自两字不同部落的点
            beginList.addAll(endList);
//            linkedMapNodes.add(beginList);            //需要注意此处的beginList已经添加到了linkedMapNodes 故此处无需再次添加
            //将endList从已连接好的点的集合里删除
            linkedMapNodes.remove(endList);
        }
    }

    //设计一个方法，通过mapNode的index属性找到相对应的mapNode
    public static MapNode getMapNodeByIndex(Set<MapNode> mapNodeSet,int index){
        for(MapNode mapNode : mapNodeSet){
            if(mapNode.index == index) return mapNode;
        }
        return null;
    }

    //设计一个方法，根据mapNode节点找到它对应的部落
    public static List<MapNode> getMapNodeList(List<List<MapNode>> linkedMapNodes,MapNode mapNode){
        for(List<MapNode> nodeList : linkedMapNodes){
            if(nodeList.contains(mapNode)) return nodeList;
        }
        return  null;
    }

}
