/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posfijaainfija;

import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author COMPAQ
 */
public class PosfijaAInfija {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println(PasarInfijaAPostFija("( 5 * 4 ) + 3 * 8 + 5 / 2 * 3"));
        EvaluadordeExpresiones ec=new EvaluadordeExpresiones();
        System.out.println(ec.SolucionarInfija("( 5 * 4 ) + 3 * 8 + 5 / 2 * 3"));
    }
    static String PasarInfijaAPostFija(String infija){
        StringTokenizer st=new StringTokenizer(infija);
        String s=null;
StringBuilder resultado=new StringBuilder();
        HashMap<String,Integer>mapa=new HashMap<>();
        mapa.put("+",1);
        mapa.put("-",1);
        mapa.put("*",2);
        mapa.put("/",2);
        mapa.put("´",3);
        mapa.put("(", Integer.MIN_VALUE);
        mapa.put(")", 12);
        
        Stack<String>pila=new Stack<>();
        while(st.hasMoreTokens()){
            s=st.nextToken();
            if(pila.isEmpty()&&mapa.get(s)!=null){
                pila.add(s);
            }else{
                if(mapa.get(s)!=null){
                    if(mapa.get(s)==12){
                        while(!pila.peek().equals("(")){
                            resultado.append(pila.pop()+" ");
                        }
                        pila.pop();
                    }else if(mapa.get(s)!=Integer.MIN_VALUE){
                          Integer precedencianuevooperador=mapa.get(s);
                    while(!pila.isEmpty()&&mapa.get(pila.peek())>=precedencianuevooperador){
                        resultado.append(pila.pop()+" ");
                        
                    }
                    pila.add(s); 
                    }else{
                        pila.add(s);//en caso de ser (
                    }
                 
                }else{
                    resultado.append(s+" ");
                }
               
                    
                    
            }
            }
        while(!pila.isEmpty())resultado.append(pila.pop()+" ");
        return resultado.toString().trim();
        }
    }

class EvaluadordeExpresiones{
    public int SolucionarInfija(String expresion){
        return solucionPostfija(PasarInfijaAPostFija(expresion)); 
    }
    public int SolucionarPostFija(String expresion){
        return solucionPostfija(expresion);
    }
    private String PasarInfijaAPostFija(String infija){
        StringTokenizer st=new StringTokenizer(infija);
        String s=null;
StringBuilder resultado=new StringBuilder();
        HashMap<String,Integer>mapa=new HashMap<>();
        mapa.put("+",1);
        mapa.put("-",1);
        mapa.put("*",2);
        mapa.put("/",2);
        mapa.put("´",3);
        mapa.put("(", Integer.MIN_VALUE);
        mapa.put(")", 12);
        
        Stack<String>pila=new Stack<>();
        while(st.hasMoreTokens()){
            s=st.nextToken();
            if(pila.isEmpty()&&mapa.get(s)!=null){
                pila.add(s);
            }else{
                if(mapa.get(s)!=null){
                    if(mapa.get(s)==12){
                        while(!pila.peek().equals("(")){
                            resultado.append(pila.pop()+" ");
                        }
                        pila.pop();
                    }else if(mapa.get(s)!=Integer.MIN_VALUE){
                          Integer precedencianuevooperador=mapa.get(s);
                    while(!pila.isEmpty()&&mapa.get(pila.peek())>=precedencianuevooperador){
                        resultado.append(pila.pop()+" ");
                        
                    }
                    pila.add(s); 
                    }else{
                        pila.add(s);//en caso de ser (
                    }
                 
                }else{
                    resultado.append(s+" ");
                }
               
                    
                    
            }
            }
        while(!pila.isEmpty())resultado.append(pila.pop()+" ");
        return resultado.toString().trim();
        }
    private int solucionPostfija(String expresion){
        StringTokenizer st=new StringTokenizer(expresion);
        String s=null;
        Stack<Integer>pila=new Stack<>();
        while(st.hasMoreTokens()){
            s=st.nextToken();
            if(!s.equals("+")&!s.equals("-")&&!s.equals("/")&&!s.equals("*")&&!s.equals("´")){
                pila.add(Integer.parseInt(s));
            }else{
                int b=pila.pop();
                int a=pila.pop();
                switch(s){
                    case"+":
                        pila.push(a+b);
                        
                        break;
                        
                    case"-":
                        pila.push(a-b);
                        break;
                    case"*":
                        pila.push(a*b);
                        break;
                    case"/":
                        pila.push(a/b);
                        break;
                    case"´":
                        pila.push((int)Math.pow(a, b));
                        break;
                    default:
                        break;
                }
            }
        }
        return pila.pop();
    }
    
}
