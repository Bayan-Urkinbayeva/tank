//package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyPlayer {
    ImageView tank_png;
    int size;
    Pane pane;
    Pane bulletPane;
    public MyPlayer(String position,ImageView tank_png){
        this.tank_png = tank_png;
        String[] pos = position.split(" ");
        tank_png.setX(Integer.parseInt(pos[0]));
        tank_png.setY(Integer.parseInt(pos[1]));
        size = Integer.parseInt(pos[2]);
        tank_png.setFitWidth(40);
        tank_png.setFitHeight(40);
    }

    public void setMap(Map map){

        pane=map;
    }

    public void setMap(Pane pane){

        bulletPane = pane;
    }

    public void moveRight(){
        tank_png.setRotate(270);
        double getLast = tank_png.getX();
        tank_png.setX(tank_png.getX()+5);
        for(Node node: pane.getChildren()){
            if (tank_png.getBoundsInParent().intersects(node.getBoundsInParent()) && !(node instanceof Trees)){
                tank_png.setX(tank_png.getX()-5);
            }
        }
        if(tank_png.getX()>size-40){
            tank_png.setX(tank_png.getX()-5);
        }
        if(getLast == tank_png.getX()){
            System.out.println("Invalid Position");
        } else{
            System.out.println("right");
        }
    }

    public void moveLeft(){
        tank_png.setRotate(90);
        double getLast = tank_png.getX();
        tank_png.setX(tank_png.getX()-5);
        for(Node node: pane.getChildren()){
            if (tank_png.getBoundsInParent().intersects(node.getBoundsInParent()) && !(node instanceof Trees)){
                tank_png.setX(tank_png.getX()+5);
            }
        }
        if(tank_png.getX()<0){
            tank_png.setX(tank_png.getX()+5);
        }
        if(getLast == tank_png.getX()){
            System.out.println("Invalid Position");
        } else{
            System.out.println("left");
        }
    }

    public void moveUp(){
        tank_png.setRotate(180);
        double getLast = tank_png.getY();
        tank_png.setY(tank_png.getY()-5);
        for(Node node: pane.getChildren()){
            if (tank_png.getBoundsInParent().intersects(node.getBoundsInParent()) && !(node instanceof Trees)){
                tank_png.setY(tank_png.getY()+5);
            }
        }
        if(tank_png.getY()<0){
            tank_png.setY(tank_png.getY()+5);
        }
        if(getLast == tank_png.getY()){
            System.out.println("Invalid Position");
        } else{
            System.out.println("up");
        }
    }

    public void moveDown(){
        tank_png.setRotate(0);
        double getLast = tank_png.getY();
        tank_png.setY(tank_png.getY()+5);
        for(Node node: pane.getChildren()){
            if (tank_png.getBoundsInParent().intersects(node.getBoundsInParent()) && !(node instanceof Trees)){
                tank_png.setY(tank_png.getY()-5);
            }
        }
        if(tank_png.getY()>size-40){
            tank_png.setY(tank_png.getY()-5);
        }
        if(getLast == tank_png.getY()){
            System.out.println("Invalid Position");
        } else{
            System.out.println("down");
        }
    }
    public void fire() throws FileNotFoundException {
        ImageView bullet = new Bullet(new Image(new FileInputStream("bullet.png")));
        bullet.setFitWidth(15);
        bullet.setFitHeight(15);
        bullet.setX(tank_png.getX()+20);
        bullet.setY(tank_png.getY()+20);
        bulletPane.getChildren().add(bullet);
        bullet.toBack();
        System.out.println("fire!");
        AnimationTimer animationRight = new AnimationTimer() {
            @Override
            public void handle(long now) {
                bullet.setX(bullet.getX() + 5);
                if (bullet.getX() > size + 20) {
                    stop();
                }
                for (Node node: pane.getChildren()){
                    if(node instanceof Brick) {
                        if (bullet.getBoundsInParent().intersects(node.getBoundsInParent())) {
                            ((Brick) node).damage();
                            if(((Brick) node).hp==0){
                                pane.getChildren().remove(node);
                            }
                            bulletPane.getChildren().remove(bullet);
                            stop();
                            bullet.toBack();
                            break;
                        }
                    }
                    if(node instanceof Steel){
                        if(bullet.getBoundsInParent().intersects(node.getBoundsInParent())){
                            bulletPane.getChildren().remove(bullet);
                            stop();
                        }
                    }
                }
            }
        };
        AnimationTimer animationLeft = new AnimationTimer() {
            @Override
            public void handle(long now) {
                bullet.setX(bullet.getX() - 5);
                if(bullet.getX()<-10){
                    stop();
                }
                for (Node node: pane.getChildren()){
                    if(node instanceof Brick) {
                        if (bullet.getBoundsInParent().intersects(node.getBoundsInParent())) {
                            ((Brick) node).damage();
                            if(((Brick) node).hp==0){
                                pane.getChildren().remove(node);
                            }
                            bulletPane.getChildren().remove(bullet);
                            stop();
                            bullet.toBack();
                            break;
                        }
                    }
                    if(node instanceof Steel){
                        if(bullet.getBoundsInParent().intersects(node.getBoundsInParent())){
                            bulletPane.getChildren().remove(bullet);
                            stop();
                        }
                    }
                }
            }
        };
        AnimationTimer animationDown = new AnimationTimer() {
            @Override
            public void handle(long now) {
                bullet.setY(bullet.getY() + 5);
                if(bullet.getY()>size+20){
                    stop();
                }
                for (Node node: pane.getChildren()){
                    if(node instanceof Brick) {
                        if (bullet.getBoundsInParent().intersects(node.getBoundsInParent())) {
                            ((Brick) node).damage();
                            if(((Brick) node).hp==0){
                                pane.getChildren().remove(node);
                            }
                            bulletPane.getChildren().remove(bullet);
                            stop();
                            bullet.toBack();
                            break;
                        }
                    }
                    if(node instanceof Steel){
                        if(bullet.getBoundsInParent().intersects(node.getBoundsInParent())){
                            bulletPane.getChildren().remove(bullet);
                            stop();
                        }
                    }
                }
            }
        };
        AnimationTimer animationUp = new AnimationTimer() {
            @Override
            public void handle(long now) {
                bullet.setY(bullet.getY() - 5);
                if(bullet.getY()<-10){
                    stop();
                }
                for (Node node: pane.getChildren()){
                    if(node instanceof Brick) {
                        if (bullet.getBoundsInParent().intersects(node.getBoundsInParent())) {
                            ((Brick) node).damage();
                            if(((Brick) node).hp==0){
                                pane.getChildren().remove(node);
                            }
                            bulletPane.getChildren().remove(bullet);
                            stop();
                            bullet.toBack();
                            break;
                        }
                    }
                    if(node instanceof Steel){
                        if(bullet.getBoundsInParent().intersects(node.getBoundsInParent())){
                            bulletPane.getChildren().remove(bullet);
                            stop();
                        }
                    }
                }
            }
        };
        if(tank_png.getRotate()==90){
            animationLeft.start();
        }
        if(tank_png.getRotate()==270){
            animationRight.start();
        }
        if(tank_png.getRotate()==180){
            animationUp.start();
        }
        if(tank_png.getRotate()==0){
            animationDown.start();
        }
    }
}
