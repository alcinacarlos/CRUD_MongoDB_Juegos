#CRUD MongoDB
## Objetivo
El objetivo del ejercicio es que se desarrolle una pequeña aplicación
que interactúe con un usuario por consola para la gestión de juegos.
Además de la implementación, se deben responder a una serie de
preguntas teóricas para asegurar el cumplimiento de todos los
criterios de evaluación.
## Especificaciones
   Crea una base de datos en MongoDB con tu nombre y crea una colección
   de juegos, almacenando la siguiente información sobre cada uno:
   título, género, precio y fecha de lanzamiento
   Crea una aplicación que permita conectar con la base de datos que has
   creado de forma que se pueda listar, en una tabla, la información de
   los juegos introducidos.
   Añade a la aplicación la posibilidad de realizar una búsqueda de todos
   los juegos del mismo género y ordénalos por título.
   Añade a la aplicación la posibilidad de registrar nuevos juegos,
   considerando el título como campo obligatorio.
   Añade a la funcionalidad de registrar nuevos juegos un control para
   que no sea posible registrar dos juegos con el mismo nombre. En ese
   caso se mostrará un mensaje de error al usuario y tendrá que
   proporcionar otro nombre diferente.
   Añade una nueva funcionalidad que permita eliminar todos los juegos de
   un mismo género.
   Añade una funcionalidad que permita modificar los datos de un juego
   
## Teoría. Responde, usando tus palabras, a las siguientes preguntas.
### a) ¿Qué ventajas e inconvenientes encuentras al usar una base de datos documental con MongoDB?
**Ventajas de MongoDB:**
- Puede manejar grandes cantidades de datos.
- No requiere una estructura fija para los datos.
- Permite replicar y distribuir los datos.

**Inconvenientes de MongoDB:**
- No soporta transacciones como en bases SQL
- Consume más recursos que otras bases de datos
- Las consultas pueden suelen ir más lentas
### b) ¿Cuáles han sido los principales problemas que has tenido al desarrollar la aplicación?
El driver de MongoDB no me funcionaba correctamente y no me serializaba los datos a la hora de usar la **Clase Juego**, y lo que hize fue buscar una versión más moderna del driver para que funcionase

### c) ¿Cómo solucionarías el problema de la inconsistencia de datosen una base de datos documental? (El hecho de que en los documentos de una colección no sea obligatorio seguir un esquema único)
1. Intentar estructurar los documentos de manera uniforme
2. Implementar índices para mejorar la consistencia y eficiencia en las consultas de documentos
3. Validar y transformar los datos antes de insertarlos en la base de datos para garantizar que cumplan con un formato esperado
