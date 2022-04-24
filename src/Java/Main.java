import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static String getAscii(Properties properties, EnumState enumState) {
        return (String) properties.get("asciiArt" + enumState.ordinal());
    }

    public static void main(String[] args) {
        FileReader fileReader;
        Properties properties;
        properties = new Properties();
        try {
            fileReader = new FileReader("game.properties");
            properties.load(fileReader);
            fileReader.close();
        } catch (IOException e) {
            IntStream.range(0, EnumState.values().length).mapToObj(n -> properties.setProperty(
                    "asciiArt" + n, "Game state " + n + "\n"));
            System.out.println("Could not open properties file: " + e.getLocalizedMessage());
        }

        GameState gameState = new GameState();
        SecretWord secretWord = new SecretWord();
        System.out.println(secretWord.getWord()); //debug
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(getAscii(properties, gameState.getState()));
            System.out.println(secretWord.displayMatchedCharacters());
            System.out.println("Guess a letter:");
            if (!scanner.hasNext("[a-zA-Z]")) {
                scanner.nextLine();
                System.out.println("Invalid input!");
                continue;
            }
            char inputCharacter = scanner.next("[a-zA-Z]").toLowerCase(Locale.ROOT).charAt(0);
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
                    gameState.setState(EnumState.WON);
                    break;
            }

            System.out.println("Unmatched characters: " + secretWord.unmatchedChars);
        } while (gameState.getState() != EnumState.WON && gameState.getState() != EnumState.OVER);

        System.out.println(secretWord.displayMatchedCharacters());
        System.out.println("The Secret word is: " + secretWord.word);
        System.out.println(getAscii(properties, gameState.getState()));

        if (gameState.getState().equals(EnumState.WON)) {
            System.out.println("You Win!");
        } else {
            System.out.println("You Lost!");
        }
    }
}
