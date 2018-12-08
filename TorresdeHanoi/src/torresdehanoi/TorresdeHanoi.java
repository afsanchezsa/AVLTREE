/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torresdehanoi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 *
 * @author COMPAQ
 */
public class TorresdeHanoi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
                // TODO code application logic here
        BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
        int discos=Integer.parseInt(sc.readLine());
               
    Stack <Integer>pila1=new Stack<>();
           Stack <Integer>pila2=new Stack<>();
              Stack <Integer>pila3=new Stack<>();
              for(int i=discos;i>=1;i--){
                  pila1.push(i);
              }
             Stack<Integer>PilaDest=new Stack<>();
              Hanoi(discos, pila1, new Stack<Integer>(),PilaDest );
              System.out.println(PilaDest);
    }
      public static void Hanoi(int discos, Stack< Integer>origen,Stack< Integer>aux,Stack< Integer>dest){
        if(discos==1){
            dest.push(origen.pop());
        }else{
            Hanoi(discos-1,origen,dest,aux);
            dest.push(origen.pop());
            Hanoi(discos-1,aux,origen,dest);
        }
              
    }
    
    
    
}
