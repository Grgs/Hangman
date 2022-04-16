import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class SecretWord {
    String word;
    WordChar[] wordChars = new WordChar[3];
    WordState wordState;

    public SecretWord() {
        Random rnd = new Random();
        Words words = new Words();
        this.setWord(words.wordList[rnd.nextInt(words.wordList.length - 1)]);
        this.wordState = WordState.NO_MATCH;
    }

    public void checkChar(char c) {
        this.wordState = WordState.NO_MATCH;
        for (int i = 0; i < 3; i++) {
            if (this.wordChars[i].c == c) {
                this.wordChars[i].revealed = true;
                this.wordState = WordState.PART_MATCH;
            }
        }
        if (Arrays.stream(wordChars).allMatch(ch -> ch.revealed)) {
            this.wordState = WordState.ALL_MATCH;
        }
    }

    public String displayWord() {
        return Arrays.stream(wordChars).map(ch -> ch.revealed ? ch.c : "_").
                collect(Collectors.toList()).toString();
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
        this.wordChars[0] = new WordChar(this.word.charAt(0), false);
        this.wordChars[1] = new WordChar(this.word.charAt(1), false);
        this.wordChars[2] = new WordChar(this.word.charAt(2), false);
    }

}
