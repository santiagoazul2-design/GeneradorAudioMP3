# Guía: Publicar APK en Google Play

## 1. Preparar Keystore

```bash
# Crear keystore (primera vez)
chmod +x create_keystore.sh
./create_keystore.sh

# O manualmente:
keytool -genkey -v -keystore keystore/release.keystore \
    -keyalg RSA -keysize 2048 -validity 10950 \
    -alias generador_audio
```

## 2. Configurar Variables de Entorno

```powershell
# Windows
[Environment]::SetEnvironmentVariable("KEYSTORE_PASSWORD", "tu_password", "User")
[Environment]::SetEnvironmentVariable("KEY_PASSWORD", "tu_password", "User")
[Environment]::SetEnvironmentVariable("KEY_ALIAS", "generador_audio", "User")

# Recargar variables
$env:KEYSTORE_PASSWORD = "tu_password"
```

```bash
# Linux/Mac
export KEYSTORE_PASSWORD="tu_password"
export KEY_PASSWORD="tu_password"
export KEY_ALIAS="generador_audio"
```

## 3. Compilar APK Firmado

```bash
cd Android
./gradlew bundleRelease

# Salida: app/build/outputs/bundle/release/app-release.aab
```

## 4. Crear Cuenta Google Play Console

1. Ir a [play.google.com/console](https://play.google.com/console)
2. Crear nueva app
3. Completar información básica
4. Subir screenshots y descripción
5. Agregar política de privacidad

## 5. Subir AAB (Android App Bundle)

```
Google Play Console → Tu App → Versiones de producción → Nuevo lanzamiento
→ Adjuntar app-release.aab
```

## 6. Información Requerida

**Ficha de Play Store:**
- Título: "Generador de Audio MP3"
- Descripción: "Convierte texto en audio natural con múltiples voces"
- Categoría: Productividad / Multimedia
- Privacidad: URL a política

**Clasificación de contenido:**
- Seleccionar categorías aplicables
- Completar cuestionario
- Google genera rating automático

**Publicación:**
- Seleccionar países
- Establecer precio (gratuito)
- Programar lanzamiento

## 7. Revisar y Publicar

- Google revisa: 2-4 horas típicamente
- Si hay problemas: recibir email en 24-48h
- Una vez aprobado: visible en Play Store

## 8. Monitorear

```
Google Play Console → Tu App → Estadísticas
- Instalaciones diarias
- Desinstalaciones
- Ratings y reseñas
- Logs de errores
```

## 9. Actualizaciones

Para cada actualización:
1. Incrementar `versionCode` y `versionName` en build.gradle.kts
2. Compilar nuevo AAB
3. Subir a Google Play
4. Esperar revisión

## Seguridad: Proteger Keystore

```bash
# Nunca commitear keystore a Git
echo "keystore/release.keystore" >> .gitignore

# Almacenar en lugar seguro
# Opción 1: Encriptado localmente
# Opción 2: GitHub Secrets (para CI/CD)
# Opción 3: Gestor de secretos (Vault, LastPass, etc.)
```
