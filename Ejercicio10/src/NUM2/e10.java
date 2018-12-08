/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NUM2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author COMPAQ
 */
public class e10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
      /*  
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        int[] arreglo;
        int n = 0, f_1 = 0, a = 0, b = 0;
        String nums[];
        SegmentTree arbol = null;

        while ((line = sc.readLine()) != null) {
            if (line.equals("AVENIDA")) {
                n = Integer.parseInt(sc.readLine());

                arreglo = new int[n + 1];
                line = sc.readLine();
                nums = line.split(" ");

                f_1 = Integer.parseInt(nums[0]);
                a = Integer.parseInt(nums[1]);
                b = Integer.parseInt(nums[2]);
                arreglo[1] = f_1;
                for (int i = 2; i < n + 1; i++) {
                    arreglo[i] = ((a * arreglo[i - 1]) + b) % 1000000;
                }
                arbol = new SegmentTree();

                arbol.Initialize(arreglo, LongitudDelArreglo(n));

            } else if (line.matches("MARCHA(.*)")) {
                nums = line.split(" ");

                int i = Integer.parseInt(nums[1]);
                int j = Integer.parseInt(nums[2]);
                if (i > j) {
                    System.out.println(arbol.Consulta(j, i));
                } else {
                    System.out.println(arbol.Consulta(i, j));
                }

            } else if (line.matches("ALCALDE(.*)")) {
                nums = line.split(" ");

                //arbol.Update(Integer.parseInt(nums[1]),Integer.parseInt(nums[2]));
                int delta = Integer.parseInt(nums[2]) - arbol.arregloactualizado[Integer.parseInt(nums[1])];
   
                arbol.addActualizacion(Integer.parseInt(nums[1]), delta);
           
            } else {
              
                break;
            }

        }*/
      BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
      String type=sc.readLine();
      String line="";
          int[] arreglo;
      StringTokenizer s;
      int p,q,delta;
      if (type.equals("AVENIDA")){
          int n=Integer.parseInt(sc.readLine());
          arreglo = new int[n + 1];
          s=new StringTokenizer(sc.readLine());
          int f_1=Integer.parseInt(s.nextToken());
          int a =Integer.parseInt(s.nextToken());
          int b=Integer.parseInt(s.nextToken());
          arreglo[1] = f_1;
                for (int i = 2; i < n + 1; i++) {
                    arreglo[i] = ((a * arreglo[i - 1]) + b) % 1000000;
                }
                
                SegmentTree arbol = new SegmentTree();

                arbol.Initialize(arreglo, LongitudDelArreglo(n));
         while(true){
             line=sc.readLine();
             if(line==null){
                 break;
             
             }
             s=new StringTokenizer(line);
             if(s.nextToken().equals("MARCHA")){
                 p=Integer.parseInt(s.nextToken());
                 q=Integer.parseInt(s.nextToken());
                 if (p > q) {
                    System.out.println(arbol.Consulta(q, p));
                } else {
                    System.out.println(arbol.Consulta(p, q));
                }
             }else{
                 int y=Integer.parseInt(s.nextToken());
                 delta=(arbol.arregloactualizado[y]*-1)+Integer.parseInt(s.nextToken());
                 arbol.addActualizacion(y, delta);
             
             }
         } 
      }
    
    }

    public static int LongitudDelArreglo(int numero) {
        double a = Math.ceil(Math.log(numero) / Math.log(2));

        return (int) Math.pow(2, a + 1) - 1;
    }

}

class Actualizacion {

    private int indice;
    private int delta;

    public Actualizacion(int indice, int delta) {
        this.indice = indice;
        this.delta = delta;
    }

    public int getIndice() {
        return indice;
    }

    public int getDelta() {
        return delta;
    }

}

class SegmentTree {
    int []arregloactualizado;
    int[] arreglo;
    private Nodo[] nodos;
    private ArrayList<Actualizacion> actualizaciones;
    private int ultimaactualizacion;

    public SegmentTree() {
        this.actualizaciones = new ArrayList<>();
        this.ultimaactualizacion = -1;
        this.sumatotal = (long) 0;
    }

    public void addActualizacion(int indice, int delta) {
        this.actualizaciones.add(new Actualizacion(indice, delta));
      
this.arregloactualizado[indice]+=delta;
        this.ultimaactualizacion++;
    }

