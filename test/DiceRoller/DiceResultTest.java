package DiceRoller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiceResultTest {
    private int total;
    private String text;
    private DiceResult testResult;
    private DiceResult testResultEmpty;

    @BeforeEach
    void setUp() {
        total = 4;
        text = "You rolled a 4";
        testResultEmpty = new DiceResult();
        testResult = new DiceResult(total,text);
    }

    @Test
    void getTotal1() {
        assertEquals(4,testResult.getTotal(),"Incorrect value returned");
    }

    @Test
    void getTotal2() {
        assertEquals(0,testResultEmpty.getTotal(),"Empty DiceResult should have value 0");
    }

    @Test
    void getText1() {
        assertEquals("",testResultEmpty.getText(),"Empty DiceResult should contain an empty string");
    }

    @Test
    void getText2() {
        assertEquals("You rolled a 4",testResult.getText(),"getText did not return the correct string");
    }

    @Test
    void addCheckTotal() {
        testResult.add(new DiceResult(2,"test"));
        assertEquals(6,testResult.getTotal(),"Incorrect summation of added DiceResult values");
    }

    @Test
    void addCheckText1() {
        testResult.add(new DiceResult(2,"test"));
        assertEquals("You rolled a 4\ntest",testResult.getText(),"Incorrect concatenation of DiceResult strings");
    }
    @Test
    void addCheckText2() {
        testResultEmpty.add(new DiceResult(2,"test"));
        assertEquals("test",testResultEmpty.getText(),"Incorrect String returned when adding a new DiceResult to an empty DiceResult Object");
    }

    @Test
    void getAllValues1() {
        assertTrue(testResult.getAllValues() instanceof ArrayList,"getAllValues Should return an ArrayList");
    }

    @Test
    void getAllValues2() {
        List<Integer> testList = testResult.getAllValues();
        int size = testList.size();
        assertEquals(1,size,"There should be 1 value in the list");
    }

    @Test
    void getAllValues3() {
        List<Integer> testList = testResult.getAllValues();
        int value = testList.get(0);
        assertEquals(4,value,"The list returned the wrong value");
    }

    @Test
    void addCheckValues() {
        testResult.add(new DiceResult(2,"test"));
        List<Integer> testList = testResult.getAllValues();
        int value = testList.get(1);
        assertEquals(2,value,"The list returned the wrong value");
    }

    @Test
    void setText() {
        String sampleText = "blabla";
        testResult.setText(sampleText);
        assertEquals(sampleText,testResult.getText(),"The text should be overwritten by the new text");
    }

    @Test
    void setTotal() {
        int newTotal = 110;
        testResult.setTotal(newTotal);
        assertEquals(newTotal,testResult.getTotal(),"The total should be overwritten by the new total");
    }

    @Test
    void addModifier1() {
        int modifier = 7;
        testResult.addModifier(modifier);
        assertEquals(total + modifier, testResult.getTotal(),"The totals don't match");
    }

    @Test
    void addModifier2() {
        int modifier = 0;
        testResult.addModifier(modifier);
        assertEquals(total + modifier, testResult.getTotal(),"The totals don't match");
    }

    @Test
    void addModifier3() {
        int modifier = -3;
        testResult.addModifier(modifier);
        assertEquals(total + modifier, testResult.getTotal(),"The totals don't match");
    }


}