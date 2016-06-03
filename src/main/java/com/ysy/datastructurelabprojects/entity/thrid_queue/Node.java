package com.ysy.datastructurelabprojects.entity.thrid_queue;

import android.util.Log;

/**
 * Created by Shengyu Yao on 2016/5/27.
 */
public class Node {

    protected Node next;
    protected char data;

    public Node(char data) {
        this.data = data;
    }

    public String display() {
        Log.d("TEST", "Data:" + data);
        return data + " ";
    }

}
