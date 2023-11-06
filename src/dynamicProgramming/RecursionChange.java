package dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * 这种递归的方法 一定会存在爆栈的情况的 无解
 */

public class RecursionChange {

    public static Map cache = new HashMap<Integer, Integer>();

    public static int change(int[] coins, int num) {
        if (num < 0) return -1;
        if (cache.containsKey(num)) return (int) cache.get(num);
        int optimal = -1;
        for (int coin : coins) {
            if (num == coin) return 1;//如果是硬币的面值 则返回1
            int subOptimal = change(coins, num - coin);
            if (subOptimal == -1) continue;//如果是无效的值就continue 直接去试下一个硬币
            //只给赋值正确的值
            if (optimal == -1 || optimal > subOptimal + 1) {
                optimal = subOptimal + 1;
            }
        }
        cache.put(num, optimal);
        return optimal;
    }

    /**
     * 我试图用二分让他不爆栈 但是我想的不对 实际没有任何意义
     * 可以直接删除的
     *
     * @param coins
     * @param num
     * @return
     */
    public static int change3(int[] coins, int num) {
        if (num < 0) return -1;
        if (cache.containsKey(num)) return (int) cache.get(num);
        int optimal = -1;
        for (int coin : coins) {
            if (num == coin) return 1;//如果是硬币的面值 则返回1
            int useCoin = num - coin, subOptimalOdd = -1, subOptimalEven = -1;
            if (useCoin % 2 == 0) {
                subOptimalOdd = change(coins, num - coin);
            } else {
                subOptimalEven = change(coins, num - coin);
            }
            int subOptimal = Math.min(subOptimalOdd, subOptimalEven);

            if (subOptimal == -1) continue;//如果是无效的值就continue 直接去试下一个硬币
            //只给赋值正确的值
            if (optimal == -1 || optimal > subOptimal + 1) {
                optimal = subOptimal + 1;
            }
        }
        cache.put(num, optimal);
        return optimal;
    }


//    public static int change2(int[] coins, int num) {
//        int optimal = -1;
//        if (num < 0) return optimal;
//        for (int coin : coins) {
//            //先考虑最小的情况 如果就是硬币 返回1
//            if (coin == num) return 1;
//            int subOptimal = change2(coins, num - coin);
//            if (subOptimal == -1) continue;
//            if (optimal == -1 || optimal > subOptimal + 1) optimal = subOptimal + 1;
//        }
//        System.out.println(optimal);
//        return optimal;
//    }


    public static void main(String[] args) {
        int[] coins = {2, 3, 5};
        System.out.println(change(coins, 2100));
//        System.out.println(change2(coins, 210));
    }

}
