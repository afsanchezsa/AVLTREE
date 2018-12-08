/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thechain;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 *
 * @author COMPAQ
 */
public class TheChain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
interface LinearList<T>{
    boolean isEmpty();
    int size();
    T get(int i);
    int indexOf(T x);
    T remove(int i);
    void add(int i,T x);
    String toString();
    
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
class Chain<T>implements LinearList<T>,Iterable<T>{
protected ChainNode<T> firstNode;
protected int size;

    public Chain() {
        firstNode=null;
        size=0;
    }
    


@Override
    public boolean isEmpty() {
            return size==0;
    }

    @Override
    public int size() {
        return size;
    }
void CheckIndex(int i){
    if(i<0||i>=size){//puede ser size pues alli se puede insertar algo
        throw new IndexOutOfBoundsException("index="+i+"size="+size);
    }
}
    @Override
    public T get(int i) {
        CheckIndex(i);
        ChainNode<T>currentNode=firstNode;
        for(int j=0;j<i;j++){
            currentNode=currentNode.next;
        }
        return currentNode.element;//en este punto j=i 
    }

    @Override
    public int indexOf(T x) {
        ChainNode<T>currentNode=firstNode;
        int i=0;
        while(currentNode!=null&&!currentNode.equals(x)){
            currentNode=currentNode.next;
                  
             i++;
        }
        if(currentNode==null){
            return -1;//retorna -1 si no encuentra el elemento
        }else{
            return i;
        }
    }

    @Override
    public T remove(int i) {
        CheckIndex(i);
        T removedElement;
        if(i==0){
            removedElement=firstNode.element;
            firstNode=firstNode.next;
        }else{
            ChainNode<T>q=firstNode;
            for(int j=0;j<i-1;j++){
                q=q.next;
            }//al finalizar el ciclo quedamos en i-1, entonces q.next es el elemento de indice i
            removedElement=q.next.element;
            q.next=q.next.next;
        }
        size--;
        return removedElement;
    }

    @Override
    public void add(int i, T x) {
       if(i<0||i>size){
           throw new IndexOutOfBoundsException("index="+i+"size"+size);
       }
       if(i==0){
          firstNode=new ChainNode<>(x,firstNode);
          
       }else{
           ChainNode<T>p=firstNode;
           for(int j=0;j<i-1;j++){
               p=p.next;
           }
           p.next=new ChainNode<>(x,p.next);
           
       }
       size++;
    }
    public String toString(){
         StringBuilder s=new StringBuilder("[");
        for(T x:this){
            s.append(Objects.toString(x)+", ");
            if(size>0){
                s.setLength(s.length()-2);
                s.append("]");
            }
            
        }
        return new String(s);
    }
    @Override
    public Iterator<T> iterator() {
        return new ChainIterator<T>(firstNode);
    }
    
}
class ChainIterator <T>implements Iterator<T>{
ChainNode<T>nextNode;

    public ChainIterator(ChainNode<T> firstNode) {
        this.nextNode = firstNode;
    }

    @Override
    public boolean hasNext() {
       return nextNode!=null;
    }

    @Override
    public T next() {
        if(nextNode!=null){
           T elementToReturn=nextNode.element;
           nextNode=nextNode.next;
           return elementToReturn;
                
                  
        }else{
            throw new NoSuchElementException("No next Element");
        }
        
    }

    @Override
    public void remove() {
     throw  new UnsupportedOperationException("remove no suported");
    }
    
}



//pila derivada de chain
interface Stack<T>{
    boolean isEmpty();
    T peek();
    void push(T theObject);
    T pop();
}
class DerivedLinkedStack<T>extends Chain<T> implements Stack<T>{

    public DerivedLinkedStack() {
    super();
    }
public boolean isEmpty(){
    return super.isEmpty();
            

}
    @Override
    public T peek() {
       return get(0);
    }

    @Override
    public void push(T theObject) {
        add(0, theObject);
    }

    @Override
    public T pop() {
      if(isEmpty()){
          throw new EmptyStackException();
      }
      return remove(0);
    }

   
    
}

