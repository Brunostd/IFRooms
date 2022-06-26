package com.deny22.reserveroomif.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.databinding.FragmentSalaSelecionadaBinding

class SalaSelecionadaFragment : Fragment() {
    private var _binding: FragmentSalaSelecionadaBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: SalaSelecionadaFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSalaSelecionadaBinding.inflate(inflater, container, false)
        val view = binding.root

        setView()

        binding.textViewSalaSelecionada.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonContinuarAgendamento.setOnClickListener {
            val action = SalaSelecionadaFragmentDirections.actionSalaSelecionadaFragmentToAgendamentoFragment(args.salaSelecionada)
            findNavController().navigate(action)
        }

        return view
    }

    fun setView(){
        binding.textNameSalaSelecionada.text = args.salaSelecionada.name
        binding.textSalaSelecionadaAssentos.text = args.salaSelecionada.assentos.toString()
        binding.textSalaSelecionadaComputadores.text = args.salaSelecionada.computadores.toString()
        binding.textSalaSelecionadaProjetores.text = args.salaSelecionada.projetores.toString()
        binding.textSalaSelecionadaQuadros.text = args.salaSelecionada.quadros.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}