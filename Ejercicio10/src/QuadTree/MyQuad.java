/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuadTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author COMPAQ
 */
public class MyQuad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String line;int f_1;int a;int b;int n1;
        BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
        String nums[];
        line=sc.readLine();
        int i1,i2,j1,j2;
        if(line.equals("CIUDAD")){
            line = sc.readLine();
                nums = line.split(" ");
                n1 = Integer.parseInt(nums[0]);
                int m = Integer.parseInt(nums[1]);
            line=sc.readLine();
                       nums=line.split(" ");
            f_1 = Integer.parseInt(nums[0]);
                a = Integer.parseInt(nums[1]);
                b = Integer.parseInt(nums[2]);
             
                QuadTree ciudad=new QuadTree(1, 1,n1,m);
                ciudad.Initialize(f_1, a, b, n1, m);
                while((line=sc.readLine())!=null){
            if(line.matches("MARCHA(.*)")){
                nums=line.split(" ");
                i1=Integer.parseInt(nums[1]);
                j1=Integer.parseInt(nums[2]);
                i2=Integer.parseInt(nums[3]);
                j2=Integer.parseInt(nums[4]);
                int aux;
                if(i1>i2){
                    aux=i1;
                    i1=i2;
                    i2=aux;
                }
                 if(j1>j2){
                    aux=j1;
                    j1=j2;
                    j2=aux;
                }
                System.out.println(ciudad.consulta(i1,j1 ,i2 ,j2 ));
            }else{
                nums=line.split(" ");
                ciudad.Update(ciudad.getRoot(),Integer.parseInt(nums[1]) ,Integer.parseInt(nums[2]) , Integer.parseInt(nums[3]));
            }
                
                                 
            
 
        }
        }
            
            
       
    }
    
}
class Comparador implements Comparator<NodoQ>{

    public Comparador() {
    }

    @Override
    public int compare(NodoQ o1, NodoQ o2) {
        return o1.compareTo(o2);
    }

  

}

interface Quad{
    public void Initialize(int f_1,int a,int b,int n, int m);
    public NodoQ Solve(NodoQ currentNode,int i1,int j1, int i2, int j2);
    public String consulta(int i1,int j1, int i2, int j2);
    public void Update(NodoQ currentNode, int i1, int j1, int nuevo_valor);
}

class QuadTree implements Quad{
NodoQ root;
 Long sumatotal;
 int [][]matriz;

    public NodoQ getRoot() {
        return root;
    }

    public QuadTree(int i1, int j1, int i2, int j2) {
        this.root=new NodoQ(i1, j1, proximaPotencia2(i2), proximaPotencia2(j2)/*,null*/);
        
    }
   

