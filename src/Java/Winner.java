import java.io.Serializable;
import java.util.Objects;

public class Winner implements Serializable {
    final public String name;
    final public int score;

    public Winner(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public boolean isEqual(Winner otherWinner) {
        return Objects.equals(this.name, otherWinner.name) && this.score == otherWinner.score;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
