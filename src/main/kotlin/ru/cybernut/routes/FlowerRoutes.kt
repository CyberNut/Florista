package ru.cybernut.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.cybernut.models.Flower
import ru.cybernut.models.flowerStorage
import java.util.concurrent.Flow
import kotlin.text.get

fun Route.flowerRouting() {
    route("/flower") {
        get {
            if (flowerStorage.isNotEmpty()) {
                call.respond(flowerStorage)
            } else {
                call.respondText("No flowers found", status = HttpStatusCode.OK)
            }
        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val flower =
                flowerStorage.find { it.id == id.toInt() } ?: return@get call.respondText(
                    "No flower with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(flower)
        }
        post {
            val flower = call.receive<Flower>()
            flowerStorage.add(flower)
            call.respondText("Flower stored correctly", status = HttpStatusCode.Created)
        }
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (flowerStorage.removeIf { it.id == id.toInt() }) {
                call.respondText("Flower remove correclty", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Flower remove correclty", status = HttpStatusCode.NotFound)
            }
        }
}