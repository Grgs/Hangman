import java.util.ArrayList;

public class SecretWord {
    String word;
    ArrayList<WordChar> wordChars;
    public void secretWord(String word){
        this.word = word;
        wordChars.add(new WordChar(this.word.charAt(0), false));
        wordChars.add(new WordChar(this.word.charAt(1), false));
        wordChars.add(new WordChar(this.word.charAt(2), false));
    }

    public String getWord() {
        return word;
    }

}
