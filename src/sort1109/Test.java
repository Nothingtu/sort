package sort1109;

import sort1108.TreeNode;

import java.util.Arrays;

/**
 * 还原二叉树
 */
public class Test {
    public static void main(String[] args){
//        int[] front = {1,2,4,5,3,6};
        int[] centre = {4,2,5,1,6,3};
        int[] back = {4,5,2,6,3,1};

//        int[] t = Arrays.copyOfRange(front,0,4);
//        System.out.println(t.length);
        TreeNode root = builderTreeNodeByBackAndCentre(centre,back);
        System.out.println(root);

    }

    //根据前序和中序还原二叉树
    public  static TreeNode builderTreeNodeByFrontAndCentre(int[] front,int[] centre){
       if(front == null || centre == null || front.length != centre.length || front.length == 0 || centre.length == 0) return null;

       //根据前序数组得到根节点root并创建根节点
        int rootValue = front[0];
        TreeNode root = new TreeNode(rootValue);

        //根据根节点的值，找到根节点在中序中对应的索引位置
        int index = getIndex(centre,rootValue);

        //关于Arrays.copyOfRange()方法是一个左闭右开的取值范围
        int[] leftCentre = Arrays.copyOfRange(centre,0,index);
        int[] leftFront = Arrays.copyOfRange(front,1,index + 1);

        int[] rightCentre = Arrays.copyOfRange(centre,index + 1,centre.length);
        int[] rightFront = Arrays.copyOfRange(front,index + 1,front.length);

        TreeNode left = builderTreeNodeByFrontAndCentre(leftFront,leftCentre);
        TreeNode right = builderTreeNodeByFrontAndCentre(rightFront,rightCentre);

        root.left = left;
        root.right = right;
        return root;
    }

    //根据中序和后序还原二叉树
    public  static TreeNode builderTreeNodeByBackAndCentre(int[] centre,int[] back){
        if(centre == null || back == null || centre.length == 0 || back.length == 0 || centre.length != back.length) return null;

        TreeNode root = new TreeNode(back[back.length- 1]);

        //获取根节点在中序中的索引位置
        int index = getIndex(centre,root.val);

        int[] leftBack = Arrays.copyOfRange(back,0,index);
        int[] leftCentre = Arrays.copyOfRange(centre,0,index);

        int[] rightBack = Arrays.copyOfRange(back,index,back.length - 1);
        int[] rightCentre = Arrays.copyOfRange(centre,index +1 ,centre.length);

        TreeNode left = builderTreeNodeByBackAndCentre(leftCentre,leftBack);
        TreeNode right = builderTreeNodeByBackAndCentre(rightCentre,rightBack);

        root.left = left;
        root.right = right;
        return root;
    }

    //根据给定的值，找寻在数组中对应的位置
    public static int getIndex(int[] arr,int value){
        for(int i = 0; i < arr.length;i ++){
            if(arr[i] == value) return i;
        }
        return 0;
    }

}
