package DiceRoller;

//import java.util.List;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple container class to store the results of a dice roll or a series of rolls, both in numerical value and as
 * a text string telling the user what happened.
 *
 * @author Robin Bolder
 * @version 1.3
 */
public class DiceResult {
    private String text;
    private int total;
    private List<Integer> allValues;
    //private List allResults;  // Can be used to store a complete list of all the different DiceResults combined in the total
    //TODO add a list with all the individual DiceResults that were added/combined into this final DiceResult

    /**
     * Main container constructor
     * @param result    Integer value of the total dice result (or total of multiple dice rolls)
     * @param text      String telling the user what exactly happened during the (series of) rolls
     * @since 1.0
     */
    public DiceResult(int result, String text) {
        this.total = result;
        this.text = text;
        this.allValues = new ArrayList<>();
        allValues.add(result);
    }

    /**
     * Additional constructor for creating empty DiceResult objects
     */
    public DiceResult() {
        this.total = 0;
        this.text = "";
        this.allValues = new ArrayList<>();
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

    /**
     * @return An ArrayList containing all the values
     * @since 1.1
     */
    public List getAllValues() { return allValues; }

    /**
     * Add the values of another dice roll to this object
     * @param additionalResult DiceResult object of which the values will be added to the existing one
     * @since 1.1
     */
    public void add(DiceResult additionalResult) {
        for (Object other : additionalResult.getAllValues()) {
            /*if (!(other instanceof Integer)){
                throw new IllegalArgumentException();
            }*/
            Integer otherValue = (Integer) other;
            this.allValues.add(otherValue);
        }

        this.total += additionalResult.getTotal();
        if (this.text.equals("")) {
            this.text = additionalResult.getText();
        }
        else{
            this.text += "\n" + additionalResult.getText();
        }
    }

    /**
     * Overwrite the original text string with new text
     * @param newText String containing the new text
     * @since 1.1
     */
    public void setText(String newText) {
        this.text = newText;
    }

    /**
     * Overwrite the total value
     * @param newTotal integer containing the new total
     * @since 1.2
     */
    public void setTotal(int newTotal) {
        this.total = newTotal;
    }

    /**
     * Adding the value of a modifier to the total and the text
     * @param modifier integer containing the modifier value
     * @since 1.3
     */
    public void addModifier(int modifier) {
        this.total += modifier;
        this.text += "\nWith a modifier of " + modifier + " the new total became" + total;
    }
}
