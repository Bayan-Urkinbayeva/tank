// package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends Application {
    private static Map map;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Map map = new Map(new Scanner(new File("src\\sample\\Map.txt")));
        Tank tank = new Tank(map.getPosition() ,new ImageView(new Image(new FileInputStream("tank.png"))));
        tank.setMap(map);
        Pane pane = new Pane(tank.tank_png,map);
        tank.setMap(pane);
        Scene scene = new Scene(pane, map.getMaxWidth(),map.getMaxHeight(), Color.BEIGE);
        scene.setOnKeyPressed(event->{
            switch (event.getCode()){
                case UP:  tank.moveUp(); break;
                case DOWN: tank.moveDown(); break;
                case RIGHT: tank.moveRight(); break;
                case LEFT: tank.moveLeft(); break;
                case SPACE:
                    try {
                        tank.fire();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = args[0];
        map = new Map(new Scanner(new File(path)));
        launch(args);
    }
}
