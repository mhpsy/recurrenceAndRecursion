package getPathSum;

import tree.Node;

import java.util.*;

public class getPathSums {

    /**
     * offer() adds an element to the tail of the queue
     * offer = add = linkLast
     * poll() removes an element from the head of the queue
     * poll = remove = unlinkFirst
     */

    public static List<Integer> getPathSumsFn(Node root) {
        var sums = new ArrayList<Integer>();
        var sumQ = new LinkedList<Integer>();
        var nodeQ = new LinkedList<Node>();
        sumQ.offer(0);
        nodeQ.offer(root);

        while (!nodeQ.isEmpty()) {
            var curNode = nodeQ.poll();
            if (curNode == null) continue;// 这个判断是因为下面 offer前 没有判断是否为null 这里统一处理一下
            Integer oldSum = sumQ.poll();
            if (oldSum == null) continue;
            var curSum = oldSum + curNode.data;//当前节点的和
            if (curNode.left == null && curNode.right == null) {
                sums.add(curSum);
            }
            nodeQ.offer(curNode.left); // 只有存在左右节点才会加入队列
            sumQ.offer(curSum); // 也要存放商这个当前路径的和 保存下来，下一次循环的时候要加上
            nodeQ.offer(curNode.right);
            sumQ.offer(curSum);
        }

        return sums;
    }


    public static Map<Node, Integer> getPathSumsGetMapFn(Node root) {
        HashMap<Node, Integer> res = new HashMap<>();
        res.put(root, root.data);
        var nodes = new LinkedList<Node>();
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            Node curNode = nodes.poll();
            Integer curSum = res.get(curNode);
            //只有左右节点都为空的时候 才不会被删除
            if (curNode.left != null && curNode.right != null) res.remove(curNode);
            if (curNode.left != null) { //如果左节点不为空 就加入队列 并且更新map key为左节点 value为当前节点的路径和
                res.put(curNode.left, curSum + curNode.left.data);
                nodes.offer(curNode.left);
            }
            if (curNode.right != null) {
                res.put(curNode.right, curSum + curNode.right.data);
                nodes.offer(curNode.right);
            }
        }
        return res;
    }


    public static Map<Node, Integer> getPath2(Node root) {
        HashMap<Node, Integer> map = new HashMap<>();
        LinkedList<Node> nodeQ = new LinkedList<>();
        map.put(root, root.data);
        nodeQ.offer(root);
        while (!nodeQ.isEmpty()) {
            Node curNode = nodeQ.poll();//获取最上面的这一个
            Integer curSum = map.get(curNode);
            if (curNode.right != null && curNode.left != null) {
                map.remove(curNode);//删除当前节点
            }
            if (curNode.left != null) {
                //如果左边是空的话 就给他加上当前节点的值
                map.put(curNode.left, curSum + curNode.left.data);
                nodeQ.offer(curNode.left);
            }
            if (curNode.right != null) {
                map.put(curNode.right, curSum + curNode.right.data);
                nodeQ.offer(curNode.right);
            }

        }
        return map;
    }

    public static Map<Node, Integer> getPathSums22(Node root) {
        var sums = new HashMap<Node, Integer>();
        var nodeQ = new LinkedList<Node>();
        sums.put(root, root.data);
        nodeQ.offer(root);

        while (!nodeQ.isEmpty()) {
            var curNode = nodeQ.poll();
            var curSum = sums.get(curNode);
            System.out.println(curSum);
//            if (curNode.left != null || curNode.right != null) {
//                sums.remove(curNode);
//            }
            if (curNode.left != null) {
                sums.put(curNode.left, curSum + curNode.left.data);
                nodeQ.offer(curNode.left);
            }
            if (curNode.right != null) {
                sums.put(curNode.right, curSum + curNode.right.data);
                nodeQ.offer(curNode.right);
            }
        }
        return sums;

    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        var root = Node.buildTree(arr, 0, arr.length - 1);
        System.out.println(root);
        assert root != null;
        System.out.println(root.toStringRecursive(root, ""));
        var sums = getPathSumsGetMapFn(root);
        for (Node node : sums.keySet()) {
            System.out.println(node + " " + sums.get(node));
        }

        System.out.println("-------------------");

        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        var root2 = Node.buildTree(arr2, 0, arr2.length - 1);
        var map = getPath2(root2);
        for (Node node : map.keySet()) {
            System.out.println(node + " " + map.get(node));
        }


        System.out.println("-------------------");

        int[] arr3 = {1, 2, 3, 4, 5, 6, 7};
        var root3 = Node.buildTree(arr3, 0, arr3.length - 1);
        System.out.println(root3);
        var map2 = getPathSums22(root3);
        for (Node node : map2.keySet()) {
            System.out.println(node.data + " " + map2.get(node));
        }
    }

}
