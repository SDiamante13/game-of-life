class GameOfLife {

    fun checkLeftIsAlive(matrix: Array<CharArray>, row: Int, col: Int): Boolean {
        return col != 0 && matrix[row][col - 1] == 'o'
    }

    fun checkUpperLeftIsAlive(matrix: Array<CharArray>, row: Int, col: Int): Boolean {
        return row != 0 && col != 0 && matrix[row - 1][col - 1] == 'o'
    }

    fun checkUpIsAlive(matrix: Array<CharArray>, row: Int, col: Int): Boolean {
        return row != 0 && matrix[row - 1][col] == 'o'
    }

    fun checkUpperRightIsAlive(matrix: Array<CharArray>, row: Int, col: Int): Boolean {
        return col != matrix[0].size - 1 && row != 0 && matrix[row - 1][col + 1] == 'o'
    }

    fun checkRightIsAlive(matrix: Array<CharArray>, row: Int, col: Int): Boolean {
        return col != matrix[0].size - 1 && matrix[row][col + 1] == 'o'
    }

    fun checkLowerRightIsAlive(matrix: Array<CharArray>, row: Int, col: Int): Boolean {
        return col != matrix[0].size - 1 && row != matrix.size - 1 && matrix[row + 1][col + 1] == 'o'
    }

    fun checkDownIsAlive(matrix: Array<CharArray>, row: Int, col: Int): Boolean {
        return row != matrix.size - 1 && matrix[row + 1][col] == 'o'
    }

    fun checkLowerLeftIsAlive(matrix: Array<CharArray>, row: Int, col: Int): Boolean {
        return col != 0 && row != matrix.size - 1 && matrix[row + 1][col - 1] == 'o'
    }

    fun checkCurrentIsAlive(matrix: Array<CharArray>, row: Int, col: Int): Boolean {
        return matrix[row][col] == 'o'
    }

    fun nextGen(matrix: Array<CharArray>): Array<CharArray> {
        val nextGenMatrix = Array(matrix.size) {CharArray(matrix[0].size) { ' ' } }

        matrix.forEachIndexed { row, chars ->
            chars.forEachIndexed { col, _ ->
                if (checkCurrentIsAlive(matrix, row, col)) {
                    if (willAliveCellSurviveNextGen(matrix, row, col)) nextGenMatrix[row][col] = 'o'
                    else nextGenMatrix[row][col] = ' '
                } else {
                    if (willDeadCellBecomeAliveNextGen(matrix, row, col)) nextGenMatrix[row][col] = 'o'
                    else nextGenMatrix[row][col] = ' '
                }
            }
        }
        return nextGenMatrix
    }

    /**
     *  ' ' 'o' ' '          'o' 'o' ' '
     *  'o' 'o' ' '    --->  'o' ' ' ' '
     *  ' ' 'o' 'o'          'o' 'o' 'o'
     **/

    fun willAliveCellSurviveNextGen(matrix: Array<CharArray>, row: Int, col: Int): Boolean {
        var neighbors = 0

        if (checkLeftIsAlive(matrix, row, col)) neighbors++
        if (checkUpperLeftIsAlive(matrix, row, col)) neighbors++
        if (checkUpIsAlive(matrix, row, col)) neighbors++
        if (checkUpperRightIsAlive(matrix, row, col)) neighbors++
        if (checkRightIsAlive(matrix, row, col)) neighbors++
        if (checkLowerRightIsAlive(matrix, row, col)) neighbors++
        if (checkDownIsAlive(matrix, row, col)) neighbors++
        if (checkLowerLeftIsAlive(matrix, row, col)) neighbors++

        if (neighbors == 2 || neighbors == 3) return true

        return false
    }

    fun willDeadCellBecomeAliveNextGen(matrix: Array<CharArray>, row: Int, col: Int): Boolean {
        var neighbors = 0

        if (checkLeftIsAlive(matrix, row, col)) neighbors++
        if (checkUpperLeftIsAlive(matrix, row, col)) neighbors++
        if (checkUpIsAlive(matrix, row, col)) neighbors++
        if (checkUpperRightIsAlive(matrix, row, col)) neighbors++
        if (checkRightIsAlive(matrix, row, col)) neighbors++
        if (checkLowerRightIsAlive(matrix, row, col)) neighbors++
        if (checkDownIsAlive(matrix, row, col)) neighbors++
        if (checkLowerLeftIsAlive(matrix, row, col)) neighbors++

        if (neighbors == 3) return true

        return false
    }

}
