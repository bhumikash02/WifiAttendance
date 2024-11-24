package com.example.wifiattendance;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_CODE = 1;
    private WifiValidator wifiValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Enable edge-to-edge insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize WifiValidator
        wifiValidator = new WifiValidator(this);

        // Request necessary permissions
        checkPermissions();

        // Set up the button for attendance validation
        Button btnCheckAttendance = findViewById(R.id.btnCheckAttendance);
        btnCheckAttendance.setOnClickListener(v -> {
            if (checkPermissions()) { // Ensure permissions are granted before checking
                boolean isOnsite = wifiValidator.validateEmployeePresence();
                if (isOnsite) {
                    Toast.makeText(this, "Attendance marked successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "You are not in the office!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Location permission required to validate attendance.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Method to check and request location permissions.
     */
    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
            return false; // Permission not yet granted
        }
        return true; // Permission already granted
    }

    /**
     * Handles the result of the location permission request.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Location permission granted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Location permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
