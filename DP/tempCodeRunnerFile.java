
        for(int [] row: dp) {
            Arrays.fill(row,-1);
        }
        
        int ans = memoPoints(n-1, 3, points, 