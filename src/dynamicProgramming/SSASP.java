package dynamicProgramming;

import dynamicProgramming.pojo.MinCountTo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SSASP {

    public static int change(int[] coins, int n) {
        PriorityQueue<MinCountTo> pq = new PriorityQueue<>();
        pq.offer(new MinCountTo(0, 0));
        while (!pq.isEmpty()) {
            MinCountTo optimal = pq.poll();
            if (optimal.to == n) return optimal.count;
            for (int coin : coins) {
                if (optimal.to + coin <= n) {
//                    System.out.println(optimal.to + coin);
                    pq.offer(new MinCountTo(optimal.count + 1, optimal.to + coin));
                }
            }
        }
        return -1;
    }

    public static int change2(int[] coins, int n) {
        int[] optimal = new int[n + 1];
        Arrays.fill(optimal, -1);
        optimal[0] = 0;
        LinkedList<Integer> q = new LinkedList<>();
        q.offer(0);
        while (!q.isEmpty()) {
            int to = q.poll();
            for (int coin : coins) {
                int newTo = to + coin;
                if (newTo > n) continue;
                q.offer(newTo);
                if (optimal[newTo] == -1 || optimal[newTo] > optimal[to] + 1) {
                    optimal[newTo] = optimal[to] + 1;
                }
            }
        }
        return optimal[n];
    }

    public static void main(String[] args) {
        System.out.println(change(new int[]{2, 3, 5}, 42));
        System.out.println(change2(new int[]{2, 3, 5}, 42));
    }

}
