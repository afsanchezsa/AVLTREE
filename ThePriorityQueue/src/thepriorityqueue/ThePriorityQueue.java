/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thepriorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author COMPAQ
 */
public class ThePriorityQueue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
                // TODO code application logic here
       
        Colaprioritaria <Reclamo>papa1;
        papa1 = new Colaprioritaria<>(new Comparador());
        
            BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
           int op=Integer.parseInt(sc.readLine());
           String doc;
           
           int i=0;
           double papa;
           String tipo="",descripcion;
           while(i<op){
              tipo=sc.readLine();
              if(tipo.equals("reclamo")){
                  doc=sc.readLine();
                  descripcion=sc.readLine();
                   papa=Double.parseDouble(sc.readLine());
                   papa1.add(new Reclamo(doc,descripcion,papa,i));
                 
              }else{
                  if(papa1.isEmpty()){
                      System.out.println("No hay reclamos pendientes");
                  }else{
                      Reclamo r=papa1.remove();
                      System.out.println(r.documento+" "+r.reclamacion);
                  }
                  
                  
              }
              i++;
           }
        
    }
    
}
class Colaprioritaria <T> implements Iterable<T>{
    NodoCola<T>front ,back,Nodo1,Nodo2,Nodo3,Nodo4;
    public int size;
    Comparator<T>comparador;
    public Colaprioritaria(Comparator <T> c){
        this.size=0;
        front=new NodoCola(null);
        back=null;
        this.comparador=c;
    }
    public void add(T element){
      if(back==null){
          
          back=new NodoCola(element);
          front.next=back;
          return;   
      }else{
          NodoCola<T>nuevo=new NodoCola(element);
          NodoCola<T>p=front;
          while(p.next!=null){
              if(comparador.compare(nuevo.elemento,p.next.elemento)==-1){
                  nuevo.next=p.next;
                  p.next=nuevo;return;
              }else{
                  p=p.next;
              }
          }
          p.next=nuevo;
          back=p.next;
      }
      
    }
    public boolean isEmpty(){
        return this.front.next==null;
    }
    public T remove(){
        if(isEmpty()){
            return null;
        }else{
            T elemento=this.front.next.elemento;
          this.front=this.front.next;  
          this.front.elemento=null;
           return elemento;
        }
       
    }

    @Override
    public Iterator<T> iterator() {
        return new iteradordecolas<T>(this.front);
    }
}
class iteradordecolas<T> implements Iterator<T>{
private NodoCola<T>nextNode;


    public iteradordecolas(NodoCola<T>nodoinicial) {
        this.nextNode=nodoinicial;
    
    }

    @Override
    public boolean hasNext() {
        return !(nextNode==null);
    }

    @Override
    public T next() {
       T element=nextNode.elemento;
       nextNode=nextNode.next;
       return element;
    }

    
}
class NodoCola<T> {
    public T elemento;
    NodoCola<T>next;
    NodoCola <T>back;
    public NodoCola(T elemento){
        this.elemento=elemento;
    }

    public NodoCola() {
        this(null);
    }

   
    
}
class Reclamo implements Comparable<Reclamo>{
public double  papa;
public String  documento;
public String reclamacion;
public int llegada;
    public Reclamo(String documento ,String reclamacion, double papa, int llegada) {
  this.papa=papa;
  this.documento=documento;
  this.reclamacion=reclamacion;
  this.llegada=llegada;
    }

   @Override
    public int compareTo(Reclamo o) {
         if (this.papa>o.papa){
           return -1;
       }else if(this.papa<o.papa){
           return 1;
       }else if(this.llegada<o.llegada){
           return -1;
       }else if(this.llegada>o.llegada){
      
           return 1;
       }
       return 0;//aqui se puede retornar cualquier valor porque nunca se llagara a este punto
           
          
            
    }
     }
class Comparador implements Comparator<Reclamo>{
public Comparador(){
    
}
/*public Comparador( Iterable<? extends Reclamo> items){
    
}*/
    @Override
    public int compare(Reclamo o1, Reclamo o2) {
     return o1.compareTo(o2);
    }

  

   
    
}
