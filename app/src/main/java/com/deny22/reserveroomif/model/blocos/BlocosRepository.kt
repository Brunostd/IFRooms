package com.deny22.reserveroomif.model.blocos

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class BlocosRepository {

    private var blocos: MutableLiveData<ArrayList<BlocoModel>>? = null
    var db = Firebase.firestore

    var aux = MutableLiveData<MutableList<BlocoModel>>().apply {
        value = getBlocos()
    }

    fun getBlocos(): MutableList<BlocoModel>{
        var listaBlocos: MutableList<BlocoModel> = arrayListOf()

        db.collection("blocos")
            .get()
            .addOnSuccessListener { result ->
                for (documents in result){
                    var note = documents.toObject(BlocoModel::class.java)

                    var p: BlocoModel = BlocoModel(
                        name = note.name
                    )
                    listaBlocos.add(p)
                }
                //Toast.makeText(requireContext(), listaBlocos.elementAt(0).name, Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                //Toast.makeText(requireContext(), "Falha", Toast.LENGTH_LONG).show()
            }
        return listaBlocos
    }
}