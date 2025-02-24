// https://www.geeksforgeeks.org/problems/encode-and-decode-strings/1
// String: Encode and decode

    // approach 1: use a non-ascii character like $ or # as a delimiter
    // the drawback is if the string itself contains the delimited char inside?

    // approach 2: adding length of string followed by a special char as a delimiter
    // ["Hello", "World"]
    // ["4#Hello5#World"]
    //    ^
    // Iterate and search for delimiter and append the next k no. of elements

public class gfgEncodeDecode {

    public String encode(String s[]) {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<s.length; i++) {

import java.util.ArrayList;
import java.util.List;

            sb.append("#");
            sb.append(s[i]);
        }

        return sb.toString();
    }

    public String[] decode(String str) {
        
        
        List<String> ans = new ArrayList<>();


        for(int i=0; i<str.length(); i++) {

            StringBuilder sb = new StringBuilder();
            
            //finding the number of letters
            while(str.charAt(i) != '#') {
                sb.append(str.charAt(i));
                i++;
            }// i would be pointing at delimiter 
            i++;

            int end = i + Integer.valueOf(sb.toString());
            sb = new StringBuilder();

            while(i<end) {
                sb.append(str.charAt(i++));
            }
            ans.add(sb.toString());
            i--;
       
        }

        String [] f  = new String[ans.size()];
        for(int i=0; i<ans.size(); i++) {
            f[i] = ans.get(i);
        }

        return f;
    }
    
}