    public String tosString() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < this.nodos.length; i++) {
            s.append("[" + this.nodos[i].inicio + "," + this.nodos[i].fin + "," + this.nodos[i].getSuma() + "," + this.nodos[i].getIndice() + "," + this.nodos[i].actualizacion + "]\n");
        }
        return s.toString();
    }

    public void Initialize(int[] arreglo, int longitudarbol) {
        this.arreglo = arreglo;
        this.arregloactualizado=new int[arreglo.length];
        for(int i=0;i<arreglo.length;i++){
            this.arregloactualizado[i]=arreglo[i];
        }
        this.nodos = new Nodo[longitudarbol + 1];
        int length = this.nodos.length;

        this.nodos[1] = new Nodo(1, (length) / 2);//pues si el numero no es potencia de 2 toca ampliar los intervaloshasta uno que sea potencia de 2

        LinkedQueue<Nodo> cola = new LinkedQueue<>();
        cola.put(nodos[1]);
        Nodo temporal = null;
        int i = 1;
        while (!cola.isEmpty() && i < this.nodos.length) {
            temporal = cola.remove();
            this.nodos[i] = temporal;

            cola.put(new Nodo(temporal.inicio, (temporal.inicio + temporal.fin) / 2));
            cola.put(new Nodo(((temporal.inicio + temporal.fin) / 2) + 1, temporal.fin));
            i++;
        }
//ahora debemos poner en las hojas los valores del arreglo

        int j = 1;
        for (int n = (this.nodos.length) / 2; n < this.nodos.length; n++, j++) {
            if (j < arreglo.length) {
                this.nodos[n].setIndice(j);
                this.nodos[n].setSuma((long) this.arreglo[j]);
            } else {
                this.nodos[n].setIndice(0);
                this.nodos[n].setSuma((long) 0);
            }

        }
        //vamos a realizar propagacion hacia arriba:
        int currentNode = (this.nodos.length - 1) / 2;
        int child = currentNode * 2;

        while (currentNode != 0 && this.arreglo[this.nodos[currentNode].getIndice()] <= this.arreglo[this.nodos[child].getIndice()]) {

            if (this.arreglo[this.nodos[child].getIndice()] < this.arreglo[this.nodos[child + 1].getIndice()]) {
                child++;
            }
            if (this.arreglo[this.nodos[currentNode].getIndice()] == this.arreglo[this.nodos[child].getIndice()]) {
                if (this.nodos[currentNode].getIndice() > this.nodos[child].getIndice()) {
                    this.nodos[currentNode].setIndice(this.nodos[child].getIndice());
                }
                this.nodos[currentNode].setSuma(this.nodos[currentNode * 2].getSuma() + this.nodos[(currentNode * 2) + 1].getSuma());
            } else {

                this.nodos[currentNode].setIndice(this.nodos[child].getIndice());
                this.nodos[currentNode].setSuma(this.nodos[currentNode * 2].getSuma() + this.nodos[(currentNode * 2) + 1].getSuma());
            }
            currentNode--;
            child = currentNode * 2;

        }

    }
    Long sumatotal;

    public Nodo Solve(int posicion, int i, int j) {

        int currentNode = posicion;
        if (this.nodos[currentNode].inicio == this.nodos[currentNode].fin) {
            //actualizacion hoja
            if (this.nodos[currentNode].actualizacion != this.ultimaactualizacion) {
                for (int n = this.nodos[currentNode].actualizacion + 1; n <= this.ultimaactualizacion; n++) {
                    if (this.nodos[currentNode].inicio == this.actualizaciones.get(n).getIndice()) {

                      //  Long valornuevodenodo = ((long) this.arreglo[this.actualizaciones.get(n).getIndice()]) + this.actualizaciones.get(n).getDelta();
 //Long valornuevodenodo = this.nodos[currentNode].getSuma() + this.actualizaciones.get(n).getDelta();
                        this.nodos[currentNode].setSuma(this.nodos[currentNode].getSuma() + this.actualizaciones.get(n).getDelta());
                       // this.arregloactualizado[this.actualizaciones.get(n).getIndice()]+=this.actualizaciones.get(n).getDelta();
                     
                        this.nodos[currentNode].actualizacion++;
                    }else{
                        this.nodos[currentNode].actualizacion++;
                    }
                }
            }
            /////
            this.sumatotal += this.nodos[currentNode].getSuma();
            return this.nodos[currentNode];
        } else {
         //Actualizacion
            if (this.nodos[currentNode].actualizacion != this.ultimaactualizacion) {
                for (int n = this.nodos[currentNode].actualizacion + 1; n <= this.ultimaactualizacion; n++) {
                    if (this.nodos[currentNode].inicio <= this.actualizaciones.get(n).getIndice()
                            && this.actualizaciones.get(n).getIndice() <= this.nodos[currentNode].fin) {

                        // this.nodos[currentNode].setSuma((long)this.arreglo[this.actualizaciones.get(n).getIndice()]+this.actualizaciones.get(n).getDelta());
                        this.nodos[currentNode].setSuma(this.nodos[currentNode].getSuma() + this.actualizaciones.get(n).getDelta());
                        
                        //this.arregloactualizado[this.actualizaciones.get(n).getIndice()]+=this.actualizaciones.get(n).getDelta();
                        //aqui cambiamos el indice si es necesario
                        if(this.arregloactualizado[this.nodos[currentNode].getIndice()]<=this.arregloactualizado[this.actualizaciones.get(n).getIndice()]){
                         if(this.arregloactualizado[this.nodos[currentNode].getIndice()]==this.arregloactualizado[this.actualizaciones.get(n).getIndice()]){
                             
                             this.nodos[currentNode].setIndice(this.actualizaciones.get(n).getIndice()<this.arregloactualizado[this.nodos[currentNode].getIndice()]?this.actualizaciones.get(n).getIndice():this.nodos[currentNode].getIndice());   
                                                     
                         }else{
                           this.nodos[currentNode].setIndice(this.actualizaciones.get(n).getIndice());   
                         }
                           
                           
                        }
                        this.nodos[currentNode].actualizacion++;
                    } else {
                        this.nodos[currentNode].actualizacion++;
                    }
                
                
                }

            }
            ///////////////////

            
            
            
            if (this.nodos[currentNode].inicio <= i && this.nodos[currentNode].fin >= j) {
                   

                if (this.nodos[currentNode].inicio == i && this.nodos[currentNode].fin == j) {

                    this.sumatotal += this.nodos[currentNode].getSuma();
                    return this.nodos[currentNode];
                } else if (this.nodos[currentNode * 2].inicio <= i && this.nodos[currentNode * 2].fin >= j) {
                    return Solve(currentNode * 2, i, j);
                } else if (this.nodos[(currentNode * 2) + 1].inicio <= i && this.nodos[(currentNode * 2) + 1].fin >= j) {
                    return Solve((currentNode * 2) + 1, i, j);
                } else {
                    int k = (i + j) / 2;
                    Nodo der = Solve(posicion, i, this.nodos[currentNode * 2].fin);
                    Nodo izq = Solve(posicion, this.nodos[currentNode * 2].fin + 1, j);

                    if (this.arregloactualizado[der.getIndice()] == this.arregloactualizado[izq.getIndice()]) {
                       
                        return der.getIndice() < izq.getIndice() ? der : izq;
                    }
                    if (this.arregloactualizado[der.getIndice()] > this.arregloactualizado[izq.getIndice()]) {
                       
                        return der;
                    }
                    return izq;

                }

            }
        }

        return null;
    }

    public String Consulta(int i, int j) {
        this.sumatotal = (long) 0;
        if (i == j) {
            return "" + i + " " + arregloactualizado[i];
        }
        return "" + Solve(1, i, j).getIndice() + " " + this.sumatotal;
    }

    public void Update(int i, int nuevovalor) {
        int currentNode = ((this.nodos.length) / 2) + i - 1;//restamos 1 pues los indices comienzan desde 1
        this.arreglo[i] = nuevovalor;
        this.nodos[currentNode].setSuma((long) nuevovalor);

        while (currentNode != 1) {

            if (this.arreglo[this.nodos[currentNode / 2].getIndice()] < this.arreglo[this.nodos[currentNode].getIndice()]) {
                this.nodos[currentNode / 2].setIndice(this.nodos[currentNode].getIndice());
            }
            this.nodos[currentNode / 2].setSuma(this.nodos[(currentNode / 2) * 2].getSuma() + this.nodos[((currentNode / 2) * 2) + 1].getSuma());
            currentNode /= 2;
        }
    }
}

