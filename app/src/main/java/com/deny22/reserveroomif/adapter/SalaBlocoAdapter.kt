package com.deny22.ifreserverdroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.databinding.SalasBlocoBinding
import com.deny22.reserveroomif.fragments.BlocoSelecionadoFragmentDirections
import com.deny22.reserveroomif.model.SalasBlocoModel

class SalaBlocoAdapter(var listaSalasBloco: MutableList<SalasBlocoModel>):
    RecyclerView.Adapter<SalaBlocoAdapter.MyViewHolder>() {
    class MyViewHolder(private val itemBinding: SalasBlocoBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(salasBlocoModel: SalasBlocoModel){
            itemBinding.run {
                //itemBinding.imageSalaBloco.setImageResource(salasBlocoModel.imageSalaBloco)
                itemBinding.textViewSalaBloco.text = salasBlocoModel.name

                itemBinding.buttonSalasBlocos.setOnClickListener {
                    val action = BlocoSelecionadoFragmentDirections.actionBlocoSelecionadoFragmentToSalaSelecionadaFragment(salasBlocoModel)
                    itemBinding.root.findNavController().navigate(action)
                }
            }
        }
        companion object{
            fun create(parent: ViewGroup): MyViewHolder{
                val itemBinding = SalasBlocoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MyViewHolder(itemBinding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listaSalasBloco[position])
    }

    override fun getItemCount(): Int = listaSalasBloco.size

}