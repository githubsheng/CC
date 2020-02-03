import java.util.Arrays;

public class Problem362 {

    private int[] time = new int[300];
    private int[] count = new int[300];
    private int earliest = 1;
    private int latest = 0;
    private int totalCount = 0;

    /** Initialize your data structure here. */
    public Problem362() {
        Arrays.fill(time, -1);
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        if(time[latest] == timestamp) {
            count[latest]++;
            totalCount++;
        } else {
            latest = (latest + 1) % 300;
            time[latest] = timestamp;
            count[latest] = 1;
            totalCount++;
        }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int s = timestamp > 300 ? timestamp - 300 : 0;
        while(time[earliest] != -1 && time[earliest] <= s) {
            totalCount -= count[earliest];
            time[earliest] = -1;
            earliest = (earliest + 1) % 300;
        }
        return totalCount;
    }

    public static void main(String[] args) {

        Problem362 counter = new Problem362();
        counter.hit(2);
        System.out.println(1);
        counter.hit(3);
        System.out.println(2);
        counter.hit(4);
        System.out.println(3);
        counter.getHits(300);
        System.out.println(4);
        counter.getHits(301);
        System.out.println(5);
        counter.getHits(302);
        System.out.println(6);
        counter.getHits(303);
        System.out.println(7);
        counter.getHits(304);
        System.out.println(8);
        counter.hit(501);
        System.out.println(9);
        counter.getHits(600);
        System.out.println(10);

    }

}
