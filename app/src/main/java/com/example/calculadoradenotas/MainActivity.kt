package com.example.calculadoradenotas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layResult.visibility = View.INVISIBLE
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        btnCalcular.setOnClickListener{
            var valido = true
            if (edtNota1.text.toString().trim().isEmpty()) {
                valido = false
                edtNota1.error = getString(R.string.erro)
            }
            if (edtNota2.text.toString().trim().isEmpty()) {
                valido = false
                edtNota2.error = getString(R.string.erro)
            }
            if (edtFaltas.text.toString().trim().isEmpty()) {
                valido = false
                edtFaltas.error = getString(R.string.erro)
            }

            if (valido){
                layResult.visibility = View.VISIBLE
                imm.hideSoftInputFromWindow(edtNota1.windowToken, 0)
                var notaUm: Float = edtNota1.text.toString().toFloat()
                var notaDois: Float = edtNota2.text.toString().toFloat()
                var faltas: Int = edtFaltas.text.toString().toInt()

                var media: Float = (notaUm!! + notaDois!!)/2
                var mediaFormat = "Média: %.2f".format(media)

                if (media >= 7 && faltas!! <= 5){
                    txtSituacao.text = getString(R.string.aprov)
                    txtSituacao.setTextColor(ContextCompat.getColor(this, R.color.green))
                    Toast.makeText(this, getString(R.string.toastAprov), Toast.LENGTH_SHORT).show()
                    txtMedia.text = mediaFormat
                    txtMedia.setTextColor(ContextCompat.getColor(this, R.color.green))
                    txtFaltas.text = "Faltas: $faltas"
                    txtFaltas.setTextColor(ContextCompat.getColor(this, R.color.green))
                    imgSituacao.setImageResource(R.drawable.emoji_aprovado)
                }
                else if(media in 6.0..7.0 && faltas!! < 10){
                    txtSituacao.text = getString(R.string.rec)
                    txtSituacao.setTextColor(ContextCompat.getColor(this, R.color.yellow))
                    Toast.makeText(this, getString(R.string.toastRec), Toast.LENGTH_SHORT).show()
                    txtMedia.text = mediaFormat
                    txtMedia.setTextColor(ContextCompat.getColor(this, R.color.yellow))
                    txtFaltas.text = "Faltas: $faltas"
                    txtFaltas.setTextColor(ContextCompat.getColor(this, R.color.yellow))
                    imgSituacao.setImageResource(R.drawable.emoji_recuperacao)
                }
                else if (media <= 5 || faltas!! >= 10) {
                    txtSituacao.text = getString(R.string.reprov)
                    txtSituacao.setTextColor(ContextCompat.getColor(this, R.color.red))
                    Toast.makeText(this, getString(R.string.toastReprov), Toast.LENGTH_SHORT).show()
                    txtMedia.text = mediaFormat
                    txtMedia.setTextColor(ContextCompat.getColor(this, R.color.red))
                    txtFaltas.text = "Faltas: $faltas"
                    txtFaltas.setTextColor(ContextCompat.getColor(this, R.color.red))
                    imgSituacao.setImageResource(R.drawable.emoji_reprovado)
                }
            }

         }

    }
}

