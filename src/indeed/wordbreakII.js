class Solution {
    buildTrie(dict) {
        const root = new Node();
        for(let str of dict) {
            this.addToTrie(str, root);
        }
        return root;
    }

    addToTrie(str, root) {
        let prev = root, i = 0;

        while(i < str.length) {
            const c = str[i];
            let node = prev.next[c];
            if(!node) {
                node = new Node();
                prev.next[c] = node;
            }
            prev = node;
            i++;
        }

        //prev is node for last char
        prev.word = str;
    }

    getPrefixes(str, root, start) {
        let prev = root, i = start;
        const ret = [];

        while(i < str.length) {
            const c = str[i];
            const node = prev.next[c];
            if(!node) return ret;
            if(node.word) ret.push(node.word);
            prev = node;
            i++;
        }
        return ret;
    }

    helper(str, root, start, path, ret, cache)           {
        if(start == str.length) {
            ret.push(path.join(" "));
            return true;
        }
        //default value is undefined, not false.
        if(cache[start] === false) return false;

        const ps = this.getPrefixes(str, root, start);

        let reachEnd = false;
        for(let p of ps) {
            path.push(p);
            let r = this.helper(str, root, start + p.length, path, ret, cache);
            if(r) reachEnd = true;
            path.pop(); //back track
        }

        cache[start] = reachEnd;
        return reachEnd;
    }

    sol(word, dict) {
        const root = this.buildTrie(dict), ret = [], cache = {};
        this.helper(word, root, 0, [], ret, cache);
        return ret;
    }
}

class Node {
    constructor(){
        this.word = null;
        this.next = {};
    }
}

const ret = new Solution().sol("catsanddog", ["cat", "cats", "and", "sand", "dog"]);
console.log(ret);
