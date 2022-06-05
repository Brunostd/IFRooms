package com.deny22.reserveroomif.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.activity.conteudo.ConteudoActivity
import com.deny22.reserveroomif.databinding.ActivityLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        supportActionBar?.hide()

        var dialogBuilder = MaterialAlertDialogBuilder(this)
        dialogBuilder
            .setTitle("Aviso de teste")
            .setMessage("Olá, como este é um app de teste, não rodamos nada no back end, você pode interagir porém bastar clicar em continuar.")
            .setNeutralButton("Fechar") { dialog, which ->
                // Respond to neutral button press
            }
            .setPositiveButton("OK") { dialog, which ->
                // Respond to positive button press
            }
            .show()

        binding.buttonLoginEmailSenha.setOnClickListener(View.OnClickListener {
            startActivity(Intent(baseContext, ConteudoActivity::class.java))
            finish()
        })

        setContentView(view)
    }
}