package day4;

import day1.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class findPaths {

    public static void findPaths(Node node, List<Node> path, List<List<Node>> paths) {
        if (node == null) return;
        path.add(node); //把当前节点加入到路径中
        //如果左右都是空的 并且 是21的公约数 就加入到结果集中
        if (node.left == null && node.right == null && 21 % node.data == 0) paths.add(path);
        findPaths(node.left, new ArrayList<Node>(path), paths);
        findPaths(node.right, new ArrayList<Node>(path), paths);
    }


    public static void findPaths2(Node node, Stack<Node> stack, List<List<Node>> paths) {
        if (node == null) return;
        stack.push(node);
        if (node.left == null && node.right == null && 21 % node.data == 0) paths.add(new ArrayList<>(stack));
        findPaths2(node.left, stack, paths);
        findPaths2(node.right, stack, paths);
        stack.pop();
    }

    public static List<List<Node>> findPaths3(Node node) {
        var paths = new ArrayList<List<Node>>();
        var count = new HashMap<Node, Integer>();
        var stack = new Stack<Node>();
        count.put(node, 0);
        stack.push(node);
        // 0          1        2
        // 先处理左边 在处理右边 最后处理根节点
        while (true) {
            var top = stack.peek();
            var flag = count.get(top);
            if (flag == 0) {
                if (top.left == null) {
                    count.put(top, 1);//下次直接检查右边
                } else {
                    count.put(top.left, 0);//左边 的节点预备了
                    stack.push(top.left);
                }
            } else if (flag == 1) {
                if (top.right == null) {
                    count.put(top, 2);
                } else {
                    count.put(top.right, 0);
                    stack.push(top.right);
                }
            } else if (flag == 2) {
                if (top.left == null && top.right == null && 21 % top.data == 0) paths.add(new ArrayList<>(stack));
                //推出来
                stack.pop();
                if (stack.empty()) break;
                count.put(stack.peek(), count.get(stack.peek()) + 1);//把上一个的count++
            }
        }
        return paths;
    }

    public static void main(String[] args) {
        Node node = Node.buildTree(new int[]{1, 2, 3, 4, 5, 6, 7}, 0, 6);
        System.out.println(node);
        var paths = new ArrayList<List<Node>>();
        findPaths(node, new ArrayList<Node>(), paths);
        paths.stream().map(x -> x.stream().map(y -> y.data)).forEach(
                x -> {
                    x.forEach(y -> System.out.print(y + " "));
                    System.out.println();
                }
        );

        System.out.println("=====");

        var paths2 = new ArrayList<List<Node>>();
        findPaths2(node, new Stack<Node>(), paths2);
        paths2.stream().map(x -> x.stream().map(y -> y.data)).forEach(
                x -> {
                    x.forEach(y -> System.out.print(y + " "));
                    System.out.println();
                }
        );

        System.out.println("=====");

        var paths3 = findPaths3(node);
        paths3.stream().map(x -> x.stream().map(y -> y.data)).forEach(
                x -> {
                    x.forEach(y -> System.out.print(y + " "));
                    System.out.println();
                }
        );

    }

}
