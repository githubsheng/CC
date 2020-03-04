package indeed;
import java.util.*;

//looks like this is a phone screen question, so less important
public class ExpiringMap {

    private Map<String, Entry> m = new HashMap<>();
    private Queue<Entry> q = new LinkedList<>();

    public void put(String key, int val) {
        tryRemoveExpired();
        long time = System.currentTimeMillis();
        Entry e = new Entry();
        e.createdTime = time;
        e.key = key;
        e.val = val;
        q.offer(e);
        m.put(key, e);
    }

    public Integer get(String key) {
        tryRemoveExpired();
        //if entry has already expired,
        //it will be removed from `m` because of `tryRemoveExpired()`
        //therefore if m does not contain entry, either it is never added
        //or it is removed because it expired.
        //if m does contain, we simply return its val
        if(!m.containsKey(key)) return null;
        return m.get(key).val;
    }

    private void tryRemoveExpired(){
        while(!q.isEmpty() && q.peek().isExpired()) {
            Entry e = q.poll();
            m.remove(e.key);
        }
    }

    private static class Entry {
        long createdTime;
        String key;
        int val;
        Entry prev;
        Entry next;

        boolean isExpired(){
            return System.currentTimeMillis() - createdTime > 1000;
        }
    }
}
