plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.vanluong.core.network"
}

dependencies {
    implementation(project(":core:model"))

    // network
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.sandwich)

    // coroutines
    implementation(libs.coroutines.android)
    testImplementation(libs.coroutines.android)
    testImplementation(libs.coroutines.test)

    // moshi
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    // hilt
    implementation(libs.android.hilt)
    ksp(libs.hilt.compiler)
}