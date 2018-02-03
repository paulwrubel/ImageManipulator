package me.paul.imagemanipulator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Manager extends Application {

    public void start(Stage stage) {
        Image image = new Image(getParameters().getRaw().get(0));

        // Get pixel data from our Image
        ArrayList<Pixel> unsortedPixels = getPixels(image);
        ArrayList<Pixel> sortedPixels = new ArrayList<>(unsortedPixels);
        // Get our pixel data in a sorted list.
        // ...and yeah, sorry about the 2n memory use.
        System.out.println("SORTING PIXELS!");
        Collections.sort(sortedPixels);

        // Create output Image
        WritableImage result = new WritableImage((int)image.getWidth(), (int)image.getHeight());
        File resultFile = new File("/home/voxaelfox/programming/java/ImageManipulator/src/output/" + getParameters().getRaw().get(1));

        System.out.println("WRITING PIXELS TO IMAGE!");
        fillImage(result, sortedPixels);

        try {
            System.out.println("EXPORTING BUFFER TO IMAGE!");
            ImageIO.write(SwingFXUtils.fromFXImage(result, null), "png", resultFile);
        } catch (IOException e) {
            // TODO: handle exception here
        }

        System.out.println("~ DONE! ~");

        Platform.exit();
    }

    private ArrayList<Pixel> getPixels(Image image) {
        ArrayList<Pixel> p = new ArrayList<>();
        PixelReader r = image.getPixelReader();

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                p.add(new Pixel(i, j, r.getColor(i, j)));
            }
        }

        return p;
    }

    private void fillImage(WritableImage image, ArrayList<Pixel> l) {
        PixelWriter writer = image.getPixelWriter();
        int i = 0;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                writer.setColor(x, y, l.get(i).getColor());
                i++;
            }
        }
    }

    public static void main(String args[]) {
        launch(args);
    }
}
