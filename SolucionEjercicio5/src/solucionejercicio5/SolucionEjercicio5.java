/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solucionejercicio5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author COMPAQ
 */
public class SolucionEjercicio5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
                // TODO code application logic here
        //Scanner sc=new Scanner(System.in);
        StringBuilder resultado=new StringBuilder("");
    BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
        int operaciones=Integer.parseInt(sc.readLine());
           PriorityQueue <Reclamo>cola=new PriorityQueue<>(operaciones,new Comparador());
        DecimalFormat f=new DecimalFormat("#.0");
     
       int i=0;
       String tipo,reclamo;
       int papa=0;
       double papadecimal;
       String  documento="";
      
       while(i<operaciones){
       tipo=sc.readLine()
               ;
          
       if(tipo.equals("reclamo")){
            documento=sc.readLine();
                    
       reclamo=sc.readLine();
       
       papadecimal=Double.parseDouble(sc.readLine());

      // papa=(int)(Double.parseDouble(f.format(papadecimal).replace(",", "."))*10);
          
       cola.add(new Reclamo(documento, reclamo, papadecimal,i));
               
       }else{
           if(cola.isEmpty()&&tipo.equals("atender")){
          //if(i==operaciones-1){
            //  resultado.append("No hay reclamos pendientes");
          //}else{
              resultado.append("No hay reclamos pendientes"+"\n");
          //}
               
               
               
              
           }else{
                Reclamo r=cola.remove();
                //if(i==operaciones-1){
            // resultado.append(r.documento+" "+r.reclamacion);
          //}else{
              resultado.append(r.documento+" "+r.reclamacion+"\n");
          //}
           }
       
       }
       i++;
       }
        System.out.print(resultado.toString());
               
        //if(cola.isEmpty())System.out.println("No hay reclamos pendientes");
      
       
         
   
     /*  DecimalFormat d=new DecimalFormat("#.0");
       double a=0.17, b=0.1;
       double apos,bpos;
       apos=Double.parseDouble(d.format(a).replace(",", "."));
       bpos=Double.parseDouble(d.format(b).replace(",", "."));
        System.out.println(apos+" "+bpos);
     */
        
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
class Comparador implements Comparator<Reclamo> {
public Comparador(){
    
}
    @Override
    public int compare(Reclamo o1, Reclamo o2) {
           
        /*if(o1.papa>o2.papa){
            return  -1;
        }else if (o1.papa<o2.papa){
            return 1;
            
        }else if(o1.llegada<o2.llegada){
            return -1;
        }else if(o1.llegada>o2.llegada){
            return 1;
        }else {
            return 0;
        }
        */
        return o1.compareTo(o2);
    }

 
    
}


