package com.example.lzl.mycardstack;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by LZL on 2017/7/16.
 */

public class ScoresListAdapter extends RecyclerView.Adapter<ScoresListAdapter.ScoresViewHodler> {
    List<LessonData> scores_list;

    public ScoresListAdapter(List<LessonData> list)
    {
        scores_list = list;
    }

    public static class ScoresViewHodler extends RecyclerView.ViewHolder
    {
        TextView name;          //课程名称
        TextView high_scores;   //最高分
        TextView daily_scores;  //平时成绩
        TextView type;          //课程类型
        TextView credit;        //学分
        TextView middle_scores; //期中成绩
        TextView final_scores;  //期末成绩
        public ScoresViewHodler(View view)
        {
            super(view);
            name = (TextView)view.findViewById(R.id.scores_lesson_name);
            high_scores = (TextView)view.findViewById(R.id.scores_lesson_score);
            daily_scores = (TextView)view.findViewById(R.id.scores_lesson_pingshi);
            type = (TextView)view.findViewById(R.id.scores_lesson_type);
            credit = (TextView)view.findViewById(R.id.scores_lesson_xf);
            middle_scores = (TextView)view.findViewById(R.id.scores_lesson_qzcj);
            final_scores = (TextView)view.findViewById(R.id.scores_lesson_qmcj);
        }
    }

    @Override
    public void onBindViewHolder(ScoresViewHodler holder, int position) {
        holder.name.setText(scores_list.get(position).getLessonName());
        holder.high_scores.setText(scores_list.get(position).getLessonScore());
    }

    @Override
    public ScoresViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scores_list_item,parent,false);
        return new ScoresViewHodler(view);
    }

    @Override
    public int getItemCount() {
        return scores_list.size();
    }
}
