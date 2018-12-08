/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio7;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author COMPAQ
 */
public class Ejercicio7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                String caso="",nombre="",carrera="";
        Colaprioritaria cola=new Colaprioritaria();
        ArrayList<Colaprioritaria>carreras=new ArrayList<>();
        //VAMOS A TENER UN ARRAYLIST DE COLASPRIORITARIAS (CADA COLA PRIORITARIA ES DE UNA CARRERA)
       
       Scanner sc=new Scanner(System.in);
       
       while(true){
           caso=sc.next();
            if(caso.equals("LLEGA")){
           nombre=sc.next();
           carrera=sc.next();
          boolean agregado=false;//PONEMOS LA VARIABLE AGREGADO EN FALSO Y LA PONDREMOS EN VERDADERO APENAS AGREGUEMOS AL ESTUDIANTE
          if(carreras.isEmpty()){//SI CARRERAS ESTA VACIO DEBEMOS AGREGAR UNA COLA PRIORITARIA CON UN ESTUDIANTE QUE TIENE NOMBRE Y CARRERA
                  Colaprioritaria nuevacola=new Colaprioritaria();
              nuevacola.add(new Estudiante(nombre,carrera));
              carreras.add(nuevacola);  
              agregado=true;
          }else{
             for(int i=0;i<carreras.size();i++){//SI LA COLA NO ESTA VACIA RECORREMOS EL ARRAYLIST DE CARRERAS 
                 //HASTA ENCONTRAR QUE EL PEEK DE LA COLA PRIORITARIA TENGA LA MISMA CARRERA QUE EL NUEVO ELEMENTO A AGREGAR
                 //SI ENCUENTRA EL PEEK QUE TENGA LA MISMA CARRERA ENTONCES SE INSERTA EL ESTUDIANTE EN ESA COLA(LA QUE TIENE EL PEEK CON LA MISMA CARRERA)
                 
               if(carreras.get(i).peek().carrera.equals(carrera)){
                   carreras.get(i).add(new Estudiante(nombre, carrera));
                   agregado=true;
                   break;
               }
             
              //SI DESPUES DE BUSCAR EL PEEK CON LA MISMA CARRERA  NO SE ENCONTRO ENTONCES SE CREA UNA NUEVA COLA PRIORITARIA Y SE AGREGA AL FINAL DEL ARRAYLIST DE CARRERAS
           }
           if(!agregado){
                 Colaprioritaria nuevacola=new Colaprioritaria();
              nuevacola.add(new Estudiante(nombre,carrera));
              carreras.add(nuevacola);  
           }  
          }
          
          
           
               
        }else if(caso.equals("ATIENDE")){
               // System.out.println(cola.remove().nombre);
               if(carreras.isEmpty()){
                   System.out.println("Forever Alone");//SI EL ARRAYLIST DE CARREAS ESTA VACIO IMPRIME FOREVER ALONE
               }
               else if(carreras.get(0).isEmpty()){//SI LA PRIMERA COLA DEL ARRAYLIST ESTA VACIA LA REMUEVE E IMPRIME 
                    carreras.remove(0);
                     System.out.println(carreras.get(0).remove().nombre);//IMPRIME EL PRIMER ELEMENTO DE LA PRIMERA COLA DEL ARRAYLIST CARRERAS
                }else{
                     System.out.println(carreras.get(0).remove().nombre);
                     if(carreras.get(0).isEmpty()){//SI DEPUES DE REMOVER EL PRIMER ELEMENTO DE LA PRIMERA COLA ESTA COLA QUEDA VACIA, REMUEVO ESA COLA DEL ARRAYLIST DE CARRERAS
                         carreras.remove(0);
                     }
                }
        }else{
            break;
        }
       }
       
       
        
        
    }
    
}

class Estudiante{
   public String nombre;
   public String carrera;

    public Estudiante(String nombre, String carrera) {
        this.nombre = nombre;
        this.carrera = carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCarrera() {
        return carrera;
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

    public T getElemento() {
        return elemento;
    }
    

   
    
}
class Colaprioritaria {
    NodoCola<Estudiante>front ,back;
    public int size;
    
    
  
    public Colaprioritaria(){
        this.size=0;
        front=new NodoCola(new Estudiante("", ""));
        back=null;
       
    }
    public void add(Estudiante element){
      if(back==null){
          
          back=new NodoCola(element);
          
          front.next=back;
          return;   
      }else{
          
           NodoCola<Estudiante>aux=new NodoCola<>(element);
           back.next=aux;
           back=back.next;
                   
          }
              
          
          
          
    }
    
    public boolean isEmpty(){
        return this.front.next==null;
    }
    public Estudiante remove(){
        if(isEmpty()){
            return null;
        }else{
            Estudiante elemento=this.front.next.elemento;
          this.front=this.front.next;  
          this.front.elemento=new Estudiante("","");
        
           return elemento;
        }
       
    }
    public Estudiante peek(){
        
        
        return front.next.elemento;
    }

 
}


