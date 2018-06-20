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
import kotlin.math.min


class GridMines(private val x: Int, private val y:Int) : GridPane() {

    val cellsBtn: Array<Array<MineBtn?>> = Array(x, { arrayOfNulls<MineBtn>(y) })

    private fun autoResize() {
        prefHeight = MainApp.HEIGHT_WINDOW - 100
        prefWidth = (MainApp.WIDTH_WINDOW * 0.7) - 20
    }

    fun show() {
        autoResize()

        val mSize = min((prefHeight - (vgap * y)) / y.toDouble(), (prefWidth - (hgap * x)) / x.toDouble())
        val fontSize = (mSize - (mSize * 0.20))
        alignment = Pos.CENTER
        vgap = 1.0
        hgap = 1.0

        for (i in 0 until x) {
            for (j in 0 until y) {
                val btn = MineBtn(Pair(x, y))
                btn.prefHeight = mSize
                btn.prefWidth = mSize
                btn.maxHeight = mSize
                btn.maxWidth = mSize
                btn.minHeight = mSize
                btn.minWidth = mSize
                btn.style = "-fx-font-size: " + fontSize.toString() + ";-fx-padding:0px;-fx-background-size:0px;"

                btn.onAction = EventHandler<ActionEvent> {
                    btn.value = 4.toString()
                }

                btn.onMouseClicked = EventHandler<MouseEvent> {
                    if (it.button == MouseButton.SECONDARY) btn.value = 1.toString()
                }
                cellsBtn[i][j] = btn

                add(btn, i, j, 1, 1)
            }
        }
    }

}