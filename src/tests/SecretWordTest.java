import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SecretWordTest {
    SecretWord secretWord;

    @BeforeEach
    void setUp() {
        secretWord = new SecretWord();
        secretWord.setWord("abc");

    }

    @Test
    void setWord() {
        assertAll(
                () -> assertEquals('a', secretWord.wordChars.get(0).c),
                () -> assertEquals(false, secretWord.wordChars.get(0).revealed),
                () -> assertEquals('b', secretWord.wordChars.get(1).c),
                () -> assertEquals(false, secretWord.wordChars.get(0).revealed),
                () -> assertEquals('c', secretWord.wordChars.get(2).c),
                () -> assertEquals(false, secretWord.wordChars.get(0).revealed)
        );
        secretWord.setWord("xyz");
        assertAll(
                () -> assertEquals('x', secretWord.wordChars.get(0).c),
                () -> assertEquals(false, secretWord.wordChars.get(0).revealed),
                () -> assertEquals('y', secretWord.wordChars.get(1).c),
                () -> assertEquals(false, secretWord.wordChars.get(0).revealed),
                () -> assertEquals('z', secretWord.wordChars.get(2).c),
                () -> assertEquals(false, secretWord.wordChars.get(0).revealed)
        );
    }

    @Test
    void checkChar() {
        secretWord.checkChar('a');
        assertEquals(WordMatch.PART_MATCH, secretWord.wordMatch);
        secretWord.checkChar('x');
        assertEquals(WordMatch.NO_MATCH, secretWord.wordMatch);
        secretWord.checkChar('c');
        assertEquals(WordMatch.PART_MATCH, secretWord.wordMatch);
        secretWord.checkChar('b');
        assertEquals(WordMatch.ALL_MATCH, secretWord.wordMatch);
    }

    @Test
    void displayMatchedCharacters() {
        ArrayList<String> stringArrayList = new ArrayList<>(
                List.of(new String[]{"_", "_", "_"}));
        String displayWord = secretWord.displayMatchedCharacters();
        assertEquals(stringArrayList.toString(), displayWord);
        stringArrayList = new ArrayList<>(List.of(new String[]{"_", "b", "_"}));
        secretWord.checkChar('b');
        assertEquals(stringArrayList.toString(), secretWord.displayMatchedCharacters());
        stringArrayList = new ArrayList<>(List.of(new String[]{"_", "b", "_"}));
        secretWord.checkChar('x');
        assertEquals(stringArrayList.toString(), secretWord.displayMatchedCharacters());
        stringArrayList = new ArrayList<>(List.of(new String[]{"_", "b", "_"}));
        secretWord.checkChar('b');
        assertEquals(stringArrayList.toString(), secretWord.displayMatchedCharacters());
        stringArrayList = new ArrayList<>(List.of(new String[]{"_", "b", "c"}));
        secretWord.checkChar('c');
        assertEquals(stringArrayList.toString(), secretWord.displayMatchedCharacters());
        stringArrayList = new ArrayList<>(List.of(new String[]{"a", "b", "c"}));
        secretWord.checkChar('a');
        assertEquals(stringArrayList.toString(), secretWord.displayMatchedCharacters());
        stringArrayList = new ArrayList<>(List.of(new String[]{"a", "b", "c"}));
        secretWord.checkChar('b');
        assertEquals(stringArrayList.toString(), secretWord.displayMatchedCharacters());
    }

    @Test
    void getWord() {
        assertEquals("abc", secretWord.getWord());
    }


}