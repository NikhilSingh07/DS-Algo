/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */


public class LC2095 {

    // https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/
    // deleting middle node in a linkedlist
    // fast and slow pointers

    // slow and fast pointers:
// initially slow pointer will be pointing to the trail node. 
// TC: O(n)
public ListNode deleteMiddle(ListNode head) {
        

    if(head == null || head.next ==null) {
        return null;
    } 

    ListNode trail = new ListNode(0, head);
    ListNode slow = trail;
    ListNode fast = head;

    while(fast!=null && fast.next!=null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    slow.next = slow.next.next;

    return trail.next;
}
    
}
