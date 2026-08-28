# 📋 Proguard Rules para Android

# Mantener la aplicación funcional
-keep class com.example.generadoraudio.** { *; }
-keep class com.example.generadoraudio.MainActivity { *; }
-keep class com.example.generadoraudio.AudioGeneratorViewModel { *; }

# Mantener interfaces y callbacks
-keep interface com.example.generadoraudio.AudioGeneratorViewModel$AudioListener { *; }

# TextToSpeech
-keep class android.speech.tts.** { *; }

# Androidx
-keep class androidx.** { *; }
-keep interface androidx.** { *; }

# Kotlin
-keep class kotlin.** { *; }
-keep class kotlinx.** { *; }

# Eliminar logs en release
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

# Optimización
-optimizationpasses 5
-dontusemixedcaseclassnames
-verbose

# Evitar ProGuard warnings
-dontwarn android.**
-dontwarn androidx.**
-dontwarn kotlin.**
