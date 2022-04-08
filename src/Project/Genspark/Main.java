package Project.Genspark;

public class Main {

    public enum STATE {
        INITIAL, ONE, TWO, THREE, OVER, WON
    }

    public static class GameState {
        STATE state;

        public void GameState() {
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

    public static String getAscii(STATE state) {
        String asciiTemplate = "H A N G M A N %n" +
                " +----+ %n" +
                "%s   | %n" +
                "%s   | %n" +
                "%s   | %n" +
                "%s   | %n" +
                "     ===%n";
        switch (state) {
            case INITIAL:
                return String.format(asciiTemplate, "   ", "   ", "   ", "   ");
            case ONE:
                return String.format(asciiTemplate, " O ", "   ", "   ", "   ");
            case TWO:
                return String.format(asciiTemplate, " O ", "/|\\", "   ", "   ");
            case THREE:
                return String.format(asciiTemplate, " O ", "/|\\", " | ", "   ");
            case OVER:
                return String.format(asciiTemplate, " O ", "/|\\", " | ", "/ \\");
            default:
                return "";
        }
    }

    public static void main(String[] args) {
        // write your code here
        GameState gameState = new GameState();

        gameState.setState(STATE.ONE);
        gameState.noMatch();
        for(STATE s: STATE.values()){
            System.out.println(getAscii(s));
        }
    }
}
