package com.example.zhaohuiyan.webopenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    TextView tvUrl,tvTitle;
    String url,title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        tvUrl = findViewById(R.id.param_url);
        tvTitle = findViewById(R.id.param_title);
        if (!TextUtils.isEmpty(url)){
            tvUrl.setText("welcome you!  this is param url " + url);
            tvTitle.setText("welcome you!  this is param title " + title);
        }

    }
}
