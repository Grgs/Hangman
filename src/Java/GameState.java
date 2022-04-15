public class GameState {

    public STATE state;

    public GameState() {
        state = STATE.INITIAL;
    }

    public void setState(STATE s) {
        state = s;
    }

    public void noMatch() {
        switch (state) {
            case INITIAL:
                setState(STATE.ONE);
                break;
            case ONE:
                setState(STATE.TWO);
                break;
            case TWO:
                setState(STATE.THREE);
                break;
            case THREE:
            case OVER:
                setState(STATE.OVER);
                break;
        }
    }
}
