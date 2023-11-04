package day1;

public class sumToEnd {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5}; // 15
        int sum1 = sum(arr), sum2 = sum2(arr, arr.length - 1);
        System.out.println(sum1 + " " + sum2);
    }

    public static int sum(int[] arr) {
        int sum = 0;
        for (int j : arr) sum += j;
        return sum;
    }

    public static int sum2(int[] arr, int index) {
        if (index == 0) return arr[index];
        return arr[index] + sum2(arr, index - 1);
    }
}
