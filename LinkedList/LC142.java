public class LC142 {


    //https://leetcode.com/problems/linked-list-cycle-ii/
    // starting node of the LL loop
/* 
    TC: O(n+m), SC: constant.

       1. Calculated intersected node using slow and fast pointers (floyd's cycle detection algorithm)
       2. Now, distance between the head node and cycle's head node will be same from the intersected node.   [df= 2ds]- > X+2Y+Z = 2X + 2Y => X = Z
       3. update the slow pointer to head and jump slow and fast pointers 1 node at a time until they meet again (at cycle's head node).

 */ 
    public ListNode detectCycle(ListNode head) {

        // if there is either 1 node or none.
        if(head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Calculating the point of intersection.
        while(fast!=null && fast.next!=null) {

             slow = slow.next;
             fast = fast.next.next;

             if(slow == fast) 
             break;
        }
        
        if(slow!=fast) return null;

        // reseting the slow pointer to head. 
        slow = head;

        // Slow is iterating from head and fast is iterating from the intersected node. 
        // Jumping one node at a time until they meet again at the cycle's head node.
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }

        //slow and fast pointers are pointing at the cycle's head.
        return slow;
    }


    
}
