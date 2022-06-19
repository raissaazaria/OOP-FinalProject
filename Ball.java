import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends Rectangle {
    Random random = new Random();
    int xVelocity; // how fast it's gonna move on the X axis
    int yVelocity; // how fast it's gonna move on the Y axis
    int initialSpeed = 2;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0) {
            --randomXDirection;// begin at 0 so it wil be negative 1
        }
        setXDirection(randomXDirection * initialSpeed);

        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0) {
            --randomYDirection;
        }
        setYDirection(randomYDirection * initialSpeed);
    }

    public void setXDirection(int randomXDirection) { // when create new ball it will appear randomly
        this.xVelocity = randomXDirection;
    }

    public void setYDirection(int randomYDirection) {
        this.yVelocity = randomYDirection;
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(this.x, this.y, this.height, this.width);
    }
}
