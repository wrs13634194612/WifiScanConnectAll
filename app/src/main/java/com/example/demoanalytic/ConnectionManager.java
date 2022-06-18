package com.example.demoanalytic;


        import android.content.Context;
        import android.net.wifi.ScanResult;
        import android.support.annotation.NonNull;
        import android.util.Log;
        import android.widget.Toast;


        import com.example.demoanalytic.wifiConnect.ConnectionErrorCode;
        import com.example.demoanalytic.wifiConnect.ConnectionSuccessListener;
        import com.example.demoanalytic.wifiDisconnect.DisconnectionErrorCode;
        import com.example.demoanalytic.wifiDisconnect.DisconnectionSuccessListener;
        import com.example.demoanalytic.wifiRemove.RemoveErrorCode;
        import com.example.demoanalytic.wifiRemove.RemoveSuccessListener;
        import com.example.demoanalytic.wifiWps.ConnectionWpsListener;

        import java.util.List;

public class ConnectionManager {
    Context context;

    public ConnectionManager(Context context) {
        this.context = context;
    }

    //打开wifi
    public void openWithWAP() {
        WifiUtils.withContext(context).enableWifi(this::checkResult);

    }

    //关闭wifi
    public void closeWithWAP() {
        WifiUtils.withContext(context).disableWifi();

    }


    //扫描wifi
    public void scanWithWAP() {
        WifiUtils.withContext(context).scanWifi(this::getScanResults).start();
    }

    //连接wifi
    public void connectWifiWithWAP(String name, String password) {
        WifiUtils.withContext(context)
                .connectWith(name, password)
                .setTimeout(10000)
                .onConnectionResult(new ConnectionSuccessListener() {
                    @Override
                    public void success() {
                        Toast.makeText(context, "SUCCESS!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(@NonNull ConnectionErrorCode errorCode) {
                        Toast.makeText(context, "EPIC FAIL!" + errorCode.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .start();
    }

    //取消正在连接的wifi
    public void cancelConnectWithWAP(String name, String password) {
        WifiConnectorBuilder.WifiUtilsBuilder builder = WifiUtils.withContext(context);
        builder.connectWith(name, password)
                .onConnectionResult(new ConnectionSuccessListener() {
                    @Override
                    public void success() {
                        Toast.makeText(context, "取消 SUCCESS!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(@NonNull ConnectionErrorCode errorCode) {
                        Toast.makeText(context, "EPIC FAIL!" + errorCode.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .start();
        builder.cancelAutoConnect();
    }

    //wps连接wifi
    public void wpsConnectWithWAP(String name, String password) {
        WifiUtils.withContext(context)
                .connectWithWps(name, password)
                .onConnectionWpsResult(new ConnectionWpsListener() {
                    @Override
                    public void isSuccessful(boolean isSuccess) {
                        Toast.makeText(context, "" + isSuccess, Toast.LENGTH_SHORT).show();

                    }
                })
                .start();
    }


    //断开wifi
    public void disConnectWithWAP() {
        WifiUtils.withContext(context)
                .disconnect(new DisconnectionSuccessListener() {
                    @Override
                    public void success() {
                        Toast.makeText(context, "Disconnect success!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(@NonNull DisconnectionErrorCode errorCode) {
                        Toast.makeText(context, "Failed to disconnect: " + errorCode.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    //断开连接并删除保存的网络配置 wifi
    public void disConnectDeleteWithWAP(String nameSsid) {
        WifiUtils.withContext(context)
                .remove(nameSsid, new RemoveSuccessListener() {
                    @Override
                    public void success() {
                        Toast.makeText(context, "Remove success!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(@NonNull RemoveErrorCode errorCode) {
                        Toast.makeText(context, "Failed to disconnect and remove: $errorCode", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void getScanResults(@NonNull final List<ScanResult> results) {
        if (results.isEmpty()) {
            Log.e("TAG", "SCAN RESULTS IT'S EMPTY");
            return;
        }
        Log.e("TAG", "GOT SCAN RESULTS " + results);
    }


    private void checkResult(boolean isSuccess) {
        if (isSuccess)
            Toast.makeText(context, "WIFI已经打开", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "无法打开wifi", Toast.LENGTH_SHORT).show();
    }


}
