package com.example.generadoraudio

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var tts: TextToSpeech
    private lateinit var text: EditText
    private lateinit var voice: Spinner
    private lateinit var speed: Spinner
    private lateinit var generate: Button
    private lateinit var status: TextView

    private val voiceNames = arrayOf(
        "Masculina",
        "Femenina"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.textInput)
        voice = findViewById(R.id.voiceSpinner)
        speed = findViewById(R.id.speedSpinner)
        generate = findViewById(R.id.generateButton)
        status = findViewById(R.id.status)

        voice.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, voiceNames)
        speed.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
            arrayOf("Lenta", "Normal", "Comercial", "Rápida"))

        tts = TextToSpeech(this, this)

        generate.setOnClickListener { createMp3() }

        text.setText("Somos el nuevo canal de análisis político y geopolítico del Ecuador.")
    }

    override fun onInit(result: Int) {
        if (result == TextToSpeech.SUCCESS) {
            val spanish = Locale("es", "EC")
            val available = tts.isLanguageAvailable(spanish)
            tts.language = if (available >= TextToSpeech.LANG_AVAILABLE) spanish else Locale("es", "ES")
            status.text = "Listo para generar."
        } else {
            status.text = "No se pudo iniciar la voz del teléfono."
        }
    }

    private fun createMp3() {
        val content = text.text.toString().trim()
        if (content.isEmpty()) {
            Toast.makeText(this, "Escribe un texto.", Toast.LENGTH_SHORT).show()
            return
        }

        val speedValue = when (speed.selectedItemPosition) {
            0 -> 0.85f
            1 -> 1.0f
            2 -> 1.08f
            else -> 1.18f
        }

        tts.setSpeechRate(speedValue)
        tts.setPitch(if (voice.selectedItemPosition == 0) 0.85f else 1.0f)

        val file = File(getExternalFilesDir(null), "audio_comercial.mp3")
        file.delete()

        val params = Bundle()
        params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "audio")

        tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(id: String?) {
                runOnUiThread { status.text = "Generando audio..." }
            }
            override fun onDone(id: String?) {
                runOnUiThread {
                    status.text = "MP3 generado correctamente."
                    shareFile(file)
                }
            }
            override fun onError(id: String?) {
                runOnUiThread { status.text = "Error al generar el audio." }
            }
        })

        val result = tts.synthesizeToFile(content, params, file, "audio")
        if (result != TextToSpeech.SUCCESS) {
            status.text = "No se pudo generar el MP3."
        }
    }

    private fun shareFile(file: File) {
        val uri = androidx.core.content.FileProvider.getUriForFile(
            this, "${BuildConfig.APPLICATION_ID}.provider", file
        )
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "audio/mpeg"
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(intent, "Compartir audio"))
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}
