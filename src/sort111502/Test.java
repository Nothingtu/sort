package sort111502;

import sort1108.TreeNode;

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

        TreeNode[] nodes = {node1, node2, node3, node4, node5, node6, node7, node8};
        TreeNode root = null;

        for (int i = 0; i < nodes.length; i++) {
            int ran = (int) (Math.random() * 8);
            root = buildSortTreeNode(root, nodes[ran]);
        }

        boolean t = isExit(root,node3);
        System.out.println(flag);
        System.out.println(t);
        System.out.println(root);
    }


    //构建二叉排序树
    public static TreeNode buildSortTreeNode(TreeNode root, TreeNode node) {
        if (root == null) return node;
        if (node == null || root.val == node.val) return root;
        if (root.val > node.val) root.left = buildSortTreeNode(root.left, node);
        else root.right = buildSortTreeNode(root.right, node);
        return root;
    }

    private static int flag = 0;

    //判断排序树是否存在某一给定的节点
    public static boolean isExit(TreeNode root, TreeNode node) {
        flag++;
        if (root == null || node == null) return false;
        //遍历每一个节点，与node进行比较
        boolean result = false ;
        if(root.val == node.val) result = true;

        else if(root.val > node.val)  result =  isExit(root.left,node);
        else if(root.val < node.val)  result =  isExit(root.right,node);

//        //上面被注释的两行等价于下面的这一行
//        result = isExit(root.left,node) || isExit(root.right,node);
        return result;
    }

    //判断排序树是否存在某一给定的数
    public static boolean isExit(TreeNode root ,int val){
        return isExit(root,new TreeNode(val));
    }
}