    @Override
    public NodoQ Solve(NodoQ currentNode, int i1, int j1, int i2, int j2) {
        if(currentNode==null)return null;
     if(currentNode.getY1()<=i1&&currentNode.getY2()>=i2&&currentNode.getX1()<=j1&&currentNode.getX2()>=j2){
         if(currentNode.getY1()==i1&&currentNode.getY2()==i2&&currentNode.getX1()==j1&&currentNode.getX2()==j2){
            this.sumatotal+=currentNode.getSuma();
             
             return currentNode;
         }
         if(currentNode.getH1()!=null&&currentNode.getH1().getY1()<=i1&&currentNode.getH1().getY2()>=i2&&currentNode.getH1().getX1()<=j1&&currentNode.getH1().getX2()>=j2){
             return Solve(currentNode.getH1(), i1, j1, i2, j2);
         }
          
             
     if(currentNode.getH2()!=null&&currentNode.getH2().getY1()<=i1&&currentNode.getH2().getY2()>=i2&&currentNode.getH2().getX1()<=j1&&currentNode.getH2().getX2()>=j2){
             return Solve(currentNode.getH2(), i1, j1, i2, j2);
     }
        if(currentNode.getH3()!=null&&currentNode.getH3().getY1()<=i1&&currentNode.getH3().getY2()>=i2&&currentNode.getH3().getX1()<=j1&&currentNode.getH3().getX2()>=j2){
             return Solve(currentNode.getH3(), i1, j1, i2, j2);
     }
          if(currentNode.getH4()!=null&&currentNode.getH4().getY1()<=i1&&currentNode.getH4().getY2()>=i2&&currentNode.getH4().getX1()<=j1&&currentNode.getH4().getX2()>=j2){
             return Solve(currentNode.getH4(), i1, j1, i2, j2);
     }
           boolean estah1,estah2,estah3,estah4;
           NodoQ Nodo1,Nodo2,Nodo3,Nodo4;
          
         try{
             estah1=i1<=currentNode.getH1().getY2()&&j1<=currentNode.getH1().getX2();//miramos en que hijos esta contenido
         }catch(Exception e){
             estah1=false;
         }
         try{
             estah2=i1<=currentNode.getH2().getY2()&&currentNode.getH2().getX1()<=j2;
         }catch(Exception e){
             estah2=false;
         }
                 
           try{
          estah3=currentNode.getH3().getY1()<=i2&&j1<=currentNode.getH3().getX2();     
           }catch(Exception e){
             estah3=false;
         }
          try{
               estah4= currentNode.getH4().getY1()<=i2&&currentNode.getH4().getX1()<=j2; 
          }
          catch(Exception e){
             estah4=false;
         }
           

          if(estah1&&estah2&&estah3&&estah4){
              Nodo1=Solve(currentNode.getH1(), i1, j1, currentNode.getH1().getY2(), currentNode.getH1().getX2());//parte perteneciente a hijo 1
              Nodo2=Solve(currentNode.getH2(),i1,currentNode.getH2().getX1(),currentNode.getH2().getY2(),j2);
              Nodo3=Solve(currentNode.getH3(), currentNode.getH3().getY1(), j1, i2, currentNode.getH3().getX2());
              Nodo4=Solve(currentNode.getH4(),currentNode.getH4().getY1(),currentNode.getH4().getX1(),i2,j2);
              
             NodoQ aux=Nodo1.compareTo(Nodo2)>0?Nodo1:Nodo2;
             aux=aux.compareTo(Nodo3)>0?aux:Nodo3;
             aux=aux.compareTo(Nodo4)>0?aux:Nodo4;
 
             
             return aux;
          }
          if(estah1&&estah3){
              Nodo1=Solve(currentNode.getH1(), i1, j1, currentNode.getH1().getY2(), j2);
              Nodo3=Solve(currentNode.getH3(), currentNode.getH3().getY1(), j1, i2, j2);
             
              return Nodo1.compareTo(Nodo3)>0?Nodo1:Nodo3;
          }
          if(estah1&&estah2){
              Nodo1=Solve(currentNode.getH1(), i1, j1, i2, currentNode.getH1().getX2());
              Nodo2=Solve(currentNode.getH2(), i1, currentNode.getH2().getX1(), i2, j2);
              
              return Nodo1.compareTo(Nodo2)>0?Nodo1:Nodo2;
          }
          if(estah2&&estah4){
              Nodo2=Solve(currentNode.getH2(), i1, j1, currentNode.getH2().getY2(), j2);
              Nodo4=Solve(currentNode.getH4(), currentNode.getH4().getY1(), j1, i2, j2);
               
              return Nodo2.compareTo(Nodo4)>0?Nodo2:Nodo4;
          }
              if(estah3&&estah4){
                  Nodo3=Solve(currentNode.getH3(), i1, j1, i2, currentNode.getH3().getX2());
                  Nodo4=Solve(currentNode.getH4(), i1, currentNode.getH4().getX1(), i2, j2);
                    
                  return Nodo3.compareTo(Nodo4)>0?Nodo3:Nodo4;
              }
          
          
          
          
       
             }else{
         return null;
     }
    return null;
    }

    @Override
    public String consulta(int i1, int j1, int i2, int j2) {
        this.sumatotal=(long)0;
        NodoQ r=this.Solve(root, i1, j1, i2, j2);
        return ""+r.getJmax()+" "+r.getXmax()+" "+this.sumatotal;
    }

