package main.kotlin.controllers

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import main.kotlin.MainApp
import kotlin.math.min


class GridMines : GridPane() {

    private fun autoResize(x: Int, y: Int) {
        val ratioXY = x.toDouble() / y.toDouble()
        //val m = min(MainApp.WIDTH_WINDOW * 0.7, MainApp.HEIGHT_WINDOW)
        prefHeight = MainApp.HEIGHT_WINDOW - 100
        prefWidth = (MainApp.WIDTH_WINDOW * 0.7) - 20

        println("H : " + prefHeight.toString())
        println("W : " + prefWidth.toString())
    }

    fun createCells(x: Int, y: Int) {
        autoResize(x, y)

        val mSize = min((prefHeight - (vgap*y)) / y.toDouble(), (prefWidth - (hgap*x)) / x.toDouble())

        alignment = Pos.CENTER
        vgap = 1.0
        hgap = 1.0
        for (i in 0 until x) {
            for (j in 0 until y) {
                val root = VBox()

                val btn = Button()
                btn.prefHeight = mSize
                btn.prefWidth = mSize
                btn.maxHeight = mSize
                btn.maxWidth = mSize
                btn.style = "-fx-font-size: 0px;-fx-padding:0px;-fx-background-size:0px;"
                root.prefHeight = mSize
                root.prefWidth = mSize
                root.maxHeight = mSize
                root.maxWidth = mSize
                root.minHeight = mSize
                root.minWidth = mSize
                root.children.addAll(btn)
                add(root, i, j, 1, 1)
            }
        }
    }

}