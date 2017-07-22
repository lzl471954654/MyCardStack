package com.example.lzl.mycardstack;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.StackAdapter;

import java.util.List;

/**
 * Created by LZL on 2017/7/16.
 */

public class ScoresCardStackAdapter extends StackAdapter<Integer> {
    //Context mContext;
    List<List<LessonData>> lessonList;
    public ScoresCardStackAdapter(Context context)
    {
        super(context);
        //mContext = context;
    }


    public void updateData(List data,List<List<LessonData>> lessonList) {
        this.lessonList = lessonList;
        updateData(data);
        System.out.println("Updata!");
    }

    @Override
    protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scores_card_item,parent,false);
        CardViewHolder holder = new CardViewHolder(view);
        System.out.println("onCreateView");
        return holder;
    }

    @Override
    public void bindView(Integer data, int position, CardStackView.ViewHolder holder) {
        if(holder instanceof CardViewHolder)
        {
            CardViewHolder cardHolder = (CardViewHolder)holder;
            cardHolder.onBind(data,position,lessonList);
        }
        System.out.println("bindView");
    }


    @Override
    public int getItemCount() {
        System.out.println("getItemCount");
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        System.out.println("getItemViewType");
        return super.getItemViewType(position);
    }

    @Override
    public Integer getItem(int position) {
        System.out.println("getItem");
        return super.getItem(position);
    }

    public static class CardViewHolder extends CardStackView.ViewHolder
    {
        View root;
        FrameLayout cardTitle;
        RecyclerView scoreList;
        TextView titleText;
        public CardViewHolder(View view)
        {
            super(view);
            root = view;
            cardTitle = (FrameLayout)view.findViewById(R.id.card_title);
            titleText = (TextView)view.findViewById(R.id.card_title_text);
            scoreList = (RecyclerView)view.findViewById(R.id.scores_list);
            System.out.println("CardViewHolder constructor");
        }

        public void onBind(Integer backgroundColorId,int position,List<List<LessonData>> dataList)
        {
            cardTitle.getBackground().setColorFilter(ContextCompat.getColor(getContext(),backgroundColorId), PorterDuff.Mode.SRC_IN);
            ScoresListAdapter adapter = new ScoresListAdapter(dataList.get(position));
            scoreList.setLayoutManager(new LinearLayoutManager(getContext()));
            scoreList.setAdapter(adapter);
            System.out.println("holder onBind");
        }

        @Override
        public void onItemExpand(boolean b) {
            scoreList.setVisibility(b ? View.VISIBLE : View.GONE);
            System.out.println("holder onItemExpand");
        }
    }
}
