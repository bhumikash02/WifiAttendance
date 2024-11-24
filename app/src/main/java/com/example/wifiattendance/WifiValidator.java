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

    // Define trusted BSSIDs (Wi-Fi Access Point MAC addresses) and signal strength threshold
    private final List<String> trustedBSSIDs = Arrays.asList(
            "04:25:EO:E3:66:4O",  // Example BSSID 1
            "00:14:22:67:89:AB",  // Example BSSID 2
            "00:14:22:34:56:78"   // Example BSSID 3
    );
    private static final int SIGNAL_THRESHOLD = -60; // Signal strength threshold in dBm (higher is better)

    public WifiValidator(Context context) {
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    /**
     * This method validates whether the employee is onsite by checking the connected Wi-Fi network's BSSID
     * and the signal strength.
     *
     * @return true if the employee is onsite (connected to a trusted BSSID with acceptable signal strength),
     *         false otherwise.
     */
    public boolean validateEmployeePresence() {
        try {
            // Perform a Wi-Fi scan to get the list of available networks
            List<ScanResult> scanResults = wifiManager.getScanResults();

            // Loop through all the scanned networks
            for (ScanResult scanResult : scanResults) {
                // Check if the current Wi-Fi network (BSSID) is trusted and signal strength is above threshold
                if (trustedBSSIDs.contains(scanResult.BSSID) && scanResult.level >= SIGNAL_THRESHOLD) {
                    return true; // The employee is onsite
                }
            }
        } catch (SecurityException e) {
            // Handle the case where the app does not have permission to access Wi-Fi scan results
            Log.e(TAG, "Permission not granted for Wi-Fi scanning: " + e.getMessage());
        }
        return false; // Default to "not onsite" if no matching BSSID found or signal strength is too weak
    }
}
