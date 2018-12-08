/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author COMPAQ
 */
public class Ejercicio9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Proceso>procesos=new ArrayList<>();
        BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
        //ESTA NO ES LA SOLUCION VALIDA , LA SOLUCION VALIDA ES LA DE LA CLAS TESTCLASS EN DEFAULT PACKAGE
    }
class Proceso implements Comparable<Proceso>{
    int id;
    int llegada;
    double priority;
    String descripcion;

        public Proceso(int id, double priority, String descripcion) {
            this.id = id;
            this.priority = priority;
            this.descripcion = descripcion;
        }

        @Override
        public int compareTo(Proceso o) {
            if(this.priority>o.priority){
                return -1;
            }else if (this.llegada<o.llegada){
                return -1;
            }else{
                return 1;
            }
        }

       
    
}
interface PriorityQueue{
    boolean isEmpty();
    int size();
    void add(Proceso x);
    Proceso getMax();
    Proceso removeMax();
}
class MaxHeap implements PriorityQueue{
    Proceso[]BinaryHeap;
      private int size;
      public MaxHeap(int initialCapacity){
          if(initialCapacity<1) throw new IllegalArgumentException("Initial Capacity must be >=1");
          BinaryHeap=new Proceso [initialCapacity+1];
          size=0;
      }
      public MaxHeap(){
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
        public void add(Proceso x) {
           if (size==BinaryHeap.length-1){
               Proceso[] old=BinaryHeap;
               BinaryHeap=(Proceso[])new Comparable[(size+1)*2];
               System.arraycopy(old, 0, BinaryHeap, 0, size+1);
           }
           int currentNode=++size;
           while(currentNode!=1&&BinaryHeap[currentNode/2].compareTo(x)<0){
               BinaryHeap[currentNode]=BinaryHeap[currentNode/2];
               currentNode/=2;
           }
           BinaryHeap[currentNode]=x;
            
        }

        @Override
        public Proceso getMax() {
           return (size==0)?null:BinaryHeap[1]; 
        }

        @Override
        public Proceso removeMax() {
            if(size==0)return null;
            Proceso maxElement=BinaryHeap[1];
            Proceso lastElement=BinaryHeap[size--];
            int currentNode=1;
            int child=2;
            while(child<=size){
                if(child<size&&BinaryHeap[child].compareTo(BinaryHeap[child+1])<0){
                    child++;
                }
                if (lastElement.compareTo(BinaryHeap[child])>=0){
                    break;
                }
                BinaryHeap[currentNode]=BinaryHeap[child];
                currentNode=child;
                child*=2;
            }
            BinaryHeap[currentNode]=lastElement;
            return maxElement;
        }
public  void initialize (ArrayList<Proceso>theList){
       size=theList.size();
       BinaryHeap=(Proceso[])new Comparable[size+1];
       for(int i=1;i<BinaryHeap.length;i++){
           BinaryHeap[i]=theList.get(i-1);
       }
       for(int root=size/2;root>=1;root--){
           Proceso rootElement=BinaryHeap[root];
           int child=2*root;
           while(child<=size){
               if(child<size&&BinaryHeap[child].compareTo(BinaryHeap[child+1])<0){
                   child++;
               }
               if(rootElement.compareTo(BinaryHeap[child])>=0)
                   break;   
               BinaryHeap[child/2]=BinaryHeap[child];
               child*=2;
           }
           BinaryHeap[child/2]=rootElement;
       }
   }  
public void ChangePriority(int indice,double newPriority){
    if(BinaryHeap[indice].priority<newPriority){
        AumentarPrioridad(indice, newPriority);
    }else{
        DisminuirPrioridad(indice, newPriority);
    }
}
    private void AumentarPrioridad(int indice ,double newPriority){
           int indiceasubir=indice;
           BinaryHeap[indiceasubir].priority=newPriority;
           Proceso cambiado= BinaryHeap[indiceasubir];
           int currentNode=indice;
           while(currentNode!=1&&BinaryHeap[currentNode/2].compareTo(BinaryHeap[indiceasubir])<0){
               BinaryHeap[currentNode]=BinaryHeap[currentNode/2];
               currentNode/=2;
           }
           BinaryHeap[currentNode]=cambiado;
            
    }
    private void DisminuirPrioridad(int indice,double newPriority){
        
          
            Proceso lastElement=BinaryHeap[indice];
            lastElement.priority=newPriority;
            int currentNode=indice;
            int child=indice*2;
            while(child<=size){
                if(child<size&&BinaryHeap[child].compareTo(BinaryHeap[child+1])<0){
                    child++;
                }
                if (lastElement.compareTo(BinaryHeap[child])>=0){
                    break;
                }
                BinaryHeap[currentNode]=BinaryHeap[child];
                currentNode=child;
                child*=2;
            }
            BinaryHeap[currentNode]=lastElement;
           
    }
    
    
    
    public Proceso Eliminar(int indice){
           if(size==0)return null;
            Proceso maxElement=BinaryHeap[indice];
            Proceso lastElement=BinaryHeap[size--];
            int currentNode=indice;
            int child=indice*2;
            while(child<=size){
                if(child<size&&BinaryHeap[child].compareTo(BinaryHeap[child+1])<0){
                    child++;
                }
                if (lastElement.compareTo(BinaryHeap[child])>=0){
                    break;
                }
                BinaryHeap[currentNode]=BinaryHeap[child];
                currentNode=child;
                child*=2;
            }
            BinaryHeap[currentNode]=lastElement;
            return maxElement;
    }
}
     
}
