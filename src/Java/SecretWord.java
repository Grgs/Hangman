import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SecretWord {
    String word;
    ArrayList<WordChar> wordChars;
    WordMatch wordMatch;
    HashSet<Character> unmatchedChars = new HashSet<>();

    public SecretWord() {
        wordChars = new ArrayList<>();
        this.setWord(this.getARandomWord());
        this.wordMatch = WordMatch.NO_MATCH;
    }

    public String getARandomWord() {
        File file = new File("english.txt");
        try {
            Scanner scanner = new Scanner(file);
            Random random = new Random();
            int randNumber = random.nextInt(2047);
            IntStream.range(0, randNumber).forEach((a) -> scanner.nextLine());
            return scanner.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
            return "error";
        }
    }

    public void checkChar(char c) {
        this.wordMatch = WordMatch.NO_MATCH;
        for (WordChar wordChar : this.wordChars) {
            if (wordChar.c == c) {
                wordChar.revealed = true;
                this.wordMatch = WordMatch.PART_MATCH;
            }
        }
        if (wordChars.stream().allMatch(ch -> ch.revealed)) {
            this.wordMatch = WordMatch.ALL_MATCH;
        }
        if (this.wordMatch.equals(WordMatch.NO_MATCH)) {
            this.unmatchedChars.add(c);
        }
    }

    public String displayMatchedCharacters() {
        return wordChars.stream().map(ch -> ch.revealed ? ch.c : "_").
                collect(Collectors.toList()).toString();
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
        this.wordChars = new ArrayList<>();
        for (char c : this.word.toCharArray()) {
            this.wordChars.add(new WordChar(c, false));
        }
    }

}
