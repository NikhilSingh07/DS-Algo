// https://leetcode.com/problems/odd-even-linked-list/
// Odd and even list pointers
// Medium

public class LC328 {

    // approach 1: using extra space: 
    // store odd indices first in an array, then even indices
    // place them back in the LL from the array.
    // TC: O(2n)
    // SC: O(n)
/*
    public ListNode oddEvenList(ListNode head) {

        if(head == null || head.next == null) {
            return head;
        }
        ListNode curr = head;
        List<Integer> ans = new ArrayList<>();

        // stoing the odd indices: O(n/2)
        while(curr!=null && curr.next!=null) {
            ans.add(curr.val);
            curr  = curr.next.next;
        }   

        // it won't add the last element in the array
        if(curr!=null) {
            ans.add(curr.val);
        }

        // moving curr to even index
        curr = head.next;

        // stoing the even indices: O(n/2)
        while(curr!=null && curr.next!=null) {
            ans.add(curr.val);
            curr = curr.next.next;
        } 

        // it won't add the last element in the array
        if(curr!=null) {
            ans.add(curr.val);
        }

        curr = head;
        int ind =0;

        while(curr!=null) {

            curr.val = ans.get(ind++);
            curr = curr.next;
        }

        return head;

    }

*/
// approach 2: Using even and odd indices pointers
// making to seperate list: odd list and even list:
// then connecting both lists together
// TC: O(n)
// SC: O(1)


public ListNode oddEvenList(ListNode head) {

    if(head == null || head.next == null) {
        return head;
    }

    ListNode odd = head;
    ListNode even = head.next;
    ListNode epHead = head.next; // to connect the odd list with the even list

    while(even!=null && even.next!=null) { // even will always be ahead of odd

         odd.next = odd.next.next; // odd pointing to the next odd index
         odd = odd.next;

         even.next = even.next.next;
         even = even.next;
    }

    // connect the odd list with the even list.
    odd.next = epHead; 
    // tadaaa! list is reordered

    return head;

}
    
}
