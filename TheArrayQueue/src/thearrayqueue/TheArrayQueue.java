/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thearrayqueue;

/**
 *
 * @author COMPAQ
 */
public class TheArrayQueue {

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

class ArrayQueue<T> implements Queue<T>{
    int front;
    int back;
    T[]queue;

    public ArrayQueue(int initialCapacity) {
        if(initialCapacity<1){
            throw new IllegalArgumentException("initial Capacity must be >=1");
        }
        
        
    queue=(T[])new Object[initialCapacity+1];
    front=back=0;
    }

    public ArrayQueue() {
        this(10);
    }
    
    @Override
    public boolean isEmpty() {
        return back==front;
    }

    @Override
    public T getFrontElement() {
        if(isEmpty()){
            return null;
        }else{
                    return queue[(front+1)%queue.length];
        }

        
    }

    @Override
    public void put(T theObject) {
        if((back+1)%queue.length==front){
            T[]newQueue=(T[])new Object[2*queue.length];
            int start=(front+1)%queue.length;
            if(start<2){
                System.arraycopy(queue, 0, newQueue, 0, queue.length-1);//-1 pues el front esta vacio
            }else{
                System.arraycopy(queue, start, newQueue, 0, queue.length-start);
                System.arraycopy(queue, 0, newQueue, queue.length-start, back+1);
            }
            front=newQueue.length-1;
            back=queue.length-2;
            queue=newQueue;
        }
        back=(back+1)%queue.length;
        queue[back]=theObject;
    }

    @Override
    public T remove() {
      if(isEmpty()){
          return null;
      }
      front=(front+1)%queue.length;
      T frontElement=queue[front];
      queue[front]=null;
      return frontElement;
              
    }
    
}
