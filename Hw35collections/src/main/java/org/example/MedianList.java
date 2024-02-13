package org.example;

import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

public class MedianList<T extends Number> {
    private CopyOnWriteArrayList<T> list;

    public MedianList() {
        this.list = new CopyOnWriteArrayList<>();
    }

    public int size() {
        return list.size();
    }

    public void add(T item) {
        list.add(item);
    }

    public void remove(T item) {
        list.remove(item);
    }

    public double getMedian() {
        if (list.isEmpty()) {
            return Double.NaN;
        }
        CopyOnWriteArrayList sortedList = new CopyOnWriteArrayList<>(list);
        sortedList.sort(Comparator.comparingDouble(Number::doubleValue));
        int size = sortedList.size();
        if (size % 2 == 0) {
            T mid1 = (T) sortedList.get(size / 2 - 1);
            T mid2 = (T) sortedList.get(size / 2);
            return (mid1.doubleValue() + mid2.doubleValue()) / 2;
        } else {
            var res = sortedList.get(size / 2);
            return Double.parseDouble(res.toString());
        }
    }
}
