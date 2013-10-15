package sorting;

/**
 *
 * @author Edward Samuel
 */
public class Radixsort {

    private static int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int[] array;
    private int length;

    public void sort(int[] values) {
        // Check for empty or null array
        if (values == null || values.length == 0) {
            return;
        }
        this.array = values;
        length = values.length;
        radixsort();
    }

    private void radixsort() {
        int i, max = array[0], exp = 1;
        int[] b = new int[array.length];

        for (i = 1; i < length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        while (max / exp > 0) {
//            System.out.println("Iterasi ke-" + (int) Math.log10(exp));            
//            System.out.print("Data    : ");
//            RandomGenerator.printHorizontal(array);
//            System.out.println();
//            System.out.println();

            int[] bucket = new int[10];
            for (i = 0; i < length; i++) {
                bucket[(array[i] / exp) % 10]++;
            }

//            System.out.print("          ");
//            RandomGenerator.printHorizontal(numbers);
//            System.out.println();
//            System.out.print("Counts  : ");
//            RandomGenerator.printHorizontal(bucket);
//            System.out.println();

            for (i = 1; i < 10; i++) {
                bucket[i] += bucket[i - 1];
            }

//            System.out.print("Offsets : ");
//            RandomGenerator.printHorizontal(bucket);
//            System.out.println();

            for (i = length - 1; i >= 0; i--) {
                b[--bucket[(array[i] / exp) % 10]] = array[i];
            }

//            System.arraycopy(b, 0, array, 0, b.length);
            exp *= 10;
            
//            System.out.println();
        }

//        System.out.print("Data    : ");
//        RandomGenerator.printHorizontal(array);
//        System.out.println();
    }

    public static void main(String[] args) {
        int size = 1000;
        Radixsort rs = new Radixsort();

        int[] numbers = new int[]{531, 141, 459, 626, 253, 358, 97, 793, 623, 684};
        // int[] numbers = RandomGenerator.randomPositive(size);

        // RandomGenerator.print(numbers);
        System.out.println("------------------------");

        long start = System.currentTimeMillis();
        rs.sort(numbers);
        long end = System.currentTimeMillis();

        // RandomGenerator.print(numbers);
        System.out.println("------------------------");
        System.out.println("Time: " + (end - start) + " milis");
    }
}
