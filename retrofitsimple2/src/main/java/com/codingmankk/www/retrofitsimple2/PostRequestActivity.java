package com.codingmankk.www.retrofitsimple2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingmankk.www.retrofitsimple2.bean.Translation;
import com.orhanobut.logger.Logger;

import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRequestActivity extends AppCompatActivity {


    private TextView mTranslationTV;
    private EditText mEt;
    private Button mBtn;
    private Button mBtnLoadImageView;
    private ImageView mIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

//        Logger.init("PostRequestActivity");


        initView();

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });

        mBtnLoadImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestImage();
            }
        });

    }

    private void initView() {
        mTranslationTV = (TextView) findViewById(R.id.id_tv);
        mEt = (EditText) findViewById(R.id.id_et);
        mBtn = (Button) findViewById(R.id.id_btnTranslation);
        mBtnLoadImageView = (Button) findViewById(R.id.id_btnLoadImageView);
        mIV = (ImageView) findViewById(R.id.id_iv);


    }

    private void request() {

//        Call<Translation> call = request.getCall("I Love you");
        Call<Translation> call = initRetrofit().getCall(mEt.getText().toString());

        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                if (response.isSuccessful()){
                    Logger.i("=================");
                    Logger.i(response.toString());
                    Logger.i(response.body().getTranslateResult().get(0).get(0).getTgt());
//                    mTranslationTV.setText(response.toString());
                    mTranslationTV.setText(response.body().getTranslateResult().get(0).get(0).getTgt());
                }
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                Logger.i("网络请求错误！");
            }
        });
    }

    private PostInterface initRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建网络请求接口 的实例
        PostInterface request = retrofit.create(PostInterface.class);
        return request;

    }

    public void requestImage(){

        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534324157411&di=07014e6b6c22c3cb7abdb5fe82a1ec35&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F5bafa40f4bfbfbedc5597ab474f0f736aec31ffc.jpg";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://timgsa.baidu.com/")
                .build();

        // 步骤5:创建网络请求接口 的实例
        PostInterface request = retrofit.create(PostInterface.class);

        Call<ResponseBody> call = request.getImageView();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    InputStream in = response.body().byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    mIV.setImageBitmap(bitmap);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
