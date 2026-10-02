plugins {
    // Jika nanti muncul error soal 'libs', ganti baris ini menjadi: id("com.android.application")
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.parodi.watermark"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.parodi.watermark"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
