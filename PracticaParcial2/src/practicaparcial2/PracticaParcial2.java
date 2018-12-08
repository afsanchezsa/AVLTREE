/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaparcial2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author COMPAQ
 */
public class PracticaParcial2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
    BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
    String s=null;
        PriorityQueue<Proceso>procesos=new PriorityQueue<>();
        StringTokenizer st;
        Proceso []arreglo=new Proceso[200001];
        long llegada=0;
        while((s=sc.readLine())!=null){

       
       switch(s){
           case "TASK":
              
               int id=Integer.parseInt(sc.readLine());
               Proceso proc=new Proceso(id, Long.parseLong(sc.readLine()), sc.readLine(), llegada);
               procesos.add(proc);
               arreglo[id]=proc;
               llegada++;
               break;
           case "EXECUTE":
           Proceso p=procesos.poll();
           if(p==null)System.out.println("TASK NOT FOUND");
           else {
               System.out.println(p.description);
                 arreglo[p.getId()]=null;           
           }
           break;
           case "KILL":
               int i=Integer.parseInt(sc.readLine());
              Proceso a= arreglo[i];
              if(a==null){
                   System.out.println("TASK NOT FOUND");
              }else{
                  procesos.remove(a);
                  arreglo[i]=null;
                      System.out.println("TASK KILLED");
              }
              
              
              
              break;
           case "CHANGE":
                Proceso c= arreglo[Integer.parseInt(sc.readLine())];
               if(c==null){
                   System.out.println("TASK NOT FOUND");
              }else{
                  procesos.remove(c);
                  c.setPriority(Long.parseLong(sc.readLine()));
                  procesos.add(c);
                      System.out.println("TASK RESCHEDULED");
              }
               break;
           case "CLEAR":
               procesos=new PriorityQueue<>();
               arreglo=new Proceso[200001];
               llegada=0;
               System.out.println("CLEARED");
                   break;
           default:
               break;
              
               
       }
    }
    
    
    
    }
    
}

class Proceso implements Comparable<Proceso>{
    int id;
    long priority;
    String description;
   long llegada;

    public Proceso(int id, long priority, String description, long llegada) {
        this.id = id;
        this.priority = priority;
        this.description = description;
        this.llegada=llegada;
    }

    public void setLlegada(int llegada) {
        this.llegada = llegada;
    }

    public long getLlegada() {
        return llegada;
    }

    public int getId() {
        return id;
    }

    public Long getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(Proceso o) {
       if(this.priority!=o.priority){
        if(this.priority>o.priority)return -1;
       return 1;
       }
       if(this.llegada<o.llegada)return -1;
       return 1;
    }
    
}

