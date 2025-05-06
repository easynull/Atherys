package com.easynull.rebyssal.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LimitedList<T> extends ArrayList<T> {
    final int maxSize;

    public LimitedList(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(T e) {
        if (size() >= maxSize) {
            return false;
        }
        return super.add(e);
    }

    @Override
    public void add(int index, T element) {
        if (size() >= maxSize) {
            throw new IllegalStateException("List is full");
        }
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (size() + c.size() > maxSize) {
            int canAdd = maxSize - size();
            List<? extends T> subList = new ArrayList<>(c).subList(0, canAdd);
            return super.addAll(subList);
        }
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (size() + c.size() > maxSize) {
            int canAdd = maxSize - size();
            List<? extends T> subList = new ArrayList<>(c).subList(0, canAdd);
            return super.addAll(index, subList);
        }
        return super.addAll(index, c);
    }

    public boolean isMax(){
        return maxSize >= size();
    }
}
