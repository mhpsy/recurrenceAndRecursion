package day2;

import day1.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class getPathSum2 {

    // 这个内部类用来存放node和sum 这样只需要一个队列就可以了
    static class Binder {
        Node node;
        int sum;

        public Binder(Node node, int sum) {
            this.node = node;
            this.sum = sum;
        }
    }

    public static List<Integer> getPathSum2Fn(Node root) {
        ArrayList<Integer> sum = new ArrayList<>();
        LinkedList<Binder> queue = new LinkedList<>();
        queue.offer(new Binder(root, 0));

        while (!queue.isEmpty()) {
            Binder curBinder = queue.poll();//获取第一个元素
            if (curBinder == null) continue;
            Node curNode = curBinder.node;
            var curSum = curNode.data + curBinder.sum;
            if (curNode.left == null && curNode.right == null) sum.add(curSum); // 只有没有节点才算上
            else {
                queue.offer(new Binder(curNode.left, curSum));
                queue.offer(new Binder(curNode.right, curSum));
            }
        }

        return sum;

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        var root = Node.buildTree(arr, 0, arr.length - 1);
        getPathSum2Fn(root).forEach(System.out::println);

    }
}
