package com.example.demoanalytic.wifiConnect;


import android.support.annotation.NonNull;

public interface WifiConnectionCallback {
    void successfulConnect();

    void errorConnect(@NonNull ConnectionErrorCode connectionErrorCode);
}
