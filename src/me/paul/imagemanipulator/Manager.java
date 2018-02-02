package me.paul.imagemanipulator;

import javafx.scene.image.Image;

public class Manager {
    public static void main(String args[]) {
        System.out.println("testing");

        Image image = new Image("/data/test.png");

        double width = image.getWidth();
        double height = image.getHeight();

        System.out.println(width + " x " + height);
    }
}
