package com.example.angithadas.otpverification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by angitha.das on 18-04-2017.
 */

public class OTPReceiver extends BroadcastReceiver {
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Intent recieved: " + intent.getAction());

        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                if (messages.length > -1) {
                    Toast.makeText(context, messages[0].getMessageBody(), Toast.LENGTH_SHORT).show();
                    System.out.println("Message recieved: " + messages[0].getMessageBody());
//
//                    Intent intent_send = new Intent();
//                    intent_send.setAction("com.example.angithadas.otpverification.CUSTOM_BROADCAST");
//                    intent_send.putExtra("otp", messages[0].getMessageBody());
//                    context.sendBroadcast(intent_send);

                    MainActivity mainActivity = ((MyApplication) context.getApplicationContext()).mainActivity;
                    mainActivity.editText.append(messages[0].getMessageBody());
                }
            }
        }
    }
}