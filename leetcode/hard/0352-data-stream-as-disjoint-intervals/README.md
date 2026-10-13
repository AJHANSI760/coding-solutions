# Q3. Data Stream as Disjoint Intervals

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given a data stream input of non-negative integers `a1, a2,..., an`, summarize the numbers seen so far as a list of disjoint intervals.

Implement the `SummaryRanges` class:

- SummaryRanges() Initializes the object with an empty stream.
- void addNum(int value) Adds the integer value to the stream.
- int[][] getIntervals() Returns a summary of the integers in the stream currently as a list of disjoint intervals [starti, endi]. The answer should be sorted by starti.

 

 **Example 1:** 

```
Input
["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
[[], [1], [], [3], [], [7], [], [2], [], [6], []]
Output
[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]

Explanation
SummaryRanges summaryRanges = new SummaryRanges();
summaryRanges.addNum(1);      // arr = [1]
summaryRanges.getIntervals(); // return [[1, 1]]
summaryRanges.addNum(3);      // arr = [1, 3]
summaryRanges.getIntervals(); // return [[1, 1], [3, 3]]
summaryRanges.addNum(7);      // arr = [1, 3, 7]
summaryRanges.getIntervals(); // return [[1, 1], [3, 3], [7, 7]]
summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
summaryRanges.getIntervals(); // return [[1, 3], [7, 7]]
summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
summaryRanges.getIntervals(); // return [[1, 3], [6, 7]]

```

 

 **Constraints:** 

- 0 <= value <= 104
- At most 3 * 104 calls will be made to addNum and getIntervals.
- At most 102 calls will be made to getIntervals.

 

 **Follow up:**  What if there are lots of merges and the number of disjoint intervals is small compared to the size of the data stream?

## Solution

**Language:** Java  
**Runtime:** 5 ms  
**Memory:** 42.5 MB  
**Submitted:** 2026-10-13T13:40:22.479Z  

```java

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
```

---

[View on LeetCode](https://leetcode.com/problems/data-stream-as-disjoint-intervals/)