package Heap.HeapSort;
import Heap.ArrayToHeap.Heapify;


public class Heapsort {
    

    public static void HeapSort(int arr[], int N){

        for(int i= N-1; i>0; i--) {  //O(n)

            Heapify.Swap(arr, 0, i);
            Heapify.heapify(arr, i-1, 0 );   // O(logn)
        } 
    }

    public static void main(String[] args) {


        int arr[] = { 12, 11, 13, 5, 6, 7 };
        int N = arr.length;

        Heapify.buildHeap(arr, N);   // O(n)

        HeapSort(arr, N);   // O(nlogn)

        // O(n) + O(nlogn)
        Heapify.printHeap(arr, N);   

    }
}
