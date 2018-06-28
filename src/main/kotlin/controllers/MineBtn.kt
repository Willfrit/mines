package controllers

import javafx.scene.control.Button


//cord (colNb, rowNb)
class MineBtn(val cord: Pair<Int, Int>) : Button() {


    var value: String = " "
        set(value) {
            field = value
            this.text = value
        }

    init {
        this.text = value
    }
}