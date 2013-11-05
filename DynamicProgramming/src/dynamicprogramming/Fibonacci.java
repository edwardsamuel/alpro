package dynamicprogramming;

import java.util.Scanner;

public class Fibonacci {

    public static long NaiveFibonacci(int n) {
        if (n <= 2) {
            return 1;
        } else {
            return NaiveFibonacci(n - 1) + NaiveFibonacci(n - 2);
        }
    }

    public static long MemoizedFibonacci(int n) {
        long p[] = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = Long.MIN_VALUE;
        }
        return MemoizedFibonacciAux(p, n);
    }

    private static long MemoizedFibonacciAux(long p[], int n) {
        if (p[n] > 0) {
            return p[n];
        }

        long q;
        if (n <= 2) {
            q = 1;
        } else {
            q = MemoizedFibonacciAux(p, n - 1) + MemoizedFibonacciAux(p, n - 2);
        }
        p[n] = q;
        return q;
    }

    public static long BottomUpFibonacci(int n) {
        long p[] = new long[n + 1];
        if (n >= 1) {
            p[1] = 1;
        }
        if (n >= 2) {
            p[2] = 1;
        }
        if (n >= 3) {
            for (int i = 3; i <= n; i++) {
                p[i] = p[i - 1] + p[i - 2];
            }
        }
        return p[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n;
        do {
            System.out.println("Fibonacci");
            System.out.print("Masukkan nilai n = ");
            n = sc.nextInt();
            if (n > 0) {
                System.out.print("Algo (1 Native / 2 Top-Down / 3 Bottom-Up) = ");
                int algo = sc.nextInt();
                if (algo == 1) {
                    long startN = System.currentTimeMillis();
                    long resN = NaiveFibonacci(n);
                    long endN = System.currentTimeMillis();
                    System.out.println(String.format("Naive               : %d (in %d ms)", resN, endN - startN));
                } else if (algo == 2) {
                    long startT = System.currentTimeMillis();
                    long resT = MemoizedFibonacci(n);
                    long endT = System.currentTimeMillis();
                    System.out.println(String.format("Top-Down (Memoized) : %d (in %d ms)", resT, endT - startT));
                } else if (algo == 3) {
                    long startB = System.currentTimeMillis();
                    long resB = BottomUpFibonacci(n);
                    long endB = System.currentTimeMillis();
                    System.out.println(String.format("Bottom-Up           : %d (in %d ms)", resB, endB - startB));
                }
            }
            System.out.println("---------");
        } while (n > 0);
    }
}
