package sorting;

import java.util.Random;

public class RandomGenerator {
 
    private static Random r = new Random();
    
    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    
    public static void printHorizontal(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%3d  ", array[i]);
        }
    }
    
    public static int[] random(int size) {
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = r.nextInt();
        }
        return numbers;
    }
    
    public static int[] randomPositive(int size) {
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = Math.abs(r.nextInt(1000));
        }
        return numbers;
    }
    
    public static int[] nearlySorted(int size) {
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            if (r.nextDouble() < 0.90) {
                numbers[i] = i + 3;
            } else {
                numbers[i] = i;
            }
        }
        return numbers;
    }
    
    
    public static int[] nearlySortedReversed(int size) {
        int[] numbers = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            if (r.nextDouble() < 0.90) {
                numbers[size - 1 - i] = i + 3;
            } else {
                numbers[size - 1 - i] = i;
            }
        }
        return numbers;
    }
    
    public static int[] fewUnique(int size) {
        int[] numbers = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            if (r.nextDouble() > 0.80) {
                numbers[size - 1 - i] = 3;
            } else if (r.nextDouble() > 0.60) {
                numbers[size - 1 - i] = 2;
            } else if (r.nextDouble() > 0.40) {
                numbers[size - 1 - i] = 1;
            } else if (r.nextDouble() > 0.20) {
                numbers[size - 1 - i] = 0;
            } else {
                numbers[size - 1 - i] = 4;                
            }
        }
        return numbers;
    }
}
