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
        assertEquals(GameStates.ONE, gameState.state);
        gameState.noMatch();
        assertEquals(GameStates.TWO, gameState.state);
        gameState.noMatch();
        assertEquals(GameStates.THREE, gameState.state);
        gameState.noMatch();
        assertEquals(GameStates.OVER, gameState.state);
        gameState.noMatch();
        assertEquals(GameStates.OVER, gameState.state);
    }

    @Test
    void getState() {
        assertEquals(GameStates.INITIAL, gameState.getState());
        gameState.state = GameStates.ONE;
        assertEquals(GameStates.ONE, gameState.getState());
    }

    @Test
    void setState() {
        assertEquals(GameStates.INITIAL, gameState.state);
        gameState.setState(GameStates.ONE);
        assertEquals(GameStates.ONE, gameState.state);
    }

}