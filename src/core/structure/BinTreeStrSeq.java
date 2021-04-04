package core.structure;

import core.been.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 描述：给定一个二叉树，返回该二叉树层序遍历的结果，（从左到右，一层一层地遍历）
 *
 * https://www.nowcoder.com/practice/04a5560e43e24e9db4595865dc9c63a3?tpId=117&&tqId=34936&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 */
public class BinTreeStrSeq {
    /**
     * @param root TreeNode类
     * @return int整型ArrayList<ArrayList <>>
     */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder (TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Queue<TreeNode> queue  = new LinkedList<>();
        queue.add(root);
        boolean left2Right = false; // true 从左向右遍历
        while(!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while(size>0){
                TreeNode node = queue.poll();
                if (left2Right){
                    list.add(node.val);
                }else {
                    list.add(0,node.val);
                }
                // 新的一层入栈
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            left2Right = !left2Right; // 切换状态
            result.add(list);
        }
        return result;
    }
}
