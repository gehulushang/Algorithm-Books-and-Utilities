package GeekLeetCode;




public class ReverseLinkedList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head){
        ListNode curNode = head;
        ListNode newNode = null;
        ListNode newNextNode = null;
        while(curNode!=null){
            newNextNode = new ListNode(curNode.val);
            newNextNode.next = newNode;
            newNode = newNextNode;
            curNode = curNode.next;
        }
        return newNextNode;
    }


}
