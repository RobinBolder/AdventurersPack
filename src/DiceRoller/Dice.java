package DiceRoller;

import java.util.Random;

/**
 * Class allowing you to create a dice with a certain number of sides and simulate the results of rolling it
 *
 * @author Robin Bolder
 * @version 1.2
 */
public class Dice {
    private int sides;

    /**
     * Main class used defining the type of dice used
     *
     * @param sides Integer number of sides on this dice (minimum of 2)
     * @since 1.0
     */
    public Dice(int sides) {
        if (sides > 1) {
            this.sides = sides;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Simple static method if only a single dice roll is required
     *
     * @param sides Integer number of sides on this dice
     * @return DiceResult class with the results
     * @since 1.0
     */
    public static DiceResult roll(int sides) {
        Dice dice = new Dice(sides);
        return dice.roll();
    }

    /**
     * Rolls the dice once and returns the result
     *
     * @return DiceResult class with the integer value of the result as well as a text String explaining the roll
     * @since 1.0
     */
    public DiceResult roll() {
        Random randomGenerator = new Random();

        int resultInt =  randomGenerator.nextInt(sides) + 1;
        String resultString = ("You rolled a d" + sides + " and got " + resultInt);
        return new DiceResult(resultInt, resultString);
    }

    /**
     * Rolls the dice with advantage
     * (Rolls it twice and returns the highest value)
     *
     * @return DiceResult class with an integer with the highest value rolled as well as a text String explaining the roll
     * @since 1.0
     */
    public DiceResult rollAdv() {
        int max;
        DiceResult output;
        DiceResult result1 = roll();
        DiceResult result2 = roll();

        if (result1.getTotal() > result2.getTotal()) {
            max = result1.getTotal();
            output = result1;
            output.add(result2);
        }
        else {
            max = result2.getTotal();
            output = result2;
            output.add(result1);
        }
        String resultString = ("You rolled a d" + sides + " with advantage and got " +
                result1.getTotal() + " and " + result2.getTotal() + ";\n" +
                "The result therefore is: " + max);
        output.setTotal(max);
        output.setText(resultString);
        return output;
    }

    /**
     * Rolls the dice with disadvantage
     * (Rolls it twice and returns the lowest value)
     *
     * @return DiceResult class with an integer with the lowest value rolled as well as a text String explaining the roll
     * @since 1.0
     */
    public DiceResult rollDis() {
        int min;
        DiceResult output;
        DiceResult result1 = roll();
        DiceResult result2 = roll();

        if (result1.getTotal() < result2.getTotal()) {
            min = result1.getTotal();
            output = result1;
            output.add(result2);
        }
        else {
            min = result2.getTotal();
            output = result2;
            output.add(result1);
        }
        String resultString = ("You rolled a d" + sides + " with disadvantage and got " +
                result1.getTotal() + " and " + result2.getTotal() + ";\n" +
                "The result therefore is: " + min);
        output.setTotal(min);
        output.setText(resultString);
        return output;
    }

    /**
     * Rolls the dice multiple times and returns the total value
     *
     * @param n Integer for the number of times the dice should be rolled (should be larger than 0)
     * @return DiceResult class with an integer with the total result as well as a text String explaining all the individual rolls
     * @since 1.0
     */
    public DiceResult rollMultiple(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        else if (n == 1) {
            return roll();
        }

        StringBuilder sb = new StringBuilder();
        DiceResult tmp;
        DiceResult total = new DiceResult();
        //int total = 0;
        sb.append("You rolled " + n + " d" + sides + "'s\n");
        for (int i = 0; i < n; i++) {
            tmp = roll();
            total.add(tmp);
            //total += tmp.getTotal();
            sb.append("You rolled a " + tmp.getTotal() + " on dice number " + (i+1) + "\n");
        }
        sb.append("Your total result is " + total.getTotal());
        //String text = sb.toString();
        total.setText(sb.toString());
        return total;//new DiceResult(total,text);
    }
}
