package com.yy.dome.entity;

import java.util.List;

public class Groupsinfo {

    public List<PrestigiousSchoolInfo> getChildList() {
        return childList;
    }

    public void setChildList(List<PrestigiousSchoolInfo> childList) {
        this.childList = childList;
    }

    public List<PrestigiousSchoolInfo> childList;
    private String id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Groupsinfo(String id,String name) {
        this.id=id;
        this.name=name;

    }
}
