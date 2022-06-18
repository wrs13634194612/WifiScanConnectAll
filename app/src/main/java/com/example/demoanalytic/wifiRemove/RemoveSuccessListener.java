package com.example.demoanalytic.wifiRemove;


import android.support.annotation.NonNull;

public interface RemoveSuccessListener {
    void success();

    void failed(@NonNull RemoveErrorCode errorCode);
}
