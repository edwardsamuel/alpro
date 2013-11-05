package dynamicprogramming;

public class MatrixChainOrder {

    public static Tuple<int[][], int[][]> MatriksChainOrder(int p[]) {
        int n = p.length - 1;
        int m[][] = new int[n + 1][n + 1];
        int s[][] = new int[n][n + 1];

        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }

        for (int l = 2; l <= n; l++) { // l is the chain length
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k <= j - 1; k++) {
                    System.out.println(String.format("%d %d %d %d", l, i, k ,j));
                    
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        return new Tuple<>(m, s);
    }

    public static void PrintOptimalParens(int[][] s, int i, int j) {
        
        if (i == j) {
            System.out.print("A");
            System.out.print(i);
        } else {
            System.out.print("(");
            PrintOptimalParens(s, i, s[i][j]);
            System.out.print("*");
            PrintOptimalParens(s, s[i][j] + 1, j);
            System.out.print(")");
        }        
    }

    public static void main(String[] args) {
        int p[] = {30, 35, 15, 5, 10, 20, 25 };
        Tuple<int[][], int[][]> result = MatriksChainOrder(p);
        PrintOptimalParens(result.getT2(), 1, 6);
    }
}
