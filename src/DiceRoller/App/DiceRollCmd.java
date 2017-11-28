package DiceRoller.App;

import DiceRoller.Dice;
import DiceRoller.DiceResult;

import java.util.Scanner;

/**
 * Simple command-line application allowing players of DnD and other games that require dice to be rolled to make
 * dice rolls without the need to carry around a large sack of dice.
 *
 * @author Robin Bolder
 * @version 1.0
 */
public class DiceRollCmd {
    /**
     * Main menu allowing access to all functionality
     *
     * @param args ...
     */
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
                        rollAttack(sc);
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

    /**
     * Sub-menu for rolling d20's
     *
     * @param sc Scanner class for reading values from the input
     * @since 1.0
     */
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

    /**
     * Sub-menu for rolling non-d20 dice
     *
     * @param sc Scanner class for reading values from the input
     * @since 1.0
     */
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

    /**
     * Sub-menu for rolling standard, non d20 type dice (d4, d6, d8, d10, d12 and d100)
     *
     * @param sc Scanner class for reading values from the input
     * @since 1.0
     */
    private static void rollStandard(Scanner sc) {
        Dice dice = getDiceType(sc);
        int number = getNumber(sc);
        DiceResult result = dice.rollMultiple(number);
        System.out.println(result.getText());
    }

    /**
     * Sub-menu for rolling non-standard dice.
     * The choice for the number of sides on this custom dice is left up to the user.
     *
     * @param sc Scanner class for reading values from the input
     * @since 1.0
     */
    private static void rollCustom(Scanner sc) {
        Dice dice = getnSides(sc);
        int number = getNumber(sc);
        DiceResult result = dice.rollMultiple(number);
        System.out.println(result.getText());
    }

    /**
     * Sub-menu for choosing the type of standard, non d20 type dice to roll (d4, d6, d8, d10, d12 and d100)
     *
     * @param sc Scanner class for reading values from the input
     * @return Dice class of the appropriate type (number of sides)
     * @since 1.0
     */
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

    /**
     * Prompt for asking how many of the selected dice the user would like to roll at once
     *
     * @param sc Scanner class for reading values from the input
     * @return Integer of the number of dice to throw
     */
    private static int getNumber(Scanner sc) {
        int response;
        System.out.println("How many of these dice do you want to roll?");
        do {
            try {
                 response = sc.nextInt();
                 if (response > 0) {
                     return response;
                 }
                 else {
                     System.out.println("You can only roll a positive number of dice");
                     throw new IllegalArgumentException();
                 }
            } catch (Exception ex) {
                System.out.println("That was not a valid answer, try again!");
            }
        } while (true);
    }

    /**
     * Prompt for asking how many sides the custom dice should have
     *
     * @param sc Scanner class for reading values from the input
     * @return Dice class with the selected number of sides
     */
    private static Dice getnSides(Scanner sc) {
        int response;
        System.out.println("How many sides do you want your dice to have?");
        do {
            try {
                response = sc.nextInt();
                if (response > 1) {
                    return new Dice(response);
                }
                else {
                    System.out.println("A dice should have at least 2 sides");
                    throw new IllegalArgumentException();
                }
            } catch (Exception ex) {
                System.out.println("That was not a valid answer, try again!");
            }
        } while (true);
    }

    /**
     * Sub-menu for complete attack flow
     *
     * @param sc Scanner class for reading values from the input
     */
    private static void rollAttack(Scanner sc) {
        System.out.println("Making a weapon attack");
        System.out.println("What is your attack modifier?");
        int attackModifier = getModifier(sc);
        DiceResult acResult = rollAc(sc);
        System.out.println("Do you want to add any additional dice to this roll? (y/n)");
        if (getYN(sc)) {
            acResult = rollAdditional(sc,acResult);
        }
        int totalAc = acResult.getTotal() + attackModifier;
        System.out.println(acResult.getText());
        System.out.println("With a modifier of " + attackModifier + " the total result is: " + totalAc);
        System.out.println("Did that hit? (y/n)");
        if (getYN(sc)) {
            System.out.println("NICE!");
            Dice damageDice = getDiceType(sc);
            int number = getNumber(sc);
            DiceResult damageResult = damageDice.rollMultiple(number);
            System.out.println("Do you want to add any additional dice to this roll? (y/n)");
            if (getYN(sc)) {
                damageResult = rollAdditional(sc,damageResult);
            }
            System.out.println("What is your damage modifier?");
            int damageModifier = getModifier(sc);
            int totalDamage = damageResult.getTotal() + damageModifier;
            System.out.println(damageResult.getText());
            System.out.println("With a modifier of " + damageModifier + " you did a total damage of: " + totalDamage);
        }

    }

    /**
     * Prompt to get the dice roll modifiers
     *
     * @param sc Scanner class for reading values from the input
     * @return integer value of the modifier
     */
    private static int getModifier(Scanner sc) {
        do {
            try {
                return sc.nextInt();
            } catch (Exception ex) {
                System.out.println("That was not a valid answer, try again!");
            }
        } while (true);
    }

    /**
     * Prompt for determining if the attack roll should be made with advantage or disadvantage
     *
     * @param sc Scanner class for reading values from the input
     * @return DiceResult containing the result of the attack roll
     */
    private static DiceResult rollAc(Scanner sc) {
        Dice d20 = new Dice(20);
        char response;
        System.out.println("is it a (n)ormal roll, or do you have (a)dvantage or (d)isadvantage?");
        do {
            try {
                response = sc.next().charAt(0);
                switch (response) {
                    case 'a': return d20.rollAdv();
                    case 'd': return d20.rollDis();
                    case 'n': return d20.roll();
                    default: throw new IllegalArgumentException();
                }
            } catch (Exception ex) {
                System.out.println("That was not a valid answer, try again!");
            }
        } while (true);
    }

    /**
     * Prompt for getting yes/no answers
     *
     * @param sc Scanner class for reading values from the input
     * @return boolean: true for yes; false for no
     */
    private static boolean getYN(Scanner sc) {
        char response;
        do {
            try {
                response = sc.next().charAt(0);
                switch (response) {
                    case 'y':
                        return true;
                    case 'n':
                        return false;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (Exception ex) {
                System.out.println("That was not a valid answer, try again!");
            }
        } while (true);
    }

    /**
     * Add additional dice results to any roll
     *
     * @param sc Scanner class for reading values from the input
     * @return DiceResult containing the values of all additional rolls
     */
    private static DiceResult rollAdditional(Scanner sc, DiceResult original) {
        boolean answer;
        do {
            Dice tempDice = getDiceType(sc);
            int tempNumber = getNumber(sc);
            DiceResult tempResult = tempDice.rollMultiple(tempNumber);
            original.add(tempResult);

            System.out.println("Do you want to add any additional dice to this roll? (y/n)");
            answer = getYN(sc);
        } while (answer);
        return original;
    }

}
