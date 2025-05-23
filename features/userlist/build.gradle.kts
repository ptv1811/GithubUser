plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.vanluong.userlist"

    buildFeatures {
        dataBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:data"))

    // Module for unit testing
    testImplementation(project(":core:testing"))
    testImplementation(project(":core:network"))
    testImplementation(project(":core:database"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.android.lifecycle.savedstate)
    implementation(libs.recyclerView)

    implementation(libs.facebook.shimmer)

    // Hilt
    implementation(libs.android.hilt)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.android.processor)

    // coroutines
    implementation(libs.coroutines.android)
    testImplementation(libs.coroutines.android)
    testImplementation(libs.coroutines.test)

    // Glide
    implementation(libs.glide)
    ksp(libs.glide.ksp)

    // moshi
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.turbine)
}