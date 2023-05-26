package com.github.marzr.castles

import com.github.marzr.castles.data.roomsByTitle
import com.github.marzr.castles.geometry.*
import com.github.marzr.castles.geometry.Position.Rotation.*
import javafx.application.Application
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.event.EventHandler
import javafx.scene.image.Image
import javafx.scene.layout.Region
import javafx.stage.Screen
import tornadofx.*

class CastlesApp : App() {
    override val primaryView = MyView::class
}

fun main(args: Array<String>) {
    Application.launch(CastlesApp::class.java, *args)
}

class MyView : View() {
    var centerX: Double = 0.0
    var centerY: Double = 0.0

    private val mainController = MainController()

    override val root = region {
        val screen = Screen.getPrimary()
        val bounds = screen.visualBounds

        primaryStage.x = bounds.minX
        primaryStage.y = bounds.minY
        primaryStage.width = bounds.width
        primaryStage.height = bounds.height

        centerX = primaryStage.width / 2
        centerY = primaryStage.height / 2

        imageRoom(PositionedTile(roomsByTitle["Верхний зал"]!!, Position(2, 0, R0)))
        imageRoom(PositionedTile(roomsByTitle["Спальня королевы"]!!, Position(0, 0, R0)))
        imageRoom(PositionedTile(roomsByTitle["Буфетная"]!!, Position(-3, 4, R270)))
        imageRoom(PositionedTile(roomsByTitle["Комната отдыха"]!!, Position(-2, 7, R90)))
        imageRoom(PositionedTile(roomsByTitle["Хижина Хундинга"]!!, Position(4, -2, R270)))

        onScroll = EventHandler { event ->
            if (event.deltaY > 0) {
                if (mainController.scale < 500.0) mainController.scale *= 1.1
            } else
                if (mainController.scale > 10) mainController.scale *= 0.9

            mainController.filesMap.keys.forEach {
                val tile = mainController.tilesMap[it]!!
                mainController.roomPropertyMap[it]!!.value = tile.toFigure().height()
                mainController.roomPropertyMap["${it}_width"]!!.value = tile.toFigure().width()
                mainController.roomPropertyMap["${it}_x"]!!.value =
                    centerX + tile.position.x * mainController.scale + mainController.xShift
                mainController.roomPropertyMap["${it}_y"]!!.value =
                    centerY - tile.position.y * mainController.scale + mainController.yShift
            }
        }

        onMouseReleased = EventHandler {
            mainController.xDrag = 0.0
            mainController.yDrag = 0.0
        }

        onMouseDragged = EventHandler {
            if (mainController.xDrag == 0.0) {
                mainController.xDrag = it.x
                mainController.yDrag = it.y
                return@EventHandler
            }

            mainController.xShift = mainController.xShift + it.x - mainController.xDrag
            mainController.yShift = mainController.yShift + it.y - mainController.yDrag

            mainController.xDrag = it.x
            mainController.yDrag = it.y


            mainController.filesMap.keys.forEach {
                val tile = mainController.tilesMap[it]!!
                mainController.roomPropertyMap[it]!!.value = tile.toFigure().height()
                mainController.roomPropertyMap["${it}_width"]!!.value = tile.toFigure().width()
                mainController.roomPropertyMap["${it}_x"]!!.value =
                    centerX + tile.position.x * mainController.scale + mainController.xShift
                mainController.roomPropertyMap["${it}_y"]!!.value =
                    centerY - tile.position.y * mainController.scale + mainController.yShift
            }
        }
    }

    private fun Region.imageRoom(tile: PositionedTile) = imageview {
        mainController.tilesMap[tile.tile.title] = tile
        val simpleObjectProperty = mainController.imageProperty(tile.tile.title)
        imageProperty().bind(simpleObjectProperty)

        val roomHeightProperty = RoomProperty(tile.toFigure().height())
        val roomWidthProperty = RoomProperty(tile.toFigure().width())
        val roomXProperty = RoomProperty(centerX + tile.position.x * mainController.scale)
        val roomYProperty = RoomProperty(centerY - tile.position.y * mainController.scale)

        fitHeightProperty().bind(roomHeightProperty.property)
        fitWidthProperty().bind(roomWidthProperty.property)
        layoutYProperty().bind(roomYProperty.property)
        layoutXProperty().bind(roomXProperty.property)

        mainController.roomPropertyMap[tile.tile.title] = roomHeightProperty
        mainController.roomPropertyMap[tile.tile.title + "_width"] = roomWidthProperty
        mainController.roomPropertyMap[tile.tile.title + "_x"] = roomXProperty
        mainController.roomPropertyMap[tile.tile.title + "_y"] = roomYProperty
        rotate = tile.position.rotation.toNumber()
    }

    fun Figure.width(): Double = when (this) {
        is Rectangle -> width * mainController.scale
        is Circle -> diameter * mainController.scale
        is LFigure -> 4 * mainController.scale
        is Octagon -> 7 * mainController.scale
    }

    fun Figure.height(): Double = when (this) {
        is Rectangle -> height * mainController.scale
        is Circle -> diameter * mainController.scale
        is LFigure -> 4 * mainController.scale
        is Octagon -> 4 * mainController.scale
    }
}

private fun Position.Rotation.toNumber(): Double = when (this) {
    R0 -> 0.0
    R90 -> 90.0
    R180 -> 180.0
    R270 -> 270.0
}

class MainController : Controller() {
    val filesMap = mapOf(
        "Верхний зал" to "Corridor/great_hall.png",
        "Спальня королевы" to "Sleeping/L_queen.png",
        "Буфетная" to "Food/L_scullery.png",
        "Комната отдыха" to "Sleeping/SR_nap_room.png",
        "Хижина Хундинга" to "Outdoor/BC_hunding.png"
    )
    val roomPropertyMap: MutableMap<String, RoomProperty> = mutableMapOf()
    val tilesMap = mutableMapOf<String, PositionedTile>()
    private fun imageUri(name: String) = this.javaClass.getResource("/image/${filesMap[name]}")?.toURI().toString()
    fun imageProperty(name: String) = SimpleObjectProperty(Image(imageUri(name)))

    val scaleProperty = SimpleDoubleProperty(50.0)
    var scale by scaleProperty

    val xShiftProperty = SimpleDoubleProperty(0.0)
    var xShift by xShiftProperty

    val yShiftProperty = SimpleDoubleProperty(0.0)
    var yShift by yShiftProperty

    val xDragProperty = SimpleDoubleProperty(0.0)
    var xDrag by xDragProperty

    val yDragProperty = SimpleDoubleProperty(0.0)
    var yDrag by yDragProperty
}

class RoomProperty(initial: Double) {
    val property = SimpleDoubleProperty(initial)
    var value by property
}
