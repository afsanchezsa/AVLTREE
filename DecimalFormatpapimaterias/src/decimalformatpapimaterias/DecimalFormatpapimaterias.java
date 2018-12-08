/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decimalformatpapimaterias;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author COMPAQ
 */
public class DecimalFormatpapimaterias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
               // TODO code application logic here
       

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Estudiante[] estudiantes = new Estudiante[n];
        String nombres[]=new String[n];
        int i = 0, creditos = 0;
        String notaString = "";
        double nota = 0.000000;
        while (i < n) {
            estudiantes[i] = new Estudiante(sc.next(), sc.nextInt());
            nombres[i]=estudiantes[i].getNombre();
            for (int j = 0; j < estudiantes[i].getNummaterias(); j++) {
                creditos = sc.nextInt();
                notaString = sc.next();
                
                if (!notaString.equals("CANCELADO")) {
                    nota =Double.parseDouble(notaString);
                    System.out.println(nota);
                    estudiantes[i].addMateria(new Materia(creditos, nota, false));
                } else {
                    estudiantes[i].addMateria(new Materia(creditos, 0, true));
                }

            }
            i++;

        }
        Estudiante temporal;
        Arrays.sort(nombres);
        
        for (int p = 0; p < estudiantes.length; p++) {
            for (int j = p + 1; j < estudiantes.length; j++) {
                System.out.println(estudiantes[p].getNombre()+estudiantes[p].getPappi());
               System.out.println(estudiantes[j].getNombre()+estudiantes[j].getPappi());
                if (estudiantes[p].getPappi() < estudiantes[j].getPappi()) {

                } else if (estudiantes[p].getPappi() > estudiantes[j].getPappi()) {
                    temporal = estudiantes[p];
                    estudiantes[p] = estudiantes[j];
                    estudiantes[j] = temporal;
                } else if (estudiantes[p].getPappi() == estudiantes[j].getPappi()) {
                    if (estudiantes[p].CreditosNoCancelados() < estudiantes[j].CreditosNoCancelados()) {

                    } else if (estudiantes[p].CreditosNoCancelados() > estudiantes[j].CreditosNoCancelados()) {
                        temporal = estudiantes[p];
                        estudiantes[p] = estudiantes[j];
                        estudiantes[j] = temporal;
                    } else if (estudiantes[p].CreditosNoCancelados() == estudiantes[j].CreditosNoCancelados()) {
                        if (ind(nombres,estudiantes[p].getNombre()) > ind(nombres,estudiantes[j].getNombre())) {

                        } else if (ind(nombres,estudiantes[p].getNombre()) < ind(nombres,estudiantes[j].getNombre())) {
                            temporal = estudiantes[p];
                            estudiantes[p] = estudiantes[j];
                            estudiantes[j] = temporal;
                        } else  {

                            
                        }
                    }
                }
            }
        }
for(int m=estudiantes.length-1;m>=0;m--){
    System.out.println(estudiantes[m].getNombre());
}
      
    }
    public static int  ind(String arr[],String obj){
    
    for(int i=0;i<arr.length;i++){
        if(arr[i]==obj){
            return i;
        }
        
    }
    return -1;//en caso de no encontrarlo retorna -1
}
    
}

class Estudiante {

    private String nombre;
    private int nummaterias;
    private Materia materias[];
    private int indicemateria;

    public Estudiante(String nombre, int nummaterias) {
        this.nombre = nombre;
        this.nummaterias = nummaterias;
        this.materias = new Materia[nummaterias];
        this.indicemateria = 0;
    }

    public int getNummaterias() {
        return nummaterias;
    }

    public void addMateria(Materia m) {
        try {
            this.materias[indicemateria] = m;
            this.indicemateria++;

        } catch (Exception e) {

        }

    }

    public String getNombre() {
        return nombre;
    }

    public int CreditosNoCancelados() {
        int num = 0;

        for (int i = 0; i < this.materias.length; i++) {
            num += this.materias[i].getCreditosNoCancelados();

        }
        return num;
    }

    public double getPappi() {
        DecimalFormat formato=new DecimalFormat("#.000000");
        double pappi = 0.000000000000, numcreditos = 0;
        for (int i = 0; i < this.materias.length; i++) {
            pappi += (this.materias[i].getNota() * this.materias[i].getCreditos());
            numcreditos += this.materias[i].getCreditos();
        }
        return Double.parseDouble(formato.format(pappi / numcreditos));
    }

}

class Materia {
    //DecimalFormat formato;
    private int creditos;
    private double nota;
    private boolean cancelada;

    public Materia(int cred, double not, boolean cancelada) {
        //this.formato=new DecimalFormat()
        
        this.creditos = cred;
        this.nota = not;
        this.cancelada = cancelada;
    }

    public double getNota() {
        return this.nota;
    }

    public int getCreditos() {
        return this.creditos;
    }

    public int getCreditosNoCancelados() {
        if (this.cancelada) {
            return 0;

        }
        return this.creditos;
    }

}

