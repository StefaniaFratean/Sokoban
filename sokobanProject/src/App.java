import javax.swing.*;
import java.awt.*;
import java.awt.EventQueue;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class App extends JFrame{

    private Container contain;
    private JPanel startPg,map;


    public void playGame(){

        JFrame frame = new JFrame("Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StartPage startPage = new StartPage(frame);
        frame.add(startPage);
        frame.setSize(800,600);

        frame.setVisible(true);

    }

    public static void main(String[] args) {
         App app = new App();
         app.playGame();

    }

}
