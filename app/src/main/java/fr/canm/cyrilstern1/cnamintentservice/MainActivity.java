package fr.canm.cyrilstern1.cnamintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Messenger;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button validation;
    private EditText duree;
    private static String MY_SUPER_SERVICE = "fr.canm.cyrilstern1.cnamintentservice.MY_SERVICE";
    private MyTickerService theService;
    private Intent intentTicker;
    private MyTickerService.Broadcast theBroadcast;
    private int delai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
       // theBroadcast = new Broadcast();
        validation = (Button) findViewById(R.id.buttonService);
        duree =(EditText) findViewById(R.id.editTextTime);
        validation.setOnClickListener(this);
        IntentFilter intentfilter = new IntentFilter(MY_SUPER_SERVICE);
        //registerReceiver(theBroadcast,intentfilter);

    }

    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            Bundle extras = message.getData();
            if (extras != null) {
                int period = extras.getInt("delai",-1);
                Toast.makeText(MainActivity.this,"messenger: délai écoulé " + delai, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonService:
//                Intent tickerService = new Intent(this, MyTickerService.class);
//                intentTicker = new Intent(this, IntentServiceTicker.class);
//                intentTicker.putExtra("time",Integer.parseInt(duree.getText().toString()));
//                startService(intentTicker);
//
//                sendBroadcast(new Intent(MY_SUPER_SERVICE));
//                tickerService.putExtra("time",duree.getText().toString());
               // startService(tickerService);
                Intent intent = new Intent(this, IntentServiceTicker.class);
                intent.putExtra("pid", duree.getText().toString());
                Messenger messager = new Messenger(handler);
                intent.putExtra("messager", messager);
                startService(intent);
        }

    }
    @Override
    protected void onStop()
    {
       // unregisterReceiver(theBroadcast);
        super.onStop();
    }
}
