package com.github.marzr.castles

import com.github.marzr.castles.dto.PositionedTileDto
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8000) {
        install(ContentNegotiation) {
            json()
        }
        routing {
            get ("/") {
                call.respondText("Hello, world!")
            }
            post("/game/buildRoom") {
                val tile = call.receive<PositionedTileDto>()
                call.respondText("Hello, Tile $tile")
            }
        }
    }.start(wait = true)
}

