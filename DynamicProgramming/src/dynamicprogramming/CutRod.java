package dynamicprogramming;

import java.lang.reflect.Array;
import java.util.Scanner;

public class CutRod {

    public static int CutRod(int p[], int n) {
        if (n == 0) {
            return 0;
        }

        int q = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int temp = p[i] + CutRod(p, n - i);
            if (q < temp) {
                q = temp;
            }
        }
        return q;
    }

    public static int MemoizedCutRod(int p[], int n) {
        int r[] = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            r[i] = Integer.MIN_VALUE;
        }
        return MemoizedCutRodAux(p, n, r);
    }

    private static int MemoizedCutRodAux(int[] p, int n, int[] r) {
        if (r[n] >= 0) {
            return r[n];
        }
        int q;
        if (n == 0) {
            q = 0;
        } else {
            q = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                int temp = p[i] + MemoizedCutRodAux(p, n - i, r);
                if (q < temp) {
                    q = temp;
                }
            }
        }
        r[n] = q;
        return q;
    }
    
    public static int BottomUpCutRod(int p[], int n) {
        int r[] = new int[n + 1];
        r[0] = 0;
        for (int j = 1; j <= n; j++) {
            int q = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                int temp = p[i] + r[j - i];
                if (q < temp) {
                    q = temp;
                }                
            }
            r[j] = q;
        }
        return r[n];
    }
    
    public static Tuple<int[], int[]> ExtendedBottomUpCutRod(int p[], int n) {
        int r[] = new int[n + 1];
        int s[] = new int[n + 1];
        r[0] = 0;
        for (int j = 1; j <= n; j++) {
            int q = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                int temp = p[i] + r[j - i];
                if (q < temp) {
                    q = temp;
                    s[j] = i;
                }                
            }
            r[j] = q;
        }
        return new Tuple<>(r, s);
    }
    
    public static void PrintCutRodSolution(int p[], int n) {
        Tuple<int[], int[]> result = ExtendedBottomUpCutRod(p, n);
        int r[] = result.getT1();
        int s[] = result.getT2();
        
        System.out.print(r[n]);
        System.out.print(" (");
        while (n > 0) {
            System.out.print(s[n] + " ");
            n = n - s[n];
        }
        System.out.print(")");
    }

    public static void main(String[] args) {
        // int p[] = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("CutRod");
        System.out.print("Masukkan nilai n = ");
        int n = sc.nextInt();
        
        int p[] = new int[n + 1];
        p[0] = 0;
        for (int i = 1; i <= n; i++) {
            System.out.print("[" + i + "] = ");
            p[i] = sc.nextInt();
        }
        
        int p1[] = new int[n + 1];
        int p2[] = new int[n + 1];
        int p3[] = new int[n + 1];
        int p4[] = new int[n + 1];
        System.arraycopy(p, 0, p1, 0, n + 1);
        System.arraycopy(p, 0, p2, 0, n + 1);
        System.arraycopy(p, 0, p3, 0, n + 1);
        System.arraycopy(p, 0, p4, 0, n + 1);
        
        System.out.println("CutRod                 := " + CutRod(p1, n));
        System.out.println("MemoizedCutRod         := " + MemoizedCutRod(p2, n));
        System.out.println("BottomUpCutRod         := " + BottomUpCutRod(p3, n));
        System.out.print("ExtendedBottomUpCutRod := ");
        PrintCutRodSolution(p4, n);
    }
}
