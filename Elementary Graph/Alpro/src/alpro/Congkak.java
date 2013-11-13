/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alpro;

import java.util.Scanner;

/**
 *
 * @author Wachid
 */
public class Congkak {
    
    public static int solvePerangko(int sisa, int[] p){
       int i = 0, count = 0;
       boolean sudah = false;
       while (i < p.length && !sudah){
           sisa -= p[i];
           if (sisa <= 0){
               sudah = true;
               count++;
           } else {
               count++;
           }
           i++;
       }
       if (sisa > 0)
           return -1;
       else
           return count;
    }
    
    public static void main(String[] args) {
        int n, p;
        int[] perangko;
        int perangkoCongkak;
        int teman;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        int i, j;
        for(i = 0; i < n; i++){
            perangkoCongkak = in.nextInt();
            p = in.nextInt();
            perangko = new int[p];
            for (j = 0; j < p; j++){
                perangko[j] = in.nextInt();
            }
            Quicksort sorter = new Quicksort();
            sorter.sort(perangko);
            teman = solvePerangko(perangkoCongkak, perangko);
            System.out.println("Pameran #"+(i+1)+":");
            if (teman == -1){
                System.out.println("tidak mungkin\n\n");
            } else {
                System.out.println(teman+"\n\n");
            }
        }
    }
}
