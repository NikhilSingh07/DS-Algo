package BinaryTree.Implementation;
import java.util.*;

public class Solution {
    
    static Scanner sc = null;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        Node root = createTree();
    }

    public static Node createTree(){

        Node root = null;
        int data = sc.nextInt();

        if(data == -1) {
            return null;
        }

        root = new Node(data);
        System.out.println("Enter left node data: ");
        createTree();
        System.out.println("Entere right node data: ");
        createTree();
        
        return root;
    }
}

class Node{

    Node left, right;
    int data;

    public Node(int data){

        this.data = data;
    }
}
