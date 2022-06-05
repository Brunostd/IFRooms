package com.deny22.reserveroomif.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.databinding.FragmentTodasAsSalasBinding

class TodasAsSalasFragment : Fragment() {
    private var _binding: FragmentTodasAsSalasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodasAsSalasBinding.inflate(inflater, container, false)
        val view = binding.root

        setListernes()

        return view
    }

    fun setListernes(){
        binding.backTodasAsSalas.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.button6.setOnClickListener {
            findNavController().navigate(R.id.action_todasAsSalasFragment_to_salaSelecionadaFragment)
        }

        binding.button7.setOnClickListener {
            findNavController().navigate(R.id.action_todasAsSalasFragment_to_salaSelecionadaFragment)
        }

        binding.button8.setOnClickListener {
            findNavController().navigate(R.id.action_todasAsSalasFragment_to_salaSelecionadaFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}