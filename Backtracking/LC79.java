package Backtracking;

public class LC79 {

    // https://leetcode.com/problems/word-search/
    // Medium
    // TC: m*n * 4 ** len(word) -> since we look in all 4 directions
    // SC: 4 ** len(word)

    private boolean findWord(char[][] board, int i, int j, String word, int ind) {

        // base case: finding all the letters of the word
        if(ind == word.length()) return true;

        // handling the edge cases of the grid (outside the boundaries)
        if(i<0 || j<0 || i>=board.length|| j>= board[0].length) return false;

        // if current element is already visited OR if the word is different
        if(board[i][j] == '$' || board[i][j]!= word.charAt(ind)) return false;

       // System.out.println("ind+"+ ind +", "+word.charAt(ind) );
        //store the char for backtracking
        char temp = board[i][j];
        board[i][j] = '$';// marking it visited

        int [][] dir = new int[][] {{-1,0}, {1,0}, {0,1}, {0,-1}};

        for(int x = 0; x<dir.length; x++) {// traverse all the directions

            int new_i = i+dir[x][0];
            int new_j = j+dir[x][1];
            // calling recursion to find the next letter
            if(findWord(board, new_i, new_j, word, ind+1))
            return true;
        }

        // backtrack
        board[i][j] = temp;

        return false;


    }


    public boolean exist(char[][] board, String word) {

        int m = board.length;
        int n = board[0].length;

        // traverse the grid and find the first word

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {

                // backtrack if we find the first letter of the word     
                if(board[i][j] == word.charAt(0) ) {

                    if(findWord(board, i, j, word, 0)) return true;
                }
            }
        }

        return false;
    }
    
}
