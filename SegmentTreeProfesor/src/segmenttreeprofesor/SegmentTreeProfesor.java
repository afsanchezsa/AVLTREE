/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segmenttreeprofesor;




/**
 *
 * @author COMPAQ
 */

public class SegmentTreeProfesor {

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
class SegmentTree{
    int []arreglo;
    Nodo[]seg_tree;
    boolean maximo;
private int k;
    public SegmentTree(boolean maximo) {
       this.maximo=maximo;
    
    }
    public void Initialize(int []arreglo){
         
         
        k=Potenciade2siguiente(arreglo.length);
     this.arreglo=new int[k];
     System.arraycopy(arreglo, 0, this.arreglo, 0, arreglo.length);
     if(maximo){
      for(int i=arreglo.length;i<this.arreglo.length;i++){
      
         this.arreglo[i]=Integer.MIN_VALUE;
     }      
        }else{
              for(int i=arreglo.length;i<this.arreglo.length;i++){
      
         this.arreglo[i]=Integer.MAX_VALUE;
     }      
     }
     
         
    this.seg_tree=new Nodo[k*2];
    for(int i=k;i<this.arreglo.length+k;i++){
       
        seg_tree[i]=new Nodo(i-k, i-k, this.arreglo[i-k], i-k);
    }
      
    for(int i=k-1;i>=1;i--)
        seg_tree[i]=unir(seg_tree[i*2],seg_tree[(i*2)+1]);
    }
    public void Update(int i,int newValue){
        int curr=i+k;
        this.arreglo[i]=newValue;
        this.seg_tree[curr].suma=newValue;
        while(curr>1){
            curr/=2;
            seg_tree[curr]=unir(seg_tree[curr*2], seg_tree[(curr*2)+1]);
        }
    }
    public String Solve(int izq,int der){
        Nodo n=Consulta(1,izq, der);
        return ""+this.arreglo[n.ind_max];
    }
    private  Nodo Consulta(int pos, int izq,int der){
        if(seg_tree[pos].izq==izq&&seg_tree[pos].der==der){
            return seg_tree[pos];
        }
        if(seg_tree[2*pos].der>=der&&seg_tree[2*pos].izq<=izq)
            return Consulta(2*pos,izq, der);
          if(seg_tree[(2*pos)+1].der>=der&&seg_tree[(2*pos)+1].izq<=izq)
            return Consulta((2*pos)+1,izq, der);
          return unir(Consulta(2*pos,izq,seg_tree[2*pos].der), Consulta((2*pos)+1,seg_tree[(2*pos)+1].izq, der));
    }
     private  Nodo unir(Nodo nizq,Nodo nder){
        Nodo unido=new Nodo();
        unido.izq=nizq.izq;
        unido.der=nder.der;
        unido.suma=nizq.suma+nder.suma;
        unido.ind_max=nizq.ind_max;
        if(!maximo){
             if(arreglo[nder.ind_max]<arreglo[nizq.ind_max])
            unido.ind_max=nder.ind_max;
        return unido;
        }
        
        if(arreglo[nder.ind_max]>arreglo[nizq.ind_max])
            unido.ind_max=nder.ind_max;
        return unido;
    }
    private int Potenciade2siguiente(int numero){
        int i=1;
        
        while(i<numero){
            i*=2;
        }
        return i;
    }
    
}
class Nodo{
    int izq;
    int der;
    long suma;
    int ind_max;

    public Nodo() {
    }

    public Nodo(int izq, int der) {
        this.izq = izq;
        this.der = der;
    }

    public Nodo(int izq, int der, long suma) {
        this.izq = izq;
        this.der = der;
        this.suma = suma;
    }

    public Nodo(int izq, int der, long suma, int ind_max) {
        this.izq = izq;
        this.der = der;
        this.suma = suma;
        this.ind_max = ind_max;
    }

    public int getIzq() {
        return izq;
    }

    public int getDer() {
        return der;
    }

    public long getSuma() {
        return suma;
    }

    public int getInd_max() {
        return ind_max;
    }

    public void setIzq(int izq) {
        this.izq = izq;
    }

    public void setDer(int der) {
        this.der = der;
    }

    public void setSuma(long suma) {
        this.suma = suma;
    }

    public void setInd_max(int ind_max) {
        this.ind_max = ind_max;
    }

   
    
   
}

