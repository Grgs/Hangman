
public class Main {

    public static void main(String[] args) {
        // write your code here
        GameState gameState = new GameState();

        gameState.setState(STATE.ONE);
        gameState.noMatch();
        for (STATE s : STATE.values()) {
            System.out.println(getAscii(s));
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


}
