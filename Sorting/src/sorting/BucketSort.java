package sorting;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Raymond
 */
public class BucketSort {

    private int bucketCount;
    
    public BucketSort(int bucketCount) {
        this.bucketCount = bucketCount;
    }
    
    /**
     * Bucket sort
     *
     * @param array array to be sorted
     * @return array sorted in ascending order
     */
    public int[] sort(int[] array) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException("Invalid bucket count");
        }
        if (array.length <= 1) {
            return array; //trivially sorted
        }
        int high = array[0];
        int low = array[0];
        for (int i = 1; i < array.length; i++) { //find the range of input elements
            if (array[i] > high) {
                high = array[i];
            }
            if (array[i] < low) {
                low = array[i];
            }
        }
        double interval = ((double) (high - low + 1)) / bucketCount; //range of one bucket
        ArrayList<Integer> buckets[] = new ArrayList[bucketCount];
        for (int i = 0; i < bucketCount; i++) { //initialize buckets
            buckets[i] = new ArrayList();
        }
        
        for (int i = 0; i < array.length; i++) { //partition the input array
            int index = (int) ((array[i] - low) / interval);
            buckets[index].add(array[i]);
        }

        int i;
        ArrayList<Integer> hsl = new ArrayList<>();
        for (i = 0; i < buckets.length; i++) {
            Collections.sort(buckets[i]); //mergeSort
            
            for (int j = 0; j < buckets[i].size(); j++) { //merge the buckets

                hsl.add(buckets[i].get(j));
            }
            
        }
        return array;
    }
}
