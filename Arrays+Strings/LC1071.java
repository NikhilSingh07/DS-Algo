public class LC1071 {
    
        // There would be a GCD if 
    // str1 + str2 = str2 + str1

    // str1 = x + x + x + x
    // str2 = x + x + x
    public String gcdOfStrings(String str1, String str2) {



        if(str2.length() > str1.length()) gcdOfStrings(str2, str1); // len of str1 always be >= len(str2)
        int n  = str1.length();
        int m = str2.length();

        if( !(str1+ str2).equals(str2+str1) ) return ""; // there is no GCD

        // now, we just need to find the GCD
        // To check if the current substring is a GCD, we do a modulo. If it is 0 in both strings we have a divisor
        // We can start iterating from the end of a string. That way our first ans would be a GCD
        
        int len = 0;
        for(int i = m-1; i>=0 ; i--) {

            if( (m % (i+1) == 0 ) && (n % (i+1) == 0) ) {
                len = i;
                break;
            }
        }
        return str2.substring(0, len+1);

    }
}
