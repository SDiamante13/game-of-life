package com.diamante.gameoflife

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CellTest {

    @DisplayName("Cell state tests")
    @ParameterizedTest(name = "{0} cell with {1} neighbors should be {2} in its next state")
    @CsvSource(value = [
        "ALIVE, 0, DEAD",
        "ALIVE, 1, DEAD",
        "ALIVE, 2, ALIVE",
        "ALIVE, 3, ALIVE",
        "ALIVE, 4, DEAD",
        "ALIVE, 5, DEAD",
        "ALIVE, 6, DEAD",
        "ALIVE, 7, DEAD",
        "ALIVE, 8, DEAD",
        "DEAD, 0, DEAD",
        "DEAD, 1, DEAD",
        "DEAD, 2, DEAD",
        "DEAD, 3, ALIVE",
        "DEAD, 4, DEAD",
        "DEAD, 5, DEAD",
        "DEAD, 6, DEAD",
        "DEAD, 7, DEAD",
        "DEAD, 8, DEAD"
    ])
    fun `cell state tests`(initialState: String, neighbors: Int, expectedState: String) {
        val cellState = CellState.valueOf(initialState)
        val cell = Cell(cellState)

        cell.update(cellState, neighbors)

        val result = cell.cellState

        assertThat(result).isEqualTo(CellState.valueOf(expectedState))
    }
}