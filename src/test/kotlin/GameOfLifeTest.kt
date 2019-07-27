import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class GameOfLifeTest {

    private val startingMatrix = arrayOf(
        charArrayOf(' ', 'o', ' '),
        charArrayOf('o', 'o', ' '),
        charArrayOf(' ', 'o', 'o')
    )

    private val gameOfLife = GameOfLife()

    private var row = 0
    private var col = 0

    @Test
    fun `checkCurrentIsAlive checks the status of the current cell`() {
        row = 2
        col = 2
        var result = gameOfLife.checkCurrentIsAlive(startingMatrix, row, col)

        assertThat(result).isTrue()
    }

    @Test
    fun `checkLeftIsAlive checks the status of the cell to the left`() {
        row = 0
        col = 1
        var result = gameOfLife.checkLeftIsAlive(startingMatrix, row, col)

        assertThat(result).isFalse()

        col = 0
        result = gameOfLife.checkLeftIsAlive(startingMatrix, row, col)

        assertThat(result).isFalse()
    }

    @Test
    fun `checkUpperLeftIsAlive checks the status of the cell to the upper left`() {
        row = 2
        col = 2
        var result = gameOfLife.checkUpperLeftIsAlive(startingMatrix, row, col)

        assertThat(result).isTrue()

        row = 1
        col = 1
        result = gameOfLife.checkUpperLeftIsAlive(startingMatrix, row, col)

        assertThat(result).isFalse()

        col = 0
        result = gameOfLife.checkUpperLeftIsAlive(startingMatrix, row, col)

        assertThat(result).isFalse()
    }

    @Test
    fun `checkUpIsAlive checks the status of the cell above`() {
        row = 1
        col = 1
        var result = gameOfLife.checkUpIsAlive(startingMatrix, row, col)

        assertThat(result).isTrue()

        row = 0
        result = gameOfLife.checkLeftIsAlive(startingMatrix, row, col)

        assertThat(result).isFalse()
    }

    @Test
    fun `checkUpperRightIsAlive checks the status of the cell to the upper right`() {
        row = 1
        col = 0
        var result = gameOfLife.checkUpperRightIsAlive(startingMatrix, row, col)

        assertThat(result).isTrue()

        col = 1
        result = gameOfLife.checkUpperRightIsAlive(startingMatrix, row, col)

        assertThat(result).isFalse()

        col = 2
        result = gameOfLife.checkUpperRightIsAlive(startingMatrix, row, col)

        assertThat(result).isFalse()
    }

    @Test
    fun `checkRightIsAlive checks the status of the cell to the right`() {
        row = 2
        col = 1
        var result = gameOfLife.checkRightIsAlive(startingMatrix, row, col)

        assertThat(result).isTrue()

        col = 2
        result = gameOfLife.checkRightIsAlive(startingMatrix, row, col)
        assertThat(result).isFalse()
    }

    @Test
    fun `checkLowerRightIsAlive checks the status of the cell to the lower right`() {
        row = 1
        col = 1
        var result = gameOfLife.checkLowerRightIsAlive(startingMatrix, row, col)

        assertThat(result).isTrue()

        col = 2
        result = gameOfLife.checkLowerRightIsAlive(startingMatrix, row, col)

        assertThat(result).isFalse()
    }

    @Test
    fun `checkDownIsAlive checks the status of the cell below`() {
        row = 0
        col = 1
        var result = gameOfLife.checkDownIsAlive(startingMatrix, row, col)
        assertThat(result).isTrue()

        row = 2
        result = gameOfLife.checkLeftIsAlive(startingMatrix, row, col)
        assertThat(result).isFalse()
    }

    @Test
    fun `checkLowerLeftIsAlive checks the status of the cell to the lower left`() {
        row = 0
        col = 1
        var result = gameOfLife.checkLowerLeftIsAlive(startingMatrix, row, col)
        assertThat(result).isTrue()

        row = 1
        col = 0
        result = gameOfLife.checkLowerLeftIsAlive(startingMatrix, row, col)
        assertThat(result).isFalse()
    }

    @Test
    fun willAliveCellSurviveNextGen() {
        row = 0
        col = 1
        var result = gameOfLife.willAliveCellSurviveNextGen(startingMatrix, row, col)
        assertThat(result).isTrue()

        row = 1
        col = 1
        result = gameOfLife.willAliveCellSurviveNextGen(startingMatrix, row, col)
        assertThat(result).isFalse()

        row = 2
        col = 1
        result = gameOfLife.willAliveCellSurviveNextGen(startingMatrix, row, col)
        assertThat(result).isTrue()
    }

    @Test
    fun willDeadCellBecomeAliveNextGen() {
        row = 0
        col = 0
        var result = gameOfLife.willDeadCellBecomeAliveNextGen(startingMatrix, row, col)
        assertThat(result).isTrue()

        row = 2
        col = 0
        result = gameOfLife.willDeadCellBecomeAliveNextGen(startingMatrix, row, col)
        assertThat(result).isTrue()

        row = 1
        col = 2
        result = gameOfLife.willDeadCellBecomeAliveNextGen(startingMatrix, row, col)
        assertThat(result).isFalse()

        row = 2
        col = 0
        result = gameOfLife.willDeadCellBecomeAliveNextGen(startingMatrix, row, col)
        assertThat(result).isTrue()
    }

    @Test
    fun `nextGen should produce the next generation of cells`() {
        val expected = arrayOf(
            charArrayOf('o', 'o', ' '),
            charArrayOf('o', ' ', ' '),
            charArrayOf('o', 'o', 'o'))

        val result = gameOfLife.nextGen(startingMatrix)

        assertThat(result).isEqualTo(expected)

        /**
         *  ' ' 'o' ' '          'o' 'o' ' '
         *  'o' 'o' ' '    --->  'o' ' ' ' '
         *  ' ' 'o' 'o'          'o' 'o' 'o'
         * */
    }

    @Test
    fun `nextGen should produce the next generation of cells for a 3x4 matrix`() {
        val startingMatrix2 = arrayOf(
            charArrayOf('o', ' ', ' '),
            charArrayOf('o', 'o', 'o'),
            charArrayOf('o', ' ', ' '),
            charArrayOf(' ', 'o', 'o'))

        val expected = arrayOf(
            charArrayOf('o', ' ', ' '),
            charArrayOf('o', ' ', ' '),
            charArrayOf('o', ' ', ' '),
            charArrayOf(' ', 'o', ' '))

        val result = gameOfLife.nextGen(startingMatrix2)

        assertThat(result).isEqualTo(expected)

        /**
         *  'o' ' ' ' '          'o' ' ' ' '
         *  'o' 'o' 'o'    --->  'o' ' ' ' '
         *  'o' ' ' ' '          'o' ' ' ' '
         *  ' ' 'o' 'o'          ' ' 'o' ' '
         * */

    }
}