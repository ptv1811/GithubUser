plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.vanluong.model"

    testOptions {
        unitTests.isReturnDefaultValues = false
    }
}

dependencies {
    // moshi
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)
}