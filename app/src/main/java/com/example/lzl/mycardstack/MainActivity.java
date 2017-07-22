package com.example.lzl.mycardstack;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.loopeer.cardstack.AllMoveDownAnimatorAdapter;
import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.UpDownAnimatorAdapter;
import com.loopeer.cardstack.UpDownStackAnimatorAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CardStackView.ItemExpendListener{
    private CardStackView mCardStack;



    Integer[] color = {
            R.color.holo_blue_bright,
            R.color.holo_orange_light,
            R.color.holo_purple,
            R.color.holo_red_light
    };
    String[] name = {"数据结构","计算机网络","编译原理","C语言","算法设计","FPGA编程"};
    String[] scores = {"77","87","65","98","74","80"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    public void onItemExpend(boolean expend) {

    }

    void init()
    {
        mCardStack = (CardStackView)findViewById(R.id.cardStackView);
        ScoresCardStackAdapter adapter = new ScoresCardStackAdapter(this);
        mCardStack.setAdapter(adapter);

        mCardStack.setItemExpendListener(this);
        List<List<LessonData>> lists = new LinkedList<>();
        for(int i = 0;i<4;i++)
        {
            List<LessonData> list = new LinkedList<>();
            for(int j = 0;j<6;j++)
            {
                list.add(new LessonData(name[j],scores[j]));
            }
            lists.add(list);
        }

        adapter.updateData(Arrays.asList(color),lists);
        //mCardStack.setAnimatorAdapter(new AllMoveDownAnimatorAdapter(mCardStack));
        //mCardStack.setAnimatorAdapter(new UpDownAnimatorAdapter(mCardStack));
        //mCardStack.setAnimatorAdapter(new UpDownStackAnimatorAdapter(mCardStack));
    }
}
