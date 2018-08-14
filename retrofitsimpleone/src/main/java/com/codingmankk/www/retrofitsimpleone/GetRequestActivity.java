package com.codingmankk.www.retrofitsimpleone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codingmankk.www.retrofitsimpleone.Bean.Translation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRequestActivity extends AppCompatActivity {

    private TextView mTranslationTV;
    private EditText mEt;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });

    }

    private void initView() {
        mTranslationTV = (TextView) findViewById(R.id.id_tv);
        mEt = (EditText) findViewById(R.id.id_et);
        mBtn = (Button) findViewById(R.id.id_btnTranslation);

    }

    private void request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final GetRequestInterface request = retrofit.create(GetRequestInterface.class);
        Call<Translation> call = request.getCall(mEt.getText().toString());

        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                if (response.isSuccessful()){
                    Translation body = response.body();
                    String out = body.content.getOut();
                    mTranslationTV.setText(out);
                }
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                Log.i("retrofit","网络请求失败");
            }
        });

    }
}
