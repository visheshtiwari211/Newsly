plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)
}

android {
    namespace = "com.example.database"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(libs.androidx.paging.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //room dependency
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    //sqlite dependency
    implementation(libs.androidx.sqlite.bundled)

    // Room testing
    testImplementation(libs.androidx.room.testing)

    // optional for coroutines support
    implementation(libs.androidx.room.ktx)

    //hilt-dependencies
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Paging support for Room
    implementation(libs.androidx.room.paging)
}

room {
    schemaDirectory("$projectDir/schemas")
}