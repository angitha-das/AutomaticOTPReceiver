package com.example.angithadas.otpverification;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import de.greenrobot.event.EventBus;

public class MainActivity extends Activity {
    EditText editText;
    CustomBroadcastReceiver customBroadcastReceiver;
    IntentFilter filter = new IntentFilter();
    IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus myEventBus = EventBus.getDefault();
        EventBus.getDefault().register(this); // this == your class instance


        MyApplication myApplication = (MyApplication) this.getApplicationContext();
        myApplication.mainActivity = this;

        editText = (EditText) findViewById(R.id.ed_otp);
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        //registerReceiver(new SmsReceiver(this), filter);

        customBroadcastReceiver = new CustomBroadcastReceiver();
        intentFilter = new IntentFilter("com.example.angithadas.otpverification.CUSTOM_BROADCAST");
        //registerReceiver(customBroadcastReceiver,intentFilter);
    }

    public void onEvent(OTPEventBus event) {
        editText.setText(event.getMessage());
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
}



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(new SmsReceiver(this));
        //unregisterReceiver(customBroadcastReceiver);
        EventBus.getDefault().unregister(this);
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

