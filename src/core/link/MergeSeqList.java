package core.link;

import core.been.ListNode;

import java.util.List;

/**
 * 描述：题目描述
 * 将两个有序的链表合并为一个新链表，要求新的链表是通过拼接两个链表的节点来生成的，且合并后新链表依然有序。
 */
public class MergeSeqList {

        /**
         * @param l1 ListNode类
         * @param l2 ListNode类
         * @return ListNode类
         */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode newList = head;
        while(l1 != null && l2 != null){
            // 找出l1 和 l2中较小的node，newList指向此节点
            if(l1.val > l2.val){
                newList.next = l2;
                l2 = l2.next;
            }else{
                newList.next = l1;
                l1 = l1.next;
            }
            newList = newList.next; // 更新newList
        }
        // 注意：最后一轮l1 或 l2中将出现一个null和node,newList指向最后一个node
        newList.next = (l1 == null?l2:l1);
        return head.next;
    }
}
