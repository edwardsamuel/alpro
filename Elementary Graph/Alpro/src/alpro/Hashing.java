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
public class Hashing {
    
    public static int[] hashToNumber(String text, int m){
        int[] a = new int[text.length()];
        int i;
        for (i = 0; i < text.length(); i++){
            a[i] = ((int) text.charAt(i)) % m;
        }
        return a;
    }
    
    public static void main(String[] args) {
        System.out.print("Masukkan kalimat yang ingin di-hash : ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.print("Masukkan nilai pembagi : ");
        int m = in.nextInt();  // h(x) = k mod m
        int[] out = hashToNumber(input, m);
        System.out.print("\nHasil hash : ");
        for (int i = 0; i < out.length; i++){
            System.out.print(out[i]);
        }
        System.out.println("");
    }
}
