/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciudad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author ProBook
 */
public class Ciudad {
//SOLUCION OPTIMA FINAL DEL EJERCICIO
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
       BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
       String s=sc.readLine();
       if(s.equals("CIUDAD")){
          StringTokenizer st=new StringTokenizer(sc.readLine());
          int n=Integer.parseInt(st.nextToken());
          int m=Integer.parseInt(st.nextToken());
          SegsegTree ciudad=new SegsegTree();
            st=new StringTokenizer(sc.readLine());
            int f1=Integer.parseInt(st.nextToken());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int [][]matriz=new int [ProximaPotencia2(n)+1][ProximaPotencia2(m)+1];
            matriz[1][1]=f1;
            for(int i=2;i<=m;i++){
                matriz[1][i]=((a*matriz[1][i-1])+b)%1000000;
            }
            for(int i=2;i<=n;i++){
                for(int j=1;j<=m;j++){
                    if(j==1){
                        matriz[i][j]=((a*matriz[i-1][m])+b)%1000000;
                    }else{
                        matriz[i][j]=((a*matriz[i][j-1])+b)%1000000;
                    }
                }
            }
            ciudad.Initialize(matriz, n, m);
           
           while((s=sc.readLine())!=null){
               st=new StringTokenizer(s);
               if(st.nextToken().equals("MARCHA")){
                  int i1,j1,i2,j2;
                  i1=Integer.parseInt(st.nextToken());
                  j1=Integer.parseInt(st.nextToken());
                  i2=Integer.parseInt(st.nextToken());
                  j2=Integer.parseInt(st.nextToken());
                  int p;
                  if(i1>i2){
                      p=i1;
                      i1=i2;
                      i2=p;
                  }
                  if(j1>j2){
                      p=j1;
                      j1=j2;
                      j2=p;
                  }
                   System.out.println(ciudad.Solve(i1,j1 ,i2 ,j2));
               
           }else{
                  
                   ciudad.Update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
               }
       }

    }else{
           int n=Integer.parseInt(sc.readLine());
           SegmentTree avenida=new SegmentTree(n);
           int f1,a,b;
           StringTokenizer st=new StringTokenizer(sc.readLine());
           
           
           f1=Integer.parseInt(st.nextToken());
           a=Integer.parseInt(st.nextToken());
           b=Integer.parseInt(st.nextToken());
           int []arreglo=new int [n+1];
           arreglo[1]=f1;
           for(int i=2;i<n+1;i++){
               arreglo[i]=((a*arreglo[i-1])+b)%1000000;
           }
       
           avenida.Initialize(arreglo, n, 0);
           while((s=sc.readLine())!=null){
               st=new StringTokenizer(s);
               if(st.nextToken().equals("MARCHA")){
                    int i1,i2;
                    i1=Integer.parseInt(st.nextToken());
                   i2=Integer.parseInt(st.nextToken());
                   if(i2<i1){
                       int p=i2;
                       i2=i1;
                       i1=p;
                   }
                   System.out.println(avenida.Consulta(i1, i2));
                   
               }else{
                  
                  
                  avenida.Update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
               }
           }
       
       }
    }
    public static int ProximaPotencia2(int n){
        int p=1;
        while(p<n){
            p*=2;
        }
        return p;
    }

}

class Nodo1d {

    int j1, j2;
    int max;
    long sum;
int imax;
    int jmax;

    public Nodo1d(int j1, int j2) {
        this.j1 = j1;
        this.j2 = j2;
    }

    public void setImax(int imax) {
        this.imax = imax;
    }

    public int getImax() {
        return imax;
    }

    public int getJ1() {
        return j1;
    }

    public void setJmax(int jmax) {
        this.jmax = jmax;
    }

    public int getJmax() {
        return jmax;
    }

    public int getJ2() {
        return j2;
    }

    public int getMax() {
        return max;
    }

    public long getSum() {
        return sum;
    }

    public void setJ1(int j1) {
        this.j1 = j1;
    }

