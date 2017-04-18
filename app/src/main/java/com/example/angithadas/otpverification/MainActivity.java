package com.example.angithadas.otpverification;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class MainActivity extends Activity {
    EditText editText;
    CustomBroadcastReceiver customBroadcastReceiver;
    IntentFilter filter = new IntentFilter();
    IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyApplication myApplication = (MyApplication) this.getApplicationContext();
        myApplication.mainActivity = this;

        editText = (EditText) findViewById(R.id.ed_otp);
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        //registerReceiver(new SmsReceiver(this), filter);

        customBroadcastReceiver = new CustomBroadcastReceiver();
        intentFilter = new IntentFilter("com.example.angithadas.otpverification.CUSTOM_BROADCAST");
        registerReceiver(customBroadcastReceiver,intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(new SmsReceiver(this));
        unregisterReceiver(customBroadcastReceiver);
    }

    public void setEditText(String otp) {
        editText.setText(otp);
    }

    public class CustomBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            editText.append(intent.getStringExtra("otp")+"\n");
        }
    }
}

