import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
    int id;
    int yVelocity; // how fast the paddle is going to move
    int speed = 20; // speed of the moving paddle (in pixel)

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) { //following what's on the panel class
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);//calling super constructor to assign some argument
        this.id = id; // assigning ID for the
    }

    public void keyPressed(KeyEvent e) {
        switch(id) { // examine the content of the ID variable
            case 1: // referring to paddle one
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                    move();
                }

                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    this.setYDirection(-speed);
                    this.move();
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    this.setYDirection(speed);
                    this.move();
                }
                break;
        }

    }

    public void keyReleased(KeyEvent e) {
        switch(this.id) {
            case 1: // referring to paddle one
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                }

                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                    move();
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                    move();
                }
                break;
        }
    }

    public void setYDirection(int yDirection) { // move up and down
        yVelocity = yDirection;
    }

    public void move() {
        y += yVelocity;
    }

    public void draw(Graphics g) {
        //setting color for each player
        if (this.id == 1) {
            g.setColor(Color.PINK);
        } else {
            g.setColor(Color.BLUE);
        }

        g.fillRect(this.x, this.y, this.width, this.height);
    }
}
