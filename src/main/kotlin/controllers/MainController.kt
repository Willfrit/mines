package controllers

import controllers.GridMines
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.VBox


class MainController {

    @FXML
    lateinit var boxGridMines: VBox

    @FXML
    lateinit var debugText: Label

    var gridMines = GridMines(10,10)

    fun initialize() {

        boxGridMines.children.addAll(gridMines)
        gridMines.show()
    }
}