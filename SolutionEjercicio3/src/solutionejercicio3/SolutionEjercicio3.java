/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solutionejercicio3;

import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author COMPAQ
 */
public class SolutionEjercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                Scanner sc=new Scanner(System.in);

      int n,a1,b,c;
        n=sc.nextInt();
        a1=sc.nextInt();
        b=sc.nextInt();
        c=sc.nextInt();
   
        
//LinkedList<Estudiante>estudiantes=new LinkedList<Estudiante>();
      Chain<Integer>noeliminados=new Chain<Integer>();
       
        int i=1;
        while(i<=n){
            noeliminados.add(new Integer(i));
            i++;
        }
  
       // int eliminados[]=new int [n-1];
        
       // eliminados[0]=a1+1;


       
   
long aindex;
  aindex=a1%noeliminados.size();
           noeliminados.remove(aindex);
        long ai=a1;
         
         // LinkedList<Integer>numerosporeliminar=new LinkedList<Integer>();
       // for(int s=1;s<n-1;s++){
       while(noeliminados.size()>1){  
       //aanterior=ai;
            //ai=((b*ai+c)%(1000000000+7));
           // System.out.println("ai:"+ai);
            
        
            
           
            //eliminados[s]=(eliminados[s-1]+ai+1)%n;
            //eliminados.add((eliminados.getLast()+ai+1)%n);
            //noeliminados.remove((aanterior+ai+1)%n);
            ai=((b*ai+c)%(1000000000+7));
            aindex=(aindex+ai)%noeliminados.size();
        
       noeliminados.remove(aindex);

       }
        //for(int p=0;p<eliminados.length;p++){
         //   System.out.println(eliminados[p]);
        //}
        for(long m=0;m<noeliminados.size();m++){
            System.out.println(noeliminados.get(m));
        }
        
          
    }
    
}
class Chain<T> implements LinearList<T>,Iterable<T>{
    protected Nodo<T> firstNode;
    protected Nodo<T>LastNode;
    protected long size;
    public Chain(){
        firstNode=null;
 
        size=0;
    }
    
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public long size() {
        return size;
    }
    void CheckIndex(long i){
        if(i<0||i>=size){
            throw new IndexOutOfBoundsException("index="+i+"size= "+size);
        }
    }

    @Override
    public T get(long i) {//EL INDICE ES UN LONG PUES NOS VAN A PASAR VALORES DE 10 A LA 9 Y ESO NO LO SOPORTA UN INT(LO QUE HACE QUE ACTUE DE FORMA EXTRAÑA)
        CheckIndex(i);
        Nodo<T>currentNode=firstNode;
for(int j=0;j<i;j++){
    currentNode=currentNode.next;
}
return currentNode.element;
                
    }

    @Override
    public long indexOf(T x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
public boolean remove(long i) {
       CheckIndex(i);
        
        if(i==0){//CASO ESPECIAL SI EL QUE SE ELIMINA ES EL PRIMERO
        firstNode=firstNode.next;
        firstNode.anterior=null;
        }else if(i==size-1){//CASO ESPECIAL SI EL QUE SE ELIMINA ES EL ULTIMO
            LastNode=LastNode.anterior;
            LastNode.next=null;
        }
        else if(i<=size/2){//SI EL INDICE ES MENOR QUE LA MITAD DEL SIZE DE LA LISTA VOY A BUSCAR EL NODO PARTIENDO DEL INICIO DE LA LISTA PARA LUEGO ELIMINARLO
            Nodo<T>q=firstNode;
            for(int j=0;j<i-1;j++){
                q=q.next;
            }//AL FINAL DEL CICLO NOS ENCONTRAMOS SOBRE EL NODO ANTERIOR AL NODO QUE VA A SER ELIMINADO
            Nodo<T>aux;
            aux=q.next.next;
            aux.anterior=q;
            q.next=aux;
          
        }
        else{
            long numero=size-1-i;//SI EL INDICE ES MAYOR QUE LA MITAD DEL SIZE DE LA LISTA VOY A BUSCAR EL NODO PARTIENDO DEL NODO FINAL DE LA LISTA PARA LUEGO ELIMINARLO
             Nodo<T>q=LastNode;//ESTA DIVISION DE CASOS DEPENDIENDO SI EL INDICE ESTA ANTES O DESPUES DE LA MITAD DE LA LISTA SE HACE PARA ELIMINAR MAS RAPIDO EL NODO ASI ENCUENTRO EL NODO A ELIMINAR MAS RAPIDO, ARRANCANDO POR EL NODO (INICIAL O FINAL)MAS CERCANO AL NODO A ELIMINAR
            for(int j=0;j<numero-1;j++){
                q=q.anterior;
                        
            }//AL FINAL DEL CICLO NOS ENCONTRAMOS SOBRE EL NODO SIGUIENTE AL NODO QUE VA A SER ELIMINADO
            q.anterior=q.anterior.anterior;
            q.anterior.next=q;
            
            
        }
            
            
          
   
        
        size--;
        return true;
    }

    @Override
    public void add(T x) {
        if(this.size==0){
            firstNode=LastNode=new Nodo<T>(x,null,null);//para que el next de ese nodo sea el mismo firstNode se agrega firstnode en el segundo parametro
            
        }else{
          
            LastNode.next=new Nodo<T>(x,LastNode,null);//ese nodo que agregue al final va a tener next=null, por eso use el constructor con un solo argumento
            LastNode=LastNode.next;//SIEMPRE AGREGAMOS EL NODO AL FINAL DE LA LISTA(SI FUERA CON UNA LISTA ENLAZADA SIMPLE SERIA MAS LARGO PUES DEBERIAMOS RECORRER DESDE EL NODO INICIAL HASTA EL FINAL PARA LUEGO AGREGAR UN NODO AL FINAL, POR ESO ESTA LISTA ENLAZADA DOBLE ES MAS EFICIENTE
        }
        this.size++;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
   
    

}

class Nodo<T>{
        T element;
        Nodo<T> next;
        Nodo<T>anterior;
        public Nodo(){
            this(null,null,null);
        }
        public Nodo(T element){
            this(element,null,null);
            
        }
        public Nodo(T element,Nodo<T>last,Nodo<T> next){
            this.element=element;
            this.next=next;
            this.anterior=last;
        }
            }

 interface LinearList<Y> {
     boolean isEmpty();
        long size();
     Y get(long i);
     long indexOf(Y x);
     boolean remove(long i);
     void add(Y x);
     String toString();
    
    
    
}