    public void setJ2(int j2) {
        this.j2 = j2;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public void Unir(Nodo1d n1, Nodo1d n2) {
        if (n1 == null || n2 == null) {
            if (n2 != null && n1 == null) {
                this.j1 = n2.getJ1();
                this.j2 = n2.getJ2();
                this.setJmax(n2.getJmax());
                this.setMax(n2.getMax());
                this.setImax(n2.getImax());
                this.setSum(n2.getSum());
                return;
            } else if (n1 != null && n2 == null) {
                this.j1 = n1.getJ1();
                this.j2 = n1.getJ2();
                this.setJmax(n1.getJmax());
                this.setMax(n1.getMax());
                this.setImax(n1.getImax());
                this.setSum(n1.getSum());
            return;
            }
            

        }

        if (n1.getMax() != n2.getMax()) {
            if (n1.getMax() > n2.getMax()) {
                this.max = n1.getMax();
                this.jmax = n1.getJmax();
                this.imax=n1.getImax();
            } else {
                this.max = n2.getMax();
                this.jmax = n2.getJmax();
                this.imax=n2.getImax();
            }

        } else {

           
           if((n1.getImax()!=n2.getImax())){
            boolean n1menor = n1.getImax() < n2.getImax();    
           
           if (n1menor) {
                this.max = n1.getMax();
                this.jmax = n1.getJmax();
                this.imax=n1.getImax();

            } else {
                this.max = n2.getMax();
                this.jmax = n2.getJmax();
            this.imax=n2.getImax();
            }
           }else{
            boolean n1menor=n1.getJmax()<n2.getJmax();
            if(n1menor){
                this.max=n1.getMax();
                this.jmax=n1.getJmax();
                this.imax=n1.getImax();
            }else{
                 this.max=n2.getMax();
                this.jmax=n2.getJmax();
                this.imax=n2.getImax();
            }
           } 
           
        }

        this.j1 = n1.getJ1();
        this.j2 = n2.getJ2();
        this.sum = n1.getSum() + n2.getSum();

    }
}

class SegmentTree {
   int i1,i2;
   
  
    Nodo1d[] arbol;
    int[] arregloInicial;
    
    public SegmentTree(int size,int i1,int i2) {
        int numero=SiguientePotencia2(size);
        this.i1=i1;
        this.i2=i2;
        this.arbol = new Nodo1d[numero * 2];

    }
    public SegmentTree(int size){
            int numero=SiguientePotencia2(size);  
            this.arbol = new Nodo1d[numero * 2];
    }
public String toString(){
    return "["+this.i1+","+this.i2+","+this.arbol[1].getSum()+","+this.arbol[1].getImax()+"]\n";
}
    private int SiguientePotencia2(int i) {
        int n = 1;
        while (n < i) {
            n *= 2;
        }
        return n;
    }
public String Consulta(int j1, int j2){
    Nodo1d n=Solve(j1, j2);
    return n.getJmax()+" "+n.getSum();
}
    public void Initialize(int[] arreglo, int elementos,int numfila) {

        this.arregloInicial = arreglo;

        int inicio = this.arbol.length / 2;
        for (int i = inicio; i < (inicio) + elementos; i++) {
            this.arbol[i] = new Nodo1d(i - inicio + 1, i - inicio + 1);
            
            this.arbol[i].setJmax(i - inicio + 1);
            this.arbol[i].setImax(numfila);
            this.arbol[i].setJ1(i - inicio + 1);
           this.arbol[i].setJ2(i - inicio + 1);
           this.arbol[i].setMax(this.arregloInicial[i-inicio+1]);
            this.arbol[i].setSum(this.arregloInicial[i - inicio + 1]);

        }
        for(int i=inicio+elementos;i<this.arbol.length;i++){
            this.arbol[i]=new Nodo1d(i - inicio+1 ,i - inicio+1 );
            this.arbol[i].setJmax(i - inicio +1);
            this.arbol[i].setImax(numfila);
            this.arbol[i].setJ1(i - inicio +1);
           this.arbol[i].setJ2(i - inicio +1);
           this.arbol[i].setMax(0);
            this.arbol[i].setSum(0);
        }
        for (int i = this.arbol.length - 1; i > 1; i--) {
            this.arbol[i / 2] = new Nodo1d(0, 0);
            this.arbol[i / 2].Unir(this.arbol[(i / 2) * 2], this.arbol[((i / 2) * 2) + 1]);

        }

    }
private int NodoActualizado;
public int getNodoActualizado(){
    return this.NodoActualizado;
}
        
    public void Update(int j, int newValue) {

        int inicio = this.arbol.length / 2;
        this.arregloInicial[j] = newValue;
        int n = inicio + j - 1;
        this.NodoActualizado=n;
        this.arbol[n].setMax(this.arregloInicial[j]);
        this.arbol[n].setSum(this.arregloInicial[j]);
        while (n > 1) {
            this.arbol[n / 2].Unir(this.arbol[(n / 2) * 2], this.arbol[((n / 2) * 2) + 1]);
            n/=2;
        }

    }
    public void PercolateUp(int indice){
        int currentNode=indice;
        while(currentNode>1){
            this.arbol[currentNode/2].Unir(this.arbol[(currentNode/2)*2], this.arbol[((currentNode/2)*2)+1]);
       currentNode/=2;
        }
    }
    public void Unir(SegmentTree s1, SegmentTree s2){
       
        this.arbol=new Nodo1d[s1.arbol.length];
        for(int i=1;i<this.arbol.length;i++){
        this.arbol[i]=new Nodo1d(0, 0);
        this.arbol[i].Unir(s1.arbol[i], s2.arbol[i]);
         this.i1=s1.i1;
         this.i2=s2.i2;
        }
        
        
    }
    public Nodo1d Solve(int j1,int j2 ){
        return Solve(1, j1, j2);
    }
    private Nodo1d Solve(int nodo,int j1,int j2){
        if(this.arbol[nodo].getJ1()==j1&&this.arbol[nodo].getJ2()==j2){
            return this.arbol[nodo];
        }
        if(this.arbol[nodo*2].getJ1()<=j1&&this.arbol[nodo*2].getJ2()>=j2){
            return Solve(nodo*2,j1,j2);
        }
        if(this.arbol[(nodo*2)+1].getJ1()<=j1&&this.arbol[(nodo*2)+1].getJ2()>=j2){
            return Solve((nodo*2)+1,j1,j2);
        }
    Nodo1d izq=Solve(nodo*2, j1, this.arbol[nodo*2].j2);
    Nodo1d der=Solve((nodo*2)+1,this.arbol[nodo*2].j2+1,j2);
    Nodo1d n=new Nodo1d(0, 0);
    n.Unir(izq, der);
    return n;
    }
    

}
class SegsegTree{

SegmentTree[]segmentos;        

