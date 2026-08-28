## 🚀 Guía de Compilación

### Windows 64-bit

#### Requisitos
- Windows 10/11 64-bit
- .NET 8.0 SDK ([descargar](https://dotnet.microsoft.com/download/dotnet/8.0))
- Visual Studio 2022 o VS Code

#### Pasos

1. **Instalar .NET SDK**
   ```powershell
   # Verificar instalación
   dotnet --version
   ```

2. **Restaurar dependencias**
   ```powershell
   cd Windows
   dotnet restore GeneradorAudioMP3.Windows.csproj
   ```

3. **Compilar**
   ```powershell
   dotnet build -c Release -r win-x64
   ```

4. **Publicar (crear ejecutable standalone)**
   ```powershell
   dotnet publish -c Release -r win-x64 --self-contained
   # Ejecutable en: bin/Release/net8.0-windows/win-x64/publish/GeneradorAudioMP3.exe
   ```

5. **Empaquetar como MSI (Instalador)**
   ```powershell
   # Usar WiX Toolset o NSIS
   # Archivo .exe listo para distribución
   ```

---

### Android (APK)

#### Requisitos
- Android Studio o SDK Command-line tools
- JDK 11+ instalado
- Kotlin Plugin

#### Pasos

1. **Compilar APK Debug**
   ```bash
   cd Android
   ./gradlew assembleDebug
   # APK en: app/build/outputs/apk/debug/app-debug.apk
   ```

2. **Compilar APK Release (Firmado)**
   ```bash
   ./gradlew bundleRelease
   # Bundle en: app/build/outputs/bundle/release/app-release.aab
   ```

3. **Instalar en dispositivo**
   ```bash
   ./gradlew installDebug
   ```

4. **Publicar en Google Play**
   - Crear cuenta en Google Play Console
   - Subir archivo .aab
   - Seguir proceso de revisión

---

### Distribución

#### Windows
- **Instalador MSI**: Crear con WiX Toolset
- **Portable EXE**: Distribución directa
- **Store**: Microsoft Store (opcional)

#### Android
- **APK directo**: Para testing
- **Google Play**: Para producción
- **F-Droid**: Para open-source (si aplica)
