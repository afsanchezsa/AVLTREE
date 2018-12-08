/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solucionejercicio8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.chrono.HijrahEra;
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author COMPAQ
 */
public class SolucionEjercicio8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
  /* BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(sc.readLine());
        int i=0;
        ArrayList<Nodo>entradas=new ArrayList<>();
        
        StringTokenizer st;
        entradas.add(new Nodo("inicio"));
        while(i<n){
            st=new StringTokenizer(sc.readLine());
           entradas.add(new Nodo(st.nextToken(),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()))) ;
           i++;
        }//ESTA SOLUCION CONSISTE EN METER LA ENTRADA EN UN  ARRAYLIST(ES UNA IMPLEMENTACION HIBRIDA DEL ARBOL)
        for(Nodo u:entradas){
            if(u.contenido.equals("/")&&entradas.get(u.hijoder).contenido.equals("0")){
                System.out.println("ERROR");
                return;//CON ESTE FOR VERIFICAMOS SI HAY UNA DIVISION CON CERO Y IMPRIMIMOS ERROR SI ES ASI
            }
      }
      
        double c,d=0;
        Nodo operado=new Nodo("");//VAMOS A USAR ESTE NODO PARA APUNTAR A DISTINTOS NODOS
       LinkedQueue<Nodo>operar=new LinkedQueue<>();//USAMOS UNA COLA CON EL OBJETIVO DE DESHACERNOS DE EL ULTIMO NIVEL, LUEGO DEL PENULTIMO Y ASI SUCESIVAMENTE
        
        boolean error=false;
        //System.out.println(entradas.get(1).contenido);
        while(!error &&entradas.get(1).hijoder!=-1&&entradas.get(1).hijoizq!=-1){
            //CUANDO EL PRIMER NODO DE ENTRADAS NO TENGA HIJOS(IZQ=-1 Y DERE=-1)ENTONCES TERMINAMOS Y EL VALOR EN ESE NODO ES EL REULTADO
            for(int a=1;a<entradas.size()-1;a++){
                try {//RECORREMOS CADA NODO MIRANDO SI EL NODO ES PADRE DE DOS HOJAS
                          if(entradas.get(entradas.get(a).hijoizq).hijoizq==-1&&entradas.get(entradas.get(a).hijoizq).hijoder==-1&&entradas.get(entradas.get(a).hijoder).hijoizq==-1&&entradas.get(entradas.get(a).hijoder).hijoder==-1){
                    operar.put(entradas.get(a));//SI EL NODO ES PADRE DE DOS HOJAS ENTONCES ES UN OPERADOR Y LO METEMOS EN OPERAR
                    
                } 
                } catch (Exception e) {
                }
         
            }
            
        
            while(!operar.isEmpty()&&!error){
                operado=operar.remove();//EN ESTE MOMENTO EN OPERAR ESTAN LOS PADRES DE LAS HOJAS DEL ULTIMO NIVEL
              //OPERADO ES EL OPERADOR QUE SALE DE LA COLA
                c=Double.parseDouble(entradas.get(operado.hijoizq).contenido);//C SERA EL HIJO IZQUIERDO DE OPERADO
                  // System.out.println(" jdjasdnjasd"+c);
                    d=Double.parseDouble(entradas.get(operado.hijoder).contenido);//D SERA EL HIJO DERECHO DE OPERADO
                //System.out.println(d);
                    switch(operado.contenido){//MIRAMOS QUE TIPO DE OPERADOR ES OPERADO Y DEPENDIENDO DE ESO CAMBIAMOS EL CONTENIDO DE OPERADO
                case"+":                       //POR EL RESULTADO DE LA OPERACION(AL CAMBIAR OPERADO.CONTENIDO ESTAMOS CAMBIANDO ESE VALOR TAMBIEN EN ENTRADAS PUES AMBOS SON EL MISMO OBJETO)
                 
                    operado.contenido=""+(c+d);
                      
                    break;
               case"-":
                 
                    operado.contenido=""+(c-d);
                  
                    break;    
                         case"*":
                   
                    operado.contenido=""+(c*d);
                  
                    break;
              default:
                  
                      
                  if(d!=0){
                     operado.contenido=""+(c/d); 
                  }else{
                        System.out.println("ERROR");//SACA ERROR SI DE ES CERO PUES NO SE PUEDE DIVIDIR EN CERO
                      error=true;
                  }
                    
                  
                    
              break;      
                     
                    
                    
            }
                 operado.hijoder=-1;//FINALMENTE COMO OPERADO AHORA CONTIENE EL RESULTADO DE LA OPERACION, AHORA ES UNA HOJA ENTONCES PONEMOS
                    operado.hijoizq=-1;//HIJOIZQ=-1 Y HIJODERE=-1
                
                
                
            }
        }
        if(!error){
            
           //SI NO HUBO UN ERROR, ENTONCES EL RESULTADO ES CONTENIDO DEL PRIMER NODO DE ENTRADAS
            System.out.println((redondear(entradas.get(1).contenido)));
        }
            
        
       */
       
    
  /*
  BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(sc.readLine());
        int i=0;
        ArrayList<Nodo>entradas=new ArrayList<>();
        
        StringTokenizer st;
        entradas.add(new Nodo("inicio"));
        ArrayList<Nodo>entrada2=new ArrayList<>();
        entrada2.add(new Nodo("inicio"));
        while(i<n){
            st=new StringTokenizer(sc.readLine());
            String cont=st.nextToken();
            int izq=Integer.parseInt(st.nextToken());
            int der=Integer.parseInt(st.nextToken());
           entradas.add(new Nodo(cont,izq,der)) ;
           entrada2.add(new Nodo(cont,izq,der));
           i++;
        }
        
      
        for(Nodo u:entradas){
            if(u.contenido.equals("/")&&entradas.get(u.hijoder).contenido.equals("0")){
                System.out.println("ERROR");
                return;
            }
      }
      
        double c,d=0;
        Nodo operado=new Nodo("");
       LinkedQueue<Nodo>operar=new LinkedQueue<>();
        
        boolean error=false;
        //System.out.println(entradas.get(1).contenido);
        while(!error &&entradas.get(1).hijoder!=-1&&entradas.get(1).hijoizq!=-1){
            
            for(int a=1;a<entradas.size()-1;a++){
                try {
                          if(entradas.get(entradas.get(a).hijoizq).hijoizq==-1&&entradas.get(entradas.get(a).hijoizq).hijoder==-1&&entradas.get(entradas.get(a).hijoder).hijoizq==-1&&entradas.get(entradas.get(a).hijoder).hijoder==-1){
                    operar.put(entradas.get(a));
                    
                } 
                } catch (Exception e) {
                }
         
            }
            
        
            while(!operar.isEmpty()&&!error){
                operado=operar.remove();
              
                c=Double.parseDouble(entradas.get(operado.hijoizq).contenido);
                  // System.out.println(" jdjasdnjasd"+c);
                    d=Double.parseDouble(entradas.get(operado.hijoder).contenido);
                //System.out.println(d);
                    switch(operado.contenido){
                case"+":
                 
                    operado.contenido=""+(c+d);
                      
                    break;
               case"-":
                 
                    operado.contenido=""+(c-d);
                  
                    break;    
                         case"*":
                   
                    operado.contenido=""+(c*d);
                  
                    break;
              default:
                  
                      
                  if(d!=0){
                     operado.contenido=""+(c/d); 
                  }else{
                        System.out.println("ERROR");
                      error=true;
                  }
                    
                  
                    
              break;      
                     
                    
                    
            }
                 operado.hijoder=-1;
                    operado.hijoizq=-1;
                
                
                
            }
        }
        if(!error){
            
           
            System.out.println((redondear(entradas.get(1).contenido)));
        }
         */   
         BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(sc.readLine());
        int i=0;
        ArrayList<Nodo>entradas=new ArrayList<>();
        
        StringTokenizer st;
        entradas.add(new Nodo("inicio"));//ponemos un nodo de inicio para manejar los mismos indices de las entradas(que empiezan en uno)
        
    
        while(i<n){
            st=new StringTokenizer(sc.readLine());
            String cont=st.nextToken();
            int izq=Integer.parseInt(st.nextToken());
            int der=Integer.parseInt(st.nextToken());
           entradas.add(new Nodo(cont,izq,der)) ;
          //LLENAMOS EL ARRAYLIST ENTRADAS CON TODOS LOS NODOS(QUE TIENEN UN CONTENIDO, UN INDICE DE HIJO IZQUIERDO Y UN INDICE DE HIJO DERECHO)
           i++;
        }
     
      Stack<Nodo> pila=new Stack<>();//CREAMOS UNA PILA
       /* try {
          recorrerpostorder(entradas, entradas.get(1),pila);  
          System.out.println(redondear(pila.pop()));//ESTA SOLUCION ES VALIDA PERO COMO UTILIZA FUNCIONES RECURSIVAS HAY UN STACKOVERLFLOW PUES SE POBREPASAN LOS 10000 LLAMADOS RECURSIVOS
        } catch (Exception e) {
            System.out.println("ERROR");
        }*/
       

            try {
            postOrderIterativo(entradas, pila);
        System.out.println(redondear(pila.peek().contenido));
        } catch (Exception e) {
                System.out.println("ERROR");
        }

    }
    public static String redondear(String numero){
      BigDecimal a=new BigDecimal(numero);
      a=a.setScale(6, BigDecimal.ROUND_HALF_UP);//ASI REDONDEAMOS A SEIS DECIMALES Y AL TECHO
        return a.toString();
    }
    public static void recorrerpostorder(ArrayList<Nodo>entradas,Nodo n,Stack<String>pila){//ESTA ES UNA FUNCION RECURSIVA QUE HACE RECORRIDO POSTORDER
        double a=0,b=0;
        if(n.hijoder==-1&&n.hijoizq==-1){
          //SI EL HIJO DERECHO E IZQUIERDO ES -1 ES UNA HOJA ENTONCES SOLO LA AGREGAMOS A LA PILA
          
           pila.add(n.contenido);
        }else{
          
            recorrerpostorder(entradas, entradas.get(n.hijoizq), pila);//EL OBJETIVO ES PRIMERO RECORRER EL HIJO IZQUIERDO Y LUEGO DEL DERECHO Y FINALMENTE EL PADRE PARA ASI CONSEGUIR UNA ESPRESION OPERANDO OPERANDO OPERADOR(NOTACION POSFIJA)
            recorrerpostorder(entradas, entradas.get(n.hijoder), pila);
            if(n.contenido.equals("+")){//SI EL PADRE(RAIZ) ES UNA SUMA NO LA METEMOS EN LA PILA SINO QUE SACAMOS LOS DOS ELEMENTOS DE LA PILA LOS SUMAMOS Y ESE RESULTADO LO METEMOS EN LA PILA
               b=Double.parseDouble(pila.pop());
              a=Double.parseDouble(pila.pop());
             
              pila.push(""+(a+b));
          }else if(n.contenido.equals("-")){
               b=Double.parseDouble(pila.pop());
              a=Double.parseDouble(pila.pop());
             
              pila.push(""+(a-b));
          }else if(n.contenido.equals("*")){
             b=Double.parseDouble(pila.pop());
              a=Double.parseDouble(pila.pop());
              
              pila.push(""+(a*b));
          }else if(n.contenido.equals("/")){
                b=Double.parseDouble(pila.pop()); 
              a=Double.parseDouble(pila.pop());
               
             
              
              pila.push(""+(a/b)); 
             
              
              
          }
          else{
            pila.add(n.contenido);  //SI NO ERA NINGUN OPERADOR ERA UN NUMERO Y SE METE(AUNQUE NUNCA SE LLEGA A ESTE PUNTO EN REALIDAD) 
          }
            
        }
    }
    public static  void postOrderIterativo(ArrayList<Nodo>entradas,Stack<Nodo>pila){
        pila.add(entradas.get(1));//INICIAMOS PONIENDO EN LA PILA LA RAIZ DEL ARBOL
        while(!pila.isEmpty()){ //EJECUTAREMOS EL PROCEDIMIENTO MIENTRAS LA PILA NO ESTE VACIA
            if(pila.peek().hijoizq!=-1){//SI EL ELEMENTO PEEK DE LA PILA TIENE HIJO IZQUIERDO AGREGAMOS ESE HIJO IZQUIERDO A LA PILA
                pila.add(entradas.get(pila.peek().hijoizq));
            }else if(pila.peek().hijoder!=-1){//SI EL ELEMENTO PEEK NO TIENE HIJO IZQUIERDO PERO SI DERECHO AGREGAMOS ESE HIJO DERECHO A LA PILA
                pila.add(entradas.get(pila.peek().hijoder));
                
            }else{//SI EL ELEMENTO PEEK NO TIENE NI HIJO DERECHO NI IZQUIERDO ES UNA HOJA
                double b=Double.parseDouble(pila.pop().contenido);//SACAMOS EL ELEMENTO PEEK Y LO CONVERTIMOS EN DOUBLE
                if(pila.isEmpty()){//SI AL SACAR EL ELEMENTO LA PILA QUEDA VACIA ESE ES EL RESULTADO ASI QUE LO METEMOS DE NUEVO EN LA PILA Y TERMINA LA FUNCION
                     pila.add(new Nodo(""+b,-1,-1));
                    return;
                }
                
                if(!pila.peek().contenido.equals("+")//SI EL PEEK NO ES NINGUNA OPERACION ENTONCES ES UN NUMERO POR LO TANTO COMO YA TENEMOS UN b QUE TAMBIEN ES NUMERO
                        && !pila.peek().contenido.equals("-")//Y EL PEEK ES UN NUMERO, a y b SON HOJAS HERMANAS POR ENDE SACAMOS a y lo pasamos a double
                        && !pila.peek().contenido.equals("*")
                        && !pila.peek().contenido.equals("/")){
                    double a =Double.parseDouble(pila.pop().contenido);
                    String operacion=pila.pop().contenido;//LUEGO SACAMOS LA OPERACION(PADRE DE a y b)
                    switch(operacion){//dependiendo el tipo de operacion, lo ejecutamos y el resultado lo metemos en la pila como una hoja
                        case"+":
                           pila.push(new Nodo(""+(a+b),-1,-1));
                                break;
                        case"-":
                            pila.push(new Nodo(""+(a-b),-1,-1)); 
                            break;
                              case"*":
                            pila.push(new Nodo(""+(a*b),-1,-1)); 
                            break;
                              default:
                                    
                            pila.push(new Nodo(""+(a/b),-1,-1)); 
                            
                                  break;
                    }
                }else{//si el PEEK ES OPERACION ENTONCES ES UN NUMERO Y COMO YA TENIAMOS UN b que era numero Y LUEGO DE SACAR EL B OBTUVIMOS UNA OPERACION, FALTA EL HERMANO 
                    int hijoder=0;//DE b QUE SERA EL HIJO DERECHO DE LA OPERACION
                    hijoder=pila.peek().hijoder;
                    pila.add(new Nodo(""+b,-1,-1));//AGREGAMOS b que es el hijo izquierdo y luego agregamos el derecho pues asi es en postorder
                    pila.add(entradas.get(hijoder));
                    
                }
            }
        }
    }
}
class Nodo{
    
