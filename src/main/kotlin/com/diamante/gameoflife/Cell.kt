package com.diamante.gameoflife

data class Cell(var cellState: CellState) {

    fun update(initialCellState: CellState, neighbors: Int) {
        cellState =  if (initialCellState == CellState.ALIVE) {
            if (neighbors in 2..3) CellState.ALIVE
            else CellState.DEAD
        } else {
            if (neighbors == 3) CellState.ALIVE
            else CellState.DEAD
        }
    }
}

enum class CellState {
    ALIVE, DEAD
}
