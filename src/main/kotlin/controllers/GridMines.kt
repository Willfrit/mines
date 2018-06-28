package controllers

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import MainApp
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import models.MinesGame
import kotlin.math.min
import javafx.animation.FadeTransition
import javafx.util.Duration


class GridMines(private val x: Int, private val y: Int) : GridPane() {

    private val cellsBtn: Array<Array<MineBtn?>> = Array(x) { arrayOfNulls<MineBtn>(y) }
    private val game = MinesGame(x, y, 0.20)

    private fun autoResize() {
        prefHeight = MainApp.HEIGHT_WINDOW - 100
        prefWidth = (MainApp.WIDTH_WINDOW * 0.7) - 20
    }

    fun show() {

        autoResize()

        val mSize = min((prefHeight - (vgap * y)) / y.toDouble(), (prefWidth - (hgap * x)) / x.toDouble())
        val fontSize = (mSize - (mSize * 0.20))
        println(fontSize)
        alignment = Pos.CENTER
        vgap = 1.0
        hgap = 1.0

        for (i in 0 until x) {
            for (j in 0 until y) {
                val btn = MineBtn(Pair(i, j))
                btn.prefHeight = mSize
                btn.prefWidth = mSize
                btn.maxHeight = mSize
                btn.maxWidth = mSize
                btn.minHeight = mSize
                btn.minWidth = mSize
                btn.style = "-fx-font-size: " + fontSize.toString() + ";-fx-padding:0px;"

                btn.onAction = EventHandler<ActionEvent> {
                    selectCell(btn)
                }

                btn.onMouseClicked = EventHandler<MouseEvent> {
                    if (it.button == MouseButton.SECONDARY) warningCell(btn)
                }
                cellsBtn[i][j] = btn

                add(btn, i, j, 1, 1)
            }
        }

    }

    fun selectCell(x: Int, y: Int) {
        selectCell(cellsBtn[x][y]!!)
    }

    fun warningCell(x: Int, y: Int) {
        warningCell(cellsBtn[x][y]!!)
    }

    fun selectCell(cellBtn: MineBtn) {

        val ret = game.chooseCell(cellBtn.cord.first, cellBtn.cord.second)

        ret.forEach {
            val text = it.third.toString()
            cellsBtn[it.first][it.second]?.value = text
            cellsBtn[it.first][it.second]?.isDisable = true
            println("Show colNb : " + it.first + ", rowNb : " + it.second + ", v : " + it.third)
        }

        this.requestFocus()
    }

    fun warningCell(cellBtn: MineBtn) {
        //cellBtn.value = 1.toString()
        //cellBtn.isDisable = true
        this.requestFocus()
    }

}