import java.util.Random;

public class SecretWord {
    String word;
    WordChar[] wordChars = new WordChar[3];

    public SecretWord() {
        Random rnd = new Random();
        Words words = new Words();
        this.setWord(words.wordList[rnd.nextInt(words.wordList.length - 1)]);

    }

    public boolean checkChar(char c) {
        for (int i = 0; i <= 3; i++) {
            if (wordChars[i].c == c) {
                wordChars[i].revealed = true;
            }
        }
        return this.word.contains("" + c);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
        wordChars[0] = new WordChar(this.word.charAt(0), false);
        wordChars[1] = new WordChar(this.word.charAt(1), false);
        wordChars[2] = new WordChar(this.word.charAt(2), false);
    }

}
