public class GameState {

    public EnumState state;

    public GameState() {
        state = EnumState.INITIAL;
    }

    public EnumState getState() {
        return state;
    }

    public void setState(EnumState s) {
        this.state = s;
    }

    public void noMatch() {
        this.state = EnumState.values()[Math.min(this.state.ordinal() + 1, EnumState.OVER.ordinal())];
    }
}
