# Activate Android - Watermark Parody

Aplikasi overlay parody "Activate Windows" (*Activate Android: Go to Settings to activate Android*) yang sangat ringan dan tidak mengganggu responsivitas layar (**touch-through**).

## Fitur Utama
- **Ultra Lightweight (~9 KB)**: Ukuran APK super mini berkat optimasi agresif R8 / ProGuard dan pembersihan resource.
- **Zero Touch Interference**: Menggunakan flag sistem `FLAG_NOT_TOUCHABLE` sehingga seluruh area layar di balik watermark tetap 100% responsif terhadap sentuhan jari.
- **Pure Java Native**: Dibangun murni menggunakan Android SDK native tanpa library eksternal yang membebani memori.
- **Standalone Controller**: Dilengkapi tombol kontrol sederhana untuk meminta izin overlay, mengaktifkan (*START*), dan mematikan (*STOP*) watermark kapan saja.
