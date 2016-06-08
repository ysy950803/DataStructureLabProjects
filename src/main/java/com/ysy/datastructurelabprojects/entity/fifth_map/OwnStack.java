package com.ysy.datastructurelabprojects.entity.fifth_map;

/**
 * Created by Shengyu Yao on 2016/6/7.
 */
public class OwnStack {

    private int MAX_SIZE;
    private int[] datas;
    private int TOP;

    public OwnStack(int s) {
        MAX_SIZE = s;
        datas = new int[s];
        TOP = -1; // 在实例化栈类的时候会在此置栈空
    }

    public void push(int d) {
        datas[++TOP] = d;
    }

    public int pop() {
        return datas[TOP--];
    }

    public int peak() {
        return datas[TOP];
    }

    public boolean isEmpty() {
        return TOP == -1;
    }

    public boolean isFull() {
        return TOP == (MAX_SIZE - 1);
    }

    public int size() {
        return TOP + 1;
    }

    public int get(int index) {
        return datas[index];
    }

}
