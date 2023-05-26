package com.github.marzr.castles

import com.github.marzr.castles.data.RoomTile
import com.github.marzr.castles.data.roomsByTitle
import com.github.marzr.castles.geometry.*
import com.github.marzr.castles.geometry.Position.Rotation.*
import javafx.application.Application
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.image.Image
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.input.ScrollEvent
import javafx.scene.layout.Pane
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

    override val root = vbox {
        val screen = Screen.getPrimary()
        val bounds = screen.visualBounds

        primaryStage.x = bounds.minX
        primaryStage.y = bounds.minY
        primaryStage.width = bounds.width
        primaryStage.height = bounds.height

        centerX = primaryStage.width / 2
        centerY = primaryStage.height / 2

        hbox {
            imageMarketRoom(roomsByTitle["Ателье"]!!)
            imageMarketRoom(roomsByTitle["Большая спальня"]!!)
            imageMarketRoom(roomsByTitle["Театр"]!!)
            imageMarketRoom(roomsByTitle["Лиловый кабинет"]!!)
            imageMarketRoom(roomsByTitle["Террасы"]!!)
            imageMarketRoom(roomsByTitle["Темница"]!!)
            imageMarketRoom(roomsByTitle["Потайная комната"]!!)
        }

        region {
            pane {
                mainController.buyingTilePane = this
                onDoubleClick {
                    println("double click pane")

                    val x = Math.round((mainController.xBuy - mainController.xShift - centerX) / mainController.scale)
                    val y = Math.round((-mainController.yBuy + mainController.yShift + centerY) / mainController.scale)
                    println("$x $y")
                    mainController.castlePane.add(
                        imageRoom(PositionedTile(mainController.byingTile!!, Position(x.toInt(), y.toInt(), R0)))
                    )
                    mainController.removeBuying = true
                    mainController.marketMap[mainController.byingTile!!.title]!!.value = true

                }
                onMousePressed = EventHandler {
                    println("onMousePressed buying")

                    mainController.xInit = it.x - mainController.xBuy
                    mainController.yInit = it.y - mainController.yBuy
                }
                onMouseDragged = EventHandler {
                    println("onMouseDragged buying")
                    mainController.xBuy = it.x - mainController.xInit
                    mainController.yBuy = it.y - mainController.yInit
                }
            }

            pane {
                mainController.castlePane = this
                imageRoom(PositionedTile(roomsByTitle["Спальня королевы"]!!, Position(0, 0, R0)))
                imageRoom(PositionedTile(roomsByTitle["Буфетная"]!!, Position(-3, 4, R270)))
                imageRoom(PositionedTile(roomsByTitle["Комната отдыха"]!!, Position(-2, 7, R90)))
                imageRoom(PositionedTile(roomsByTitle["Хижина Хундинга"]!!, Position(4, -2, R270)))
                imageRoom(PositionedTile(roomsByTitle["Верхний зал"]!!, Position(2, 0, R0)))
                onMouseReleased = onMouseReleasedEventHandler()
                onMouseDragged = onMouseDraggedEventHandler()
                toBack()
            }
            onScroll = onScrollEventHandler()
        }
    }

    private fun onScrollEventHandler(): EventHandler<ScrollEvent> = EventHandler { event ->
        if (event.deltaY > 0) {
            if (mainController.scale < 500.0) mainController.scale *= 1.1
        } else
            if (mainController.scale > 10) mainController.scale *= 0.9

        val f = mainController.byingTile?.toFigure(Position(0, 0, R0))
        if (f != null) {
            mainController.widthBuy = f.width()
            mainController.heightBuy = f.height()
        }

        filesMap.keys.forEach {
            val tile = mainController.tilesMap[it]
            if (tile != null) {
                mainController.roomPropertyMap[it]!!.value = tile.toFigure().height()
                mainController.roomPropertyMap["${it}_width"]!!.value = tile.toFigure().width()
                mainController.roomPropertyMap["${it}_x"]!!.value =
                    centerX + tile.position.x * mainController.scale + mainController.xShift
                mainController.roomPropertyMap["${it}_y"]!!.value =
                    centerY - tile.position.y * mainController.scale + mainController.yShift
            }
        }
    }

    private fun onMouseDraggedEventHandler(): EventHandler<MouseEvent> = EventHandler {
        println("onMouseDraggedEventHandler castle")
        if (mainController.xDrag == 0.0) {
            mainController.xDrag = it.x
            mainController.yDrag = it.y
            return@EventHandler
        }

        mainController.xShift = mainController.xShift + it.x - mainController.xDrag
        mainController.yShift = mainController.yShift + it.y - mainController.yDrag

        mainController.xDrag = it.x
        mainController.yDrag = it.y

        filesMap.keys.forEach {
            val tile = mainController.tilesMap[it]
            if (tile != null) {
                mainController.roomPropertyMap[it]!!.value = tile.toFigure().height()
                mainController.roomPropertyMap["${it}_width"]!!.value = tile.toFigure().width()
                mainController.roomPropertyMap["${it}_x"]!!.value =
                    centerX + tile.position.x * mainController.scale + mainController.xShift
                mainController.roomPropertyMap["${it}_y"]!!.value =
                    centerY - tile.position.y * mainController.scale + mainController.yShift
            }
        }
    }

    private fun onMouseReleasedEventHandler(): EventHandler<MouseEvent> = EventHandler {
        mainController.xDrag = 0.0
        mainController.yDrag = 0.0
    }

    private fun Region.imageRoom(tile: PositionedTile) = imageview {
        mainController.tilesMap[tile.tile.title] = tile
        val simpleObjectProperty = mainController.imageProperty(tile.tile.title)
        imageProperty().bind(simpleObjectProperty)

        val roomHeightProperty = RoomProperty(tile.toFigure().height())
        val roomWidthProperty = RoomProperty(tile.toFigure().width())
        val roomXProperty = RoomProperty(centerX + tile.position.x * mainController.scale + mainController.xShift)
        val roomYProperty = RoomProperty(centerY - tile.position.y * mainController.scale + mainController.yShift)

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

    private fun Region.imageMarketRoom(tile: RoomTile) = hbox {
        val sbp = SimpleBooleanProperty(false)
        mainController.marketMap[tile.title] = sbp
        removeWhen(sbp)
        padding = Insets(10.0, 20.0, 10.0, 20.0)
        imageview {
            onMousePressed = EventHandler {
                mainController.removeBuying = false
                mainController.buyingTilePane.add(
                    imageview {
                        imageProperty().bind(mainController.buingImageProperty)
                        visibleProperty().bind(mainController.visibleProperty)
                        layoutXProperty().bind(mainController.xBuyProperty)
                        layoutYProperty().bind(mainController.yBuyProperty)
                        fitWidthProperty().bind(mainController.widthBuyProperty)
                        fitHeightProperty().bind(mainController.heightBuyProperty)
                        rotateProperty().bind(mainController.rotationBuyProperty)
                        onMouseClicked = EventHandler {
                            if (it.button == MouseButton.SECONDARY)
                                mainController.rotationBuy += 90.0
                        }
                        toFront()
                        removeWhen(mainController.removeBuyingProperty)
                    }
                )

                println("onMousePressed imageMarketRoom ${tile.title}")
                mainController.byingTile = tile
                val f = tile.toFigure(Position(0, 0, R0))
                mainController.widthBuy = f.width()
                mainController.heightBuy = f.height()
                mainController.buingImage = Image(mainController.imageUri(tile.title))
                mainController.rotationBuy = 0.0
                mainController.visible = true
            }
            val simpleObjectProperty = mainController.imageProperty(tile.title)
            imageProperty().bind(simpleObjectProperty)
            fitWidth = tile.toFigure(Position(0, 0, R0)).width(40.0)
            fitHeight = tile.toFigure(Position(0, 0, R0)).height(40.0)
        }
    }

    fun Figure.width(scale: Double? = null): Double = when (this) {
        is Rectangle -> width * (scale ?: mainController.scale)
        is Circle -> diameter * (scale ?: mainController.scale)
        is LFigure -> 4 * (scale ?: mainController.scale)
        is Octagon -> 7 * (scale ?: mainController.scale)
    }

    fun Figure.height(scale: Double? = null): Double = when (this) {
        is Rectangle -> height * (scale ?: mainController.scale)
        is Circle -> diameter * (scale ?: mainController.scale)
        is LFigure -> 4 * (scale ?: mainController.scale)
        is Octagon -> 4 * (scale ?: mainController.scale)
    }
}

