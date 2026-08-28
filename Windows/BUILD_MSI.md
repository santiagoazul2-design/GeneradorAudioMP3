# Compilar instalador MSI con WiX

## Requisitos
- WiX Toolset 3.14+ ([descargar](https://github.com/wixtoolset/wix3/releases))
- Visual Studio 2022 con extensión WiX (opcional)

## Pasos

### 1. Instalar WiX Toolset
```powershell
# Descargar e instalar desde:
# https://github.com/wixtoolset/wix3/releases/download/wix3141rtm/wix314.exe
```

### 2. Publicar la aplicación Windows
```powershell
cd Windows
dotnet publish -c Release -r win-x64 --self-contained -p:PublishTrimmed=true
```

### 3. Compilar MSI
```powershell
# Desde command line
candle.exe GeneradorAudioMP3.wxs -o obj\
light.exe -out GeneradorAudioMP3.msi obj\GeneradorAudioMP3.wixobj

# O usar Visual Studio
msbuild GeneradorAudioMP3.wixproj -t:Build -p:Configuration=Release
```

### 4. Verificar instalador
```powershell
# Instalar en modo test
msiexec /i GeneradorAudioMP3.msi /l*v install.log

# Verificar log
Get-Content install.log
```

### 5. Crear instalador con auto-actualización (opcional)
```powershell
# Modificar GeneradorAudioMP3.wxs para agregar:
# - Update Check URL
# - Auto-download de nuevas versiones
# - Rollback automático en caso de error
```

## Distribución

### Opción 1: Direct Download
```
https://releases.example.com/GeneradorAudioMP3-1.0.0-x64.msi
```

### Opción 2: Windows Store
1. Empaquetar como MSIX
2. Subir a Microsoft Store
3. Proceso de revisión: 1-3 días

### Opción 3: Directorio Compartido
```powershell
# Crear recurso compartido en red corporativa
\\server\software\GeneradorAudioMP3.msi
```
