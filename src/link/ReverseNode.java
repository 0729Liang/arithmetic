package link;

import been.ListNode;
import been.TreeNode;

/**
 * 描述：输入一个链表，反转链表后，输出新链表的表头。
 * 解题思路：将ListNode.next的作用改为pre
 * 即遍历当前指针，取到的每个对象，将next指向前一项
 * 如果是第一项，则next指向null,
 * 从第二项开始，每个的next将指为上一个对象
 * 直到取到最后一项的next为null,则他为新的head
 *
 * https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca?tpId=117&&tqId=35000&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 */
public class ReverseNode {
    public ListNode ReverseList(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode newHead = null;
        ListNode curNode = head;
        ListNode preNode = null;

        while(curNode != null){
            ListNode node = curNode.next;
            if (node == null){
                newHead = curNode;
            }
            curNode.next = preNode;
            preNode = curNode;
            curNode = node;
        }
        return newHead;
    }

    public static void main(String[] args) {
        int[] pres = new int[]{3,9,20,15,7};
        int[] ins = new int[]{9,3,15,20,7};

        System.out.println(new ReverseNode().buildTree(pres,ins));
    }

    int pre = 0;
    int in = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (pre >= preorder.length)
            return null;
        System.out.println("tops="+stop);
        if (inorder[in] == stop) {
            System.out.println("in="+in+" stop="+stop);
            in++;
            return null;
        }

        TreeNode node = new TreeNode(preorder[pre++]);
        node.left = build(preorder, inorder, node.val);
        node.right = build(preorder, inorder, stop);
        return node;
    }
}

