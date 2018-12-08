/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thelinkedstack;

import java.util.EmptyStackException;

/**
 *
 * @author COMPAQ
 */
public class TheLinkedStack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
interface Stack<T>{
    boolean isEmpty();
    T peek();
    void push(T theObject);
    T pop();
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
class LinkedStack<T> implements Stack<T>{
protected ChainNode<T>topNode;

    public LinkedStack() {
        this.topNode=null;
    }

    @Override
    public boolean isEmpty() {
        return topNode==null;
    }

    @Override
    public T peek() {
       if(isEmpty()){
           throw new EmptyStackException();
       }
       return topNode.element;
    }

    @Override
    public void push(T theObject) {
        topNode=new ChainNode<>(theObject, topNode);
    }

    @Override
    public T pop() {
       if(isEmpty()){
           throw new EmptyStackException();
       }
       T topElement=topNode.element;
       topNode=topNode.next;
    return topElement;
            
    }
    


}