    public SegsegTree() {
        
   
    }
    
    private int SiguientePotencia2(int i) {
        int n = 1;
        while (n < i) {
            n *= 2;
        }
        return n;
    }
    
    public void Initialize(int [][]matriz, int n, int m){
        this.segmentos= new SegmentTree[SiguientePotencia2(n)*2];
        for(int i=this.segmentos.length/2;i<(this.segmentos.length/2)+n;i++){
            this.segmentos[i]=new SegmentTree(m,i-(this.segmentos.length/2)+1,i-(this.segmentos.length/2)+1);
            this.segmentos[i].Initialize(matriz[i-(this.segmentos.length/2)+1], m,i-(this.segmentos.length/2)+1);
          }
        for(int i=(this.segmentos.length/2)+n;i<this.segmentos.length;i++){
            this.segmentos[i]=new SegmentTree(m,i-(this.segmentos.length/2)+1,i-(this.segmentos.length/2)+1);
            this.segmentos[i].Initialize(matriz[i-(this.segmentos.length/2)+1], m,i-(this.segmentos.length/2)+1);
        }
        
        for(int i=this.segmentos.length-1;i>1;i--){
            this.segmentos[i/2]=new SegmentTree(m);
            this.segmentos[i/2].Unir(this.segmentos[(i/2)*2], this.segmentos[((i/2)*2)+1]);
        }
    }
    public void Update(int i, int j,int newValue){
        this.segmentos[(this.segmentos.length/2)+i-1].Update(j, newValue);
        int currentNode=(this.segmentos.length/2)+i-1;
         int NodoActualizado=this.segmentos[(this.segmentos.length/2)+i-1].getNodoActualizado();
        while(currentNode>1){
           // this.segmentos[currentNode/2].Unir(this.segmentos[(currentNode/2)*2], this.segmentos[((currentNode/2)*2)+1]);
           Nodo1d n=new Nodo1d(0, 0);
           n.Unir(this.segmentos[(currentNode/2)*2].arbol[NodoActualizado], this.segmentos[((currentNode/2)*2)+1].arbol[NodoActualizado]);
           this.segmentos[currentNode/2].arbol[NodoActualizado]=n;
           this.segmentos[currentNode/2].PercolateUp(NodoActualizado);
           currentNode/=2;
        }
        
 
        
    }
    public String Solve (int i1,int j1,int i2,int j2){
        Nodo1d nodo=Solve(1, i1, j1, i2, j2);
        return nodo.getImax()+" "+nodo.getJmax()+" "+nodo.getSum();
    }
private  Nodo1d Solve(int nodo,int i1, int j1,int i2, int j2){
    if(this.segmentos[nodo].i1==i1&&this.segmentos[nodo].i2==i2){
        
        return this.segmentos[nodo].Solve(j1,j2);
    }else if(this.segmentos[nodo*2].i1<=i1&&this.segmentos[nodo*2].i2>=i2){
        return Solve(nodo*2, i1,j1, i2,  j2);
        
    }else if(this.segmentos[(nodo*2)+1].i1<=i1&&this.segmentos[(nodo*2)+1].i2>=i2){
        return Solve((nodo*2)+1, i1, j1,i2 ,j2);
}else{
        Nodo1d izq=Solve(nodo*2,i1, j1, this.segmentos[nodo*2].i2, j2);
        Nodo1d der=Solve((nodo*2)+1,this.segmentos[nodo*2].i2+1, j1, i2, j2);
        Nodo1d n=new Nodo1d(0, 0);
        n.Unir(izq, der);
        return n;
    }
    
}
public String toString(){
    StringBuilder s=new StringBuilder();
    for(int i=1;i<this.segmentos.length;i++){
        s.append(this.segmentos[i].toString());
    }
            return s.toString();
}
}