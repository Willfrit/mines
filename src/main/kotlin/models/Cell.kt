package models

class Mine : Cell(-1)

open class Cell(val v: Int) {

    override fun toString(): String {
        return when {
            isMine() -> "\u25CF"
            v == 0 -> "·"
            else -> v.toString()
        }
    }

    fun isMine(): Boolean = v == -1
    fun isSafe(): Boolean = v == 0
}

