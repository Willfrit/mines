package models

import java.lang.Exception

class LossGameException(override var message: String) : Exception(message)
class WinGameException(override var message: String) : Exception(message)

class MinesGame(val x: Int, val y: Int, val nbMines: Int) {

    constructor (x: Int, y: Int, ratioMines: Double) : this(x, y, ((x * y) * ratioMines).toInt())

    private val cellsValue: Array<Array<Int>> = Array(x) { Array(y) { 0 } }

    var nbTurn = 0

    init {
        if (x < 0 || y < 0 || nbMines < 1 || nbMines > x * y - 1 || nbMines < 10) {
            throw Exception("Bad parameter for the game x : " + x.toString() + ", y : " + y.toString() + ", nbMines : " + nbMines)
        }

    }

    fun chooseCell(x: Int, y: Int): List<Triple<Int, Int, Int>> {
        println("Choose x : " + x.toString() + " ,  y : " + y.toString())
        if (nbTurn == 0) firstTurnInit(x, y)
        val visibleCell: MutableList<Triple<Int, Int, Int>> = MutableList(0) { Triple(0,0,0) }

        // Debug
        for (i in 0 until this.x)
            for (j in 0 until this.y)
                visibleCell.add(Triple(i,j,cellsValue[i][j]))


        if (cellsValue[x][y] == -1) {
            throw LossGameException("Loss")
        }

        nbTurn++
        return visibleCell
    }

    private fun firstTurnInit(x: Int, y: Int) {
        val freeCell: MutableList<Pair<Int, MutableList<Int>>> = MutableList(this.x)
        { i ->
            Pair(i, MutableList(this.y) { j -> j })
        }
        freeCell.filter { it.first == x || it.first == x + 1 || it.first == x - 1 }
                .forEach {
                    it.second.removeAll { y == it || y + 1 == it || y - 1 == it }
                }
        freeCell.removeAll { it.second.size == 0 }
        for (i in 1..nbMines) {
            freeCell.shuffle()
            freeCell[0].second.shuffle()

            cellsValue[freeCell[0].first][freeCell[0].second[0]] = -1

            freeCell[0].second.removeAt(0)
            if (freeCell[0].second.size == 0) {
                freeCell.removeAt(0)
            }
        }

    }

}