package com.deny22.reserveroomif.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.adapter.ChatRVAdapter
import com.deny22.reserveroomif.databinding.ActivityChatBotBinding
import com.deny22.reserveroomif.model.chatbot.ChatModel

class ChatBotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBotBinding

    private lateinit var chatRVAdapter: ChatRVAdapter
    final var BOT_KEY = "bot"
    final var USER_KEY = "user"
    var listChatModel: ArrayList<ChatModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBotBinding.inflate(layoutInflater)
        val view = binding.root

        chatRVAdapter = ChatRVAdapter(listChatModel, this)
        var linearLayoutManager = LinearLayoutManager(this)

        binding.idIFChats.layoutManager = linearLayoutManager
        binding.idIFChats.adapter = chatRVAdapter

        binding.idFABSend.setOnClickListener {
            if (binding.idEditMessage.text.isEmpty()){
                Toast.makeText(this, "Por favor insira uma mensagem", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            getResponse(binding.idEditMessage.text.toString())
            binding.idEditMessage.setText("")
        }
        setContentView(view)
    }

    fun getResponse(message: String){
        listChatModel.add(ChatModel(message, USER_KEY))
        chatRVAdapter.notifyDataSetChanged()
        val timer = object: CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                listChatModel.add(ChatModel("Olá eu sou o bruno, ainda estou trabalhando neste bot, então entre em contato para mais detalhes, valeu e até mais.", BOT_KEY))
                chatRVAdapter.notifyDataSetChanged()
            }
        }
        timer.start()
        /*var url = message
        var msgModal = MsgModal(url)
        var BASE_URL = "https://rasa-horizon733.cloud.okteto.net/"
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitAPI = retrofit.create(RetrofitAPI::class.java)
        var call = retrofitAPI.getMessage(msgModal)
        call.enqueue(object : Callback<MsgModal>{
            override fun onResponse(call: Call<MsgModal>, response: Response<MsgModal>) {
                if (response.isSuccessful){
                    var msgModal = response.body()
                    if (msgModal != null) {
                        listChatModel.add(ChatModel(msgModal.cnt,BOT_KEY))
                        chatRVAdapter.notifyDataSetChanged()
                    }
                }
            }
            override fun onFailure(call: Call<MsgModal>, t: Throwable) {
                listChatModel.add(ChatModel("Olá eu sou o bruno, ainda estou trabalhando neste bot, então entre em contato, para mais detalhes, valeu e até mais.", BOT_KEY))
                chatRVAdapter.notifyDataSetChanged()
            }

        })*/
    }
}