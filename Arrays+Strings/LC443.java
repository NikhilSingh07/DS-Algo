public class LC443 {
//https://leetcode.com/problems/string-compression/?envType=study-plan-v2&envId=leetcode-75
// String: Medium
    public int compress(char[] chars) {

        StringBuilder sb = new StringBuilder();
       // int count  = 0;

        //  a b b  

        for(int i=0; i<chars.length; i++) {

            int count =0;
            char ch = chars[i];
            while(i<chars.length && chars[i] == ch) {
                count++;
                i++;
            }
            
            sb.append(ch);
            if(count > 1) {
                if(count > 10) {
                    int a  = count /10;
                    int b  = count %10;
                    sb.append(a);
                    sb.append(b);
                } else {
                    sb.append(count);
                }
            }
            i--;
        }
        /*   
        // same TC: but handling last char seperately  
        for(int i=0; i<chars.length-1; i++) {

            if(chars[i] == chars[i+1]) count++;
            else {

                sb.append(chars[i]);
                if(count > 1) {
                    if(count > 10) {
                        int a  = count /10;
                        int b  = count %10;
                        sb.append(a);
                        sb.append(b);
                    } else {
                        sb.append(count);
                    }
                }
                count =1;
            }
        }

        // handling the last character
        if(count > 1) {
            sb.append(chars[chars.length-1]);
                if(count > 1) {
                    if(count > 10) {
                        int a  = count /10;
                        int b  = count %10;
                        sb.append(a);
                        sb.append(b);
                    } else {
                        sb.append(count);
                    }
                }
        } else {
            sb.append(chars[chars.length-1]);
        }*/

        char [] ans = sb.toString().toCharArray();

        for(int i=0; i<ans.length; i++) {
            chars[i] = ans[i];
        }
        return ans.length;
    }
    
}
