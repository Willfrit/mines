package controllers

import com.sun.javafx.tk.Toolkit
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import com.sun.javafx.tk.Toolkit.getToolkit
import javafx.geometry.Insets


//cord (x, y)
class MineBtn(val cord:Pair<Int,Int>) : Button() {


    var value :String = " "
        set(value) {
            field =value
            label.text = value
        }

    private val label = Label(value)

    var metrics = Toolkit.getToolkit().fontLoader.getFontMetrics(label.getFont())


    init {
        this.graphic = label
        label.style = "-fx-padding:0px;"
        label.alignment = Pos.CENTER
        label.padding = Insets(-metrics.descent.toDouble(), 0.0, 0.0, 0.0)
    }
}