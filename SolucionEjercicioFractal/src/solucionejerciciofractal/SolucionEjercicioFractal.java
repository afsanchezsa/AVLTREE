/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solucionejerciciofractal;

import java.util.Scanner;

/**
 *
 * @author COMPAQ
 */
public class SolucionEjercicioFractal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
              Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int profundidad=sc.nextInt();
        // TODO code application logic here
        String matriz[][]=new String[N][N];
        for(int i=0;i<matriz.length;i++){
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j]="m";
            }
        }
        
        sierpinski(profundidad,matriz,0,0,N-1,N-1);
        
        imprimir(matriz);
    }
     public static void sierpinski(int profundidad, String[][]matriz, int i1, int j1,int i2,int j2){
        int i=i1;
        int j=j1;
        int k=0;
        int n=i1+1;
        if(profundidad==-1){
            return;
        }else{
            /*if(matriz.length==1){
            matriz[i1][j2]="_";
            
            return;
            
            }
           */ 
          for(int s=j1;s<=j2;s++){
              for(int p=i1;p<=i2;p++){
                  matriz[s][p]="m";
              }
          }
           for(j=j1;j<=j2;j++){
               for(i=i1;i<n&&i<=i2;i++){
                   matriz[j][i]="#";
               }
               n++;
           }
           for(j=j1;j<=j2;j++){
               for(i=i1;i<=i2;i++){
                   if(!matriz[j][i].equals("#")){
                       matriz[j][i]="_";
                   }
               }
               
           }
            
           try{
            sierpinski(profundidad-1, matriz, i1,((j1+j2)/2)+1,((i1+i2)/2),j2);
           }catch(Exception e){
               
           }
             try{
            sierpinski(profundidad-1, matriz, i2+1,((j1+j2)/2)+1,((j2-j1)+1)+((i2+i1)/2),j2);//derecho
           }catch(Exception e){
                 
           }
              try{
            sierpinski(profundidad-1, matriz, ((i1+i2)/2)-((j2-j1)/2),j1-1-(((j2-j1)/2)),(i1+i2)/2,j1-2);//arriba
           }catch(Exception e){
                 
           }
             
           
        }
    }
      public static void imprimir(  String[][]a){
             for(int i=0;i<a.length;i++){
           for(int j=0;j<a.length;j++){
               System.out.print(a[i][j]); 
           }
                 System.out.print("\n");
        }
    }    
}
