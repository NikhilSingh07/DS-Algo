package BinaryTree.left_rightView;
import java.util.*;

import javax.swing.text.html.parser.Element;

public class Solution {
 static Scanner sc;
 static int maxLevel= 0;
 static ArrayList<Node> list,listr;

    public static void main(String[] args) {

        Node root = createTree();
        printViews(root);
    }

// SC: O(n) bec of the arrayLists (left+right), but can be reduced to O(h) [recursion stack size] by printling result inside recursion without storing in arraylists.
    static void printViews(Node root){      // O(n), where n is the number of nodes.

        list = new ArrayList<>();
        listr = new ArrayList<>();

        leftViewUtil(root,1);   // O(n)
        maxLevel = 0;
        rightViewUtil(root,1);  // O(n)

        System.out.println("left view: ");
        for (Node node : list) {           // O(n)
             
            System.out.print(node.data+" ");
        }

        System.out.println("right view: ");       
        for (Node node : listr) {         // O(n)
            
            System.out.print(node.data+" ");
        }
    }

    static void rightViewUtil(Node root, int level){      //T(n) = 2T(n/2) + 1 = O(n)

        if(root == null) return;

        if(maxLevel< level) {           // constant
            listr.add(root);
            maxLevel = level;
        }

        rightViewUtil(root.right, level+1);        // T(n/2)
        rightViewUtil(root.left, level+1);         // T(n/2)
    }

    static void leftViewUtil(Node root,int level){         // O(n)

        if(root == null) return;

        if(maxLevel < level) {
            list.add(root);
            maxLevel = level;
        }

        leftViewUtil(root.left, level+1);
        leftViewUtil(root.right, level+1);
        

    }

    public static Node createTree(){

        sc = new Scanner(System.in);
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
    Node(int data){

        this.data = data;
    }
}


