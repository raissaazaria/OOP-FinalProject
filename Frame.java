import java.awt.*;
import javax.swing.JFrame;

public class Frame extends JFrame {
    Panel panel = new Panel();

    Frame() {
        this.panel = new Panel();
        this.add(this.panel);
        this.setTitle("Ping Pong Game");
        this.setResizable(false); // so it will not resizeable
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo((Component)null); // game frame will located in the middle
    }
}
