import references.input.InputUtil;
import references.output.OutputUtil;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class Problem973 {

    public int[][] kClosest2(int[][] points, int K) {
        int k = K;
        int size = points.length;

        int[] distance = new int[size];

        for(int i = 0; i < size; i++) {
            distance[i] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(K, (a, b) -> {
            return distance[b] - distance[a];
        });

        for(int i = 0; i < size; i++) {
            if(heap.size() < k) {
                heap.add(i);
            } else {
                Integer largest = heap.peek();
                if(distance[largest] <= distance[i]) continue;
                heap.poll();
                heap.add(i);
            }
        }

        int retSize = heap.size();
        Integer[] indices = new Integer[heap.size()];
        indices = heap.toArray(indices);
        int[][] results = new int[retSize][2];
        for(int i = 0; i < retSize; i++) {
            results[i] = points[indices[i]];
        }

        return results;
    }

    private Random random = new Random();

    public int[][] kClosest(int[][] points, int K) {
        int k = K;
        sort(points, k);
        return Arrays.copyOf(points, k);
    }

    public void sort(int[][] arr, int k) {
        sort(arr, k - 1, 0, arr.length - 1);
    }

    private void sort(int[][] arr, int k, int lowIdx, int highIdx) {
        int p = partition(arr, lowIdx, highIdx);
        if (p == k) return;
        if (p < k) {
            sort(arr, k, p + 1, highIdx);
        } else {
            //p > k
            sort(arr, k, lowIdx, p - 1);
        }
    }

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


    public static void main(String[] args) {
        Problem973 sol = new Problem973();
        int[][] ret = sol.kClosest(InputUtil.parseToNestedArrays("[[6,10],[-3,3],[-2,5],[0,2]]"), 3);
        System.out.println(ret);
    }
}
