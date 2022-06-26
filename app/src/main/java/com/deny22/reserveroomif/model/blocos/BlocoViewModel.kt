package com.deny22.reserveroomif.model.blocos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BlocoViewModel: ViewModel() {

    private var blocosRepository: BlocosRepository = BlocosRepository()
    private var lista = blocosRepository.getBlocos()
    private val _listaBlocos = MutableLiveData<MutableList<BlocoModel>>().apply {
        value = lista
    }

    var listaBlocos: LiveData<MutableList<BlocoModel>> = _listaBlocos

}