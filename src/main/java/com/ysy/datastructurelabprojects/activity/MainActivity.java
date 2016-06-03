package com.ysy.datastructurelabprojects.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ysy.datastructurelabprojects.R;
import com.ysy.datastructurelabprojects.entity.first_linkedList.LinkedListApplication;
import com.ysy.datastructurelabprojects.entity.second_stack.StackApp;
import com.ysy.datastructurelabprojects.entity.thrid_queue.QueueApplication;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFirstLayoutView();
        initThirdLayoutView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
            forthLayout.setVisibility(View.VISIBLE);
            fifthLayout.setVisibility(View.GONE);
            lastLayout.setVisibility(View.GONE);
        } else if (id == R.id.nav_binary_tree) {
            firstLayout.setVisibility(View.GONE);
            secondLayout.setVisibility(View.GONE);
            thirdLayout.setVisibility(View.GONE);
            forthLayout.setVisibility(View.GONE);
            fifthLayout.setVisibility(View.VISIBLE);
            lastLayout.setVisibility(View.GONE);
        } else if (id == R.id.nav_last) {
            firstLayout.setVisibility(View.GONE);
            secondLayout.setVisibility(View.GONE);
            thirdLayout.setVisibility(View.GONE);
            forthLayout.setVisibility(View.GONE);
            fifthLayout.setVisibility(View.GONE);
            lastLayout.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

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
    }

    private void initFirstLayoutView() {
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

    private int[] stringToIntArray(String s) {
        String[] tempStr = s.trim().split(" ");
        int[] tempArray = new int[tempStr.length];
        for (int i = 0; i < tempStr.length; ++i) {
            tempArray[i] = Integer.parseInt(tempStr[i]);
        }
        return tempArray;
    }

}
