//package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map extends GridPane {
    int size;
    char[][] map;
    int x, y;

    public Map(Scanner in) throws FileNotFoundException {
        size = in.nextInt();
        map = new char[size][size];
        setMaxSize(size*50,size*50);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = in.next().charAt(0);
                if(map[i][j]=='P'){
                    x = j * 50;
                    y = i * 50;
                }
            }
        }
        /*if(in.hasNext()){
            System.out.println("Invalid map");
        }*/
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ImageView wall;
                char[] walls = {'T','S','B','W'};
                if(map[i][j]==walls[1]){
                    wall = new Steel(new Image(new FileInputStream("steel.png")));
                    wall.setFitHeight(50);
                    wall.setFitWidth(50);
                    add(wall,j,i);
                }
                if(map[i][j]==walls[2]){
                    wall = new Brick(new Image(new FileInputStream("brick.png")));
                    wall.setFitHeight(50);
                    wall.setFitWidth(50);
                    add(wall,j,i);
                }
                if(map[i][j]==walls[3]){
                    wall = new Water(new Image(new FileInputStream("water.jpg")));
                    wall.setFitHeight(50);
                    wall.setFitWidth(50);
                    add(wall,j,i);
                }
                if(map[i][j]==walls[0]){
                    wall = new Trees(new Image(new FileInputStream("tree.png")));
                    wall.setFitHeight(50);
                    wall.setFitWidth(50);
                    add(wall,j,i);
                }
            }
        }
    }

    public String getPosition(){
        return x + " " + y + " " + size*50;
    }
}
