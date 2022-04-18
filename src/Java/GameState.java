public class GameState {

    public GameStates state;

    public GameState() {
        state = GameStates.INITIAL;
    }

    public GameStates getState() {
        return state;
    }

    public void setState(GameStates s) {
        this.state = s;
    }

    public void noMatch() {
        switch (state) {
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
