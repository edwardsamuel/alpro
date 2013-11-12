/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alpro;

import java.util.Scanner;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author Wachid
 */
public class Greedy {
    
    public static String recursiveActivitySelector(int[] s, int[] f, int k, int n){
        int m = k + 1;
        String out = "";
        while (m <= n-1 && s[m] < f[k]){
            m++;
        }
        out = out.concat(Integer.toString(m+1));
        out = out.concat(" ");
        if (m <= n-1)
            return out.concat(recursiveActivitySelector(s, f, m, n));
        else
            return "";
    }
    
    public static String greedyActivitySelector(int[] s, int[] f){
        int m;
        int n = s.length;
        String A = "1 ";
        int k = 0;
        for (m = 1; m < n; m++){
            if (s[m] >= f[k]){
                A = A.concat(Integer.toString(m+1)+" ");
                k = m;
            }
        }
        return A;
    }
    
    public static void main(String[] args) {
        int[] s;
        int[] f;
        int i;
        
        System.out.print("Masukkan jumlah aktivitas : ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        s = new int[n];
        f = new int[n];
        for(i = 0; i<n; i++ ){
            System.out.print("Masukkan waktu start : ");
            s[i] = in.nextInt();
            System.out.print("Masukkan waktu finish : ");
            f[i] = in.nextInt();
        }
        
        String recursive = Integer.toString(1) + " " + recursiveActivitySelector(s, f, 0, s.length);
        String iterative = greedyActivitySelector(s, f);
        System.out.println("\nStart time : ");
        for(int e: s){
            System.out.print(e+" ");
        }
        System.out.println("");
        System.out.println("Finish time : ");
        for(int e: f){
            System.out.print(e+" ");
        }
        System.out.println("\n");
        System.out.println("Activity selection problem with recursive greedy : "+recursive);
        System.out.println("Activity selection problem with iterative greedy : "+iterative);
    }
}
