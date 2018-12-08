/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;



/**
 *
 * @author COMPAQ
 */
public class Ejercicio10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
       /*  Integer []test={1,2,3,4,5,6,7,8,9};
       SegmentTree  arbol_de_seg=new SegmentTree();
       arbol_de_seg.Initialize(test);
     //  arbol_de_seg.Update(1, 2);
       //arbol_de_seg.Update(9,8);
//arbol_de_seg.Update(4, 9);
        System.out.println(arbol_de_seg.toString());
System.out.println(arbol_de_seg.Solve(2, 9));
       
      // System.out.println(arbol_de_seg.toString());
        */
  
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        String line = "";
        String[] nums = null;
        SegmentTree arbol = new SegmentTree();

        int n = 0, f_1 = 0, a = 0, b = 0;
        Integer[] arreglo;
        while ((line = sc.readLine()) != null) {
            if (line.equals("AVENIDA")) {
                
                    n = Integer.parseInt(sc.readLine());
                    nums = sc.readLine().split(" ");

                    f_1 = Integer.parseInt(nums[0]);
                    a = Integer.parseInt(nums[1]);
                    b = Integer.parseInt(nums[2]);
                    arreglo = new Integer[n];

                    arreglo[0] = f_1;
                    for (int i = 1; i < n; i++) {
                        arreglo[i] = ((a * arreglo[i - 1]) + b) % 1000000;

                    }
                    arbol.Initialize(arreglo);
      
                    
                    }else if(line.matches("MARCHA(.*)")){
                    nums=line.split(" ");
               

int i=Integer.parseInt(nums[1]);
int j=Integer.parseInt(nums[2]);
if(i>j){
  
    //System.out.println(arbol.Solve(j, i)+" "+arbol.sumar(j, i));
    System.out.println(arbol.Solve(j, i)+" "+arbol.sumar(j,i));
   
}
    
                  else {
  
    //System.out.println(arbol.Solve(i, j)+" "+arbol.sumar(i, j));
    System.out.println(arbol.Solve(i, j)+" "+arbol.sumar(i,j));
}
    
               
                 
                    
                    
            }else if(line.matches("ALCALDE(.*)")){
                nums=line.split(" ");
                arbol.Update(Integer.parseInt(nums[1]),Integer.parseInt(nums[2]));
               
             
            }else{
                
            }
                
            
                
                

        }

    }

}

interface IntSegmentTree<T> {

    int size();

    boolean isEmpty();

    T Solve(int i, int j);

    void Update(int i, T x);

    void Initialize(T[] x);

}

class Intervalo implements Comparable<Intervalo> {

    int inicio, fin;
    int numHoja;
    Integer valor;
    long suma;

    public Intervalo(int inicio, int fin) {
        this.fin = fin;
        this.inicio = inicio;
        valor = null;
        suma=0;
        //if(inicio==fin) numHoja=inicio;
    }

    public Intervalo() {
        this.fin = 0;
        this.inicio = 0;
        valor = null;
        suma=0;
    }

    public String toString() {
        if (valor != null) {
            return "[" + inicio + "," + fin + "," + valor + ","+suma+","+numHoja+"]";
        }
        return "[" + inicio + "," + fin + "," + "null,"+suma+","+numHoja+"]";
    }

    public void setValor(Integer valor) {
        this.valor = valor;
        
   this.suma=(valor==null)?0:valor;
    if(valor==null)this.numHoja=0;
    }

    @Override
    public int compareTo(Intervalo o) {
        if (this.valor == null) {
            return -1;
        } else if (o.valor == null) {
            return 1;
        }else if( this.valor.compareTo(o.valor)!=0){
           return this.valor.compareTo(o.valor);
            
        } 
        else {
            
            return (this.numHoja-o.numHoja)*-1;
        }
    }



}

class SegmentTree implements IntSegmentTree<Integer> {

