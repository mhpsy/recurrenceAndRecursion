package getPathSum2;

import java.util.HashMap;
import java.util.Map;

public class demo1 {

    public static void printMap(Map<Integer, Integer> map) {
        map.forEach((k, v) -> System.out.printf("%d: %d\n", k, v));
    }

    public static int getCount(int num) {
        int count = 0;
        while (num != 1) {
            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num = num * 3 + 1;
            }
            count++;
        }
        return count;
    }

    public static int RecurrenceGetCount(int num, int count) {
        if (num == 1) return count;
        count++;
        if (num % 2 == 0) {
            return RecurrenceGetCount(num / 2, count);
        } else {
            return RecurrenceGetCount(num * 3 + 1, count);
        }
    }

    public static Map<Integer, Integer> cache = new HashMap<>();

    public static int RecurrenceGetCountHaveCache(int num, int count) {
        if (num == 1) return count;
        if (cache.containsKey(num)) {
            return count + cache.get(num);
        }
        count++;
        int res;
        if (num % 2 == 0) {
            res = RecurrenceGetCountHaveCache(num / 2, count);
        } else {
            res = RecurrenceGetCountHaveCache(num * 3 + 1, count);
        }
        cache.put(num, res - count);
        return res;
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        HashMap<Integer, Integer> map3 = new HashMap<>();

        int count = 30000;

        long start1 = System.currentTimeMillis();
        for (int i = 2; i < count; i++) {
            map.put(i, getCount(i));
        }
        long end1 = System.currentTimeMillis();
        long start2 = System.currentTimeMillis();
        for (int i = 2; i < count; i++) {
            map2.put(i, RecurrenceGetCount(i, 0));
        }
        long end2 = System.currentTimeMillis();
        long start3 = System.currentTimeMillis();
        for (int i = 2; i < count; i++) {
            map3.put(i, RecurrenceGetCountHaveCache(i, 0));
        }
        long end3 = System.currentTimeMillis();
        System.out.println("time1: " + (end1 - start1));
        System.out.println("time2: " + (end2 - start2));
        System.out.println("time3: " + (end3 - start3));

    }

}
