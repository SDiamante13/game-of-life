package com.diamante.gameoflife

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat


class UniverseTest {

    private val O = CellState.ALIVE
    private val X = CellState.DEAD

    private val original = arrayOf(
        arrayOf(X, O, X),
        arrayOf(O, O, X),
        arrayOf(X, O, O)
    )

    @Test
    fun `should store its initial state`() {
        val universe = Universe(original)

        val result = universe.getState()

        assertThat(result).isEqualTo(original)
    }

    @Test
    fun `should update cell`() {
        val universe = Universe(arrayOf(arrayOf(X)))

        universe.update()

        val result = universe.getState()

        assertThat(result[0][0]).isEqualTo(CellState.DEAD)
    }

    @Test
    fun `should update all cells`() {
        val expected = arrayOf(
            arrayOf(O, O, X),
            arrayOf(O, X, X),
            arrayOf(O, O, O)
        )

        val universe = Universe(original)

        universe.update()

        assertThat(universe.getState()).isEqualTo(expected)


        /**
         *  ' ' 'o' ' '          'o' 'o' ' '
         *  'o' 'o' ' '    --->  'o' ' ' ' '
         *  ' ' 'o' 'o'          'o' 'o' 'o'
         * */
    }

    @Test
    fun `should update the next generation of cells for a 3x4 matrix`() {
        val initialCellStates = arrayOf(
            arrayOf(O, X, X),
            arrayOf(O, O, O),
            arrayOf(O, X, X),
            arrayOf(X, O, O)
        )

        val expected = arrayOf(
            arrayOf(O, X, X),
            arrayOf(O, X, X),
            arrayOf(O, X, X),
            arrayOf(X, O, X)
        )

        val universe = Universe(initialCellStates)

        universe.update()

        assertThat(universe.getState()).isEqualTo(expected)

        /**
         *  'o' ' ' ' '          'o' ' ' ' '
         *  'o' 'o' 'o'    --->  'o' ' ' ' '
         *  'o' ' ' ' '          'o' ' ' ' '
         *  ' ' 'o' 'o'          ' ' 'o' ' '
         * */
    }
}