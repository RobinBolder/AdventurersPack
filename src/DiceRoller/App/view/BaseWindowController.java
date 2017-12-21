package DiceRoller.App.view;


import DiceRoller.App.DiceRollApp;
import DiceRoller.Dice;
import DiceRoller.DiceResult;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class BaseWindowController {

    @FXML
    private Label outputTextField;
    @FXML
    private RadioButton advantage;
    @FXML
    private RadioButton normal;
    @FXML
    private RadioButton disadvantage;
    @FXML
    private TextField d20Modifier;
    @FXML
    private TextField d4Modifier;
    @FXML
    private TextField d6Modifier;
    @FXML
    private TextField d8Modifier;
    @FXML
    private TextField d10Modifier;
    @FXML
    private TextField d12Modifier;
    @FXML
    private TextField d100Modifier;
    @FXML
    private TextField dOtherModifier;
    @FXML
    private TextField d4Number;
    @FXML
    private TextField d6Number;
    @FXML
    private TextField d8Number;
    @FXML
    private TextField d10Number;
    @FXML
    private TextField d12Number;
    @FXML
    private TextField d100Number;
    @FXML
    private TextField dOtherNumber;
    @FXML
    private TextField dOtherSides;
    @FXML
    private Label d20Result;
    @FXML
    private Label d4Result;
    @FXML
    private Label d6Result;
    @FXML
    private Label d8Result;
    @FXML
    private Label d10Result;
    @FXML
    private Label d12Result;
    @FXML
    private Label d100Result;
    @FXML
    private Label dOtherResult;

    @FXML
    private ScrollPane scroll;

    private ToggleGroup d20Radios;

    private DiceRollApp diceRollApp;
    private Dice d20 = new Dice(20);
    private Dice d4 = new Dice(4);
    private Dice d6 = new Dice(6);
    private Dice d8 = new Dice(8);
    private Dice d10 = new Dice(10);
    private Dice d12 = new Dice(12);
    private Dice d100 = new Dice(100);

    @FXML
    private void initialize() {
        d20Radios = new ToggleGroup();
        advantage.setToggleGroup(d20Radios);
        disadvantage.setToggleGroup(d20Radios);
        normal.setToggleGroup(d20Radios);
        normal.setSelected(true);
        normal.requestFocus();
        outputTextField.setText("Welcome");
    }

    @FXML
    private void handleD20() {
        //Dice d20 = new Dice(20);
        DiceResult result = new DiceResult();
        if (advantage.isSelected()) {
            result = d20.rollAdv();
        }
        else if (normal.isSelected()) {
            result = d20.roll();
        }
        else if (disadvantage.isSelected()) {
            result = d20.rollDis();
        }
        else {
            alertHandler("No Selection","No rolltype Selected","Please either normal, advantage or disadvantage");
        }

        doModifier(result, d20Modifier);

        addText(result.getText());

        d20Result.setText(Integer.toString(result.getTotal()));

    }

    @FXML
    private void handleD4() {
        doRoll(d4, d4Number, d4Modifier, d4Result);
    }

    @FXML
    private void handleD6() {
        doRoll(d6, d6Number, d6Modifier, d6Result);
    }

    @FXML
    private void handleD8() {
        doRoll(d8, d8Number, d8Modifier, d8Result);
    }

    @FXML
    private void handleD10() {
        doRoll(d10, d10Number, d10Modifier, d10Result);
    }

    @FXML
    private void handleD12() {
        doRoll(d12, d12Number, d12Modifier, d12Result);
    }

    @FXML
    private void handleD100() {
        doRoll(d100, d100Number, d100Modifier, d100Result);
    }

    @FXML
    private void handleDOther() {
        try {
            Dice dOther = new Dice(Integer.parseInt(dOtherSides.getText()));
            doRoll(dOther, dOtherNumber, dOtherModifier, dOtherResult);
        } catch (Exception e) {
            alertHandler("Incorrect Number of Rolls", "The given value is incorrect", "Please enter a positive integer");
        }

    }


    private void doRoll(Dice dice, TextField nRolls, TextField modifier, Label resultField) {
        DiceResult result = doBaseRoll(dice, nRolls);
        result = doModifier(result, modifier);
        addText(result.getText());
        resultField.setText(Integer.toString(result.getTotal()));
    }

    private DiceResult doBaseRoll(Dice dice, TextField nRolls) {
        //do {
            try {
                int rolls = Integer.parseInt(nRolls.getText());
                if (rolls > 0) {
                    return dice.rollMultiple(rolls);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                nRolls.selectAll();
                alertHandler("Incorrect Number of Rolls", "The given value is incorrect", "Please enter a positive integer");
                return new DiceResult();
            }
        //} while (true);
    }

    private DiceResult doModifier(DiceResult result, TextField modifier) {
        //do {
            if (modifier.getText() == null || modifier.getText().length() == 0) {
                return result;
            } else {
                try {
                    int mod = Integer.parseInt(modifier.getText());
                    if (mod != 0) {
                        result.addModifier(mod);
                    }
                    return result;
                } catch (Exception e) {
                    modifier.selectAll();
                    alertHandler("Incorrect Modifier", "The given modifier is incorrect", "Please enter an integer");
                    return result;
                }
            }
        //} while (true);
    }

    private void addText(String text) {
        outputTextField.setText( outputTextField.getText() + "\n\n" + text);
        scroll.setVvalue(1.0);
    }

    private void alertHandler(String title, String header, String context) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        //alert.initOwner(diceRollApp.getPrimaryStage());
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);

        alert.showAndWait();
    }


}
