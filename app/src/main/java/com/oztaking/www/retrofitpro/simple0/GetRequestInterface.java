package com.oztaking.www.retrofitpro.simple0;

import com.oztaking.www.retrofitpro.simple0.Bean.User;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

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
     * [1]--->@GET注解的作用:采用Get方法发送网络请求
     *
     *getCall() = 接收网络请求数据的方法
     *其中返回类型为Call<*>，*是接收数据的类（即上面定义的Translation类）
     *如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>
     */
    @GET("www.codingmankk.com")
    Call<Reception> getCall();

    /**
     * [2]--->Http 注解
     *
     * method：网络请求的方法（区分大小写）
     * path：网络请求地址路径
     * hasBody：是否有请求体
     * {id} 表示是一个变量
     * method 的值 retrofit 不会做处理，所以需要自行保证准确
     */
    @HTTP(method = "GET", path = "blog/{id}",hasBody = false)
    Call<Reception> getCall2(@Path("id") int id);


    /**
     * [3]---> FormUrlEncoded 表示发送的是from-encoded数据
     *
     * 每个键值对需要用@Filed来注解键名，随后的对象需要提供值
     *
     * 表明是一个表单格式的请求（Content-Type:application/x-www-form-urlencoded）
     * Field("username")表示将后面的 String name中name的取值作为 username 的值
     */

    @POST("/form")
    @FormUrlEncoded
    Call<Reception> getCallFormUrlEncoded3(@Field("username") String name, @Field("args") int age);

    /**
     * [4]---> @Multipart
     *
     * 作用：表示发送form-encoded的数据（适用于 有文件 上传的场景）
     * 每个键值对需要用@Part来注解键名，随后的对象需要提供值
     *
     * {Part} 后面支持三种类型，{RequestBody}、{okhttp3.MultipartBody.Part} 、任意类型
     *
     * 除{okhttp3.MultipartBody.Part} 以外，其它类型都必须带上表单字段({okhttp3.MultipartBody.Part}中已经包含了表单字段的信息)
     */
    @POST("/form")
    @Multipart
    Call<RequestBody> getCall4(@Part("name") RequestBody name, @Part("age") RequestBody age, @Part MultipartBody.Part file);


    /**
     * [5] 添加请求头：Header 添加请求头 /Headers  添加不固定的请求头
     *
     * 区别在于使用场景和使用方式
     * 1. 使用场景：@Header用于添加不固定的请求头，@Headers用于添加固定的请求头
     * 2. 使用方式：@Header作用于方法的参数；@Headers作用于方法
     *
     */

    @GET("user")
    Call<User> getUser(@Header("Authorization") String authorization);

    @Headers("Authorization:authorization")
    @GET("user")
    Call<User> getUser();


    /**
     * [6] Body 作用：以 Post方式 传递 自定义数据类型 给服务器
     * 不过Map要经过 FormBody.Builder 类处理成为符合 Okhttp 格式的表单
     *
     * 特别注意：如果提交的是一个Map，那么作用相当于 @Field
     */

    //    FormBody.Builder builder = new FormBody.Builder();
    //    builder.add("key","value");

    @POST("list/testJson.action")
    Call<User> postUser2(@Body HashMap<String,String> user);


    /**
     * [7] Field & FieldMap
     *  作用：发送 Post请求 时提交请求的表单字段
     */

    /**
     * [7-1]表明是一个表单格式的请求（Content-Type:application/x-www-form-urlencoded）
     * Field("username") 表示将后面的 String name 中name的取值作为 username 的值
     */
    @POST("/form")
    @FormUrlEncoded
    Call<RequestBody> postFormUrlField(@Field("username") String name, @Field("age") int  age);


    /**
     * [7-2]Map的key作为表单的键
     * @param map
     * @return
     */
    @POST("/form")
    @FormUrlEncoded
    Call<RequestBody> getFormUrlFieldMap(@FieldMap Map<String,Object> map);


    /**
     * [8] Part-PartMap
     * 作用：发送 Post请求时提交请求的表单字段
     * 与@Field的区别：功能相同，但携带的参数类型更加丰富，包括数据流，所以适用于 有文件上传 的场景
     *
     * 具体使用：与 @Multipart 注解配合使用
     */

    /**
     * [8-1]
     * {Part} 后面支持三种类型，{RequestBody}、{okhttp3.MultipartBody.Part} 、任意类型
     * 除 {@link okhttp3.MultipartBody.Part} 以外，其它类型都必须带上表单字段({okhttp3.MultipartBody.Part} 中已经包含了表单字段的信息)，
     */
    @POST("/form")
    @Multipart
    Call<RequestBody> postPart(@Part("name") RequestBody name,@Part("age") RequestBody age,@Part MultipartBody.Part file);

    /**
     * [8-2]
     * PartMap 注解支持一个Map作为参数，支持 {RequestBody } 类型，
     * 如果有其它的类型，会被{retrofit2.Converter}转换，
     * 如后面会介绍的 使用{com.google.gson.Gson} 的
     * {retrofit2.converter.gson.GsonRequestBodyConverter}
     * 所以{MultipartBody.Part} 就不适用了,所以文件只能用 @Part MultipartBody.Part
     */

    @POST("/form")
    @Multipart
    Call<RequestBody> postPartMap(@PartMap Map<String,RequestBody> args,@Part MultipartBody.Part file);

    @POST("/form")
    @Multipart
    Call<RequestBody> postPartMap2(@PartMap Map<String,RequestBody> args);


    /**
     * [9] Query-QueryMap
     *  作用：用于 @GET 方法的查询参数（Query = Url 中 ‘?’ 后面的 key-value）
     *  如：url = http://www.println.net/?cate=android，其中，Query = cate
     *
     *  具体使用：配置时只需要在接口方法中增加一个参数即可
     */

    @GET("/")
    Call<String> getQuery(@Query("cate") String cate);

    @GET("/")
    Call<String> getQueryMap(@QueryMap HashMap<String,String> map);


    /**
     * [10] Path
     * 作用：URL地址的缺省值
     *
     * 例：访问的API是：https://api.github.com/users/{user}/repos
     *  在发起请求时， {user} 会被替换为方法的第一个参数 user（即@Path注解的user）
     */
    @GET("users/{user}/repos")
    Call<RequestBody> getPath(@Path("user") String user);


    /**
     * [11] Url
     * 作用：直接传入一个请求的 URL变量 用于URL设置
     *
     * 注：
     * [1]当有URL注解时，@GET传入的URL就可以省略
     * [2]当GET、POST...HTTP等方法中没有设置Url时，则必须使用 {Url}提供
     */
    Call<RequestBody> getUrlQuery(@Url String url,@Query("cate") String cate);


}
