/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maptree;

/**
 *
 * @author COMPAQ
 */
public class MyMapTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MapTree<Integer,String>usuarios=new MapTree<>();
        System.out.println("Empty:"+usuarios.isEmpty()+"Size:"+ usuarios.size());
        usuarios.put(3, "hi");
        usuarios.put(5, "this");
        usuarios.put(4, "is");
        usuarios.put(1, "a");
        usuarios.put(6, "test");
             System.out.println("Empty:"+usuarios.isEmpty()+"Size:"+ usuarios.size());
             System.out.println("key 7 is on map:"+usuarios.containsKey(7));
                          System.out.println("key 1 is on map:"+usuarios.containsKey(1));
                          System.out.println("key 6 is "+usuarios.get(6));
                          System.out.println("key 8 is "+usuarios.get(8));
                          System.out.println("height:"+usuarios.height());
                          
   }
    
}
interface MapSorted<K extends Comparable<?super K>,E>{
    boolean isEmpty();
    int size();
    boolean containsKey(K key);
    E get(K key);
    void put(K key, E x);
   void remove(K key);
}
class MapNode<K,E>{
    K key;
    E element;
    MapNode<K,E>leftChild,rightChild;
    int height;

    public MapNode(K key, E element) {
        this.key = key;
        this.element = element;
        this.height=1;
    }

    public MapNode() {
        this(null,null);
    }

    public void setKey(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public E getElement() {
        return element;
    }

    public MapNode<K, E> getLeftChild() {
        return leftChild;
    }

    public MapNode<K, E> getRightChild() {
        return rightChild;
    }

    public int getHeight() {
        return height;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public void setLeftChild(MapNode<K, E> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(MapNode<K, E> rightChild) {
        this.rightChild = rightChild;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
}
class MapTree<K extends Comparable<? super K >, E >implements MapSorted<K, E>{
private MapNode<K,E>root;
private int size;

    public MapTree() {
        this.root=null;
        size=0;
                
    }
    @Override
    public boolean isEmpty() {
       return root==null;
    }

    @Override
    public int size() {
           return size;
    }

    @Override
    public boolean containsKey(K key) {
     MapNode<K,E>nodo=root;
        while(nodo!=null){
         int comparation=key.compareTo(nodo.getKey());
         if(comparation==0){
             return true;
         }else if(comparation<0){
             nodo=nodo.getLeftChild();
         }else{
             nodo=nodo.getRightChild();
         }
     }
        return false;
    }

    @Override
    public E get(K key) {
         MapNode<K,E>nodo=root;
        while(nodo!=null){
         int comparation=key.compareTo(nodo.getKey());
         if(comparation==0){
             return nodo.getElement();
         }else if(comparation<0){
             nodo=nodo.getLeftChild();
         }else{
             nodo=nodo.getRightChild();
         }
     }
        return null;
    }

    @Override
    public void put(K key, E x) {
      root=add(root,key,x);
    }
private MapNode<K,E> add(MapNode<K,E>nodo,K key, E x){
      if(nodo==null){
        size++;
        return new MapNode<K,E>(key, x);
    }
    int comparation=key.compareTo(nodo.getKey());
    if(comparation<0){
        nodo.setLeftChild(add(nodo.getLeftChild(),key, x));
    }else if(comparation>0){
        nodo.setRightChild(add(nodo.getRightChild(),key,x));
    }else{
        nodo.setElement(x);
    }
    return balance(nodo);
}
    @Override
    public void remove(K key) {
        root=remove(root,key);
    }
        private MapNode<K,E>remove(MapNode<K,E>node,K key){
        if(node==null)return null;
        int comparation=key.compareTo(node.getKey());
        if(comparation<0)
            node.setLeftChild(remove(node.getLeftChild(), key));
        else if(comparation>0)
            node.setRightChild(remove(node.getRightChild(), key));
        else{
            if(node.getLeftChild()!=null&&node.getRightChild()!=null){
                MapNode<K,E>a=getMin(node.getRightChild());
                node.setElement(a.getElement());
                node.setKey(a.getKey());
                node.setRightChild(remove(node.getRightChild(), node.getKey()));
            }else{
                node=(node.getLeftChild()!=null)?node.getLeftChild():node.getRightChild();
               
            }
            size--;
        }
        return balance(node);
    }
            
    public MapNode<K,E> getMin() {
        return getMin(root);
    }
    private MapNode<K,E> getMin(MapNode<K,E> nodoInicial){
        MapNode<K,E>nodo=nodoInicial;
        MapNode<K,E> minElement=null;
        while(nodo!=null){
            minElement=nodo;
            nodo=nodo.getLeftChild();
        }
        return minElement;
                
                
    }

    
    public E getMax() {
        return getMax(root);
    }
      private E getMax(MapNode<K,E>nodoInicial){
          MapNode<K,E>nodo=nodoInicial;
          E maxElement=null;
          while(nodo!=null){
              maxElement=nodo.getElement();
              nodo=nodo.getRightChild();
          }
          return maxElement;
      }
    private MapNode<K,E>balance(MapNode<K,E>nodo){
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
    private int height(MapNode<K,E>nodo){
    return nodo==null?0:nodo.getHeight();//si el nodo es nulo su altura es cero
    
}
public int height(){
    return height(root);
}
private MapNode<K,E>rotateWithLeftChild(MapNode<K,E>b){//rota cuando el arbol tiene mas altura en su brazo izquierdo(rotacion simple)
    MapNode<K,E> a=b.getLeftChild();
    b.setLeftChild(a.getRightChild());
    a.setRightChild(b);
    b.setHeight(Math.max(height(b.getLeftChild()),height(b.getRightChild()))+1);//primero se arregla la altura de b pues como b es hijo de a la altura de a depende de la altura de b
    a.setHeight(Math.max(height(a.getLeftChild()), b.getHeight())+1);
    return a;
}
private MapNode<K,E>rotateWithRightChild(MapNode<K,E> a){
    MapNode<K,E>b=a.getRightChild();
    a.setRightChild(b.getLeftChild());
    b.setLeftChild(a);
    a.setHeight(Math.max(height(a.getLeftChild()), height(a.getRightChild()))+1);
    b.setHeight(Math.max(height(b.getRightChild()), a.getHeight())+1);
    return b;
}
        private MapNode<K,E> doubleWithLeftChild(MapNode<K,E>a){
            a.setLeftChild(rotateWithRightChild(a.getLeftChild()));
            return rotateWithLeftChild(a);
        }
        private MapNode<K,E>doubleWithRightChild(MapNode<K,E>a){
            a.setRightChild(rotateWithLeftChild(a.getRightChild()));
            return rotateWithRightChild(a);
        }
}