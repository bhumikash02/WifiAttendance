
# WiFi Attendance App 📶📲

A smart Android-based attendance system that validates employee presence inside an office building using WiFi network detection. It ensures genuine location-based logging by verifying connections to predefined WiFi routers (SSID + BSSID).

## 📌 Features

- 📡 Auto-detects if the employee is connected to an authorized office WiFi network.
- 📝 Logs attendance with timestamp and router details.
- 🔐 Role-based login for Employee and HR/Admin.
- ✅ HR panel to view, approve, or reject attendance logs.
- 🧠 Prevents spoofing using BSSID (unique WiFi MAC address).
- 🗂 Stores attendance data in Firebase Firestore (real-time).
- 🔔 Instant feedback after check-in (e.g., success/failure message).

## 📱 Tech Stack

- **Frontend:** Java, XML, Jetpack Components
- **Backend:** Firebase (Firestore, Auth)
- **Location Verification:** WiFiManager + BSSID checks
- **Architecture:** MVVM 

## 🛠️ Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/wifi-attendance.git
   ```

2. Open with Android Studio.

3. Add your `google-services.json` file in the `app/` folder (Firebase setup).

4. Replace the default router list in `WiFiValidator.java`:
   ```java
   private static final Map<String, String> approvedRouters = new HashMap<String, String>() {{
       put("Office_WiFi", "AA:BB:CC:DD:EE:FF"); // SSID and BSSID
       put("Backup_WiFi", "11:22:33:44:55:66");
   }};
   ```

5. Run the app on a real device (emulator doesn't support WiFi APIs properly).

## 🚀 Usage

### Employee:

- Login using your credentials.
- On launch, the app checks if you're connected to an authorized office router.
- If verified, your attendance gets logged with time and router info.

### HR/Admin:

- Log in to the HR portal.
- View and filter employee logs.
- Approve/reject entries (optional).
- Track attendance statistics.


## ✅ Future Improvements

- Notification for successful check-in.
- Attendance history graph/chart.
- Geo-fencing fallback if WiFi is unavailable.
- Support for dynamic router list from Firebase.

## 🧑‍💻 Author

**Bhumika**  
3rd Year B.Tech CSE  
Tech Stack: Java, XML, Firebase, Android Studio

## 📄 License

This project is licensed under the MIT License. See `LICENSE` for more information.
