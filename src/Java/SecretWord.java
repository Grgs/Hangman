import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
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
        if (!this.word.contains(String.valueOf(c))) {
            this.unmatchedChars.add(c);
            this.wordMatch = WordMatch.NO_MATCH;
        } else {
            this.wordChars = (ArrayList<WordChar>) this.wordChars.stream().peek(w -> {
                if (w.c == c) {
                    w.revealed = true;
                }
            }).collect(Collectors.toList());
            if (wordChars.stream().allMatch(ch -> ch.revealed)) {
                this.wordMatch = WordMatch.ALL_MATCH;
            } else {
                this.wordMatch = WordMatch.PART_MATCH;
            }
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
        this.wordChars.addAll(Arrays.stream(this.word.split("")).
                map(c -> new WordChar(c.charAt(0), false)).collect(Collectors.toList()));
    }

}
