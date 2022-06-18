package com.example.demoanalytic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TextActivity extends AppCompatActivity {
    String wifiName3 = "wanye_2021";
    String wifiPassword3 = "123456789";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_wifiConnect = findViewById(R.id.btn_main);

        btn_wifiConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ConnectionManager(TextActivity.this).connectWifiWithWAP(wifiName3, wifiPassword3);

            }
        });
    }

}