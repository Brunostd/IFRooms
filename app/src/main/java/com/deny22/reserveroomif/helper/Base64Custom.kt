package com.deny22.reserveroomif.helper

import android.util.Base64

class Base64Custom {
    fun codificarBase64(texto: String): String? {
        return Base64.encodeToString(texto.toByteArray(), Base64.NO_WRAP)
            .replace("(\\n|\\r)".toRegex(), "")
    }

    fun decodificarBase64(textoCodificado: String?): String? {
        return String(Base64.decode(textoCodificado, Base64.DEFAULT))
    }
}