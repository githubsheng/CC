import java.util.Arrays;

public class Problem973 {

    public int[][] kClosest(int[][] points, int K) {
        /*
        the Kth smallest element in a sorted array will have index (first occurrence if duplicate) target = K -1
        all elements before it will be smaller than it
        all elements after it will be equal or bigger than it
        for instance, [1, 2, 3, 3, 3, 4, 5, 5, 6]
        3rd smallest element, first occurs at index 2 = 3 - 1. and all elements before it are smaller than it,
        all elements after it are bigger than it
         */
        int target = K - 1;
        int start = 0;
        int end = points.length - 1;
        int pivot;
        while(start < end) {
            pivot = partition(points, start, end);
            if(target > pivot) {
                start = pivot + 1;
            } else if (target < pivot) {
                end = pivot - 1;
            } else {
                //target == pivot
                break;
            }
        }
        return Arrays.copyOf(points, K);
    }


    /*
     * after partition, pivotLoc will be pointing at an element such that,
     * from lowIdx to pivot - 1 (inclusive), all elements are smaller than it
     * from pivot + 1 to highIdx (inclusive), all elements are bigger or equal than it.
     */
    private int partition(int[][] arr, int lowIdx, int highIdx) {
        if(lowIdx >= highIdx) return highIdx;
        int pivotVal = distance(arr[highIdx]);
        int pivotLoc = lowIdx;
        for (int i = lowIdx; i <= highIdx; i++) {
            if(distance(arr[i]) < pivotVal) {
                swap(arr, i, pivotLoc);
                pivotLoc++;
            }
        }
        swap(arr, pivotLoc, highIdx);
        return pivotLoc;
    }

    private int distance(int[] pos) {
        return pos[0] * pos[0] + pos[1] * pos[1];
    }

    private void swap(int[][] arr, int a, int b) {
        int[] temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
