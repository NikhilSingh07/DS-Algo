public class LC151 {


        // approach 1: using stack to words
    // TC: O(n) + O(n), extracting words and rebuilding the string
    // SC: O(n)

    // approach 2:using Regex to trim down the multiple space and split the string
    /*
    public String reverseWords(String s) {
        

        // first intuition is to use a stack to store words seperated by space
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        Stack<String> st = new Stack<>();

        // if not space and sb len > 0; we have a word, push in in stack
        for(int i=0; i<n; i++) {
            if(s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            } else if(sb.length() > 0){
                st.push(sb.toString());
                sb.setLength(0);
            }
        }

        // need to push the last word not ending with space
        if(sb.length() > 0){ // push the last word not ending with space.
            st.push(sb.toString());
        }

        sb.setLength(0);
        while(!st.isEmpty()) {
            sb.append(st.pop());
            if(!st.isEmpty()) sb.append(" ");
        }
        return sb.toString();
    }*/

    // approach 2:using Regex to trim down the multiple space and split the string
    public String reverseWords(String s) {

        String [] words= s.trim().split("\\s+"); //+ for 1 or more space
        StringBuilder sb = new StringBuilder();

        for(int i=words.length-1; i>=0; i--){
            sb.append(words[i]);
            if(i>0) sb.append(" ");
        }

        return sb.toString();
    }

    
}