    @Override
    public void Initialize(int f_1,int a ,int b, int n, int m) {
        /*int lado=proximaPotencia2(n+1,m+1);
        this.matriz=new  int [lado][lado];
        int longitudarreglo=(int)(Math.pow(4, (AlturaArbol(lado*lado)))-1)/3;
        this.arreglo=new Nodo[longitudarreglo];
        */
        this.matriz=new  int [proximaPotencia2(n)+1][proximaPotencia2(m)+1];
        matriz[1][1]=f_1;
        //llenamos la matriz
  for(int i=2;i<m+1;i++){
      matriz[1][i]=((a*matriz[1][i-1])+b)%1000000;
  
  }
                for(int i=2;i<n+1;i++){
                    for(int j=1;j<m+1;j++){
                        if(j==1){
                            
                            matriz[i][j]=((a*matriz[i-1][m])+b)%1000000;
               
                        }else{
                            matriz[i][j]=((a*matriz[i][j-1])+b)%1000000;
                            
                        }
                    }
                }
               
                LlenarIntervalo(this.root);
                
        
    }
    public void LlenarIntervalo(NodoQ a ){
        if(a==null){
            return;
        }
        if(a.getX1()==a.getX2()&&a.getY1()==a.getY2()){
           
            a.setXmax(a.getX1());
            a.setJmax(a.getY1());
          
            a.setValorMaximo(this.matriz[a.getJmax()][a.getXmax()]);
           
            a.setSuma(this.matriz[a.getJmax()][a.getXmax()]);
           // System.out.println(a.getJmax()+" "+a.getXmax()+" "+a.getSuma());
            return;
        }
        if(a.getX1()==a.getX2()){
           
            a.setH1(new NodoQ(a.getY1(), a.getX1(), (a.getY1()+a.getY2())/2,a.getX2()/*, a*/));
             a.setH3(new NodoQ(((a.getY1()+a.getY2())/2)+1,a.getX1(), a.getY2(),a.getX2() /*, a*/));
            LlenarIntervalo(a.getH1());
            LlenarIntervalo(a.getH3());
            if(a.getH1().compareTo(a.getH3())>0){
                a.setXmax(a.getH1().getXmax());
                a.setJmax(a.getH1().getJmax());
                a.setValorMaximo(a.getH1().getValorMaximo());
            }else{
                 a.setXmax(a.getH3().getXmax());
                a.setJmax(a.getH3().getJmax());
                a.setValorMaximo(a.getH3().getValorMaximo());
            }
              a.setSuma((a.getH1().getSuma()+a.getH3().getSuma()));
            return;
        }
        if(a.getY1()==a.getY2()){
            
            a.setH1(new NodoQ(a.getY1(),a.getX1(),a.getY2(),(a.getX1()+a.getX2())/2/*,a*/));
            a.setH2(new NodoQ(a.getY1(),((a.getX1()+a.getX2())/2 )+1,a.getY2() , a.getX2()/*, a*/));
                LlenarIntervalo(a.getH1());
            LlenarIntervalo(a.getH2());
              if(a.getH1().compareTo(a.getH2())>0){
                a.setXmax(a.getH1().getXmax());
                a.setJmax(a.getH1().getJmax());
                a.setValorMaximo(a.getH1().getValorMaximo());
            }else{
                 a.setXmax(a.getH2().getXmax());
                a.setJmax(a.getH2().getJmax());
                a.setValorMaximo(a.getH2().getValorMaximo());
            }
              a.setSuma((a.getH1().getSuma()+a.getH2().getSuma()));
            return;
        }
      
        a.setH1(new NodoQ(a.getY1(), a.getX1(), (a.getY1()+a.getY2())/2,(a.getX1()+a.getX2())/2 /*, a*/));
       
        a.setH2(new NodoQ(a.getY1(), ((a.getX1()+a.getX2())/2)+1,(a.getY1()+ a.getY2())/2,a.getX2() /*, a*/));
        a.setH3(new NodoQ(((a.getY1()+a.getY2())/2)+1, a.getX1(),a.getY2(),(a.getX1()+a.getX2())/2 /*, a*/));
        a.setH4(new NodoQ(((a.getY1()+a.getY2())/2)+1, (( a.getX1()+a.getX2())/2)+1, a.getY2(),a.getX2()/*, a*/));
        
        LlenarIntervalo(a.getH1());
        LlenarIntervalo(a.getH2());
        LlenarIntervalo(a.getH3());
        LlenarIntervalo(a.getH4());
        ArrayList<NodoQ>ar=new ArrayList<>();
        ar.add(a.getH1());
        ar.add(a.getH2());
        ar.add(a.getH3());
        ar.add(a.getH4());
        Collections.sort(ar,Collections.reverseOrder());
        a.setXmax(ar.get(0).getXmax());
        a.setJmax(ar.get(0).getJmax());
        a.setValorMaximo(ar.get(0).getValorMaximo());
        a.setSuma(a.getH1().getSuma()+a.getH2().getSuma()+a.getH3().getSuma()+a.getH4().getSuma());
        return;
        
    }

   
    private int AlturaArbol(int elementos){
        return (int) Math.ceil((Math.log(elementos)/Math.log(4))+1);
    }
    private int proximaPotencia2(int a){
       
       
        int s=(int)Math.ceil(Math.log(a)/Math.log(2));
    return (int)Math.pow(2, s);
    }

