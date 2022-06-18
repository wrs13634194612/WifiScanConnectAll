package com.example.demoanalytic;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.example.demoanalytic.wifiConnect.ConnectionScanResultsListener;
import com.example.demoanalytic.wifiConnect.ConnectionSuccessListener;
import com.example.demoanalytic.wifiDisconnect.DisconnectionSuccessListener;
import com.example.demoanalytic.wifiRemove.RemoveSuccessListener;
import com.example.demoanalytic.wifiScan.ScanResultsListener;
import com.example.demoanalytic.wifiState.WifiStateListener;
import com.example.demoanalytic.wifiWps.ConnectionWpsListener;


public interface WifiConnectorBuilder {
    void start();

    interface WifiUtilsBuilder {
        void enableWifi(WifiStateListener wifiStateListener);

        void enableWifi();

        void disableWifi();

        @NonNull
        WifiConnectorBuilder scanWifi(@Nullable ScanResultsListener scanResultsListener);

        @NonNull
        WifiSuccessListener connectWith(@NonNull String ssid);

        @NonNull
        WifiSuccessListener connectWith(@NonNull String ssid, @NonNull String password);

        @NonNull
        WifiSuccessListener connectWith(@NonNull String ssid, @NonNull String bssid, @NonNull String password);

        WifiSuccessListener connectWith(@NonNull String ssid, @NonNull String password, @NonNull TypeEnum type);

        @NonNull
        WifiUtilsBuilder patternMatch();

        @Deprecated
        void disconnectFrom(@NonNull String ssid, @NonNull DisconnectionSuccessListener disconnectionSuccessListener);

        void disconnect(@NonNull DisconnectionSuccessListener disconnectionSuccessListener);

        void remove(@NonNull String ssid, @NonNull RemoveSuccessListener removeSuccessListener);

        @NonNull
        WifiSuccessListener connectWithScanResult(@NonNull String password, @Nullable ConnectionScanResultsListener connectionScanResultsListener);

        @NonNull
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        WifiWpsSuccessListener connectWithWps(@NonNull String bssid, @NonNull String password);

        void cancelAutoConnect();

        boolean isWifiConnected(@NonNull String ssid);
        boolean isWifiConnected();
    }

    interface WifiSuccessListener {
        @NonNull
        WifiSuccessListener setTimeout(long timeOutMillis);

        @NonNull
        WifiConnectorBuilder onConnectionResult(@Nullable ConnectionSuccessListener successListener);
    }

    interface WifiWpsSuccessListener {
        @NonNull
        WifiWpsSuccessListener setWpsTimeout(long timeOutMillis);

        @NonNull
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        WifiConnectorBuilder onConnectionWpsResult(@Nullable ConnectionWpsListener successListener);
    }
}
