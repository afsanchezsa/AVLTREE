/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysegmenttree;

import java.util.LinkedList;

/**
 *
 * @author COMPAQ
 */
public class MySegmentTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       /* Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        SegmentTree<Integer> b = new SegmentTree<>();
        b.Initialize(a);
       
       
        b.Update(5, 0);
         b.Imprimirarbol();
         System.out.println( b.Solve(6, 7));
    */
       Integer test[]={-20,19,7,4,-10,5,100,1,3};//las posiciones van desde el cero
       SegmentTree arbol_de_seg=new SegmentTree();
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

interface IntSegmentTree<T> {

    int size();

    boolean isEmpty();

    T Solve(int i, int j);

    void Update(int i, T x);

    void Initialize(T[] x);

}

class Intervalo<T extends Comparable<? super T>> implements Comparable<Intervalo<T>> {

    int inicio, fin;
    T valor;
    boolean prioridadInfinita;

    public Intervalo(int inicio, int fin) {
        this.fin = fin;
        this.inicio = inicio;
        valor = null;
       
    }

    public Intervalo() {
        this.fin = 0;
        this.inicio = 0;
        valor = null;
      

    }

    public String toString() {
        if(valor!=null)
        return "[" + inicio + "," + fin + ","+valor.toString()+"]";
        return "[" + inicio + "," + fin + ","+"null]";
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    @Override
    public int compareTo(Intervalo<T> o) {
        if(this.valor==null)return -1;
        else if(o.valor==null)return 1; 
        else return this.valor.compareTo(o.valor)*-1;//por -1 pues tiene prioridad el minimo
    }

}

class SegmentTree<T extends Comparable<? super T>> implements IntSegmentTree<T> {

    Intervalo<T>[] arraySegment;
    int size;

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
 public T Solve(int i, int j) {
     return Solve(1,i,j);
     }
    private  T Solve(int p,int i, int j) {
        int n=p;
        while(n<=size){
             if(arraySegment[n].inicio==arraySegment[n].fin){
                 return arraySegment[n].valor;
             }
            else if(arraySegment[n*2].inicio<=i&&arraySegment[n*2].fin>=j){
                if(arraySegment[n*2].inicio==i&&arraySegment[n*2].fin==j)return arraySegment[n*2].valor;
                else {
                    n*=2;
                }
                        
            }else if(arraySegment[(n*2)+1].inicio<=i&&arraySegment[(n*2)+1].fin>=j){
                  if(arraySegment[(n*2)+1].inicio==i&&arraySegment[(n*2)+1].fin==j)return arraySegment[(n*2)+1].valor;
                  else{
                     n=(n*2)+1;
                  }
                       
            }
            else{
               // int k=(int)Math.floor((i+j)/2);
                T der=Solve(n*2,i,this.arraySegment[n*2].fin);
                T izq=Solve((n*2)+1,this.arraySegment[n*2].fin+1,j);
                return der.compareTo(izq)>0?izq:der;//pues estamos comparando T no intervalos entonces si me da prioridad para der es lo contrario:prioridad para izq
            }
        }
   return arraySegment[n].valor;
    
   /* if(i==j){
        
          for(int n=(arraySegment.length/2);n<arraySegment.length;n++){
             if(arraySegment[n].inicio==i)return arraySegment[n].valor; 
       }
    }else{
        if()
    }*/
    }

    @Override
    public void Update(int i, T x) {
        percolateUp(i, x);
    }
private void percolateUp(int i,T nuevo){
    int currentNode=(arraySegment.length/2)+i;
    arraySegment[currentNode].valor=nuevo;
    Intervalo<T> x=new Intervalo<>(arraySegment[currentNode].inicio, arraySegment[currentNode].fin);
    x.valor=nuevo;

   // System.out.println(arraySegment[currentNode/2].compareTo(x));
    while(currentNode>1){
        arraySegment[currentNode/2].valor=arraySegment[(currentNode/2)*2].valor.compareTo(arraySegment[((currentNode/2)*2)+1].valor)>0?arraySegment[(currentNode/2)*2].valor:arraySegment[((currentNode/2)*2)+1].valor;
        currentNode/=2;
    }
    
    arraySegment[currentNode].valor=nuevo;

}
    @Override
    public void Initialize(T[] x) {
        int length = x.length;

        this.arraySegment = new Intervalo[LengthArraySegment(length) + 1];//sumamos 1 pues la casilla cero no se ocupa.pero debe contarse
        //NO PUSE new Intervalo<T> pues ya se sabe que arraySegment tiene T adentro

        this.arraySegment[1] = new Intervalo<>(0, PotenciaDe2siguiente(length)-1);//pues si el numero no es potencia de 2 toca ampliar los intervaloshasta uno que sea potencia de 2
        size++;
        ArrayQueue<Intervalo<T>> cola = new ArrayQueue<>();
        cola.put(arraySegment[1]);
        Intervalo<T> temporal = null;
        int i = 1;
        //System.out.println("len"+(length-1));

        while (!cola.isEmpty() && i < arraySegment.length) {

            temporal = cola.remove();
            arraySegment[i] = new Intervalo<>(temporal.inicio, temporal.fin);
            size++;
            cola.put(new Intervalo<>(temporal.inicio, (temporal.inicio + temporal.fin) / 2));
            cola.put(new Intervalo<>(((temporal.inicio + temporal.fin) / 2) + 1, temporal.fin));
            i++;
        }
//ahora debemos poner en las hojas los valores del arreglo
size--;
     
        i = 0;
  
       for(int j=(arraySegment.length/2);j<arraySegment.length;j++,i++){
             if (i < length) {
                arraySegment[j].valor = x[i];
            } else {
                arraySegment[j].valor = null;//si el valor del intervalo es nulo su prioridad es infinita
            }
       }
       
       //ahora hacemos propagacion hacia arriba
for(int root=size/2;root>=1;root--){//empieza en size/1 y no en (size-1)/2 pues se debe tener en cuenta que la casilla cero esta vacia
    Intervalo<T> rootElement=arraySegment[root];
    int child=2*root;
    while(child<=size){
        if(child<size && arraySegment[child].compareTo(arraySegment[child+1])<0)
            child++;
        if(rootElement.compareTo(arraySegment[child])>=0)break;
        //arraySegment[child/2]=arraySegment[child];
        arraySegment[child/2].valor=arraySegment[child].valor;
        child*=2;
    }
    arraySegment[child/2].valor=rootElement.valor;
        
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
