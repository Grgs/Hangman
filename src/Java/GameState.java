public class GameState {

    public GameStates gameStates;

    public GameState() {
        gameStates = GameStates.INITIAL;
    }

    public GameStates getState() {
        return gameStates;
    }

    public void setState(GameStates s) {
        this.gameStates = s;
    }

    public void noMatch() {
        switch (gameStates) {
            case INITIAL:
                setState(GameStates.ONE);
                break;
            case ONE:
                setState(GameStates.TWO);
                break;
            case TWO:
                setState(GameStates.THREE);
                break;
            case THREE:
            case OVER:
                setState(GameStates.OVER);
                break;
        }
    }
}
