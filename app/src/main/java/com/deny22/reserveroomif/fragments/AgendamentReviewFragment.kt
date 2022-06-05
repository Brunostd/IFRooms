package com.deny22.reserveroomif.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.databinding.FragmentAgendamentReviewBinding
import com.ebanx.swipebtn.OnStateChangeListener

class AgendamentReviewFragment : Fragment() {
    private var _binding: FragmentAgendamentReviewBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgendamentReviewBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.swipeBtn.setOnStateChangeListener(object : OnStateChangeListener {
            override fun onStateChange(active: Boolean) {
                findNavController().navigate(R.id.action_agendamentReviewFragment_to_agendamentoAwaitFragment)
            }
        })

        binding.backReview.setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}