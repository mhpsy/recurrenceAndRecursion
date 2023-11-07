package dynamicProgramming;

import java.util.Arrays;

public class noDynamicProgramming {

    public static int change(int num, int[] coins) {
        int[] optimal = new int[num + 1];
        Arrays.fill(optimal, -1);
        optimal[0] = 0;
        for (int i = 0; i <= num; i++) {
            if (optimal[i] == -1) continue;//这种正着推的方式 一定要先判断这个值是否有解
            for (int coin : coins) {
                int to = i + coin;//根据当前的钱推断换取需要的钱
                if (to < num && (optimal[to] == -1 || optimal[to] > optimal[i] + 1))//如果这个值已经有解了 并且这个解比之前的解要小
                    optimal[to] = optimal[i] + 1;//更新这个值
            }
        }
        System.out.println(Arrays.toString(optimal));
        return optimal[num];
    }

    public static void main(String[] args) {
        int[] coins = {2, 3, 5};
        System.out.println(change(2100, coins));
    }

}
