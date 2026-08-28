#!/bin/bash

# Script para crear keystore de firma para APK
# Ejecutar una sola vez para generar el keystore

KEYSTORE_PATH="keystore/release.keystore"
KEYSTORE_PASSWORD=${KEYSTORE_PASSWORD:-"GeneradorAudio2024!"}
KEY_ALIAS="generador_audio"
KEY_PASSWORD=${KEY_PASSWORD:-"GeneradorAudio2024!"}
VALIDITY_DAYS=10950  # 30 años

# Crear directorio
mkdir -p keystore

# Generar keystore
keytool -genkey -v \
    -keystore "$KEYSTORE_PATH" \
    -keyalg RSA \
    -keysize 2048 \
    -validity $VALIDITY_DAYS \
    -alias "$KEY_ALIAS" \
    -storepass "$KEYSTORE_PASSWORD" \
    -keypass "$KEY_PASSWORD" \
    -dname "CN=Santiago Azul, OU=GeneradorAudio, O=Personal, L=Ecuador, ST=Pichincha, C=EC"

# Verificar keystore
keytool -list -v -keystore "$KEYSTORE_PATH" -storepass "$KEYSTORE_PASSWORD"

echo "✓ Keystore creado en: $KEYSTORE_PATH"
echo "✓ Alias: $KEY_ALIAS"
echo ""
echo "⚠️  IMPORTANTE: Guarda en lugar seguro"
echo "   Pérdida del keystore = No podrás actualizar la app en Google Play"
