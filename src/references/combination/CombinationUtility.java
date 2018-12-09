package references.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("Duplicates")
public class CombinationUtility {

    //for general objects, replace <T> with something else, say <String>
    public <T extends Comparable<? super T>> void uniqueCombinationsUsingDfs(List<T> list) {
        if(list.isEmpty()) return;

        List<T> buffer = new ArrayList<>();

        //if elements in list are not unique, we need to sort them, this helps to find out if same elements exists before a certain element.
        //todo: consider
        Collections.sort(list);

        _dfsAndBt(list, 0, buffer);

    }

    private <T> void _dfsAndBt(List<T> list, int startIdx, List<T> buffer) {
        //early termination. todo:

        int size = list.size();
        if(startIdx >= size) return;

        for(int i = startIdx; i < size; i++) {

            /*
            if element is not unique, but sorted already, then the same element must be the element before current one
            combination starts with the previous same element is a super set of combinations that starts with the current one
            so we can skip the current one. todo: consider.
             */
            int prev;
            //todo: equals may be replace by ==
            if((prev = i - 1) >= 0 && list.get(prev).equals(list.get(i))) continue;

            T elm = list.get(i);
            //apply elem
            buffer.add(elm);

            _dfsAndBt(list, i + 1, buffer);

            //back tracking, may need to do in another way. todo:
            buffer.remove(buffer.size() - 1);

            //go on apply other elem
        }
    }

    //same as _dfsAndBt, but in this version, elm can be applied multiple times.
    private <T> void _dfsAndBtMulti(List<T> list, int startIdx, List<T> buffer) {
        //early termination. todo:

        int size = list.size();
        if(startIdx >= size) return;

        for(int i = startIdx; i < size; i++) {

            int prev;
            //todo:
            if((prev = i - 1) >= 0 && list.get(prev).equals(list.get(i))) continue;

            T elm = list.get(i);

            int originalBufferSize = buffer.size();

            while(someConditions()) {
                //apply elm multiple times.
                buffer.add(elm);

                //depending on how back tracking is done, buffer here may be modified, in this case, pass in a copy. todo:
                _dfsAndBtMulti(list, i + 1, buffer);// may not needed if above if statement works. todo:
            }

            //back track, may need modifications. todo:
            while(buffer.size() > originalBufferSize) {
                //remove the last element
                buffer.remove(buffer.size() - 1);
            }

            //go on apply other elem
        }
    }

    /**
     * convert int[] to array
     * @param arr array to convert
     * @return a list
     */
    private List<Integer> convertIntArrayToList(int[] arr){
        List<Integer> list = new ArrayList<>(arr.length);
        for (int i = 0, l = arr.length; i < l; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    /**
     * initialize a list. The list's size is `size` and all elements' values are `initVal`
     * @param size size of the list
     * @param initVal initial value
     * @param <T> type
     * @return a list of Array that are type T
     */
    private <T> List<T> initListWithValues(int size, T initVal) {
        List<T> temp = new ArrayList<T>();
        temp.addAll(Collections.nCopies(size, initVal));
        return temp;
    }


    private boolean someConditions(){
        return System.currentTimeMillis() % 2 == 0;
    }

}
