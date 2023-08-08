package Heap.ArrayToHeap;

// TC: O(n)  
// AS: O(n)   -> Recursive stack space.


public class Heapify {

    public static void Swap(int arr[], int i, int largest) {

        int temp = arr[i];
        arr[i] = arr[largest];
        arr[largest] = temp;
    }

    public static void heapify(int arr[], int n, int i){
        
        int largest =i;
        int left = 2*i+1; // bec index starts from 0
        int right = 2*i+2; 

        if(left <n && arr[left] > arr[largest]) {

            largest = left;
        }

        if(right < n && arr[right] > arr[largest]) {

            largest = right;
        }

        if(largest!=i) {
            
            Swap(arr, i, largest);
            heapify(arr, n, largest);
        } else {
            return;
        }
    }
    
    public static void buildHeap(int arr[], int n){

        for(int i=n/2; i>=0; i--) {

            heapify(arr, n, i);
        
        }
    }

    public static void printHeap(int arr[], int n) {

        for(int i=0; i<n; i++) {

           System.out.print(arr[i]+" | ");
        }
    }

    public static void main(String args[]) {

        int arr[] = {1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};

        int n = arr.length;

        buildHeap(arr, n);

        printHeap(arr, n);
    }
}
