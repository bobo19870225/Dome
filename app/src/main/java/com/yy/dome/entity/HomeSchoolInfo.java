package com.yy.dome.entity;

public class HomeSchoolInfo {
    private String id;
    private int image;
    private String name;
    private String city;

    public void setImage(int image){
        this.image = image;
    }
    public int getImage(){
        return this.image;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setCity(String token){
        this.city = token;
    }
    public String getCity(){
        return this.city;
    }



    public HomeSchoolInfo(String id,String name,String city,int image) {
        this.id=id;
        this.name=name;
        this.city=city;
        this.image=image;
    }

}
