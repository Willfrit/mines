package controllers

import javafx.scene.control.Button
import javafx.scene.control.Label

//cord (x, y)
class MineBtn(val cord:Pair<Int,Int>) : Button() {

    var value :String = " "
        set(value) {
            field =value
            label.text = value
        }

    private val label = Label(value)

    init {
        this.graphic = label
    }
}