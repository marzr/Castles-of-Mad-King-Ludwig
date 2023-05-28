package com.github.marzr.castles

import com.github.marzr.castles.data.*
import com.github.marzr.castles.data.rooms.*
import com.github.marzr.castles.game.Player
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
import java.util.UUID
import kotlin.math.roundToInt

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

    private fun rotationShift(tile: Tile, rotation: Position.Rotation): Double = when {
        tile is OctagonRoom && (rotation == R90 || rotation == R270) -> 1.5
        tile is LongRectangleRoom && (rotation == R90 || rotation == R270) -> 2.5
        tile is MiddleRectangleRoom && (rotation == R90 || rotation == R270) -> 1.5
        tile is LargeRectangleRoom && (rotation == R90 || rotation == R270) -> 1.5
        tile is SmallRectangleRoom && (rotation == R90 || rotation == R270) -> 1.0
        (tile is Hallway || tile is DarkHallway) && (rotation == R90 || rotation == R270) -> 3.5
        tile is Stairs && (rotation == R90 || rotation == R270) -> 1.0
        else -> 0.0
    }

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
            imageMarketRoom(roomsByTitle["Ателье"]!!, "15000")
            imageMarketRoom(roomsByTitle["Большая спальня"]!!, "10000")
            imageMarketRoom(roomsByTitle["Салон"]!!, "8000")
            imageMarketRoom(roomsByTitle["Каретный гараж"]!!, "6000")
            imageMarketRoom(roomsByTitle["Террасы"]!!, "4000")
            imageMarketRoom(roomsByTitle["Солярий"]!!, "2000")
            imageMarketRoom(roomsByTitle["Потайная комната"]!!, "1000")
            imageMarketRoom(Stairs(), "3000")
            imageMarketRoom(Hallway(), "3000")
            imageMarketRoom(DarkHallway(), "3000")

            prefHeight = 60.0
        }

        region {
            pane {
                mainController.buyingTilePane = this
                onDoubleClick {
                    with(mainController) {
                        val rotation = rotationBuy.toRotation()
                        val rotationShift = rotationShift(buyingTile!!, rotation)
                        val x = ((xBuy - xShift - centerX) / scale + rotationShift).roundToInt()
                        val y = ((-yBuy + yShift + centerY) / scale + rotationShift).roundToInt()
                        castlePane.add(
                            imageRoom(PositionedTile(buyingTile!!, Position(x, y, rotation)))
                        )
                        removeBuying = true
                        marketMap[mainController.buyingTile!!.title]!!.value = true
                    }
                }
                onMousePressed = EventHandler {
                    mainController.xInit = it.x - mainController.xBuy
                    mainController.yInit = it.y - mainController.yBuy
                }
                onMouseDragged = EventHandler {
                    mainController.xBuy = it.x - mainController.xInit
                    mainController.yBuy = it.y - mainController.yInit
                }
            }

            pane {
                mainController.castlePane = this
                imageRoom(PositionedTile(Foyer(Player.PlayerColor.RED), Position(0, 0, R0)))
                imageRoom(PositionedTile(roomsByTitle["Буфетная"]!!, Position(-2, 4, R270)))
                imageRoom(PositionedTile(roomsByTitle["Комната отдыха"]!!, Position(0, 8, R90)))
                imageRoom(PositionedTile(roomsByTitle["Хижина Хундинга"]!!, Position(5, -2, R270)))
                imageRoom(PositionedTile(roomsByTitle["Верхний зал"]!!, Position(3, 0, R0)))
                imageRoom(PositionedTile(Stairs(), Position(-3, -1, R0)))
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

        if (mainController.buyingTile != null) {
            val f = PositionedTile(mainController.buyingTile!!, Position(0, 0, R0)).toFigure()
            mainController.widthBuy = f.width()
            mainController.heightBuy = f.height()
        }

        mainController.tilesMap.forEach { (id, tile) ->
            mainController.roomPropertyMap[id.toString()]!!.value = tile.toFigure().height()
            mainController.roomPropertyMap["${id}_width"]!!.value = tile.toFigure().width()
            mainController.roomPropertyMap["${id}_x"]!!.value = positionX(tile)
            mainController.roomPropertyMap["${id}_y"]!!.value = positionY(tile)
        }
    }

    private fun onMouseDraggedEventHandler(): EventHandler<MouseEvent> = EventHandler {
        if (mainController.xDrag == 0.0) {
            mainController.xDrag = it.x
            mainController.yDrag = it.y
            return@EventHandler
        }

        mainController.xShift = mainController.xShift + it.x - mainController.xDrag
        mainController.yShift = mainController.yShift + it.y - mainController.yDrag

        mainController.xDrag = it.x
        mainController.yDrag = it.y

        mainController.tilesMap.forEach { (id, tile) ->
            mainController.roomPropertyMap[id.toString()]!!.value = tile.toFigure().height()
            mainController.roomPropertyMap["${id}_width"]!!.value = tile.toFigure().width()
            mainController.roomPropertyMap["${id}_x"]!!.value = positionX(tile)
            mainController.roomPropertyMap["${id}_y"]!!.value = positionY(tile)
        }
    }

    private fun onMouseReleasedEventHandler(): EventHandler<MouseEvent> = EventHandler {
        mainController.xDrag = 0.0
        mainController.yDrag = 0.0
    }

    private fun Region.imageRoom(tile: PositionedTile) = imageview {
        val id = UUID.randomUUID()
        mainController.tilesMap[id] = tile
        val simpleObjectProperty = mainController.imageProperty(tile.tile.title)
        imageProperty().bind(simpleObjectProperty)

        val roomHeightProperty = RoomProperty(tile.toFigure().height())
        val roomWidthProperty = RoomProperty(tile.toFigure().width())

        val roomXProperty = RoomProperty(positionX(tile))
        val roomYProperty = RoomProperty(positionY(tile))

        fitHeightProperty().bind(roomHeightProperty.property)
        fitWidthProperty().bind(roomWidthProperty.property)
        layoutYProperty().bind(roomYProperty.property)
        layoutXProperty().bind(roomXProperty.property)

        mainController.roomPropertyMap[id.toString()] = roomHeightProperty
        mainController.roomPropertyMap["${id}_width"] = roomWidthProperty
        mainController.roomPropertyMap["${id}_x"] = roomXProperty
        mainController.roomPropertyMap["${id}_y"] = roomYProperty
        rotate = tile.position.rotation.toNumber()
    }

    fun positionX(tile: PositionedTile): Double {
        val rotationShift = rotationShift(tile.tile, tile.position.rotation)
        return centerX + (tile.position.x - rotationShift) * mainController.scale + mainController.xShift
    }

    fun positionY(tile: PositionedTile): Double {
        val rotationShift = rotationShift(tile.tile, tile.position.rotation)
        return centerY - (tile.position.y - rotationShift) * mainController.scale + mainController.yShift
    }

    private fun Region.imageMarketRoom(tile: Tile, price: String) = vbox {
        label {
            text = price
            padding = Insets(5.0)
        }
        padding = Insets(10.0, 20.0, 10.0, 20.0)
        imageview {
            val sbp = SimpleBooleanProperty(false)
            mainController.marketMap[tile.title] = sbp
            removeWhen(sbp)
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
                            if (it.button == MouseButton.SECONDARY) {
                                mainController.rotationBuy += 90.0
                                if (mainController.rotationBuy >= 360.0)
                                    mainController.rotationBuy -= 360.0
                            }
                        }
                        toFront()
                        mainController.xBuy = 600.0
                        mainController.yBuy = 30.0
                        removeWhen(mainController.removeBuyingProperty)
                    }
                )
                mainController.buyingTile = tile
                val f = PositionedTile(tile, Position(0, 0, R0)).toFigure()
                mainController.widthBuy = f.width()
                mainController.heightBuy = f.height()
                mainController.buingImage = Image(mainController.imageUri(tile.title))
                mainController.rotationBuy = 0.0
                mainController.visible = true
            }
            val simpleObjectProperty = mainController.imageProperty(tile.title)
            imageProperty().bind(simpleObjectProperty)
            fitWidth = PositionedTile(tile, Position(0, 0, R0)).toFigure().width(40.0)
            fitHeight = PositionedTile(tile, Position(0, 0, R0)).toFigure().height(40.0)
        }
    }

    fun Figure.width(scale: Double? = null): Double = when (this) {
        is Rectangle -> width * (scale ?: mainController.scale)
        is Circle -> diameter * (scale ?: mainController.scale)
        is LFigure -> 4 * (scale ?: mainController.scale)
        is Octagon -> width * (scale ?: mainController.scale)
    }

    fun Figure.height(scale: Double? = null): Double = when (this) {
        is Rectangle -> height * (scale ?: mainController.scale)
        is Circle -> diameter * (scale ?: mainController.scale)
        is LFigure -> 4 * (scale ?: mainController.scale)
        is Octagon -> height * (scale ?: mainController.scale)
    }
}

private fun Position.Rotation.toNumber(): Double = when (this) {
    R0 -> 0.0
    R90 -> 90.0
    R180 -> 180.0
    R270 -> 270.0
}

private fun Double.toRotation(): Position.Rotation = when (this) {
    0.0 -> R0
    90.0 -> R90
    180.0 -> R180
    270.0 -> R270
    else -> throw IllegalStateException("Illegal rotation $this")
}

class MainController {
    val roomPropertyMap: MutableMap<String, RoomProperty> = mutableMapOf()
    val tilesMap = mutableMapOf<UUID, PositionedTile>()
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

    var buyingTile: Tile? = null
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
