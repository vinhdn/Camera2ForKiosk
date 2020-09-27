plugins {
    id("com.android.library")
    id("kotlin-android")
    id("jacoco")
}

android {
    setCompileSdkVersion(29)
    defaultConfig {
        setMinSdkVersion(15)
        setTargetSdkVersion(29)
        versionCode = 1
        versionName = "2.6.4"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArgument("filter", "" +
                "com.otaliastudios.cameraview.tools.SdkExcludeFilter," +
                "com.otaliastudios.cameraview.tools.SdkIncludeFilter")
    }
}

dependencies {
    testImplementation("junit:junit:4.13")
    testImplementation("org.mockito:mockito-inline:2.28.2")

    androidTestImplementation("androidx.test:runner:1.3.0")
    androidTestImplementation("androidx.test:rules:1.3.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("org.mockito:mockito-android:2.28.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    api("androidx.exifinterface:exifinterface:1.2.0")
    api("androidx.lifecycle:lifecycle-common:2.2.0")
    api("com.google.android.gms:play-services-tasks:17.2.0")
    implementation("androidx.annotation:annotation:1.1.0")
    implementation("com.otaliastudios.opengl:egloo:0.5.3")
}