package com.deny22.reserveroomif.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.activity.conteudo.ConteudoActivity
import com.deny22.reserveroomif.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        supportActionBar?.hide()

        setListerner()

        /*var dialogBuilder = MaterialAlertDialogBuilder(this)
        dialogBuilder
            .setTitle("Aviso de teste")
            .setMessage("Olá, como este é um app de teste, não rodamos nada no back end, você pode interagir porém bastar clicar em continuar.")
            .setNeutralButton("Fechar") { dialog, which ->
                // Respond to neutral button press
            }
            .setPositiveButton("OK") { dialog, which ->
                // Respond to positive button press
            }
            .show()*/



        setContentView(view)
    }

    fun loginEmailSenha(){
        var email = binding.loginEmail.text.toString()
        var senha = binding.loginSenha.text.toString()

        if (!email.isEmpty()){
            if (!senha.isEmpty()){

                mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(
                    OnCompleteListener {
                        if (it.isSuccessful){
                            startActivity(Intent(baseContext, ConteudoActivity::class.java))
                            finish()
                        } else if(!it.isSuccessful){
                            try {
                                throw it.exception!!
                            } catch (e: FirebaseAuthInvalidCredentialsException){
                                Toast.makeText(this, "Credenciais invalidas", Toast.LENGTH_SHORT).show()
                            } catch (e: FirebaseAuthInvalidUserException){
                                Toast.makeText(this, "Usuario não cadastrado", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception){
                                Toast.makeText(this, "Erro: "+e.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    })

            } else{
                Toast.makeText(this, "Digite a sua senha", Toast.LENGTH_SHORT).show()
            }
        } else{
            Toast.makeText(this, "Digite o seu email", Toast.LENGTH_SHORT).show()
        }

    }

    fun setListerner(){
        binding.buttonLoginEmailSenha.setOnClickListener(View.OnClickListener {
            loginEmailSenha()
        })

        binding.textViewFazerCadastro.setOnClickListener {
            startActivity(Intent(baseContext, CadastroActivity::class.java))
        }
    }

}