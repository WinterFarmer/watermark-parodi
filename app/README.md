# Activate Android - Watermark Parody

Aplikasi overlay parody "Activate Windows" (*Activate Android: Go to Settings to activate Android*) yang sangat ringan dan tidak mengganggu responsivitas layar (**touch-through**).

## Fitur Utama
- **Ultra Lightweight (~9 KB)**: Ukuran APK super mini berkat optimasi agresif R8 / ProGuard dan pembersihan resource.
- **Zero Touch Interference**: Menggunakan flag sistem `FLAG_NOT_TOUCHABLE` sehingga seluruh area layar di balik watermark tetap 100% responsif terhadap sentuhan jari.
- **Pure Java Native**: Dibangun murni menggunakan Android SDK native tanpa library eksternal yang membebani memori.
- **Standalone Controller**: Dilengkapi tombol kontrol sederhana untuk meminta izin overlay, mengaktifkan (*START*), dan mematikan (*STOP*) watermark kapan saja.

## Cara Mengompilasi (Build)
Pastikan environment Android SDK dan Gradle sudah tersedia, lalu jalankan perintah berikut di root folder proyek:

Untuk versi Debug:
./gradlew assembleDebug

Untuk versi Rilis (Mini 9 KB):
./gradlew assembleRelease

File APK hasil kompilasi akan berada di direktori:
app/build/outputs/apk/release/
