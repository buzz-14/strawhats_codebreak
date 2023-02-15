package com.example.codebreak;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView tv1;
    WifiManager wifiManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.b1);
        tv1 = findViewById(R.id.t1);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo(v);
            }
        });
    }

    public void getInfo(View view) {
        String ssid="";
        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
             ssid = wifiInfo.getSSID();
            Toast.makeText(this, ""+ssid, Toast.LENGTH_SHORT).show();
        }
        int ip = wifiInfo.getIpAddress();
        String bssid = wifiInfo.getBSSID();
        int rssi = wifiInfo.getRssi();
        int linkspeed = wifiInfo.getLinkSpeed();
        int frequency = wifiInfo.getFrequency();
        String info = "ipAddress: " + ip + "\n" + "bssid: " + bssid +"\n"+"rssi: "+rssi+"\n"+"linkspeed: "+linkspeed+"\n"+"ssid: "+ssid+"\n"+"frequency: "+frequency;
        tv1.setText(info);

    }
}