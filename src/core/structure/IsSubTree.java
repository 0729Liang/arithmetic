package core.structure;

import core.been.TreeNode;

/**
 * 描述：
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * <p>
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
 */
public class IsSubTree {

    /**
     * 步骤：先判断A和B是否为空，有一个为空，就返回false;
     * 以下三者满足一个即可返回true;
     * 把A和B传入hepler进行判断;
     * 验证B是不是A的左子树；
     * 验证B是不是A的右子树；
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {// 当 树 A 为空 或 树 B 为空 时，直接返回 false 
            return false;
        }
        // 以 节点 A 为根节点的子树 包含树 B ，对应 helper(A, B)；
        // 树 B 是 树 A 左子树 的子结构，对应 isSubStructure(A.left, B)；
        // 树 B 是 树 A 右子树 的子结构，对应 isSubStructure(A.right, B)；
        return helper(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    public boolean helper(TreeNode A, TreeNode B) {
        /**
         * 描述：终止条件
         * 当节点 B 为空：说明树 B 已遍历完成（越过叶子节点），因此返回 true ；
         * 当节点 A 为空：说明已经越过树 A 叶子节点，即匹配失败，返回 false ；
         * 当节点 A 和 B 的值不同：说明匹配失败，返回 false ；
         */
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        if (A.val != B.val) {
            return false;
        }

        //判断 A 和 B 的左子节点是否相等，即 helper(A.left, B.left) ；
        //判断 A 和 B 的右子节点是否相等，即 helper(A.right, B.right) ；
        return helper(A.left, B.left) && helper(A.right, B.right);
    }
}
