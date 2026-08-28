package com.example.generadoraudio

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var text: EditText
    private lateinit var voice: Spinner
    private lateinit var speed: Spinner
    private lateinit var generate: Button
    private lateinit var status: TextView
    private lateinit var viewModel: AudioGeneratorViewModel

    private val voiceNames = arrayOf("Masculina", "Femenina")

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

        viewModel = AudioGeneratorViewModel(this)
        viewModel.initialize(object : AudioGeneratorViewModel.AudioListener {
            override fun onStatusChanged(status: String) {
                runOnUiThread { this@MainActivity.status.text = status }
            }

            override fun onAudioGenerated(file: File) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Audio guardado: ${file.name}", Toast.LENGTH_SHORT).show()
                    shareFile(file)
                }
            }

            override fun onError(error: String) {
                runOnUiThread {
                    this@MainActivity.status.text = error
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                }
            }
        })

        generate.setOnClickListener {
            val content = text.text.toString().trim()
            val selectedVoice = voiceNames[voice.selectedItemPosition]
            val speedOptions = arrayOf("Lenta", "Normal", "Comercial", "Rápida")
            val selectedSpeed = speedOptions[speed.selectedItemPosition]

            viewModel.generateAudio(content, voice = selectedVoice, speed = selectedSpeed)
        }

        text.setText("Somos el nuevo canal de análisis político y geopolítico del Ecuador.")
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
        viewModel.shutdown()
        super.onDestroy()
    }
}
