package com.example.webscrapping;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextInputEditText userName,passWord;
    Button register,login;
    LoginDatabase loginDatabase;
    String user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginDatabase = new LoginDatabase(this);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        userName = findViewById(R.id.username_login);
        passWord = findViewById(R.id.password_login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s = userName.getText().toString().trim();

                Cursor res = loginDatabase.getdata(s);
                if(res.getCount() == 0){
                    toastMessage("no user , Register first");
                }
                else {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res.moveToNext()) {
                        user = res.getString(1);
                        pass = res.getString(3);
                        // s3 = res.getString(1);
                        buffer3.append("/n"+res.getString(1));
                        buffer3.append("/n"+res.getString(2));
                        buffer3.append("/n"+res.getString(3));
                        buffer3.append("/n"+res.getString(4));
                    }

            //    showMessage("data",buffer3.toString());

                }
                if(passWord.getText().toString().trim().equals(pass) && userName.getText().toString().trim().equals(user)){
                    Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    toastMessage("Invalid Username and Password");
                }


//                Cursor res2 = loginDatabase.getData();
//                if (res2.getCount() == 0) {
//                    // toastMessage("no data");
//                    return;
//                }
//                else {
//                    StringBuffer buffer2 = new StringBuffer();
//                    while (res2.moveToNext()) {
//                        //  buffer2.append("id " + res.getString(0) + "name" + res.getString(1) + "\n");
//                        i1 = res2.getInt(0);
//                        s2 = res2.getString(1);
//                    }
//                    //  showMessage("cal",buffer2.toString());
//                    calor.setText(s2);
//                    if (calor.getText().toString().length() != 0) {
//                        imv2.setVisibility(View.INVISIBLE);
//                    }
//                    //
//                }
//                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
//                startActivity(intent);
            }
        });
    }
    private void toastMessage(String message) {
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show();
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}