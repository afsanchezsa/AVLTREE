/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltree;

/**
 *
 * @author COMPAQ
 */
public class AVLTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
class   AVLnode<T>{
    T element;
    AVLnode<T>leftChild,rightChild;
int height;
public AVLnode(){
   this(null,null,null);
}
public AVLnode(T element){
    this(element,null,null);
}
public AVLnode(T element, AVLnode<T>leftChild,AVLnode<T>rightChild){
    this.element=element;
    this.leftChild=leftChild;
    this.rightChild=rightChild;
    this.height=1;
}
public String toString(){
    return element.toString();
}

    public T getElement() {
        return element;
    }

    public AVLnode<T> getLeftChild() {
        return leftChild;
    }

    public AVLnode<T> getRightChild() {
        return rightChild;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public void setLeftChild(AVLnode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(AVLnode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
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
class AVLtree<T extends Comparable<? super T>> implements SetSorted<T>{
private AVLnode<T> root;
private int size;
public AVLtree(){
    this.root=null;
    this.size=0;
}
    @Override
    public boolean IsEmpty() {
        return root==null;
    }

    @Override
    public int size() {
     return size;
             
    }

    @Override
    public boolean contains(T x) {
      AVLnode<T> currNode=root;
      
      while(currNode!=null){
          int comparation=x.compareTo(currNode.element);
          if(comparation==0){
              return true;
          }else if(comparation<0){
              currNode=currNode.getLeftChild();
          }else{
              currNode=currNode.getRightChild();
          }
      }
      
    return false;
    }

    @Override
    public void add(T x) {
        root=add(root,x);
    }
private AVLnode<T>add(AVLnode<T>nodo,T x){
    if(nodo==null){
        size++;
        return new AVLnode<T>(x);
    }
    int comparation=x.compareTo(nodo.getElement());
    if(comparation<0){
        nodo.setLeftChild(add(nodo.getLeftChild(), x));
    }else if(comparation>0){
        nodo.setRightChild(add(nodo.getRightChild(),x));
    }
    return balance(nodo);//en caso de que ya este ese elemento entonces no se agrega
}
    @Override
    public void remove(T x) {
        root=remove(root,x);
    }
    private AVLnode<T>remove(AVLnode<T>node,T x){
        if(node==null)return null;
        int comparation=x.compareTo(node.getElement());
        if(comparation<0)
            node.setLeftChild(remove(node.getLeftChild(), x));
        else if(comparation>0)
            node.setRightChild(remove(node.getRightChild(), x));
        else{
            if(node.getLeftChild()!=null&&node.getRightChild()!=null){
                node.setElement(getMin(node.getRightChild()));
                node.setRightChild(remove(node.getRightChild(), node.getElement()));
            }else{
                node=(node.getLeftChild()!=null)?node.getLeftChild():node.getRightChild();
               
            }
            size--;
        }
        return balance(node);
    }

    @Override
    public T getMin() {
        return getMin(root);
    }
    private T getMin(AVLnode<T> nodoInicial){
        AVLnode<T>nodo=nodoInicial;
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
      private T getMax(AVLnode<T>nodoInicial){
          AVLnode<T>nodo=nodoInicial;
          T maxElement=null;
          while(nodo!=null){
              maxElement=nodo.getElement();
              nodo=nodo.getRightChild();
          }
          return maxElement;
      }
private int height(AVLnode<T>nodo){
    return nodo==null?0:nodo.getHeight();//si el nodo es nulo su altura es cero
    
}
public int height(){
    return height(root);
}
private AVLnode<T>rotateWithLeftChild(AVLnode<T>b){//rota cuando el arbol tiene mas altura en su brazo izquierdo(rotacion simple)
    AVLnode<T> a=b.getLeftChild();
    b.setLeftChild(a.getRightChild());
    a.setRightChild(b);
    b.setHeight(Math.max(height(b.getLeftChild()),height(b.getRightChild()))+1);//primero se arregla la altura de b pues como b es hijo de a la altura de a depende de la altura de b
    a.setHeight(Math.max(height(a.getLeftChild()), b.getHeight())+1);
    return a;
}
private AVLnode<T>rotateWithRightChild(AVLnode<T> a){
    AVLnode<T>b=a.getRightChild();
    a.setRightChild(b.getLeftChild());
    b.setLeftChild(a);
    a.setHeight(Math.max(height(a.getLeftChild()), height(a.getRightChild()))+1);
    b.setHeight(Math.max(height(b.getRightChild()), a.getHeight())+1);
    return b;
}
        private AVLnode<T> doubleWithLeftChild(AVLnode<T>a){
            a.setLeftChild(rotateWithRightChild(a.getLeftChild()));
            return rotateWithLeftChild(a);
        }
        private AVLnode<T>doubleWithRightChild(AVLnode<T>a){
            a.setRightChild(rotateWithLeftChild(a.getRightChild()));
            return rotateWithRightChild(a);
        }
private AVLnode<T>balance(AVLnode<T>nodo){
    if(nodo==null)return nodo;
if(height(nodo.getLeftChild())-height(nodo.getRightChild())>1)
    if(height(nodo.getLeftChild().getLeftChild())>=height(nodo.getLeftChild().getRightChild()))
        nodo=rotateWithLeftChild(nodo);
    else nodo=doubleWithLeftChild(nodo);
else if(height(nodo.getRightChild())-height(nodo.getLeftChild())>1)
    if(height(nodo.getRightChild().getRightChild())>=height(nodo.getRightChild().getLeftChild()))
        nodo=rotateWithRightChild(nodo);
    else nodo=doubleWithRightChild(nodo);
nodo.setHeight(Math.max(height(nodo.getLeftChild()), height(nodo.getRightChild()))+1);
return nodo;

}                


}

