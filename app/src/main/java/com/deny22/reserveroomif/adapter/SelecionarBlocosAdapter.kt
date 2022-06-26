package com.deny22.reserveroomif.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.deny22.reserveroomif.R
import com.deny22.reserveroomif.databinding.SelecionarBlocoBinding
import com.deny22.reserveroomif.fragments.BlocosFragmentDirections
import com.deny22.reserveroomif.model.blocos.BlocoModel

class SelecionarBlocosAdapter(var listaBlocos: MutableList<BlocoModel>): RecyclerView.Adapter<SelecionarBlocosAdapter.MyViewHolder>() {
    class MyViewHolder(private val itemBinding: SelecionarBlocoBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(blocoModel: BlocoModel){
            itemBinding.run {
                itemBinding.textViewSelecionarBloco.text = blocoModel.name
                itemBinding.textViewIdBloco.text = blocoModel.id
                itemBinding.textViewTotalSalasBloco.text = blocoModel.salas

                itemBinding.cardViewBloco.setOnClickListener {
                    val action = BlocosFragmentDirections.actionBlocosFragmentToBlocoSelecionadoFragment(blocoModel)
                    itemBinding.root.findNavController().navigate(action)
                }
            }
        }
        companion object{
            fun create(parent: ViewGroup): MyViewHolder{
                val itemBinding = SelecionarBlocoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MyViewHolder(itemBinding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listaBlocos[position])
    }

    override fun getItemCount(): Int = listaBlocos.size
}