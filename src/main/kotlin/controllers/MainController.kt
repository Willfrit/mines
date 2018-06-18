package main.kotlin.controllers

import javafx.fxml.FXML
import javafx.scene.control.Label
import kotlin.math.max
import kotlin.math.min


class MainController {

    @FXML
    lateinit var minesGPane: GridMines

    @FXML
    lateinit var debugText: Label

    fun initialize() {
        minesGPane.createCells(30,30)
    }
}