package com.sequoiahack.storylead.controller.data.tablemodels;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Table model for Missed Call
 * Created by zac on 10/09/16.
 */

@Table(name = "MissedCall")
public class CallData extends Model {

    @Column(name = "filename")
    public String filename;

    @Column(name = "analyzed")
    public String analyzed;

    @Column(name = "text")
    public String text;

    @Column(name = "sentiment")
    public String sentiment;

    @Column(name = "emotion")
    public String emotion;

    @Column(name = "politicalleaning")
    public String politicalleaning;

    @Column(name = "topics")
    public String topics;

    public CallData() {
        super();
    }

    public CallData(String filename, String analyzed, String text, String sentiment, String emotion, String politicalleaning, String topics) {
        super();
        this.filename = filename;
        this.analyzed = analyzed;
        this.text = text;
        this.sentiment = sentiment;
        this.emotion = emotion;
        this.politicalleaning = politicalleaning;
        this.topics = topics;
    }
}

