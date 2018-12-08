/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thelinkedqueue;

/**
 *
 * @author COMPAQ
 */
public class TheLinkedQueue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
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
