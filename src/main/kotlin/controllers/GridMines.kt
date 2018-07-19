package controllers

import MainApp
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import models.LossGameException
import models.MinesGame
import models.WinGameException
import kotlin.math.min


class GridMines(private val game: MinesGame, private val parent: VBox) : GridPane() {

    private val cellsBtn: Array<Array<MineBtn?>> = Array(game.colNb) { arrayOfNulls<MineBtn>(game.rowNb) }

    fun show() {

        prefHeight = parent.height
        prefWidth = parent.width
        alignment = Pos.CENTER
        vgap = 1.0
        hgap = 1.0

        val mSize = 0.98 * min((prefHeight - (vgap * game.rowNb)) / game.rowNb.toDouble(), (prefWidth - (hgap * game.colNb)) / game.colNb.toDouble())


        val fontSize = (mSize - (mSize * 0.20))

        for (i in 0 until game.colNb) {
            for (j in 0 until game.rowNb) {
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

        try {
            val ret = game.chooseCell(cellBtn.cord.first, cellBtn.cord.second)
            ret.forEach {
                cellsBtn[it.first][it.second]?.found(it.third)
                println("Show colNb : " + it.first + ", rowNb : " + it.second + ", v : " + it.third)
            }
        } catch (e : LossGameException) {
            println("LOSS")
            e.remained.forEach {
                cellsBtn[it.first][it.second]?.found(it.third)
                cellsBtn[it.first][it.second]?.style = cellsBtn[it.first][it.second]?.style + "-fx-text-fill:red;"
            }
        } catch (e: WinGameException) {
            println("WIN")
            e.discovered.forEach {
                cellsBtn[it.first][it.second]?.found(it.third)
                cellsBtn[it.first][it.second]?.style = cellsBtn[it.first][it.second]?.style + "-fx-text-fill:green;"
            }
            e.mines.forEach {
                cellsBtn[it.first][it.second]?.found(it.third)
            }

        }

        this.requestFocus()
    }

    fun warningCell(cellBtn: MineBtn) {
        //cellBtn.value = 1.toString()
        //cellBtn.isDisable = true
        this.requestFocus()
    }

}