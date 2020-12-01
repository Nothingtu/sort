package sort110902;

import sort1108.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的深度优先搜索和广度优先搜索
 */
public class Test {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;


        List list = new  ArrayList<TreeNode>();
        list.add(node1);
        System.out.println(bfs(list,8));
    }

    //二叉树的深度优先搜索Depth first search
    public static boolean dfs(TreeNode node, int target){
        if(node == null) return false;
        if(node.val == target) return true;
        return dfs(node.left,target) || dfs(node.right,target);
    }


    //二叉树的广度优先搜索Breadth-First Search
    public static boolean bfs(List<TreeNode> nodeList,int target){
        if(nodeList == null || nodeList.size() == 1) return false;
        List<TreeNode> list;
        boolean result = true;
        for(TreeNode snode : nodeList){
            if(snode == null) result = false;
            else if(snode.val == target){
                result = true;
            }else{
                list = new ArrayList<TreeNode>();
                if(snode.left != null) list.add(snode.left);
                if(snode.right != null) list.add(snode.right);
                result = bfs(list,target);
            }
        }
        return  result;
    }

    public static boolean bfs2(List<TreeNode> nodeList,int target){
        if(nodeList == null || nodeList.size() == 1) return false;
        List<TreeNode> list = null;
        for(TreeNode snode : nodeList){
            if(snode.val == target){
                return true;
            }
            list  = new ArrayList<TreeNode>();
            if(snode.left != null) list.add(snode.left);
            if(snode.right != null) list.add(snode.right);
        }
        return bfs2(list,target);
    }
}
