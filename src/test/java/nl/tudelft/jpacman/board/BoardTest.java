package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {

    /**
     * Test to create a 1x1 board and check if it is set up correctly.
     */
    @Test
    void createBoard() {
        // Create a 1x1 grid with a BasicSquare.
        Square square = new BasicSquare();
        Square[][] grid = {{square}};
        Board board = new Board(grid);

        // Assert that (0, 0) is within the board's boundaries.
        assertThat(board.withinBorders(0, 0)).isTrue();

        // Assert that the square at (0, 0) is the one we created.
        assertThat(board.squareAt(0, 0)).isEqualTo(square);

        // Assert that out-of-bounds coordinates are handled correctly.
        assertThat(board.withinBorders(-1, 0)).isFalse();  // Out of bounds: negative index
        assertThat(board.withinBorders(0, -1)).isFalse();  // Out of bounds: negative index
        assertThat(board.withinBorders(1, 0)).isFalse();   // Out of bounds: greater than grid size
        assertThat(board.withinBorders(0, 1)).isFalse();   // Out of bounds: greater than grid size
    }

    /**
     * Test to create a board with a null grid and verify that an exception is thrown.
     */
    @Test
    void createNullBoardTest() {
        // Attempt to create a board with a null grid and expect a NullPointerException.
        assertThrows(AssertionError.class, () -> new Board(null));
    }

    /**
     * Test to create a board with a grid containing a null Square and verify that it throws an exception.
     */
    @Test
    void createBoardWithNullSquareTest() {
        // Attempt to create a board with a null square in the grid.
        Square[][] grid = {{null}};
        assertThrows(AssertionError.class, () -> new Board(grid));
    }

    /**
     * Test for a board with multiple squares to check boundary conditions.
     */
    @Test
    void multipleSquareBoardTest() {
        // Create a 2x2 grid of BasicSquares.
        Square square1 = new BasicSquare();
        Square square2 = new BasicSquare();
        Square[][] grid = {
            {square1, square2},
            {square2, square1}
        };
        Board board = new Board(grid);

        // Assert that all within-border coordinates are correctly recognized.
        assertThat(board.withinBorders(0, 0)).isTrue();
        assertThat(board.withinBorders(0, 1)).isTrue();
        assertThat(board.withinBorders(1, 0)).isTrue();
        assertThat(board.withinBorders(1, 1)).isTrue();

        // Assert that the squares are accessible as expected.
        assertThat(board.squareAt(0, 0)).isEqualTo(square1);
        assertThat(board.squareAt(0, 1)).isEqualTo(square2);
        assertThat(board.squareAt(1, 0)).isEqualTo(square2);
        assertThat(board.squareAt(1, 1)).isEqualTo(square1);

        // Test out-of-bounds access.
        assertThat(board.withinBorders(2, 0)).isFalse();
        assertThat(board.withinBorders(0, 2)).isFalse();
        assertThat(board.withinBorders(-1, 0)).isFalse();
        assertThat(board.withinBorders(0, -1)).isFalse();
    }

    /**
     * Test for handling of invalid coordinates in squareAt method.
     */
    @Test
    void squareAtInvalidCoordinatesTest() {
        // Create a simple 1x1 board.
        Square square = new BasicSquare();
        Square[][] grid = {{square}};
        Board board = new Board(grid);

        // Assert that accessing an invalid coordinate throws an IndexOutOfBoundsException.
        assertThrows(AssertionError.class, () -> board.squareAt(-1, 0));
        assertThrows(AssertionError.class, () -> board.squareAt(0, -1));
        assertThrows(AssertionError.class, () -> board.squareAt(1, 0));
        assertThrows(AssertionError.class, () -> board.squareAt(0, 1));
    }
}
