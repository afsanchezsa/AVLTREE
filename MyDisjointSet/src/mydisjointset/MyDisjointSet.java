/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydisjointset;

import java.util.HashMap;

/**
 *
 * @author COMPAQ
 */
public class MyDisjointSet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Integer []ar={0,1,2,3,4,5,6,7,8,9};
            
        
        DisjoinSet<Integer>c=new DisjoinSet<>();
        c.Initialize(ar);
        System.out.println(c.find(4));
        c.Union(4, 5);
         System.out.println(c.find(4));
         c.Union(4, 6);
         System.out.println(c.find(5));
                System.out.println(c.find(4));
    }
    
}
class DisjoinSet<T>{
  T[]elements;
    HashMap<T, Integer>mapabusquedas;//asigna a cada elemento T el indice de su padre
    public DisjoinSet() {
    
    }
  public void Initialize(T []elements){
this.elements=elements;
        
    mapabusquedas=new HashMap<>();
      for(int i=0;i<elements.length;i++){
            
                   mapabusquedas.put( elements[i], i);//para cada elemento el indice del padre es el indice del elemento
      }

      
  }
  public Integer find(T x){//devuelve el indice del padre
      int indicepadre=mapabusquedas.get(x);
      if(x.equals(elements[indicepadre]))return indicepadre;
   Integer ind=find(elements[indicepadre]);  //ind sera el indice del elemento representativo del conjunto
   mapabusquedas.replace(x, ind);//ahora el padre de x es el que esta en el indice ind
      return ind;
      
      
  }
  public void Union(T x, T y){
      mapabusquedas.replace(elements[find(x)], find(y));//ahora el indice del elemento representativo del conjunto de x es el indice del elemento representativo del conjunto de y
  }
          
          
    
}
class Nodo<T>{
    T element;
    int parent;

    public Nodo(T element, int parent) {
        this.element = element;
        this.parent = parent;
    }
    
}