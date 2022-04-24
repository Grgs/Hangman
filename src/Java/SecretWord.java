import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

public class SecretWord {
    String word;
    WordChar[] wordChars = new WordChar[3];
    WordMatch wordMatch;
    HashSet<Character> unmatchedChars = new HashSet<>();

    public SecretWord() {
        Random rnd = new Random();
//        WordsList wordsList = new WordsList();
        this.setWord(WordsList.wordList[rnd.nextInt(WordsList.wordList.length - 1)]);
        this.wordMatch = WordMatch.NO_MATCH;
    }

    public void checkChar(char c) {
        this.wordMatch = WordMatch.NO_MATCH;
        for (int i = 0; i < 3; i++) {
            if (this.wordChars[i].c == c) {
                this.wordChars[i].revealed = true;
                this.wordMatch = WordMatch.PART_MATCH;
            }
        }
        if (Arrays.stream(wordChars).allMatch(ch -> ch.revealed)) {
            this.wordMatch = WordMatch.ALL_MATCH;
        }
        if (this.wordMatch.equals(WordMatch.NO_MATCH)) {
            this.unmatchedChars.add(c);
        }
    }

    public String displayMatchedCharacters() {
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
