plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.cleango"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cleango"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation ("com.google.android.libraries.places:places:3.0.0")
    implementation ("androidx.activity:activity:1.9.3")
    implementation ("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation ("com.google.android.libraries.places:places:2.7.0")
    implementation ("com.google.android.gms:play-services-maps:18.1.0")
    implementation ("androidx.appcompat:appcompat:1.7.0")
    implementation ("com.google.android.material:material:1.12.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.google.maps) // Thêm thư viện Google Maps
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}