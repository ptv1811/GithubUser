plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.vanluong.githubuser"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.vanluong.githubuser"
        minSdk = 25
        targetSdk = 35
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.recyclerView)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Viewmodel
    implementation(libs.androidx.lifecyle.viewmodel.ktx)
    implementation(libs.android.lifecycle.savedstate)

    // hilt
    implementation(libs.android.hilt)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.android.processor)

    // network
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)

    // Room
    implementation(libs.android.room)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // coroutines
    implementation(libs.couroutines.android)

    // gson
    implementation(libs.gson)

    // Jetpack Datastore
    implementation(libs.datastore)

    // Glide
    implementation(libs.glide)
}