package link;

import been.ListNode;

public class ReverseNodeGroup {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode next = head;
        ListNode tmp = null;
        for (int i = 2; i <= 5; i++) {
            next.next = new ListNode(i);
            next = next.next;
            if (i == 3) {
                tmp = next;
            }
        }

        head.print();
        ReverseNodeGroup reverseNodeGroup = new ReverseNodeGroup();
        ListNode reverse = reverseNodeGroup.reverse(head, tmp);
        tmp.print();
        reverse.print();

    }

    /**
     * @param head ListNode类
     * @param k    int整型
     * @return ListNode类
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode nextNode = head;
        int length = length(nextNode);

        if (head == null) return null;
        if (k <= 0 || k > length) return head;
        int reversal = length - (length % k);  //链表中有多少个节点参与运转。
        int position = 0;
        ListNode newHead = new ListNode(0);
        ListNode result = newHead;
        ListNode tmp = null;
        for (position = 0; position < reversal; ) {
            ListNode preNode = null;
            for (int i = 0; i < k; i++) {
                tmp = nextNode.next;
                nextNode.next = preNode;
                preNode = nextNode;
                nextNode = tmp;
                position++;
            }
            newHead.next = preNode;
            for (int j = 0; j < k; j++) {
                newHead = newHead.next;
            }
        }
        newHead.next = nextNode;
        return result.next;
    }

    /*
    获取链表的长度。
    */
    public int length(ListNode node) {
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    /**
     * 描述：翻转部分链表[startNode,endNode]
     */
    private ListNode reverse(ListNode startNode, ListNode endNode) {
        ListNode node = startNode;
        ListNode pre = null;
        while (node != null) {
            ListNode tmp = node.next;
            node.next = pre;
            pre = node;
            node = tmp;
            if (pre == endNode) {
                break;
            }
        }
        return pre;
    }

    /**
     * 描述：获取大小
     */
    private int getSize(ListNode node) {
        int size = 0;
        while (node != null) {
            size++;
            node = node.next;
        }
        return size;
    }
}
