package core.tree;

import core.been.TreeNode;
import core.structure.RebuildBinTree;

import java.util.*;

/**
 * 描述：给定一个二叉树，返回该二叉树层序遍历的结果，（从左到右，一层一层地遍历）
 * <p>
 * https://www.nowcoder.com/practice/04a5560e43e24e9db4595865dc9c63a3?tpId=117&&tqId=34936&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 */
public class BinTreeSeq {
    public static void main(String[] args) {
        int[] pres = new int[]{3, 9, 20, 15, 7};
        int[] ins = new int[]{9, 3, 15, 20, 7};

        TreeNode root = new RebuildBinTree().buildTree(pres, ins);
        System.out.println(new BinTreeSeq().levelOrder(root));
        System.out.println(new BinTreeSeq().levelOrderContainNull(root));
        System.out.println(getTreeNodeDeep(root));
    }

    /**
     * 描述：层序遍历，不打印空值
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
        // 此题不能返回 null
        if (root == null) {
            return resList;
        }
        Queue<TreeNode> quque = new LinkedList<>();
        quque.offer(root);
        // 通过队列层序遍历二叉树
        while (!quque.isEmpty()) {
            int size = quque.size();
            List<Integer> tmpList = new ArrayList<>();
            // 遍历当前层数
            while (size > 0) {
                TreeNode node = quque.poll();
                tmpList.add(node.val);
                if (node.left != null) {
                    quque.offer(node.left);
                }
                if (node.right != null) {
                    quque.offer(node.right);
                }
                size--;
            }
            resList.add(tmpList);
        }
        return resList;
    }

    /**
     * 描述：打印空值遍历
     */
    public ArrayList<ArrayList<Integer>> levelOrderContainNull(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 通过控制遍历结束
        int length = getTreeNodeDeep(root);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty() && length > 0) {
            int size = queue.size();
            ArrayList<Integer> tmpList = new ArrayList<>();
            // 遍历一层
            while (size > 0) {
                TreeNode node = queue.poll();
                tmpList.add(node == null ? null : node.val);
                queue.offer(node == null ? null : node.left);
                queue.offer(node == null ? null : node.right);
                size--;
            }
            result.add(tmpList);
            length--;
        }
        return result;
    }

    /**
     * 描述：返回二叉树的深度
     */
    public static int getTreeNodeDeep(TreeNode root) {
        // 如果当前节点为空，说明已经到达最底层
        if (root == null) {
            return 0;
        }
        int left = getTreeNodeDeep(root.left);
        int right = getTreeNodeDeep(root.right);
        // 返回左右节点的深度的最大值，再加上自身的一层就是当前节点的深度
        return Math.max(left, right) + 1;
    }

    /**
     * 描述：返回二叉树的节点数
     */
    private int getTreeNodeSize(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1+ getTreeNodeSize(root.left)+ getTreeNodeSize(root.right);
    }
}
