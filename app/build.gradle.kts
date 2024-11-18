plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.mvvmnewapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mvvmnewapp"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

kapt {
    correctErrorTypes = true
}
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    // Retrofit and OkHttp dependencies
    implementation(libs.retrofit)
    implementation(libs.convertergson)
    implementation(libs.logginginterceptor)

    // Navigation dependencies
    implementation(libs.androidxnavigationfragment)
    implementation(libs.androidxnavigationui)

    // Glide dependencies
    implementation(libs.glide)
    kapt(libs.glide.compiler)
    implementation(libs.kotlinxcoroutinescore)
    implementation(libs.kotlinxcoroutinesandroid)
    implementation(libs.androidxlifecycleruntime)
}
