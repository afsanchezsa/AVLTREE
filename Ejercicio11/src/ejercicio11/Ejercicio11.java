/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 *
 * @author COMPAQ
 */
public class Ejercicio11 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here


   BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer s=new StringTokenizer(sc.readLine());

   int n=Integer.parseInt(s.nextToken());
   int m=Integer.parseInt(s.nextToken());
   int i=0;
  PrefixTree arbol=new PrefixTree();
   while(i<n){
      arbol.add(sc.readLine());
       i++;
   }
   
        System.out.println(arbol.Consulta(m));
    
    }
    
}


class PrefixNode{
 private  Character element;
  
  PrefixNode hijos[];//en hijos vamos a almacenar el caracter que le sigue a element(que pueden ser 28)
    public PrefixNode(Character element) {
        this.element = element;
        
       this.hijos=new PrefixNode[29];//en la posicion 0 de hijos no guardamos nada y su posicion 1 corresponde a la a
    }
  
}
class PrefixTree{
    private PrefixNode root;
   
    int []elementos_por_nivel;

 
    
    public PrefixTree() {
        this.root=new PrefixNode(null);//la raiz es nivel 0 y no tiene ningun caracter
//en una palabra su primer caracter pertenece al nivel 1, su segundo caracter al nivel 2 y asi sucesivamente
        elementos_por_nivel=new int [101];//van a haber maximo 101 niveles pues las palabras pueden tener 100 caracteres y en la posicion 0 no guardamos nada
    }
    public void add(String palabra){
        //palabra=palabra.trim();
        PrefixNode curreNode=root;
       int i=0;
         while(i<palabra.length()){
          //codepoint retorna el numero ascii al que se asocia el caracter en la posicion i del string palabra 
             int indice=palabra.codePointAt(i)-96;//identificamos que caracter es y el indice que el corresponde en el arreglo hijos y a ese valor le llamamos "indice"(vamos a enumerar la a con el indice 1 la b con 2...)
            if(indice==65437){//en este caso es cuando se inserta la ñ, la ñ la meteremos en la posicion 28 del arreglo hijos
                indice=28;
            }
         try{
                  if(curreNode.hijos[indice]==null){//si ese hijo es nulo(es decir aun no existe una palabra que contenga element seguido de el caracter palabra.charAt(i)) debemos crearlo
              curreNode.hijos[indice]=new PrefixNode(palabra.charAt(i));
            elementos_por_nivel[i+1]++;//le sumamos 1 a la cantidad de palabras que llegan hasta el nivel i+1(si dos palabras empiezan por lo mismo como interracial e internacional el prefijo inter solo cuenta 1 vez para esas dos palabras )
          //por lo tanto solo sumamos cuando en el nivel i+1 aparece un caracter nuevo
                  }
            }catch(IndexOutOfBoundsException e){//en caso de que no sea un caracter entre a minuscula y z minuscula, no hacer nada
                
            }
                      i++;
           curreNode=curreNode.hijos[indice];//currenode ahora es ese hijo que se acabo de agregar(o que en su defecto ya existia)
              }
           
    }
    public String Consulta(int n){
    StringBuilder s=new StringBuilder();
    for(int i=1;i<n+1;i++){//solamente lo vamos a contemplar hasta ese n+1(que es en realidad el m+1)
        s.append(elementos_por_nivel[i]+" ");
    }
     return s.toString();
}
    
    
}

