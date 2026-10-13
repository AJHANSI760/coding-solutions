
import java.util.TreeMap;
import java.util.Map;

class SummaryRanges {
    private final TreeMap<Integer, Integer> intervals;

    public SummaryRanges() {
        intervals = new TreeMap<>();
    }

    public void addNum(int value) {
        if (intervals.isEmpty()) {
            intervals.put(value, value);
            return;
        }

        Map.Entry<Integer, Integer> floor =
                intervals.floorEntry(value);

        if (floor != null && floor.getValue() >= value) {
            return;
        }

        Map.Entry<Integer, Integer> lower =
                intervals.lowerEntry(value);

        Map.Entry<Integer, Integer> higher =
                intervals.ceilingEntry(value);

        boolean mergeLower =
                lower != null && lower.getValue() + 1 == value;

        boolean mergeHigher =
                higher != null && higher.getKey() - 1 == value;

        if (mergeLower && mergeHigher) {
            intervals.put(lower.getKey(), higher.getValue());
            intervals.remove(higher.getKey());
        } else if (mergeLower) {
            intervals.put(lower.getKey(), value);
        } else if (mergeHigher) {
            intervals.remove(higher.getKey());
            intervals.put(value, higher.getValue());
        } else {
            intervals.put(value, value);
        }
    }

    public int[][] getIntervals() {
        int[][] result = new int[intervals.size()][2];
        int i = 0;

        for (Map.Entry<Integer, Integer> entry :
                intervals.entrySet()) {
            result[i][0] = entry.getKey();
            result[i][1] = entry.getValue();
            i++;
        }

        return result;
    }
}