class Solution {
    
    private static long wineBuyingNSelling (int Arr[], int N) {
        
        
        int sp = 0;
        int bp = 0;
        long dist = 0;
        
        while(sp<N && bp <N) {
            
            while(Arr[sp] >= 0) {
                sp++;
                if(sp == N)
                return dist;
            }
            
            while(Arr[bp] <= 0) {
                bp++;
                if(bp == N)
                return dist;
            }
            
            if(Math.abs(Arr[sp]) <=  Math.abs(Arr[bp] )) {
                Arr[bp] += Arr[sp];
                dist += Math.abs(Arr[sp]) * Math.abs(sp-bp);
                Arr[sp] = 0;
                sp++;
            
                
            } else {
                Arr[sp] += Arr[bp];
                dist += Math.abs(Arr[bp]) * Math.abs(sp-bp);
                Arr[bp] =0;
                bp++;
                
            }
        }
        
        return dist;
    }
    
    public static void main(String[] args) {

        int arr[] = {5, -4, 1, -3, 1};
        int n = 5;
        long dist = wineBuyingNSelling(arr, n);

        System.out.println("distance: "+ dist);

    }

    }
