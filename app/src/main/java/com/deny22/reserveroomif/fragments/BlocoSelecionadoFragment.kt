package com.deny22.reserveroomif.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.deny22.ifreserverdroom.adapter.SalaBlocoAdapter
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.databinding.FragmentBlocoSelecionadoBinding
import com.deny22.reserveroomif.model.SalasBlocoModel
import com.deny22.reserveroomif.model.blocos.BlocoModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class BlocoSelecionadoFragment : Fragment() {
    private var _binding: FragmentBlocoSelecionadoBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: BlocoSelecionadoFragmentArgs by navArgs()
    private var db = Firebase.firestore
    private var listaSalasBlocos: MutableList<SalasBlocoModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlocoSelecionadoBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.textViewRetornar.setOnClickListener {
            findNavController().popBackStack()
        }

        /*addListaSalasBlocos()
        binding.recyclerSalasBloco.adapter = SalaBlocoAdapter(listaSalasBlocos)*/
        getSalasDoBloco(args.blocoSelecionado.name)

        setView()

        return view
    }

    fun setView(){
        binding.textViewNameBlocoSelecionado.text = args.blocoSelecionado.name
        binding.textTotalSalasBlocoSelecionado.text = args.blocoSelecionado.salas
        binding.textSalaDisponiveisBlocoSelecionado.text = args.blocoSelecionado.salas
    }

    fun getSalasDoBloco(bloco: String): MutableList<SalasBlocoModel>{
        var listaSalas: MutableList<SalasBlocoModel> = arrayListOf()

        db.collection("salas")
            .whereEqualTo("bloco", bloco)
            .get()
            .addOnSuccessListener { result ->
                for (documents in result){
                    var note = documents.toObject(SalasBlocoModel::class.java)
                    listaSalas.add(note)
                }
                binding.recyclerSalasBloco.adapter = SalaBlocoAdapter(listaSalas)
            }
        return listaSalas
    }

    /*fun addListaSalasBlocos(){
        var p: SalasBlocoModel = SalasBlocoModel(R.drawable.computer_lab, "Sala 01")
        listaSalasBlocos.add(p)

        p = SalasBlocoModel(R.drawable.computer_lab, "Sala 02")
        listaSalasBlocos.add(p)

        p = SalasBlocoModel(R.drawable.computer_lab, "Sala 03")
        listaSalasBlocos.add(p)

        p = SalasBlocoModel(R.drawable.computer_lab, "Sala 04")
        listaSalasBlocos.add(p)

        p = SalasBlocoModel(R.drawable.computer_lab, "Sala 05")
        listaSalasBlocos.add(p)
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}