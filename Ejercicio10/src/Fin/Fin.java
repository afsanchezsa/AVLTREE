/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fin;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author COMPAQ
 */
public class Fin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
         BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        int[] arreglo;
        StringTokenizer s;
        int[] fila;
        ArrayList<int[]> matriz;
        int n = 0, f_1 = 0, a = 0, b = 0;
        String nums[];
        SegmentTree arbol = null;
        int comodin = 0;
        int n1 = 0;
        int i1,i2,j1,j2;
        line=sc.readLine();
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
             while ((line = sc.readLine()) != null){
                 if (line.matches("MARCHA(.*)")) {

                nums = line.split(" ");

                int i = Integer.parseInt(nums[1]);
                int j = Integer.parseInt(nums[2]);
                
              
                if (i > j) {
                    System.out.println(arbol.Consulta(j, i));
                } else {
                    System.out.println(arbol.Consulta(i, j));
                }

            } else if (line.matches("ALCALDE(.*)")) {
                //nums = line.split(" ");
                s=new StringTokenizer(line);
                s.nextToken();
                //arbol.Update(Integer.parseInt(nums[1]), Integer.parseInt(nums[2]));
arbol.Update(Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()));
            } else{
                break;
            }
             }
            }else if(line.equals("CIUDAD")){
                  line = sc.readLine();
                nums = line.split(" ");
                n1 = Integer.parseInt(nums[0]);
                int m = Integer.parseInt(nums[1]);
                    if (m == 1) {
                    arreglo = new int[n1 + 1];
                    line = sc.readLine();
                    nums = line.split(" ");
                    f_1 = Integer.parseInt(nums[0]);
                    a = Integer.parseInt(nums[1]);
                    b = Integer.parseInt(nums[2]);
                    arreglo[1] = f_1;
                    for (int i = 2; i < n1 + 1; i++) {
                        arreglo[i] = ((a * arreglo[i - 1]) + b) % 1000000;
                    }
                    arbol = new SegmentTree();
                    arbol.Initialize(arreglo, LongitudDelArreglo(n1));
                    while ((line = sc.readLine()) != null) {
                        if (line.matches("MARCHA(.*)")) {

                            nums = line.split(" ");

                            int i = Integer.parseInt(nums[1]);
                            int j = Integer.parseInt(nums[3]);
                            arbol.sumatotal=(long)0;
                            if (i > j) {
                                System.out.println( arbol.Solve(1, j, i).getIndice() + " 1 " + arbol.sumatotal);
                            } else {
                                System.out.println(arbol.Solve(1, i, j).getIndice() + " 1 " + arbol.sumatotal);
                            }

                        }else if (line.matches("ALCALDE(.*)")) {
                nums = line.split(" ");
                arbol.Update(Integer.parseInt(nums[1]), Integer.parseInt(nums[3]));

            }else{
                            break;   
                        }
                    }
                    
                }else if(n1<10){
                    line = sc.readLine();
                nums = line.split(" ");
                f_1 = Integer.parseInt(nums[0]);
                a = Integer.parseInt(nums[1]);
                b = Integer.parseInt(nums[2]);
                fila = new int[m + 1];
                matriz = new ArrayList<>();//pues no vamos a manejar indices cero
                fila[1] = f_1;

                for (int j = 2; j < m + 1; j++) {
                    fila[j] = ((a * fila[j - 1]) + b) % 1000000;
                }
                matriz.add(null);
                matriz.add(1, fila);
                for (int i = 2; i < n1 + 1; i++) {
                    fila = new int[m + 1];
                    for (int j = 1; j < m + 1; j++) {
                        if (j == 1) {
                            fila[j] = ((a * matriz.get(i - 1)[m]) + b) % 1000000;
                            comodin = fila[j];

                        } else {
                            fila[j] = ((a * comodin) + b) % 1000000;
                            comodin = fila[j];

                        }
                    }
                    matriz.add(i, fila);
                }

                //ImprimirMatriz(matriz, n1, m);
                Ciudad ciudad = new Ciudad(LongitudDelArreglo(n1));
                ciudad.Initialize(matriz, LongitudDelArreglo(m));
  while ((line = sc.readLine()) != null) {
                    if (line.matches("MARCHA(.*)")) {
                       /* s = new StringTokenizer(line);
                        s.nextToken();
                        int i1 = Integer.parseInt(s.nextToken());
                        int j1 = Integer.parseInt(s.nextToken());
                        int i2 = Integer.parseInt(s.nextToken());
                        int j2 = Integer.parseInt(s.nextToken());
                        */
                       nums=line.split(" ");
                         i1 = Integer.parseInt(nums[1]);
                         j1 = Integer.parseInt(nums[2]);
                         i2 = Integer.parseInt(nums[3]);
                         j2 = Integer.parseInt(nums[4]);
                       
                        int auxiliar = 0;
                        if (i1 > i2) {
                            auxiliar = i1;
                            i1 = i2;
                            i2 = auxiliar;
                        }
                        if (j1 > j2) {
                            auxiliar = j1;
                            j1 = j2;
                            j2 = auxiliar;
                        }

                        System.out.println(ciudad.Consulta(i1, j1, i2, j2));

                    } else if (line.matches("ALCALDE(.*)")) {
                        s = new StringTokenizer(line);
                        s.nextToken();

                        ciudad.Update(Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()));

                    } else {

                        break;
                    }

                }
                }
                    
                    
                    else{
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
    
    public static void ImprimirMatriz(ArrayList<int[]> ar, int n, int m) {
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                System.out.print(ar.get(i)[j] + " ");
            }
            System.out.println("");
        }
    }

    public static int LongitudDelArreglo(int numero) {
        double a = Math.ceil(Math.log(numero) / Math.log(2));

        return (int) Math.pow(2, a + 1) - 1;
    }
    
}

