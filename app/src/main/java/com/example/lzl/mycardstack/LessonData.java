package com.example.lzl.mycardstack;

import android.support.v7.widget.RecyclerView;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by LZL on 2017/7/16.
 */

public class LessonData {
    String lessonName;
    String lessonScore;

    public LessonData(String lessonName, String lessonScore) {
        this.lessonName = lessonName;
        this.lessonScore = lessonScore;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonScore() {
        return lessonScore;
    }

    public void setLessonScore(String lessonScore) {
        this.lessonScore = lessonScore;
    }
}
