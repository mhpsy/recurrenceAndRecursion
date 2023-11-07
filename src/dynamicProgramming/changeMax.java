package dynamicProgramming;

import java.util.Arrays;
import java.util.Stack;

public class changeMax {

    public static int change(int[] coins, int n) {
        int[] count = new int[n + 1];//用来记录处理了 多少问题 也就是当前该弄第几个硬币了
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        Stack<Integer> stack = new Stack<>();
        stack.push(n);
        while (true) {
            var top = stack.peek();
            if (count[top] == coins.length) {
                //处理这一个
                int sub = stack.pop();//sub 其实 就是 top
                if (stack.isEmpty()) break;
                top = stack.peek();//获取上一个
                if (cache[sub] != -1 && (cache[top] == -1 || cache[top] > cache[sub] + 1)) {
                    //子问题有解 并且 当前的解没有解 或者 当前的解大于子问题的解
                    cache[top] = cache[sub] + 1;
                }
                count[top]++;
            } else {
                //要获取这个的缓存和其他的东西
                int sub = top - coins[count[top]];//需要的硬币
                if (sub < 0 || (count[sub] == coins.length - 1 && cache[sub] == -1)) { //无解的问题
                    count[top]++;
                } else if (sub == 0) {
                    cache[top] = 1;//1 是可能的解
                    count[top]++;
                } else if (cache[sub] != -1) {//存在子解
                    if (cache[top] == -1 || cache[top] > cache[sub] + 1)
                        cache[top] = cache[sub] + 1;
                    count[top]++;
                } else {
                    stack.push(sub);//要进入上面的if了
                }
            }
        }

        return cache[n];
    }

    public static void main(String[] args) {
        int[] coins = {2, 3, 5};
        System.out.println(change(coins, 2100));
    }

}
