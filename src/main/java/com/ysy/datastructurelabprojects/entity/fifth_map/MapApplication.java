package com.ysy.datastructurelabprojects.entity.fifth_map;

/**
 * Created by Shengyu Yao on 2016/6/7.
 */
public class MapApplication {

    private VertexNode G[];
    private String outputVexStr = "";

    public MapApplication(int datas[], Arc arcs[]) {
        this.G = new VertexNode[datas.length];
        createOrlist(this.G, datas, arcs);
    }

    private void createOrlist(VertexNode G[], int datas[], Arc arcs[]) {
        ArcNode p;
        int x, y;
        for (int i = 0; i < datas.length; ++i) { // 从用户界面提取到的datas数据填入顶点表G
            G[i].data = datas[i];
            G[i].fin = G[i].fout = null;
        }

        for (int j = 0; j < arcs.length; ++j) {
            x = locateVex(G, arcs[j].u);
            y = locateVex(G, arcs[j].v);
            p = new ArcNode();
            p.tail = x;
            p.head = y;
            p.tlink = G[x].fout;
            G[x].fout = p;
            p.hlink = G[y].fin;
            G[y].fin = p;
        }

    }

    private int locateVex(VertexNode G[], int vex) { // 获取顶点在顶点表中的序号
        int temp = 0;
        for (int i = 0; i < G.length; ++i) {
            if (G[i].data == vex)
                temp = i;
        }
        return temp;
    }

    private void createId(VertexNode G[], int n, int id[]) {
        int count;
        ArcNode p;
        for (int i = 0; i < n; ++i) { // 求n个顶点的入度
            id[i] = 0;
            count = 0; // 准备计数
            p = G[i].fin; // 取以Vi为弧头的第一弧节点
            while (p != null) {
                ++count;
                p = p.hlink;
            }
            id[i] = count;
        } // 入度赋值完成
    }

    public boolean isTopology() {
        int n = this.G.length, count = 0, id[] = new int[0];
        ArcNode p;
        OwnStack ownStack = new OwnStack(n);

        createId(this.G, n, id); // 建立G的入度表id
        for (int i = 0; i < n; ++i)
            if (id[i] == 0)
                ownStack.push(i); // 入度为0的顶点序号进栈

        int j, k;
        while (!ownStack.isEmpty()) {
            j = ownStack.pop(); // 退栈，栈顶赋给j
            outputVex(j, G[j].data);
            ++count;
            p = G[j].fout; // 取Vj发出的第一条弧
            while (p != null) {
                k = p.head; // 得到有向弧头指向的节点的序号
                --id[k]; // 并将其入度减1
                if (id[k] == 0)
                    ownStack.push(k);
                p = p.tlink;
            }
        }

        return count == n; // 如果为满计数，则为拓扑无环有向图
    }

    private void outputVex(int j, int data) {
        outputVexStr = outputVexStr + data + "(" + j + ")" + " "; // data(j)...
    }

    public String getOutputVexStr() {
        return outputVexStr;
    }

}
