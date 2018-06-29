package models

import java.lang.Exception

class LossGameException(override var message: String,
                        val remained: List<Triple<Int, Int, Cell>>) : Exception(message)

class WinGameException(override var message: String,
                       val discovered: List<Triple<Int, Int, Cell>>,
                       val mines: List<Triple<Int, Int, Cell>>,
                       val nbTurn: Int) : Exception(message)