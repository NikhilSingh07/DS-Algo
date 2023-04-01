package BinaryTree.Height;

import java.util.*;

public class Solution {
    
    static Scanner sc = null;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        Node root = createTree();


        int height = getHeight(root);
     
        System.out.println("Height of the tree is: "+ height);
    }

    static int getHeight(Node root) {

        if(root == null)
        return 0;

        if(root.data == -1) return 0;
        
        return Math.max(getHeight(root.left), getHeight(root.right) + 2);
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
