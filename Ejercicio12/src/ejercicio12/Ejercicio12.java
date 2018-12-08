/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 *
 * @author COMPAQ
 */
public class Ejercicio12 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //TANTO ESTE COMO EL EJERCICIO12FINAL FUNCIONAN BIEN
  BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
        String s=null,operacion=null,nombreVariable=null;
        StringTokenizer st;

        Arboles arbol=new Arboles();
        while((s=sc.readLine())!=null){
            st=new StringTokenizer(s);
            operacion=st.nextToken();
            if(operacion.equals("CREATE")){
                arbol.add(st.nextToken(), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()));
            }else if(operacion.equals("READ")){
                nombreVariable=st.nextToken();
                if(nombreVariable.equals("ID")){
                    System.out.println(arbol.ReadbyId(st.nextToken()));
                     // System.out.println(arbol.arreglo[0].root.maxheap.peek().getDocumento());
                }else if(nombreVariable.equals("NOMBRE")){
                    System.out.println(arbol.ReadbyNombre(st.nextToken()));
                }else if(nombreVariable.equals("APELLIDO")){
                    System.out.println(arbol.ReadbyApellido(st.nextToken()));
                }else if(nombreVariable.equals("EDAD")){
                    System.out.println(arbol.ReadbyEdad(Integer.parseInt(st.nextToken())));
                }else{
                    System.out.println(arbol.ReadbyPapa(Double.parseDouble(st.nextToken())));
                }
            }else if(operacion.equals("UPDATE")){
                String id=st.nextToken();
                nombreVariable=st.nextToken();
                if(nombreVariable.equals("NOMBRE")){
                   arbol.UpdateNombre(id, st.nextToken());
                       
                   
               
                }else if(nombreVariable.equals("APELLIDO")){
                    arbol.UpdateApellido(id, st.nextToken());
                }else if(nombreVariable.equals("EDAD")){
                    arbol.UpdateEdad(id, Integer.parseInt(st.nextToken()));
                }else {
                    arbol.UpdatePapa(id, Double.parseDouble(st.nextToken()));
                }
            }else if(operacion.equals("DELETE")){
               arbol.remove(st.nextToken());
            }
        }
        
    }
    
}
class Arboles{
    MapTree<String,Estudiante> arbolId;
    MapTree<String,PriorityQueue<Estudiante>>arbolNombre;
    MapTree<String, PriorityQueue<Estudiante>>arbolApellido;
    MapTree<Integer, PriorityQueue<Estudiante>>arboledad;
    MapTree<Double, PriorityQueue<Estudiante>>arbolpapa;

    public Arboles() {
    this.arbolId=new MapTree<>();
    this.arbolNombre=new MapTree<>();
    this.arbolApellido=new MapTree<>();
    this.arboledad=new MapTree<>();
    this.arbolpapa=new MapTree<>();
    }
    public void add(String id,String nombre,String apellido,int edad, double papa){
        Estudiante a=new Estudiante(id, nombre, apellido, edad, papa);
        arbolId.put(a.getDocumento(), a);
        PriorityQueue<Estudiante>c;
        c=arbolNombre.get(a.getNombre());
        if(c==null){
            PriorityQueue<Estudiante>cola=new PriorityQueue<>();
            cola.add(a);
            arbolNombre.put(a.getNombre(), cola);
        }else{
            c.add(a);
        }
        c=arbolApellido.get(a.getApellido());
        if(c==null){
            PriorityQueue<Estudiante>cola=new PriorityQueue<>();
            cola.add(a);
            arbolApellido.put(a.getApellido(), cola);
        }else{
            c.add(a);
        }
        c=arboledad.get(edad);
        if(c==null){
            PriorityQueue<Estudiante>cola=new PriorityQueue<>();
            cola.add(a);
            arboledad.put(a.getEdad(), cola);
        }else{
            c.add(a);
        }
        c=arbolpapa.get(papa);
        if(c==null){
               PriorityQueue<Estudiante>cola=new PriorityQueue<>();
            cola.add(a);
            arbolpapa.put(a.getPapa(), cola);
        }else{
            c.add(a);
        }
        
    }
    
