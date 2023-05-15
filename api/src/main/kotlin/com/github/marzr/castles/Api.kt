package com.github.marzr.castles

import com.github.marzr.castles.dto.GameSettingsDto
import com.github.marzr.castles.dto.PositionedTileDto
import com.github.marzr.castles.dto.toDto
import com.github.marzr.castles.game.GameService
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

@OptIn(ExperimentalStdlibApi::class)
fun startServer(gameService: GameService) {
    embeddedServer(Netty, port = 8000) {
        install(ContentNegotiation) {
            json()
        }
        install(Authentication) {
            basic("auth-basic") {
                realm = "Access to the '/' path"
                validate { cred ->
                    when {
                        cred.name == "admin" && cred.password == "admin" -> UserIdPrincipal(cred.name)
                        cred.name == "p1" && cred.password == "p1" -> UserIdPrincipal(cred.name)
                        cred.name == "p2" && cred.password == "p2" -> UserIdPrincipal(cred.name)
                        cred.name == "p3" && cred.password == "p3" -> UserIdPrincipal(cred.name)
                        cred.name == "p4" && cred.password == "p4" -> UserIdPrincipal(cred.name)
                        else -> null
                    }
                }
            }
        }
        routing {
            authenticate("auth-basic") {
                get("/") {
                    call.respondText("Hello, world!")
                }
                post("/game") {
                    val gameSettings = call.receive<GameSettingsDto>()
                    val game = gameService.createGame(gameSettings.playersCount).toDto()
                    call.respond(game)
                }
                get("/game/{id}") {
                    val gameId = call.parameters["id"]?.toLongOrNull() ?: TODO("400")
                    gameService.getGame(gameId)?.toDto() ?: TODO("404")
                }
                post("/game/{id}/buildRoom") {
                    val gameId = call.parameters["id"]
                    val tile = call.receive<PositionedTileDto>()
                    call.respondText("Hello, Tile $tile")
                }
            }
        }
    }.start(wait = true)
}
