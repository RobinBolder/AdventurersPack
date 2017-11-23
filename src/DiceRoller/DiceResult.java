package DiceRoller;

import java.util.List;

public class DiceResult {
    private String text;
    private int total;
    private List allResults;

    public DiceResult(int result, String text) {
        this.total = result;
        this.text = text;
    }

    public int getTotal() {
        return total;
    }

    public String getText() {
        return text;
    }
}
