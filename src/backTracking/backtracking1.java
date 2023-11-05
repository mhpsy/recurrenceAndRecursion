package backTracking;

import java.util.Stack;

public class backtracking1 {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        System.out.println(stack.size());
        System.out.println("=======");
        action(98, 100, stack);
        System.out.println("=======");
        System.out.println(stack.size());
    }

    public static void action(int start, int end, Stack<Integer> stack) {
        //直接加入进去
        stack.push(start);
        System.out.println(stack.size());
        if (start < end) action(start + 1, end, stack);//相当于递归的push
        stack.pop();//相当于递归的pop
        System.out.println(stack.size());
    }

}
