package com.deny22.reserveroomif.model.minhaagenda

import java.io.Serializable

class MinhaAgendaModel(
    var idEmail: String = "",
    var assentos: String = "",
    var computadores: String = "",
    var horario: String = "",
    var data: String = "",
    var projetores: String = "",
    var quadros: String = "",
    var sala: String = ""
): Serializable {
}