package com.deny22.reserveroomif.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.databinding.FragmentComprovantMinhaAgendaBinding
import com.deny22.reserveroomif.helper.Base64Custom
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ComprovantMinhaAgendaFragment : Fragment() {
    private var _binding: FragmentComprovantMinhaAgendaBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: ComprovantMinhaAgendaFragmentArgs by navArgs()
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentComprovantMinhaAgendaBinding.inflate(inflater, container, false)
        val view = binding.root

        setView()
        setListerner()

        return view
    }

    fun setView(){

        var email = Base64Custom().decodificarBase64(args.comprovantMinhaAgenda.idEmail)

        binding.textComprovantMinhaAgendaName.text = args.comprovantMinhaAgenda.sala
        binding.textComprovantMinhaAgendaData.text = args.comprovantMinhaAgenda.data
        binding.textComprovantMinhaAgendaHora.text = args.comprovantMinhaAgenda.horario
        binding.textComprovantMinhaAgendaAssentos.text = args.comprovantMinhaAgenda.assentos
        binding.textComprovantMinhaAgendaComputadores.text = args.comprovantMinhaAgenda.computadores
        binding.textComprovantMinhaAgendaProjetores.text = args.comprovantMinhaAgenda.projetores
        binding.textComprovantMinhaAgendaQuadros.text = args.comprovantMinhaAgenda.quadros
        binding.textComprovantMinhaAgendaUsuario.text = email
    }

    fun setListerner(){
        binding.backComprovantMinhaAgenda.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonCancelarAgendamento.setOnClickListener {
            var auxHorario = args.comprovantMinhaAgenda.horario.substring(0,14)+" - "+"Indisponível"

            if (auxHorario == "18:30 às 20:00 - Indisponível"){
                var reservaDetail: MutableMap<String, Any> = HashMap()
                reservaDetail.put("horario1", "18:30 às 20:00 - Disponível")

                db.collection("reservar")
                    .whereEqualTo("data", args.comprovantMinhaAgenda.data)
                    .whereEqualTo("sala", args.comprovantMinhaAgenda.sala)
                    .get()
                    .addOnCompleteListener {
                        var documentSnapShot = it.getResult().getDocumentChanges().get(0)
                        var documentID = documentSnapShot.document.id
                        db.collection("reservar")
                            .document(documentID)
                            .update(reservaDetail)
                            .addOnSuccessListener {
                                Toast.makeText(requireContext(), "Agendamento cancelado com sucesso", Toast.LENGTH_LONG).show()
                                removeAt()
                                findNavController().navigate(R.id.action_comprovantMinhaAgendaFragment_to_conteudoFragment)
                            }.addOnFailureListener {
                                Toast.makeText(requireContext(), "Falha no cancelamento", Toast.LENGTH_LONG).show()
                            }
                    }
            } else if (auxHorario == "20:20 às 21:50 - Indisponível"){
                var reservaDetail: MutableMap<String, Any> = HashMap()
                reservaDetail.put("horario2", "20:20 às 21:50 - Disponível")

                db.collection("reservar")
                    .whereEqualTo("data", args.comprovantMinhaAgenda.data)
                    .whereEqualTo("sala", args.comprovantMinhaAgenda.sala)
                    .get()
                    .addOnCompleteListener {
                        var documentSnapShot = it.getResult().getDocumentChanges().get(0)
                        var documentID = documentSnapShot.document.id
                        db.collection("reservar")
                            .document(documentID)
                            .update(reservaDetail)
                            .addOnSuccessListener {
                                Toast.makeText(requireContext(), "Agendamento cancelado com sucesso", Toast.LENGTH_LONG).show()
                                removeAt()
                                findNavController().navigate(R.id.action_comprovantMinhaAgendaFragment_to_conteudoFragment)
                            }.addOnFailureListener {
                                Toast.makeText(requireContext(), "Falha no cancelamento", Toast.LENGTH_LONG).show()
                            }
                    }
            }
        }
    }

    fun removeAt(){
        db.collection("reservasSalvas")
            .whereEqualTo("data", args.comprovantMinhaAgenda.data)
            .whereEqualTo("sala", args.comprovantMinhaAgenda.sala)
            .whereEqualTo("horario", args.comprovantMinhaAgenda.horario)
            .whereEqualTo("idEmail", args.comprovantMinhaAgenda.idEmail)
            .get()
            .addOnCompleteListener {
                var documentSnapShot = it.getResult().getDocumentChanges().get(0)
                var documentID = documentSnapShot.document.id
                db.collection("reservasSalvas")
                    .document(documentID)
                    .delete()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}