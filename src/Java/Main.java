import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static String getAscii(GameStates gameStates) {
        String asciiTemplate = "H A N G M A N %n" +
                " +----+ %n" +
                "%s   | %n" +
                "%s   | %n" +
                "%s   | %n" +
                "%s   | %n" +
                "     ===%n";
        switch (gameStates) {
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
        GameState gameState = new GameState();
        SecretWord secretWord = new SecretWord();
//        System.out.println(secretWord.getWord()); //debug
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(getAscii(gameState.gameStates));
            System.out.println(secretWord.displayMatchedCharacters());
            System.out.println("Guess a letter:");
            char inputCharacter = getInputCharacter(scanner);
            secretWord.checkChar(inputCharacter);
            switch (secretWord.wordMatch) {
                case PART_MATCH:
                    System.out.println("Matched: " + inputCharacter);
                    break;
                case NO_MATCH:
                    gameState.noMatch();
                    System.out.println("\"" + inputCharacter + "\" did not match.");
                    break;
                case ALL_MATCH:
                    gameState.setState(GameStates.WON);
                    break;
            }

            System.out.println("Unmatched characters: " + secretWord.unmatchedChars);
        } while (gameState.getState() != GameStates.WON && gameState.getState() != GameStates.OVER);

        System.out.println("The Secret word is: " + secretWord.word);

        if (gameState.getState().equals(GameStates.WON)) {
            System.out.println("You Win!");
        } else {
            System.out.println("You Lost!");
        }

    }

    private static char getInputCharacter(Scanner scanner) {
        String inputString;
        do {
            inputString = scanner.nextLine();
            inputString = inputString.replaceAll("[^a-zA-Z]", "");
        } while (inputString.length() == 0);
        return inputString.toLowerCase(Locale.ROOT).charAt(0);
    }


}
