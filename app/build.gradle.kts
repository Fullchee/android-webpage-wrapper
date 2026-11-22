plugins {
    id("com.android.application")
}

android {
    namespace = "com.fullchee.webwrapper"
    compileSdk = 37

    defaultConfig {
        minSdk = 29
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        buildConfig = true
        resValues = true
    }

    // One flavor per site. Each installs as its own app, with its own cookies.
    // A site's icon goes in app/src/<site>/res/; without one it gets the default globe.
    flavorDimensions += "site"
    productFlavors {
        create("journal") {
            dimension = "site"
            // The first wrapper shipped with this ID; changing it would sign the phone out.
            applicationId = "com.fullchee.webwrapper"
            resValue("string", "app_name", "Journal Site")
            buildConfigField("String", "HOME_URL", "\"https://journal.fullchee.workers.dev/\"")
        }
    }
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}

androidComponents {
    onVariants { variant ->
        variant.outputs.forEach { it.outputFileName.set("${variant.flavorName}.apk") }
    }
}
