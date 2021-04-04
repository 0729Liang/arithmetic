package core.link;

import core.been.ListNode;
import core.been.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 描述：断给定的链表中是否有环。如果有环则返回true，否则返回false。
 * 链表有环思路：如果有环，设置一个快指针，设置一个慢指针，
 * 指针一次走两步，慢指针一次走一步，快指针总能追上慢的
 * https://www.nowcoder.com/practice/650474f313294468a4ded3ce0f7898b9?tpId=117&&tqId=34925&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 */
public class HasCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null){
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow){
                return true;
            }
        }

        return false;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            int size = stack.size();
            List<Integer> list = new ArrayList<>();
            while(size > 0){
                TreeNode node = stack.pollFirst();
                list.add(node.val);
                if (node.left!=null){
                    stack.add(node.left);
                }
                if (node.right!=null){
                    stack.add(node.right);
                }
                size--;
            }
            result.add(0,list);
        }
        return result;
    }
}
