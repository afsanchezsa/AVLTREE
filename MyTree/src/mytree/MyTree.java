/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 *
 * @author COMPAQ
 */
public class MyTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LinkedBinaryTree<Integer>a=new LinkedBinaryTree<>();
        LinkedBinaryTree<Integer>x=new LinkedBinaryTree<>();
        LinkedBinaryTree<Integer>y=new LinkedBinaryTree<>();
        LinkedBinaryTree<Integer>z=new LinkedBinaryTree<>();
        
        y.makeTree(new Integer(1), a, a);
        z.makeTree(new Integer(2), a, a);
        x.makeTree(new Integer(3), y, z);
        y.makeTree(new Integer(4), x, a);
        y.makeTree(new Integer(5), a, y);
        System.out.println("Preorder sequence is ");
        y.preOrder();
        System.out.println("\n"+y.IterativePreOrder());
        System.out.println("\nInorder sequence is ");
        y.inOrder();
        System.out.println("\n"+y.IterativeInOrder());
        System.out.println("\nPostOrder sequence is ");
        y.postOrder();
        System.out.println("\n"+y.IterativePostOrder());
        System.out.println("\nLevelOrder sequence is ");
        y.levelOrder();
        
        System.out.println("\n Number of nodes is: "+y.size());
        System.out.println("Height of tree is: "+y.height());
    }
    
}
interface BinaryTree<T>{
    boolean isEmpty();
    T root();
    void makeTree(T element,BinaryTree<T>left,BinaryTree<T>right);
    BinaryTree<T>removeLeftSubtree();
    BinaryTree<T>removeRightSubtree();
void preOrder();
void inOrder();
void postOrder();
void levelOrder();

}
class BinaryTreeNode<T>{
    T element;

     BinaryTreeNode<T>leftChild;
     BinaryTreeNode<T>rigthChild;
     public  BinaryTreeNode(){
         
     }
     public BinaryTreeNode(T theElement){
         this.element=theElement;
         leftChild=null;
         rigthChild=null;
         
     }
     public BinaryTreeNode(T theElement,BinaryTreeNode<T>theLeftChild,BinaryTreeNode<T>theRightChild){
         element=theElement;
         leftChild=theLeftChild;
         rigthChild=theRightChild;
     }
    public BinaryTreeNode<T>getLeftChild(){
        return leftChild;
        
    }

    public BinaryTreeNode<T> getRigthChild() {
        return rigthChild;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T theElement) {
        this.element = theElement;
    }

    public void setLeftChild(BinaryTreeNode<T> theLeftChild) {
        this.leftChild = theLeftChild;
    }

    public void setRigthChild(BinaryTreeNode<T> theRigthChild) {
        this.rigthChild = theRigthChild;
    }
    public String toString(){
        return element.toString();
    }
    
    
}
class LinkedBinaryTree<T>implements BinaryTree<T>{
    BinaryTreeNode<T>root;
    public LinkedBinaryTree() {
    root=null;
    }

    
    
    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public T root() {
      return (root==null)?null:root.element;
    }

    @Override
    public void makeTree(T element, BinaryTree<T> left, BinaryTree<T> right) {
        this.root=new BinaryTreeNode<T>(element,((LinkedBinaryTree<T>)left).root,((LinkedBinaryTree<T>)right).root);
                
    }

    @Override
    public BinaryTree<T> removeLeftSubtree() {
       if(root==null)throw new IllegalArgumentException("tree is Empty");
       LinkedBinaryTree<T>leftSubtree=new LinkedBinaryTree<T>();
       leftSubtree.root=root.leftChild;
       root.leftChild=null;
       return(BinaryTree<T>)leftSubtree;
    }

    @Override
    public BinaryTree<T> removeRightSubtree() {
        if(root==null)throw new IllegalArgumentException("tree is Empty");
        LinkedBinaryTree<T>rightSubtree=new LinkedBinaryTree<T>();
        rightSubtree.root=root.rigthChild;
        root.rigthChild=null;
        return (BinaryTree<T>)rightSubtree;
    }

    @Override
    public void preOrder() {
        thePreOrder(root);
    }

    @Override
    public void inOrder() {
        theInOrder(root);
    }
static<T> void thePreOrder(BinaryTreeNode<T>t){
    if(t!=null){
        System.out.print(t+" ");
        thePreOrder(t.getLeftChild());
        thePreOrder(t.getRigthChild());
    }
} 
    @Override
    public void postOrder() {
      thePostOrder(root);
    }
    static<T> void thePostOrder(BinaryTreeNode<T>t){
        if(t!=null){
            thePostOrder(t.getLeftChild());
            thePostOrder(t.getRigthChild());
            System.out.print(t+" ");
        }
    }   
    static<T>void theInOrder(BinaryTreeNode<T>t){
        if(t!=null){
            theInOrder(t.getLeftChild());
            System.out.print(t+" ");
            theInOrder(t.getRigthChild());
        }
    }

