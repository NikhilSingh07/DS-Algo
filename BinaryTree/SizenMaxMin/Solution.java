package BinaryTree.SizenMaxMin;

import java.util.*;

public class Solution {
    
    static Scanner sc = null;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        Node root = createTree();

        int size = size(root);
        System.out.println("Size/Number of edges in BT: "+size);

        int max = maxElement(root);

        System.out.println("maximum element in the BT: "+max);

    }

    static int size(Node root) {

        if(root == null) return 0;

        return (size(root.left)+size(root.right)+1);
    }

    static int maxElement(Node root) {

        if(root == null) return Integer.MIN_VALUE;

        return Math.max(root.data, Math.max(maxElement(root.left), maxElement(root.right)));
    }

    public static Node createTree(){

        Node root = null;
        int data = sc.nextInt();

        if(data == -1) {
            return null;
        }

        root = new Node(data);
        System.out.println("Enter left node data: ");
        root.left= createTree();
        System.out.println("Entere right node data: ");
        root.right=createTree();
        
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
