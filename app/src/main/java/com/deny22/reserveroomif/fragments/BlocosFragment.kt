package com.deny22.reserveroomif.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.databinding.FragmentBlocosBinding

class BlocosFragment : Fragment() {
    private var _binding: FragmentBlocosBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlocosBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.textViewBlocosRetornar.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.cardViewBloco.setOnClickListener {
            findNavController().navigate(R.id.action_blocosFragment_to_blocoSelecionadoFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}