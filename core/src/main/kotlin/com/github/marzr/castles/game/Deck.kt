package com.github.marzr.castles.game

class Deck<T : Any>(list: List<T>) {
    private val deck = list.shuffled().toMutableList()

    @ExperimentalStdlibApi
    fun issue(count: Int): List<T> = (1..count).mapNotNull { deck.removeLastOrNull() }

    @ExperimentalStdlibApi
    fun issueOne(): T = deck.removeLast()

    fun issueOne(filter: (T) -> Boolean): T? = deck.find(filter)?.also {
        deck.remove(it)
    }
}
