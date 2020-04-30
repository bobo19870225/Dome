package com.yy.dome.entity.home;

public class RugbyPlayerInfo {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    private String money;

    public String getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    private  String studyYear;

    public String getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
    }

    private  String planNumber;

    public RugbyPlayerInfo(String id, String name,String planNumber,String studyYear,String money) {
        this.id = id;
        this.name = name;
        this.planNumber = planNumber;
        this.studyYear = studyYear;
        this.money = money;

    }

}
