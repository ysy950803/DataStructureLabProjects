package com.ysy.datastructurelabprojects.entity.thrid_queue;

/**
 * Created by Shengyu Yao on 2016/5/27.
 */
public class QNode {

    protected Node front;
    protected Node rear;

    public QNode() {
        this.front = new Node(' ');
        this.front.next = null;
        this.rear = this.front;
    }

}
