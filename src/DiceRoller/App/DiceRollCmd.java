package DiceRoller.App;

import DiceRoller.Dice;
import DiceRoller.DiceResult;

import java.util.Scanner;

public class DiceRollCmd {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int response;

        System.out.println("Welcome to the DiceRoller app!\n");

        do {
            System.out.println("What would you like to do?");
            System.out.println("1. Roll a d20");
            System.out.println("2. Roll different dice");
            System.out.println("3. Make an attack");
            System.out.println("4. Close the application");
            try {
                response = sc.nextInt();
                switch (response) {
                    case 1:
                        rollD20(sc);
                        break;
                    case 2:
                        rollOther(sc);
                        break;
                    case 3:
                        System.out.println("Sorry, this functionality is not yet added :(");
                        break;
                    case 4:
                        System.out.println("Goodbye");
                        break;
                    default:
                        System.out.println("That was not a valid answer, try again!");
                }
            }
            catch (Exception ex) {
                System.out.println("That was not a valid answer, try again!");
                response = 0;
            }

        } while (response!=4);

    }

    private static void rollD20(Scanner sc) {
        int response;
        DiceResult result;
        Dice d20 = new Dice(20);

        System.out.println("What type of d20 roll do you want to make?");
        System.out.println("1. Normal");
        System.out.println("2. Advantage");
        System.out.println("3. Disadvantage");
        System.out.println("4. Back to main menu");

        try {
            response = sc.nextInt();
            switch (response) {
                case 1:
                    result = d20.roll();
                    System.out.println(result.getText());
                    break;
                case 2:
                    result = d20.rollAdv();
                    System.out.println(result.getText());
                    break;
                case 3:
                    result = d20.rollDis();
                    System.out.println(result.getText());
                    break;
                case 4:
                    break;
                default:
                    System.out.println("That was not a valid answer, try again!");
            }
        } catch (Exception ex) {
            System.out.println("That was not a valid answer, try again!");
        }
    }

    private static void rollOther(Scanner sc) {
        int response;

        System.out.println("What type of dice do you want to roll?");
        System.out.println("1. Standard");
        System.out.println("2. Custom");
        System.out.println("3. Back to main menu");
        do {
            try {
                response = sc.nextInt();
                switch (response) {
                    case 1:
                        rollStandard(sc);
                        response = 3;
                        break;
                    case 2:
                        rollCustom(sc);
                        response = 3;
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("That was not a valid answer, try again!");
                }
            } catch (Exception ex) {
                System.out.println("That was not a valid answer, try again!");
                response = 0;
            }
        } while (response !=3);

    }

    private static void rollStandard(Scanner sc) {
        Dice dice = getDiceType(sc);
        int number = getNumber(sc);
        DiceResult result = dice.rollMultiple(number);
        System.out.println(result.getText());
    }

    private static void rollCustom(Scanner sc) {
        Dice dice = getnSides(sc);
        int number = getNumber(sc);
        DiceResult result = dice.rollMultiple(number);
        System.out.println(result.getText());
    }

    private static Dice getDiceType(Scanner sc) {
        int response;

        System.out.println("What type of dice do you want to roll?");
        System.out.println("1. d4");
        System.out.println("2. d6");
        System.out.println("3. d8");
        System.out.println("4. d10");
        System.out.println("5. d12");
        System.out.println("6. d100");
        System.out.println("7. Other");

        do {
            try {
                response = sc.nextInt();
                switch (response) {
                    case 1:
                        return new Dice(4);
                    case 2:
                        return new Dice(6);
                    case 3:
                        return new Dice(8);
                    case 4:
                        return new Dice(10);
                    case 5:
                        return new Dice(12);
                    case 6:
                        return new Dice(100);
                    case 7:
                        return getnSides(sc);
                    default:
                        System.out.println("That was not a valid answer, try again!");
                }
            } catch (Exception ex) {
                System.out.println("That was not a valid answer, try again!");
            }
        } while(true);
    }

    private static int getNumber(Scanner sc) {
        System.out.println("How many of these dice do you want to roll?");
        do {
            try {
                return sc.nextInt();
            } catch (Exception ex) {
                System.out.println("That was not a valid answer, try again!");
            }
        } while (true);
    }

    private static Dice getnSides(Scanner sc) {
        System.out.println("How many sides do you want your dice to have?");
        do {
            try {
                return new Dice(sc.nextInt());
            } catch (Exception ex) {
                System.out.println("That was not a valid answer, try again!");
            }
        } while (true);
    }

}
