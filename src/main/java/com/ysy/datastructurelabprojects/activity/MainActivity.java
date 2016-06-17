package com.ysy.datastructurelabprojects.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ysy.datastructurelabprojects.R;
import com.ysy.datastructurelabprojects.entity.fifth_map.Arc;
import com.ysy.datastructurelabprojects.entity.fifth_map.MapApplication;
import com.ysy.datastructurelabprojects.entity.first_linkedList.LinkedListApplication;
import com.ysy.datastructurelabprojects.entity.forth_binaryTree.BinaryTreeApplication;
import com.ysy.datastructurelabprojects.entity.last_application.DataProcess;
import com.ysy.datastructurelabprojects.entity.second_stack.StackApp;
import com.ysy.datastructurelabprojects.entity.thrid_queue.QueueApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RelativeLayout firstLayout;
    private RelativeLayout secondLayout;
    private RelativeLayout thirdLayout;
    private RelativeLayout forthLayout;
    private RelativeLayout fifthLayout;
    private RelativeLayout lastLayout;

    private TextView firstTitleTv;
    private EditText firstLinkedListEdt;
    private EditText firstKEdt;
    private Button firstCreateBtn;
    private EditText firstShowResultEdt;

    private EditText secondMidEdt;
    private Button secondTransBtn;
    private EditText secondPostEdt;
    private Button secondSumBtn;
    private EditText secondShowResultEdt;

    private EditText thirdEnterQueueEdt;
    private Button thirdEnterBtn;
    private EditText thirdShowResultEdt;
    private EditText thirdShowQueueEdt;

    private EditText forthSentenceEdt;
    private Button forthCreateBTBtn;
    private Button forthLDRBtn;
    private EditText forthLDREdt;
    private boolean isCreated;
    private BinaryTreeApplication binaryTreeApp;

    private EditText fifthVertexEdt;
    private EditText fifthArcEdt;
    private Button fifthCrossBtn;
    private Button fifthJudgeBtn;
    private EditText fifthResultEdt;
    private MapApplication mapApp;

    private RecyclerView lastRecyclerView;
    public static String FILE_NAME = "";
    public static int POSITION = 0;
    public static List<String> TITLE_LIST;
    private static final int FILE_SELECT_CODE = 0X111;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        initView();
        initLastLayoutView(TITLE_LIST);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_first) {
            // Handle the camera action
            firstLayout.setVisibility(View.VISIBLE);
            secondLayout.setVisibility(View.GONE);
            thirdLayout.setVisibility(View.GONE);
            forthLayout.setVisibility(View.GONE);
            fifthLayout.setVisibility(View.GONE);
            lastLayout.setVisibility(View.GONE);
            initFirstLayoutView();
        } else if (id == R.id.nav_stack) {
            firstLayout.setVisibility(View.GONE);
            secondLayout.setVisibility(View.VISIBLE);
            thirdLayout.setVisibility(View.GONE);
            forthLayout.setVisibility(View.GONE);
            fifthLayout.setVisibility(View.GONE);
            lastLayout.setVisibility(View.GONE);
            initSecondLayoutView();
        } else if (id == R.id.nav_queue) {
            firstLayout.setVisibility(View.GONE);
            secondLayout.setVisibility(View.GONE);
            thirdLayout.setVisibility(View.VISIBLE);
            forthLayout.setVisibility(View.GONE);
            fifthLayout.setVisibility(View.GONE);
            lastLayout.setVisibility(View.GONE);
            initThirdLayoutView();
        } else if (id == R.id.nav_map) {
            firstLayout.setVisibility(View.GONE);
            secondLayout.setVisibility(View.GONE);
            thirdLayout.setVisibility(View.GONE);
            forthLayout.setVisibility(View.GONE);
            fifthLayout.setVisibility(View.VISIBLE);
            lastLayout.setVisibility(View.GONE);
            initFifthLayoutView();
        } else if (id == R.id.nav_binary_tree) {
            firstLayout.setVisibility(View.GONE);
            secondLayout.setVisibility(View.GONE);
            thirdLayout.setVisibility(View.GONE);
            forthLayout.setVisibility(View.VISIBLE);
            fifthLayout.setVisibility(View.GONE);
            lastLayout.setVisibility(View.GONE);
            initForthLayoutView();
        } else if (id == R.id.nav_last) {
            firstLayout.setVisibility(View.GONE);
            secondLayout.setVisibility(View.GONE);
            thirdLayout.setVisibility(View.GONE);
            forthLayout.setVisibility(View.GONE);
            fifthLayout.setVisibility(View.GONE);
            lastLayout.setVisibility(View.VISIBLE);
            initLastLayoutView(TITLE_LIST);
        } else if (id == R.id.nav_share) {
            openBrowser("https://github.com/ysy950803/DataStructureLabProjects");
        } else if (id == R.id.nav_exit) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initView() {
        firstLayout = (RelativeLayout) findViewById(R.id.first_layout);
        secondLayout = (RelativeLayout) findViewById(R.id.second_layout);
        thirdLayout = (RelativeLayout) findViewById(R.id.third_layout);
        forthLayout = (RelativeLayout) findViewById(R.id.forth_layout);
        fifthLayout = (RelativeLayout) findViewById(R.id.fifth_layout);
        lastLayout = (RelativeLayout) findViewById(R.id.last_layout);

        TITLE_LIST = new ArrayList<>();
        String tLArrayStr = new DataProcess(MainActivity.this).readStrData("TITLE_LIST");
        if (!tLArrayStr.equals("")) {
            tLArrayStr = tLArrayStr.replace("[", "").replace("]", "");
            String[] tLStrArray = tLArrayStr.replace(", ", ",").trim().split(",");
            Collections.addAll(TITLE_LIST, tLStrArray);
        }
    }

    private void initFirstLayoutView() {
        fab.hide();
        firstTitleTv = (TextView) findViewById(R.id.first_title_tv);
        firstLinkedListEdt = (EditText) findViewById(R.id.first_linked_list_edt);
        firstKEdt = (EditText) findViewById(R.id.first_k_edt);
        firstCreateBtn = (Button) findViewById(R.id.first_create_btn);
        firstShowResultEdt = (EditText) findViewById(R.id.first_show_result_edt);

        firstLinkedListEdt.setText("");
        firstShowResultEdt.setText("");
        firstKEdt.setText("");

        firstCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = firstLinkedListEdt.getText().toString();
                String k = firstKEdt.getText().toString();
                if (s.equals("") || k.equals(""))
                    Toast.makeText(MainActivity.this, "请按规定输入相关数据", Toast.LENGTH_SHORT).show();
                else {
                    int[] tempArray = stringToIntArray(s);
                    int K = Integer.parseInt(k);
                    LinkedListApplication linkedListApp = new LinkedListApplication(tempArray, K);
                    firstShowResultEdt.setText(linkedListApp.getAdjMax());
                }
            }
        });
    }

    private void initSecondLayoutView() {
        fab.hide();
        secondMidEdt = (EditText) findViewById(R.id.second_mid_edt);
        secondPostEdt = (EditText) findViewById(R.id.second_post_edt);
        secondSumBtn = (Button) findViewById(R.id.second_sum_btn);
        secondTransBtn = (Button) findViewById(R.id.second_trans_btn);
        secondShowResultEdt = (EditText) findViewById(R.id.second_show_result_edt);

        secondMidEdt.setText("");
        secondPostEdt.setText("");
        secondShowResultEdt.setText("");

        secondTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StackApp stackApp = new StackApp(secondMidEdt.getText().toString().trim() + "=");
                String[] tempStrArray = stackApp.getPostFix();
                String tempStr = "";
                for (int i = 0; i < tempStrArray.length; i++) {
                    if (tempStrArray[i] != null)
                        tempStr = tempStr + tempStrArray[i];
                }
                secondPostEdt.setText(tempStr.replace("=", ""));
            }
        });

        secondSumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StackApp stackApp = new StackApp(secondMidEdt.getText().toString().trim() + "=");
                secondShowResultEdt.setText(stackApp.getSum());
            }
        });
    }

    private void initThirdLayoutView() {
        fab.hide();
        thirdEnterQueueEdt = (EditText) findViewById(R.id.third_enter_queue_edt);
        thirdEnterBtn = (Button) findViewById(R.id.third_enter_btn);
        thirdShowResultEdt = (EditText) findViewById(R.id.third_show_result_edt);
        thirdShowQueueEdt = (EditText) findViewById(R.id.third_show_queue_edt);

        thirdEnterQueueEdt.setText("");
        thirdShowResultEdt.setText("");
        thirdShowQueueEdt.setText("");

        final QueueApplication queueApp = new QueueApplication();
        thirdEnterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thirdEnterQueueEdt.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "请按规定输入元素", Toast.LENGTH_SHORT).show();
                } else {
                    char q = thirdEnterQueueEdt.getText().toString().charAt(0);
                    if (q == '@') {
                        String tempStr = queueApp.quitQueueAll(queueApp.getQ());
                        if (tempStr.equals(""))
                            thirdShowResultEdt.setText("没有任何元素");
                        else
                            thirdShowResultEdt.setText("全部元素出队：" + tempStr);
                    } else if (q == '0') {
                        thirdShowResultEdt.setText("出队元素：" + queueApp.quitQueue(queueApp.getQ()));
                    } else {
                        queueApp.enterQueue(queueApp.getQ(), q);
                        thirdShowResultEdt.setText("");
                    }
                    thirdShowQueueEdt.setText(queueApp.displayAllNodes());
                    thirdEnterQueueEdt.setText("");
                }
            }
        });
    }

    private void initForthLayoutView() {
        fab.hide();
        forthSentenceEdt = (EditText) findViewById(R.id.forth_sentence_edt);
        forthCreateBTBtn = (Button) findViewById(R.id.forth_create_bt_btn);
        forthLDRBtn = (Button) findViewById(R.id.forth_ldr_btn);
        forthLDREdt = (EditText) findViewById(R.id.forth_ldr_edt);

        forthSentenceEdt.setText("");
        forthLDREdt.setText("");
        forthLDRBtn.setEnabled(false);
        isCreated = false;

        forthCreateBTBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (forthSentenceEdt.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, "句子输入有误，请重新输入！", Toast.LENGTH_SHORT).show();
                else {
                    String[] keys = stringToStringArray(forthSentenceEdt.getText().toString());
                    if (keys == null)
                        Toast.makeText(MainActivity.this, "句子输入有误，请重新输入！", Toast.LENGTH_SHORT).show();
                    else {
                        binaryTreeApp = new BinaryTreeApplication(keys);
                        Toast.makeText(MainActivity.this, "构造成功！", Toast.LENGTH_SHORT).show();
                        forthLDRBtn.setEnabled(true);
                        isCreated = true;
                    }
                }
            }
        });

        forthLDRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCreated) {
                    Toast.makeText(MainActivity.this, "请先构造二叉排序树再遍历！", Toast.LENGTH_SHORT).show();
                } else {
                    forthLDREdt.setText(binaryTreeApp.getVisitResult());
                    isCreated = false;
                }
            }
        });

    }

    private void initFifthLayoutView() {
        fab.hide();
        fifthVertexEdt = (EditText) findViewById(R.id.fifth_vertex_edt);
        fifthArcEdt = (EditText) findViewById(R.id.fifth_arc_edt);
        fifthCrossBtn = (Button) findViewById(R.id.fifth_cross_btn);
        fifthJudgeBtn = (Button) findViewById(R.id.fifth_judge_btn);
        fifthResultEdt = (EditText) findViewById(R.id.fifth_result_edt);

        fifthVertexEdt.setText("");
        fifthArcEdt.setText("");
        fifthResultEdt.setText("");
        fifthJudgeBtn.setEnabled(false);
        isCreated = false;

        fifthCrossBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
                mapApp = new MapApplication(stringToIntArray(fifthVertexEdt.getText().toString()), stringToArcArray(fifthArcEdt.getText().toString()));
                Toast.makeText(MainActivity.this, "成功创建有向图的十字链表！", Toast.LENGTH_SHORT).show();
                fifthJudgeBtn.setEnabled(true);
                isCreated = true;
//                } catch (Exception e) {
//                    Log.d("TEST", "Exception:" + e);
//                }
            }
        });

        fifthJudgeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCreated) {
                    Toast.makeText(MainActivity.this, "请先创建十字链表再判断！", Toast.LENGTH_SHORT).show();
                } else {
                    boolean temp = mapApp.isTopology();
                    if (temp) {
                        fifthResultEdt.setText("This graph has not cycle.\n" + "All Outputs:" + mapApp.getOutputVexStr());
                    } else
                        fifthResultEdt.setText("This graph has cycle.");
                    isCreated = false;
                }
            }
        });
    }

    private void initLastLayoutView(final List<String> titleList) {
        fab.show();
        lastRecyclerView = (RecyclerView) findViewById(R.id.last_recyclerView);
        lastRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ListViewAdapter listAdapter = new ListViewAdapter(titleList);
        listAdapter.setListOnItemClickListener(new ListOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FILE_NAME = titleList.get(position);
                POSITION = position;
                startActivity(new Intent(MainActivity.this, SearchResultActivity.class));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        lastRecyclerView.setAdapter(listAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initLastLayoutView(TITLE_LIST);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (TITLE_LIST.size() == 0)
            new DataProcess(MainActivity.this).saveData("TITLE_LIST", "");
        else
            new DataProcess(MainActivity.this).saveData("TITLE_LIST", TITLE_LIST.toString()); // toString后的格式[XXX, XXX...]
    }

    public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ListViewHolder> {

        //        public List<String> card_details_list;
        public List<String> card_title_list;

        private ListOnItemClickListener mOnItemClickListener;

        public void setListOnItemClickListener(ListOnItemClickListener mOnItemClickListener) {
            this.mOnItemClickListener = mOnItemClickListener;
        }

        public ListViewAdapter(List<String> title_list) {
//            this.card_details_list = details_list;
            this.card_title_list = title_list;
        }

        class ListViewHolder extends RecyclerView.ViewHolder {
            //            private final TextView mDetailText;
            private final TextView mTitleText;

            public ListViewHolder(View itemView) {
                super(itemView);
//                mDetailText = (TextView) itemView.findViewById(R.id.card_details);
                mTitleText = (TextView) itemView.findViewById(R.id.card_title);
            }
        }

        @Override
        public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ListViewHolder(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.last_app_list_item, parent, false));
        }

        @Override
        public int getItemCount() {
            return card_title_list.size();
        }

        @Override
        public void onBindViewHolder(final ListViewHolder viewHolder, int position) {
//            viewHolder.mDetailText.setText(card_details_list.get(position));
            viewHolder.mTitleText.setText(card_title_list.get(position));

            // 如果设置了回调，则设置点击事件
            if (mOnItemClickListener != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = viewHolder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(viewHolder.itemView, pos);
                    }
                });

                viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = viewHolder.getLayoutPosition();
                        mOnItemClickListener.onItemLongClick(viewHolder.itemView, pos);
                        return false;
                    }
                });
            }

        }
    }

    /**
     * Created by 姚圣禹 on 2016/3/11.
     * 自定义接口，然后在onBindViewHolder中去为holder.itemView去设置相应的监听最后回调设置的监听
     */
    public interface ListOnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    /**
     * 调用文件管理器来选择文件
     **/
    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent, "请选择一个要添加的文本文件"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(MainActivity.this, "请安装文件管理器", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 回调获取绝对路径
     **/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) { // 是否选择，没选择就不会继续
            Uri uri = data.getData();
            TITLE_LIST.add(uri.toString().replace("file://", ""));
        }
    }

    private int[] stringToIntArray(String s) {
        String[] tempStr = s.trim().split(" ");
        int[] tempArray = new int[tempStr.length];
        for (int i = 0; i < tempStr.length; ++i)
            tempArray[i] = Integer.parseInt(tempStr[i]);
        return tempArray;
    }

    private String[] stringToStringArray(String s) {
        String[] tempStr = s.trim().split(" ");
        String[] tempStrArray = tempStr;
        for (int i = 0; i < tempStr.length; ++i) { // 循环判断句号是否出现在错误的位置
            if (i != tempStr.length - 1 && tempStr[i].contains(".")) {
                tempStrArray = null;
                break;
            }
            if (i == tempStr.length - 1) {
                String lastKey = tempStr[i];
                for (int j = 0; j < lastKey.length(); ++j) {
                    if (lastKey.charAt(j) == '.' && j != lastKey.length() - 1) {
                        tempStrArray = null;
                        break;
                    } else if (j == lastKey.length() - 1 && lastKey.charAt(j) != '.') {
                        tempStrArray = null;
                        break;
                    }
                }
            }
        }
        return tempStrArray;
    }

    private Arc[] stringToArcArray(String s) {
        String[] tempStr = s.trim().split(" ");
        Arc[] arcs = new Arc[tempStr.length];
        int u, v;

        int i = 0;
        while (i < tempStr.length) {
            String[] uv = tempStr[i].split(",");
            u = Integer.parseInt(uv[0]);
            v = Integer.parseInt(uv[1]);
            arcs[i] = new Arc(u, v);
            i++;
        }

        return arcs;
    }

    private void openBrowser(String URL) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(URL));
        startActivity(intent);
    }
}
