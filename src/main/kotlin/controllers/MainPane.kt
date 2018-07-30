package controllers

import javafx.beans.value.ChangeListener
import javafx.fxml.FXML
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import models.MinesGame
import javafx.beans.value.ObservableValue
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.text.Text
import network.MinesServer


class MainPane : GridPane() {

    @FXML
    lateinit var boxGridMines: VBox

    @FXML
    lateinit var btnGameInput: Button

    @FXML
    lateinit var settingsPane: ScrollPane

    @FXML
    lateinit var xField: TextField

    @FXML
    lateinit var yField: TextField

    @FXML
    lateinit var mineField: TextField

    @FXML
    lateinit var portField: TextField

    @FXML
    lateinit var hboxPort: HBox

    @FXML
    lateinit var btnGameSettings: Button

    @FXML
    lateinit var cboxGameType: ComboBox<String>

    @FXML
    lateinit var debugText: Text

    var game: MinesGame? = null

    fun initialize() {
        boxGridMines.widthProperty().addListener { obs, oldVal, newVal ->
            resizeGame()
        }
        boxGridMines.heightProperty().addListener { obs, oldVal, newVal ->
            resizeGame()
        }

        cboxGameType.valueProperty().addListener { obs, oldVal, newVal ->
            hboxPort.isVisible = newVal == "TCP"
        }

        btnGameInput.onAction = EventHandler {
            debugText.text += "\nReset input method to " + cboxGameType.value
            if (cboxGameType.value == "TCP") {
                startServer(portField.text.toInt())
            }
        }
    }

    private fun resizeGame() {
        if (boxGridMines.width < 1 || boxGridMines.height < 1) return
        if (game == null) {
            game = MinesGame(30,30,0.20)
            val gridMines = GridMines(game!!, boxGridMines)
            boxGridMines.children.clear()
            boxGridMines.children.addAll(gridMines)
            gridMines.show()
        }
    }

    private fun startServer(port : Int){
        MinesServer(port)
    }

}