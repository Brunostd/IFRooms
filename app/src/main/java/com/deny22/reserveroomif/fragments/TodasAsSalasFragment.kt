package com.deny22.reserveroomif.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.adapter.AgendamentoRapidoAdapter
import com.deny22.reserveroomif.databinding.FragmentTodasAsSalasBinding
import com.deny22.reserveroomif.model.AgendamentoRapidoModel
import com.deny22.reserveroomif.model.SalasBlocoModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class TodasAsSalasFragment : Fragment() {
    private var _binding: FragmentTodasAsSalasBinding? = null
    private val binding get() = _binding!!

    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodasAsSalasBinding.inflate(inflater, container, false)
        val view = binding.root

        getSalas()
        setListernes()

        return view
    }

    fun getSalas(){

        var listaSalas: MutableList<SalasBlocoModel> = arrayListOf()

        db.collection("salas")
            .get()
            .addOnSuccessListener { result ->
                for (documents in result){
                    var note = documents.toObject(SalasBlocoModel::class.java)
                    listaSalas.add(note)
                }
                binding.recyclerAgendamentoRapido.adapter = AgendamentoRapidoAdapter(listaSalas)
            }
    }

    fun setListernes(){
        binding.backTodasAsSalas.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}