/****************************************************************************************
 * Description: This java program 
 *  1) Builds a binary search tree
 *  2) Print the tree by traversing inOrdertravel
 *  3) Deletes a leaf node
 *  4) Deletes a non-leaf node and replaces with predecessor which is not a leaf node
 *  5) Deleted a node and replaces with predecessor which is a leaf node
 *  6) This also works for replacing with successor
 *  7) Tree is printed after every node deletion
 *
 * Author:
 *      Kavitha Rajendran
 */
 * ***************************************************************************************/
import java.util.*;

public class BSTree<T extends Comparable<T>> {
    
    //Node Class of generic type
    class Node<T>{
        T data;
        Node<T> leftPtr;
        Node<T> rightPtr;
    
        //Initialize a node with given data        
        Node(T data){
            this.data = data;
            this.leftPtr = null;
            this.rightPtr = null;
        }
    }
    //root of the tree 
    private Node<T> root;
    BSTree() {
        root = null;
    }
    
    //Insert a node in BST
    private Node<T> insertNode(Node<T> t, T data) {
        Node<T> newNode = new Node<>(data);
        if (t==null) {
            //System.out.println("Inserting Root node");
            t = newNode;
        }  
        //System.out.println("Inserting Data:"+ data);
        int compareResult = data.compareTo(t.data);
        if (compareResult < 0 ) {
            //System.out.println("Inserting left side");
            if (t.leftPtr == null) {
                t.leftPtr = newNode;
            }
            else
                insertNode(t.leftPtr, data);
        }
        if (compareResult > 0) {
           //System.out.println("Inserting right side");
            if (t.rightPtr == null) {
                t.rightPtr = newNode;
            }
            else
                insertNode(t.rightPtr, data);
        }
         
        //if (t.data == data) {
        //    System.out.println("Duplicate values not allowed");
        //}

        return t;
    }
    public void insert(T data) {
        root = insertNode(root,data);
    }

    //Inorder Traversal
    private void inOrderTraversal(Node<T> root) {
        if(root==null)
            return;
        else {
            inOrderTraversal(root.leftPtr);
            System.out.printf(" %d ",root.data);
            inOrderTraversal(root.rightPtr);
        }
    }

    public void printTree(){
    if(root == null)
        System.out.println("Tree is empty. Nothing to print");
    else
        inOrderTraversal(root);
    }
    
    //Deleting leaf node
    public Node<T> deleteLeafNode( Node<T> root, Node<T> parent, Node<T> t){
        //If parent is null, Setting root to null
        if(parent==null) {
            System.out.println("Deleting root node of the tree");
            root = null;
        }
        else if(parent.leftPtr == t)
            parent.leftPtr = null;
        else
            parent.rightPtr = null;
        return root;
    }
     
    //Replacing with a successor
    public void replaceWithSuccessor(Node<T> t){
        Node<T> successorNode;
        Node<T> successorParent=t;
        System.out.println("Replacing with a successor");
        successorNode = t.rightPtr;
        while(successorNode.leftPtr!=null){
            successorParent = successorNode;
            successorNode = successorNode.leftPtr;
        }
        System.out.println("Successor is "+successorNode.data);
        //System.out.println("Successor Parent is "+successorParent.data);
        //Successor is leaf node
        if(successorNode.rightPtr == null)
            System.out.println("Successor is leaf node");
        else
            System.out.println("Successor is not leaf node");
        t.data = successorNode.data;
        if(t==successorParent) {
            //System.out.println("Node to be deleted is successor's Parent");
            successorParent.rightPtr = successorNode.rightPtr;
        }
        else
            successorParent.leftPtr = successorNode.rightPtr;
    }
    
    //Replacing with a predecessor
    public void replaceWithPredecessor(Node<T> t) {
        //Deleting a node with one child
        Node<T> predecessorNode;
        Node<T> predecessorParent=t;
        System.out.println("Replacing with a predecessor");
        predecessorNode = t.leftPtr;
        while(predecessorNode.rightPtr!=null){
            predecessorParent = predecessorNode;
            predecessorNode = predecessorNode.rightPtr;
        }
        System.out.println("Predecessor is "+predecessorNode.data);
        if(predecessorNode.leftPtr == null)
            System.out.println("Predecessor is leaf node");
        else
            System.out.println("Predecessor is not leaf node");
        //System.out.println("Predecessor's Parent is "+predecessorParent.data);
        t.data = predecessorNode.data;
        if(t==predecessorParent) {
            //System.out.println("Node to be deleted is predecessor's Parent");
            predecessorParent.leftPtr = predecessorNode.leftPtr;
        }
        else
            predecessorParent.rightPtr = predecessorNode.leftPtr;
    }
 
    //Deletion
    private Node<T> deleteNode(Node<T> root, T value) {
        //First find the node to be deleted & the its parent
        Node<T> toBeDeleted;
        Node<T> parent = null;
        Node<T> t = root;
        while(t!=null)
        { 
            int compRes = value.compareTo(t.data);
            if(compRes < 0) {
                parent = t;
                t = t.leftPtr;
            }   
            else if(compRes > 0) {   
                parent = t;
                t = t.rightPtr;
            }
            else {
                System.out.println("Found the Node to be deleted "+value);
                break;
            }
        }
        if(t==null){
            System.out.println("Node not present in tree");
            //return
        }
        //Given data found in tree
        else {
            if (parent == null)
                System.out.println("Deleting root of the tree");
            //Deleting leaf node
            if((t.leftPtr==null)&&(t.rightPtr==null)) {
                System.out.println("Node to be deleted is a leaf node");
                root = deleteLeafNode(root, parent, t);
            }
            else if(t.rightPtr == null){
                //Deleting a node with one child
                //Replacing with a predecessor
                replaceWithPredecessor(t);
            }
            else if (t.leftPtr == null){
                //Replacing with a successor   
                replaceWithSuccessor(t); 
            }
            else {
                //having both childer
                //can be replaced with successor or predecessor
                //replaceWithSuccessor(t);
                replaceWithPredecessor(t);
            }
        }
        return root;
    }
    public void delete(T data) {
        if(root == null)
            System.out.println("Tree is empty.Insert values");
        else
            root = deleteNode(root, data);
    }

    //Main Function
    public static void main(String[] args) {
        BSTree<Integer> binaryTree = new BSTree<>();
        binaryTree.printTree();

        //Building a BST
        int[] input = {51,32,80,20,42,70,90,37,48,68,76,85,98,39,38};
        for( int i: input){
           binaryTree.insert(i);
        }
        //Printing the tree
        System.out.println("Tree is built now - inOrderTraversal ");
        binaryTree.printTree();
        System.out.println("\n");

        //Leaf node deletion
        System.out.println("**** Deleting leaf node --> Deleting node with no child **** "); 
        binaryTree.delete(98);
        System.out.println("Tree after leaf node deletion");
        binaryTree.printTree();
        System.out.println("\n");

        //Non leaf node deletion
        System.out.println("**** Deleting root node --> Deleting a node with two child **** ");
        System.out.println("--- Replacing with predecessor which is not leaf node --");
        binaryTree.delete(51);
        binaryTree.printTree();
        System.out.println("\n");

        System.out.println("**** Deleting non-leaf node **** ");
        System.out.println("--- Replacing with predecessor which is a leaf node --");
        binaryTree.delete(42);
        binaryTree.printTree();
        System.out.println("\n");
        
    }
} 
