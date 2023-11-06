package dynamicProgramming.pojo;

import java.util.ArrayList;

public class ChangeRes {
    ArrayList<Integer> res;
    int count;

    public ChangeRes(ArrayList<Integer> res, int count) {
        this.res = res;
        this.count = count;
    }

    @Override
    public String toString() {
        return "ChangeRes{" +
                "res=" + res +
                ", count=" + count +
                '}';
    }
}
