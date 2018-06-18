package main.kotlin.models

import java.lang.Exception

class MinesGame(val x :Int , val y:Int, val nbMines: Int) {

    constructor (x :Int , y:Int, ratioMines: Double): this(x,y, ((x * y) * ratioMines).toInt())

    init {
        if (x < 0 || y < 0 || nbMines < 1 || nbMines > x*y - 1) {
            throw Exception("Bad parameter for the game")
        }
        val nbTurn = 0
    }



}