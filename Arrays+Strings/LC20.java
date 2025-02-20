import java.util.Stack;

public class LC20 {

    //https://leetcode.com/problems/valid-parentheses/description/
    // String: valid parenthesis

        // TC: O(n)
    // SC: O(n)
    public boolean isValid(String s) {

        Stack<Character> st = new Stack<>();
        int n = s.length();
        
        for(int i=0; i<n; i++) {

            // if bracket is open
            if(s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') st.push(s.charAt(i));

            // if bracket is closed, we check if it's a pair
            else {
                if(st.isEmpty()) return false;

                if(s.charAt(i) == ')' && st.peek() == '(' ||
                   s.charAt(i) == '}' && st.peek() == '{' ||
                   s.charAt(i) == ']' && st.peek() == '[') {

                    st.pop();   
                } else return false;
            }
        } 

        return st.isEmpty()?true:false;        
        
    }
    
}