    @Override
    public void Update(NodoQ currentNode,int i1, int j1,int nuevo_valor) {
        if (currentNode==null)return ;
      if(currentNode.getY1()==i1&&currentNode.getX1()==j1&&currentNode.getX2()==currentNode.getX1()&&currentNode.getY1()==currentNode.getY2()){
          this.matriz[i1][j1]=nuevo_valor;
          currentNode.setSuma(this.matriz[currentNode.getY1()][currentNode.getX1()]);
          currentNode.setValorMaximo(this.matriz[currentNode.getY1()][currentNode.getX1()]);
          
      }
         
        
        
        boolean estah1,estah2,estah3,estah4;
        try{
             estah1=i1<=currentNode.getH1().getY2()&&j1<=currentNode.getH1().getX2();//miramos en que hijos esta contenido
         }catch(Exception e){
             estah1=false;
         }
        if(estah1){
           long anterior=currentNode.getH1().getSuma();
            Update(currentNode.getH1(), i1, j1, nuevo_valor);
          NodoQ aux=currentNode.getH1().compareTo(currentNode.getH2())>0?currentNode.getH1():currentNode.getH2();
          aux=aux.compareTo(currentNode.getH3())>0?aux:currentNode.getH3();
          aux=aux.compareTo(currentNode.getH4())>0?aux:currentNode.getH4();
          currentNode.setJmax(aux.getJmax());
          currentNode.setXmax(aux.getXmax());
          currentNode.setValorMaximo(matriz[currentNode.getJmax()][currentNode.getXmax()]);
          currentNode.SumDelta(currentNode.getH1().getSuma()-anterior);
                  
          return;
        }
            
         try{
             estah2=i1<=currentNode.getH2().getY2()&&j1>=currentNode.getH2().getX1();
         }catch(Exception e){
             estah2=false;
         }
                if(estah2){
                    long anterior=currentNode.getH2().getSuma();
            Update(currentNode.getH2(), i1, j1, nuevo_valor);
     
          NodoQ aux=currentNode.getH1().compareTo(currentNode.getH2())>0?currentNode.getH1():currentNode.getH2();
          aux=aux.compareTo(currentNode.getH3())>0?aux:currentNode.getH3();
          aux=aux.compareTo(currentNode.getH4())>0?aux:currentNode.getH4();
          
         // Nodo m[]={currentNode.getH1(),currentNode.getH2(),currentNode.getH3(),currentNode.getH4()};
              
          
          //Arrays.sort(m,Collections.reverseOrder());
            currentNode.setJmax(aux.getJmax());
          currentNode.setXmax(aux.getXmax());
            //currentNode.setJmax(m[0].getJmax());
             //currentNode.setXmax(m[0].getXmax());
          currentNode.setValorMaximo(matriz[currentNode.getJmax()][currentNode.getXmax()]);
          currentNode.SumDelta(currentNode.getH2().getSuma()-anterior);
          return;
        } 
           try{
          estah3=currentNode.getH3().getY1()<=i1&&j1<=currentNode.getH3().getX2();     
           }catch(Exception e){
             estah3=false;
         }
            if(estah3){
                    long anterior=currentNode.getH3().getSuma();
            Update(currentNode.getH3(), i1, j1, nuevo_valor);
     
          NodoQ aux=currentNode.getH1().compareTo(currentNode.getH2())>0?currentNode.getH1():currentNode.getH2();
          aux=aux.compareTo(currentNode.getH3())>0?aux:currentNode.getH3();
          aux=aux.compareTo(currentNode.getH4())>0?aux:currentNode.getH4();
          currentNode.setJmax(aux.getJmax());
          currentNode.setXmax(aux.getXmax());
          currentNode.setValorMaximo(matriz[currentNode.getJmax()][currentNode.getXmax()]);
          currentNode.SumDelta(currentNode.getH3().getSuma()-anterior);
          return;
        } 
          try{
               estah4= currentNode.getH4().getY1()<=i1&&currentNode.getH4().getX1()<=j1; 
          }
          catch(Exception e){
             estah4=false;
         }
           if(estah4){
                    long anterior=currentNode.getH4().getSuma();
            Update(currentNode.getH4(), i1, j1, nuevo_valor);
     
          NodoQ aux=currentNode.getH1().compareTo(currentNode.getH2())>0?currentNode.getH1():currentNode.getH2();
          aux=aux.compareTo(currentNode.getH3())>0?aux:currentNode.getH3();
          aux=aux.compareTo(currentNode.getH4())>0?aux:currentNode.getH4();
          currentNode.setJmax(aux.getJmax());
          currentNode.setXmax(aux.getXmax());
          currentNode.setValorMaximo(matriz[currentNode.getJmax()][currentNode.getXmax()]);
          currentNode.SumDelta(currentNode.getH4().getSuma()-anterior);
          return;
        } 
    }
}
class NodoQ implements Comparable<NodoQ>{
    private int x1;
    private int y1;
    private int x2;
    private int y2;
   // private Nodo padre;
    private NodoQ h1;
    private NodoQ h2;
     private NodoQ h3;
     private NodoQ h4;
     private int jmax;
     private int xmax;
     private int valorMaximo;
     private long Suma;
public void SumDelta(Long valor){
    this.Suma+=valor;
}
    public int getValorMaximo() {
        return valorMaximo;
    }

