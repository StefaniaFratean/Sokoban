import javax.swing.*;
import java.awt.*;

public class MyCanvas extends Canvas {

    private String filename;
    private int coordX;
    private int coordY;

    MyCanvas(String filename, int coordX, int coordY) {
        this.filename = filename;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    @Override
    public void paint(Graphics g) {
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage(filename);
        g.drawImage(i, this.coordX, this.coordY, this);
    }
/*
    public static void main(String[] args) {
        MyCanvas m = new MyCanvas();
        JFrame f = new JFrame();
        f.add(m);
        f.setSize(400, 400);
        f.setVisible(true);
    }

 */
}
