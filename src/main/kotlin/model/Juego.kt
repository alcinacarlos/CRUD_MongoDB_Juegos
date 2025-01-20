package org.carlosalcina.model

import java.util.Date

data class Juego(
    var titulo: String,
    var genero: String,
    var precio: Double,
    var fecha: Date
)