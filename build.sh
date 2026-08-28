#!/bin/bash

# Script para compilar automáticamente Windows y Android
# Ejecutar desde raíz del proyecto

echo "🚀 COMPILADOR AUTOMÁTICO - GeneradorAudioMP3"
echo "=============================================="
echo ""

# Detectar sistema operativo
if [[ "$OSTYPE" == "msys" ]] || [[ "$OSTYPE" == "cygwin" ]]; then
    OS="windows"
    echo "✅ Sistema: Windows"
else
    OS="linux"
    echo "✅ Sistema: Linux/Mac"
fi

# Compilar Windows
echo ""
echo "📦 COMPILANDO WINDOWS..."
if command -v dotnet &> /dev/null; then
    cd Windows
    echo "  • Restaurando dependencias..."
    dotnet restore GeneradorAudioMP3.Windows.csproj
    
    echo "  • Compilando Release..."
    dotnet publish -c Release -r win-x64 --self-contained -p:PublishTrimmed=true
    
    if [ -f "bin/Release/net8.0-windows/win-x64/publish/GeneradorAudioMP3.exe" ]; then
        echo "✅ Windows compilado exitosamente"
        echo "   📁 Ubicación: Windows/bin/Release/net8.0-windows/win-x64/publish/"
    else
        echo "❌ Error compilando Windows"
    fi
    cd ..
else
    echo "⚠️  .NET SDK no instalado. Descargar de: https://dotnet.microsoft.com/download"
fi

# Compilar Android
echo ""
echo "📦 COMPILANDO ANDROID..."
if command -v java &> /dev/null; then
    cd Android
    echo "  • Limpiando builds anteriores..."
    chmod +x gradlew
    ./gradlew clean
    
    echo "  • Compilando AAB (Android App Bundle)..."
    ./gradlew bundleRelease
    
    if [ -f "app/build/outputs/bundle/release/app-release.aab" ]; then
        echo "✅ Android compilado exitosamente"
        echo "   📁 Ubicación: Android/app/build/outputs/bundle/release/"
    else
        echo "❌ Error compilando Android"
    fi
    cd ..
else
    echo "⚠️  Java/Android SDK no instalado. Descargar Android Studio de: https://developer.android.com/studio"
fi

echo ""
echo "=============================================="
echo "✨ ¡COMPILACIÓN COMPLETADA!"
echo ""
echo "📥 Descarga tus archivos de:"
echo "   • Windows: Windows/bin/Release/net8.0-windows/win-x64/publish/"
echo "   • Android: Android/app/build/outputs/bundle/release/"
