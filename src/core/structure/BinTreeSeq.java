package core.structure;

import core.been.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 描述：给定一个二叉树，返回该二叉树层序遍历的结果，（从左到右，一层一层地遍历）
 * <p>
 * https://www.nowcoder.com/practice/04a5560e43e24e9db4595865dc9c63a3?tpId=117&&tqId=34936&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 */
public class BinTreeSeq {
    /**
     * @param root TreeNode类，不打印空值
     * @return int整型ArrayList<ArrayList <>>
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            int size = stack.size();
            ArrayList<Integer> list = new ArrayList<>();
            // 遍历一层
            while (size > 0) {
                TreeNode node = stack.pollFirst();
                list.add(node.val);
                // 新的一层入栈
                if (node.left != null) {
                    stack.add(node.left);
                }
                if (node.right != null) {
                    stack.add(node.right);
                }
                size--;
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 描述：打印空值遍历
     */
    public ArrayList<ArrayList<Integer>> levelOrder2(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);

        int length = getLength(root);
        while (!stack.isEmpty() && length > 0) {
            int size = stack.size();
            ArrayList<Integer> list = new ArrayList<>();
            // 遍历一层
            while (size > 0) {
                TreeNode node = stack.pollFirst();
                if (node == null) {
                    list.add(null);
                } else {
                    list.add(node.val);
                }
                size--;
                stack.add(node == null?null:node.left);
                stack.add(node == null?null:node.right);
            }
            result.add(list);
            length--;
        }
        return result;
    }


    public int getLength(TreeNode root){
        // 如果当前节点为空，说明已经到达最底层
        if (root == null){
            return 0;
        }
        int left=getLength(root.left);
        int right=getLength(root.right);
        // 返回左右节点的深度的最大值，再加上自身的一层就是当前节点的深度
        return Math.max(left,right)+1;
    }
}
