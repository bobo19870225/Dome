package com.yy.dome.ui.activity.school.adapter;

public class BeanInfo {
    private String id;

    private String batch;

    private String subject;

    private String name;

    private String money;

    private String planNumber;

    private String studyYear;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setBatch(String batch){
        this.batch = batch;
    }
    public String getBatch(){
        return this.batch;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }
    public String getSubject(){
        return this.subject;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setMoney(String money){
        this.money = money;
    }
    public String getMoney(){
        return this.money;
    }
    public void setPlanNumber(String planNumber){
        this.planNumber = planNumber;
    }
    public String getPlanNumber(){
        return this.planNumber;
    }
    public void setStudyYear(String studyYear){
        this.studyYear = studyYear;
    }
    public String getStudyYear(){
        return this.studyYear;
    }

    public BeanInfo(String id,String batch,String subject,String name,String money,String planNumber,String studyYear) {
        this.id=id;
        this.batch=batch;
        this.subject=subject;
        this.name=name;
        this.batch=money;
        this.subject=planNumber;
        this.name=studyYear;
    }
}
