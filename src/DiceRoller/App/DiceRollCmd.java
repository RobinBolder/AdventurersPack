package DiceRoller.App;

import DiceRoller.Dice;
import DiceRoller.DiceResult;

import java.util.Scanner;

public class DiceRollCmd {

    public static void main(String[] args) {
        System.out.println("Welcome \n what kind of die do you want to roll?");
        Scanner sc = new Scanner(System.in);
        int response = sc.nextInt();
        DiceResult result = Dice.roll(response);
        //System.out.println("You rolled a " + result.getTotal() + " using your d" + response);
        System.out.println(result.getText());
    }
}
