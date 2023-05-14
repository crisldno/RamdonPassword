package co.com.ramdonpassword

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var generateButton: Button
    private lateinit var copyButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generateButton = findViewById(R.id.generateButton)
        copyButton = findViewById(R.id.copyButton)
        resultTextView = findViewById(R.id.resultTextView)

        generateButton.setOnClickListener {
            val randomString = generarStringAleatorio()
            resultTextView.text = randomString
        }

        copyButton.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Contraseña", resultTextView.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Contraseña copiada al portapapeles", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generarStringAleatorio(): String {
        val caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()"
        val longitud = 7

        val random = Random(System.currentTimeMillis())
        val sb = StringBuilder(longitud)
        repeat(longitud) {
            val indiceAleatorio = random.nextInt(caracteres.length)
            val caracterAleatorio = caracteres[indiceAleatorio]
            sb.append(caracterAleatorio)
        }
        return sb.toString()
    }
}