class SegmentTree implements Comparable<SegmentTree> {

    private int[] arreglo;
    private Nodo[] nodos;
    int x1;
    int x2;
    int maximo;//va a almacenar el indice valor mayor del arbol de segmentos(carrera) 
    int valormaximo;
    int indice;//el indice sera el numero de la fila a la que pertenece el segment tree
    //private int size;

    public Long sumaTotal() {
        if (this.arreglo == null) {
            return this.sumatotal;
        }
        return this.nodos[1].getSuma();
    }

    public int[] getArreglo() {
        return this.arreglo;
    }

    public int Maximo() {//retorna el indice del mayor elementos del arbol
        if (this.nodos == null) {
            return maximo;
        }
        return this.nodos[1].getIndice();
    }

    public int ValorMaximo() {//retorna el maximo elemento del arbol;
        if (this.arreglo == null) {
            return this.valormaximo;
        }
        return this.arreglo[this.nodos[1].getIndice()];
    }

    public SegmentTree() {
        //this.size = 0;
        this.sumatotal = (long) 0;
    }

    public String tosString() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < this.nodos.length; i++) {
            s.append("[" + this.nodos[i].inicio + "," + this.nodos[i].fin + "," + this.nodos[i].getSuma() + "," + this.nodos[i].getIndice() + "]\n");
        }
        return s.toString();
    }

    public void Initialize(int[] arreglo, int longitudarbol) {
        this.arreglo = arreglo;

        this.nodos = new Nodo[longitudarbol + 1];
        int length = this.nodos.length;

        this.nodos[1] = new Nodo(1, (length) / 2);//pues si el numero no es potencia de 2 toca ampliar los intervaloshasta uno que sea potencia de 2
        // size++;
        LinkedQueue<Nodo> cola = new LinkedQueue<>();
        cola.put(nodos[1]);
        Nodo temporal = null;
        int i = 1;
        while (!cola.isEmpty() && i < this.nodos.length) {
            temporal = cola.remove();
            this.nodos[i] = temporal;

            //   size++;
            cola.put(new Nodo(temporal.inicio, (temporal.inicio + temporal.fin) / 2));
            cola.put(new Nodo(((temporal.inicio + temporal.fin) / 2) + 1, temporal.fin));
            i++;
        }
//ahora debemos poner en las hojas los valores del arreglo
        // size--;

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
        this.maximo = this.nodos[1].getIndice();
    }
    Long sumatotal;

    public Nodo Solve(int posicion, int i, int j) {
        int currentNode = posicion;

        if (this.nodos[currentNode].inicio == this.nodos[currentNode].fin) {

            this.sumatotal += this.nodos[currentNode].getSuma();
            return this.nodos[currentNode];
        } else {
            if (this.nodos[currentNode].inicio <= i && this.nodos[currentNode].fin >= j) {
                if (this.nodos[currentNode].inicio == i && this.nodos[currentNode].fin == j) {
                    this.sumatotal += this.nodos[currentNode].getSuma();
                    return this.nodos[currentNode];
                } else if (this.nodos[currentNode * 2].inicio <= i && this.nodos[currentNode * 2].fin >= j) {
                    return Solve(currentNode * 2, i, j);
                } else if (this.nodos[(currentNode * 2) + 1].inicio <= i && this.nodos[(currentNode * 2) + 1].fin >= j) {
                    return Solve((currentNode * 2) + 1, i, j);
                } else {
                    // int k=(i+j)/2;
                    //  Nodo der= Solve(posicion, i, k);
                    //Nodo izq=Solve(posicion, k+1, j);
                    Nodo izq = Solve(posicion, i, this.nodos[currentNode * 2].fin);
                    Nodo der = Solve(posicion, this.nodos[currentNode * 2].fin + 1, j);
                    if (this.arreglo[der.getIndice()] == this.arreglo[izq.getIndice()]) {
                        return der.getIndice() < izq.getIndice() ? der : izq;
                    }
                    if (this.arreglo[der.getIndice()] > this.arreglo[izq.getIndice()]) {
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
            return "" + i + " " + arreglo[i];
        }
        return "" + Solve(1, i, j).getIndice() + " " + this.sumatotal;
    }

    public void Update(int i, int nuevovalor) {
        int currentNode = ((this.nodos.length) / 2) + i - 1;//restamos 1 pues los indices comienzan desde 1
        this.arreglo[i] = nuevovalor;
        this.nodos[currentNode].setSuma((long) nuevovalor);

        while (currentNode != 1) {

            if (this.arreglo[this.nodos[(currentNode / 2) * 2].getIndice()] != this.arreglo[this.nodos[((currentNode / 2) * 2) + 1].getIndice()]) {
                if (this.arreglo[this.nodos[(currentNode / 2) * 2].getIndice()] > this.arreglo[this.nodos[((currentNode / 2) * 2) + 1].getIndice()]) {
                    this.nodos[currentNode / 2].setIndice(this.nodos[(currentNode / 2) * 2].getIndice());
                } else {

                    this.nodos[currentNode / 2].setIndice(this.nodos[((currentNode / 2) * 2) + 1].getIndice());
                }

            } else {
                this.nodos[currentNode / 2].setIndice(this.nodos[(currentNode / 2) * 2].getIndice() < this.nodos[((currentNode / 2) * 2) + 1].getIndice()
                        ? this.nodos[(currentNode / 2) * 2].getIndice() : this.nodos[((currentNode / 2) * 2) + 1].getIndice());
            }

            this.nodos[currentNode / 2].setSuma(this.nodos[(currentNode / 2) * 2].getSuma() + this.nodos[((currentNode / 2) * 2) + 1].getSuma());
            currentNode /= 2;
        }
        this.maximo = this.nodos[1].getIndice();
    }

    @Override
    public int compareTo(SegmentTree o) {
        if (o == null) {
            return 1;
        }
        if (this.ValorMaximo() != o.ValorMaximo()) {
            return (this.ValorMaximo() - o.ValorMaximo());
        }
        if (this.indice != o.indice) {
            return (this.indice - o.indice) * -1;
        }
        return (this.Maximo() - o.Maximo()) * -1;
        /*if(this.Maximo()!=o.Maximo())return (this.Maximo()-o.Maximo())*-1;
        return (this.indice-o.indice)*-1;
         */

    }

    public void setIntervalo(int x1, int x2) {
        this.x1 = x1;
        this.x2 = x2;

    }
}

class Nodo {

    private int indice;
    int inicio, fin;
    private Long suma;
    int calle;

    public Nodo(int inicio, int fin) {

        this.inicio = inicio;
        this.fin = fin;

    }

    public Nodo() {
        this.indice = 0;
        this.suma = new Long(0);
        this.inicio = 0;
        this.fin = 0;
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

class Ciudad {

    StringBuilder salida;
    private SegmentTree[] arboles;
    private ArrayList<int[]> matriz;
    Long sumatotal;

    public Ciudad(int n) {

        this.arboles = new SegmentTree[n + 1];
        this.salida = new StringBuilder();
    }

    public void Initialize(ArrayList<int[]> ar, int longitudarboles) {
        this.matriz = ar;
        int j = 1;
        //ponemos las hojas

        for (int i = (this.arboles.length / 2); i < this.arboles.length; i++, j++) {
            if (j < ar.size()) {
                this.arboles[i] = new SegmentTree();
                this.arboles[i].Initialize(ar.get(j), longitudarboles);
                this.arboles[i].x1 = this.arboles[i].x2 = this.arboles[i].indice = j;

            } else {
                this.arboles[i] = new SegmentTree();
                this.arboles[i].x1 = this.arboles[i].x2 = this.arboles[i].indice = j;

            }

        }
        //propagamos hacia arriba
        int currentNode = (this.arboles.length - 1) / 2;

        while (currentNode != 0) {
            this.arboles[currentNode] = new SegmentTree();
            this.arboles[currentNode].x1 = this.arboles[currentNode * 2].x1;
            this.arboles[currentNode].x2 = this.arboles[(currentNode * 2) + 1].x2;
            this.arboles[currentNode].sumatotal = this.arboles[currentNode * 2].sumaTotal() + this.arboles[(currentNode * 2) + 1].sumaTotal();
            if (this.arboles[currentNode * 2].compareTo(this.arboles[(currentNode * 2) + 1]) > 0/*this.arboles[currentNode*2].ValorMaximo()>=this.arboles[(currentNode*2)+1].ValorMaximo()*/) {
                this.arboles[currentNode].indice = this.arboles[currentNode * 2].indice;
                this.arboles[currentNode].maximo = this.arboles[currentNode * 2].Maximo();
                this.arboles[currentNode].valormaximo = this.arboles[currentNode * 2].ValorMaximo();

            } else {
                this.arboles[currentNode].indice = this.arboles[(currentNode * 2) + 1].indice;
                this.arboles[currentNode].maximo = this.arboles[(currentNode * 2) + 1].Maximo();
                this.arboles[currentNode].valormaximo = this.arboles[currentNode * 2].ValorMaximo();

            }
            currentNode--;
        }

    }

    public void Update(int i, int j, int x) {
        int currentNode = (this.arboles.length / 2) + i - 1;

        this.arboles[currentNode].Update(j, x);

        //ahora debemos propagar hacia arriba:
        this.matriz.get(i)[j] = x;
        while (currentNode != 1) {

            /* if(currentNode%2==0&&this.arboles[currentNode].compareTo(this.arboles[currentNode+1])<0){
              currentNode++;
          }else if(currentNode%2!=0&&this.arboles[currentNode].compareTo(this.arboles[currentNode-1])<0){
              currentNode--;
          }else{
              
          }
            // if(this.arboles[(currentNode/2)*2].compareTo(this.arboles[((currentNode/2)*2)+1])<0){//si el hijo izquierdo es menor que el derecho entonces debe comparar con el izquierdo(para arreglar su valor de maximo)
            //if(currentNode%2==0)//solamente pasamos al hijo derecho si currentNode es un hijo izquierdo es decir si currentNode es par(si currentnode es par es  un hijo izquierdo)   
            //currentNode++;
                }
              

           this.arboles[currentNode/2].indice=this.arboles[currentNode].indice;
              
           this.arboles[currentNode/2].maximo=this.arboles[currentNode].Maximo();
               this.arboles[currentNode/2].sumatotal=this.arboles[(currentNode/2)*2].sumaTotal()+this.arboles[((currentNode/2)*2)+1].sumaTotal();
          this.arboles[currentNode/2].valormaximo=this.arboles[currentNode].ValorMaximo();
            
            currentNode/=2;
       
             */
            if (this.arboles[(currentNode / 2) * 2].compareTo(this.arboles[((currentNode / 2) * 2) + 1]) > 0) {
                this.arboles[currentNode / 2].indice = this.arboles[(currentNode / 2) * 2].indice;
                this.arboles[currentNode / 2].maximo = this.arboles[(currentNode / 2) * 2].Maximo();
                this.arboles[currentNode / 2].sumatotal = this.arboles[(currentNode / 2) * 2].sumaTotal() + this.arboles[((currentNode / 2) * 2) + 1].sumaTotal();
                this.arboles[currentNode / 2].valormaximo = this.arboles[(currentNode / 2) * 2].ValorMaximo();
            } else {
                this.arboles[currentNode / 2].indice = this.arboles[((currentNode / 2) * 2) + 1].indice;
                this.arboles[currentNode / 2].maximo = this.arboles[((currentNode / 2) * 2) + 1].Maximo();
                this.arboles[currentNode / 2].sumatotal = this.arboles[(currentNode / 2) * 2].sumaTotal() + this.arboles[((currentNode / 2) * 2) + 1].sumaTotal();
                this.arboles[currentNode / 2].valormaximo = this.arboles[((currentNode / 2) * 2) + 1].ValorMaximo();
            }
            currentNode /= 2;
        }
    }

    public String toString() {
        StringBuilder a = new StringBuilder();

        for (int i = 1; i < this.arboles.length; i++) {
            a.append("[" + this.arboles[i].x1 + "," + this.arboles[i].x2 + "," + this.arboles[i].indice + "," + this.arboles[i].Maximo() + "]");
        }
        return a.toString();
    }

    public String Consulta(int i1, int j1, int i2, int j2) {
        this.sumatotal = (long) 0;
        SegmentTree a = this.Solve(1, i1, j1, i2, j2);
        return a.indice + " " + a.maximo + " " + this.sumatotal;
    }

    public SegmentTree Solve(int i, int i1, int j1, int i2, int j2) {
//if(this.arboles[i].getArreglo()==null) return this.arboles[i];
        if (j1 == 1 && j2 == this.matriz.get(1).length - 1 && this.arboles[i].x1 == i1 && this.arboles[i].x2 == i2) {
            this.sumatotal += this.arboles[i].sumaTotal();

            return this.arboles[i];

        } else if ((j1 != 1 || j2 != this.matriz.get(1).length - 1) && this.arboles[i].x1 == i1 && this.arboles[i].x2 == i2 && i1 == i2) {

            SegmentTree r = new SegmentTree();
            SegmentTree arbol;

            arbol = this.arboles[i];
            arbol.sumatotal = (long) 0;
            Nodo a = this.arboles[i].Solve(1, j1, j2);

            r.maximo = a.getIndice();
            r.x1 = r.x2 = j1;
            r.valormaximo = this.matriz.get(i1)[a.getIndice()];
            r.indice = i1;
            //r.sumatotal=a.getSuma();

            //no usamos sumatotal() pues eso es solo si consideramos el arbol completo(no sirve para arboles que se les consulta un intervalo )
            this.sumatotal += arbol.sumatotal;
            return r;
        }/*else if(this.arboles[i].x1==i1&&this.arboles[i].x2==i2&&j1==1&&j2==this.matriz.get(1).length-1){
 
           this.sumatotal+=this.arboles[i].sumaTotal();
           return arboles[i];
       }*/ else if (this.arboles[i].x1 <= i1 && this.arboles[i].x2 >= i2) {
            if (this.arboles[i * 2].x1 <= i1 && this.arboles[i * 2].x2 >= i2) {
                return Solve(i * 2, i1, j1, i2, j2);
            }
            if (this.arboles[(i * 2) + 1].x1 <= i1 && this.arboles[(i * 2) + 1].x2 >= i2) {
                return Solve((i * 2) + 1, i1, j1, i2, j2);
            }
            SegmentTree izq = Solve(i, i1, j1, this.arboles[i * 2].x2, j2);
            SegmentTree der = Solve(i, this.arboles[i * 2].x2 + 1, j1, i2, j2);

            return izq.compareTo(der) >= 0 ? izq : der;
        } else {

            return null;

        }

    }

}
