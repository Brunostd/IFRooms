package com.deny22.reserveroomif.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.deny22.ifreserverdroom.adapter.SalaBlocoAdapter
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.databinding.FragmentBlocoSelecionadoBinding
import com.deny22.reserveroomif.model.SalasBlocoModel

class BlocoSelecionadoFragment : Fragment() {
    private var _binding: FragmentBlocoSelecionadoBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
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

        addListaSalasBlocos()
        binding.recyclerSalasBloco.adapter = SalaBlocoAdapter(listaSalasBlocos)

        return view
    }

    fun addListaSalasBlocos(){
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}