    int hijoizq;
    int hijoder;
    String contenido;//el contenido es el numero o operacion

    public Nodo(String contenido) {
       
        this(contenido,-1,-1);
    }

    public Nodo( String contenido,int hijoizq, int hijoder) {
        this.hijoizq = hijoizq;
        this.hijoder = hijoder;
        this.contenido = contenido;
    }
    
    
}

interface Queue<T>{
     boolean isEmpty();
     T getFrontElement();
     void put(T theObject);
     T remove();
}
class ChainNode<T>{
    T element;
    ChainNode<T>next;

    public ChainNode(T element, ChainNode<T> next) {
        this.element = element;
        this.next = next;
    }

    public ChainNode(T element) {
        this(element,null);
    }

    public ChainNode() {
        this(null,null);
    }
    
}
class LinkedQueue<T> implements Queue<T>{
    ChainNode<T>front;
    ChainNode<T>back;

    public LinkedQueue() {
        front=back=null;
    }
 
    
    @Override
    public boolean isEmpty() {
               return front==null;
    }

    @Override
    public T getFrontElement() {
       return isEmpty()?null:front.element;
}
    @Override
    public void put(T theObject) {
        ChainNode<T>p=new ChainNode<T>(theObject,null);
        if(front==null){
            front=p;
        }else{
            back.next=p;
            
        }
        back=p;
    }

