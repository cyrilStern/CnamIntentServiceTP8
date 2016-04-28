package fr.canm.cyrilstern1.cnamintentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by cyrilstern1 on 28/04/2016.
 */
public class IntentServiceTicker extends android.app.IntentService {

    public static  String MY_TICKER_EMITER = "fr.canm.cyrilstern1.cnamintentservice.MY_SERVICER";
    private Broadcaste broadcast;
    private IntentFilter intent;
    private Messenger messager;
    private Bundle  extras;
    private Message msg;

    public IntentServiceTicker() {
        super("IntentServiceTicker");

    }
    @Override
    public void onCreate(){
        super.onCreate();
        broadcast=new Broadcaste();
        intent = new IntentFilter(MY_TICKER_EMITER);
        registerReceiver(broadcast,intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        final Integer period = intent.getIntExtra("time",1);
        SystemClock.sleep(period*1000);
        extras = intent.getExtras();
        messager = (Messenger) extras.get("messager");
        Log.i("time",extras.toString());
        msg = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putLong("delai", period);
        msg.setData(bundle);
        try {
            messager.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //        Log.i("temps", String.valueOf(period));
//        Intent toastIntent = new Intent(MY_TICKER_EMITER);
//        toastIntent.putExtra("time",String.valueOf(period));
//        sendBroadcast(toastIntent);
    }

    public class Broadcaste extends BroadcastReceiver {
        private String time;
        @Override
        public void onReceive(Context context, Intent intent) {
            time = intent.getStringExtra("time");
            Toast.makeText(context, "Temps écoulé:" + time, Toast.LENGTH_LONG).show();


        }

    }
    @Override
    public void onDestroy()
    {
        unregisterReceiver(broadcast);
        super.onDestroy();
    }
}
