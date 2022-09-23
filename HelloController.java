package com.company.hellofx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable
{
    @FXML
    public Label welcomeLabel;

    @FXML
    public ChoiceBox<String> choiceBox;

    @FXML
    public TextField userInput;

    @FXML
    public Button convertBtn;

    private static final String C_TO_F_TEXT ="Celsius to Fahrenheit";
    private static final String F_TO_C_TEXT ="Fahrenheit to Celsius";

    private boolean isC_TO_F= true;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        choiceBox.getItems().add(C_TO_F_TEXT);
        choiceBox.getItems().add(F_TO_C_TEXT);

        choiceBox.setValue(C_TO_F_TEXT);

        choiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->
        {
            if(newValue.equals(C_TO_F_TEXT))
            {
                isC_TO_F=true;
            }else
            {
                isC_TO_F=false;
            }
        });
          convertBtn.setOnAction(event->
          {
              convert();
          });

    }

    private void convert()
    {
        String input = userInput.getText();
        float enteredTemp =0.0f;
        try{
             enteredTemp = Float.parseFloat(input);
        }catch (Exception ex){
            warnUser();
            return;
        }

        float newTemp = 0.0f;
        if(isC_TO_F)     //if user has selected celsius to fahrenheit
        {
            newTemp = (enteredTemp * 9 / 5) + 32 ;
        }else           //else user has selected fahrenheit to celsius
        {
            newTemp = (enteredTemp - 32) * 5 / 9 ;
        }
        display(newTemp);
    }

    private void warnUser()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error Occurred");
        alert.setHeaderText("Invalid Temperature Entered");
        alert.setContentText("Please Enter a Valid Temperature");
        alert.show();
    }

    private void display(float newTemp)
    {
        String unit = isC_TO_F? " F" : " C";
        System.out.println("The new Temperature is: " + newTemp + unit);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setContentText("The new Temperature is: " + newTemp + unit);
        alert.show();
    }
}