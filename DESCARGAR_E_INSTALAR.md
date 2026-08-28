# 🚀 INSTRUCCIONES PARA DESCARGAR Y COMPILAR

## ⚠️ REQUISITOS PREVIOS

### Para Windows (App 64-bit)

#### 1️⃣ Instalar .NET 8.0 SDK
```powershell
# Descargar desde:
# https://dotnet.microsoft.com/download/dotnet/8.0

# Verificar instalación
dotnet --version
# Debe mostrar: 8.x.x
```

#### 2️⃣ Instalar Visual Studio 2022 Community (opcional pero recomendado)
```
https://visualstudio.microsoft.com/es/downloads/
Seleccionar: .NET desktop development
```

---

### Para Android (APK)

#### 1️⃣ Instalar Android Studio
```
https://developer.android.com/studio
```

#### 2️⃣ Instalar JDK 17+
```
https://www.oracle.com/java/technologies/downloads/#java17
```

#### 3️⃣ Configurar variables de entorno
```powershell
[Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Program Files\Java\jdk-17", "User")
[Environment]::SetEnvironmentVariable("ANDROID_HOME", "C:\Users\Tu_Usuario\AppData\Local\Android\sdk", "User")
```

---

## ⏱️ COMPILACIÓN PASO A PASO

### 📱 COMPILAR ANDROID (APK)

```bash
cd Android

# 1. Limpiar builds anteriores
./gradlew clean

# 2. Compilar APK Debug (para testing)
./gradlew assembleDebug
# Resultado: app/build/outputs/apk/debug/app-debug.apk

# 3. Compilar APK Release (para Google Play)
./gradlew assembleRelease
# Resultado: app/build/outputs/apk/release/app-release.apk

# 4. O compilar AAB (Android App Bundle - recomendado)
./gradlew bundleRelease
# Resultado: app/build/outputs/bundle/release/app-release.aab
```

**⏱️ Tiempo estimado**: 5-10 minutos (depende de tu PC)

---

### 💻 COMPILAR WINDOWS (EXE 64-bit)

```powershell
cd Windows

# 1. Restaurar dependencias
dotnet restore GeneradorAudioMP3.Windows.csproj

# 2. Compilar Release (x64)
dotnet build -c Release -r win-x64

# 3. Publicar como ejecutable (sin instalador)
dotnet publish -c Release -r win-x64 --self-contained -p:PublishTrimmed=true
# Resultado: bin/Release/net8.0-windows/win-x64/publish/GeneradorAudioMP3.exe

# 4. Crear MSI (instalador)
# Necesita WiX Toolset instalado:
candle.exe GeneradorAudioMP3.wxs -o obj\
light.exe -out GeneradorAudioMP3.msi obj\GeneradorAudioMP3.wixobj
```

**⏱️ Tiempo estimado**: 2-5 minutos

---

## 📁 UBICACIÓN DE ARCHIVOS COMPILADOS

### Android
```
Android/app/build/outputs/
├── apk/
│   ├── debug/app-debug.apk ⬅️ Para testing
│   └── release/app-release.apk ⬅️ Para Google Play
└── bundle/
    └── release/app-release.aab ⬅️ Recomendado para Play Store
```

### Windows
```
Windows/bin/Release/net8.0-windows/win-x64/
└── publish/
    ├── GeneradorAudioMP3.exe ⬅️ Ejecutable
    ├── NAudio.dll
    └── ... (librerías)

O como MSI:
Windows/GeneradorAudioMP3.msi ⬅️ Instalador
```

---

## 📥 DESCARGAR BINARIOS COMPILADOS

Si no deseas compilar, puedes descargar versiones precompiladas desde:

### GitHub Releases
```
https://github.com/santiagoazul2-design/GeneradorAudioMP3/releases
```

Busca:
- `GeneradorAudioMP3-1.0.0-x64.exe` (Windows)
- `GeneradorAudioMP3-1.0.0-x64.msi` (Windows Installer)
- `app-release.apk` (Android)
- `app-release.aab` (Android - Google Play)

---

## 🎯 INSTALACIÓN RÁPIDA

### ✅ Windows
1. Descargar `GeneradorAudioMP3.msi`
2. Doble click para instalar
3. Seguir wizard
4. ¡Listo! Buscar en Menú Inicio

### ✅ Android
1. Descargar `app-release.apk`
2. Transferir a tu Android
3. Permitir "Fuentes desconocidas" en Configuración
4. Tocar el APK e instalar
5. ¡Listo!

---

## 🆘 SOLUCIÓN DE PROBLEMAS

### "Command not found: dotnet"
→ Instalar .NET SDK: https://dotnet.microsoft.com/download

### "Command not found: gradlew"
→ Instalar Android Studio o JDK

### "No Android SDK found"
→ Ejecutar: `sdkmanager --install "platform-tools" "build-tools;33.0.0" "platforms;android-33"`

### "APK muy grande"
→ Usar `--self-contained=false` en dotnet publish

---

## 📊 TAMAÑOS ESPERADOS

| Archivo | Tamaño |
|---------|--------|
| Windows EXE (self-contained) | ~100-150 MB |
| Windows EXE (sin deps) | ~2-5 MB |
| Windows MSI | ~80-120 MB |
| Android APK | ~20-40 MB |
| Android AAB | ~15-30 MB |

---

## ✨ PRÓXIMO PASO

Ejecuta estos comandos en orden:

**Windows:**
```powershell
cd "C:\Users\Santy\.copilot\repos\copilot-worktrees\GeneradorAudioMP3\santiagoazul2-design-potential-guacamole"
cd Windows
dotnet publish -c Release -r win-x64 --self-contained
```

**Android:**
```bash
cd "C:\Users\Santy\.copilot\repos\copilot-worktrees\GeneradorAudioMP3\santiagoazul2-design-potential-guacamole"
cd Android
./gradlew bundleRelease
```

¿Necesitas ayuda con la instalación de requisitos? 😊
