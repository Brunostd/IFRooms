package com.deny22.reserveroomif.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.deny22.ifreserverdroom.adapter.TodasSalasAdapter
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.databinding.FragmentConteudoBinding
import com.deny22.reserveroomif.model.SalasModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ConteudoFragment : Fragment() {
    private var _binding: FragmentConteudoBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var mUser: FirebaseAuth = FirebaseAuth.getInstance()
    private var db = Firebase.firestore
    var listaSalas: MutableList<SalasModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConteudoBinding.inflate(inflater, container, false)
        val view = binding.root

        setView()
        setListernes()
        setRecyclerView()

        return view
    }

    fun setView(){
        BottomSheetBehavior.from(binding.sheet).apply {
            peekHeight=150
            this.state= BottomSheetBehavior.STATE_COLLAPSED
        }

        binding.textHomeNamePerfil.text = mUser.currentUser?.email
        mUser.currentUser?.photoUrl.let {
            if (it != null){
                Glide.with(requireContext()).load(it).into(binding.imageHomePerfil)
            }
        }
    }

    fun setRecyclerView(){
        //addListaSalas()
        binding.recyclerTodasAsSalas.adapter = TodasSalasAdapter(listaSalas)
    }

    fun setListernes(){
        binding.imageViewEsconderPerfil.setOnClickListener(View.OnClickListener {
            if (binding.frameLayoutPerfil.visibility == View.VISIBLE){
                binding.frameLayoutPerfil.visibility = View.GONE
                binding.imageViewEsconderPerfil.setImageResource(R.drawable.ic_baseline_expand_more_24)
            } else if (binding.frameLayoutPerfil.visibility == View.GONE){
                binding.frameLayoutPerfil.visibility = View.VISIBLE
                binding.imageViewEsconderPerfil.setImageResource(R.drawable.ic_baseline_expand_less_24)
            }
        })

        binding.chatBot.setOnClickListener {
            findNavController().navigate(R.id.action_conteudoFragment_to_chatBotActivity)
        }

        binding.cardViewBlocos.setOnClickListener {
            findNavController().navigate(R.id.action_conteudoFragment_to_blocosFragment)
        }

        binding.cardViewTodasAsSalas.setOnClickListener {
            findNavController().navigate(R.id.action_conteudoFragment_to_todasAsSalasFragment)
        }

        binding.cardMinhaAgenda.setOnClickListener {
            findNavController().navigate(R.id.action_conteudoFragment_to_minhaAgendaFragment)
        }

        binding.cardRegrasAgendamento.setOnClickListener {
            dialogConstruction()
        }

        binding.textConfig.setOnClickListener {
            dialogConstruction()
        }

        binding.textExit.setOnClickListener {
            mUser.signOut()
            activity?.finish()
        }
    }

    fun dialogConstruction(){
        var dialogBuilder = MaterialAlertDialogBuilder(requireContext())
        var inflater = this.layoutInflater
        var dialogView = inflater.inflate(R.layout.em_construcao, null)
        dialogBuilder.setView(dialogView)

        dialogBuilder
            .setTitle("Em constru????o")
            .setNeutralButton("Fechar") { dialog, which ->
                // Respond to neutral button press
            }
            .setPositiveButton("OK") { dialog, which ->
                // Respond to positive button press
            }
            .show()
    }


    fun addListaSalas(){
        var p = SalasModel("sala 1", R.drawable.computer_lab, 30)
        listaSalas.add(p)

        p = SalasModel("sala 2", R.drawable.computer_lab, 30)
        listaSalas.add(p)

        p = SalasModel("sala 3", R.drawable.computer_lab, 30)
        listaSalas.add(p)

        p = SalasModel("sala 4", R.drawable.computer_lab, 30)
        listaSalas.add(p)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}