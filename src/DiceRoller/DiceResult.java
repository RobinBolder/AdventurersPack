package DiceRoller;

//import java.util.List;

/**
 * Simple container class to store the results of a dice roll or a series of rolls, both in numerical value and as
 * a text string telling the user what happened.
 *
 * @author Robin Bolder
 * @version 1.0
 */
public class DiceResult {
    private String text;
    private int total;
    //private List allResults;  // Can be used to store a complete list of all the different roll values

    /**
     * Main container constructor
     * @param result    Integer value of the total dice result (or total of multiple dice rolls)
     * @param text      String telling the user what exactly happened during the (series of) rolls
     * @since 1.0
     */
    public DiceResult(int result, String text) {
        this.total = result;
        this.text = text;
    }

    /**
     * @return Integer value of the total dice result
     * @since 1.0
     */
    public int getTotal() {
        return total;
    }

    /**
     * @return String explaining the full roll history
     * @since 1.0
     */
    public String getText() {
        return text;
    }
}
