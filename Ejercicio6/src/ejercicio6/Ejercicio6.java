/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;


/**
 *
 * @author COMPAQ
 */
public class Ejercicio6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        /*ArrayDeque<Character>bicola=new ArrayDeque<>();
        BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
        String expresion=sc.readLine();
        boolean agregaralfinal=true;
        int largo=expresion.length();
        for(int i=0;i<largo;i++){
            
            
            if(expresion.charAt(i)=='('){
                agregaralfinal=false;
             continue;
                
            }else if(expresion.charAt(i)==')'){
                agregaralfinal=true;
                continue;
            }else{
                if(agregaralfinal){
                    bicola.addLast(new Character((expresion.charAt(i))));
                }else{
                    bicola.addFirst(new Character(expresion.charAt(i)));
                }
            }
        }
       /* for(Character a :bicola){
            System.out.print(a.toString());
        }
       int length=bicola.size();//debe hacerse asi pues el size va cambiando conforme se remueve de la bicola
       for(int i=0;i<length;i++){
           System.out.print(bicola.remove());
       }
           */ 
       
        
        
        //VAMOS A USAR UNA BICOLA QUE ALMACENE COLAS
        
        ArrayDeque<Queue<Character>>bicola=new ArrayDeque<>();
//usamos una bicola para agregar TANTO AL FINAL COMO AL INICIO NUEVAS COLAS 
        
        
        bicola.add(new LinkedList<Character>());
        //INICIAMOS AGREGANDO UNA COLA QUE ALMACENA CARACTERES 
         Queue<Character>cola;
          BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
        String expresion=sc.readLine();
        boolean agregaralfinal=true;//SI AGREGARALFINAL ES TRUE SIGNIFICA QUE LOS CARACTERES SE AGREGARAN A LA COLA QUE ESTA AL FINAL DE LA BICOLA
        int largo=expresion.length();
        for(int i=0;i<largo;i++){
            
            
            if(expresion.charAt(i)=='('){//SI ENCONTRAMOS '('SIGNIFICA QUE EL CURSOR PASA AL INICIO, ENTONCES AGREGAMOS UNA COLA 
                //QUE ALMACENE CARACTERES AL INICIO DE LA BICOLA
                agregaralfinal=false;//PONEMOA AGREGARALFINAL EN FALSO PUES VAMOS A AGREGAR A LA PRIMER COLA DE LA BICOLA
                cola=new LinkedList<Character>();
                 bicola.addFirst(cola);//AGREGAMOS AL PRINCIPIO DE LA BICOLA LA COLA NUEVA
             continue;
                
            }else if(expresion.charAt(i)==')'){//SI NOS ENCONTRAMOS CON UN ')' AGREGAMOS UNA NUEVA COLA AL FINAL DE LA BICOLA 
                agregaralfinal=true;//PONEMOS AGREGARALFINAL EN VERDADERO PARA QUE LO QUE SE AGREGUE A CONTINUACION SE AGREGUE EN ULTIMA COLA DE LA BICOLA
                cola=new LinkedList<Character>();
                bicola.addLast(cola);
                continue;
            }else{
                if(agregaralfinal){
                   bicola.getLast().add(new Character(expresion.charAt(i)));
                    //AGREGAMOS EL CARACTER A LA COLA DEL FINAL DE LA BICOLA SI AGREGARALFINAL ES TRUE
                }else{
                   
                    bicola.getFirst().add(new Character(expresion.charAt(i)));
                    //AGREGAMOS EL CARACTER A LA COLA DEL INICIO DE LA BICOLA SI AGREGARALFINAL ES FALSO
                }
            }
        }
        
        for(Queue q:bicola){//VAMOS A IMPRIMIR CADA ELEMENTO PERTENECIENTE A UNA COLA DE LA BICOLA
            while(!q.isEmpty()){
                System.out.print(q.remove());
            }
        }
        
        
    }
    
}
