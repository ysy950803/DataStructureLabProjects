package com.ysy.datastructurelabprojects.entity.thrid_queue;

/**
 * Created by Shengyu Yao on 2016/5/27.
 */
public class QueueApplication {

    private QNode q;

    public QueueApplication() {
        q = new QNode();
    }

    public QNode getQ() {
        return this.q;
    }

    boolean isQueueEmpty(QNode q) {
        return q.front == q.rear;
    }

    public void enterQueue(QNode q, char data) {
        Node p = new Node(data);
        p.next = null;
        q.rear.next = p; // 此时q.rear还是旧队队尾，将其指向新队尾p
        q.rear = p; // 队尾更新为p
    }

    public char quitQueue(QNode q) {
        Node p;
        if (isQueueEmpty(q))
            return ' ';
        else {
            p = q.front;
            q.front = p.next; // 等价式q.front = q.front.next，将即将出队的队头元素提前至头节点
            p = null; // 清空临时节点
            return q.front.data; // 返回被提前至头节点的原队头元素，即出队元素
        }
    }

    public String quitQueueAll(QNode q) {
        String tempStr = "";
        Node p;
        if (!isQueueEmpty(q)) {
            while (q.front != q.rear) {
                p = q.front;
                q.front = p.next; // 等价式q.front = q.front.next，将即将出队的队头元素提前至头节点
                p = null;
                tempStr = tempStr + q.front.data + " ";
            }
        }
        return tempStr;
    }

    public String displayAllNodes() {
        String tempStr = "";
        Node current = q.front.next;
        while (current != null) {
            tempStr = tempStr + current.display();
            current = current.next;
        }
        return tempStr;
    }

}
