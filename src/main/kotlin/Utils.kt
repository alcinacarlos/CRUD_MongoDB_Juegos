package org.carlosalcina

import org.carlosalcina.model.Juego
import java.text.SimpleDateFormat
import java.util.*

class Utils() {
    companion object{
        fun pedirDouble(enunciado:String):Double{
            var numero:Double? = null
            while (numero == null){
                println(enunciado)
                numero = readln().toDoubleOrNull()
            }
            return numero
        }
        fun pedirFecha(enunciado: String): Date {
            val dateFormat = SimpleDateFormat("dd-MM-yyyy")
            var fecha: Date? = null
            while (fecha == null) {
                println(enunciado)
                val input = readln()
                try {
                    fecha = dateFormat.parse(input)
                } catch (e: Exception) {
                    println("Formato inválido, introduce la fecha en formato yyyy-MM-dd")
                }
            }
            return fecha
        }
        fun formatearJuego(juego: Juego):Juego{
            return Juego(
                juego.titulo.lowercase(),
                juego.genero.lowercase(),
                juego.precio,
                juego.fecha
            )
        }
        fun formatearFecha(fecha: Date):String{
            val formatter = SimpleDateFormat("dd/MM/yyyy")

            return formatter.format(fecha)
        }
    }
}