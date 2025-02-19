public class LC345 {
    

        // approach 1: using stack 
    // approach 2: using 2 pointers
    private boolean isVowel(Character c) {

        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
           c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' ) return true;
        else return false;   
    } 
    /*
    public String reverseVowels(String s) {
        
        // using a stack ds
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder(s);

        int n = s.length();
        for(int i=0; i<n; i++) {
            if(isVower(s.charAt(i))) {
                st.push(s.charAt(i));
            }
        }

        for(int i=0; i<n; i++) {
            if(isVower(s.charAt(i))) {
                sb.setCharAt(i, st.pop());
            }
        }
        return sb.toString();
    }*/

    // using 2 pointers
    public String reverseVowels(String s) {

        StringBuilder sb = new StringBuilder(s);
        int n = s.length();
        int l = 0;
        int h = n-1;

        while(l<h) {

            if(isVowel(s.charAt(l)) && isVowel(s.charAt(h))) {
                char c1 = s.charAt(l);
                sb.setCharAt(l, s.charAt(h));
                sb.setCharAt(h, c1);
                l++;
                h--;
            }

            // move the left pointer
            else if(!isVowel(s.charAt(l)) && isVowel(s.charAt(h))) l++;
            else h--;
        }

        return sb.toString();
    }
}
