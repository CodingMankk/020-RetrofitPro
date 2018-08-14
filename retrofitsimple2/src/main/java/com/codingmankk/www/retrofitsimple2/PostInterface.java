package com.codingmankk.www.retrofitsimple2;


import com.codingmankk.www.retrofitsimple2.bean.Translation;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded  //采用表单形式
    Call<Translation> getCall(@Field("i") String targetSentence);  //需要配合@Field 向服务器提交需要的字段


}