    @Override
    public T remove() {
        if(isEmpty()){
            return  null;
        }
        T frontElement=front.element;
        front=front.next;
        if(isEmpty()){
            back=null;
        }
        return frontElement;
              
    }
    
}






/*
 BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(sc.readLine());
        int i=0;
        LinkedList<Nodo>entradas=new LinkedList<>();
        ArrayList<Integer>disponibles=new ArrayList<>();
        StringTokenizer st;
        Nodo k;
        entradas.add(new Nodo("inicio"));
        while(i<n){
            st=new StringTokenizer(sc.readLine());
           k=new Nodo(st.nextToken(),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
           k.indice=i+1;
            entradas.add(k) ;
           i++;
        }
        
        
        for(int p=1;p<entradas.size();p++){
             disponibles.add(new Integer(p));
        }
       
    
      
      
        double c,d=0;
        Nodo operado=new Nodo("");
         LinkedQueue<Nodo>operar=new LinkedQueue<>();
        
        boolean error=false;
       
        while(!error &&entradas.get(1).hijoder!=-1&&entradas.get(1).hijoizq!=-1){
            
            for(Integer a:disponibles){
                try {
                          if(entradas.get(entradas.get(a.intValue()).hijoizq).hijoizq==-1&&entradas.get(entradas.get(a.intValue()).hijoizq).hijoder==-1&&entradas.get(entradas.get(a.intValue()).hijoder).hijoizq==-1&&entradas.get(entradas.get(a.intValue()).hijoder).hijoder==-1){
                    operar.put(entradas.get(a.intValue()));
                    
                } 
                } catch (Exception e) {
                }
         
            }
            
        
            while(!operar.isEmpty()&&!error){
                operado=operar.remove();
            
                c=Double.parseDouble(entradas.get(operado.hijoizq).contenido);
               
                    d=Double.parseDouble(entradas.get(operado.hijoder).contenido);
                
                    switch(operado.contenido){
                case"+":
                 
                    operado.contenido=""+(c+d);
                      
                    break;
               case"-":
                 
                    operado.contenido=""+(c-d);
                  
                    break;    
                         case"*":
                   
                    operado.contenido=""+(c*d);
                  
                    break;
              default:
                  
                      
                  if(d!=0){
                     operado.contenido=""+(c/d); 
                  }else{
                        System.out.println("ERROR");
                      error=true;
                  }
                    
                  
                    
              break;      
                     
                    
                    
            }
                    disponibles.remove(entradas.get(operado.hijoder));
                      disponibles.remove(entradas.get(operado.hijoizq));
                 operado.hijoder=-1;
                    operado.hijoizq=-1;
                
                
                
            }
        }
        if(!error){
            
           
            System.out.println((redondear(entradas.get(1).contenido)));
        }
            
       */
       
  