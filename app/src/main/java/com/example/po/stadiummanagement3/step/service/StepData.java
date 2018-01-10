package com.example.po.stadiummanagement3.step.service;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by 田雍恺 on 2017/12/19.
 */

@Table("step")
public class StepData {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    @Column("today")
    private String tody;
    @Column("step")
    private String step;
    @Column("previousStep")
    private String previousStep;

    public void setId(int id){
        this.id = id;
    }

    public void setToday(String tody) {
        this.tody = tody;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public void setPreviousStep(String previousStep) {
        this.previousStep = previousStep;
    }

    public int getId(){
        return id;
    }

    public String getTody(){
        return tody;
    }

    public String getStep(){
        return step;
    }

    public String getPreviousStep() {
        return previousStep;
    }
}
