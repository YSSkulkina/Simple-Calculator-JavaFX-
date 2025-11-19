package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML private Button b0;
    @FXML private Button b1;
    @FXML private Button b2;
    @FXML private Button b3;
    @FXML private Button b4;
    @FXML private Button b5;
    @FXML private Button b6;
    @FXML private Button b7;
    @FXML private Button b8;
    @FXML private Button b9;
    @FXML private Button clearButton;
    @FXML private Button divButton;
    @FXML private Button multButton;
    @FXML private Button pointButton;
    @FXML private Button minusButton;
    @FXML private Button plusButton;
    @FXML private Button resultButton;
    @FXML private TextField textArea;

    private Double num1, num2;
    public enum BiOperatorModes { normal, add, minus, multiply, divide }
    private BiOperatorModes mode = BiOperatorModes.normal;
    private double result = 0;

    // Общий метод для обработки нажатий цифр
    @FXML void onClickDigitButton(ActionEvent event) {
        Button source = (Button) event.getSource();
        textArea.appendText(source.getText());
    }
    @FXML
    void onClickClearButton(ActionEvent event) {
        textArea.clear();
        num1=null;
        num2=null;
        mode = BiOperatorModes.normal;
    }

    @FXML
    void onClickPointButton(ActionEvent event) {
        String currentText = textArea.getText();
        if (!currentText.contains(".")) {
            textArea.appendText(".");
        }
    }

    @FXML
    void onClickPlusButton(ActionEvent event) {
        mode = BiOperatorModes.add;
        getNum1();
    }

    @FXML
    void onClickDivButton(ActionEvent event) {
        mode = BiOperatorModes.divide;
        getNum1();
    }

    @FXML
    void onClickMultButton(ActionEvent event) {
        mode = BiOperatorModes.multiply;
        getNum1();
    }

    @FXML
    void onClickMinusButton(ActionEvent event) {
        mode = BiOperatorModes.minus;
        getNum1();
    }

    @FXML
    void onClickResultButton(ActionEvent event) {
        if (num1 != null && !textArea.getText().isEmpty()) {
            try {
                num2 = Double.parseDouble(textArea.getText());

                switch (mode) {
                    case add:
                        result = num1 + num2;
                        break;
                    case minus:
                        result = num1 - num2;
                        break;
                    case multiply:
                        result = num1 * num2;
                        break;
                    case divide:
                        if (num2 == 0) {
                            textArea.setText("На 0 делить нельзя!");
                            return;
                        }
                        result = num1 / num2;
                        break;
                    default:
                        return;
                }

                textArea.setText(formatNumber(result));
                mode = BiOperatorModes.normal;
            } catch (NumberFormatException e) {
                textArea.setText("Ошибка ввода");
            }
        }
    }

    public static String formatNumber(double value) {
        return value == (long) value ? String.format("%d", (long) value) : String.valueOf(value);
    }

    public void getNum1() {
        String currentText = textArea.getText();
        if (!currentText.isEmpty()) {
            try {
                num1 = Double.parseDouble(currentText);
                textArea.clear();
            } catch (NumberFormatException e) {
                textArea.setText("Ошибка ввода");
            }
        }
    }
}