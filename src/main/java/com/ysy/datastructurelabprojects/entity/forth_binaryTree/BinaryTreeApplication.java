package com.ysy.datastructurelabprojects.entity.forth_binaryTree;

/**
 * Created by Shengyu Yao on 2016/6/3.
 */
public class BinaryTreeApplication {

    protected String visitResult = "";
    protected BSNode Tree = null;

    public BinaryTreeApplication(String[] keys) {
        Tree = CreateBST(keys);
    }

    public String getVisitResult() {
        inorder(Tree);
        visitResult = visitResult.replace(".", "");
        return visitResult;
    }

    private BSNode BSTinsert(BSNode T, BSNode S) { // 二叉排序树插入算法
        BSNode q, p;
        if (T == null) // 树为空时，以S为根节点
            return S;
        p = T; // q为p的父节点指针
        q = null;
        while (p != null) { // 寻找插入位置
            q = p;
            //ASCII码比较，compareTo等价于C语言strcmp函数
            if (S.data.key.compareTo(p.data.key) == 0) { // S节点已存在，返回
                S = null;
                return T;
            } else if (S.data.key.compareTo(p.data.key) < 0) {
                p = p.LChild; // 向左找
            } else {
                p = p.RChild; // 向右找
            }

        }
        if (S.data.key.compareTo(q.data.key) < 0)
            q.LChild = S; // S为q的左子插入
        else
            q.RChild = S; // S为p的左子插入

        return T;
    }

    private BSNode CreateBST(String[] keys) { // 建立二叉排序树
        BSNode T, S;
        int c, j = 0;
        String key = "";
        T = null; // 置空树

        if (keys.length == 0)
            return null; // 句子为空
        else if (keys[0].charAt(0) == '.')
            return null; // 句子为空

        key = keys[0];
        c = key.length();
        while (key.charAt(c - 1) != '.') {
            S = new BSNode(); // 申请一个S节点
            S.data.key = key;
            S.data.w = c - 1;

            S.LChild = S.RChild = null;
            T = BSTinsert(T, S);

            ++j;
            if (j < keys.length) {
                key = keys[j];
                c = key.length();
            }
        }

        if (key.charAt(c - 1) == '.') { // 处理句子的最后一个单词（eg:Hello.）
            S = new BSNode();
            S.data.key = key;
            S.data.w = c - 1;
            S.LChild = S.RChild = null;
            T = BSTinsert(T, S);
        }

        return T;
    }

    public void visit(BSNode T) { // 访问节点
        visitResult = visitResult + T.data.key + " ";
    }

    public void inorder(BSNode T) { // 中序遍历
        if (T != null) {
            inorder(T.LChild);
            visit(T);
            inorder(T.RChild);
        }
    }

}
