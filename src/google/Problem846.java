package google;

import java.util.*;

/*

Alice has a hand of cards, given as an array of integers.

Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.

Return true if and only if she can.



Example 1:

Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
Example 2:

Input: hand = [1,2,3,4,5], W = 4
Output: false
Explanation: Alice's hand can't be rearranged into groups of 4.


Note:

1 <= hand.length <= 10000
0 <= hand[i] <= 10^9
1 <= W <= hand.length

 */
public class Problem846 {

    private final static class CardFreq {
        int card;
        int count;
        //...constructor
        CardFreq(int card, int count) {
            this.card = card;
            this.count = count;
        }
    }

    public boolean isNStraightHand(int[] hand, int W) {
        Arrays.sort(hand);
        List<CardFreq> cfs = new ArrayList<>();
        CardFreq last = null;
        for(int c : hand) {
            if(last == null || last.card != c) {
                last = new CardFreq(c, 1);
                cfs.add(last);
            } else {
                last.count++;
            }
        }
        cfs.add(last);

        /*
            1 0
            2 0
            3 0
            4 0
            6 1
            7 1
            8 1

         */

        for (int i = 0, l = cfs.size(); i < l; i++) {
            CardFreq cf = cfs.get(i);
            if(cf.count == 0) continue;
            int groups = cf.count; //need to have this many groups to allocate `cf.count` of `cf.card`
            for(int j = 0; j < W; j++) {
                int nextIdx = i + j;
                int nextValue = cf.card + j;
                if(nextIdx >= l) return false;
                CardFreq next = cfs.get(nextIdx);
                if(next.card != nextValue) return false;
                //for each of the W subsequent cards(including cf itself), we allocate `group` of it to the groups
                next.count -= groups;
                if(next.count < 0) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Problem846 sol = new Problem846();
        boolean ret = sol.isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8}, 3);
        System.out.println(ret);
    }



}
