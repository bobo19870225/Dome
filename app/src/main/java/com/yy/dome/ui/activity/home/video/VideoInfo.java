package com.yy.dome.ui.activity.home.video;

public class VideoInfo {
    private String id;
    private int image;
    private String name;
    private String contents;
    private String Url;

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
    public void setContents(String contents){
        this.contents = contents;
    }
    public String getContents(){
        return this.contents;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getUrl() {
        return Url;
    }

    public VideoInfo(String id, String name, String contents, int image ,String Url) {
        this.id=id;
        this.name=name;
        this.contents=contents;
        this.image=image;
        this.Url=Url;
    }

}