    public void remove(String id){
       Estudiante estudiante=this.arbolId.get(id);
       if(estudiante==null)return;
       this.arbolId.remove(id);
       PriorityQueue<Estudiante>nodo=this.arbolNombre.get(estudiante.getNombre());
       nodo.remove(estudiante);
       if(nodo.isEmpty())this.arbolNombre.remove(estudiante.getNombre());
       nodo=this.arbolApellido.get(estudiante.getApellido());
       nodo.remove(estudiante);
       if(nodo.isEmpty())this.arbolApellido.remove(estudiante.getApellido());
       nodo=this.arboledad.get(estudiante.getEdad());
       nodo.remove(estudiante);
       if(nodo.isEmpty())this.arboledad.remove(estudiante.getEdad());
       nodo=this.arbolpapa.get(estudiante.getPapa());
       nodo.remove(estudiante);
       if(nodo.isEmpty())this.arbolpapa.remove(estudiante.getPapa());
       
        
    }
    public void UpdateNombre(String id,String nombre){
       Estudiante estudiante=this.arbolId.get(id);
       if(estudiante==null)return;
        remove(id);
        add(estudiante.getDocumento(), nombre, estudiante.getApellido(), estudiante.getEdad(), estudiante.getPapa());
    }
    public void UpdateApellido(String id, String apellido){
        Estudiante estudiante=this.arbolId.get(id);
        if(estudiante==null)return;
        remove(id);
        add(id, estudiante.getNombre(), apellido, estudiante.getEdad(),estudiante.getPapa());
    }
    public void UpdateEdad(String id, int edad){
        Estudiante estudiante=this.arbolId.get(id);
         if(estudiante==null)return;
        remove(id);
        add(id, estudiante.getNombre(), estudiante.getApellido(), edad,estudiante.getPapa());
    }
    
    public void UpdatePapa(String id,double papa){
        Estudiante estudiante=this.arbolId.get(id);
        if(estudiante==null)return;
        remove(id);
        add(estudiante.getDocumento(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getEdad(), papa);
    }
    public String ReadbyId(String Id){
        Estudiante estudiante=this.arbolId.get(Id);
        if(estudiante==null)return "no existe estudiante con ID "+Id;
        return estudiante.getDocumento()+" "+estudiante.getNombre()+" "+estudiante.getApellido()+" "+estudiante.getEdad()+" "+estudiante.getPapa();
                
    }
        public String ReadbyNombre(String Nombre){
        PriorityQueue<Estudiante>cola=this.arbolNombre.get(Nombre);
        if(cola==null)return "no existe estudiante con NOMBRE "+Nombre;
        Estudiante estudiante =cola.peek();
        return estudiante.getDocumento()+" "+estudiante.getNombre()+" "+estudiante.getApellido()+" "+estudiante.getEdad()+" "+estudiante.getPapa();
                
    }
               public String ReadbyApellido(String Apellido){
        PriorityQueue<Estudiante>cola=this.arbolApellido.get(Apellido);
        if(cola==null)return "no existe estudiante con APELLIDO "+Apellido;
        Estudiante estudiante =cola.peek();
        return estudiante.getDocumento()+" "+estudiante.getNombre()+" "+estudiante.getApellido()+" "+estudiante.getEdad()+" "+estudiante.getPapa();
                
    }
                      public String ReadbyEdad(int edad){
        PriorityQueue<Estudiante>cola=this.arboledad.get(edad);
        if(cola==null)return "no existe estudiante con EDAD "+edad;
        Estudiante estudiante =cola.peek();
        return estudiante.getDocumento()+" "+estudiante.getNombre()+" "+estudiante.getApellido()+" "+estudiante.getEdad()+" "+estudiante.getPapa();
                
    }
                             public String ReadbyPapa(double papa){
        PriorityQueue<Estudiante>cola=this.arbolpapa.get(papa);
        if(cola==null)return "no existe estudiante con PAPA "+papa;
        Estudiante estudiante =cola.peek();
        return estudiante.getDocumento()+" "+estudiante.getNombre()+" "+estudiante.getApellido()+" "+estudiante.getEdad()+" "+estudiante.getPapa();
                
    }
}
class Estudiante implements Comparable<Estudiante>{
    String documento;
    String Nombre;
    String Apellido;
    int edad;
    double papa;

