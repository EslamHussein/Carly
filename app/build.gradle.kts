plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0" // Add this line
    kotlin("kapt")


}

android {
    namespace = "com.carly"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.carly"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        javaCompileOptions {
            annotationProcessorOptions {
                arguments(
                    mapOf("room.schemaLocation" to "$projectDir/schemas")
                )
            }
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "2.0.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.bundles.core)
    implementation(libs.bundles.ui)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.koin)

    testImplementation(libs.bundles.testing)
    testImplementation(libs.bundles.koin.testing)
    testImplementation(libs.bundles.compose.testing)

    androidTestImplementation(libs.bundles.android.testing)

    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))

    implementation(libs.room)
    annotationProcessor(libs.roomprocessor)
    ksp(libs.roomprocessor)

    debugImplementation(libs.bundles.ui.debug)
    implementation(libs.kotlinx.serialization.json) // Add this line

    implementation (libs.androidx.room.ktx) // Or the latest stable version



}