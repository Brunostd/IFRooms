package com.deny22.reserveroomif.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.adapter.SelecionarBlocosAdapter
import com.deny22.reserveroomif.databinding.FragmentBlocosBinding
import com.deny22.reserveroomif.model.blocos.BlocoModel
import com.deny22.reserveroomif.model.blocos.BlocoViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BlocosFragment : Fragment() {
    private var _binding: FragmentBlocosBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: BlocoViewModel
    private var db = Firebase.firestore
    private var lista: MutableList<BlocoModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlocosBinding.inflate(inflater, container, false)
        val view = binding.root

        getBlocos()

        binding.textViewBlocosRetornar.setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }

    fun getBlocos(): MutableList<BlocoModel>{
        var listaBlocos: MutableList<BlocoModel> = arrayListOf()

        db.collection("blocos")
            .get()
            .addOnSuccessListener { result ->
                for (documents in result){
                    var note = documents.toObject(BlocoModel::class.java)

                    var p: BlocoModel = BlocoModel(
                        id = note.id,
                        name = note.name,
                        salas = note.salas
                    )
                    listaBlocos.add(p)
                }
                binding.recyclerSelecionarBlocos.adapter = SelecionarBlocosAdapter(listaBlocos)
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Falha", Toast.LENGTH_LONG).show()
            }
        return listaBlocos
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}