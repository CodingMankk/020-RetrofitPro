package com.codingmankk.www.retrofitsimple2;


import com.codingmankk.www.retrofitsimple2.bean.Translation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * ================================================
 * 版    本：
 * 创建日期：
 * 描    述：
 * 修订历史：
 * ================================================
 */
public interface PostInterface {

    //采用@Post表示Post方法进行请求（传入部分url地址）
    // 采用@FormUrlEncoded注解的原因:API规定采用请求格式x-www-form-urlencoded,即表单形式
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded  //采用表单形式
    Call<Translation> getCall(@Field("i") String targetSentence);  //需要配合@Field 向服务器提交需要的字段'

    //https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534324157411&di=07014e6b6c22c3cb7abdb5fe82a1ec35&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F5bafa40f4bfbfbedc5597ab474f0f736aec31ffc.jpg
    //加载图片
    @GET("timg?image&quality=80&size=b9999_10000&sec=1534324157411&di=07014e6b6c22c3cb7abdb5fe82a1ec35&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F5bafa40f4bfbfbedc5597ab474f0f736aec31ffc.jpg")
    Call<ResponseBody> getImageView();

}
