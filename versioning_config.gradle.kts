// Configuración de versionado automático para Android
// Agregar al archivo: app/build.gradle.kts

def getVersionCode() {
    return (System.getenv("BUILD_NUMBER") ?: "0").toInteger() + 1
}

def getVersionName() {
    // Formato: MAJOR.MINOR.PATCH
    // Ejemplo: 1.0.5
    return "1.0.0"
}

android {
    defaultConfig {
        versionCode getVersionCode()
        versionName getVersionName()
        
        // Metadata para CI/CD
        buildConfigField("String", "BUILD_NUMBER", "\"${System.getenv("BUILD_NUMBER") ?: "local"}\"")
        buildConfigField("String", "GIT_COMMIT", "\"${getGitCommit()}\"")
        buildConfigField("String", "BUILD_TIMESTAMP", "\"${new Date().format("yyyy-MM-dd'T'HH:mm:ss'Z'")}\"")
    }
}

static String getGitCommit() {
    return "git rev-parse --short HEAD".execute().text.trim()
}

// Crear CHANGELOG automático
task generateChangelog {
    doLast {
        def changelog = """
# Changelog

## [${getVersionName()}] - ${new Date().format("yyyy-MM-dd")}

### Agregado
- Nuevas características

### Corregido
- Correcciones de bugs

### Cambiado
- Cambios en funcionalidades existentes

---
"""
        file("CHANGELOG.md").text = changelog + file("CHANGELOG.md").text ?: ""
    }
}
