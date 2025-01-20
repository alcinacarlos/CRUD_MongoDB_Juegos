package org.carlosalcina.gestor

import org.carlosalcina.Utils
import org.carlosalcina.model.Juego
import org.carlosalcina.repository.JuegoRepository


class GestorJuego(
    private val juegosRepo: JuegoRepository
) {

    fun menu() {
        var menuActivo = true
        while (menuActivo) {
            showMenu()

            when (readln().toIntOrNull()) {
                1 -> listarJuegos()
                2 -> buscarPorGenero()
                3 -> registrarJuego()
                4 -> eliminarPorGenero()
                5 -> modificarJuego()
                6 -> {
                    println("Saliendo...")
                    menuActivo = false
                }

                else -> println("Opción no válida")
            }
        }
    }

    private fun listarJuegos() {
        println("\n===== LISTA DE JUEGOS =====")
        val juegos = juegosRepo.getAll()
        if (juegos.isEmpty()) {
            println("No hay juegos registrados")
        } else {
            val header = String.format(
                "%-20s %-15s %-10s %-15s",
                "Título", "Género", "Precio", "Fecha de Lanzamiento"
            )
            println(header)
            // separador
            println("-".repeat(header.length))

            juegos.forEach {
                mostrarJuego(it)
            }
        }
    }

    private fun buscarPorGenero() {
        println("\nIntroduce el género: ")
        val genero = readln()
        val juegos = juegosRepo.getAllByGenre(genero).sortedBy { it.titulo }

        if (juegos.isEmpty()) {
            println("No se encontraron juegos de $genero")
        } else {
            println("\n===== JUEGOS DE $genero =====")
            val header = String.format(
                "%-20s %-15s %-10s %-15s",
                "Título", "Género", "Precio", "Fecha de Lanzamiento"
            )
            println(header)
            // separador
            println("-".repeat(header.length))
            juegos.forEach {
                mostrarJuego(it)
            }
        }
    }

    private fun registrarJuego() {
        try {
            println("\nIntroduce el título del juego: ")
            val titulo = readln()

            if (juegosRepo.get(titulo) != null) {
                println("Título ya existente")
                return
            }

            println("Introduce el género: ")
            val genero = readln()

            val precio = Utils.pedirDouble("Introduce el precio")

            val fechaLanzamiento = Utils.pedirFecha("Introduce la fecha de lanzamiento (dd-MM-yyyy): ")

            val nuevoJuego = Juego(titulo, genero, precio, fechaLanzamiento)

            if (juegosRepo.insert(nuevoJuego)) {
                println("Juego registrado")
            } else {
                println("Error al registrar juego")
            }

        } catch (e: Exception) {
            println("Error al registrar: ${e.message}")
        }
    }

    private fun eliminarPorGenero() {
        println("\nIntroduce el género de los juegos a eliminar: ")
        val genero = readln()

        val juegos = juegosRepo.getAllByGenre(genero)

        if (juegos.isEmpty()) {
            println("No se encontraron juegos de $genero")
        } else {
            juegosRepo.deleteByGenre(genero)
            println("Se han eliminado todos los juegos de $genero")
        }
    }

    private fun modificarJuego() {

        println("\nIntroduce el título del juego a modificar: ")
        val titulo = readln()

        val juegoExistente = juegosRepo.get(titulo)
        if (juegoExistente == null) {
            println("No se encontró ningún juego llamado $titulo")
            return
        }
        println("Título\t\tGénero\t\tPrecio\t\tFecha de Lanzamiento")

        mostrarJuego(juegoExistente)

        val nuevoJuego = juegoExistente.copy()

        var menuActivo = true
        while (menuActivo) {
            showModifyMeny()

            when (readln().toIntOrNull()) {
                0 -> menuActivo = false
                1 -> {
                    println("Introduce el nuevo título: ")
                    val nuevoTitulo = readln()
                    nuevoJuego.titulo = nuevoTitulo
                }

                2 -> {
                    println("Introduce el nuevo género: ")
                    val nuevoGenero = readln()
                    nuevoJuego.genero = nuevoGenero
                }

                3 -> {
                    val nuevoPrecio = Utils.pedirDouble("Introduce el nuevo precio")
                    nuevoJuego.precio = nuevoPrecio
                }

                4 -> {
                    val nuevaFecha = Utils.pedirFecha("Introduce la nueva fecha (dd-MM-yyyy): ")
                    nuevoJuego.fecha = nuevaFecha
                }

                5 -> {
                    if (juegosRepo.update(juegoExistente, nuevoJuego)) {
                        println("Juego modificado correctamente")
                    } else {
                        println("No se ha modificado el juego")
                    }
                    menuActivo = false
                }

                else -> println("Opción no válida")
            }
        }
    }

    private fun showModifyMeny() {
        println("Qué quieres modificar?")
        println("0. No modificar nada y salir")
        println("1. Título")
        println("2. Género")
        println("3. Precio")
        println("4. Fecha Lanzamiento")
        println("5. Guardar cambios y salir")
    }

    private fun showMenu() {
        println("\n===== MENÚ DE JUEGOS =====")
        println("1. Listar todos los juegos")
        println("2. Buscar juegos por género")
        println("3. Registrar un nuevo juego")
        println("4. Eliminar juegos por género")
        println("5. Modificar un juego")
        println("6. Salir")
        println("Elige una opción: ")
    }

    private fun mostrarJuego(juego: Juego) {
        val row = String.format(
            "%-20s %-15s %-10.2f %-15s",
            juego.titulo.capitalize(), juego.genero.capitalize(), juego.precio, Utils.formatearFecha(juego.fecha)
        )
        println(row)
    }
}