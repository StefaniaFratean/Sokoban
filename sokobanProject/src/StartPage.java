import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage extends JPanel {

    private JButton lvl1;
    private JButton lvl2;
    private JButton lvl3;

    public StartPage(Frame f){
        setFocusable(true);

        if(lvl1 == null) {
            lvl1 = new JButton("Level 1");
            addButton(lvl1, f, 130, 1);
        }
        if(lvl2 == null) {
            lvl2 = new JButton("Level 2");
            addButton(lvl2, f, 190, 2);
        }
        if(lvl3 == null) {
            lvl3 = new JButton("Level 3");
            addButton(lvl3, f, 250, 3);
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawStartPage(g);
    }

    public void drawStartPage(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 2000, 2000);
        Image img = new ImageIcon("src/images/Sokoban2.png").getImage();
        g.drawImage(img, 40, 100, this);

    }


    private void addButton(JButton button, Frame f, int y, int buttonNr){
        button.setBounds(500, y, 80, 30);
        this.setLayout(null);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                addMap(f, buttonNr);
            }
        });
        this.add(button);
        button.setVisible(true);
        f.setVisible(true);
    }

    private void addMap(Frame f, int buttonNr) {
        Map map = new Map(buttonNr, f);
        f.add(map);
        map.setVisible(true);
        map.setFocusable(true);
        map.requestFocus();
    }

}
