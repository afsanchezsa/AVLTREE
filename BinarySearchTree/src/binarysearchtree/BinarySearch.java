/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtree;

/**
 *
 * @author COMPAQ
 */
public class BinarySearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BinarySearchTree<String>usuarios=new BinarySearchTree<>();
        System.out.println("Empty: "+usuarios.IsEmpty()+" Size: "+usuarios.size());
        usuarios.add("hi");
        usuarios.add("this");
        usuarios.add("is");
        usuarios.add("a");
        usuarios.add("test");
           System.out.println("Empty: "+usuarios.IsEmpty()+" Size: "+usuarios.size());
          System.out.println("hello "+ usuarios.contains("hello"));
          System.out.println("a "+usuarios.contains("a"));
          System.out.println("test "+usuarios.contains("test"));
          System.out.println("First: "+usuarios.getMin()+" Last: "+usuarios.getMax());
          usuarios.remove("is");
          usuarios.remove("a");
          usuarios.remove("remove");
                  System.out.println("Empty: "+usuarios.IsEmpty()+" Size: "+usuarios.size());
                  System.out.println("hi "+usuarios.contains("hello"));
                             System.out.println("a "+usuarios.contains("a"));
                             System.out.println("test "+usuarios.contains("test"));
          
        System.out.println("First: "+usuarios.getMin()+" Last: "+usuarios.getMax());
    }

}

interface SetSorted<T extends Comparable<? super T>> {

    boolean IsEmpty();

    int size();

    boolean contains(T x);

    void add(T x);

    void remove(T x);

    T getMin();

    T getMax();

}

class BinaryTreeNode<T extends Comparable<? super T>>  {

    private T element;
    private BinaryTreeNode<T> leftChild;
    private BinaryTreeNode<T> rigthChild;

    public BinaryTreeNode(T element, BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChild) {
        this.element = element;
        this.leftChild = leftChild;
        this.rigthChild = rightChild;

    }

    public BinaryTreeNode(T element) {
    this.element=element;
    this.rigthChild=null;
    this.leftChild=null;
           
    
    }

    public void setLeftChild(BinaryTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRigthChild(BinaryTreeNode<T> rigthChild) {
        this.rigthChild = rigthChild;
    }
    

    public BinaryTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public BinaryTreeNode<T> getRigthChild() {
        return rigthChild;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }



}

class BinarySearchTree<T extends Comparable<? super T>> implements SetSorted<T> {

    private BinaryTreeNode<T> root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;

    }

    @Override
    public boolean IsEmpty() {
        return this.root == null;

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(T x) {
        BinaryTreeNode<T> currNode = root;
        while (currNode != null) {
            int comparation = x.compareTo(currNode.getElement());
            if (comparation == 0) {
                return true;
            } else if (comparation < 0) {
                currNode = currNode.getLeftChild();
            }else{
                currNode=currNode.getRigthChild();
            }

        }
        return false;
    }

    @Override
    public void add(T x) {
        root=add(root,x);
        
    }
    private BinaryTreeNode<T>add(BinaryTreeNode<T> nodo, T x){//RETORNA EL ARBOL CUYA RAIZ ES NODO PERO YA CON EL ELEMENTO AGREGADO
        if(nodo==null){
            size++;
            return new  BinaryTreeNode<T>(x);
        }
        int comparation =x.compareTo(nodo.getElement());
        if(comparation<0){
           nodo.setLeftChild(add(nodo.getLeftChild(), x));
        }else if(comparation>0){
            nodo.setRigthChild(add(nodo.getRigthChild(),x));
        }
                return nodo;
        
        
    }
            

    @Override
    public void remove(T x) {
        root=remove(root, x);
    }
    private BinaryTreeNode<T>remove(BinaryTreeNode<T> node, T x){
        if (node==null)return null;//esto pasa solo si no aparece el elemento en el arbol;
        int comparation=x.compareTo(node.getElement());
        if(comparation<0){
            node.setLeftChild(remove(node.getLeftChild(), x));
            
        }else if(comparation>0){
            node.setRigthChild(remove(node.getRigthChild(),x));
            
        }else{
            if(node.getLeftChild()!=null&&node.getRigthChild()!=null){
                node.setElement(getMin(node.getRigthChild()));
                node.setRigthChild(remove(node.getRigthChild(), node.getElement()));
            }
            else{
               node=  node.getLeftChild()!=null?node.getLeftChild():node.getRigthChild();//en caso de que el elemento que se va a eliminar no aparezca aqui me retornara un nulo
            //este caso incluye el caso de que el nodo tenga una sola hoja o el elemento que se quiere eliminar no aparezca(si el elemento no esta, aqui se retorna nulo)
            }
            
            size--;
            
        }
        return node;//se retorna el nodo actual 
        
    }

    @Override
    public T getMin() {
     return  getMin(root);
    }

   private T getMin(BinaryTreeNode<T>nodoInicial){
        BinaryTreeNode<T>nodo=nodoInicial;
        T minElement=null;
        while(nodo!=null){
           minElement=nodo.getElement();
           nodo=nodo.getLeftChild();
                 
                 
        }
        return minElement;
                
    }
    @Override
    public T getMax() {
        return getMax(root);
    }
    
    private T getMax(BinaryTreeNode<T>nodoInicial){
        BinaryTreeNode<T>nodo=nodoInicial;
        T maxElement=null;
        while(nodo!=null){
            maxElement=nodo.getElement();
            nodo=nodo.getRigthChild();
            
            
        }
        return maxElement;
    }

}
