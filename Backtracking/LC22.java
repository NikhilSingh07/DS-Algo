import java.util.ArrayList;
import java.util.List;

public class LC22 {

// https://leetcode.com/problems/generate-parentheses/description/
// Genearting all valid combination of parentheses
// Medium

    // There are 2 choices at each step; either you open a bracket or you close it.
    // if (open > close): then only it can be closed
    // if(open < n): then can only be opened; can't open more than n brackets.
    // TC: O(2**n): we have branches: which doesn't always double all the time. butroughly TC would be 2**n
    // SC: 2*n (depth of the recursion tree)
    
    public void getParentheses(int n, List<String> ans,  StringBuilder sb, int opened, int closed) {

        // base case
        if(sb.length() == 2*n) {

            ans.add(sb.toString());
            return;
        }

        // can only open backets equal to n
        if(opened < n) {
            sb.append('(');
            getParentheses(n, ans, sb, opened+1, closed);
            sb.deleteCharAt(sb.length()-1);
        }

        // can only close a backet if it already has been opened
        if(opened > closed) {
            sb.append(')');
            getParentheses(n, ans, sb, opened, closed+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public List<String> generateParenthesis(int n) {

        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int opened = 0;
        int closed = 0;

        getParentheses(n, ans, sb, opened, closed);

        return ans;
    }
    
}
