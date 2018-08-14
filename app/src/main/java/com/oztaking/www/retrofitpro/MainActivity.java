package com.oztaking.www.retrofitpro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.oztaking.www.retrofitpro.simple0.GetRequestInterface;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        requestDemo1();
    }

    /**
     * [1] Retrofit把 网络请求的URL 分成了两部分设置
     *
     * 第1部分：在网络请求接口的注解设置
     * @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
     * Call<Translation> getCall();
     */
    private void requestDemo1() {
        // 第2部分：在创建Retrofit实例时通过.baseUrl()设置
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("www.baidu.com") //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();
    }

    /**
     * [3] FormUrlEncoded 注解的使用
     */
    private void requestDemo2(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("www.baidu.com") //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();

        GetRequestInterface service = retrofit.create(GetRequestInterface.class);
        //FormUrlEncoded 注解的使用
        service.getCallFormUrlEncoded3("xiaoming",24);

    }


    /**
     * [3] Multipart 注解的使用
     */
    private void requestMultipart3(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("www.baidu.com") //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();

        GetRequestInterface service = retrofit.create(GetRequestInterface.class);
        MediaType type = MediaType.parse("text/plain");
        RequestBody name = RequestBody.create(type, "XiaMing");
        RequestBody age = RequestBody.create(type, "24");

        MediaType streamType = MediaType.parse("application/octet-stream");
        RequestBody file = RequestBody.create(streamType, "模拟文件内容");
        MultipartBody.Part file2 = MultipartBody.Part.createFormData("file", "text.txt", file);

        service.getCall4(name,age,file2);

    }

    /**
     * [4] Field & FieldMap 使用
     */
    private void requestFieldFieldMap(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("www.baidu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetRequestInterface service = retrofit.create(GetRequestInterface.class);
        Call<RequestBody> response = service.postFormUrlField("xiaD", 24);

        HashMap<String, Object> map = new HashMap<>();
        map.put("username","xiaD");
        map.put("age",24);
        Call<RequestBody> responseFieldMap = service.getFormUrlFieldMap(map);

    }


    /**
     * [5] Part-PartMap
     */
    private void requestPartPartMap() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("www.baidu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetRequestInterface service = retrofit.create(GetRequestInterface.class);


        MediaType TextType = MediaType.parse("text-plain");
        MediaType streamType = MediaType.parse("application/octet-stream");

        RequestBody name = RequestBody.create(TextType, "xiaD");
        RequestBody age = RequestBody.create(TextType, "24");
        RequestBody file = RequestBody.create(streamType, "模拟文件内容");

        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "text.txt", file);

        //Part
        Call<RequestBody> response = service.postPart(name, age,filePart);

        //PartMap
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("name",name);
        map.put("age",age);
        Call<RequestBody> call = service.postPartMap(map, filePart);


        //异步发送
        call.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                //请求处理
                response.body().toString();
            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {

            }
        });

        //同步发送
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
