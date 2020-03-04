/**
 * Created by wangsheng on 2020/02/03.
 */

//solution one: not good....
function intersectionOfKSortedArray(list) {
    let common = list[0];
    for(let i = 1; i < list.length; i++) {
       common = intersectionOfTwoSortedArray(common, list[i]);
    }
    return common;
}

function intersectionOfTwoSortedArray(arr1, arr2) {
    let idx1 = 0;
    let idx2 = 0;
    const ans = [];
    let prev;
    while(idx1 < arr1.length && idx2 < arr2.length) {
        const e1 = arr1[idx1];
        const e2 = arr2[idx2];
        if(e1 < e2) {
            idx1++;
        } else if (e1 > e2) {
            idx2++;
        } else {
            //same
            if(e1 != prev) { //avoid duplicates
                ans.push(e1);
                prev = e1
            }
            idx1++;
            idx2++;
        }
    }
    return ans;
}
//solution one ends...


//solution two starts
//this is extremely similar to KFrequentNumber, i would say they are most likely the same question
//because this solution requires a heap, i use java, and it is in the java file with the same name

//solution two ends

const list = [];
list.push([1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 10]);
list.push([2, 4, 6, 8, 10]);
list.push([6, 8, 11, 12, 13]);

const ans = intersectionOfKSortedArray(list);
console.log(ans);
