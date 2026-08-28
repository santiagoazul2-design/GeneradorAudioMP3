# Guía de Testing para GeneradorAudioMP3

## Testing Android

### Unit Tests
```bash
cd Android
./gradlew test
```

### Instrumented Tests (en dispositivo)
```bash
./gradlew connectedAndroidTest
```

### Cobertura de código
```bash
./gradlew testDebugUnitTestCoverage
```

## Testing Windows

### Unit Tests C#
```powershell
cd Windows
dotnet test GeneradorAudioMP3.Windows.csproj
```

### Testing de interfaz con UI Automation
```csharp
// Crear tests para:
// - Carga de texto
// - Selección de voz/velocidad
// - Generación de MP3
// - Reproducción
```

## Testing Manual

### Checklist Android
- [ ] Generar MP3 con texto en español
- [ ] Cambiar velocidad (Lenta, Normal, Comercial, Rápida)
- [ ] Cambiar voz (Masculina/Femenina)
- [ ] Compartir archivo generado
- [ ] Desinstalar y reinstalar
- [ ] Revisar permisos solicitados

### Checklist Windows
- [ ] Escribir texto y generar MP3
- [ ] Cambiar idiomas (Español, Inglés, Francés)
- [ ] Ajustar volumen (0-100%)
- [ ] Ajustar tono (Grave, Normal, Agudo)
- [ ] Preescuchar audio
- [ ] Verificar ubicación del archivo MP3
- [ ] Instalar/desinstalar desde MSI

## Performance Testing

### Memoria
```bash
# Android
adb shell dumpsys meminfo com.example.generadoraudio

# Windows
Get-Process GeneradorAudioMP3 | Select-Object WorkingSet
```

### Velocidad de generación
- Objetivo: < 3 segundos para 500 caracteres
- Objetivo: < 1 segundo para interfaz responsiva

## Load Testing

```bash
# Generar 100 MP3s consecutivos
for i in {1..100}; do
  echo "Generando archivo $i..."
  # Llamar API o función
done
```

## Logs & Debugging

### Android
```bash
adb logcat com.example.generadoraudio
```

### Windows
```powershell
Get-EventLog -LogName Application -Source "GeneradorAudioMP3"
```

## Reporting

- Reportar bugs en **Issues** de GitHub
- Incluir logs, screenshots, pasos para reproducir
- Especificar sistema operativo y versión
