//https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/description/
// Get Middle (slow and fast pointers) + Reverse LL
// Medium

public class LC2130 {

// approach 1: use array to store the values and use 2 pointers to calculaye max sum
// TC: O(2n)
// SC: O(n) 

// approach 2: use stack to store half of the elements.
// Iterate the second half of the linkedList and check max sum by popping elements from stack + curr.val;
// TC: O(n)
// SC: O(n/2)

/*    
    public int pairSum(ListNode head) {

        List<Integer> arr = new ArrayList<>();

        ListNode curr = head;

        while(curr!=null) {
            arr.add(curr.val);
            curr = curr.next;
        }

        int s = 0;
        int e = arr.size()-1;
        int maxSum = 0;
        int sum =0;

        while(s<e) {
            sum = arr.get(s) + arr.get(e);
            maxSum = Math.max(maxSum, sum);
            s++;
            e--;
        }

        return maxSum;
    }*/

// Optimal Solution: update the input linkedList
// either reverse the second half or the first half of the LL
// Rest is just adding and checking values using pointers.

private ListNode getMiddle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while(fast!=null) {
        fast = fast.next.next;
        slow = slow.next;
    }

    return slow;
}
 
private ListNode reverseFirstHalf(ListNode head, ListNode m) {
    //   x<-5 <- 4  2
    //           p  c   n
    ListNode prev = null;
    ListNode curr = head;
    ListNode nxt = null;

    while(curr!=m) {

        nxt = curr.next;
        curr.next = prev;
        prev = curr;
        curr =nxt;
    }

    return prev;
}

public int pairSum(ListNode head) {

    // get the middle of the LL
    ListNode m = getMiddle(head);  // O(n/2)

    // reversing the first half of the LL
    ListNode curr = reverseFirstHalf(head, m); // O(n/2)
    
    int sum = 0;
    int maxSum = 0;

    // traverse the second half and calculte the sum usign reversed first Half
    while(m!=null ) { // O(n/2)

        sum = curr.val+m.val;
        maxSum = Math.max(maxSum, sum);

        curr = curr.next;
        m = m.next;
    }

    return maxSum;
}
    
}
