package com.deny22.reserveroomif.model

import java.io.Serializable

class SalasBlocoModel(
    var name: String = "",
    var assentos: Int = 0,
    var computadores: Int = 0,
    var projetores: Int = 0,
    var bloco: String = "",
    var quadros: Int = 0
): Serializable {
}