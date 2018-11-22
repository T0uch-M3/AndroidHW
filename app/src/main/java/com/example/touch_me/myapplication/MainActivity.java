package com.example.touch_me.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    TextView textView, tvRes;
    EditText inputLeft;
    EditText inputRight;
    Button btn;
    String res;
    Boolean ex0 = true;
    Boolean ex1 = false;
    Boolean ex2 = false;
    Boolean ex3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //final TextView textView = (TextView) findViewById(R.id.textV);

        final IntentFilter myFilter = new IntentFilter();
        myFilter.addAction("SOME_ACTION");
        registerReceiver(BReceveiver, myFilter);

        textView = (TextView) findViewById(R.id.textView);
        inputLeft = (EditText) findViewById(R.id.etLeft);
        inputRight = (EditText) findViewById(R.id.etRight);
        tvRes = (TextView) findViewById(R.id.tvRes);
       // btn = (Button) findViewById(R.id.btnCal);
       // btn.setOnClickListener(this);

    }

    private BroadcastReceiver BReceveiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            String valuefromservice = intent.getStringExtra("interpretation");
            Toast.makeText(getApplicationContext(),valuefromservice,Toast.LENGTH_LONG).show();
        }
    };

    public void calculer(View view) {
        String a = inputLeft.getText().toString();
        String b = inputRight.getText().toString();
        res = a + b;
        if(ex0){//a
            tvRes.setText(res);
        }
        if(ex1) {//a+a
            tvRes.setText(null);
            Bundle c = new Bundle();
            c.putString("val1", res);
            Intent i = new Intent(MainActivity.this, Main3Activity.class);
            i.putExtras(c);
            startActivity(i);
        }
        if(ex2){//a+s
            tvRes.setText(null);
            Bundle c = new Bundle();
            c.putString("val1", res);
            Intent i = new Intent(MainActivity.this, MyService.class);
            i.putExtras(c);
            startService(i);
        }
        if(ex3){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,100,0,"A+A");
        menu.add(0,200,0,"A+S");
        menu.add(0,300,0,"A+BR");
        menu.add(0,400,0,"A");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case 100: {
                ex1 = true;
                ex3 = false;
                ex0 = false;
                ex2 = false;
            }
                break;
            case 200:{
                ex2 = true;
                ex3 = false;
                ex1 = false;
                ex0 = false;
            }
                break;
            case 300:{
                ex3 = true;
                ex1 = false;
                ex0 = false;
                ex2 = false;
            }
                break;
            case 400:{
                ex3 = false;
                ex1 = false;
                ex0 = true;
                ex2 = false;
            }
            break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
        }



    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(BReceveiver,new IntentFilter("message"));
    }
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(BReceveiver);
    }

}
