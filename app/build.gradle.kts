plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias (libs.plugins.hilt.application)
    alias(libs.plugins.kapt)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.crashlytics)
}

android {
    namespace = "com.lauraalvarez.movehabits"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.lauraalvarez.movehabits"
        minSdk = 23
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.datastore.preferences)
    implementation(libs.joda.time)
    //hilt
    kapt(libs.hilt.android.compiler)
    implementation (libs.hilt.android)
    implementation (libs.hilt.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlythics)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)

    implementation(libs.coil)

    //calendar api
    /*
    implementation(libs.google.play.services.auth)
    implementation(libs.google.api.client.android)
    implementation(libs.google.api.client.gson)
    implementation(libs.google.auth.library.oauth2.http)
    implementation(libs.google.api.services.calendar)*/


}