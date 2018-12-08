/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solutionejercicio4;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author COMPAQ
 */
public class SolutionEjercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                 Scanner br=new Scanner(System.in);
        String s="",tipo="";
       
            tipo=br.nextLine();
            s=br.nextLine();
             
      
        
        if(tipo.equals("INFIJA")){
                  Stack pila=new Stack();
        //char[]arreglocaracteres=s.toCharArray();
        String []arreglocaracteres=s.split("\\ ");
//dividimos el string en un arreglo de strings los cuales obtenemos de s con un split que indica que va a armar un strign cada que encuentre un 
//espacio y esos string los mete en el arreglocaracteres
        
        
        for(int i=0;i<arreglocaracteres.length;i++){
          if(pila.isEmpty()){
              pila.push(arreglocaracteres[i].trim());//si la pila esta vacia agrego el primer arreglocaracteres[i]
          }else if(arreglocaracteres[i].trim().equals(")")){//si no esta vacia y va a entrar un ) quiere decir que ya hay un parentesis abierto
              
               String operacion="";
               
              while(!pila.peek().toString().trim().equals("(")){
              //por lo tanto debo sacar todo de la pila hasta que el ( sea el peek. todo lo que saco antes de que ( sea peek lo almaceno en operacion
              //operacion.push(pila.pop());
             
              operacion+=pila.pop().toString()+" ";
                
             
              }
              pila.pop();//aqui saco el parentesis abierto
               pila.push(Suma(operacion).trim());//se evalua el valor de lo que habia en ese parentesis con la funcion suma y eso se mete a la pila
               
          }else{
              pila.push(arreglocaracteres[i].trim());//si no es un ) y la pila no esta vacia, simplemente agregamos el valor en arreglocaracteres[i
          }
          
        }
        String operacion ="";//en este punto ya tenemos en la pila todo sin parentesis, por lo que solo debemos evaluar sumas y multiplicaciones
         while(!pila.isEmpty()){
              
              //operacion.push(pila.pop());
             operacion+=pila.pop().toString()+" ";//en operacion guardamos toda la expresion que queda en la pila y que nos falta por evaluar
          
                
             
              }
         pila.push(Suma(operacion).trim());//realizamos multiplicaciones y sumas con la funcion suma y ese valor lo metemos en la pila
        System.out.println(pila.pop().toString().trim());//imprimimos el ultimo valor que quedo en la pila que es el resultado de Suma(operacion)

        
       

          
        }else{
             Stack<String>PilaNumeros=new Stack<>();
             String[]arregloNumeros=s.split("\\ ");
           //  for(int i=0;i<arregloNumeros.length;i++)  System.out.println(arregloNumeros[i]);
             for(int i=0;i<arregloNumeros.length;i++){
                
                 if(arregloNumeros[i].trim().equals("+")){
                     BigInteger a=new BigInteger(PilaNumeros.pop().trim());
                     BigInteger b= new BigInteger(PilaNumeros.pop().trim());
                     BigInteger c=a.add(b);
                     PilaNumeros.push(c.toString());
                     
                 }else if(arregloNumeros[i].trim().equals("*")){
                     BigInteger a=new BigInteger(PilaNumeros.pop().trim());
                     BigInteger b= new BigInteger(PilaNumeros.pop().trim());
                     BigInteger c=a.multiply(b);
                     PilaNumeros.push(c.toString());
                     
                 }else{
                     PilaNumeros.push(arregloNumeros[i]);
                 }
                 
             }
            System.out.println(PilaNumeros.pop().toString());
        }


    }
      public static String Suma(String Operacion){
        String []partes=Operacion.split("\\+");//partes es un arreglo de string hecho de los substrings de Operacion, los cuales se separan por el operador +
         BigInteger resultado=new BigInteger("0"),operando;      
        for(int i=0;i<partes.length;i++){
            if(partes[i].contains("*")){
//si uno de esos substrings contiene * entonces ese substring debe ser una multiplicacion y para conocer su valor le aplicamos la funcion multiplicar
//por ejemplo si partes[i]=2 * 3 * 4 la funcion multiplicar evalua su valor que es 24 y lo asigna a partes[i]

        
              partes[i]=multiplicar(partes[i].trim());
              
            }
                
            
            try{
                //resultado+=Integer.parseInt(partes[i].trim());//al final suma todos los elementos de partes(que son solo numeros) y el valor lo guarda en reultado para retornarlo
            
                operando=new BigInteger(partes[i].trim());
                resultado=resultado.add(operando);
            }catch(Exception e){
               
           }
            
                    
        }
        return ""+resultado.toString();
        
      
    }
    public static String multiplicar(String s){
        String []coeficientes=s.split("\\*");//separa el string s por el delimitador * y esos substrings los mete en el arreglo coeficientes
       BigInteger resultado=new BigInteger("1");
       for(int i=0;i<coeficientes.length;i++){
          // resultado*=Integer.parseInt(coeficientes[i].trim());
          resultado=resultado.multiply(new BigInteger(coeficientes[i].trim()));
//por ultimo multiplica todos los valores de coeficientes y lo guarda en resultado para retornar
       }
       return ""+resultado.toString();
    }
 
    
    
}
