package sort1108;

public class Test {
    public static void main(String[] args){
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

        midRoot(node1);

    }

    //先根次序遍历(前序遍历) 结果
    public static void firstRoot(TreeNode node){
        if(node == null) return;
        System.out.println(node.val);
        firstRoot(node.left);
        firstRoot(node.right);
    }


    //中根次序遍历(中序遍历)
    public static void midRoot(TreeNode node){
        if(node == null) return ;
        midRoot(node.left);
        System.out.println(node.val);
        midRoot(node.right);
    }

    //后根次序遍历(后序遍历)
    public static void lastRoot(TreeNode node){
        if(node == null) return ;
        lastRoot(node.left);
        lastRoot(node.right);
        System.out.println(node.val);
    }
}
