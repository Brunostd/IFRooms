package com.deny22.reserveroomif.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.databinding.FragmentAgendarHorasBinding

class AgendarHorasFragment : Fragment() {
    private var _binding: FragmentAgendarHorasBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgendarHorasBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.textView45.setOnClickListener {
            binding.textView62.visibility = View.VISIBLE
            binding.textView62.text = "Você escolheu\nturno da noite\n18:30 as 20:00"
            binding.button2.isEnabled = true
        }

        binding.textView46.setOnClickListener {
            binding.textView62.visibility = View.VISIBLE
            binding.textView62.text = "Horário\njá agendado\nescolha outro"
            binding.button2.isEnabled = false
        }

        binding.backAgendamentHorario.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_agendarHorasFragment_to_agendamentReviewFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}