private fun Position.Rotation.toNumber(): Double = when (this) {
    R0 -> 0.0
    R90 -> 90.0
    R180 -> 180.0
    R270 -> 270.0
}

class MainController {
    val roomPropertyMap: MutableMap<String, RoomProperty> = mutableMapOf()
    val tilesMap = mutableMapOf<String, PositionedTile>()
    val marketMap = mutableMapOf<String, SimpleBooleanProperty>()
    fun imageUri(name: String) = this.javaClass.getResource("/image/${filesMap[name]}")?.toURI().toString()
    fun imageProperty(name: String) = SimpleObjectProperty(Image(imageUri(name)))

    val buingImageProperty = SimpleObjectProperty(Image(imageUri("Буфетная")))
    var buingImage by buingImageProperty

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

    val visibleProperty = SimpleBooleanProperty(false)
    var visible by visibleProperty

    val xBuyProperty = SimpleDoubleProperty(600.0)
    var xBuy by xBuyProperty

    val yBuyProperty = SimpleDoubleProperty(30.0)
    var yBuy by yBuyProperty

    val widthBuyProperty = SimpleDoubleProperty(200.0)
    var widthBuy by widthBuyProperty

    val heightBuyProperty = SimpleDoubleProperty(200.0)
    var heightBuy by heightBuyProperty

    val rotationBuyProperty = SimpleDoubleProperty(0.0)
    var rotationBuy by rotationBuyProperty

    var byingTile: RoomTile? = null
    lateinit var buyingTilePane: Pane

    var xInit = 0.0
    var yInit = 0.0

    val removeBuyingProperty = SimpleBooleanProperty(false)
    var removeBuying by removeBuyingProperty

    lateinit var castlePane: Pane
}

class RoomProperty(initial: Double) {
    val property = SimpleDoubleProperty(initial)
    var value by property
}
