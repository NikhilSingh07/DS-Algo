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
    }
}
