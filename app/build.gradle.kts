plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.androidx.room)
}

android {
    namespace = "com.vishesh.newsly"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.vishesh.newsly"
        minSdk = 24
        targetSdk = 36
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

    configurations.all {
        exclude(group = "com.intellij", module = "annotations")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    //hilt-dependencies
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //hilt-navigation
    implementation(libs.androidx.hilt.navigation.compose)

    //room dependency
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    //sqlite dependency
    implementation(libs.androidx.sqlite.bundled)

    // Room testing
    testImplementation(libs.androidx.room.testing)

    // optional for coroutines support
    implementation(libs.androidx.room.ktx)

    //coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    //livedata
    implementation(libs.androidx.compose.runtime.livedata)

}

room {
    schemaDirectory("$projectDir/schemas")
}