/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaquizmonticulos;





/**
 *
 * @author COMPAQ
 */
public class PruebaQuizMonticulos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        MaxHeap<Estudiante>hs=new MaxHeap<>();
        hs.add(new Estudiante(13,"50","Abelardo"));
        hs.add(new Estudiante(6,"27","Benito"));
        hs.add(new Estudiante(2,"50","Cesidia"));
        hs.add(new Estudiante(15,"1","Dominga"));
        hs.add(new Estudiante(7,"100","Epifanio"));
        System.out.println("sale"+hs.removeMax().tosString());
        hs.add(new Estudiante(8,"3","Fulano"));
        hs.add(new Estudiante(34,"100","Gregoria"));
        hs.add(new Estudiante(3,"270","Hermelando"));
           System.out.println("sale"+hs.removeMax().tosString());    System.out.println("sale"+hs.removeMax().tosString());
        hs.add(new Estudiante(28,"1","Iscariote"));
        hs.add(new Estudiante(10,"50","Jacinto"));
        while(!hs.isEmpty()){
           System.out.println(hs.removeMax().tosString());
        }
        System.out.println("270".compareTo("27"));
    }
    
}
class Estudiante implements Comparable<Estudiante>{
    Integer id;
    String codigo;
    String nombre;

    public Estudiante(Integer id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Estudiante() {
        codigo=null;
    nombre=null;
    id=0;
    }

    @Override
    public int compareTo(Estudiante x) {
        if(codigo.compareTo(x.codigo)<0 ||(codigo.compareTo(x.codigo))==0&&id>x.id)return 1;
        if(codigo.equals(x.codigo)&&id==x.id)return 0;
        return -1;
    }
            public String tosString(){
                return this.nombre;//((Character)nombre.charAt(0)).toString();
            }
}

interface PriorityQueue <T>{

    boolean isEmpty();

    int size();

    void add(T x);

    T getMax();

    T removeMax();
}

class MaxHeap <T extends   Comparable <?super T>> implements PriorityQueue<T>{
T[]BinaryHeap;
int size;
public MaxHeap(int initialCapacity) {
        // if(initialCapacity<1) throw new IllegalArgumentException("Initial Capacity must be >=1");
        BinaryHeap = (T[])new Comparable[initialCapacity + 1];
        size = 0;
    }

    public MaxHeap() {
        this(10);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

 

    @Override
       public void add(T x) {
        if (size == BinaryHeap.length - 1) {
           T[] old = BinaryHeap;
            BinaryHeap = (T[])new Comparable[2 * (size + 1)];
            System.arraycopy(old, 0, BinaryHeap, 0, size + 1);
        }
        int currentNode = ++size;

        while (currentNode != 1 && BinaryHeap[currentNode / 2].compareTo(x) < 0) {
            BinaryHeap[currentNode] = BinaryHeap[currentNode / 2];
            
            currentNode /= 2;

        }

        BinaryHeap[currentNode] = x;
        

    }

    @Override
    public T getMax() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T removeMax() {
              if (size == 0) {
            return null;
        }
        T maxElement = BinaryHeap[1];

        
        T lastElement = BinaryHeap[size--];
        int currentNode = 1;

        int child = 2;
        while (child <= size) {
            if (child < size && BinaryHeap[child].compareTo(BinaryHeap[child + 1]) <0) 
                child++;
           
            if (lastElement.compareTo(BinaryHeap[child]) >= 0) 
                break;
            
            BinaryHeap[currentNode] = BinaryHeap[child];
            
            currentNode = child;
            child *= 2;
        }
        BinaryHeap[currentNode] = lastElement;
       
        return maxElement;
    }
    
}

   