    public Estudiante(String documento, String Nombre, String Apellido, int edad, double papa) {
        this.documento = documento;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.edad = edad;
        this.papa = papa;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public int getEdad() {
        return edad;
    }

    public double getPapa() {
        return papa;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPapa(double papa) {
        this.papa = papa;
    }

    @Override
    public int compareTo(Estudiante o) {
      if(!this.Apellido.equals(o.getApellido()))return this.Apellido.compareTo(o.getApellido());
      if(!this.Nombre.equals(o.getNombre()))return this.Nombre.compareTo(o.getNombre());
      if(this.papa!=o.getPapa()){
          if(this.papa>o.papa)return -1;//pues priorityqueue es un min heap asi le damos prioridad al que tenga mayor papa;
           return 1;
      } 
      return this.documento.compareTo(o.getDocumento());
      
    }
    
}
        
interface MapSorted<K extends Comparable<?super K>,E>{
    boolean isEmpty();
    int size();
    boolean containsKey(K key);
    E get(K key);
    void put(K key, E x);
   void remove(K key);
}
class MapNode<K,E>{
    K key;
    E element;
    MapNode<K,E>leftChild,rightChild;
    int height;

    public MapNode(K key, E element) {
        this.key = key;
        this.element = element;
        this.height=1;
    }

    public MapNode() {
        this(null,null);
    }

    public void setKey(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public E getElement() {
        return element;
    }

    public MapNode<K, E> getLeftChild() {
        return leftChild;
    }

    public MapNode<K, E> getRightChild() {
        return rightChild;
    }

    public int getHeight() {
        return height;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public void setLeftChild(MapNode<K, E> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(MapNode<K, E> rightChild) {
        this.rightChild = rightChild;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
}
class MapTree<K extends Comparable<? super K >, E >implements MapSorted<K, E>{
private MapNode<K,E>root;
private int size;

    public MapTree() {
        this.root=null;
        size=0;
                
    }
    @Override
    public boolean isEmpty() {
       return root==null;
    }

    @Override
    public int size() {
           return size;
    }

    @Override
    public boolean containsKey(K key) {
     MapNode<K,E>nodo=root;
        while(nodo!=null){
         int comparation=key.compareTo(nodo.getKey());
         if(comparation==0){
             return true;
         }else if(comparation<0){
             nodo=nodo.getLeftChild();
         }else{
             nodo=nodo.getRightChild();
         }
     }
        return false;
    }

    @Override
    public E get(K key) {
         MapNode<K,E>nodo=root;
        while(nodo!=null){
         int comparation=key.compareTo(nodo.getKey());
         if(comparation==0){
             return nodo.getElement();
         }else if(comparation<0){
             nodo=nodo.getLeftChild();
         }else{
             nodo=nodo.getRightChild();
         }
     }
        return null;
    }

    @Override
    public void put(K key, E x) {
      root=add(root,key,x);
    }
private MapNode<K,E> add(MapNode<K,E>nodo,K key, E x){
      if(nodo==null){
        size++;
        return new MapNode<K,E>(key, x);
    }
    int comparation=key.compareTo(nodo.getKey());
    if(comparation<0){
        nodo.setLeftChild(add(nodo.getLeftChild(),key, x));
    }else if(comparation>0){
        nodo.setRightChild(add(nodo.getRightChild(),key,x));
    }else{
        nodo.setElement(x);
    }
    return balance(nodo);
}
    @Override
    public void remove(K key) {
        root=remove(root,key);
    }
        private MapNode<K,E>remove(MapNode<K,E>node,K key){
        if(node==null)return null;
        int comparation=key.compareTo(node.getKey());
        if(comparation<0)
            node.setLeftChild(remove(node.getLeftChild(), key));
        else if(comparation>0)
            node.setRightChild(remove(node.getRightChild(), key));
        else{
            if(node.getLeftChild()!=null&&node.getRightChild()!=null){
                MapNode<K,E>a=getMin(node.getRightChild());
                node.setElement(a.getElement());
                node.setKey(a.getKey());
                node.setRightChild(remove(node.getRightChild(), node.getKey()));
            }else{
                node=(node.getLeftChild()!=null)?node.getLeftChild():node.getRightChild();
               
            }
            size--;
        }
        return balance(node);
    }
            
    public MapNode<K,E> getMin() {
        return getMin(root);
    }
    private MapNode<K,E> getMin(MapNode<K,E> nodoInicial){
        MapNode<K,E>nodo=nodoInicial;
        MapNode<K,E> minElement=null;
        while(nodo!=null){
            minElement=nodo;
            nodo=nodo.getLeftChild();
        }
        return minElement;
                
                
    }

    
    public E getMax() {
        return getMax(root);
    }
      private E getMax(MapNode<K,E>nodoInicial){
          MapNode<K,E>nodo=nodoInicial;
          E maxElement=null;
          while(nodo!=null){
              maxElement=nodo.getElement();
              nodo=nodo.getRightChild();
          }
          return maxElement;
      }
    private MapNode<K,E>balance(MapNode<K,E>nodo){
    if(nodo==null)return nodo;
if(height(nodo.getLeftChild())-height(nodo.getRightChild())>1)
    if(height(nodo.getLeftChild().getLeftChild())>=height(nodo.getLeftChild().getRightChild()))
        nodo=rotateWithLeftChild(nodo);
    else nodo=doubleWithLeftChild(nodo);
else if(height(nodo.getRightChild())-height(nodo.getLeftChild())>1)
    if(height(nodo.getRightChild().getRightChild())>=height(nodo.getRightChild().getLeftChild()))
        nodo=rotateWithRightChild(nodo);
    else nodo=doubleWithRightChild(nodo);
nodo.setHeight(Math.max(height(nodo.getLeftChild()), height(nodo.getRightChild()))+1);
return nodo;

}
    private int height(MapNode<K,E>nodo){
    return nodo==null?0:nodo.getHeight();//si el nodo es nulo su altura es cero
    
}
public int height(){
    return height(root);
}
private MapNode<K,E>rotateWithLeftChild(MapNode<K,E>b){//rota cuando el arbol tiene mas altura en su brazo izquierdo(rotacion simple)
    MapNode<K,E> a=b.getLeftChild();
    b.setLeftChild(a.getRightChild());
    a.setRightChild(b); 
    b.setHeight(Math.max(height(b.getLeftChild()),height(b.getRightChild()))+1);//primero se arregla la altura de b pues como b es hijo de a la altura de a depende de la altura de b
    a.setHeight(Math.max(height(a.getLeftChild()), b.getHeight())+1);
    return a;
}
private MapNode<K,E>rotateWithRightChild(MapNode<K,E> a){
    MapNode<K,E>b=a.getRightChild();
    a.setRightChild(b.getLeftChild());
    b.setLeftChild(a);
    a.setHeight(Math.max(height(a.getLeftChild()), height(a.getRightChild()))+1);
    b.setHeight(Math.max(height(b.getRightChild()), a.getHeight())+1);
    return b;
}
        private MapNode<K,E> doubleWithLeftChild(MapNode<K,E>a){
            a.setLeftChild(rotateWithRightChild(a.getLeftChild()));
            return rotateWithLeftChild(a);
        }
        private MapNode<K,E>doubleWithRightChild(MapNode<K,E>a){
            a.setRightChild(rotateWithLeftChild(a.getRightChild()));
            return rotateWithRightChild(a);
        }
}