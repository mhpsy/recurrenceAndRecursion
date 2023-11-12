package cutDynamicProgramming;

public class cut1 {

    public static int cut2(int[] prices, int n) {
        int[] optimal = new int[n + 1];
        for (int i = 1; i <= n; i++) {//直接遍历每一个价格
            if (i < prices.length) optimal[i] = prices[i];//如果价格是有一斤的几个 那么先默认给上一斤的价格
            for (int j = 1; j < i; j++) {
                //依次遍历每一个可能的切法 比如 6 就要 依次遍历 1-5 2-4 3-3
                int sum = optimal[j] + optimal[i - j];
                //如果 optimal[i] 是小于 optimal[j] + optimal[i - j] 那么就更新
                if (sum > optimal[i]) optimal[i] = sum;
            }
        }
        return optimal[n];
    }

    public static void main(String[] args) {
        cake cake = new cake();
        System.out.println(cut2(cake.prices, cake.need));
    }

}
