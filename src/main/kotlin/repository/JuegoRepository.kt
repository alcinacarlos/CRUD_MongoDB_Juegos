package org.carlosalcina.repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import org.carlosalcina.MongoDBManager
import org.carlosalcina.Utils
import org.carlosalcina.model.Juego

class JuegoRepository {

    companion object {
        const val DATABASE_NAME = "alcinacarlo"
        const val COLLECTION_NAME = "juegos"
    }


    private fun getCollection(database: MongoDatabase): MongoCollection<Juego> {
        return database.getCollection(COLLECTION_NAME, Juego::class.java)
    }


    fun insert(juego: Juego): Boolean {
        try {
            val database = MongoDBManager.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)

            // Verificar si existe un juego con el mismo título
            val existingJuego = coll.find(Filters.eq("titulo", juego.titulo.lowercase())).first()
            if (existingJuego != null) {
                throw IllegalArgumentException("Ya existe un juego con el título '${juego.titulo}'")
            }

            val juegoFormateado = Utils.formatearJuego(juego)

            coll.insertOne(juegoFormateado)

            return true
        } catch (e: Exception) {
            println("Error al insertar: ${e.message}")
            return false
        }
    }

    fun update(oldJuego: Juego, updatedJuego: Juego): Boolean {
        try {
            val database = MongoDBManager.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            val filtro = Filters.eq("titulo", oldJuego.titulo.lowercase())

            val juegoFormateado = Utils.formatearJuego(updatedJuego)
            val result = coll.replaceOne(filtro, juegoFormateado)
            return result.matchedCount > 0

        } catch (e: Exception) {
            println("Error al actualizar: ${e.message}")
            return false
        }
    }

    fun delete(titulo: String): Boolean {
        try {
            val database = MongoDBManager.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            val filtro = Filters.eq("titulo", titulo.lowercase())

            val result = coll.deleteOne(filtro)
            return result.deletedCount > 0

        } catch (e: Exception) {
            println("Error al eliminar: ${e.message}")
            return false
        }
    }

    fun get(titulo: String): Juego? {
        try {
            val database = MongoDBManager.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)

            return coll.find(Filters.eq("titulo", titulo.lowercase())).first()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return null
        }
    }

    fun getAll(): List<Juego> {
        try {
            val database = MongoDBManager.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)

            return coll.find().toList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return emptyList()
        }
    }

    fun getAllByGenre(genero: String): List<Juego> {
        try {
            val database = MongoDBManager.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            val filtro = Filters.eq("genero", genero.lowercase())

            return coll.find(filtro).toList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return emptyList()
        }
    }

    fun deleteByGenre(genero: String): Boolean {
        try {
            val database = MongoDBManager.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            val filtro = Filters.eq("genero", genero.lowercase())

            val result = coll.deleteMany(filtro)
            return result.deletedCount > 0

        } catch (e: Exception) {
            println("Error al eliminar: ${e.message}")
            return false
        }
    }
}