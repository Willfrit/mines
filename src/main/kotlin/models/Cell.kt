package models

class Cell(val v: Int) {
    companion object {
        fun Mine(): Cell {
            return Cell(-1)
        }
    }

    override fun toString(): String {
        return when {
            isMine() -> "Q"
            v == 0 -> " "
            else -> v.toString()
        }
    }

    fun isMine(): Boolean = v == -1
    fun isSafe(): Boolean = v == 0
}

