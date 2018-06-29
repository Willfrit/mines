package models

import java.lang.Exception

class MinesGame(val colNb: Int, val rowNb: Int, val nbMines: Int) {

    constructor (x: Int, y: Int, ratioMines: Double) : this(x, y, ((x * y) * ratioMines).toInt())

    private val cellsValue: Array<Array<Cell>> = Array(colNb) { Array(rowNb) { Cell(0) } }

    private val alreadyDiscovered = mutableSetOf<Triple<Int, Int, Cell>>()

    var nbTurn = 0

    init {
        if (colNb < 0 || rowNb < 0 || nbMines < 1 || nbMines > colNb * rowNb - 1 || colNb * rowNb - nbMines < 10) {
            throw Exception("Bad parameter for the game colNb : " + colNb.toString() + ", rowNb : " + rowNb.toString() + ", nbMines : " + nbMines)
        }

    }

    fun chooseCell(x: Int, y: Int): List<Triple<Int, Int, Cell>> {
        println("Choose colNb : " + x.toString() + " ,  rowNb : " + y.toString())

        if (nbTurn == 0) {
            firstTurnInit(x, y)
        }

        if (cellsValue[x][y].isMine()) {
            throw LossGameException("Loss", getRemainedCell())
        }

        val visibleCell: List<Triple<Int, Int, Cell>> = discoverCell(x, y)

        if (alreadyDiscovered.size == (rowNb * colNb) - nbMines) {
            throw WinGameException("Win", alreadyDiscovered.toList(), getRemainedCell(), nbTurn)
        }

        nbTurn++
        return visibleCell
    }

    private fun getRemainedCell() : List<Triple<Int, Int, Cell>> {
        val remainCells: MutableList<Triple<Int, Int, Cell>> = MutableList(0) { Triple(0, 0, Cell(0)) }
        for (i in 0 until this.colNb)
            for (j in 0 until this.rowNb)
                if (!alreadyDiscovered.contains(Triple(i,j, cellsValue[i][j])))
                    remainCells.add(Triple(i, j, cellsValue[i][j]))
        return remainCells
    }

    private fun discoverCell(x: Int, y: Int): List<Triple<Int, Int, Cell>> {
        val discovered = mutableSetOf<Triple<Int, Int, Cell>>()

        val queue = mutableListOf<Pair<Int, Int>>()
        val root = cellsValue[x][y]
        if (root.isSafe()) {
            queue.add(Pair(x, y))
        }

        discovered.add(Triple(x, y, root))
        alreadyDiscovered.add(Triple(x, y, root))

        while (queue.isNotEmpty()) {
            val current = queue.removeAt(0)
            neighborsCell(current.first, current.second).forEach {
                val cell = cellsValue[it.first][it.second]
                if (!discovered.contains(Triple(it.first, it.second, cell))) {

                    discovered.add(Triple(it.first, it.second, cell))
                    alreadyDiscovered.add(Triple(it.first, it.second, cell))

                    if (cell.isSafe()) {
                        queue.add(Pair(it.first, it.second))
                    }
                }
            }
        }

        return discovered.toList()
    }

    private fun neighborsCell(x: Int, y: Int): List<Pair<Int, Int>> {
        val neighbors = mutableListOf<Pair<Int, Int>>()

        // above-right -
        if (x > 0 && y > 0) neighbors.add(Pair(x - 1, y - 1))
        // above -
        if (y > 0) neighbors.add(Pair(x, y - 1))
        // above-left -
        if (x < colNb - 1 && y > 0) neighbors.add(Pair(x + 1, y - 1))
        // left
        if (x > 0) neighbors.add(Pair(x - 1, y))
        // right
        if (x < colNb - 1) neighbors.add(Pair(x + 1, y))
        // below-right
        if (x > 0 && y < rowNb - 1) neighbors.add(Pair(x - 1, y + 1))
        // below
        if (y < rowNb - 1) neighbors.add(Pair(x, y + 1))
        // below-left
        if (x < colNb - 1 && y < rowNb - 1) neighbors.add(Pair(x + 1, y + 1))

        return neighbors
    }

    private fun firstTurnInit(x: Int, y: Int) {
        placeMines(x, y)
        createNumCell()
    }

    private fun createNumCell() {
        for (x in 0 until this.colNb) {
            for (y in 0 until this.rowNb) {
                if (cellsValue[x][y].isMine()) continue

                var nbMineNext = 0
                neighborsCell(x, y).forEach {
                    if (cellsValue[it.first][it.second].isMine())
                        nbMineNext++
                }

                cellsValue[x][y] = Cell(nbMineNext)
            }
        }
    }

    private fun placeMines(x: Int, y: Int) {
        val freeCell: MutableList<Pair<Int, MutableList<Int>>> = MutableList(this.colNb)
        { i ->
            Pair(i, MutableList(this.rowNb) { j -> j })
        }
        freeCell.filter { it.first == x || it.first == x + 1 || it.first == x - 1 }
                .forEach {
                    it.second.removeAll { y == it || y + 1 == it || y - 1 == it }
                }
        freeCell.removeAll { it.second.size == 0 }
        for (i in 1..nbMines) {
            freeCell.shuffle()
            freeCell[0].second.shuffle()

            cellsValue[freeCell[0].first][freeCell[0].second[0]] = Mine()

            freeCell[0].second.removeAt(0)
            if (freeCell[0].second.size == 0) {
                freeCell.removeAt(0)
            }
        }

    }

}