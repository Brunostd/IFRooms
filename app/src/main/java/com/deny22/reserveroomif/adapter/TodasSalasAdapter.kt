package com.deny22.ifreserverdroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deny22.reserveroomif.databinding.TodasAsSalasBinding
import com.deny22.reserveroomif.model.SalasModel

class TodasSalasAdapter(var listaSalas: MutableList<SalasModel>):
    RecyclerView.Adapter<TodasSalasAdapter.MyViewHolder>() {
     class MyViewHolder(private val itemBinding: TodasAsSalasBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(salasModel: SalasModel){
            itemBinding.run {
                itemBinding.imageTodasSalas.setImageResource(salasModel.image)
                itemBinding.textNameTodasSalas.text = salasModel.nome
                itemBinding.textAssentosTodasSalas.text = salasModel.assentos.toString()
            }
        }
         companion object {
             fun create(parent: ViewGroup): MyViewHolder{
                 val itemBinding = TodasAsSalasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                 return MyViewHolder(itemBinding)
             }
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listaSalas[position])
    }

    override fun getItemCount(): Int = listaSalas.size
}