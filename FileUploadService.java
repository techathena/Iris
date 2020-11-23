package com.example.webscrapping;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileUploadService {


       // @Multipart
        @FormUrlEncoded
        @POST("home")
        Call<output> ursend(
                @Field("url") String url
              //  @Part List<MultipartBody.Part> files


        );

    @FormUrlEncoded
    @POST("search")
    Call<output> ursend2(
            @Field("keywords") String url
            //  @Part List<MultipartBody.Part> files


    );

   // @FormUrlEncoded
    @GET("clear")
    Call<output> ursend3(
           // @Field("keywords") String url
            //  @Part List<MultipartBody.Part> files


    );

}



