/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysegmenttreefinal;

/**
 *
 * @author COMPAQ
 */
public class MysegmenttreeFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
           int test[]={-20,19,7,4,-10,5,100,1,3};//las posiciones van desde el cero
    //este es un arbol de maximos
       SegmentTree arbol_de_seg=new SegmentTree(false);//poner true si es arbol de maximos y false si es de minimos
       arbol_de_seg.Initialize(test);
        System.out.println(arbol_de_seg.Solve(2, 5));
         System.out.println(arbol_de_seg.Solve(0, 8));
          System.out.println(arbol_de_seg.Solve(6, 6));
          arbol_de_seg.Update(6, -99);
              System.out.println(arbol_de_seg.Solve(2, 5));
         System.out.println(arbol_de_seg.Solve(0, 8));
          
         System.out.println(arbol_de_seg.Solve(6, 6));
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
public String toString(){
    return ""+this.max;
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

    public void Unir(Nodo1d n1, Nodo1d n2,boolean arboldemaximos) {
       
   this.j1=n1.getJ1();
   this.j2=n2.getJ2();
   
   if(arboldemaximos){
       if(n1.getMax()>=n2.getMax()){
           this.max=n1.getMax();
           this.jmax=n1.getJmax();
       }else{
           this.max=n2.getMax();
           this.jmax=n2.getJmax();
       }
        /*  this.max=n1.getMax()>=n2.getMax()?n1.getMax():n2.getMax();
          this.jmax=n1.getMax()>=n2.getMax()?n1.getJmax():n2.getJmax();*/
   }else{
       if(n1.getMax()<=n2.getMax()){
           this.max=n1.getMax();
           this.jmax=n1.getJmax();
       }else{
           this.max=n2.getMax();
           this.jmax=n2.getJmax();
       }
        /*this.max=n1.getMax()<=n2.getMax()?n1.getMax():n2.getMax();
         this.jmax=n1.getMax()<=n2.getMax()?n1.getJmax():n2.getJmax();*/
   }
this.sum=n1.getSum()+n2.getSum();
        /* if (n1 == null || n2 == null) {
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
*/
    }
}

class SegmentTree {
   int i1,i2;
   
  boolean arboldemaximos;
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
    public SegmentTree(boolean arboldemaximos){
        this.arboldemaximos=arboldemaximos;
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
    public void Initialize(int[] arreglo) {
        int elementos=arreglo.length;
        this.arbol=new Nodo1d[SiguientePotencia2(elementos)*2];
        
        this.arregloInicial = arreglo;
        
        int inicio = this.arbol.length / 2;
        for (int i = inicio; i < (inicio) + elementos; i++) {
            this.arbol[i] = new Nodo1d(i - inicio , i - inicio );
            
            this.arbol[i].setJmax(i - inicio );
            //this.arbol[i].setImax(numfila);
            this.arbol[i].setJ1(i - inicio );
           this.arbol[i].setJ2(i - inicio );
            
           this.arbol[i].setMax(this.arregloInicial[i-inicio]);
            this.arbol[i].setSum(this.arregloInicial[i - inicio ]);

        }
        for(int i=inicio+elementos;i<this.arbol.length;i++){
            this.arbol[i]=new Nodo1d(i - inicio ,i - inicio );//ojo cambiar aqui
            this.arbol[i].setJmax(i - inicio );
            //this.arbol[i].setImax(numfila);
            this.arbol[i].setJ1(i - inicio );
           this.arbol[i].setJ2(i - inicio );
           this.arbol[i].setMax(arboldemaximos?Integer.MIN_VALUE:Integer.MAX_VALUE);
            this.arbol[i].setSum(0);
        }
        
        for (int i = this.arbol.length - 1; i > 1; i--) {
            this.arbol[i / 2] = new Nodo1d(0, 0);
            this.arbol[i / 2].Unir(this.arbol[(i / 2) * 2], this.arbol[((i / 2) * 2) + 1],this.arboldemaximos);

        }

    }
private int NodoActualizado;
public int getNodoActualizado(){
    return this.NodoActualizado;
}
        
    public void Update(int j, int newValue) {

        int inicio = this.arbol.length / 2;
        this.arregloInicial[j] = newValue;
        int n = inicio + j ;
        this.NodoActualizado=n;
        this.arbol[n].setMax(this.arregloInicial[j]);
        this.arbol[n].setSum(this.arregloInicial[j]);//ojo nunca actualizar posiciones que no esten en el arreglo(lo que sobran al aproximarlo a la proxima potencia de 2)
        while (n > 1) {
            this.arbol[n / 2].Unir(this.arbol[(n / 2) * 2], this.arbol[((n / 2) * 2) + 1],this.arboldemaximos);
            n/=2;
        }

    }
    public void PercolateUp(int indice){
        int currentNode=indice;
        while(currentNode>1){
            this.arbol[currentNode/2].Unir(this.arbol[(currentNode/2)*2], this.arbol[((currentNode/2)*2)+1],this.arboldemaximos);
       currentNode/=2;
        }
    }
    public void Unir(SegmentTree s1, SegmentTree s2){
       
        this.arbol=new Nodo1d[s1.arbol.length];
        for(int i=1;i<this.arbol.length;i++){
        this.arbol[i]=new Nodo1d(0, 0);
        this.arbol[i].Unir(s1.arbol[i], s2.arbol[i],this.arboldemaximos);
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
    n.Unir(izq, der,this.arboldemaximos);
    return n;
    }
    

}