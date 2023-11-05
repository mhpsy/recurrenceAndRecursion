package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class go {
    public static void main(String[] args) {
        long s1 = System.currentTimeMillis();
        System.out.println(change1(2100));
        long e1 = System.currentTimeMillis();
        System.out.println("time:" + (e1 - s1));
        long s2 = System.currentTimeMillis();
        System.out.println(change2(new int[]{2, 3, 5}, 2100));
        long e2 = System.currentTimeMillis();
        System.out.println("time:" + (e2 - s2));
    }

    public static int change1(int num) {
        int minCount = -1, time = 0;
        for (int c5 = 0; c5 <= num / 5; c5++)
            for (int c3 = 0; c3 <= num / 3; c3++)
                for (int c2 = 0; c2 <= num / 2; c2++) {
                    time++;
                    if (c5 * 5 + c3 * 3 + c2 * 2 == num) {
                        int count = c5 + c3 + c2;
                        if (minCount == -1 || count < minCount) minCount = count;
                    }
                }
        System.out.println(time);
        return minCount;
    }


    public static int change2(int[] coins, int n) {
        if (n == 0) return 0;
        var optimal = new int[n + 1];
        Arrays.fill(optimal, -1);
        for (int coin : coins) optimal[coin] = 1;//初始化 我们兑现2，3，5这样的（coins的值）时候最优解一定是1
        for (int i = 1; i <= n; i++) { //从一开始数
            if (optimal[i] != -1) continue; //这些都是已经有解的了
            for (int coin : coins) {
                int needCoin = i - coin;
                if (needCoin < 0 || optimal[needCoin] == -1) {
                    // 如果i-coin<0 说明这个coin不可用
                    // 如果optimal[i-coin] == -1  就说明这个coin不可用 比如 i是4 coin是3 那么optimal[1]就是-1 因为1不可用 无法处理
                    continue;
                }
                if (optimal[i] == -1 || optimal[i] > optimal[needCoin] + 1) {
//                    System.out.println(coin + " " + i + " " + Arrays.toString(optimal));
                    optimal[i] = optimal[needCoin] + 1;
                    //如果optimal[i] == -1 说明这个coin是第一个可用的coin 所以要等于可用icon的位置的值+1
                    //如果optimal[i] > optimal[needCoin] + 1 说明这个coin的值比之前的要小 所以要更新 为最小的可用位置的值+1
                }
            }
        }

        //最后的结果就是optimal[n]
        return optimal[n];
    }


}
