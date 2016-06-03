package com.ysy.datastructurelabprojects.entity.second_stack;

import android.util.Log;

public class OwnStack {

    private int maxSize; // 栈的最大容量
    private String[] ch; // 栈的数据
    private int top; // 栈头标记

    public OwnStack(int s) {
        maxSize = s;
        ch = new String[s];
        top = -1;
    }

    public void push(String c) {//入栈
        ch[++top] = c;
    }

    public String pop() {//出栈
        return ch[top--];
    }

    public String peek() {
        return ch[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == (maxSize - 1);
    }

    public int size() {
        return top + 1;
    }

    public String get(int index) {
        return ch[index];
    }

    public void display(String str) {
//        System.out.print(str);
//        System.out.print(" OwnStack (bottom-->top): ");
        Log.d("TEST", str + " OwnStack (bottom-->top): ");
        for (int i = 0; i < size(); i++) {
//            System.out.print(get(i)+" ");
            Log.d("TEST", get(i) + " ");
        }
//        System.out.println();
    }

}  