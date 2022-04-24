import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameStateTest {
    GameState gameState;

    @BeforeEach
    void setUp() {
        gameState = new GameState();

    }

    @Test
    void noMatch() {
        gameState.noMatch();
        assertEquals(EnumState.ONE, gameState.state);
        gameState.noMatch();
        assertEquals(EnumState.TWO, gameState.state);
        gameState.setState(EnumState.FIVE);
        gameState.noMatch();
        assertEquals(EnumState.SIX, gameState.state);
        gameState.noMatch();
        assertEquals(EnumState.OVER, gameState.state);
    }

    @Test
    void getState() {
        assertEquals(EnumState.INITIAL, gameState.getState());
        gameState.state = EnumState.ONE;
        assertEquals(EnumState.ONE, gameState.getState());
    }

    @Test
    void setState() {
        assertEquals(EnumState.INITIAL, gameState.state);
        gameState.setState(EnumState.ONE);
        assertEquals(EnumState.ONE, gameState.state);
    }

}