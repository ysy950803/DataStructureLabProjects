package com.ysy.datastructurelabprojects.entity.second_stack;

import java.util.EmptyStackException;

/**
 * Created by Shengyu Yao on 2016/5/29.
 */
public class StackApp {

    private String[] postFix; // 输出的后缀表达式
    private String sum = "";

    public StackApp(String midFix) {
        this.postFix = transMidToPost(midFix);
        this.sum = evaluate(midFix);
    }

    public String[] getPostFix() {
        return postFix;
    }

    public String getSum() {
        return sum;
    }

    private static String[] transMidToPost(String midFix) {
        String postFix[] = new String[midFix.length()];
        StringBuffer numBuffer = new StringBuffer(); // 用来保存一个数的
        OwnStack s = new OwnStack(midFix.length()); // 栈结构存放操作符
        String a;
        s.push("="); // 第一个为等号
        int i = 0, j = 0;
        char ch;
        for (i = 0; i < midFix.length(); ) {
            ch = midFix.charAt(i);
            switch (ch) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    while (Character.isDigit(ch) || ch == '.') // 拼数
                    {
                        numBuffer.append(ch); // 追加字符
                        ch = midFix.charAt(++i);
                    }
                    postFix[j++] = numBuffer.toString(); // break;
                    numBuffer = new StringBuffer(); // 清空已获取的运算数字
                    continue; // 这里要重新循环，因为i已经增加过了
                case '(':
                    s.push("(");
                    break;
                case ')':
                    while (s.peek() != "(")
                        postFix[j++] = s.pop();
                    break;
                case '+':
                case '-':
                    while (s.size() > 1 && s.peek() != "(")
                        postFix[j++] = s.pop();
                    a = String.valueOf(ch);
                    s.push(a);
                    break;
                case '*':
                case '/':
                    while (s.size() > 1 && (s.peek() == "*") || s.peek() == "/"
                            || s.peek() == "s" || s.peek() == "c"
                            || s.peek() == "t" || s.peek() == "^"
                            || s.peek() == "√")
                        // 优先级比较，与栈顶比较，
                        postFix[j++] = s.pop(); // 当前操作符优先级大于等于栈顶的弹出栈顶
                    a = String.valueOf(ch);
                    s.push(a);
                    break;
            }
            i++;
        }
        while (s.size() > 1)
            postFix[j++] = s.pop();
        postFix[j] = "=";

        return postFix;
    }

    public static String evaluate(String midFix) { // 后缀表达式求值
        String postFix[] = null;
        postFix = transMidToPost(midFix);
        int i = 0;
        double x1, x2, n;
        String str;
        OwnStack s = new OwnStack(midFix.length());
        while (postFix[i] != "=") {
            str = postFix[i];
            switch (str.charAt(0)) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    s.push(str);
                    break;
                case '+':
                    x1 = Double.parseDouble(s.pop());
                    x2 = Double.parseDouble(s.pop());
                    n = x1 + x2;
                    s.push(String.valueOf(n));
                    break;
                case '-':
                    x1 = Double.parseDouble(s.pop());
                    x2 = Double.parseDouble(s.pop());
                    n = x2 - x1;
                    s.push(String.valueOf(n));
                    break;
                case '*':
                    x1 = Double.parseDouble(s.pop());
                    x2 = Double.parseDouble(s.pop());
                    n = x1 * x2;
                    s.push(String.valueOf(n));
                    break;
                case '/':
                    x1 = Double.parseDouble(s.pop());
                    x2 = Double.parseDouble(s.pop());
                    n = x2 / x1;
                    s.push(String.valueOf(n));
                    break;
            }
            i++;
        }
        return s.pop();
    }

}
