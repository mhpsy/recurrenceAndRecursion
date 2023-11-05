package day3;

public class test {

    public static void main(String[] args) throws InterruptedException {

        int num = 113383;

        long count = 0;
        while (num != 1) {
            System.out.println(num);
            if (num < 0) Thread.sleep(1000);
            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num = num * 3 + 1;
            }
            count++;
        }

        System.out.println(count);
    }
}
