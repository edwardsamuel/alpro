package sorting;

import java.util.Arrays;

public class CountingSort {
    
    public static int maxInt(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    public static int minInt(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }
    
    public void sort(int[] arr) {
        int valMin = minInt(arr);
        int valMax = maxInt(arr);
        
        int[] tabCount = new int[valMax - valMin + 1];

        for (int x : arr) {
            tabCount[x - valMin]++;
        }

        int k = 0;
        for (int x = 0; x < tabCount.length; x++) {
            Arrays.fill(arr, k, k + tabCount[x], x + valMin);
            k += tabCount[x];
        }
    }
}