package tree;

public class Node {
    public int data;
    public Node left, right;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
//        return toStringRecursive(this, "");
        return "Node{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public String toStringRecursive(Node node, String prefix) {
        if (node == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(node.data);
        sb.append("\n");
        String childPrefix = prefix + "├─ ";
        String childSuffix = prefix + "│  ";
        if (node.left != null || node.right != null) {
            if (node.left != null) {
                sb.append(childPrefix);
                sb.append("L: ");
                sb.append(toStringRecursive(node.left, childSuffix));
            }
            if (node.right != null) {
                sb.append(childPrefix);
                sb.append("R: ");
                sb.append(toStringRecursive(node.right, childSuffix));
            }
        }
        return sb.toString();
    }

    public static Node buildTree(int[] arr, int li, int hi) {
        if (li > hi) return null;
        int mid = (li + hi) / 2;
        var root = new Node(arr[mid]);
        root.left = buildTree(arr, li, mid - 1);
        root.right = buildTree(arr, mid + 1, hi);
        return root;
    }
}
