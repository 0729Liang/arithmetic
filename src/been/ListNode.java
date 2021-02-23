package been;

public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    /**
     * 描述:
     */
    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(val);
        ListNode node = next;
        while (node != null) {
            sb.append(" -> ").append(node.val);
            node = node.next;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

}
