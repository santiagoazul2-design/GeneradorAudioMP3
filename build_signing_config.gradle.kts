// Configuración de firma de APK para Google Play
// Agregar esto al archivo: app/build.gradle.kts

signingConfigs {
    create("release") {
        storeFile = file("keystore/release.keystore")
        storePassword = System.getenv("KEYSTORE_PASSWORD") ?: "changeme"
        keyAlias = System.getenv("KEY_ALIAS") ?: "generador_audio"
        keyPassword = System.getenv("KEY_PASSWORD") ?: "changeme"
    }
}

buildTypes {
    release {
        isMinifyEnabled = true
        isShrinkResources = true
        signingConfig = signingConfigs.getByName("release")
        
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
        
        // Versioning automático
        versionNameSuffix = "-release"
    }
    
    debug {
        isMinifyEnabled = false
        isDebuggable = true
    }
}

// Metadata para publicación
publishing {
    singleVariant("release") {
        withSourcesJar()
        withJavadocJar()
    }
}
