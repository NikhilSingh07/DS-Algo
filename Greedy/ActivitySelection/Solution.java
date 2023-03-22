package Greedy.ActivitySelection;
import java.util.*;

public class Solution {
    
    public static void maxMeeting(int N, int []s, int []f){


        ArrayList<Integer> ans = new ArrayList<>();
        int fp = 0;
    
        int arr[][] = new int[N][3];
    
        for(int i=0; i<N;i++){        //n
            arr[i][0] = i+1;
            arr[i][1] = s[i];
            arr[i][2] = f[i]; 
        }
    
        Arrays.sort(arr, Comparator.comparingInt(o -> o[2]));
        ans.add(arr[0][0]);
        fp = arr[0][2];
    
        for(int i=1; i<N; i++ ) {
    
            if(arr[i][1] > fp) {
                ans.add(arr[i][0]);
                fp = arr[i][2];
            }
        }
    
        Collections.sort(ans);
       
        for(int i=0; i<ans.size(); i++) {

            System.out.println(ans.get(i));
        }
    }
    

    public static void main(String[] args) {

        int s[] = {1,3,0,5,8,5};
        int f[] = {2,4,6,7,9,9} ;
        int N = 6;
        maxMeeting(N, s, f);
    }

}
