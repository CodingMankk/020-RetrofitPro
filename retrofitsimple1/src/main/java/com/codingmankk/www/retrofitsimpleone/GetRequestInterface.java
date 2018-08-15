package com.codingmankk.www.retrofitsimpleone;


import com.codingmankk.www.retrofitsimpleone.Bean.Translation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ================================================
 * 版    本：
 * 创建日期：
 * 描    述：
 * 修订历史：
 * ================================================
 */
public interface GetRequestInterface {


    /**
     * getCall()是接受网络请求数据的方法
     * @return
     */
    @GET("ajax.php?a=fy&f=auto&t=auto&w=Good%20Bye")
    Call<Translation> getCall(@Query("w") String w);

}
