package controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import models.MinesGame


class MainPane : GridPane() {

    @FXML
    lateinit var boxGridMines: VBox

    @FXML
    lateinit var btnGameType: Button

    @FXML
    lateinit var settingsPane: ScrollPane

    @FXML
    lateinit var debugText: Label

    @FXML
    lateinit var xField: TextField

    @FXML
    lateinit var yField: TextField

    @FXML
    lateinit var mineField: TextField

    @FXML
    lateinit var btnGameSettings: Button

    var game: MinesGame? = null

    fun initialize() {
        println("Init : " + height.toString())
        boxGridMines.widthProperty().addListener { obs, oldVal, newVal ->
            resizeGame()
        }
        boxGridMines.heightProperty().addListener { obs, oldVal, newVal ->
            resizeGame()
        }
    }

    private fun resizeGame() {
        if (boxGridMines.width < 1 || boxGridMines.height < 1) return
        if (game == null){
            game = MinesGame(30,30,0.20)
            val gridMines = GridMines(game!!, boxGridMines)
            boxGridMines.children.clear()
            boxGridMines.children.addAll(gridMines)
            gridMines.show()
        }
    }

}