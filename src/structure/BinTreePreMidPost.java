package structure;

import been.TreeNode;

/**
 * 描述：分别按照二叉树先序，中序和后序打印所有的节点。
 * 二叉树的前中后续遍历方式
 * 前序：根左右
 * 中序：左根右
 * 后序：左右根
 *
 * https://www.nowcoder.com/practice/a9fec6c46a684ad5a3abd4e365a9d362?tpId=117&&tqId=35075&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 */
public class BinTreePreMidPost {
    /**
     *
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */

    private int preIndex = 0;
    private int midIndex = 0;
    private int postIndex = 0;

    public int[][] threeOrders (TreeNode root) {
        int[][] arrays = new int[3][getNodeSize(root)];
        preOrder(root,arrays);
        midOrder(root,arrays);
        postOrder(root,arrays);
        return arrays;
    }

    private int getNodeSize(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1+getNodeSize(root.left)+getNodeSize(root.right);
    }

    // 前序：根左右
    private void preOrder(TreeNode root,int[][] arrays){
        if (root == null){
            return;
        }
        arrays[0][preIndex++] = root.val;
        preOrder(root.left,arrays);
        preOrder(root.right,arrays);
    }

    // 中序：左根右
    private void midOrder(TreeNode root,int[][] arrays){
        if (root == null){
            return;
        }
        midOrder(root.left,arrays);
        arrays[1][midIndex++] = root.val;
        midOrder(root.right,arrays);
    }

    // 后序：左右根
    private void postOrder(TreeNode root, int[][] arrays){
        if (root == null){
            return;
        }
        postOrder(root.left,arrays);
        postOrder(root.right,arrays);
        arrays[2][postIndex++] = root.val;
    }

}