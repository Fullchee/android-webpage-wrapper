plugins {
    id("com.android.application")
}

android {
    namespace = "com.fullchee.webwrapper"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.fullchee.webwrapper"
        minSdk = 29
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}