    Intervalo[] arraySegment;
    int size;
  
   
 
           
    public long getSuma(){
         return arraySegment[1].suma;
    }
    public SegmentTree(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("initial Capacity must be >=0");
        }
        this.arraySegment = null;//(Intervalo<T>[])new Object[initialCapacity+1];
        this.size = 0;
    
      
    }

    public SegmentTree() {
        this(10);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Integer Solve(int i, int j) {
        
       
        
        return Solve(1, i - 1, j - 1).numHoja;
    }

    
    
 

   
    
private Intervalo Solve(int p, int i, int j) {//retorna el numero de la hoja con mayor valor
        int n = p;
        
        while (n <= size) {
            if (arraySegment[n].inicio == arraySegment[n].fin) {
                
              
                return arraySegment[n];
            } else if (arraySegment[n * 2].inicio <= i && arraySegment[n * 2].fin >= j) {
                if (arraySegment[n * 2].inicio == i && arraySegment[n * 2].fin == j) {
                  
                    
                return arraySegment[n*2];
                } else {
                    n *= 2;
                }

            } else if (arraySegment[(n * 2) + 1].inicio <= i && arraySegment[(n * 2) + 1].fin >= j) {
                if (arraySegment[(n * 2) + 1].inicio == i && arraySegment[(n * 2) + 1].fin == j) {
                
                
                return arraySegment[(n * 2) + 1];
                } else {
                    n = (n * 2) + 1;
                }

            } else {
                int k = (int) Math.floor((i + j) / 2);
                Intervalo der = this.Solve(n, i, k);
                Intervalo izq = this.Solve(n, k + 1, j);
           
                return der.compareTo(izq) > 0 ? der: izq;//tomamos el mayor(si coincide el valor de los intervalos el metodo compare to de la prioridad al que tenga menor numhoja
            }
        }
      
        return arraySegment[n];

       
    }    
public Long sumar(int i,int j){
    return sumar(1, i-1, j-1);
}
   private Long sumar(int p, int i, int j) {
        int n = p;
        
        while (n <= size) {
            if (arraySegment[n].inicio == arraySegment[n].fin) {
            
                return arraySegment[n].suma;
            } else if (arraySegment[n * 2].inicio <= i && arraySegment[n * 2].fin >= j) {
                if (arraySegment[n * 2].inicio == i && arraySegment[n * 2].fin == j) {
                 
                return arraySegment[n*2].suma;
                } else {
                    n *= 2;
                }

            } else if (arraySegment[(n * 2) + 1].inicio <= i && arraySegment[(n * 2) + 1].fin >= j) {
                if (arraySegment[(n * 2) + 1].inicio == i && arraySegment[(n * 2) + 1].fin == j) {
              
                   
                return arraySegment[(n * 2) + 1].suma;
                } else {
                    n = (n * 2) + 1;
                }

            } else {
                int k = (int) Math.floor((i + j) / 2);
               Long der = sumar(n, i, k);
                Long izq = sumar(n, k + 1, j);
              
                return der+izq;//pues estamos comparando T no intervalos entonces si me da prioridad para der es lo contrario:prioridad para izq
            }
        }
       
        return arraySegment[n].suma;

   
    }
    @Override
    public void Update(int i, Integer x) {
        percolateUp(i - 1, x);
    }

    private void percolateUp(int i, Integer nuevo) {
        int currentNode = (arraySegment.length / 2) + i;
        arraySegment[currentNode].setValor(nuevo);
        
     
        Intervalo x = new Intervalo(arraySegment[currentNode].inicio, arraySegment[currentNode].fin);
        x.setValor(nuevo);
        x.numHoja=i+1;
       
     
        while (currentNode != 1 && arraySegment[currentNode / 2].compareTo(x) < 0) {
             
            arraySegment[currentNode / 2].setValor(arraySegment[currentNode].valor); 
            arraySegment[currentNode / 2].numHoja=arraySegment[currentNode].numHoja; 
            
            arraySegment[currentNode/2].suma=arraySegment[(currentNode/2)*2].suma+arraySegment[((currentNode/2)*2)+1].suma;
            currentNode /= 2;
        }

        arraySegment[currentNode].setValor(nuevo);
        arraySegment[currentNode].numHoja=i+1;
        
        try{
            arraySegment[currentNode].suma=arraySegment[currentNode*2].suma+arraySegment[(currentNode*2)+1].suma;
        }catch(Exception e){
            
        }
        
         while (currentNode != 1 ) {
           
            
            arraySegment[currentNode/2].suma=arraySegment[(currentNode/2)*2].suma+arraySegment[((currentNode/2)*2)+1].suma;
    
            currentNode /= 2;
        }
       
    }

    @Override
    public void Initialize(Integer[] x) {
        int length = x.length;

        this.arraySegment = new Intervalo[LengthArraySegment(length) + 1];//sumamos 1 pues la casilla cero no se ocupa.pero debe contarse
        //NO PUSE new Intervalo<T> pues ya se sabe que arraySegment tiene T adentro

        this.arraySegment[1] = new Intervalo(0, PotenciaDe2siguiente(length) - 1);//pues si el numero no es potencia de 2 toca ampliar los intervaloshasta uno que sea potencia de 2
        size++;
        ArrayQueue<Intervalo> cola = new ArrayQueue<>();
        cola.put(arraySegment[1]);
        Intervalo temporal = null;
        int i = 1;
     

        while (!cola.isEmpty() && i < arraySegment.length) {

            temporal = cola.remove();
            arraySegment[i] = new Intervalo(temporal.inicio, temporal.fin);
            size++;
            cola.put(new Intervalo(temporal.inicio, (temporal.inicio + temporal.fin) / 2));
            cola.put(new Intervalo(((temporal.inicio + temporal.fin) / 2) + 1, temporal.fin));
            i++;
        }
//ahora debemos poner en las hojas los valores del arreglo
        size--;

        i = 0;

        for (int j = (arraySegment.length / 2); j < arraySegment.length; j++, i++) {
            if (i < length) {
                arraySegment[j].setValor(x[i]);
                arraySegment[j].numHoja=i+1;
            } else {
                arraySegment[j].setValor(null);//si el valor del intervalo es nulo su prioridad es infinita
            }
        }

        //ahora hacemos propagacion hacia arriba
        
        for (int root = size / 2; root >= 1; root--) {
            Intervalo rootElement = arraySegment[root];
            int child = 2 * root;
            while (child <= size) {
                if (child < size && arraySegment[child].compareTo(arraySegment[child + 1]) < 0) {
                    child++;
                }
                if (rootElement.compareTo(arraySegment[child]) >= 0) {
                    break;
                }
                //arraySegment[child/2]=arraySegment[child];
                arraySegment[child / 2].setValor(arraySegment[child].valor) ;
                     
                arraySegment[child/2].suma=(arraySegment[(child/2)*2].suma+arraySegment[((child/2)*2)+1].suma);
             arraySegment[child / 2].numHoja=arraySegment[child].numHoja;
                child *= 2;
            }
            arraySegment[child / 2].setValor(rootElement.valor); 
            arraySegment[child / 2].numHoja=rootElement.numHoja;
            
            
try{
    arraySegment[child/2].suma=arraySegment[(child/2)*2].suma+arraySegment[((child/2)*2)+1].suma;//arroja una excepcion cuando child/2 es una hoja pues se sale del indice pero como no necesitamos sumar cosas que no estan en el arreglo simplemente le decimos que no haga nada en caso de error
}catch(Exception e){
     
}
        }
    }

    public void Imprimirarbol() {

        for (int i = 1; i < arraySegment.length; i++) {
            System.out.println(arraySegment[i].toString());
        }
    }

    private int PotenciaDe2siguiente(int numero) {
        double lo = Math.ceil(Math.log(numero) / Math.log(2));
        return (int) Math.pow(2, lo);
    }

    private int PotenciaDe2anterior(int numero) {
        return PotenciaDe2siguiente(numero) / 2;
    }

    private int LengthArraySegment(int tamanoarreglo) {
        double i = Math.ceil(((Math.log(tamanoarreglo) / Math.log(2))));//le sumamos 1 pues queremos techo

        int altura = (int) Math.ceil(i);//funcion techo
        //  System.out.println(altura);
        return (int) (Math.pow(2, (int) (altura + 1))) - 1;//pues el tamaño debe ser una potencia de 2
    }
    public String toString(){
        StringBuilder s=new StringBuilder();
        for(int i=1;i<arraySegment.length;i++){
            s.append(arraySegment[i].toString()+"\n");
        }
        return s.toString();
    }
}

