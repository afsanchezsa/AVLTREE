/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theordenamiento;

/**
 *
 * @author COMPAQ
 */
public class TheOrdenamiento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
           int[]vector={2,1,8,7,11}; 
                Quick q=new Quick();
        q.Ordenar(vector, 0, vector.length-1);
        mostrar(vector);
    }
        public static void mostrar(int[]a ){
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]+"--");
        }
    }
    
}
class Burbuja {
    int i,j,temporal;

    public Burbuja() {
        this.i=0;
        this.j=0;
        this.temporal=0;
    }
    public int[] Ordernar(int []vector){
        for(i=0;i<vector.length;i++){
            for(j=i+1;j<vector.length;j++){
                if(vector[i]<vector[j]){
                    
                }else{
                    this.temporal=vector[i];
                    vector[i]=vector[j];
                    vector[j]=this.temporal;
                }
            }
        }
        return vector;
    }
}
class Insercion {
    /**
     * 
     * @param arreglo arreglo de enteros para ordenar 
     * @param n
     * @return 
     */
     
    public int [] ordenar(int[] arreglo, int n){
        int i,j,auxiliar;
       for(i=1;i<n;i++){
           auxiliar=arreglo[i];
           j=i-1;
           while(j>=0 &&arreglo[j]>auxiliar){
               arreglo[j+1]=arreglo[j];
               j--;
           }
         arreglo[j+1]=auxiliar;
           
                   
                   
       }
       return arreglo;
    }
}
class Quick {
    
    
    
    public void Ordenar(int []arreglo,int primero,int ultimo){
        int i,j,pivote,auxiliar;
        i=primero;
        j=ultimo;
        pivote=arreglo[(primero+ultimo)/2];
    
        do{
            while(arreglo[i]<pivote){
                i++;
            }
            while(arreglo[j]>pivote){
                j--;
            }
            if(i<=j){
                auxiliar=arreglo[i];
                arreglo[i]=arreglo[j];
                arreglo[j]=auxiliar;
                i++;
                j--;
                
            }
                
        }while(i<=j);
      
        
        if(primero<j){
            Ordenar(arreglo, primero, j);
        }if(i<ultimo){
            Ordenar(arreglo,i,ultimo);
        }
      
        
        //mostrar(arreglo);
    }
    
 public  void mostrar(int[]a ){
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]+"--");
        }
    }
}
class radix {

    public int[] ordenar(int[] arreglo) {
        int x, i, j;
        for (x = Integer.SIZE; x >= 0; x--) {
            int auxiliar[] = new int[arreglo.length];
            j = 0;
            for (i = 0; i < arreglo.length; i++) {
                boolean mover = arreglo[i] << x >= 0;
                if (x == 0 ? !mover : mover) {
                    auxiliar[j] = arreglo[i];
                  
                    j++;
                } else {
                    arreglo[i - j] = arreglo[i];
                }
            }
            for (i = j; i < auxiliar.length; i++) {
auxiliar[i]=arreglo[i-j];
            }
            arreglo=auxiliar;
        }
   return arreglo;
    }
    }