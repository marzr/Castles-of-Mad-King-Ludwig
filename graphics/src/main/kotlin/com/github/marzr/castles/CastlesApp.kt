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
                imageview {
                    imageProperty().bind(mainController.buingImageProperty)
                    visibleProperty().bind(mainController.visibleProperty)
                    layoutXProperty().bind(mainController.xBuyProperty)
                    layoutYProperty().bind(mainController.yBuyProperty)
                    fitWidthProperty().bind(mainController.widthBuyProperty)
                    fitHeightProperty().bind(mainController.heightBuyProperty)
                    rotateProperty().bind(mainController.rotationBuyProperty)
                    onDoubleClick {
                        println("double click")
                    }
                    onMouseClicked = EventHandler {
                        if (it.button == MouseButton.SECONDARY)
                            mainController.rotationBuy += 90.0
                    }
                    toFront()

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

        mainController.filesMap.keys.forEach {
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

    fun Region.imageMarketRoom(tile: RoomTile) = hbox {
        padding = Insets(10.0, 20.0, 10.0, 20.0)
        imageview {
            onMousePressed = EventHandler {
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

class MainController : Controller() {
    val filesMap = mapOf(
        "Театр" to "Activity/BC_Theater.png",
        "Зал заседаний" to TODO(),
        "Кегельбан" to "Activity/LR_kreg.png",
        "Игровая" to "Activity/LR_train.png",
        "Бильярдная" to "Activity/MR_bil.png",
        "Часовня" to "Activity/MR_oratory.png",
        "Ателье" to "Activity/MR_sewing_room.png",
        "Зал приемов" to "Activity/Oct_Aud.png",
        "Певческий зал" to TODO(),
        "Фортепианная" to "Activity/SC_Piano.png",
        "Кабинет Берты" to TODO(),
        "Музыкальный класс" to TODO(),

        "Верхний зал" to "Corridor/great_hall.png",
        "Главный зал" to "Corridor/upper_hall.png",

        "Потайная комната" to "Utility/L_panic_room.png",

        "Темница" to "Downstairs/SC_the_hole.png",

        "Буфетная" to "Food/L_scullery.png",

        "Хижина Хундинга" to "Outdoor/BC_hunding.png",
        "Террасы" to "Outdoor/Oct_terrace.png",
        "Парадные сады" to "Outdoor/LR_formal_gardens.png",

        "Лиловый кабинет" to "Living tooms/SSq_lilac_cabinet.png",

        "Спальня королевы" to "Sleeping/L_queen.png",
        "Большая спальня" to "Sleeping/Sq_grand_bedchamber.png",
        "Комната отдыха" to "Sleeping/SR_nap_room.png",
    )
    val roomPropertyMap: MutableMap<String, RoomProperty> = mutableMapOf()
    val tilesMap = mutableMapOf<String, PositionedTile>()
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

    var xInit = 0.0
    var yInit = 0.0
}

class RoomProperty(initial: Double) {
    val property = SimpleDoubleProperty(initial)
    var value by property
}
