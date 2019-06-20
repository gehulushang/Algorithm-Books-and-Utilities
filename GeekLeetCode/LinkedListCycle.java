package GeekLeetCode;

import java.util.*;

public class LinkedListCycle {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public boolean hasCycle(ListNode head) {
        Map<ListNode,Integer> res = new HashMap<>();
        if(head==null||head.next==null){
            return false;
        }
        ListNode cur = head;

        while(cur!=null){
            if(res.containsKey(cur)&&res.get(cur)==cur.val){
                return true;
            }
            res.put(cur,cur.val);
            cur = cur.next;
        }
        return false;
    }

}
