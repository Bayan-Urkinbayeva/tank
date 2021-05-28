//package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Brick extends ImageView {
    int hp;
    public Brick(Image image){
        super(image);
        hp=4;
    }
    public void damage(){
        hp--;
    }
}
