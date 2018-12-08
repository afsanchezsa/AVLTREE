/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhashmap;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author COMPAQ
 */
public class MyHashMap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HashDict<Integer,Integer>h=new HashDict<>(7);
        h.put(80, 180);h.put(40,140);h.put(65, 165);
        System.out.println(h);
        h.put(58, 158);h.put(80,124); h.put(2, 102);
        h.put(7,107); h.put(65, 121);
        System.out.println(h);
        System.out.println("Element "+h.get(2)+" found");
        System.out.println("Element "+h.get(58)+" found");
        System.out.println("Element "+h.remove(58)+" removed");
        System.out.println(h);
        System.out.println("Element "+h.get(2)+" found");
        System.out.println("Element "+h.get(58)+" found");
        
        
    }
    
}
class DataDict<K,E>{
    K key;
    E element;

    public DataDict() {
    this.key=null;
    this.element=null;
    }

    public DataDict(K key, E element) {
        this.key = key;
        this.element = element;
    }
    public String toString(){
        return "["+Objects.toString(element)+",key="+ Objects.toString(key)+"]";
    }
            
}
class HashDict<K,E>{
    protected int divisor;
    protected DataDict<K,E>[]table;
    protected boolean []neverUsed;
    protected int size;

    public HashDict(int theDivisor) {//el divisor es el tamaño de la tabla hash
        if(theDivisor<1)throw new IllegalArgumentException("Capacity of HashDict must be >=1");
        this.divisor = theDivisor;
        table=new DataDict[divisor];
        neverUsed=new boolean[divisor];
        Arrays.fill(neverUsed,true);
        size=0;
        
                
                
        
    }
    public HashDict(){
        this(10);
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public int probe(int i){
        return i*i;
    }
    private int search(K theKey){///search devuelve el indice donde se debe agregar el elemento con llave key o donde ya hay un elemento con esa llave
        int hash=Math.abs(theKey.hashCode())%divisor;
        int i=0;
        int pos;
        pos=(hash+probe(i))%divisor;
        do{
            if(neverUsed[pos]||(table[pos]!=null&&table[pos].key.equals(theKey)))
                return pos;
            i++;
         pos=(hash+probe(i))%divisor;
        }while(pos!=hash);
        return pos;
           
    }
   public E get(K theKey){
       int b=search(theKey);
       if(neverUsed[b]||!table[b].key.equals(theKey))
           return null;
       return table[b].element; 
   }
           
      public E put(K theKey,E theElement){
          if(2*(size+1)>divisor)
              rehash();//asi el factor de carga es siempre menor a 0.5 y se asegura que encontraremos un lugar libre para cualquier elemento nuevo
      int b=search(theKey);
      if(neverUsed[b]){
          table[b]=new  DataDict<K,E>(theKey,theElement);
          neverUsed[b]=false;
          size++;
          return null;//retorna null en caso de que inserte satisfactoriamente
      }else{
          if(table[b].key.equals(theKey)){//aqui entra solo si ya habia un objeto con esa llave entonces se procede a reemplazarlo
              E elementToReturn=table[b].element;//si ya habia un objeto con esa llave se reemplaza por the Element y retornamos el objeto que se reemplazo
              table[b].element=theElement;
              return elementToReturn;
          }else throw new IllegalArgumentException("insert failed");//esto ocurre si no encontro donde insertarlo
      }
      }
      private void rehash(){
          DataDict<K,E>old[]=table;
          divisor=divisor*2;
          table=new DataDict[divisor];
          neverUsed=new boolean[divisor];
          Arrays.fill(neverUsed, true);
          size=0;
          for(int i=0;i<divisor/2;i++){
              if(old[i]!=null){
                  put(old[i].key, old[i].element);
              }
          }
          
      }
      public E remove(K theKey){
          int b=search(theKey);
          if(neverUsed[b])
              return null;
          if(table[b].key.equals(theKey)){
              E elementToReturn=table[b].element;
              table[b]=null;
              size--;
              return elementToReturn;
          }
          else return null;//esto pasa si no encontro la llave
      }
      public String toString(){
          StringBuilder s=new StringBuilder("\n[");
          for(int i=0;i<divisor;i++){
              s.append("{"+Objects.toString(table[i])+","+(neverUsed[i]?"T":"F")+"}, ");
          }
          if(size>0)
              s.setLength(s.length()-2);//quitamos la ultimo ", "
          s.append("]\n");
          return new String(s);
      }
}
