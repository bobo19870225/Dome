package com.yy.dome.entity;

public class TextViewInfo {
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

    public TextViewInfo(String id,String name) {
        this.id=id;
        this.name=name;

    }

}
