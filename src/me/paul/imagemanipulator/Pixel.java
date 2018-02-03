package me.paul.imagemanipulator;

import javafx.scene.paint.Color;

public class Pixel implements Comparable<Pixel> {
    private Color color;
    private int x;
    private int y;

    Pixel(int x_, int y_, Color color_) {
        x = x_;
        y = y_;

        if (color_.getSaturation() < 0.2) {
            color = Color.hsb(0.0, color_.getSaturation(), color_.getBrightness());
        } else {
            color = color_;
        }
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int compareTo(Pixel p) {
        if (Double.compare(color.getHue(), p.color.getHue()) == 0) {
            // Hues are the same!
            // Better compare by Saturation
            if (Double.compare(color.getSaturation(), p.color.getSaturation()) == 0) {
                // Saturation is the same!
                // Better compare by Brightness
                return Double.compare(color.getBrightness(), p.color.getBrightness());
            } else {
                return Double.compare(color.getSaturation(), p.color.getSaturation());
            }
        } else {
            return Double.compare(color.getHue(), p.color.getHue());
        }
    }

    @Override
    public String toString() {
        return "loc: " + x + ", " + y + " - col: " + color.toString();
    }
}
