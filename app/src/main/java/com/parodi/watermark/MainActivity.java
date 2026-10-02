package com.parodi.watermark;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int OVERLAY_PERMISSION_REQ_CODE = 1234;
    private TextView tvStatus;
    private TextView tvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Layout sederhana langsung dari Java (Anti-Error Resource)
        ScrollView scrollView = new ScrollView(this);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(40, 60, 40, 40);
        scrollView.addView(root);

        TextView tvTitle = new TextView(this);
        tvTitle.setText("Activate Android Controller");
        tvTitle.setTextSize(22);
        tvTitle.setTextColor(Color.BLACK);
        tvTitle.setGravity(Gravity.CENTER);
        root.addView(tvTitle);

        tvStatus = new TextView(this);
        tvStatus.setTextSize(16);
        tvStatus.setPadding(0, 30, 0, 30);
        root.addView(tvStatus);

        Button btnPermission = new Button(this);
        btnPermission.setText("1. Izinkan Tampil di Atas Aplikasi Lain");
        btnPermission.setOnClickListener(v -> mintaIzinOverlay());
        root.addView(btnPermission);

        Button btnStart = new Button(this);
        btnStart.setText("2. Munculkan Watermark (START)");
        btnStart.setOnClickListener(v -> mulaiOverlay());
        root.addView(btnStart);

        Button btnStop = new Button(this);
        btnStop.setText("3. Hilangkan Watermark (STOP)");
        btnStop.setOnClickListener(v -> stopService(new Intent(this, OverlayService.class)));
        root.addView(btnStop);

        tvLog = new TextView(this);
        tvLog.setTextColor(Color.RED);
        tvLog.setPadding(0, 30, 0, 0);
        root.addView(tvLog);

        setContentView(scrollView);
        updateStatus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStatus();
    }

    private void updateStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean hasPermission = Settings.canDrawOverlays(this);
            tvStatus.setText("Status Izin Overlay: " + (hasPermission ? "AKTIF / DIIZINKAN" : "BELUM DIIZINKAN"));
            tvStatus.setTextColor(hasPermission ? Color.parseColor("#008800") : Color.RED);
        } else {
            tvStatus.setText("Status Izin: Otomatis diizinkan");
        }
    }

    private void mintaIzinOverlay() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }
        } catch (Exception e1) {
            try {
                // Fallback untuk HP yang menolak URI package
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            } catch (Exception e2) {
                tvLog.setText("Gagal membuka menu izin: " + e2.getMessage());
            }
        }
    }

    private void mulaiOverlay() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "Berikan izin overlay terlebih dahulu!", Toast.LENGTH_SHORT).show();
                mintaIzinOverlay();
                return;
            }
            startService(new Intent(this, OverlayService.class));
            Toast.makeText(this, "Watermark aktif! Cek pojok kanan bawah", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            tvLog.setText("Error saat memunculkan watermark: " + e.getMessage());
        }
    }
}
