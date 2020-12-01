package sort111503;

import sort1108.TreeNode;

public class Test {
    //旋转 rotate

    public static void main(String[] args){

    }

    //讲一个排序树改变成平衡二叉树
    public static TreeNode change(TreeNode root){
        if(root == null) return null;
        //先判断该二叉树是否是平衡二叉树
        if(isBalance(root)) return root;

        return null;
    }

    //判断该二叉树是否是平衡二叉树
    private static boolean isBalance(TreeNode root){
        if(root == null) return true;//暂且把空也当作平衡二叉树
        int leftDeep = getDeep(root.left);
        int rightDeep = getDeep(root.right);
        if(Math.abs(leftDeep - rightDeep) == 1) return true;
        return false;
    }

    //获取二叉树的深度
    private static int getDeep(TreeNode root){
        if(root == null) return 0;
        int leftDeep = getDeep(root.left);
        int rightDeep = getDeep(root.right);
        return  Math.max(leftDeep,rightDeep) + 1;
    }
}
