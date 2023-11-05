package day4;

public class backtracking2 {

    public static void visit(boolean[][] visited, int n) {
        int i = visited.length, j = visited[0].length;
        if (n < 0 || n >= i * j) return;
        int x = n / i, y = n % i;
        visited[x][y] = true;
        System.out.println(x + " " + y);
        visit(visited, n + 1);
        visited[x][y] = false;
        System.out.println(x + " " + y);
    }

    public static void main(String[] args) {
        var visited = new boolean[3][3];
        visit(visited, 0);

    }

}
