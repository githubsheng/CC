/**
 * @param {string} version1
 * @param {string} version2
 * @return {number}
 */
var compareVersion = function(version1, version2) {
    let v1 = version1.split('.');
    let v2 = version2.split('.');
    v1 = v1.map(removePrefixZeros);
    v2 = v2.map(removePrefixZeros);

    v1 = removeUncessaryTrailingZeros(v1);
    v2 = removeUncessaryTrailingZeros(v2);

    let isSwapped = false;
    if(v1.length > v2.length) {
        [v1, v2] = [v2, v1];
        isSwapped = true;
    }

    let ret = 0;

    for(let i = 0, l = v1.length; i < l; i++) {
        let c1 = v1[i];
        let c2 = v2[i];
        if(c1 === c2) continue;
        ret = +(c1) < +(c2) ? -1 : 1;
        break;
    }

    //if ret is 0, it means v1 is a prefix of v2

    //if v1 is shorter than v2, then v1 must be smaller than v2.
    if(ret === 0 && v1.length !== v2.length) ret = -1;

    if(isSwapped) ret = -ret;

    return ret ;
};

function removePrefixZeros(str) {
    if(str[0] !== '0') return str;
    let noneZeroCharIdx = -1;
    for(let i = 1, l = str.length; i < l; i++) {
        if(str[i] !== '0') {
            noneZeroCharIdx = i;
            break;
        }
    }
    if(noneZeroCharIdx === -1) return '0';
    return str.substring(noneZeroCharIdx);
}

function removeUncessaryTrailingZeros(list) {
    let last = list.length - 1;

    if(list[last] !== '0') return list;
    let noneZeroCharIdx = -1;
    for(let i = last; i > -1 ; i--) {
        if(list[i] !== '0') {
            noneZeroCharIdx = i;
            break;
        }
    }
    if(noneZeroCharIdx === -1) return ['0'];
    return list.slice(0, noneZeroCharIdx + 1);
}

let ret = compareVersion('1.0', '1.0.0');
console.log(ret);