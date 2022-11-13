package com.example.kyrsach2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button button;
    @FXML
    private TextField text1;
    @FXML
    private TextField text2;
    JOptionPane JOptionPane;
    static int i1 = 0;
    static int i2 = 0;
    double x0 = 2;
    double x1 = 10;
    double e = 0.001;


    @FXML
    void button_active(ActionEvent event) {

        i1 = Integer.parseInt(text1.getText());
        i2 = Integer.parseInt(text2.getText());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("answerWindow.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Scene scene = new Scene(root,600,400);
        Label label = (Label) scene.lookup("#label1");
        Stage stage = new Stage();
        stage.setScene(scene);

        if(comboBox.getSelectionModel().isEmpty()){

            javax.swing.JOptionPane.showMessageDialog(null, "Выберите метод");

        }else if(comboBox.getValue().equalsIgnoreCase("Метод касательных")){

            double x = method_newton(x0, e);
            String formattedDouble = String.format("%.4f", x);
            label.setText(formattedDouble);
            stage.show();

        }else if(comboBox.getValue().equalsIgnoreCase("Метод хорд")) {

            double x = method_chord(x0, x1, e);
            String formattedDouble = String.format("%.4f", x);
            label.setText(formattedDouble);
            stage.show();
        }
    }

    public static double method_chord (double x_prev, double x_curr, double e) {
        while(Math.abs(x_curr - x_prev) > e) {
            x_prev = x_curr - (x_curr - x_prev) * f(x_curr) / (f(x_curr) - f(x_prev));
            x_curr = x_prev - (x_prev - x_curr) * f(x_prev) / (f(x_prev) - f(x_curr));
        }
        return x_curr;
    }

    public static double f(double x){
        return Math.pow(x, 3) + i1 * x + i2;
    }

    public static double prf(double x) {
        return 3 * x * x - 7 * x + 0.5;
    }

    public static double method_newton(double a, double e) {
        double x = a;
        double razn;
        do {
            double xn = x - f(x) / prf(x);
            razn = Math.abs(xn - x);
            x = xn;
        } while (razn > e);

        return x - f(x) / prf(x);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        comboBox.setPromptText("Please select one");

        comboBox.getItems().addAll("Метод касательных", "Метод хорд");

    }
}
