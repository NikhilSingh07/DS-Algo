package BinaryTree.Implementation;
import java.util.*;

public class Solution {
    
    static Scanner sc = null;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        Node root = createTree();

        // Tree Traversal, T.C = O(n)
        // S.C = If we don’t consider the size of the stack for function calls then O(1) otherwise O(h) where h is the height of the tree. 
        
        System.out.println("Inoreder Traversal");
        Inorder(root);

        System.out.println("Preorder Traversal");
        Preorder(root);

        System.out.println("Postorder Traversal");
        Postorder(root);
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

    public static void Inorder(Node root){

        if(root == null) return;
        Inorder(root.left);
        System.out.println(root.data);
        Inorder(root.right);

    }

    public static void Preorder(Node root){

        if(root == null) return;

        System.out.println(root.data);
        Preorder(root.left);
        Preorder(root.right);

    }

    public static void Postorder(Node root){

        if(root == null) return;

        Postorder(root.left);
        Postorder(root.right);
        System.out.println(root.data);

    }
}

class Node{

    Node left, right;
    int data;

    public Node(int data){

        this.data = data;
    }
}
