package BinaryTree.LevelOrderTraversal;
import java.util.*;

public class Solution {

    static Scanner sc;

    public static void main(String[] args) {

        Node root = createTree();

        int height = getHeight(root);
       // System.out.println("height of the tree is: "+height);

        //printLevelOrder(root, height);      // Time complexity is O(n^2) using recursion.
        levelOrderUsingQueue(root);           // Time complexity is  O(n) using Queue data structure
        // print each level in a different line.
    }

    static void levelOrderUsingQueue(Node root) {

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);
        queue.add(null); // to print each level in a different line.

        while(!queue.isEmpty()){    // O(n)

            Node curr = queue.poll();
            if(curr == null) {
                if(queue.isEmpty()) return;
                else {
                    queue.add(null);
                }
                System.out.println();
                continue;
            }

            System.out.print(curr.data+" ");

            if(curr.left!=null) {
                queue.add(curr.left);
            }
            if(curr.right!=null){
                queue.add(curr.right);
            }
        }

    }

    static void printLevelOrder(Node root, int height){   // T(n) = O(n^2)


        if(height == 0){
            System.out.println("empty tree");         // O(1)
        }
        
        for(int i=1 ;i <= height; i++) {        

            getCurrentLevel(root, i);          //O(n)*O(n)
        }

    }

    static void getCurrentLevel(Node root, int level){    // T(n) = 2T(n/2) + 1 -> O(n) [Master theroem]

        if(root == null)
        return;

        if(level == 1) 
        System.out.print(root.data+" ");             // O(1)

        else {

            getCurrentLevel(root.left, level-1);          // T(n/2)
            getCurrentLevel(root.right, level-1);         // T(n/2)
            System.out.println();                     // O(1)
        }


    }


    static int getHeight(Node root) {

        if(root == null)
        return 0 ;

        return Math.max(getHeight(root.left), getHeight(root.right))+1;

    }

    static Node createTree() {

        Node root;
        sc = new Scanner(System.in);
        int data = sc.nextInt();

        if(data == -1) return null;

        root = new Node(data);
        System.out.println("Enter left Node");
        root.left = createTree();
        System.out.println("Enter right Node");
        root.right = createTree();

        return root;

    }
}

class Node{

    Node left;
    Node right;
    int data;

    Node(int data){
        this.data = data;
    }
}

