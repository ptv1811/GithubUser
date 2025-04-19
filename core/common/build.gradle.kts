plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.vanluong.common"
}

dependencies {

    // moshi
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)
}