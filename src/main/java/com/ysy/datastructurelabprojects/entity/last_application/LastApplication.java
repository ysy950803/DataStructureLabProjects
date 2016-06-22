package com.ysy.datastructurelabprojects.entity.last_application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shengyu Yao on 2016/6/12.
 */
public class LastApplication {

    private List<List<String>> wordLists;
    private List<List<Integer>> countLists;

    private List<List<String>> topTenWordLists;
    private List<List<Integer>> topTenCountLists;

    public LastApplication() {
        wordLists = new ArrayList<>();
        countLists = new ArrayList<>();
        topTenWordLists = new ArrayList<>();
        topTenCountLists = new ArrayList<>();
    }

    public List<List<String>> getWordLists() {
        return wordLists;
    }

    public List<List<Integer>> getCountLists() {
        return countLists;
    }

    public void collectAllWords(String[] eachReadStrArray) {
        List<String> wordList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        List<String> topTenWordList = new ArrayList<>();
        List<Integer> topTenCountList = new ArrayList<>();
        for (int i = 0; i < eachReadStrArray.length; ++i) {
            if (wordList.size() == 0) {
                wordList.add(eachReadStrArray[i]);
                countList.add(1);
            } else {
                int j;
                for (j = 0; j < wordList.size(); ++j) {
                    if (eachReadStrArray[i].equals(wordList.get(j))) { // 不能使用==，而要用equals，否则无法进入判断
                        countList.set(j, countList.get(j) + 1);
                        break;
                    }
                }
                if (j == wordList.size()) {
                    wordList.add(eachReadStrArray[i]);
                    countList.add(1);
                }
            }
        }

        quickSortForList(countList, wordList); // 快速排序后得到每一片段的降序所有单词
        wordLists.add(wordList);
        countLists.add(countList);

        int count_ten;
        if (wordLists.size() >= 10)
            count_ten = 10;
        else
            count_ten = wordLists.size();
        for (int i = 0; i < count_ten; i++) { // 将每一片段的TopTen单独存起来，以便在后续findTopTen时效率更高（片段数*10）
            topTenWordList.add(wordList.get(i));
            topTenCountList.add(countList.get(i));
        }
        topTenWordLists.add(topTenWordList); // 若文件过大，最后会有多组TopTen
        topTenCountLists.add(topTenCountList);
    }

    /**
     * 快速排序，同时对单词和其对应的数量进行排序
     *
     * @param countList
     * @param wordList
     */
    private void quickSortForList(List<Integer> countList, List<String> wordList) {
        if (countList.size() > 0)
            quickSort(countList, wordList, 0, countList.size() - 1);
    }

    private void quickSort(List<Integer> countList, List<String> wordList, int low, int high) {
        if (low < high) {
            int mid = getMid(countList, wordList, low, high); // 将list进行一分为二
            quickSort(countList, wordList, low, mid - 1); // 对低字表进行递归排序
            quickSort(countList, wordList, mid + 1, high); //对高字表进行递归排序
        }
    }

    private int getMid(List<Integer> countList, List<String> wordList, int low, int high) {
        int temp = countList.get(low);
        String tempStr = wordList.get(low); // list的第一个Object作为中轴

        while (low < high) {
            while (low < high && countList.get(high) <= temp) // 升序是>=
                high--;
            countList.set(low, countList.get(high));
            wordList.set(low, wordList.get(high)); // 比中轴大的记录移到低端
            while (low < high && countList.get(low) >= temp) // 升序是<=
                low++;
            countList.set(high, countList.get(low));
            wordList.set(high, wordList.get(low)); // 比中轴小的记录移到高端
        }
        countList.set(low, temp);
        wordList.set(low, tempStr);

        return low;
    }

    public String[] findTopTen() {
        String[] tempTopTen = new String[10];
        List<String> mergeTopTenWordList = new ArrayList<>();
        List<Integer> mergeTopTenCountList = new ArrayList<>();
        if (topTenWordLists.size() == 1) {
            mergeTopTenWordList = topTenWordLists.get(0);
            mergeTopTenCountList = topTenCountLists.get(0);
        } else if (topTenWordLists.size() > 1) {
            for (int i = 0; i < topTenWordLists.size(); ++i) { // 对多组TopTen进行无重复合并处理，再重新排序
                for (int j = 0; j < topTenWordLists.get(i).size(); ++j) {
                    if (mergeTopTenWordList.size() == 0) {
                        mergeTopTenWordList.add(topTenWordLists.get(i).get(j));
                        mergeTopTenCountList.add(topTenCountLists.get(i).get(j));
                    } else {
                        int k;
                        for (k = 0; k < mergeTopTenWordList.size(); ++k) {
                            if (mergeTopTenWordList.get(k).equals(topTenWordLists.get(i).get(j))) {
                                mergeTopTenCountList.set(k, mergeTopTenCountList.get(k) + topTenCountLists.get(i).get(j));
                                break;
                            }
                        }
                        if (k == mergeTopTenWordList.size()) {
                            mergeTopTenWordList.add(topTenWordLists.get(i).get(j));
                            mergeTopTenCountList.add(topTenCountLists.get(i).get(j));
                        }
                    }
                }
            }
            quickSortForList(mergeTopTenCountList, mergeTopTenWordList); // 多个TopTen合并后进行快速排序
        }

        for (int i = 0; i < 10; i++) { // 得到综合后的TopTen
            tempTopTen[i] = mergeTopTenWordList.get(i)
                    + " " + mergeTopTenCountList.get(i);
        }

        return tempTopTen;
    }

}
