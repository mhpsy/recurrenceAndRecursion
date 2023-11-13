package attendDynamicProgramming;

public class attend {
    public static void main(String[] args) {
        Lectures l = new Lectures();

        System.out.println(Lectures.attend1(l.lectures));
        System.out.println(Lectures.attend2(l.lectures, l.lectures.length - 1));
    }
}
