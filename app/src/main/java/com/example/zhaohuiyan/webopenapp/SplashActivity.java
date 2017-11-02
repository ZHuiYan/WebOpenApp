package com.example.zhaohuiyan.webopenapp;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 2:
                        isActionView(getIntent());
                        finish();
                        break;

                }

            }

    };
    boolean isActionView = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(Intent.ACTION_VIEW.equals(getIntent().getAction())){
            isActionView = true;
        }
        if(!isActionView){
            handler.sendEmptyMessageDelayed(1,3000);
        }else {
            handler.sendEmptyMessage(2);

        }
    }

    private void isActionView(Intent intent){
            Uri uri = intent.getData();
            if(uri!=null){
                String showUrl = uri.getQueryParameter("param_show_url");
                String title = uri.getQueryParameter("param_title");
                Intent resultIntent = new Intent(SplashActivity.this,ShowActivity.class);
                resultIntent.putExtra("url",showUrl);
                resultIntent.putExtra("title",title);
                if (isLaunchedActivity(this, MainActivity.class)) {
                    resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(resultIntent);
                } else {
                    TaskStackBuilder.create(this)
                            .addParentStack(resultIntent.getComponent())
                            .addNextIntent(resultIntent)
                            .startActivities();
                }
            }
        }


    private boolean isLaunchedActivity(@NonNull Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        ComponentName cmpName = intent.resolveActivity(context.getPackageManager());
        boolean flag = false;
        if (cmpName != null) {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
}
