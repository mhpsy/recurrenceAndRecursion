package dynamicProgramming.pojo;

public class MinCountTo implements Comparable<MinCountTo> {

    public int count;//个数
    public int to;//硬币的钱

    public MinCountTo(int count, int to) {
        this.count = count;
        this.to = to;
    }

    @Override
    public int compareTo(MinCountTo that) {
        //如果count大就大 如果相等 比较 to  to大就小
        if (this.count > that.count) return 1;
        else if (this.count < that.count) return -1;
        else if (this.to > that.to) return -1;
        else if (this.to < that.to) return 1;
        else return 0;
    }
}
