package core.structure;

import core.been.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：剑指 Offer 07. 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * <p>
 * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
 */
public class RebuildBinTree {

    public static void main(String[] args) {
        int[] pres = new int[]{3,9,20,15,7};
        int[] ins = new int[]{9,3,15,20,7};

        TreeNode root = new RebuildBinTree().buildTree(pres, ins);
        System.out.println(new BinTreeSeq().levelOrder(root));
        System.out.println(new BinTreeSeq().levelOrder2(root));
    }
    Map<Integer, Integer> inMap = new HashMap<>();// 记录中序遍历的数据，k=数组中具体的值，v=数组中此值的索引

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 记录中序遍历的kv
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        int length = preorder.length;
        return build(preorder,inorder,0, length-1,0,length-1);
    }

    public TreeNode build(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }

        // 1. 前序遍历的第一个位置就是根节点
        int preRoot = preLeft;
        int rootVal = preorder[preRoot];
        // 2. 找到中序遍历中根节点的位置
        int inRoot = inMap.get(rootVal);
        // 3. 从中序遍历中计算出左子树中的节点数目
        int leftTreeSize = inRoot - inLeft;
        // 4. 构建根节点
        TreeNode root = new TreeNode(rootVal);
        // 5. 递归左子树: 先序遍历中「从 左边界+1 开始的 leftTreeSize」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = build(preorder, inorder, preLeft + 1, preLeft+leftTreeSize, inLeft, inRoot - 1);
        // 6. 递归右子树: 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = build(preorder, inorder, preLeft + leftTreeSize + 1, preRight, inRoot + 1, inRight);
        return root;
    }

}