interface Queue<T> {

    boolean isEmpty();

    T getFrontElement();

    void put(T theObject);

    T remove();
}

class ArrayQueue<T> implements Queue<T> {

    int front;
    int back;
    T[] queue;

    public ArrayQueue(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("initial Capacity must be >=1");
        }

        queue = (T[]) new Object[initialCapacity + 1];
        front = back = 0;
    }

    public ArrayQueue() {
        this(10);
    }

    @Override
    public boolean isEmpty() {
        return back == front;
    }

    @Override
    public T getFrontElement() {
        if (isEmpty()) {
            return null;
        } else {
            return queue[(front + 1) % queue.length];
        }

    }

    @Override
    public void put(T theObject) {
        if ((back + 1) % queue.length == front) {
            T[] newQueue = (T[]) new Object[2 * queue.length];
            int start = (front + 1) % queue.length;
            if (start < 2) {
                System.arraycopy(queue, 0, newQueue, 0, queue.length - 1);//-1 pues el front esta vacio
            } else {
                System.arraycopy(queue, start, newQueue, 0, queue.length - start);
                System.arraycopy(queue, 0, newQueue, queue.length - start, back + 1);
            }
            front = newQueue.length - 1;
            back = queue.length - 2;
            queue = newQueue;
        }
        back = (back + 1) % queue.length;
        queue[back] = theObject;
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        front = (front + 1) % queue.length;
        T frontElement = queue[front];
        queue[front] = null;
        return frontElement;

    }

}
/*
    private Integer Solve(int p, int i, int j) {//retorna el numero de la hoja con mayor valor
        int n = p;
        
        while (n <= size) {
            if (arraySegment[n].inicio == arraySegment[n].fin) {
                
              
                return arraySegment[n].numHoja;
            } else if (arraySegment[n * 2].inicio <= i && arraySegment[n * 2].fin >= j) {
                if (arraySegment[n * 2].inicio == i && arraySegment[n * 2].fin == j) {
                  
                  
                return arraySegment[n*2].numHoja;
                } else {
                    n *= 2;
                }

            } else if (arraySegment[(n * 2) + 1].inicio <= i && arraySegment[(n * 2) + 1].fin >= j) {
                if (arraySegment[(n * 2) + 1].inicio == i && arraySegment[(n * 2) + 1].fin == j) {
                
               
                return arraySegment[(n * 2) + 1].numHoja;
                } else {
                    n = (n * 2) + 1;
                }

            } else {
                int k = (int) Math.floor((i + j) / 2);
                Integer der = this.Solve(n, i, k);
                Integer izq = this.Solve(n, k + 1, j);
              
                return der.compareTo(izq) > 0 ? der: izq;//pues estamos comparando T no intervalos entonces si me da prioridad para der es lo contrario:prioridad para izq
            }
        }
      
        return arraySegment[n].numHoja;

       
    }*/
    
