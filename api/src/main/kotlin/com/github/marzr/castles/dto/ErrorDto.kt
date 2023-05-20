package com.github.marzr.castles.dto

data class ErrorDto(
    val code: ErrorCode,
    val message: String
)

enum class ErrorCode {
    GAME_NOT_FOUND,
    GAME_ID_REQUIRED
}
