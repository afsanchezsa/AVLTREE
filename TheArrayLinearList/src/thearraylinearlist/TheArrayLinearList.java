/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thearraylinearlist;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 *
 * @author COMPAQ
 */
public class TheArrayLinearList {

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
    int IndexOf(T x);
    T remove(int i);
    void add(int i,T x);
    @Override
    String toString();
            
}
class ArrayLinearList<T> implements LinearList<T>,Iterable<T>{
protected T[] element;
protected int size;

    public ArrayLinearList(int initialCapacity) {
        if(initialCapacity<1){
            throw new IllegalArgumentException("Initial Capacity must be >=1");
       
        }
         element=(T[]) new Object[initialCapacity];
        size=0;
    }

    public ArrayLinearList(){
    this(10);
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
    if(i<0||i>=size){
        throw new IndexOutOfBoundsException("index= "+i+"size= "+size);
    }
}
    @Override
    public T get(int i) {
        CheckIndex(i);
        return element[i];
    }

    @Override
    public int IndexOf(T x) {
        for(int i=0;i<size;i++){
            if(element[i].equals(x)){
                return i;
            }
        }
        return -1;//retorna -1 en caso de no encontrar el elemento
    }

    @Override
    public T remove(int i) {
        CheckIndex(i);
        T removedElement=element[i];
        for(int j=i+1;j<size;j++){
            element[j-1]=element[j];
        }
        element[--size]=null;
        return removedElement;
        
    }

    @Override
    public void add(int i, T x) {
        if(i<0||i>size){
            throw new IndexOutOfBoundsException("index="+i+"size="+size);
        }
        if(size==element.length){
            T[]old=element;
            element=(T[])new Object[size*2];
            System.arraycopy(old, 0, element, 0, size);//el ultimo es la cantidad de elementos que desea copiar
        }
        for(int j=size-1;j>=i;j--){
            element[j+1]=element[j];
        }
        element[i]=x;
        size++;
        
            
    }
    public String toString(){
        StringBuilder s=new StringBuilder("[");
        for(T x:this){
            s.append(Objects.toString(x)+", ");
        
            
        }
            if(size>0){
                s.setLength(s.length()-2);
                
            }   
            s.append("]");
        return new String(s);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayLinearListIterator<T>(this);
    }
    
}
class ArrayLinearListIterator <T>implements Iterator<T>{
private ArrayLinearList<T>list;
private int nextIndex;
    public ArrayLinearListIterator(ArrayLinearList<T>theList) {
        list=theList;
        nextIndex=0;
    }

    @Override
    public boolean hasNext() {
        return nextIndex<list.size;
    }

    @Override
    public T next() {
      if(nextIndex<list.size){
          return list.element[nextIndex++];
      }else{
          throw new NoSuchElementException("No next Element");
      }
    }

    @Override
    public void remove() {
       throw new UnsupportedOperationException("remove no supported");
       
    }

    
    
}


// pila derivada de arrayLinearList
interface Stack<T>{
    boolean isEmpty();
    T peek();
    void push(T theObject);
    T pop();
}
class DerivedArrayStack<T>extends ArrayLinearList<T> implements Stack<T>{
public DerivedArrayStack(int initialCapacity){
    super(initialCapacity);
}
    public DerivedArrayStack() {
       this(10);
    }
@Override
     public boolean isEmpty(){
         return super.isEmpty();
     }
    @Override
    public T peek() {
       if(isEmpty()){
           throw new EmptyStackException();
           
       }return get(size-1);
    }

    @Override
    public void push(T theObject) {
        add(size(), theObject);
    }

    @Override
    public T pop() {
        if(isEmpty()){
            throw new EmptyStackException();
            
        }
        return remove(size-1);
                
    }

    
    
   
    
}
