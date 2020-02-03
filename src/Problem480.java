import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Problem480 {

    //max heap
    private PriorityQueue<Integer> smaller = new PriorityQueue<>((a, b) -> b.compareTo(a));
    //min heap
    private PriorityQueue<Integer> bigger = new PriorityQueue<>();
    private double median;

    public double[] medianSlidingWindow(int[] nums, int k) {
        if(nums.length == 0) return new double[]{};
        List<Double> ret = new ArrayList<>(nums.length);
        boolean isEven = k % 2 == 0;
        median = nums[0];
        bigger.add(nums[0]);
        if(k == 1) ret.add(median);

        for (int i = 1; i < nums.length; i++) {
            if(i >= k) {
                remove(i, nums, k);
            }
            add(i, nums);
            balance();
            median = getMedian(smaller, bigger, isEven);
            if(i >= k - 1) {
                ret.add(median);
            }
        }

        double[] r = new double[ret.size()];
        int i = 0;
        for(double d : ret) {
            r[i++] = d;
        }

        return r;
    }

    private void add(int i, int[] nums){
        int t = nums[i];
        if(t < median) {
            smaller.add(t);
        } else {
            bigger.add(t);
        }

    }

    private void remove(int i, int[] nums, int k) {
        int ri = i - k;
        int t = nums[ri];
        if( t < median) {
            smaller.remove(t);
        } else {
            bigger.remove(t);
        }
    }

    private void balance(){
        //bigger is always same or 1 more than smaller
        while(bigger.size() < smaller.size()) {
            bigger.add(smaller.poll());
        }
        while(bigger.size() - smaller.size() > 1) {
            smaller.add(bigger.poll());
        }
    }

    private double getMedian(PriorityQueue<Integer> smaller, PriorityQueue<Integer> bigger, boolean isEven) {
        if(isEven) {
            double s = smaller.peek();
            double b = bigger.peek();
            return s + (b - s) / 2.0;
        } else {
            return bigger.peek();
        }
    }

    public static void main(String[] args) {
        Problem480 sol = new Problem480();
        double[] ret = sol.medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 1);
        for (double d : ret) {
            System.out.println(d);
        }
    }

}
