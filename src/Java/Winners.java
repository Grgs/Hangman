import java.io.Serializable;
import java.util.ArrayList;

public class Winners implements Serializable {
    public ArrayList<Winner> winners;

    public Winners() {
        this.winners = new ArrayList<>();
    }


    public Winner getTopWinner() {
        return this.winners.get(0);
    }

    public void setWinner(Winner newWinner) {
        this.winners.add(newWinner);
        this.winners.sort((a, b) -> Integer.compare(b.score, a.score));
    }

    @Override
    public String toString() {
        return "Winners{" +
                "winners=" + winners +
                '}';
    }
}
