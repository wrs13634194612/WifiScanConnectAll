package com.example.demoanalytic.wifiDisconnect;


import android.support.annotation.NonNull;

public interface DisconnectionSuccessListener {
    void success();

    void failed(@NonNull DisconnectionErrorCode errorCode);
}