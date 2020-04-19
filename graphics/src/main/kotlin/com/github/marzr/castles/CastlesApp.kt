package com.github.marzr.castles

import javafx.application.Application
import javafx.scene.layout.VBox
import javafx.scene.control.Button
import javafx.scene.paint.Color
import tornadofx.*

class CastlesApp : App() {
    override val primaryView = MyView::class
}

fun main(args: Array<String>) {
    Application.launch(CastlesApp::class.java, *args)
}

class MyView : View() {

    override val root = VBox()

    init {
        root.apply {
            this += Button("Press Me").apply {
                textFill = Color.RED
                action { println("Button pressed!") }
            }
        }
    }
}
