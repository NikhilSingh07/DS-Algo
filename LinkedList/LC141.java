public class LC141 {

    // https://leetcode.com/problems/linked-list-cycle/description/
    // Detecting cycle in a LinkedList

    /**
       Approach 1: Use a HashSet data structure: TC: O(n), SC: O(n)

       Approach 2: Floyd's cycle detection algorithm -> tortoise and hare algorithm using slow and fast pointers

 */
public class Solution {

    // Approach 1: HashSet

   /* public boolean hasCycle(ListNode head) {

        Set<ListNode> set = new HashSet<ListNode>();
        ListNode current = head;

        while(current!=null){

            if(!set.add(current)) return true;

            current = current.next;
        }
        
        return false;
    }*/

    // Approach 2: Tortoise and hare approach.

    public boolean hasCycle(ListNode head) {

        if(head == null || head.next == null) return false;


        ListNode slow = head;
        ListNode fast = head;


        while(fast!=null && fast.next!=null) { // incase we have a linear linkedlist

            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) return true;
        }

        return false;
    }

    
}
