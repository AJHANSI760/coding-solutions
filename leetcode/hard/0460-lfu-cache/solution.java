
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

class LFUCache {
    private final int capacity;
    private int minFreq;

    private final Map<Integer, Integer> values;
    private final Map<Integer, Integer> frequencies;
    private final Map<Integer, LinkedHashSet<Integer>> freqKeys;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;

        values = new HashMap<>();
        frequencies = new HashMap<>();
        freqKeys = new HashMap<>();
    }

    public int get(int key) {
        if (!values.containsKey(key)) {
            return -1;
        }

        increaseFrequency(key);
        return values.get(key);
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (values.containsKey(key)) {
            values.put(key, value);
            increaseFrequency(key);
            return;
        }

        if (values.size() >= capacity) {
            LinkedHashSet<Integer> keys = freqKeys.get(minFreq);
            int lruKey = keys.iterator().next();

            keys.remove(lruKey);
            if (keys.isEmpty()) {
                freqKeys.remove(minFreq);
            }

            values.remove(lruKey);
            frequencies.remove(lruKey);
        }

        values.put(key, value);
        frequencies.put(key, 1);
        freqKeys.computeIfAbsent(1, k -> new LinkedHashSet<>())
                .add(key);

        minFreq = 1;
    }

    private void increaseFrequency(int key) {
        int freq = frequencies.get(key);

        LinkedHashSet<Integer> keys = freqKeys.get(freq);
        keys.remove(key);

        if (keys.isEmpty()) {
            freqKeys.remove(freq);

            if (minFreq == freq) {
                minFreq++;
            }
        }

        int newFreq = freq + 1;
        frequencies.put(key, newFreq);

        freqKeys.computeIfAbsent(newFreq, k -> new LinkedHashSet<>())
                .add(key);
    }
}