    public long getSuma() {
        return Suma;
    }

    public void setValorMaximo(int valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public void setSuma(long Suma) {
        this.Suma = Suma;
    }
    public NodoQ(int y1, int x1, int y2, int x2/*, Nodo Padre*/) {
    //this.padre=Padre;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

   // public void setPadre(Nodo padre) {
     //   this.padre = padre;
    //}

    public void setH1(NodoQ h1) {
        this.h1 = h1;
    }

    public void setH2(NodoQ h2) {
        this.h2 = h2;
    }

    public void setH3(NodoQ h3) {
        this.h3 = h3;
    }

    public void setH4(NodoQ h4) {
        this.h4 = h4;
    }

    public void setJmax(int jmax) {
        this.jmax = jmax;
    }

    public void setXmax(int xmax) {
        this.xmax = xmax;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

   // public Nodo getPadre() {
     //   return padre;
    //}

    public NodoQ getH1() {
        return h1;
    }

    public NodoQ getH2() {
        return h2;
    }

    public NodoQ getH3() {
        return h3;
    }

    public NodoQ getH4() {
        return h4;
    }

    public int getJmax() {
        return jmax;
    }

    public int getXmax() {
        return xmax;
    }

    @Override
    public int compareTo(NodoQ o) {
      if(o==null) return 1;
 
      if(this==null)return -1;
      if(this.getValorMaximo()!=o.getValorMaximo())return this.getValorMaximo()-o.getValorMaximo();
      if(this.getJmax()!=o.getJmax())return (this.getJmax()-o.getJmax())*-1;
      return (this.getXmax()-o.getXmax())*-1;
    }
  

} 