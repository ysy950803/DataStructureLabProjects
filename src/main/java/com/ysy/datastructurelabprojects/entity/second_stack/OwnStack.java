package com.ysy.datastructurelabprojects.entity.second_stack;

public class OwnStack {

    private int MAX_SIZE; // 栈的最大容量
    private String[] chars; // 栈的数据
    private int TOP; // 栈头标记

    public OwnStack(int s) {
        MAX_SIZE = s;
        chars = new String[s];
        TOP = -1;
    }

    public void push(String c) {//入栈
        chars[++TOP] = c;
    }

    public String pop() {//出栈
        return chars[TOP--];
    }

    public String peek() {
        return chars[TOP];
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

    public String get(int index) {
        return chars[index];
    }

//    public void display(String str) {
////        System.out.print(str);
////        System.out.print(" OwnStack (bottom-->TOP): ");
//        Log.d("TEST", str + " OwnStack (bottom-->TOP): ");
//        for (int i = 0; i < size(); i++) {
////            System.out.print(get(i)+" ");
//            Log.d("TEST", get(i) + " ");
//        }
////        System.out.println();
//    }

}  