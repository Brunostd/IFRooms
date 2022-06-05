package com.deny22.reserveroomif.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.databinding.FragmentAgendamentoAwaitBinding

class AgendamentoAwaitFragment : Fragment() {
    private var _binding: FragmentAgendamentoAwaitBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgendamentoAwaitBinding.inflate(inflater, container, false)
        val view = binding.root


        val timer = object: CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                binding.frameAnimationLoading.visibility = View.INVISIBLE
                binding.animationConfirmed.visibility = View.VISIBLE
                binding.animationViewConfirmed.playAnimation()
                binding.textTitleAwait.text = "Parabéns\nsala reservada com sucesso"
                binding.button3.visibility = View.VISIBLE
                binding.backAwait.visibility = View.VISIBLE
            }
        }
        timer.start()

        binding.button3.setOnClickListener {
            findNavController().navigate(R.id.action_agendamentoAwaitFragment_to_comprovanteFragment)
        }

        binding.backAwait.setOnClickListener {
            findNavController().navigate(R.id.action_agendamentoAwaitFragment_to_conteudoFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}