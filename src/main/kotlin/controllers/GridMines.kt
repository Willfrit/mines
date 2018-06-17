package main.kotlin.controllers

import javafx.scene.layout.GridPane
import javafx.stage.Screen
import main.kotlin.MainApp
import kotlin.math.min

class GridMines : GridPane() {

    fun autoResize() {
        val primaryScreenBounds = Screen.getPrimary().visualBounds
        val m = min(primaryScreenBounds.width * MainApp.RATIO_WINDOWS, primaryScreenBounds.height * MainApp.RATIO_WINDOWS)
        setPrefSize(m-10, m-10)
    }

    fun createCell(x: Int, y: Int) {

    }

}