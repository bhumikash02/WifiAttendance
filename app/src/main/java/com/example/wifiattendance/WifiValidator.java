package com.example.wifiattendance;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class WifiValidator {

    private final WifiManager wifiManager;
    private static final String TAG = "WifiValidator";

    private final List<String> trustedBSSIDs = Arrays.asList(
            "04:25:EO:E3:66:4O",  // Example BSSID 1
            "00:14:22:67:89:AB",  // Example BSSID 2
            "00:14:22:34:56:78"   // Example BSSID 3
        );
    private static final int SIGNAL_THRESHOLD = -60; // Signal strength threshold in dBm (higher is better)

    public WifiValidator(Context context) {
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

   
    public boolean validateEmployeePresence() {
        try {
            List<ScanResult> scanResults = wifiManager.getScanResults();
            for (ScanResult scanResult : scanResults) {
                
                if (trustedBSSIDs.contains(scanResult.BSSID) && scanResult.level >= SIGNAL_THRESHOLD) {
                    return true; 
                }
            }
        } catch (SecurityException e) {
            
            Log.e(TAG, "Permission not granted for Wi-Fi scanning: " + e.getMessage());
        }
        return false; 
    }
}