class Nodo {

    private int indice;
    int inicio, fin;
    int actualizacion;
    private Long suma;

    public Nodo(int inicio, int fin) {

        this.inicio = inicio;
        this.fin = fin;
        this.actualizacion = -1;

    }

    public Nodo() {
        this.indice = 0;
        this.suma = new Long(0);
        this.inicio = 0;
        this.fin = 0;
        this.actualizacion = -1;
    }

    public int getIndice() {
        return indice;
    }

    public Long getSuma() {
        return suma;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void setSuma(Long suma) {
        this.suma = suma;
    }

}

interface Queue<T> {

    boolean isEmpty();

    T getFrontElement();

    void put(T theObject);

    T remove();
}

class ChainNode<T> {

    T element;
    ChainNode<T> next;

    public ChainNode(T element, ChainNode<T> next) {
        this.element = element;
        this.next = next;
    }

    public ChainNode(T element) {
        this(element, null);
    }

    public ChainNode() {
        this(null, null);
    }

}

class LinkedQueue<T> implements Queue<T> {

    ChainNode<T> front;
    ChainNode<T> back;

    public LinkedQueue() {
        front = back = null;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public T getFrontElement() {
        return isEmpty() ? null : front.element;
    }

    @Override
    public void put(T theObject) {
        ChainNode<T> p = new ChainNode<T>(theObject, null);
        if (front == null) {
            front = p;
        } else {
            back.next = p;

        }
        back = p;
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T frontElement = front.element;
        front = front.next;
        if (isEmpty()) {
            back = null;
        }
        return frontElement;

    }

}
