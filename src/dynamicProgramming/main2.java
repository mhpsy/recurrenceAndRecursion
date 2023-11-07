package dynamicProgramming;

import dynamicProgramming.pojo.ChangeRes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class main2 {

    public static ChangeRes change(int[] coins, int num) {
        int[] optimal = new int[num + 1];//先创建一个数组 用来存储全部的最优解
        int[] useCoin = new int[num + 1];//用来存储使用的硬币
        ArrayList<Integer> res = new ArrayList<>();
        Arrays.fill(optimal, -1);//先全部填充为-1 代表没有解
        for (int coin : coins) {
            optimal[coin] = 1;//初始化 我们兑现2，3，5这样的（coins的值）时候最优解一定是1
            useCoin[coin] = coin;
        }
        for (int i = 1; i <= num; i++) { //要从1开始 到num
            for (int coin : coins) {
                int uesCoin = i - coin;//需要的coin
                if (0 > uesCoin || optimal[uesCoin] == -1) continue;
                //要替换的值一定要小于这个值 不然就不替换了 因为要替换为需要的硬币数量 +1 所以就要小于
                if (optimal[i] == -1 || optimal[i] > optimal[uesCoin] + 1) {
                    optimal[i] = optimal[uesCoin] + 1;
                    useCoin[i] = coin;
                }
            }
        }
        int i = num;
        while (i > 0) {
            int useCoinNum = useCoin[i];
            res.add(useCoinNum);
            i -= useCoinNum;
        }
        return new ChangeRes(res, optimal[num]);
    }

    public static void main(String[] args) {
        int[] coins = {2, 3, 5};
        System.out.println(change(coins, 2100));
    }


}
