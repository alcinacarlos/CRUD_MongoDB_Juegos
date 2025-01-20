package org.carlosalcina

import org.carlosalcina.gestor.GestorJuego
import org.carlosalcina.repository.JuegoRepository

fun main(){
    val juegoRepository = JuegoRepository()
    val gestorJuego = GestorJuego(juegoRepository)

    gestorJuego.menu()
}
