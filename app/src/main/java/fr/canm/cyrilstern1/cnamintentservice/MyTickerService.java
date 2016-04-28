package fr.canm.cyrilstern1.cnamintentservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicLong;

public class MyTickerService extends Service {
    private static Thread ticker;
    private static AtomicLong count;
    private  Integer  duree;
    private Broadcast bro;
   // private Intent intent;
    public MyTickerService() {
    }
    @Override
    public void onCreate(){
    bro = new Broadcast();
        IntentFilter intent = new IntentFilter("fr.canm.cyrilstern1.cnamintentservice.MY_SERVICE");
        registerReceiver(bro,intent);
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public int onStartCommand(Intent intent,int flags, int startId){
       // duree = Integer.valueOf(intent.getStringExtra("time"));
       // Log.i("times",duree.toString());
        if(ticker==null){

            count= new AtomicLong();
            ticker = new Thread(new Ticker(duree));
            ticker.start();
        }
        return START_STICKY;
    }

    public class Broadcast extends BroadcastReceiver {
        private String time;
        @Override
        public void onReceive(Context context, Intent intent) {
            time = intent.getStringExtra("time");
            Toast.makeText(context, "Temps écoulé:" + time, Toast.LENGTH_LONG).show();


        }

    }

    private class Ticker implements Runnable{

        public Long time;
        public Ticker(Integer duree){
          //  this.time = Long.valueOf(duree);
        }
        @Override
        public void run() {
            Log.i("tf","passe par la");
//            while(!ticker.isInterrupted() && count.longValue() < time ){
//                try {
//                    Thread.sleep(1000);
//                    Log.i("damned",count.toString());
//                    count.set(count.longValue() + 1L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            Intent intent = new Intent("fr.canm.cyrilstern1.cnamintentservice.MY_SERVICE");
//            intent.putExtra("time",duree.toString());
//            sendBroadcast(intent);
//            ticker=null;
        }
    }

}
