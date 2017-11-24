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

    public DiceResult rollAdv() {
        int total;
        DiceResult result1 = roll();
        DiceResult result2 = roll();

        if (result1.getTotal() > result2.getTotal()) {
            total = result1.getTotal();
        }
        else {
            total = result2.getTotal();
        }
        String resultString = ("You rolled a d" + sides + " with advantage and got " +
                result1.getTotal() + " and " + result2.getTotal() + ";\n" +
                "The result therefore is: " + total);
        return new DiceResult(total, resultString);
    }

    public DiceResult rollDis() {
        int total;
        DiceResult result1 = roll();
        DiceResult result2 = roll();

        if (result1.getTotal() < result2.getTotal()) {
            total = result1.getTotal();
        }
        else {
            total = result2.getTotal();
        }
        String resultString = ("You rolled a d" + sides + " with disadvantage and got " +
                result1.getTotal() + " and " + result2.getTotal() + ";\n" +
                "The result therefore is: " + total);
        return new DiceResult(total, resultString);
    }

    public DiceResult rollMultiple(int n) {
        StringBuilder sb = new StringBuilder();
        DiceResult tmp;
        int total = 0;
        sb.append("You rolled " + n + " d" + sides + "'s\n");
        for (int i = 0; i < n; i++) {
            tmp = roll();
            total += tmp.getTotal();
            sb.append("You rolled a " + tmp.getTotal() + " on dice number " + (i+1) + "\n");
        }
        sb.append("Your total result is " + total);
        String text = sb.toString();
        return new DiceResult(total,text);
    }
}
