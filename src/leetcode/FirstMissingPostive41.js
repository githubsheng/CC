/**
 * @param {number[]} nums
 * @return {number}
 */
var firstMissingPositive = function(nums) {

    if (nums.length === 0) return 1;

    let arrayLength = nums.length;
    let upperLimit = arrayLength;
    for (let i = 0; i < nums.length; i++) {
        let val = nums[i];
        if(val < 1 || val > upperLimit) {
            nums[i] = undefined;
        } else {
            let idx = val - 1;
            while( idx !== i ) {
                let tmp = nums[idx];
                nums[idx] = val;
                nums[i] = tmp;

                if(tmp < 1 || tmp > upperLimit || tmp === undefined || tmp === val ) {
                    nums[i] = undefined;
                    break;
                } else {
                    val = tmp;
                    idx = val - 1;
                }
            }
        }
    }

    for (let i = 0; i < nums.length; i++) {
        if (nums[i] === undefined) {
            return i + 1;
        }
    }

    return upperLimit + 1;

};


let ret;
// ret = firstMissingPositive([1, 2, 0]);
// ret = firstMissingPositive([7,8,9,11,12]);
// ret = firstMissingPositive([3, 4, -1, 1]);

ret = firstMissingPositive([1]);

console.log(ret);