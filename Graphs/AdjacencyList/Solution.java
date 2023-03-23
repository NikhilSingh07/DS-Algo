package Graphs.AdjacencyList;

import java.util.ArrayList;

public class Solution {
    
    public static void main(String[] args) {

        //creating graph

        int vertices = 5+1;
        ArrayList<ArrayList<Integer>> adj  = new ArrayList<>();

        for(int i=0; i<vertices; i++) {
            adj.add(new ArrayList<Integer>());
        }

        addEdges(adj, 1, 5);
        addEdges(adj, 1, 4);
        addEdges(adj, 1, 2);
        addEdges(adj, 2, 4);
        addEdges(adj, 3, 5);
        addEdges(adj, 4, 5);


        printGraph(adj);
    }

    static void addEdges(ArrayList<ArrayList<Integer>> adj, int s, int d){

        adj.get(s).add(d);
        adj.get(d).add(s);

    }

    static void printGraph(ArrayList<ArrayList<Integer>> adj){

        for(int i=1; i<adj.size(); i++) {            // O(V)

            System.out.print(i+": ");                // (1)
            for(int j=0; j<adj.get(i).size(); j++) {       //O(Eadj)

                System.out.print(adj.get(i).get(j)+"->");     
            }
            System.out.print("END");
            System.out.println("");
        }

        // Time complexity: O(V*Eadj) = O(E) [total number of edges]

        // V*(1+Eadv+1+1)
        //V+V*Eadj+V+V
        //3V + E
        //T.C = (V+E)

        // Space complexity: O(V+E)
        // in worst case: O(V^2): There are V^2 edges in fully connnected graph.


        /*The outer for loop runs V times, which is the number of vertices. Within each iteration of the loop, the inner for loop runs Eadj times, which is the number of adjacent vertices for the current vertex. Therefore, the total number of iterations of the inner loop is equal to the sum of the sizes of all the adjacency lists, which is equal to the number of edges in the graph.

The statements marked as (1) are executed V times, and each of these statements has a time complexity of O(1). The statements inside the inner loop are executed E times, where E is the total number of edges in the graph, and each of these statements has a time complexity of O(1). Therefore, the overall time complexity of the printGraph method is O(V+E).

The space complexity of the implementation is O(V+E) because we are using an adjacency list to represent the graph, which requires V lists to store the adjacency information for each vertex, and E elements to store the adjacency information for all edges.
         * 
         * 
         */
    }
}
