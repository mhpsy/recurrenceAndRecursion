package getPathSum2;

import tree.Node;

import java.util.ArrayList;
import java.util.List;

public class getPathSums {

    public static void getPathSumFn(Node root, int parentSum, List<Integer> resList) {
        //如果为空就返回
        if (root == null) return;
        int curSum = parentSum + root.data;//当前的值
        //如果没有孩子 就加入到结果集中
        if (root.left == null && root.right == null) resList.add(curSum);
        //如果有孩子 就递归调用
        getPathSumFn(root.left, curSum, resList);
        getPathSumFn(root.right, curSum, resList);
    }

    public static List<Integer> collectSums(Node root) {
        var res = new ArrayList<Integer>();
        if (root == null) return res;//如果为空 也要返回一个空的list
        res.addAll(collectSums(root.left));
        res.addAll(collectSums(root.right));
        res.replaceAll(x -> x + root.data);//这里是把每个节点的值都 加上当前节点的值
        if (root.right == null && root.left == null) res.add(root.data);
        return res;
    }

    public static List<Integer> collectSums2(Node root) {
        var res = new ArrayList<Integer>();
        if (root == null) return res;
        // collectSums(root.left) 就是
        collectSums(root.left).forEach(x -> res.add(x + root.data));
        collectSums(root.right).forEach(x -> res.add(x + root.data));
        if (root.left == null && root.right == null) res.add(root.data);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Node root = Node.buildTree(arr, 0, arr.length - 1);
        System.out.printf("root: %s\n", root);
        var list = new ArrayList<Integer>();
        assert root != null;
        getPathSumFn(root, 0, list);
        System.out.println(list);
        System.out.println(collectSums2(root));
    }
}
