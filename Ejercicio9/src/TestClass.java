import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;

class TestClass {

    public static void main(String args[]) throws Exception {

        int[] procesos = new int[200001];

        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String line;
        MaxHeap arbol = new MaxHeap();
        int id;

        long llegada = 0,newPriority=0;
        while ((line = sc.readLine()) != null) {
            if (line.equals("TASK")) {
                arbol.add(new Proceso(Integer.parseInt(sc.readLine()), Long.parseLong(sc.readLine()), sc.readLine(), llegada), procesos);
                llegada++;
            } else if (line.equals("EXECUTE")) {

                if (!arbol.isEmpty()) {
                    System.out.println(arbol.removeMax(procesos).descripcion);
                } else {
                    System.out.println("TASK NOT FOUND");
                }

            } else if (line.equals("KILL")) {
                id = Integer.parseInt(sc.readLine());
                if (procesos[id] == 0) {
                    System.out.println("TASK NOT FOUND");
                } else {

                    arbol.Eliminar(procesos[id], procesos);
                    procesos[id]=0;
                    System.out.println("TASK KILLED");

                }

            } else if (line.equals("CHANGE")) {
                id = Integer.parseInt(sc.readLine());
                newPriority=Long.parseLong(sc.readLine());
                if (procesos[id] == 0) {
                    System.out.println("TASK NOT FOUND");
                } else {
                    arbol.ChangePriority(procesos[id],newPriority , procesos);
                    System.out.println("TASK RESCHEDULED");
                }

            } else if (line.equals("CLEAR")) {
                arbol.clear();
                procesos = new int[200001];
                llegada = 0;
                System.out.println("CLEARED");
            }

        }

    }

    public static void procesos(int[] procesos) {
        for (int i = 0; i < procesos.length; i++) {
            if (procesos[i] != 0) {
                System.out.println("el id: " + i + " esta en:" + procesos[i]);
            }
        }
    }

}

class Proceso implements Comparable<Proceso> {
    
    int id;
    long llegada;
    long priority;
    String descripcion;

    public Proceso(int id, long priority, String descripcion, long llegada) {
        this.id = id;
        this.priority = priority;
        this.descripcion = descripcion;
        this.llegada = llegada;
    }

    @Override
    public int compareTo(Proceso o) {

        if (this.priority > o.priority) {
            return 1;
        } else if (this.priority == o.priority && this.llegada <= o.llegada) {
            return 1;
        }
        return -1;

    }

}

interface PriorityQueue {

    boolean isEmpty();

    int size();

    void add(Proceso x, int[] procesos);

    Proceso getMax();

    Proceso removeMax(int[] procesos);
}

class MaxHeap implements PriorityQueue {

    Proceso[] BinaryHeap;
    private int size;

    public String toString() {

        String r = "Arbol: ";
        for (int i = 1; i < size + 1; i++) {
            r += BinaryHeap[i].priority + ",";
        }
        return r;
    }

    public MaxHeap(int initialCapacity) {
        // if(initialCapacity<1) throw new IllegalArgumentException("Initial Capacity must be >=1");
        BinaryHeap = new Proceso[initialCapacity + 1];
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

    public void clear() {
     
        size=0;
        BinaryHeap=new Proceso[11];

    }

    @Override
    public void add(Proceso x, int[] procesos) {
        if (size == BinaryHeap.length - 1) {
            Proceso[] old = BinaryHeap;
            BinaryHeap = new Proceso[2 * (size + 1)];
            System.arraycopy(old, 0, BinaryHeap, 0, size + 1);
        }
        int currentNode = ++size;

        while (currentNode != 1 && BinaryHeap[currentNode / 2].compareTo(x) < 0) {
            BinaryHeap[currentNode] = BinaryHeap[currentNode / 2];
            procesos[BinaryHeap[currentNode].id] = currentNode;
            currentNode /= 2;

        }

        BinaryHeap[currentNode] = x;
        procesos[BinaryHeap[currentNode].id] = currentNode;

    }

    @Override
    public Proceso getMax() {
        return (size == 0) ? null : BinaryHeap[1];
    }

    @Override
    public Proceso removeMax(int[] procesos) {
        if (size == 0) {
            return new Proceso(0, 0, "TASK NOT FOUND", 0);
        }
        Proceso maxElement = BinaryHeap[1];

        procesos[BinaryHeap[1].id] = 0;
        Proceso lastElement = BinaryHeap[size--];
        int currentNode = 1;

        int child = 2;
        while (child <= size) {
            if (child < size && BinaryHeap[child].compareTo(BinaryHeap[child + 1]) <0) 
                child++;
           
            if (lastElement.compareTo(BinaryHeap[child]) >= 0) 
                break;
            
            BinaryHeap[currentNode] = BinaryHeap[child];
            procesos[BinaryHeap[currentNode].id] = currentNode;
            currentNode = child;
            child *= 2;
        }
        BinaryHeap[currentNode] = lastElement;
        procesos[BinaryHeap[currentNode].id] = currentNode;
        return maxElement;
    }

    public void ChangePriority(int indice, long newPriority, int[] procesos) {
        if (BinaryHeap[indice].priority < newPriority) {
            PercolateUp(indice, newPriority, procesos);
        } else {
            PercolateDown(indice, newPriority, procesos);
        }

    }

    private void PercolateUp(int indice, long newPriority, int[] procesos) {
        int currentNode = indice;
        Proceso proc = BinaryHeap[indice];
        proc.priority = newPriority;
        while (currentNode != 1 && BinaryHeap[currentNode / 2].compareTo(proc) < 0) {
            BinaryHeap[currentNode] = BinaryHeap[currentNode / 2];
            procesos[BinaryHeap[currentNode / 2].id] = currentNode;
            currentNode /= 2;
        }
        BinaryHeap[currentNode] = proc;
        procesos[BinaryHeap[currentNode].id] = currentNode;

    }

    private void PercolateDown(int indice, long newPriority, int[] procesos) {
        int currentNode = indice;
        int child = currentNode * 2;
        Proceso proc = BinaryHeap[indice];
        proc.priority = newPriority;
        while (child <= size) {
            if (child < size && BinaryHeap[child].compareTo(BinaryHeap[child + 1]) < 0) {
                child++;
            }
            if (proc.compareTo(BinaryHeap[child]) >= 0) {
                break;
            }

            BinaryHeap[currentNode] = BinaryHeap[child];
            procesos[BinaryHeap[currentNode].id] = currentNode;
            currentNode = child;
            child *= 2;
        }
        BinaryHeap[currentNode] = proc;
        procesos[BinaryHeap[currentNode].id] = currentNode;

    }

    public void Eliminar(int indice, int[] procesos) {
        ChangePriority(indice, Long.parseLong("1000000000002"), procesos);
    
        removeMax(procesos);

    }

}
