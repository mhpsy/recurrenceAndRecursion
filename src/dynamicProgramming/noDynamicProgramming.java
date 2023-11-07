package dynamicProgramming;

import java.util.Arrays;

public class noDynamicProgramming {

    /**
     * 需要注意的是这种严格来说不是动态规划 (虽然有点鄙视链的嫌疑)
     * 动态 规划 只有主动的把所有子问题都搞定了才算是的 这种不算是
     * 因为它只是碰巧解决了所有的 问题
     */

    public static int change(int num, int[] coins) {
        int[] optimal = new int[num + 1];
        Arrays.fill(optimal, -1);
        optimal[0] = 0; //让0的最优解为0 并且for从0开始 那么就可以通过 这个0就让 optimal[coin] = 1了
        for (int i = 0; i <= num; i++) {
            if (optimal[i] == -1) continue;//这种正着推的方式 一定要先判断这个值是否有解
            for (int coin : coins) {
                int to = i + coin;//根据当前的钱推断换取需要的钱
                if (to <= num && (optimal[to] == -1 || optimal[to] > optimal[i] + 1))//如果这个值已经有解了 并且这个解比之前的解要小
                    optimal[to] = optimal[i] + 1;//更新这个值
            }
        }
        return optimal[num];
    }

    public static int change2(int num, int[] coins) {
        int[] optimal = new int[num + 1];
        Arrays.fill(optimal, -1);
        for (int coin : coins) optimal[coin] = 1;
        for (int i = 1; i <= num; i++) {//因为一开始给了初始值 所以就要从1开始了
            for (int coin : coins) {
                int to = i + coin;
                if (to <= num && (optimal[to] == -1 || optimal[to] > optimal[i] + 1))
                    optimal[to] = optimal[i] + 1;
            }
        }

        return optimal[num];
    }

    public static void main(String[] args) {
        int[] coins = {2, 3, 5};
        System.out.println(change(2100, coins));
        System.out.println(change2(2100, coins));
    }

}
