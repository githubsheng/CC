package references.algs;

import java.util.Random;

public class QuickSelect {

    private Random random = new Random();

    public int kthSmallest(int[] arr, int k) {
        //0, 1, 2, 3, second smallest is 1, and its index is 1.
        //so kth smallest, its index should be k-1.
        return selectElementAtIdx(arr, k - 1, 0, arr.length - 1);
    }

    private int selectElementAtIdx(int[] arr, int k, int lowIdx, int highIdx) {
        int p = partition(arr, lowIdx, highIdx);
        if (p == k) return arr[p];
        if (p < k) return selectElementAtIdx(arr, k, p + 1, highIdx);
        //p > k
        return selectElementAtIdx(arr, k, lowIdx, p - 1);
    }

    private int partition(int[] arr, int lowIdx, int highIdx) {
        if(lowIdx >= highIdx) return highIdx;
        int pivotIdx = random.nextInt(highIdx - lowIdx + 1) + lowIdx;
        int pivotVal = arr[pivotIdx];
        //move this pivot value to the end so that we can swap it to the pivotLoc later.
        swap(arr, pivotIdx, highIdx);
        int pivotLoc = lowIdx;
        for (int i = lowIdx; i <= highIdx; i++) {
            if(arr[i] < pivotVal) {
                swap(arr, i, pivotLoc);
                pivotLoc++;
            }
        }
        swap(arr, pivotLoc, highIdx);
        return pivotLoc;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public static void main(String[] args) {
        QuickSelect sol = new QuickSelect();
        int[] array = new int[]{5, 3, 4, 2, 1, 2, 2, 2, 6, 9, 8, 7};
        for (int i = 1; i < 13; i++) {
            int ret = sol.kthSmallest(array, i);
            System.out.println(ret);
        }

    }

}
