package com.diamante.gameoflife

class Universe(cellStates: Array<Array<CellState>>) {

    private var state: Array<Array<Cell>> = Array(cellStates.size) { Array(cellStates[0].size) { Cell(CellState.DEAD) } }

    init {
        cellStates.forEachIndexed { row, states ->
            states.forEachIndexed { col, _ ->
                state[row][col] = Cell(cellStates[row][col])
            }
        }
    }


    fun getState(): Array<Array<CellState>> {
        val cellStates = Array(state.size) { Array(state[0].size) { CellState.DEAD } }

        state.forEachIndexed { row, states ->
            states.forEachIndexed { col, _ ->
                cellStates[row][col] = state[row][col].cellState
            }
        }
        return cellStates
    }

    fun update() {
        val updatedCells = Array(state.size) { Array(state[0].size) { Cell(CellState.DEAD) } }

        state.forEachIndexed { row, states ->
            states.forEachIndexed { col, _ ->
                val numberOfAliveNeighbors = getNumberOfAliveNeighbors(row, col)
                updatedCells[row][col].update(state[row][col].cellState, numberOfAliveNeighbors)
            }
        }
        state = updatedCells
    }

    private fun getNumberOfAliveNeighbors(row: Int, col: Int): Int {
        var neighbors = 0

        if (checkLeftIsAlive(row, col)) neighbors++
        if (checkUpperLeftIsAlive(row, col)) neighbors++
        if (checkUpIsAlive(row, col)) neighbors++
        if (checkUpperRightIsAlive(row, col)) neighbors++
        if (checkRightIsAlive(row, col)) neighbors++
        if (checkLowerRightIsAlive(row, col)) neighbors++
        if (checkDownIsAlive(row, col)) neighbors++
        if (checkLowerLeftIsAlive(row, col)) neighbors++

        return neighbors

    }

    private fun checkLeftIsAlive(row: Int, col: Int): Boolean {
        return col != 0 && state[row][col - 1].cellState == CellState.ALIVE
    }

    private fun checkUpperLeftIsAlive(row: Int, col: Int): Boolean {
        return row != 0 && col != 0 && state[row - 1][col - 1].cellState == CellState.ALIVE
    }

    private fun checkUpIsAlive(row: Int, col: Int): Boolean {
        return row != 0 && state[row - 1][col].cellState == CellState.ALIVE
    }

    private fun checkUpperRightIsAlive(row: Int, col: Int): Boolean {
        return col != state[0].size - 1 && row != 0 && state[row - 1][col + 1].cellState == CellState.ALIVE
    }

    private fun checkRightIsAlive(row: Int, col: Int): Boolean {
        return col != state[0].size - 1 && state[row][col + 1].cellState == CellState.ALIVE
    }

    private fun checkLowerRightIsAlive(row: Int, col: Int): Boolean {
        return col != state[0].size - 1 && row != state.size - 1 && state[row + 1][col + 1].cellState == CellState.ALIVE
    }

    private fun checkDownIsAlive(row: Int, col: Int): Boolean {
        return row != state.size - 1 && state[row + 1][col].cellState == CellState.ALIVE
    }

    private fun checkLowerLeftIsAlive(row: Int, col: Int): Boolean {
        return col != 0 && row != state.size - 1 && state[row + 1][col - 1].cellState == CellState.ALIVE
    }


}