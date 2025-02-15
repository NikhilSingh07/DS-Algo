import java.util.function.Function;
// https://www.geeksforgeeks.org/problems/find-length-of-loop/1

public class GFGfindLoopLen {
    
// approach 1: using HashMap to find the loop and keep track on the number of steps to reach a node twice.
//           ex: 1st time: 2 steps
//               2nd time: 6 steps
// len = 6-2 = 4
// TC: O(n) + O(2*logn)[for map operation in case of collision]
// SC: O(n)

// approach 2: using slow and fast pointers: Tortoise and Hare algorithm
// TC: O(n)
// SC: O(1)
class Solution {
    // Function to find the length of a loop in the linked list.
    
    private int countLength(Node slow, Node fast) {
        
        int count =1;
        fast = fast.next;
        
        while(slow!=fast) {
            count++;
            fast = fast.next;
        }
        
        return count;
    }
    
    public int countNodesinLoop(Node head) {
        // Add your code here.
        
        Node slow = head;
        Node fast = head;

        while(fast!=null && fast.next!=null) { // for linear LL
        
                    
            slow = slow.next;
            fast = fast.next.next;
            
            
            if(slow == fast) {
                return countLength(slow, fast);
            }

        }
        
        return 0;
    }
}
