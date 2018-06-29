package controllers

import javafx.scene.control.Button
import models.Cell


//cord (colNb, rowNb)
class MineBtn(val cord: Pair<Int, Int>) : Button() {

    fun found(c: Cell) {
        val text = c.toString()
        this.value = text
        this.isDisable = true
    }

    var value: String = " "
        set(value) {
            field = value
            this.text = value
        }

    init {
        this.text = value
    }
}