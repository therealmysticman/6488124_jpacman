package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test suite to confirm that {@link Unit}s correctly (de)occupy squares.
 *
 * @author Jeroen Roosen 
 *
 */
class OccupantTest {

    /**
     * The unit under test.
     */
    private Unit unit;

    /**
     * Resets the unit under test.
     */
    @BeforeEach
    void setUp() {
        unit = new BasicUnit();
    }

    /**
     * Asserts that a unit has no square to start with.
     */
    @Test
    void noStartSquare() {
        assertThat(unit.hasSquare()).isFalse();
    }

    /**
     * Tests that the unit indeed has the target square as its base after
     * occupation.
     */
    @Test
    void testOccupy() {
        Square square = new BasicSquare();

        // Occupy the square
        unit.occupy(square);

        // Assert that the unit is occupying the correct square
        assertThat(unit.getSquare()).isEqualTo(square);
        assertThat(square.getOccupants()).contains(unit);
    }

    /**
     * Test that the unit indeed has the target square as its base after
     * double occupation.
     */
    @Test
    void testReoccupy() {
        Square firstSquare = new BasicSquare();
        Square secondSquare = new BasicSquare();

        // Occupy the first square
        unit.occupy(firstSquare);
        assertThat(unit.getSquare()).isEqualTo(firstSquare);

        // Reoccupy with a different square
        unit.occupy(secondSquare);

        // Assert that the unit now occupies the second square
        assertThat(unit.getSquare()).isEqualTo(secondSquare);
        assertThat(secondSquare.getOccupants()).contains(unit);
        assertThat(firstSquare.getOccupants()).doesNotContain(unit);
    }
}
