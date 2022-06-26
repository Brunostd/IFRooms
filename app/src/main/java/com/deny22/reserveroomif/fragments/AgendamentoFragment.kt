package com.deny22.reserveroomif.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.databinding.FragmentAgendamentoBinding
import kotlin.time.Duration.Companion.days


class AgendamentoFragment : Fragment() {
    private var _binding: FragmentAgendamentoBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: AgendamentoFragmentArgs by navArgs()
    private var date: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgendamentoBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.backAgendament.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.calendarView.setOnDateChangeListener { _, p1, p2, p3 ->
            date = p3.toString()+"/"+p2.toString()+"/"+p1.toString()
        }

        binding.button.setOnClickListener {
            if (date.isNotEmpty()){
                val action = AgendamentoFragmentDirections.actionAgendamentoFragmentToAgendarHorasFragment(args.agendamentoSala, date)
                findNavController().navigate(action)
            } else{
                Toast.makeText(requireContext(), "Por favor! escolha uma data.", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}