    @Override
    public void levelOrder() {
       LinkedQueue<BinaryTreeNode<T>>q=new LinkedQueue<>();
       q.put(root);
       while(!q.isEmpty()){
           BinaryTreeNode<T>t=(BinaryTreeNode<T>)q.remove();
           System.out.print(t+" ");
           if( t.leftChild!=null)
               q.put(t.getLeftChild());
           if(t.rigthChild!=null)
               q.put(t.rigthChild);

       }
    }
    public int size(){
        return theSize(root);
    }
    static<T> int theSize(BinaryTreeNode<T>t){
        if(t==null)return 0;
        int hl=theSize(t.getLeftChild());
        int hr=theSize(t.getRigthChild());
        return hl+hr+1;
        
    }
    public int height(){
        return theHeight(root);
    }
    static<T>int theHeight(BinaryTreeNode<T>t){
        if(t==null)return 0;
        int hl=theHeight(t.leftChild);
        int hr=theHeight(t.rigthChild);
        if(hl>hr)return ++hl;
        return ++hr;
    }
    public String IterativePreOrder(){
        StringBuilder s=new StringBuilder();
        ArrayDeque<BinaryTreeNode<T>> cola=IterativePreorder(root);
        while(!cola.isEmpty())
            s.append(cola.pollLast().toString()+" ");
        return s.toString();
                
    }
   private  ArrayDeque<BinaryTreeNode<T>> IterativePreorder(BinaryTreeNode<T>t){//solo hay que sacar de esa cola para obtener el preorer
      ArrayDeque<BinaryTreeNode<T>> cola=new ArrayDeque<>();
    if(t==null)return cola;
    Stack<BinaryTreeNode<T>> pila=new Stack<>();
    pila.push(t);
    while(!pila.isEmpty()){
        t=pila.pop();
        cola.addFirst(t);
        if(t.rigthChild!=null)pila.push(t.rigthChild);
        if(t.leftChild!=null)pila.push(t.leftChild);
     }
    return cola;
      
    }
   public String IterativeInOrder(){
         StringBuilder s=new StringBuilder();
        ArrayDeque<BinaryTreeNode<T>> cola=IterativeInOrder(root);
        while(!cola.isEmpty())
            s.append(cola.pollLast().toString()+" ");
        return s.toString();
   }
   private ArrayDeque<BinaryTreeNode<T>>  IterativeInOrder(BinaryTreeNode<T>t){
         ArrayDeque<BinaryTreeNode<T>> cola=new ArrayDeque<>();
         
       if(t==null)return cola;
      Stack<BinaryTreeNode<T>> pila=new Stack<>();
     while(t!=null|| !pila.isEmpty()){
         while(t!=null){
             pila.push(t);
             t=t.getLeftChild();
         }
         t=pila.pop();
         cola.addFirst(t);
         t=t.getRigthChild();
     }
     return cola;
   }
      
   public String IterativePostOrder(){
          StringBuilder s=new StringBuilder();
        Stack<BinaryTreeNode<T>> pilafinal=IterativePostOrder(root);
        while(!pilafinal.isEmpty())
            s.append(pilafinal.pop().toString()+" ");
        return s.toString();
   }
            private Stack<BinaryTreeNode<T>> IterativePostOrder(BinaryTreeNode<T>t){
                      Stack<BinaryTreeNode<T>> pilafinal=new Stack<>();
                     if(t==null)return pilafinal;
                     Stack<BinaryTreeNode<T>>pila=new Stack<>();
                     pila.push(t);
                     while(!pila.isEmpty()){
                         t=pila.pop();
                         pilafinal.push(t);
                         if(t.getLeftChild()!=null)pila.push(t.getLeftChild());
                         if(t.getRigthChild()!=null)pila.push(t.getRigthChild());
                     }
                     
                     return pilafinal;
            }
            
    
}
interface Queue<T>{
     boolean isEmpty();
     T getFrontElement();
     void put(T theObject);
     T remove();
}
class ChainNode<T>{
    T element;
    ChainNode<T>next;

    public ChainNode(T element, ChainNode<T> next) {
        this.element = element;
        this.next = next;
    }

    public ChainNode(T element) {
        this(element,null);
    }

    public ChainNode() {
        this(null,null);
    }
    
}
class LinkedQueue<T> implements Queue<T>{
    ChainNode<T>front;
    ChainNode<T>back;

    public LinkedQueue() {
        front=back=null;
    }
 
    
    @Override
    public boolean isEmpty() {
               return front==null;
    }

    @Override
    public T getFrontElement() {
       return isEmpty()?null:front.element;
}
    @Override
    public void put(T theObject) {
        ChainNode<T>p=new ChainNode<T>(theObject,null);
        if(front==null){
            front=p;
        }else{
            back.next=p;
            
        }
        back=p;
    }

    @Override
    public T remove() {
        if(isEmpty()){
            return  null;
        }
        T frontElement=front.element;
        front=front.next;
        if(isEmpty()){
            back=null;
        }
        return frontElement;
              
    }
    
}