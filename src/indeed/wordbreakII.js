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

    //this is useful for returning all solutions
    helperUsingTrie(str, root, start, cache) {
        if(start > str.length) return [];
        if(cache[start]) return cache[start];
        const ret = [];
        const ps = this.getPrefixes(str, root, start);
        for(let p of ps) {
            if(start + p.length == str.length) {
                ret.push(p);
            } else {
                const remainComb = this.helperUsingTrie(str, root, start + p.length, cache);
                for(let comb of remainComb) {
                    ret.push(p + " " + comb);
                }
            }
        }
        cache[start] = ret;
        return ret;
    }


    solUsingTrie(word, dict) {
        const root = this.buildTrie(dict), ret = [], cache = {};
        this.helperUsingTrie(word, root, 0, cache);
        return ret;
    }


    helperUsingSubstr(word, dict, cache) {
        if(cache[word]) return cache[word];
        const ret = [];

        for(let i = 1; i < word.length; i++) {
            const prefix = word.substring(0, i);

            if(dict.has(prefix)) {
                const suffix = word.substring(i);
                const t = this.helperUsingSubstr(suffix, dict, cache);
                for(let remain of t) {
                    ret.push(`${prefix} ${remain}`);
                }
            }
        }

        if(dict.has(word)) ret.push(word);
        cache[word] = ret;
        return ret;

    }

    solUsingSubstr(word, dictList) {
        const dict = new Set();
        for(let e of dictList) dict.add(e);
        return this.helperUsingSubstr(word, dict, {});
    }

    helperFindAnyUsingSubstr(word, dict, cache) {
        if(cache[word]) return cache[word];
        let ret;

        for(let i = 1; i < word.length; i++) {
            const prefix = word.substring(0, i);

            if(dict.has(prefix)) {
                const suffix = word.substring(i);
                const t = this.helperFindAnyUsingSubstr(suffix, dict, cache);
                if(!t) continue;
                ret = `${prefix} ${t}`;
                break;
            }
        }

        if(dict.has(word)) ret = word;
        cache[word] = ret;
        return ret;
    }

    solFindAnySubstr(word, dictList) {
        const dict = new Set();
        for(let e of dictList) dict.add(e);
        return this.helperFindAnyUsingSubstr(word, dict, {});
    }

}

class Node {
    constructor(){
        this.word = null;
        this.next = {};
    }
}

const ret = new Solution().solFindAnySubstr("catsanddog", ["cat", "cats", "and", "sand", "dog"]);
console.log(ret);
