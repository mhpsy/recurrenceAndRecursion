package chooseDynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Invitation {
    int hour;
    int reward;

    public Invitation(int hour, int reward) {
        this.hour = hour;
        this.reward = reward;
    }


    /**
     * 有一个很难解释的东西
     * 就是只需要跟之前的最优解比较 而不是 需要跟之前的所有解比较 书上有一个例子
     *
     * @param invitations
     * @param limit
     * @return
     */
    public static int choose(Invitation[] invitations, int limit) {
        HashMap<Integer, Integer> optimal = new HashMap<>();
        for (Invitation inv : invitations) {
            HashMap<Integer, Integer> temp = new HashMap<>(optimal);//浅拷贝一次
            //之所以需要浅拷贝一次是为了防止一边更改map一边遍历
            if (optimal.isEmpty()) {
                temp.put(inv.hour, inv.reward); //如果是第一次 那么就直接放进去
            } else {
                for (Integer hour : optimal.keySet()) {
                    // 这里是判断已经有的最优解是否可以和当前的邀请碰撞
                    //也就是遍历已经有的最优解 如果当前的邀请和已经有的最优解碰撞了
                    //那么就要判断是否需要更新
                    int newHour = hour + inv.hour;
                    int newReward = optimal.get(hour) + inv.reward;
                    if (!temp.containsKey(newHour) || temp.get(newHour) < newReward)
                        temp.put(newHour, newReward); //碰撞取优
                }

                //如果temp中还没有这个hour 或者更这个hour的reward更大 那么就放进去
                if (!temp.containsKey(inv.hour) || temp.get(inv.hour) < inv.reward)
                    temp.put(inv.hour, inv.reward); //碰撞取优

            }

            optimal = temp;
        }

        System.out.println(optimal);

        int max = 0;
        for (Integer hour : optimal.keySet())
            if (hour <= limit && max < optimal.get(hour)) max = optimal.get(hour);

        return max;
    }


    public static int choose2(Invitation[] invitations, int limit) {
        HashMap<Integer, Integer> optimal = new HashMap<>();
        for (Invitation invitation : invitations) {
            HashMap<Integer, Integer> temp = new HashMap<>(optimal);
            if (optimal.isEmpty()) {
                temp.put(invitation.hour, invitation.reward);
            } else {
                for (Integer hour : optimal.keySet()) {
                    int newHour = hour + invitation.hour;
                    if (newHour > limit) continue;
                    //这里要用optimal中的hour来判断 因为optimal中的hour是之前的最优解
                    //如果用temp中的hour来判断 那么就是用当前的最优解来判断了
                    int newReward = optimal.get(hour) + invitation.reward;
                    if (!temp.containsKey(newHour) || temp.get(newHour) < newReward)
                        temp.put(newHour, newReward);
                }
                if (!temp.containsKey(invitation.hour) || temp.get(invitation.hour) < invitation.reward)
                    temp.put(invitation.hour, invitation.reward);
            }

            optimal = temp;
        }

        System.out.println(optimal);

        int max = 0;
        for (Integer hour : optimal.keySet()) {
            if (max < optimal.get(hour)) max = optimal.get(hour);
        }

        return max;
    }


    public static int choose3(Invitation[] invitations, int limit) {
        int[] optimal = new int[limit + 1];

        for (Invitation inv : invitations) {
            int[] temp = optimal.clone();

            for (int hour = 0; hour <= limit; hour++) {
                int newHour = hour + inv.hour;
                if (newHour > limit) continue;
                int newReward = optimal[hour] + inv.reward;
                if (temp[newHour] < newReward) temp[newHour] = newReward;
            }

            if (inv.hour < limit && temp[inv.hour] < inv.reward) temp[inv.hour] = inv.reward;

            optimal = temp;

        }

        return optimal[limit];

    }


    //为什么要每次都克隆出来一份呢！！！
    public static int choose4(Invitation[] invitations, int limit) {
        // hour >= invitations[invIndex].hour
        // 一定要理解这个 这个的意思实际就是
        // 检查当前考虑的时间(for循环到了的值)是否大于或等于当前邀请所需要的时间 也就代表这个是可以试一试的
        // 如果当前考虑的时间 小于当前邀请所需要的时间 那么就不可能选择这个邀请
        int[][] optimal = new int[invitations.length][limit + 1];
        //row是inv column是hour
        for (int invIndex = 0; invIndex < invitations.length; invIndex++) {
            for (int hour = 0; hour <= limit; hour++) {
                if (invIndex == 0) {
                    if (hour >= invitations[invIndex].hour) optimal[invIndex][hour] = invitations[invIndex].reward;
                } else {
                    if (hour >= invitations[invIndex].hour) {
                        //optimal[invIndex - 1][hour] 就是上一个的最优解
                        //optimal[invIndex - 1][hour - invitations[invIndex].hour] + invitations[invIndex].reward
                        //分开解释[hour - invitations[invIndex].hour] 这个是 去上一个最优解中的哪一个里面去获取
                        //invitations[invIndex].reward 这个是当前的邀请的reward
                        //其实就是先获取上一个最优解中的减去当前邀请所需要的时间的那个最优解 然后 加上当前的邀请的reward
                        optimal[invIndex][hour] = Math.max(
                                optimal[invIndex - 1][hour],
                                optimal[invIndex - 1][hour - invitations[invIndex].hour] + invitations[invIndex].reward
                        );
                    } else {
                        //如果当前考虑的时间 小于当前邀请所需要的时间 那么就不可能选择这个邀请
                        //所以直接用上一个的最优解就行了
                        optimal[invIndex][hour] = optimal[invIndex - 1][hour];
                    }
                }
            }
        }
        return optimal[invitations.length - 1][limit];
    }

    public static Map<Integer, Integer> choose5(Invitation[] invs, int i, int limit) {
        if (i >= invs.length) return new HashMap<>();//如果已经没有邀请了 那么就返回一个空的map
        Map<Integer, Integer> subOptimal = choose5(invs, i + 1, limit);//先获取下一个的最优解
        Map<Integer, Integer> optimal = new HashMap<>(subOptimal);//浅拷贝一份
        if (optimal.isEmpty()) {
            //如果是第一次 并且当前的邀请的时间小于等于limit 那么就放进去 总是就是初始化第一个
            if (invs[i].hour <= limit) optimal.put(invs[i].hour, invs[i].reward);
        } else {
            for (Integer h : subOptimal.keySet()) {
                int newHour = h + invs[i].hour;
                int newReward = subOptimal.get(h) + invs[i].reward;
                //如果新的时间小于等于limit 并且新的reward大于之前的reward 那么就放进去
                if (newHour <= limit && (!optimal.containsKey(newHour) || optimal.get(newHour) < newReward))
                    optimal.put(newHour, newReward);
            }

            //如果没有这个hour 或者这个hour的reward更大 那么就放进去
            if (!optimal.containsKey(invs[i].hour)
                    || invs[i].reward > optimal.get((invs[i].hour)))
                optimal.put(invs[i].hour, invs[i].reward);
        }
        return optimal;
    }

    public static int choose6(Invitation[] invs, int limit) {
        int[][] optimal = new int[invs.length][limit + 1];
        if (invs[0].hour < limit)
            //因为第一个不需要进行碰撞 所以直接赋值就行了
            optimal[0][invs[0].hour] = invs[0].reward;
        for (int invIndex = 1; invIndex < invs.length; invIndex++) {
            //要把上一个的最优解复制过来一套
            System.arraycopy(optimal[invIndex - 1], 0, optimal[invIndex], 0, limit + 1);
            for (int hour = 1; hour <= limit; hour++) {
                int newHour = hour + invs[invIndex].hour;
                int newReward = optimal[invIndex - 1][hour] + invs[invIndex].reward;
                //数组的好处就是不用判断是否存在了
                if (newHour <= limit && optimal[invIndex][newHour] < newReward) {
                    optimal[invIndex][newHour] = newReward;
                }
            }
            //判断当前的邀请是否可以直接放进去
            if (invs[invIndex].hour <= limit && invs[invIndex].reward > optimal[invIndex][invs[invIndex].hour]) {
                optimal[invIndex][invs[invIndex].hour] = invs[invIndex].reward;
            }
        }

        System.out.println(Arrays.deepToString(optimal));

        return Arrays.stream(optimal[invs.length - 1]).max().orElse(-1);
    }
}
