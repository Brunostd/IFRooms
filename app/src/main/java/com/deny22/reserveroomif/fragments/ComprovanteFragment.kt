package com.deny22.reserveroomif.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.databinding.FragmentComprovanteBinding
import com.google.firebase.auth.FirebaseAuth

class ComprovanteFragment : Fragment() {
    private var _binding: FragmentComprovanteBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: ComprovanteFragmentArgs by navArgs()

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentComprovanteBinding.inflate(inflater, container, false)
        val view = binding.root

        setView()

        binding.backComprovant.setOnClickListener {
            findNavController().navigate(R.id.action_comprovanteFragment_to_conteudoFragment)
        }

        return view
    }

    fun setView(){
        binding.textComprovantName.text = args.salaComprovant.name
        binding.textComprovantHorario.text = "Agendamento concluido\n"+args.horarioComprovant
        binding.textComprovantUsuario.text = firebaseAuth.currentUser.email
        binding.textComprovantAssentos.text = args.salaComprovant.assentos.toString()
        binding.textComprovantComputadores.text = args.salaComprovant.computadores.toString()
        binding.textComprovantProjetores.text = args.salaComprovant.projetores.toString()
        binding.textComprovantQuadros.text = args.salaComprovant.quadros.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}