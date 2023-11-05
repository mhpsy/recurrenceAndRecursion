package tree;

public class buildTree {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        var root = Node.buildTree(arr, 0, arr.length - 1);
        System.out.println(root);
    }

}
