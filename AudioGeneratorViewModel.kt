package com.example.generadoraudio

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import java.io.File
import java.util.*

class AudioGeneratorViewModel(
    private val context: Context
) {
    private lateinit var tts: TextToSpeech
    private var audioFile: File? = null
    
    interface AudioListener {
        fun onStatusChanged(status: String)
        fun onAudioGenerated(file: File)
        fun onError(error: String)
    }
    
    private var listener: AudioListener? = null
    
    fun initialize(listener: AudioListener) {
        this.listener = listener
        tts = TextToSpeech(context) { result ->
            if (result == TextToSpeech.SUCCESS) {
                val spanish = Locale("es", "EC")
                val available = tts.isLanguageAvailable(spanish)
                tts.language = if (available >= TextToSpeech.LANG_AVAILABLE) spanish else Locale("es", "ES")
                listener.onStatusChanged("Listo para generar audio.")
            } else {
                listener.onError("No se pudo inicializar la síntesis de voz.")
            }
        }
    }
    
    fun generateAudio(
        text: String,
        language: String = "Español",
        voice: String = "Masculina",
        speed: String = "Normal"
    ) {
        if (text.trim().isEmpty()) {
            listener?.onError("El texto no puede estar vacío.")
            return
        }
        
        try {
            listener?.onStatusChanged("Generando audio...")
            
            val speedValue = when (speed) {
                "Lenta" -> 0.85f
                "Normal" -> 1.0f
                "Comercial" -> 1.08f
                "Rápida" -> 1.18f
                else -> 1.0f
            }
            
            val pitchValue = when (voice) {
                "Masculina" -> 0.85f
                else -> 1.0f
            }
            
            tts.setSpeechRate(speedValue)
            tts.setPitch(pitchValue)
            
            audioFile = File(context.getExternalFilesDir(null), "audio_comercial.mp3")
            audioFile?.delete()
            
            val params = android.os.Bundle()
            params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "audio")
            
            tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(id: String?) {
                    listener?.onStatusChanged("Generando audio...")
                }
                
                override fun onDone(id: String?) {
                    if (audioFile != null && audioFile!!.exists()) {
                        listener?.onStatusChanged("MP3 generado correctamente.")
                        listener?.onAudioGenerated(audioFile!!)
                    }
                }
                
                override fun onError(id: String?) {
                    listener?.onError("Error al generar el audio.")
                }
            })
            
            val result = tts.synthesizeToFile(text, params, audioFile, "audio")
            if (result != TextToSpeech.SUCCESS) {
                listener?.onError("No se pudo generar el MP3.")
            }
        } catch (e: Exception) {
            listener?.onError("Error: ${e.message}")
        }
    }
    
    fun shutdown() {
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
    }
}
