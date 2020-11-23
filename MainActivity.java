package com.example.webscrapping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class
MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText input,input2;
    Button btn,btn2,clear;
    TextView output,output2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.user_input);
        clear = findViewById(R.id.clear);
        btn = findViewById(R.id.click_btn);
        output = findViewById(R.id.text_output);
        input2 = findViewById(R.id.user_input2);
        btn2 = findViewById(R.id.click_btn2);
        output2 = findViewById(R.id.text_output2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadalbum();

//                input2.setVisibility(View.VISIBLE);
//                btn2.setVisibility(View.VISIBLE);

                output2.setVisibility(View.VISIBLE);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadalbum3();
                input.setText("");
                input2.setText("");
                output.setText("");
                output2.setText("");
                input2.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.INVISIBLE);
                output2.setVisibility(View.INVISIBLE);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadalbum2();
            }
        });
    }

    private void uploadalbum() {

        OkHttpClient.Builder okClient = new OkHttpClient.Builder();
        okClient.connectTimeout(60000, TimeUnit.MILLISECONDS);
        okClient.writeTimeout(60000, TimeUnit.MILLISECONDS);
        okClient.readTimeout(60000, TimeUnit.MILLISECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okClient.interceptors().add(interceptor);

        okClient.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response response = chain.proceed(chain.request());
                response.newBuilder()
                        .header("Cache-Control", "only-if-cached")
                        .build();
                return response;
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gunjan05.pythonanywhere.com/")
                .client(okClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();




        FileUploadService client2 = retrofit.create(FileUploadService.class);
        Call<output> call = client2.ursend(input.getText().toString().trim());

        call.enqueue(new Callback<output>() {
            @Override
            public void onResponse(Call<output> call, Response<output> response) {
                Log.e(TAG, "onResponse:response==> " + response.isSuccessful() + "==>" + response.errorBody());

                if (response.isSuccessful()) {
                    Log.e(TAG, "onResponse: ==>+" + new Gson().toJson(response.body()));

                    StringBuilder stringBuilder = new StringBuilder();


                    for(String key : response.body().getKeywords()){

                        stringBuilder.append("\n\n"+key);

                    }

                    StringBuilder stringBuilder2 = new StringBuilder();

                    for(String key2 : response.body().getContent()){

                        stringBuilder2.append("\n\n"+key2);

                    }

                    output.setText( stringBuilder);
                    output2.setText( stringBuilder2 );

                    //output.setText(response.body().getKeywords());

                } else {

                }
            }

            @Override
            public void onFailure(Call<output> call, Throwable t) {

                Log.e(TAG, "onFailure:==> " + t.toString());

                          }
        });




    }
    private void uploadalbum2() {

        OkHttpClient.Builder okClient = new OkHttpClient.Builder();
        okClient.connectTimeout(60000, TimeUnit.MILLISECONDS);
        okClient.writeTimeout(60000, TimeUnit.MILLISECONDS);
        okClient.readTimeout(60000, TimeUnit.MILLISECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okClient.interceptors().add(interceptor);

        okClient.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response response = chain.proceed(chain.request());
                response.newBuilder()
                        .header("Cache-Control", "only-if-cached")
                        .build();
                return response;
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gunjan05.pythonanywhere.com/")
                .client(okClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();




        FileUploadService client2 = retrofit.create(FileUploadService.class);
        Call<output> call = client2.ursend2(input2.getText().toString().trim());

        call.enqueue(new Callback<output>() {
            @Override
            public void onResponse(Call<output> call, Response<output> response) {
                Log.e(TAG, "onResponse:response==> " + response.isSuccessful() + "==>" + response.errorBody());

                if (response.isSuccessful()) {
                    Log.e(TAG, "onResponse: ==>+" + new Gson().toJson(response.body()));

//                    StringBuilder stringBuilder = new StringBuilder();
//
//                    for(String key : response.body().getKeywords()){
//
//                        stringBuilder.append("\n"+key);
//
//                    }

                   // output2.setText(response.body().getKeywords());

                    //output.setText(response.body().getKeywords());

                } else {

                }
            }

            @Override
            public void onFailure(Call<output> call, Throwable t) {

                Log.e(TAG, "onFailure:==> " + t.toString());

            }
        });




    }

    private void uploadalbum3() {

        OkHttpClient.Builder okClient = new OkHttpClient.Builder();
        okClient.connectTimeout(60000, TimeUnit.MILLISECONDS);
        okClient.writeTimeout(60000, TimeUnit.MILLISECONDS);
        okClient.readTimeout(60000, TimeUnit.MILLISECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okClient.interceptors().add(interceptor);

        okClient.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response response = chain.proceed(chain.request());
                response.newBuilder()
                        .header("Cache-Control", "only-if-cached")
                        .build();
                return response;
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gunjan05.pythonanywhere.com/")
                .client(okClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();




        FileUploadService client2 = retrofit.create(FileUploadService.class);
        Call<output> call = client2.ursend3();

        call.enqueue(new Callback<output>() {
            @Override
            public void onResponse(Call<output> call, Response<output> response) {
                Log.e(TAG, "onResponse:response==> " + response.isSuccessful() + "==>" + response.errorBody());

                if (response.isSuccessful()) {
                  toastMessage("cleared");
                    //output.setText(response.body().getKeywords());

                } else {
                    toastMessage("not cleared");
                }
            }

            @Override
            public void onFailure(Call<output> call, Throwable t) {

                Log.e(TAG, "onFailure:==> " + t.toString());

            }
        });




    }  private void toastMessage(String message) {
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show();
    }


}