package com.cn.common.util.sort;

import com.cn.common.util.exception.ApplicationException;

import java.util.*;

public class SetMapSort<V extends SetMapSort.Sortable> {

    private Set<V> set;

    private Map<Object, List<V>> map = new HashMap<>();

    public SetMapSort(Collection<V> source, Comparator<V> comparable) {
        if (null == source) throw new ApplicationException("source collection is null");

        this.set = new TreeSet<V>(comparable);
        Iterator<V> iterator = source.iterator();
        while (iterator.hasNext()) {
            V next = iterator.next();
            if (this.set.contains(next)) {
                List<V> vs = map.get(next.getSortKey());
                if (null == vs) {
                    vs = new ArrayList<>();
                    this.map.put(next.getSortKey(), vs);
                }
                vs.add(next);
            } else {
                this.set.add(next);
            }
        }
    }

    public void consumer(CFunction function) {
        Iterator<V> iterator = this.set.iterator();
        while (iterator.hasNext()) {
            V next = iterator.next();
            function.call(next);
            List<V> list = map.get(next.getSortKey());
            if (null != list) {
                list.forEach(o -> {
                    function.call(o);
                });
            }
        }
    }

    public void transfor(Collection<V> c) {
        if (null == c) throw new ApplicationException("target collection is null");
        Iterator<V> iterator = this.set.iterator();
        while (iterator.hasNext()) {
            V next = iterator.next();
            c.add(next);
            List<V> list = map.get(next.getSortKey());
            if (null != list) {
                c.addAll(list);
            }
        }
    }


    interface Sortable {

        Object getSortKey();
    }

    interface CFunction<U> {
        void call(U u);
    }
}
