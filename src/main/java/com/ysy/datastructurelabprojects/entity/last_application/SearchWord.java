package com.ysy.datastructurelabprojects.entity.last_application;

/**
 * Created by Shengyu Yao on 2016/6/17.
 */
public class SearchWord {

    protected String[] wordsArray;
    protected String[] countsArray;
    protected String word;

    public SearchWord(String[] wordsArray, String[] countsArray, String word) {
        this.wordsArray = wordsArray;
        this.countsArray = countsArray;
        this.word = word;
    }

    /**
     * 搜索算法
     *
     * @return
     */
    public int start() {
        int count = 0;
        int searchTime = wordsArray.length / 4;
        for (int j = 0; j < searchTime; j++) { // 每片的单词数j
            // 分段同时查找，并行思想
            if (word.equals(wordsArray[j]) ||
                    word.equals(wordsArray[j + searchTime]) ||
                    word.equals(wordsArray[j + searchTime * 2]) ||
                    word.equals(wordsArray[j + searchTime * 3]))
                count = Integer.parseInt(countsArray[j]);
        }
        return count;
    }

}
