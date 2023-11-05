package day3;

import day1.Node;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    public Node node;
    public int count;
    public List<Integer> sums;

    public Frame(Node node) {
        this.node = node;
        this.sums = new ArrayList<Integer>();
    }
}
