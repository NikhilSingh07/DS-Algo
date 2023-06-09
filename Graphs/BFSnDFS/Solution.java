package Graphs.BFSnDFS;
import java.util.*;

public class Solution {
    
    public static void main(String[] args) {

        int V = 8;
        int src = 1;
        int dest = 6;
    
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0; i<=V; i++) {
            adj.add(new ArrayList<Integer>());
        }

        addEdges(adj, 1, 2);
        addEdges(adj, 1, 3);
        addEdges(adj, 1, 5);
        addEdges(adj, 2, 5);
        addEdges(adj, 3, 4);
        addEdges(adj, 4, 5);
        addEdges(adj, 4, 6);
        addEdges(adj, 7, 8);
        

       // printGraph(adj);

       System.out.println("\n\n\n----------------------------BFS traversal------------------\n");
        BFS(adj, src, dest);

        ArrayList<Integer> ansDFS = new ArrayList<>();
        boolean visited[] = new boolean[V+1];
        int components = 0;

        for(int i=1; i<visited.length; i++) {
                
            if(!visited[i]) {
                components++;
                DFS(adj, i, visited, ansDFS);
            }
        }
       // DFS(adj, src, visited, ansDFS);

        System.out.println("\n\n\n----------------------------DFS traversal------------------\n");

        System.out.println("The given graph has "+components+" components");
        for(int i=0; i<ansDFS.size();i ++) {
             System.out.print(ansDFS.get(i)+" ");
        }

    }

    static void DFS( ArrayList<ArrayList<Integer>> adj, int src, boolean [] visited, ArrayList<Integer> ans ) {

        visited[src] = true;
        ans.add(src);

        for(Integer neighbor: adj.get(src)) {
            if(!visited[neighbor]) {
                DFS(adj, neighbor, visited, ans);
            }
        }
    }

    static void BFS(ArrayList<ArrayList<Integer>> adj, int src, int dest) {

       int dist[] = new int[dest+1];
       int pred[] = new int[dest+1];
       boolean visited[] = new boolean[adj.size()];
       LinkedList<Integer> queue = new LinkedList<>();

       visited[src] = true;
       queue.add(src);
       dist[0] = 0;  //since the src node is 1
       pred[0]  = 0;
       dist[src] = 0;

       while(queue.size()!=0) {

        int current = queue.poll();  
        System.out.print(current+"->");

        for(int i=0; i<adj.get(current).size(); i++) {
            int neighbor = adj.get(current).get(i);
            if(!visited[neighbor]) {
                visited[neighbor] = true;
                queue.add(neighbor);
                dist[neighbor] = dist[current]+1;
                pred[neighbor] = current;
            }
        }

       }

       System.out.println("\n\n");
       for(int i=1; i<dist.length; i++) {
            System.out.println("Distance of node "+i+" from source node: "+src+" is: "+dist[i]);
       }

       System.out.println("\n\n");
       for(int i=1; i<pred.length; i++) {
            System.out.println("Predecessor of node "+i+" is "+pred[i]);
       }
    

    }


    static void addEdges(ArrayList<ArrayList<Integer>> adj, int s, int d) {

        adj.get(s).add(d);
        adj.get(d).add(s);
    }

    static void printGraph(ArrayList<ArrayList<Integer>> adj){

        for(int i=0; i<adj.size(); i++) {

            System.out.print(i+": ");
            for(int j=0; j<adj.get(i).size(); j++) {
                System.out.print(adj.get(i).get(j)+"->");
            }
            System.out.print("END\n");
       
        }
    }
}
