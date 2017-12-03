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
    private TextField d20Modifier;
    @FXML
    private TextField d4Number;
    @FXML
    private TextField d4Modifier;
    @FXML
    private RadioButton advantage;
    @FXML
    private RadioButton normal;
    @FXML
    private RadioButton disadvantage;

    private ToggleGroup d20Radios;

    private DiceRollApp diceRollApp;
    private Dice d20 = new Dice(20);
    private Dice d4 = new Dice(4);

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

        outputTextField.setText(result.getText());

    }

    @FXML
    private void handleD4() {
        doRoll(d4, d4Number, d4Modifier);
    }

    private void doRoll(Dice dice, TextField nRolls, TextField modifier) {
        DiceResult result = doBaseRoll(dice, nRolls);
        result = doModifier(result, modifier);
        outputTextField.setText(result.getText());
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

    private void alertHandler(String title, String header, String context) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        //alert.initOwner(diceRollApp.getPrimaryStage());
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);

        alert.showAndWait();
    }


}
