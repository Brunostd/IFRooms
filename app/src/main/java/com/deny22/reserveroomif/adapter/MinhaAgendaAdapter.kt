package com.deny22.reserveroomif.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.deny22.reserveroomif.databinding.MinhaAgendaBinding
import com.deny22.reserveroomif.fragments.MinhaAgendaFragmentDirections
import com.deny22.reserveroomif.model.minhaagenda.MinhaAgendaModel

class MinhaAgendaAdapter(var listaMinhaAgenda: MutableList<MinhaAgendaModel>): RecyclerView.Adapter<MinhaAgendaAdapter.MyViewHolder>() {
    class MyViewHolder(private val itemBinding: MinhaAgendaBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(minhaAgendaModel: MinhaAgendaModel){
            itemBinding.run {
                this.textMinhaAgendaSala.text = minhaAgendaModel.sala
                this.textMinhaAgendaData.text = minhaAgendaModel.data
                this.textMinhaAgendaHorario.text = minhaAgendaModel.horario

                this.frameMinhaAgenda.setOnClickListener {
                    val action = MinhaAgendaFragmentDirections.actionMinhaAgendaFragmentToComprovantMinhaAgendaFragment(minhaAgendaModel)
                    itemBinding.root.findNavController().navigate(action)
                }
            }
        }
        companion object{
            fun create(parent: ViewGroup): MyViewHolder{
                val itemBinding = MinhaAgendaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MyViewHolder(itemBinding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listaMinhaAgenda[position])
    }

    override fun getItemCount(): Int = listaMinhaAgenda.size
}