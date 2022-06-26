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
import com.deny22.reserveroomif.databinding.FragmentAgendamentReviewBinding
import com.deny22.reserveroomif.helper.Base64Custom
import com.ebanx.swipebtn.OnStateChangeListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AgendamentReviewFragment : Fragment() {
    private var _binding: FragmentAgendamentReviewBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: AgendamentReviewFragmentArgs by navArgs()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgendamentReviewBinding.inflate(inflater, container, false)
        val view = binding.root

        setListerner()
        setView()

        return view
    }

    fun setView(){
        binding.textReviewSalaName.text = args.confirmacaoSalaReservar.name
        binding.textReviewData.text = args.data
        binding.textReviewHorario.text = args.horario
        binding.textReviewAssentos.text = args.confirmacaoSalaReservar.assentos.toString()
        binding.textReviewComputadores.text = args.confirmacaoSalaReservar.computadores.toString()
        binding.textReviewProjetores.text = args.confirmacaoSalaReservar.projetores.toString()
        binding.textReviewQuadros.text = args.confirmacaoSalaReservar.quadros.toString()
    }

    fun setListerner(){
        binding.swipeBtn.setOnStateChangeListener(object : OnStateChangeListener {
            override fun onStateChange(active: Boolean) {

                if (args.horario == "18:30 às 20:00 - Disponível"){
                    var reservaDetail: MutableMap<String, Any> = HashMap()
                    reservaDetail.put("horario1", "18:30 às 20:00 - Indisponível")

                    db.collection("reservar")
                        .whereEqualTo("data", args.data)
                        .whereEqualTo("sala", args.confirmacaoSalaReservar.name)
                        .get()
                        .addOnCompleteListener {
                            var documentSnapShot = it.getResult().getDocumentChanges().get(0)
                            var documentID = documentSnapShot.document.id
                            db.collection("reservar")
                                .document(documentID)
                                .update(reservaDetail)
                                .addOnSuccessListener {
                                    var auxHorario = "18:30 às 20:00 - Reservado"
                                    realizarMinhaReserva(auxHorario)
                                    val action = AgendamentReviewFragmentDirections.actionAgendamentReviewFragmentToAgendamentoAwaitFragment(
                                        auxHorario,
                                        args.confirmacaoSalaReservar
                                    )
                                    findNavController().navigate(action)
                                } .addOnFailureListener {
                                    Toast.makeText(requireContext(), "Falha", Toast.LENGTH_LONG).show()
                                }
                        }
                } else if(args.horario == "20:20 às 21:50 - Disponível"){
                    var reservaDetail: MutableMap<String, Any> = HashMap()
                    reservaDetail.put("horario2", "20:20 às 21:50 - Indisponível")

                    db.collection("reservar")
                        .whereEqualTo("data", args.data)
                        .whereEqualTo("sala", args.confirmacaoSalaReservar.name)
                        .get()
                        .addOnCompleteListener {
                            var documentSnapShot = it.getResult().getDocumentChanges().get(0)
                            var documentID = documentSnapShot.document.id
                            db.collection("reservar")
                                .document(documentID)
                                .update(reservaDetail)
                                .addOnSuccessListener {
                                    var auxHorario = "20:20 às 21:50 - Reservado"
                                    realizarMinhaReserva(auxHorario)
                                    val action = AgendamentReviewFragmentDirections.actionAgendamentReviewFragmentToAgendamentoAwaitFragment(
                                        auxHorario,
                                        args.confirmacaoSalaReservar
                                    )
                                    findNavController().navigate(action)
                                } .addOnFailureListener {
                                    Toast.makeText(requireContext(), "Falha", Toast.LENGTH_LONG).show()
                                }
                        }
                }
            }
        })

        binding.backReview.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun realizarMinhaReserva(horario: String){

        var emailBase64 = Base64Custom().codificarBase64(firebaseAuth.currentUser.email.toString())

        val salvar = hashMapOf(
            "idEmail" to emailBase64,
            "sala" to args.confirmacaoSalaReservar.name,
            "horario" to horario,
            "assentos" to args.confirmacaoSalaReservar.assentos.toString(),
            "computadores" to args.confirmacaoSalaReservar.computadores.toString(),
            "projetores" to args.confirmacaoSalaReservar.projetores.toString(),
            "quadros" to args.confirmacaoSalaReservar.quadros.toString()
        )

        db.collection("reservasSalvas")
            .add(salvar)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Horário salvo com sucesso\n você pode acha-lo em minha agenda", Toast.LENGTH_LONG).show()
            } .addOnFailureListener {
                Toast.makeText(requireContext(), "Falha em salvar horário, procure o nosso suporte", Toast.LENGTH_LONG).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}