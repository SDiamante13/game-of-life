package com.diamante.gameoflife

import org.junit.jupiter.api.Test

class GameOfLifeTest {

    private val O = CellState.ALIVE
    private val X = CellState.DEAD

    @Test
    fun `cycle a game of life`() {
        val initialState = arrayOf(
            arrayOf(X, X, O, O, O, X, X, X, O, O, O, X, X),
            arrayOf(X, X, X, X, X, X, X, X, X, X, X, X, X),
            arrayOf(O, X, X, X, X, O, X, O, X, X, X, X, O),
            arrayOf(O, X, X, X, X, O, X, O, X, X, X, X, O),
            arrayOf(O, X, X, X, X, O, X, O, X, X, X, X, O),
            arrayOf(X, X, O, O, O, X, X, X, O, O, O, X, X),
            arrayOf(X, X, X, X, X, X, X, X, X, X, X, X, X),
            arrayOf(X, X, O, O, O, X, X, X, O, O, O, X, X),
            arrayOf(O, X, X, X, X, O, X, O, X, X, X, X, O),
            arrayOf(O, X, X, X, X, O, X, O, X, X, X, X, O),
            arrayOf(O, X, X, X, X, O, X, O, X, X, X, X, O),
            arrayOf(X, X, X, X, X, X, X, X, X, X, X, X, X),
            arrayOf(X, X, O, O, O, X, X, X, O, O, O, X, X)
        )
        val pulsar = Universe(initialState)

        for (i in 1..10) {
            pulsar.printCells()
            pulsar.update()
        }

    }
}