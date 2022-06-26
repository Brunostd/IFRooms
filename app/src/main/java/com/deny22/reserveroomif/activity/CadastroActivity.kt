package com.deny22.reserveroomif.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.activity.conteudo.ConteudoActivity
import com.deny22.reserveroomif.databinding.ActivityCadastroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        val view = binding.root

        setListerner()

        setContentView(view)
    }

    fun setListerner(){

        binding.backCadastro.setOnClickListener {
            startActivity(Intent(baseContext, LoginActivity::class.java))
            finish()
        }
        binding.buttonRealizarCadastro.setOnClickListener {
            meCadastrar()
        }
    }

    fun meCadastrar(){

        var email = binding.textEmailCadastro.text.toString()
        var senha = binding.textSenhaCadastro.text.toString()

        if (email.isNotEmpty()){
            if (senha.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        try {
                            throw task.exception!!
                        } catch (e: FirebaseAuthWeakPasswordException) {
                            Toast.makeText(applicationContext, "Senha fraca, digite uma senha mais forte", Toast.LENGTH_LONG).show()
                        } catch (e: FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(applicationContext, "Email invalido, digite um email valido", Toast.LENGTH_LONG).show()
                        } catch (e: FirebaseAuthUserCollisionException) {
                            Toast.makeText(applicationContext, "Usuario já existente", Toast.LENGTH_LONG).show()
                        } catch (e: Exception) {
                        }
                    } else if(task.isSuccessful){
                        startActivity(Intent(this, ConteudoActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }

}