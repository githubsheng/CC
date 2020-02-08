package leetcode;

import java.util.LinkedList;

//14:00
//14:26
public class Problem42 {

    public int trap2(int[] height) {

        int totalTrapped = 0;
        LinkedList<Integer> bars = new LinkedList<>();

        for (int i = 0; i < height.length; i++) {

            if(bars.isEmpty()) {
                bars.add(i);
                continue;
            }

            int currBar = height[i];

            int lastBar = height[bars.peekLast()];

            if(currBar < lastBar) {
                bars.add(i);
                continue;
            }

            while(currBar > lastBar) {
                //last last bar > last bar < currBar => can trap water
                int middle = bars.pollLast();
                Integer left = bars.peekLast();
                if (left == null) break; // no left, cannot trap water
                //right is currBar idx i.

                //water height
                int h = Math.min(currBar, height[left]) - height[middle];
                //water width
                int w = i - left - 1;
                int trapped = h * w;
//                System.out.println(trapped);
                totalTrapped += trapped;
                lastBar = height[left];
            }

            bars.add(i);

        }

        return totalTrapped;
    }


    public int trap(int[] height) {

        int totalTrapped = 0;

        int[] leftTallestRecords = new int[height.length];
        int leftTallest = -1;
        for (int i = 0; i < height.length; i++) {
            int bar = height[i];
            leftTallest = Math.max(leftTallest, bar);
            leftTallestRecords[i] = leftTallest;
        }

        int[] rightTallestRecords = new int[height.length];
        int rightTallest = -1;
        for (int i = height.length - 1; i > -1 ; i--) {
            int bar = height[i];
            rightTallest = Math.max(rightTallest, bar);
            rightTallestRecords[i] = rightTallest;
        }

        for (int i = 0; i < height.length; i++) {
            int lowerBar = Math.min(leftTallestRecords[i], rightTallestRecords[i]);
            int trapped = Math.max(0, lowerBar - height[i]);
            totalTrapped += trapped;
        }

        return totalTrapped;
    }

    public static void main(String[] args) {
        Problem42 sol = new Problem42();
        int ret = sol.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
        System.out.println(ret);
    }

}
