package attendDynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class Lectures {

    public int[][] lectures = {
            {9, 2, 2},
            {9, 4, 5},
            {11, 3, 4},
            {12, 5, 7},
            {13, 4, 5},
            {17, 3, 4},
            {14, 7, 7},
    };

    public static int attend1(int[][] lectures) {
        HashMap<Integer, Integer> optimal = new HashMap<>();

        for (int[] lect : lectures) {
            int start = lect[0];
            int end = lect[0] + lect[1] - 1;
            int value = lect[2];
            if (optimal.isEmpty())
                optimal.put(end, value);
            else {
                HashMap<Integer, Integer> temp = new HashMap<>(optimal);
                //e is end
                for (Integer e : optimal.keySet()) {
                    if (e >= start) continue;
                    //也就是可以上当前的课
                    int newValue = optimal.get(e) + value;//新的价值
                    //要注意要put的不是newEnd 而是end
                    if (!temp.containsKey(end) || temp.get(end) < newValue)
                        temp.put(end, newValue);
                }
                if (!temp.containsKey(end) || temp.get(end) < value)
                    temp.put(end, value);
                optimal = temp;
            }
        }

        System.out.println(optimal);

        return optimal.values().stream().max(Integer::compareTo).orElse(-1);
    }

    public static Map<Integer, Integer> attend2(int[][] lectures, int i) {
        if (i < 0) return new HashMap<Integer, Integer>();
        int[] lecture = lectures[i];
        int start = lecture[0];
        int end = lecture[0] + lecture[1] - 1;
        int value = lecture[2];
        Map<Integer, Integer> subOptimal = attend2(lectures, i - 1);
        Map<Integer, Integer> optimal = new HashMap<>(subOptimal);
        if (optimal.isEmpty()) {
            //递推到最下面肯定会给一个的
            optimal.put(end, value);
        } else {
            for (Integer e : subOptimal.keySet()) {
                if (e >= start) continue;
                int newValue = subOptimal.get(e) + value;
                if (!optimal.containsKey(end) || optimal.get(end) < newValue)
                    optimal.put(end, newValue);
            }
            if (!optimal.containsKey(end) || optimal.get(end) < value)
                optimal.put(end, value);
        }

        return optimal;
    }

}
