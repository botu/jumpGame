package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Group background = new Group();  // Задний фон
        Group playGround = new Group();  // то с чем можно взаимодействовать
        Group mainGroup  = new Group();  // объединяющая нода

        mainGroup.getChildren().add(background);

        mainGroup.getChildren().add(playGround);

        Player player = new Player(15,0,0,playGround);

        player.addStatus(MoveStatusEnum.RIGHT);
        player.addStatus(MoveStatusEnum.DOWN);


        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                player.move();
            }
        };
        timer.scheduleAtFixedRate(task,0,100);

        Scene scene = new Scene(mainGroup, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}