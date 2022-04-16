import java.util.Scanner;

public class Main {

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
        SecretWord secretWord = new SecretWord();
        System.out.println(secretWord.getWord()); //debug
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(getAscii(gameState.state));
            System.out.println("Guess a letter:");
            String input = scanner.nextLine();
            secretWord.checkChar(input.charAt(0));
            switch (secretWord.wordState) {
                case PART_MATCH:
                    System.out.println("Matched: " + input.charAt(0));
                    break;
                case NO_MATCH:
                    gameState.noMatch();
                    break;
                case ALL_MATCH:
                    gameState.setState(STATE.WON);
                    break;
            }

            System.out.println(secretWord.displayWord());
        } while (gameState.getState() != STATE.WON && gameState.getState() != STATE.OVER);

        System.out.println("The Secret word is: " + secretWord.word);

        if (gameState.getState().equals(STATE.WON)) {
            System.out.println("You Win!");
        } else {
            System.out.println("You Lost!");
        }

    }

}
