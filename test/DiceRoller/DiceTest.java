package DiceRoller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
    private Dice d20;
    private Dice d2;

    @BeforeEach
    void setUp() {
    d20 = new Dice(20);
    d2 = new Dice(2);
    }

    @Test
    void DiceInvalidOneSide() {
        assertThrows(IllegalArgumentException.class,() -> new Dice(0));
    }

    @Test
    void DiceInvalidZeroSides() {
        assertThrows(IllegalArgumentException.class,() -> new Dice(0));
    }

    @Test
    void DiceInvalidNegativeSides() {
        assertThrows(IllegalArgumentException.class,() -> new Dice(-5));
    }

    @Test
    void roll1() {
        for (int i = 0; i < 100; i++) {
            DiceResult ans = d2.roll();
            int value = ans.getTotal();
            assertTrue((value==1 || value ==2),"Result out of bounds");
        }
    }

    @Test
    void roll2() {
        DiceResult test = d2.roll();
        String ans1 = "You rolled a d2 and got 1";
        String ans2 = "You rolled a d2 and got 2";
        assertTrue((test.getText().equals(ans1) || test.getText().equals(ans2)),"When input is 1, it should behave like the normal roll() method");
    }

    @Test
    void rollAbstract() {
        for (int i = 0; i < 100; i++) {
            DiceResult ans = Dice.roll(2);
            int value = ans.getTotal();
            assertTrue((value==1 || value ==2),"Result out of bounds");
        }
    }

    @Test
    void rollAdv() {
    }

    @Test
    void rollDis() {
    }

    @Test
    void rollMultiple1() {
        assertThrows(IllegalArgumentException.class,() -> d2.rollMultiple(-2));
    }

    @Test
    void rollMultiple2() {
        DiceResult test = d2.rollMultiple(1);
        String ans1 = "You rolled a d2 and got 1";
        String ans2 = "You rolled a d2 and got 2";
        assertTrue((test.getText().equals(ans1) || test.getText().equals(ans2)),"When input is 1, it should behave like the normal roll() method");
    }

    @Test
    void rollMultiple3() {
        int nRolls = 5;
        DiceResult test = d2.rollMultiple(nRolls);
        List testList = test.getAllValues();
        assertEquals(nRolls,testList.size(),"Incorrect number of rolls");
    }

    @Test
    void rollMultiple4() {
        int nRolls = 5;
        DiceResult test = d20.rollMultiple(nRolls);
        List testList = test.getAllValues();
        int total = 0;
        for (Object element : testList) {
            total += (int) element;
        }
        assertEquals(total,test.getTotal(),"Incorrect total value");
    }

}