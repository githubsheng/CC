package references.combination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wangsheng on 5/12/18.
 */
public class CombinationUtility {

    public <T extends Comparable<? super T>> void uniqueCombinationsUsingDfs(List<T> list) {
        if(list.isEmpty()) return;

        List<T> buffer = new ArrayList<>();
        List<List<T>> ret = new ArrayList<>();

        //if elements in list are not unique, we need to sort them, this helps to find out if same elements exists before a certain element.
        Collections.sort(list);

        _dfsAndBt(list, 0, buffer, ret);

    }

    public <T> void _dfsAndBt(List<T> list, int startIdx, List<T> buffer, List<List<T>> ret) {
        int size = list.size();
        if(startIdx >= size) return;

        for(int i = startIdx; i < size; i++) {

            //if element is not unique, but sorted already, then the same element must be the element before current one
            //combination starts with the previous same element is a super set of combinations that starts with the current one
            //so we can skip the current one

            int prev;
            if((prev = i - 1) >= 0 && list.get(prev).equals(list.get(i))) continue;

            T elm = list.get(i);
            buffer.add(elm);

            if(System.currentTimeMillis() % 2 == 0) {
                //if certain condition meets
                //do something about temp / ret

                List<T> temp = new ArrayList<>();
                temp.addAll(buffer);
                ret.add(temp);
            }

            _dfsAndBt(list, i + 1, buffer, ret);
            buffer.remove(buffer.size() - 1);
        }
    }

}
