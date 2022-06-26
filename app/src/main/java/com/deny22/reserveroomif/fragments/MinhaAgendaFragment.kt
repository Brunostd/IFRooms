package com.deny22.reserveroomif.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.adapter.MinhaAgendaAdapter
import com.deny22.reserveroomif.databinding.FragmentMinhaAgendaBinding
import com.deny22.reserveroomif.helper.Base64Custom
import com.deny22.reserveroomif.model.minhaagenda.MinhaAgendaModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MinhaAgendaFragment : Fragment() {
    private var _binding: FragmentMinhaAgendaBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val db = Firebase.firestore
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMinhaAgendaBinding.inflate(inflater, container, false)
        val view = binding.root

        getMinhaAgenda()

        return view
    }

    fun getMinhaAgenda(){
        var idEmail = Base64Custom().codificarBase64(firebaseAuth.currentUser.email.toString())
        var listaMinhaAgenda: MutableList<MinhaAgendaModel> = arrayListOf()

        db.collection("reservasSalvas")
            .whereEqualTo("idEmail", idEmail)
            .get()
            .addOnSuccessListener { result ->
                for (documents in result){
                    var note = documents.toObject(MinhaAgendaModel::class.java)

                    listaMinhaAgenda.add(note)
                }
                if (listaMinhaAgenda.isEmpty()){
                    binding.textCheckAgenda.visibility = View.VISIBLE
                } else{
                    binding.textCheckAgenda.visibility = View.INVISIBLE
                }
                binding.recyclerMinhaAgenda.adapter = MinhaAgendaAdapter(listaMinhaAgenda)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}