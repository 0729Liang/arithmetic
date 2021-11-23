package core.utils;

import core.been.ListNode;

public class Utils {

    public static <T> void printArray(T[] s){
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i]+" ");
        }
        System.out.println();
    }

    public static  void printArray(char[] s){
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i]+" ");
        }
        System.out.println();
    }

    public static  void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void printListNode(ListNode head){
        while(head!=null){
            System.out.print(head.val+" ");
            head = head.next;
        }
        System.out.println("");
    }
    public static ListNode createListNode(int[] arr){
        ListNode head = new ListNode(-1);
        ListNode node = head;
        for (int num :arr) {
            node.next = new ListNode(num);
            node = node.next;
        }
        printListNode(head.next);
        return head.next;
    }


}
