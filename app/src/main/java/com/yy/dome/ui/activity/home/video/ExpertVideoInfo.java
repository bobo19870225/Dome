package com.yy.dome.ui.activity.home.video;

public class ExpertVideoInfo {

        private String id;

        private String goodsId;

        private String flag;

        private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

    private String cover;

        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return this.id;
        }
        public void setGoodsId(String goodsId){
            this.goodsId = goodsId;
        }
        public String getGoodsId(){
            return this.goodsId;
        }
        public void setFlag(String flag){
            this.flag = flag;
        }
        public String getFlag(){
            return this.flag;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }



}
