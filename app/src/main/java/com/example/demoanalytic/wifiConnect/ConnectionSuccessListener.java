package com.example.demoanalytic.wifiConnect;


import android.support.annotation.NonNull;

public interface ConnectionSuccessListener {
    void success();

    void failed(@NonNull ConnectionErrorCode errorCode);
}
