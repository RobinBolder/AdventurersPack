package DiceRoller.App.view;


import DiceRoller.App.DiceRollApp;
import DiceRoller.Dice;
import DiceRoller.DiceResult;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


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
        Dice d20 = new Dice(20);
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
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.initOwner(DiceRollApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }

        int modifier = Integer.parseInt(d20Modifier.getText());
        //TODO: Add modifier to value --> in DiceResult and/or Dice class

        outputTextField.setText(result.getText());

    }


}
