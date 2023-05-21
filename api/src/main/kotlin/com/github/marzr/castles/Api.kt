package com.github.marzr.castles

import com.github.marzr.castles.dto.*
import com.github.marzr.castles.dto.ErrorCode.GAME_ID_REQUIRED
import com.github.marzr.castles.dto.ErrorCode.GAME_NOT_FOUND
import com.github.marzr.castles.service.GameService
import com.github.marzr.castles.service.PreGameService
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

@OptIn(ExperimentalStdlibApi::class)
fun startServer(
    gameService: GameService,
    preGameService: PreGameService
) {
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
                post("/preGame") {
                    val currentUser = call.principal<UserIdPrincipal>()!!.name
                    val preGame = preGameService.create(currentUser).toDto()
                    call.respond(preGame)
                }
                post("/preGame/{id}/join") {
                    val preGameId = call.parameters["id"]?.toLongOrNull() ?: TODO("400")
                    val currentUser = call.principal<UserIdPrincipal>()!!.name
                    val preGame = preGameService.join(currentUser, preGameId).toDto()
                    call.respond(preGame)
                }
                post("/game") {
                    val gameSettings = call.receive<GameSettingsDto>()
                    val game = gameService.createGame(gameSettings.playersCount).toDto()
                    call.respond(game)
                }
                get("/game/{id}") {
                    val gameId = call.parameters["id"]?.toLongOrNull() ?: return@get gameIdRequired()
                    val gameDto = gameService.getGame(gameId)?.toDto() ?: return@get gameNotFound()
                    call.respond(gameDto)
                }
                get("/game/{id}/me") {
                    val gameId = call.parameters["id"]?.toLongOrNull() ?: return@get gameIdRequired()
                    val game = gameService.getGame(gameId) ?: return@get gameNotFound()
                    val currentUser = call.principal<UserIdPrincipal>()
                    val player = game.players.get(currentUser!!.name).toDto()
                    call.respond(player)
                }
                post("/game/{id}/buildRoom") {
                    val gameId = call.parameters["id"]
                    val tile = call.receive<PositionedTileDto>()
                    call.respondText("Hello, Tile $tile")
                }
                get("/game/{id}/getMoney") {
                    val gameId = call.parameters["id"]?.toLongOrNull() ?: return@get gameIdRequired()
                    val game = gameService.getGame(gameId) ?: return@get gameNotFound()
                    val currentUser = call.principal<UserIdPrincipal>()
                    val player = game.players.get(currentUser!!.name)
                    val playerMoney = PlayerMoneyDto(gameService.passTurnAndReceiveMoney(player).money)
                    call.respond(playerMoney)
                }
            }
        }
    }.start(wait = true)
}

private suspend fun PipelineContext<Unit, ApplicationCall>.gameNotFound() {
    call.respond(NotFound, ErrorDto(GAME_NOT_FOUND, "Game not found"))
}

private suspend fun PipelineContext<Unit, ApplicationCall>.gameIdRequired() {
    call.respond(BadRequest, ErrorDto(GAME_ID_REQUIRED, "Integer game id required"))
}
