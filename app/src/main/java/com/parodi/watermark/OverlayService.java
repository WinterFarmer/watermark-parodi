package com.parodi.watermark;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OverlayService extends Service {
    private WindowManager windowManager;
    private View overlayView;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // Buat tampilan watermark secara dinamis (Aman dari bug layout XML)
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20, 20, 20, 20);

        TextView title = new TextView(this);
        title.setText("Activate Android");
        title.setTextColor(Color.argb(128, 255, 255, 255)); // Putih 50% transparan
        title.setTextSize(18);

        TextView subtitle = new TextView(this);
        subtitle.setText("Go to Settings to activate Android.");
        subtitle.setTextColor(Color.argb(128, 255, 255, 255));
        subtitle.setTextSize(12);

        layout.addView(title);
        layout.addView(subtitle);
        overlayView = layout;

        int layoutType = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) 
            ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY 
            : WindowManager.LayoutParams.TYPE_PHONE;

        // Kunci sentuhan tembus ke aplikasi di bawahnya
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            layoutType,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE |
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT
        );

        params.gravity = Gravity.BOTTOM | Gravity.END;
        params.x = 40;
        params.y = 120; // Jarak dari navigation bar bawah

        if (windowManager != null) {
            windowManager.addView(overlayView, params);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (windowManager != null && overlayView != null) {
            windowManager.removeView(overlayView);
        }
    }
}
