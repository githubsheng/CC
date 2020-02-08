package leetcode;

import references.input.InputUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*

n LeetCode Store, there are some kinds of items to sell. Each item has a price.

However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.

You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers.

Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.

You could use any of special offers as many times as you want.

Example 1:
Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
Output: 14
Explanation:
There are two kinds of items, A and B. Their prices are $2 and $5 respectively.
In special offer 1, you can pay $5 for 3A and 0B
In special offer 2, you can pay $10 for 1A and 2B.
You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.


Example 2:
Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
Output: 11
Explanation:
The price of A is $2, and $3 for B, $4 for C.
You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C.
You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C.



You cannot add more items, though only $9 for 2A ,2B and 1C.
Note:
There are at most 6 kinds of items, 100 special offers.
For each item, you need to buy at most 6 of them.
You are not allowed to buy more items than you want, even if that would lower the overall price.


 */

@SuppressWarnings("Duplicates")
public class ShopOfferings638 {

    private int lowestPrice = Integer.MAX_VALUE;

    //for general objects, replace <Integer> with something else, say <String>
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {

        //convert price into special
        for(int itemIdx = 0; itemIdx < price.size(); itemIdx++) {
            int priceOfItem = price.get(itemIdx);
            List<Integer> offer = new ArrayList<>(price.size() + 1);
            offer.addAll(Collections.nCopies(price.size() + 1, 0));
            offer.set(itemIdx, 1);
            offer.set(offer.size() - 1, priceOfItem);
            special.add(offer);
        }


        List<Integer> bought = new ArrayList<>();
        for(int i = 0, l = needs.size(); i < l; i++) {
            bought.add(0);
        }

        _dfsAndBtMulti(special, 0, bought, 0, needs);

        if(lowestPrice == Integer.MAX_VALUE) return 0;

        return lowestPrice;
    }


    private void _dfsAndBtMulti(List<List<Integer>> special, int startIdx, List<Integer> bought, int price, List<Integer> needs) {

        if(price > lowestPrice) return;

        int size = special.size();
        if(startIdx >= size) return;

        for(int i = startIdx; i < size; i++) {

            List<Integer> offer = special.get(i);

            boolean notMoreThanNeed = true;
            int newPrice = price;

            //make a copy
            List<Integer> _bought = new ArrayList<>(bought);

            while(notMoreThanNeed) { //bought does not meet need, keep apply the same offer
                //apply offer.
                for(int j = 0; j < _bought.size(); j++) {
                    _bought.set(j, _bought.get(j) + offer.get(j));
                    if(_bought.get(j) > needs.get(j)) notMoreThanNeed = false;
                }

                if(notMoreThanNeed) {
                    newPrice += offer.get(offer.size() - 1);
                    if(isBoughtNeedSame(_bought, needs)) {
                        lowestPrice = Math.min(lowestPrice, newPrice);
                        break;
                    }

                    _dfsAndBtMulti(special, i + 1, _bought, newPrice, needs);// may not needed if above if statement works. todo:

                }
            }



            //apply other offer
        }
    }

    private boolean isBoughtNeedSame(List<Integer> bought, List<Integer> need) {

        for(int i = 0, l = bought.size(); i < l; i++) {
            if(bought.get(i) != need.get(i)) return false;
        }

        return true;
    }


/*
[2,5]
[[3,0,5],[1,2,10]]
[3,2]
 */
    public static void main(String[] args) {
        List<Integer> prices = InputUtil.parseToList("[2,5]");
        List<List<Integer>> specials = InputUtil.parseToNestedList("[[3,0,5],[1,2,10]]");
        List<Integer> needs = InputUtil.parseToList("[3,2]");

        ShopOfferings638 sol = new ShopOfferings638();
        int price = sol.shoppingOffers(prices, specials, needs);
        System.out.println(price);

    }

}