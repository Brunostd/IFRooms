package com.deny22.reserveroomif.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.databinding.FragmentAgendarHorasBinding
import com.deny22.reserveroomif.model.reservar.ReservarModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class AgendarHorasFragment : Fragment() {
    private var _binding: FragmentAgendarHorasBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: AgendarHorasFragmentArgs by navArgs()
    private var db = Firebase.firestore

    private var auxHorario: String = ""
    private var auxData: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgendarHorasBinding.inflate(inflater, container, false)
        val view = binding.root

        getHorarios(args.agendarSalaHoras.name, args.data)

        setListerner()
        setView()

        return view
    }

    fun setView(){

        binding.button2.isEnabled = false

        if (auxHorario == "18:30 às 20:00 - Disponível"){
            binding.button2.isEnabled = true
        } else if (auxHorario == "20:20 às 21:50 - Disponível") {
            binding.button2.isEnabled = true
        }
    }

    fun setListerner(){

        binding.backAgendamentHorario.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.button2.setOnClickListener {
            val action = AgendarHorasFragmentDirections.actionAgendarHorasFragmentToAgendamentReviewFragment(auxData, auxHorario, args.agendarSalaHoras)
            findNavController().navigate(action)
        }

        binding.textHorario1.setOnClickListener {
            binding.textView62.text = "Você selecionou\n"+binding.textHorario1.text.toString()
            binding.textView62.visibility = View.VISIBLE
            auxHorario = binding.textHorario1.text.toString()
            setView()
        }

        binding.textHorario2.setOnClickListener {
            binding.textView62.text = "Você selecionou\n"+binding.textHorario2.text.toString()
            binding.textView62.visibility = View.VISIBLE
            auxHorario = binding.textHorario2.text.toString()
            setView()
        }
    }

    fun getHorarios(sala: String, data: String){

        var reservar = ReservarModel()

        db.collection("reservar")
            .whereEqualTo("data", data)
            .whereEqualTo("sala", sala)
            .get()
            .addOnSuccessListener { result ->
                for(documents in result){
                    var note = documents.toObject(ReservarModel::class.java)

                    reservar = note
                }
                binding.textHorario1.text = reservar.horario1
                binding.textHorario2.text = reservar.horario2
                auxData = reservar.data
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}