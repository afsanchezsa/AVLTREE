/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymaxheapcompleto;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.TreeMap;

/**
 *
 * @author COMPAQ
 */
public class MyMaxHeapCompleto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                MaxHeap<Integer>h=new MaxHeap<Integer>(10);
        h.add(new Integer(10));
        h.add(new Integer(20));
        h.add(new Integer(5));
        System.out.println(h.removeMax());
        System.out.println(h.removeMax());
        h.add(new Integer(15));
        h.add(new Integer(0));
        h.ChangePriority(new Integer(0), new Integer(20));
        h.remove(new Integer(5));
        System.out.println(h.removeMax());
        System.out.println(h.removeMax());
        System.out.println(h.removeMax());
        System.out.println(h.removeMax());
    }
    
}
interface PriorityQueue<T extends Comparable<? super T>>{
    boolean isEmpty();
    int size();
    void add(T x);
    T getMax();
    T removeMax();
}

class MaxHeap <T extends Comparable <? super T>> implements PriorityQueue<T>{
 TreeMap<T,Integer>arboldebusqueda;//nos dira en que indice esta el elemento que vamos a buscar
T[] BinaryHeap;
int size;
public MaxHeap(int initialCapacity){
    if(initialCapacity<1)throw new IllegalArgumentException("initialCapacity must be >=1");
    BinaryHeap=(T[])new Comparable[initialCapacity+1];
    size=0;
    arboldebusqueda=new TreeMap<>();    
}

    public MaxHeap() {
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

    @Override
    public void add(T x) {
       if(size==BinaryHeap.length-1){
           T[]old=BinaryHeap;
           BinaryHeap=(T[])new Comparable[2*(size+1)];
           System.arraycopy(old, 0,BinaryHeap, 0, size+1);
           
       }
       int currentNode=++size;
       while(currentNode!=1&&BinaryHeap[currentNode/2].compareTo(x)<0){
           BinaryHeap[currentNode]=BinaryHeap[currentNode/2];
           this.arboldebusqueda.put(BinaryHeap[currentNode], currentNode);//el put en caso de encontrar un elemento con esa clave simplemente lo reemplaza
           currentNode/=2;
                   
       }
      BinaryHeap[currentNode]=x;
       this.arboldebusqueda.put(BinaryHeap[currentNode], currentNode);
    }

    @Override
    public T getMax() {
       return (size==0)?null:BinaryHeap[1];
    }

    @Override
    public T removeMax() {
        if(size==0)return null;
        T maxElement=BinaryHeap[1];
        T lastElement=BinaryHeap[size--];
        int currentNode=1;
        int child=2;
        while(child<=size){
            if(child<size&&BinaryHeap[child].compareTo(BinaryHeap[child+1])<0)
                child++;///siempre child es el hijo maximo pues esto es un maxheap;
            if(lastElement.compareTo(BinaryHeap[child])>=0)
                break;
            BinaryHeap[currentNode]=BinaryHeap[child];
             this.arboldebusqueda.put(BinaryHeap[currentNode], currentNode);
            currentNode=child;
            child*=2;
            
        }
        BinaryHeap[currentNode]=lastElement;
         this.arboldebusqueda.put(BinaryHeap[currentNode], currentNode);
        return maxElement;
    }
    public String toString(){
        StringBuilder s=new StringBuilder();
        s.append("The "+size+"elements are [ ");
        if(size>0){
            s.append(BinaryHeap[1]);
            for(int i=2;i<=size;i++){
                s.append(", "+Objects.toString(BinaryHeap[i]));
                
            }
        }
        s.append("]");
        return new String(s);
                
    }
    public void initialize(ArrayLinearList<T>theList){
        size=theList.size();
        BinaryHeap=(T[])new Comparable[size+1];//pues el cero no se usa
        for(int i=1;i<BinaryHeap.length;i++)
            BinaryHeap[1]=theList.get(i-1);
        for(int root=size/2;root>=1;root--){
            T rootElement=BinaryHeap[root];
            int child=2*root;
            while(child<=size){
                if(child<size&&BinaryHeap[child].compareTo(BinaryHeap[child+1])<0)
                    child++;
                if(rootElement.compareTo(BinaryHeap[child])>=0)
                    break;
                BinaryHeap[child/2]=BinaryHeap[child];
                child*=2;
                
            }
            BinaryHeap[child/2]=rootElement;
        }
        for(int i=1;i<BinaryHeap.length;i++){
            this.arboldebusqueda.put(BinaryHeap[i], i);
        }
    }
    private Integer getElement(T element){//la idea es que introduzcan el objeto que quieren cambiar prioridad, si es por id crear
        //un nuevo objeto con el mismo id para que el compareto de cero luego cambiar las propiedades de ese objeto y asi cambia la prioridad
        Integer indice= this.arboldebusqueda.get(element);
        return indice!=null?indice:null;
        
    }
    public T remove(T element){
        Integer indice=getElement(element);
        if(indice!=null){
             this.arboldebusqueda.remove(element);
             IncrementarPrioridadAlInfinito(indice, element);
        return removeMax();
        }else{
            return null;//si no lo encuentra retorna nulo
        }
    }
    private void IncrementarPrioridadAlInfinito(int indice,T element){
        int currentNode=indice;//para asegurarse que es un numero par
     
        while(currentNode>1){
                   
            BinaryHeap[currentNode]=BinaryHeap[currentNode/2];
            this.arboldebusqueda.put(BinaryHeap[currentNode], currentNode);
            currentNode/=2;
            
        }
        BinaryHeap[currentNode]=element;
          this.arboldebusqueda.put(BinaryHeap[currentNode], currentNode);
    }
    public void ChangePriority(T element,T newElement){ //T element es el elemento al que se le va a cambiar la prioridad y newElement es el elemento por el que se va a cambiar element(ese newElement tiene la nueva prioridad) 
        Integer indice=getElement(element);
        if(indice!=null){
            this.arboldebusqueda.remove(element);
            
            if(newElement.compareTo(element)<0){
                DecrementarPrioridad(indice, newElement);
            }else {
                IncrementarPrioridad(indice, newElement);
            }
                
        }else{
            /*throw new IllegalArgumentException("el elemento no existe");*/ //si el elemento no existe no hace nada
        }
    
    
    
    }
    private void IncrementarPrioridad(int indice,T newElement){
       
        
        int currentNode=indice;//para asegurarse que es un numero par
     
        while(currentNode>1&&BinaryHeap[currentNode/2].compareTo(newElement)>0){
                   
            BinaryHeap[currentNode]=BinaryHeap[currentNode/2];
            this.arboldebusqueda.put(BinaryHeap[currentNode], currentNode);
            currentNode/=2;
            
        }
        BinaryHeap[currentNode]=newElement;
          this.arboldebusqueda.put(BinaryHeap[currentNode], currentNode);
    }
    private void DecrementarPrioridad(int indice,T newElement){
       
       int currentNode=indice*2;
       while(currentNode<=size){
           if(currentNode<size&&BinaryHeap[currentNode].compareTo(BinaryHeap[currentNode+1])<0)//si no ponemos currentNode<size currentNode+1 seria nulo
                currentNode++;
           if(BinaryHeap[currentNode].compareTo(newElement)<0)
           break;
           BinaryHeap[currentNode/2]=BinaryHeap[currentNode];
           this.arboldebusqueda.put(BinaryHeap[currentNode/2], currentNode/2);
           currentNode*=2;
       }
       BinaryHeap[currentNode/2]=newElement;
       this.arboldebusqueda.put(BinaryHeap[currentNode/2], currentNode/2);
       
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
public void add(T x){
    add(size(),x);
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

