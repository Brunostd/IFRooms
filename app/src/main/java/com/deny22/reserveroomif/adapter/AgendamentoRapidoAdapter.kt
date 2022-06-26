package com.deny22.reserveroomif.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.deny22.reserveroomif.databinding.AgendamentoRapidoBinding
import com.deny22.reserveroomif.fragments.TodasAsSalasFragment
import com.deny22.reserveroomif.fragments.TodasAsSalasFragmentDirections
import com.deny22.reserveroomif.model.AgendamentoRapidoModel
import com.deny22.reserveroomif.model.SalasBlocoModel

class AgendamentoRapidoAdapter(var listaAgendamentoRapidoModel: MutableList<SalasBlocoModel>):
    RecyclerView.Adapter<AgendamentoRapidoAdapter.MyViewHolder>() {
    class MyViewHolder(private val itemBinding: AgendamentoRapidoBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(salasBlocoModel: SalasBlocoModel){
            itemBinding.run {
                this.buttonAgendamentoRapido.text = salasBlocoModel.name

                this.buttonAgendamentoRapido.setOnClickListener {
                    val action = TodasAsSalasFragmentDirections.actionTodasAsSalasFragmentToSalaSelecionadaFragment(salasBlocoModel)
                    itemBinding.root.findNavController().navigate(action)
                }
            }
        }
        companion object{
            fun create(parent: ViewGroup): MyViewHolder{
                val itemBinding = AgendamentoRapidoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MyViewHolder(itemBinding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listaAgendamentoRapidoModel[position])
    }

    override fun getItemCount(): Int = listaAgendamentoRapidoModel.size
}