package com.sequoiahack.storylead.controller.data.tablemodels;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Table model for CallData
 * Created by zac on 10/09/16.
 */

@Table(name = "CallData")
public class CallData extends Model {

    @Column(name = "filename")
    public String filename;

    @Column(name = "name")
    public String name;

    @Column(name = "number")
    public String number;

    @Column(name = "status")
    public String status;

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

    @Column(name = "summary")
    public String summary;

    public CallData() {
        super();
    }

    public CallData(String filename, String name, String number, String status, String text, String sentiment, String emotion, String politicalleaning, String topics, String summary) {
        super();
        this.filename = filename;
        this.name = name;
        this.number = number;
        this.status = status;
        this.text = text;
        this.sentiment = sentiment;
        this.emotion = emotion;
        this.politicalleaning = politicalleaning;
        this.topics = topics;
        this.summary = summary;
    }
}

