package DiceRoller;

import java.util.Random;

public class Dice {

    private int sides;

    public Dice(int sides) {
        this.sides = sides;
    }

    public static DiceResult roll(int sides) {
        Dice dice = new Dice(sides);
        return dice.roll();
    }

    public DiceResult roll() {
        Random randomGenerator = new Random();

        int resultInt =  randomGenerator.nextInt(sides) + 1;
        String resultString = ("You rolled a d" + sides + " and got " + resultInt);
        return new DiceResult(resultInt, resultString);
    }
}
