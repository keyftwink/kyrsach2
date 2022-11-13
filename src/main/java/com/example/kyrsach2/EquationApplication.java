package com.example.kyrsach2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EquationApplication extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(EquationApplication.class.getResource("mainMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Решение уравнений");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
                System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}