# 🎵 Generador de Audio MP3

Aplicación multiplataforma para convertir texto a audio MP3 con síntesis de voz natural.

## 🚀 Características

### 📱 Android
- ✅ Síntesis de voz (Text-to-Speech)
- ✅ Múltiples velocidades (Lenta, Normal, Comercial, Rápida)
- ✅ Selección de voz (Masculina/Femenina)
- ✅ Generación de MP3
- ✅ Compartir archivos
- ✅ Interfaz moderna en Kotlin/MVVM

### 💻 Windows (64-bit)
- ✅ Interfaz WPF moderna
- ✅ SAPI5 - Síntesis de voz nativa de Windows
- ✅ Control de volumen y tono
- ✅ Múltiples idiomas (Español, Inglés, Francés)
- ✅ Preescucha en tiempo real
- ✅ Exportación a MP3 con NAudio

## 📋 Requisitos

### Android
- Android 7.0 (API 24) o superior
- Kotlin 1.8+
- Gradle 7.0+

### Windows
- Windows 10/11 64-bit
- .NET 8.0
- Visual Studio 2022 (o build con dotnet CLI)

## 🛠️ Instalación

### Android
```bash
cd Android
./gradlew build
./gradlew installDebug
```

### Windows
```bash
cd Windows
dotnet publish -c Release -r win-x64
```

## 📁 Estructura del Proyecto

```
GeneradorAudioMP3/
├── Android/                    # Aplicación Android (Kotlin)
│   ├── app/src/main/
│   ├── MainActivity.kt          # UI principal (refactorizado con MVVM)
│   └── AudioGeneratorViewModel.kt # Lógica de negocio
│
├── Windows/                    # Aplicación Windows (C# + WPF)
│   ├── App.xaml
│   ├── MainWindow.xaml         # Interfaz principal
│   ├── MainWindow.xaml.cs      # Lógica de la aplicación
│   └── GeneradorAudioMP3.Windows.csproj
│
└── README.md
```

## 🎯 Uso

### Android
1. Ingresa el texto a convertir
2. Selecciona voz (Masculina/Femenina) y velocidad
3. Presiona **Generar MP3**
4. Comparte o guarda el archivo

### Windows
1. Escribe o pega el texto
2. Configura idioma, voz y velocidad
3. Ajusta volumen y tono
4. Presiona **Generar MP3** o **Preescuchar**
5. El archivo se guarda automáticamente

## 🔧 Configuración Avanzada

### Variables de entorno
```
AUDIO_OUTPUT_PATH: Ruta de salida de archivos MP3
```

## 🐛 Reportar Problemas

Crea un issue en GitHub con:
- Sistema operativo y versión
- Descripción del problema
- Pasos para reproducir

## 📄 Licencia

MIT License - Libre para uso personal y comercial

## 👨‍💻 Autor

Santiago Azul - 2024
