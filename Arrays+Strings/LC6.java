public class LC6 {
//https://leetcode.com/problems/zigzag-conversion/
// String: ZigZag: Medium

    public String convert(String s, int numRows) {
        
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        // current row = numRows
        int cRow = 0;
        int ind = 0; 
        int up = 0;
        int dn = numRows-1;
        int elmCount = 1;

        if (numRows <= 1) return s;

        while(cRow < numRows) {
            ind = cRow;
            elmCount  = 1;

            // corner cases -> 0th row and last row
            // either we go down or go up
            if(cRow == 0 || cRow == numRows-1) { 
                while(ind < n) {
                    sb.append(s.charAt(ind));
                    ind = ind + 2*(numRows-1);
                }
            }
            else { // we move up and down alternatively
                while (ind < n) {
                    if(elmCount % 2 !=0) { // go dn
                        sb.append(s.charAt(ind));
                        ind = ind + 2*dn;
                    }else {
                        sb.append(s.charAt(ind));
                        ind = ind + 2*up;
                    }
                    elmCount ++;
                }
            }
            
            dn--;
            up++;
            cRow ++;
        }

        return sb.toString();
    }
    
}
