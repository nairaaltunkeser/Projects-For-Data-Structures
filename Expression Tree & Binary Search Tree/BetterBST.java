import java.util.*;
import java.util.LinkedList;

public class BetterBST<T extends Comparable<? super T>> extends BinarySearchTree<T>{
    
    //private helper method to see if there is an imbalance between subtrees
    private T imbalance(BinaryNode<T> root ){
        if (root == null){
            return null;
        }
        int h = height(root.left) - height(root.right);
        int absH = Math.abs(h);

        if (absH > 1){
            return root.data;
        }
        T imbalanceOfLeft = imbalance(root.left);
        T imbalanceOfRight = imbalance(root.right);

        if ( imbalanceOfRight == null){
            return imbalanceOfLeft;
        }
        if ( imbalanceOfLeft == null){
            return imbalanceOfRight;
        }
        return imbalanceOfRight;

    }
    // the private method to find the smallest value in tree that is bigger than t 
    private T smallestGreaterThan(T t, BinaryNode<T> root){
        if (root == null){
            return null;
        }
        int compareResult = t.compareTo(root.data);
        if (compareResult >= 0){ // if result is bigger move to the right in the tree 
            return smallestGreaterThan(t, root.right);
        }
        else{
            T compareLeft = smallestGreaterThan(t, root.left);
            if(compareLeft == null){
                return root.data; // if there isn't smaller in the left subtree than the smallest is the root
            }
            return compareLeft;
        }
    }
    //public method to find linked list of the traversal level 
    // will be using a queue and its methods in queue interface for this mehtod
    public LinkedList<BinaryNode<T>> levelOrderTraversal(){
        LinkedList<BinaryNode<T>> traversal = new LinkedList<>();
        LinkedList<BinaryNode<T>> q = new LinkedList<>(); // will be my queue
        
        BinaryNode<T> deleted = null;
        q.offer(root);
        
        while(!q.isEmpty()){
            deleted = q.poll(); // remove the front of the queue
            if(deleted != null){
                traversal.add(deleted);
                q.offer(deleted.right);
                q.offer(deleted.left);

            }
        }
        return traversal;
    }

    // private method for creating a new mirroring BinarySearchTree
    private BinaryNode<T> mirror(BinaryNode<T> root){
        if (root == null){
            return null;
        }
        //mirror new node: left link is the right one from the original tree and vica versa
        BinaryNode<T> mirrored = new BinaryNode<T>(root.data, mirror(root.right), mirror(root.left));
        return mirrored;
    }

    //private helper method to get the height of BinarySearchTree
    private int height(BinaryNode<T> root){
        if (root == null){
            return -1;
        }
        int resultH = Math.max(height(root.left),height(root.right)) +1;
        return resultH;
    }

    //constructor fir the mirror method
    public BetterBST(){
        this.root= null;
    }
    public BetterBST(BinaryNode<T> root){
        this.root=root;
    }

    //all public methods that call for the private helper methods are below 
    public int height(){
        return height(root);
    }
    public T imbalance(){
        return imbalance(root);
    }
    public BinarySearchTree<T> mirror(){
        return new BetterBST<T>(mirror(root));
    }
    public T smallestGreaterThan(T t){
        return smallestGreaterThan(t, root);
    }
     
}
