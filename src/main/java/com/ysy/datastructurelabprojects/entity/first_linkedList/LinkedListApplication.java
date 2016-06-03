package com.ysy.datastructurelabprojects.entity.first_linkedList;

/**
 * Created by Shengyu Yao on 2016/5/27.
 */
public class LinkedListApplication {

    private String result = "";

    public LinkedListApplication(int[] dataArray, int K) {
        Node testHead = createList(dataArray);
        displayAllNodes(testHead);
        Node resultP = adjMax(testHead, K);
        if (resultP == null)
            result = "K值大于链表长度，无法进行正常运算，请重新输入";
        else
            result = resultP.display();
    }

    private Node createList(int[] dataArray) {
        Node H, P, r;
        H = new Node(-1);
        r = H;
        for (int i = 0; i < dataArray.length; i++) {
            P = new Node(dataArray[i]);
            P.position = i; // 0~n-1
            r.next = P;
            r = P;
        }
        r.next = null;
        return H;
    }

    private Node adjMax(Node head, int K) {
        Node p, p1, q;
        int s0, s1;
        p = p1 = head.next;
        for (int i = 0; i < K; i++) { // 检查K次，K次内一旦有next为空则说明长度不足K，立即return
            if (p1 == null)
                return p1;
            p1 = p1.next; // 后移
        }
        if (p1 == null) // 长度恰好为K，直接返回首节点p
            return p;

        q = p.next;
        s0 = p.data;
        for (int i = 0; i < K - 1; i++) { // K位求和
            s0 = s0 + q.data;
            q = q.next;
        }

        while (q != null) {
            p = p.next; // 当长度大于K时，进入此循环，p右移
            q = p.next; // q归位
            s1 = p.data;
            for (int i = 0; i < K - 1; i++) {
                s1 = s1 + q.data;
                q = q.next;
            }
            if (s1 > s0) {
                p1 = p;
                s0 = s1; // 记录当前最大值以及第一节点
            }
        }

        return p1;
    }

    private void displayAllNodes(Node head) {
        Node currP = head;
        while (currP != null) {
            currP.display();
            currP = currP.next;
        }
    }

    public String getAdjMax() {
        return this.result;
    }

}
