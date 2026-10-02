plugins {
    id("com.android.application")
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

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // Trik agar APK Release bisa langsung di-install di HP tanpa bikin keystore manual
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
