import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static String getAscii(Properties properties, EnumState enumState) {
        return (String) properties.get("asciiArt" + enumState.ordinal());
    }

    private static void saveWinningScore(int secretWordLength, Scanner scanner, Instant startTime) {
        Instant endTime = Instant.now();
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileInputStream = new FileInputStream("winnerList.ser");
            objectInputStream = new ObjectInputStream(fileInputStream);

            scanner.nextLine();
            System.out.println("Enter your name:");
            String winnerName = scanner.nextLine();
            int score = (int) (10000000 * secretWordLength /
                    Duration.between(startTime, endTime).toMillis());
            System.out.printf("Winner name: %s\tScore: %d%n", winnerName, score);
            Winners winners = (Winners) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            Winner winner = new Winner(winnerName, score);
            winners.setWinner(winner);
            if (winner.isEqual(winners.getTopWinner()))
                System.out.println("You have the highest score!");

            System.out.printf("%nPrevious winners:%n");
            winners.winners.forEach(System.out::println);

            fileOutputStream = new FileOutputStream("winnerList.ser");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(winners);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error saving score: " + e.getLocalizedMessage());
        }

        System.out.printf("Time elapsed: %ss%n", Duration.between(startTime, endTime).toSeconds());
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
            IntStream.range(0, EnumState.values().length).forEach(n -> properties.setProperty(
                    "asciiArt" + n, "Game state " + n + "\n"));
            System.out.println("Could not open properties file: " + e.getLocalizedMessage());
        }

        GameState gameState = new GameState();
        SecretWord secretWord = new SecretWord();
//        System.out.println(secretWord.getWord()); //debug
        Scanner scanner = new Scanner(System.in);
        Instant startTime = Instant.now();

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
            saveWinningScore(secretWord.word.length(), scanner, startTime);
        } else {
            System.out.println("You Lost!");
        }
    }
}
