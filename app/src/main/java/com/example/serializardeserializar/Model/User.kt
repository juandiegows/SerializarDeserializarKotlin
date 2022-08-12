package com.example.serializardeserializar.Model


class User() {

    var ID: Int = 0
    var Nombre: String = ""
    var Edad: Int = 0
    var Genero: Genero = Genero()
    override fun toString(): String {
        return "{ID:$ID,Nombre:$Nombre,Edad:$Edad, Genero:{ID=${Genero.ID},Nombre:${Genero.Nombre}}}"
    }

}