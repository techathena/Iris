package com.example.webscrapping;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextInputEditText userName,Email,Password,Phone;
    Button Register;
    //TextView output,output2;
    LoginDatabase loginDatabase;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginDatabase = new LoginDatabase(this);
        userName = findViewById(R.id.username);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Phone = findViewById(R.id.enter_phone);
        Register = findViewById(R.id.registeration);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = loginDatabase.getData();
                if(res.getCount() == 0){
                    addData1();
                  //  toastMessage("no food");
                }
                else {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res.moveToNext()) {
                        user = res.getString(1);

                        // s3 = res.getString(1);
                        buffer3.append("/n"+res.getString(1));
                        buffer3.append("/n"+res.getString(2));
                        buffer3.append("/n"+res.getString(3));
                        buffer3.append("/n"+res.getString(4));
                    }

                  //  showMessage("data",buffer3.toString());

                }
                if(userName.getText().toString().trim().equals("")){
                    userName.setError("Enter Username");
                }else  if(Email.getText().toString().trim().equals("")){
                    Email.setError("Enter Email");
                }else  if(Password.getText().toString().trim().equals("")){
                    Password.setError("Enter Password");
                }else  if(Phone.getText().toString().trim().equals("")){
                    Phone.setError("Enter Phone Number");
                }else{
                    if(userName.getText().toString().equals(user)){
                        toastMessage("Username already exist");
                    }
                    else{
                        addData1();
                    }
                }
            }
        });



    }

    public void addData1() {

        boolean insertData = loginDatabase.addData(userName.getText().toString().trim()
            ,Email.getText().toString().trim(),
                Password.getText().toString().trim(),
                Phone.getText().toString().trim());

        if (insertData) {
            toastMessage("Register Successfully");
            finish();

        } else {
            toastMessage("Something went wrong");
        }
    }
    private void toastMessage(String message) {
        Toast.makeText( this, message, Toast.LENGTH_SHORT ).show();
    }


}