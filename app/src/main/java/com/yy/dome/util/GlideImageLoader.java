package com.yy.dome.util;

import android.content.Context;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;
import com.yy.dome.api.Api;
import com.yy.dome.entity.home.ArticleInfo;
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        //Glide 加载图片简单用法
       /// Glide.with(context).load(url+((ArticleInfo.DataBean)path).getTitleImg()).into(imageView);
        Glide.with(context).load(Api.URLs +((ArticleInfo.Data)path).getTitleImg()).transform(new GlideRoundTransform(context, 10)).into(imageView);
        //Picasso.with(context).load(url+((ArticleInfo.DataBean)path).getTitleImg()).transform(new PicassoRoundTransform()).into(imageView);
/*        Uri uri = Uri.parse((String) path);
        imageView.setImageURI(uri);*/
    }

    @Override
    public ImageView createImageView(Context context) {
        return new ImageView(context);
    }


}
