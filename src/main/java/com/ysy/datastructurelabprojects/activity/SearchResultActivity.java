package com.ysy.datastructurelabprojects.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ysy.datastructurelabprojects.R;
import com.ysy.datastructurelabprojects.entity.last_application.LastApplication;
import com.ysy.datastructurelabprojects.entity.last_application.DataProcess;
import com.ysy.datastructurelabprojects.entity.last_application.SearchWord;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private TextView fileNameTV;
    private TextView top1;
    private TextView top1Count;
    private TextView top2;
    private TextView top2Count;
    private TextView top3;
    private TextView top3Count;
    private TextView top4;
    private TextView top4Count;
    private TextView top5;
    private TextView top5Count;
    private TextView top6;
    private TextView top6Count;
    private TextView top7;
    private TextView top7Count;
    private TextView top8;
    private TextView top8Count;
    private TextView top9;
    private TextView top9Count;
    private TextView top10;
    private TextView top10Count;
    private EditText searchEdt;
    private Button searchBtn;
    private TextView searchWord;
    private TextView searchCount;
    private Button resetBtn;
    private Button deleteBtn;

    private String[] temp_topTen;

    private String FIXED_FILE_NAME = MainActivity.FILE_NAME.replace("/", "").replace(".", "");

    private static int READ_EXTERNAL_STORAGE_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        setupActionBar();

        findView();

        fileNameTV.setText(MainActivity.FILE_NAME);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchEdt.getText().toString().equals(""))
                    Toast.makeText(SearchResultActivity.this, "单词内容不能为空！", Toast.LENGTH_SHORT).show();
                else
                    searchWordAndShowCount(searchEdt.getText().toString());
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermission();
                } else {
                    try {
                        readTxtFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.TITLE_LIST.remove(MainActivity.POSITION);
                new DataProcess(SearchResultActivity.this).saveData(FIXED_FILE_NAME + "TOP_TEN", "");

                int lists_length = new DataProcess(SearchResultActivity.this).readIntData(FIXED_FILE_NAME + "WORD_LISTS_LENGTH");
                for (int i = 0; i < lists_length; i++) {
                    new DataProcess(SearchResultActivity.this).saveData(FIXED_FILE_NAME + "ALL_WORDS" + i, "");
                    new DataProcess(SearchResultActivity.this).saveData(FIXED_FILE_NAME + "ALL_COUNTS" + i, "");
                }
                new DataProcess(SearchResultActivity.this).saveData(FIXED_FILE_NAME + "WORD_LISTS_LENGTH", 0);

                finish();
            }
        });

        // 启动该页面时获取存储在本地的TOP_TEN数据
        String topTenArrayStr = new DataProcess(SearchResultActivity.this).readStrData(FIXED_FILE_NAME + "TOP_TEN");
        if (topTenArrayStr.equals("")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermission();
            } else {
                try {
                    readTxtFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            topTenArrayStr = topTenArrayStr.replace("[", "").replace("]", "");
            String[] topTenStrArray = topTenArrayStr.replace(", ", ",").trim().split(",");
            setTopTenFromStorage(topTenStrArray);
        }

    }

    /**
     * Android 6.0后读写文件需要向用户提示单独获取权限
     */
    public void requestPermission() {
        //判断当前Activity是否已经获得了该权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //如果App的权限申请曾经被用户拒绝过，就需要在这里跟用户做出解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "Please give me the permission.", Toast.LENGTH_SHORT).show();
            } else {
                //进行权限请求
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        READ_EXTERNAL_STORAGE_REQUEST_CODE);
            }
        } else {
            try {
                readTxtFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                try {
                    readTxtFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Permission Denied
                Toast.makeText(this, "Please give me the permission.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void findView() {
        fileNameTV = (TextView) findViewById(R.id.last_file_name_tv);
        top1 = (TextView) findViewById(R.id.last_top1_tv);
        top1Count = (TextView) findViewById(R.id.last_top1_num_tv);
        top2 = (TextView) findViewById(R.id.last_top2_tv);
        top2Count = (TextView) findViewById(R.id.last_top2_num_tv);
        top3 = (TextView) findViewById(R.id.last_top3_tv);
        top3Count = (TextView) findViewById(R.id.last_top3_num_tv);
        top4 = (TextView) findViewById(R.id.last_top4_tv);
        top4Count = (TextView) findViewById(R.id.last_top4_num_tv);
        top5 = (TextView) findViewById(R.id.last_top5_tv);
        top5Count = (TextView) findViewById(R.id.last_top5_num_tv);
        top6 = (TextView) findViewById(R.id.last_top6_tv);
        top6Count = (TextView) findViewById(R.id.last_top6_num_tv);
        top7 = (TextView) findViewById(R.id.last_top7_tv);
        top7Count = (TextView) findViewById(R.id.last_top7_num_tv);
        top8 = (TextView) findViewById(R.id.last_top8_tv);
        top8Count = (TextView) findViewById(R.id.last_top8_num_tv);
        top9 = (TextView) findViewById(R.id.last_top9_tv);
        top9Count = (TextView) findViewById(R.id.last_top9_num_tv);
        top10 = (TextView) findViewById(R.id.last_top10_tv);
        top10Count = (TextView) findViewById(R.id.last_top10_num_tv);
        searchEdt = (EditText) findViewById(R.id.last_search_edt);
        searchBtn = (Button) findViewById(R.id.last_search_btn);
        searchWord = (TextView) findViewById(R.id.last_search_word_tv);
        searchCount = (TextView) findViewById(R.id.last_search_word_num_tv);
        resetBtn = (Button) findViewById(R.id.last_reset_btn);
        deleteBtn = (Button) findViewById(R.id.last_delete_btn);
    }

    /**
     * map(FileChannel.MapMode mode,long position, long size)
     * mode - 根据是按只读、读取/写入或专用（写入时拷贝）来映射文件，分别为 FileChannel.MapMode 类中所定义的
     * READ_ONLY、READ_WRITE 或 PRIVATE 之一
     * position - 文件中的位置，映射区域从此位置开始；必须为非负数
     * size - 要映射的区域大小；必须为非负数且不大于 Integer.MAX_VALUE
     * 若想读取文本后1/8内容，需要这样写map(FileChannel.MapMode.READ_ONLY, f.length()*7/8, f.length()/8)
     * 想读取文件所有内容，需要这样写map(FileChannel.MapMode.READ_ONLY, 0, f.length())
     */
    private void readFile() throws Exception {
        String[] eachReadStrArray;
        LastApplication lastApp = new LastApplication();

        final int BUFFER_SIZE = 0xf4240; // 缓冲大小为1M

        File f = new File(MainActivity.FILE_NAME);

        long start = System.currentTimeMillis();

        MappedByteBuffer inputBuffer = new RandomAccessFile(f, "r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0, f.length());

        byte[] dst = new byte[BUFFER_SIZE]; // 每次读出1M的内容
        for (int offset = 0; offset < inputBuffer.capacity(); offset += BUFFER_SIZE) {
            if (inputBuffer.capacity() - offset >= BUFFER_SIZE) {
                for (int i = 0; i < BUFFER_SIZE; i++)
                    dst[i] = inputBuffer.get(offset + i);
            } else {
                for (int i = 0; i < inputBuffer.capacity() - offset; i++)
                    dst[i] = inputBuffer.get(offset + i);
            }
            int length = (inputBuffer.capacity() % BUFFER_SIZE == 0) ? BUFFER_SIZE
                    : inputBuffer.capacity() % BUFFER_SIZE;

            String eachReadStr = new String(dst, 0, length);
            String indexOfEachReadStr = eachReadStr;

            Toast.makeText(SearchResultActivity.this, offset + " " + indexOfEachReadStr.length(), Toast.LENGTH_SHORT).show();

            for (int i = 0; i < indexOfEachReadStr.length(); ++i) {
                if (!('0' < indexOfEachReadStr.charAt(i) && indexOfEachReadStr.charAt(i) < '9' ||
                        indexOfEachReadStr.charAt(i) > 'A' && indexOfEachReadStr.charAt(i) < 'z' ||
                        indexOfEachReadStr.charAt(i) == '_')) {
                    eachReadStr = eachReadStr.replace(indexOfEachReadStr.charAt(i), ' ');
                }
            }
//
            eachReadStrArray = eachReadStr.trim().split("\\s+"); // 得到分段的单词数组，若文件内容过多，将会有很多段
            lastApp.collectAllWords(eachReadStrArray); // 收集片段
        }

        long end = System.currentTimeMillis();
        Toast.makeText(SearchResultActivity.this, "读取文本耗时为 " + (end - start) + " ms", Toast.LENGTH_SHORT).show();

        temp_topTen = lastApp.findTopTen();
        setTopTenFromRAM(temp_topTen);

        saveTopTen();
        saveAllWords(lastApp.getWordLists(), lastApp.getCountLists());
    }

    private void readTxtFile() throws IOException {
        long start = System.currentTimeMillis();

        LastApplication lastApp = new LastApplication();
        File file = new File(MainActivity.FILE_NAME);
        BufferedInputStream bIS = new BufferedInputStream(new FileInputStream(file));
        BufferedReader bR = new BufferedReader(new InputStreamReader(bIS, "utf-8"), 4 * 1024 * 1024); // 用4M的缓冲读取文本文件

        String[] eachReadStrArray;
        String eachReadStr = "";
        String line;
        int eachCount = 0;

        while ((line = bR.readLine()) != null) {
            eachReadStr = eachReadStr + " " + line + " ";
            ++eachCount;
            if (eachCount == 32) {
                String indexOfEachLine = eachReadStr;
                for (int i = 0; i < indexOfEachLine.length(); ++i) {
                    if (!('0' < indexOfEachLine.charAt(i) && indexOfEachLine.charAt(i) < '9' ||
                            indexOfEachLine.charAt(i) > 'A' && indexOfEachLine.charAt(i) < 'z' ||
                            indexOfEachLine.charAt(i) == '_')) {
                        eachReadStr = eachReadStr.replace(indexOfEachLine.charAt(i), ' ');
                    }
                }
                eachReadStrArray = eachReadStr.trim().split("\\s+"); // 得到分段的单词数组，若文件内容过多，将会有很多段
                lastApp.collectAllWords(eachReadStrArray); // 收集片段
                eachCount = 0;
                eachReadStr = "";
            }
        }
        if (eachCount != 0) {
            String indexOfEachLine = eachReadStr;
            for (int i = 0; i < indexOfEachLine.length(); ++i) {
                if (!('0' < indexOfEachLine.charAt(i) && indexOfEachLine.charAt(i) < '9' ||
                        indexOfEachLine.charAt(i) > 'A' && indexOfEachLine.charAt(i) < 'z' ||
                        indexOfEachLine.charAt(i) == '_')) {
                    eachReadStr = eachReadStr.replace(indexOfEachLine.charAt(i), ' ');
                }
            }
            eachReadStrArray = eachReadStr.trim().split("\\s+"); // 得到分段的单词数组，若文件内容过多，将会有很多段
            lastApp.collectAllWords(eachReadStrArray); // 收集片段
        }

        long end = System.currentTimeMillis();
        Toast.makeText(SearchResultActivity.this, "读取文本耗时为 " + (end - start) + " ms", Toast.LENGTH_SHORT).show();

        temp_topTen = lastApp.findTopTen();
        setTopTenFromRAM(temp_topTen);

        saveTopTen();
        saveAllWords(lastApp.getWordLists(), lastApp.getCountLists());
    }

    private void setTopTenFromRAM(String[] temp_topTen) {
        for (int i = 0; i < 10; i++) {
            String[] topAndCount = temp_topTen[i].split(" "); // eg: "Apple 2"
            switch (i) {
                case 0:
                    top1.setText(topAndCount[0]); // Apple
                    top1Count.setText(topAndCount[1]); // 2
                    break;
                case 1:
                    top2.setText(topAndCount[0]);
                    top2Count.setText(topAndCount[1]);
                    break;
                case 2:
                    top3.setText(topAndCount[0]);
                    top3Count.setText(topAndCount[1]);
                    break;
                case 3:
                    top4.setText(topAndCount[0]);
                    top4Count.setText(topAndCount[1]);
                    break;
                case 4:
                    top5.setText(topAndCount[0]);
                    top5Count.setText(topAndCount[1]);
                    break;
                case 5:
                    top6.setText(topAndCount[0]);
                    top6Count.setText(topAndCount[1]);
                    break;
                case 6:
                    top7.setText(topAndCount[0]);
                    top7Count.setText(topAndCount[1]);
                    break;
                case 7:
                    top8.setText(topAndCount[0]);
                    top8Count.setText(topAndCount[1]);
                    break;
                case 8:
                    top9.setText(topAndCount[0]);
                    top9Count.setText(topAndCount[1]);
                    break;
                case 9:
                    top10.setText(topAndCount[0]);
                    top10Count.setText(topAndCount[1]);
                    break;
            }
        }
    }

    /**
     * 持久化存储TopTen数据
     */
    private void saveTopTen() {
        List<String> topTenList = new ArrayList<>();
        Collections.addAll(topTenList, temp_topTen);

        new DataProcess(SearchResultActivity.this).saveData(FIXED_FILE_NAME + "TOP_TEN", topTenList.toString());
    }

    /**
     * 持久化存储单词列表和对应的计数
     */
    private void saveAllWords(List<List<String>> wordLists, List<List<Integer>> countLists) {
        for (int i = 0; i < wordLists.size(); i++) {
            new DataProcess(SearchResultActivity.this).saveData(FIXED_FILE_NAME + "ALL_WORDS" + i, wordLists.get(i).toString());
            new DataProcess(SearchResultActivity.this).saveData(FIXED_FILE_NAME + "ALL_COUNTS" + i, countLists.get(i).toString());
        }
        // 本地化存储片段数
        new DataProcess(SearchResultActivity.this).saveData(FIXED_FILE_NAME + "WORD_LISTS_LENGTH", wordLists.size());
    }

    private void setTopTenFromStorage(String[] topTenStrArray) {
        List<String> topTenList = new ArrayList<>();
        Collections.addAll(topTenList, topTenStrArray);
        for (int i = 0; i < 10; i++) {
            String[] topAndCount = topTenList.get(i).split(" "); // eg: "Apple 2"
            switch (i) {
                case 0:
                    top1.setText(topAndCount[0]); // Apple
                    top1Count.setText(topAndCount[1]); // 2
                    break;
                case 1:
                    top2.setText(topAndCount[0]);
                    top2Count.setText(topAndCount[1]);
                    break;
                case 2:
                    top3.setText(topAndCount[0]);
                    top3Count.setText(topAndCount[1]);
                    break;
                case 3:
                    top4.setText(topAndCount[0]);
                    top4Count.setText(topAndCount[1]);
                    break;
                case 4:
                    top5.setText(topAndCount[0]);
                    top5Count.setText(topAndCount[1]);
                    break;
                case 5:
                    top6.setText(topAndCount[0]);
                    top6Count.setText(topAndCount[1]);
                    break;
                case 6:
                    top7.setText(topAndCount[0]);
                    top7Count.setText(topAndCount[1]);
                    break;
                case 7:
                    top8.setText(topAndCount[0]);
                    top8Count.setText(topAndCount[1]);
                    break;
                case 8:
                    top9.setText(topAndCount[0]);
                    top9Count.setText(topAndCount[1]);
                    break;
                case 9:
                    top10.setText(topAndCount[0]);
                    top10Count.setText(topAndCount[1]);
                    break;
            }
        }
    }

    private void searchWordAndShowCount(String word) {
        int count = 0;
        long start = System.currentTimeMillis();

        // 将本地存储中的单词和对应的计数分段加载在内存中，并进行分段统计，以应对大型文件造成内存溢出的问题
        int LISTS_LENGTH = new DataProcess(SearchResultActivity.this).readIntData(FIXED_FILE_NAME + "WORD_LISTS_LENGTH");

        for (int i = 0; i < LISTS_LENGTH; i++) {
            // 分段获取数据，每次都会重新分配内存，不会把单词全部加载到内存
            String words = new DataProcess(SearchResultActivity.this).readStrData(FIXED_FILE_NAME + "ALL_WORDS" + i);
            String counts = new DataProcess(SearchResultActivity.this).readStrData(FIXED_FILE_NAME + "ALL_COUNTS" + i);
            words = words.replace("[", "").replace("]", "");
            counts = counts.replace("[", "").replace("]", "");
            String[] wordsArray = words.replace(", ", ",").trim().split(","); // 分段的String[]
            String[] countsArray = counts.replace(", ", ",").trim().split(",");

            // 然后进行查找计数
            count = count + new SearchWord(wordsArray, countsArray, word).start();
        }

        searchWord.setText(word);
        searchCount.setText(count + "");
        long end = System.currentTimeMillis();
        Toast.makeText(SearchResultActivity.this, "搜索单词耗时为 " + (end - start) + " ms", Toast.LENGTH_SHORT).show();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //覆盖整个Activity的返回按钮
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed(); //调用onKeyDown内部方法
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
