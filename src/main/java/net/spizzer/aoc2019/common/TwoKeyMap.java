package net.spizzer.aoc2019.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TwoKeyMap<A, B, V> {
    private final Map<A, Map<B, V>> map;

    public TwoKeyMap() {
        map = new HashMap<>();
    }

    public V get(A a, B b) {
        return map.computeIfAbsent(a, a1 -> new HashMap<>()).get(b);
    }

    public Collection<V> get(A a) {
        return map.computeIfAbsent(a, a1 -> new HashMap<>()).values();
    }

    public Set<B> getKeys(A a) {
        return map.computeIfAbsent(a, a1 -> new HashMap<>()).keySet();
    }

    public void put(A a, B b, V v) {
        map.computeIfAbsent(a, a1 -> new HashMap<>()).put(b, v);
    }
}
