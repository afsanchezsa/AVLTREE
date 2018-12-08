/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thearraystack;

import java.util.EmptyStackException;

/**
 *
 * @author COMPAQ
 */
public class TheArrayStack {

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
class ArrayStack<T> implements Stack<T>{
int top;
T[]stack;
    public ArrayStack(int initialCapacity) {
    if(initialCapacity<1){
        throw new IllegalArgumentException("Capacity must be >=1");
        
    }
    stack=(T[])new Object[initialCapacity];
    top=-1;
    
    
    }

    public ArrayStack() {
    this(10);
    }
    

    @Override
    public boolean isEmpty() {
       return top==-1;
    }

    @Override
    public T peek() {
             if(isEmpty()){
           throw new EmptyStackException();
       }
       return stack[top];
    }

    @Override
    public void push(T theObject) {
     if(top==stack.length-1){
         T[]old=stack;
         stack=(T[])new Object[2*stack.length];
         System.arraycopy(old, 0, stack,0,old.length);
     }
     stack[++top]=theObject;
   
     
    }

    @Override
    public T pop() {
  if(isEmpty()){
      throw new EmptyStackException();
  }
  T topElement=stack[top];
  stack[top--]=null;
  return topElement;
    }
    
}