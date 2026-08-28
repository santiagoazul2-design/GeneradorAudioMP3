@echo off
REM Script para compilar automáticamente Windows y Android
REM Ejecutar desde raíz del proyecto

echo.
echo 🚀 COMPILADOR AUTOMÁTICO - GeneradorAudioMP3
echo ============================================
echo.

REM Verificar .NET
where /q dotnet
if %ERRORLEVEL% EQU 0 (
    echo 📦 COMPILANDO WINDOWS...
    cd Windows
    
    echo   - Restaurando dependencias...
    call dotnet restore GeneradorAudioMP3.Windows.csproj
    
    echo   - Compilando Release (x64)...
    call dotnet publish -c Release -r win-x64 --self-contained -p:PublishTrimmed=true
    
    if exist "bin\Release\net8.0-windows\win-x64\publish\GeneradorAudioMP3.exe" (
        echo ✅ Windows compilado exitosamente
        echo    Ubicación: Windows\bin\Release\net8.0-windows\win-x64\publish\
    ) else (
        echo ❌ Error compilando Windows
    )
    cd ..
) else (
    echo ⚠️  .NET SDK no instalado
    echo    Descargar de: https://dotnet.microsoft.com/download
)

REM Verificar Android
where /q gradlew.bat
if %ERRORLEVEL% EQU 0 (
    echo.
    echo 📦 COMPILANDO ANDROID...
    cd Android
    
    echo   - Limpiando builds...
    call gradlew.bat clean
    
    echo   - Compilando AAB...
    call gradlew.bat bundleRelease
    
    if exist "app\build\outputs\bundle\release\app-release.aab" (
        echo ✅ Android compilado exitosamente
        echo    Ubicación: Android\app\build\outputs\bundle\release\
    ) else (
        echo ❌ Error compilando Android
    )
    cd ..
) else (
    echo ⚠️  Gradle no encontrado. Descargar Android Studio
    echo    De: https://developer.android.com/studio
)

echo.
echo ============================================
echo ✨ ¡COMPILACIÓN COMPLETADA!
echo.
echo 📥 Descarga tus archivos compilados de:
echo    - Windows: Windows\bin\Release\net8.0-windows\win-x64\publish\
echo    - Android: Android\app\build\outputs\bundle\release\
echo.
pause
