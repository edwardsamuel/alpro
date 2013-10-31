package sorting;

import java.util.Scanner;

public class Sorting {
    
    public static void mainTugas1(String[] args) {
        int[] array1 = RandomGenerator.nearlySorted(1000);
        int[] array2 = new int[array1.length];
               
        System.arraycopy(array1, 0, array2, 0, array1.length);
               
        Quicksort qs = new Quicksort();
        long start1 = System.currentTimeMillis();
        qs.sort(array1);
        long end1 = System.currentTimeMillis();
        
        Heapsort hs = new Heapsort();
        long start2 = System.currentTimeMillis();
        hs.sort(array2);
        long end2 = System.currentTimeMillis();
        
        System.out.println("Quicksort : " + (end1 - start1) + " milis");
        System.out.println("Heapsort  : " + (end2 - start2) + " milis");
    }
    
    public static void mainTugas2(String[] args) {
        int[] array1 = RandomGenerator.fewUnique(10);
        int[] array2 = new int[array1.length];
        int[] array3 = new int[array1.length];
               
        System.arraycopy(array1, 0, array2, 0, array1.length);
        System.arraycopy(array1, 0, array3, 0, array1.length);
               
        Radixsort rs = new Radixsort();
        long start1 = System.currentTimeMillis();
        rs.sort(array1);
        long end1 = System.currentTimeMillis();
                
        CountingSort cs = new CountingSort();
        long start2 = System.currentTimeMillis();
        cs.sort(array2);
        long end2 = System.currentTimeMillis();
        
        BucketSort bs = new BucketSort(array1.length / 10);
        long start3 = System.currentTimeMillis();
        bs.sort(array3);
        long end3 = System.currentTimeMillis();
        
        System.out.println("Radix Sort    : " + (end1 - start1) + " milis");
        System.out.println("Counting Sort : " + (end2 - start2) + " milis");
        System.out.println("Bucket Sort   : " + (end3 - start3) + " milis");
    }
    
    public static void main(String[] args) {
        ISortingAlgorithm sorter;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Sorting");
        System.out.println("-----------------------------------");
        System.out.println("1. Bucket Sort");
        System.out.println("2. Counting Sort");
        System.out.println("3. Heap Sort");
        System.out.println("4. Quick Sort");
        System.out.println("5. Radix Sort");
        System.out.print("Select Algorithm: ");
        
        int opt = sc.nextInt();
        switch (opt) {
            case 1:
                System.out.print("Jumlah Bucket: ");
                sorter = new BucketSort(sc.nextInt());
                break;
            case 2:
                sorter = new CountingSort();
                break;
            case 3:
                sorter = new Heapsort();
                break;
            case 4:
                sorter = new Quicksort();
                break;
            case 5:
                sorter = new Radixsort();
                break;
            default:
                System.out.println("Unknown option. Quit.");
                return;
        }
        
        System.out.print("Jumlah Angka: ");        
        int[] array = new int[sc.nextInt()];
        
        for (int i = 0, len = array.length; i < len; i++) {
            System.out.print(i);
            System.out.print(" > ");
            array[i] = sc.nextInt();
        }
        
        long start = System.currentTimeMillis();
        sorter.sort(array);
        long end = System.currentTimeMillis();
        
        System.out.println("-----------------------------------");
        System.out.println("Hasil: ");        
        RandomGenerator.print(array);
        
        System.out.println("-----------------------------------");
        System.out.println("Sorted in " + (end - start) + " milis");
    }
}
