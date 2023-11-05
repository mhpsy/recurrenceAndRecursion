package day3;

import day1.Node;

import java.util.List;
import java.util.Stack;

public class getPathSumsByStack {

    public static Node addChildren(Node node, int val, boolean isLeft) {
        var child = new Node(val);
        return isLeft ? (node.left = child) : (node.right = child);
    }

    public static Node buildTree(int n) {
        Node root = new Node(1), p = root, q = root;
        for (int i = 0; i < n; i++) {
            p = addChildren(p, 1, true);
            q = addChildren(q, 1, false);
        }

        Node pp = p, qq = q;
        for (int i = 0; i < n; i++) {
            p = addChildren(p, 1, true);
            pp = addChildren(pp, 1, false);
            q = addChildren(q, 1, false);
            qq = addChildren(qq, 1, true);
        }
        return root;
    }

    public static List<Integer> getPathSumsFn(Node root) {
        var stack = new Stack<Frame>();
        var pre = new Frame(root);
        stack.push(pre);

        while (true) {
            var top = stack.peek();
            if (top.node == null) { //最顶层的是空
                stack.pop();//弹出来最顶层的
                stack.peek().count++;
            } else if (top.count == 0) { //如果最顶层的count为0
                stack.push(new Frame(top.node.left));//把左节点放进去
            } else if (top.count == 1) { //如果最顶层的count为1
                stack.push(new Frame(top.node.right));//把右节点放进去
            } else { //如果最顶层的count为2
                var pop = stack.pop();//弹出来最顶层的
                if (pop.sums.isEmpty()) pop.sums.add(pop.node.data);//如果是空的 说明是最下面了 就加入当前节点的值
                if (stack.empty()) break;
                top = stack.peek();//这是最顶层的下一个了 因为 上面的pop了
                Frame finalTop = top;
                pop.sums.forEach(x -> finalTop.sums.add(x + finalTop.node.data));//把当前节点的值加上当前节点的值
                top.count++;//处理好了 之后就++
            }
        }

        return pre.sums;
    }

    public static void main(String[] args) {

        var root = buildTree(20000);
        var sums = getPathSumsFn(root);
        System.out.println(sums);

    }

}
