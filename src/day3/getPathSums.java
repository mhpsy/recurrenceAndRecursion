package day3;

import day1.Node;

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

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Node root = Node.buildTree(arr, 0, arr.length - 1);
        System.out.printf("root: %s\n", root);
        var list = new ArrayList<Integer>();
        assert root != null;
        getPathSumFn(root, 0, list);
        System.out.println(list);
    }
}
