package google;

/*

Given an array nums of length n. All elements appear in pairs except one of them. Find this single element that appears alone.
Pairs of the same element cannot be adjacent:

[2, 2, 1, 2, 2] // ok
[2, 2, 2, 2, 1] // not allowed
Example 1:

Input: [2, 2, 1, 1, 9, 9, 5, 2, 2]
Output: 5
Example 2:

Input: [1, 1, 2]
Output: 2
Example 3:

Input: [3, 3, 2, 3, 3]
Output: 2


O(n) is easy...
hint, use binary search to do it in O(log(n)).

 */
public class FindUniqueElement {

    public int findUnique(int[] input) {
        int ret = 0;
        for (int value : input)
            ret ^= value;
        return ret;
    }

    public static void main(String[] args) {
        FindUniqueElement sol = new FindUniqueElement();
        int ret = sol.findUnique(new int[]{2, 2, 1, 1, 9, 9, 5, 2, 2});
        System.out.println(ret);
    }

}
