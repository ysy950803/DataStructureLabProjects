package com.ysy.datastructurelabprojects.entity.first_linkedList;

import android.util.Log;

/**
 * Created by Shengyu Yao on 2016/5/27.
 */
public class Node {
    protected Node next;
    protected int data;
    protected int position;

    public Node(int data) {
        this.data = data;
    }

    public String display() {
        Log.d("TEST", "Data:" + data + " Position:" + position);
        return "Data:" + data + "\n" + "Position:" + position;
    }

}