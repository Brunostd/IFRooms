package com.deny22.reserveroomif

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.deny22.reserveroomif.activity.LoginActivity
import com.deny22.reserveroomif.activity.conteudo.ConteudoActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        mAuth = FirebaseAuth.getInstance()
        var user = mAuth.currentUser

        val timer = Timer()
        timer.schedule(timerTask {
            if (user!=null){
                startActivity(Intent(baseContext, ConteudoActivity::class.java))
                finish()
            } else{
                startActivity(Intent(baseContext, LoginActivity::class.java))
                finish()
            }
        }, 2000)

    }
}