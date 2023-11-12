package cutDynamicProgramming;

import java.util.Arrays;

public class cut2 {
    public static void main(String[] args) {
        cake cake = new cake();
        int[] cache = new int[cake.need + 1];
        Arrays.fill(cache, -1);
        System.out.println(cut(cake.prices, cake.need, cache));
    }

    public static int cut(int[] prices, int w, int[] cache) {
        if (w < 0) return 0;
        if (cache[w] != -1) return cache[w]; //如果缓存中有了 就直接返回
        int max = w < prices.length ? prices[w] : 0; //如果有一斤的价格 就先默认为一斤的价格
        for (int dw = 1; dw < w; dw++) {
            int sub = cut(prices, dw, cache) + cut(prices, w - dw, cache);//还是一次求一遍
            if (sub > max) max = sub;
        }
        return max;
    }
}
