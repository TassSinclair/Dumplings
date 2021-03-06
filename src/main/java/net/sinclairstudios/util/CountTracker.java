package net.sinclairstudios.util;

import java.util.Collection;
import java.util.HashSet;

public class CountTracker {
    private final Collection<CountTrackerListener> listeners = new HashSet<CountTrackerListener>();
    private int count;

    public CountTracker(int count) {
        this.count = count;
    }

    public void add(int add) {
        this.count += add;
        for (CountTrackerListener listener : listeners) {
            listener.onCountTrackerAdd(add, count);
        }
    }

    public void addOnAddListener(CountTrackerListener listener) {
        listeners.add(listener);
        listener.onCountTrackerAdd(0, count);
    }
}
