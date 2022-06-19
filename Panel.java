import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable {
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH*(0.5555)); // standard ping pong table
    static final Dimension SCREEN_SIZE = new Dimension(1000, 555);
    static int BALL_DIAMETER = 20; // the higher the ball, the higher the ball diameter
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    //declaring instances
    Thread gameThread; //because we implement the runnable interface
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1; //player 1
    Paddle paddle2; //player2
    Ball ball;
    Score score;

    Panel() {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true); // if we press any key, it will focus
        this.addKeyListener(new Panel.AL());
        this.setPreferredSize(SCREEN_SIZE);
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    public void newBall() {
        random = new Random(); // ball will start randomly in the y axis
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    }

    public void newPaddles() {
        //positioning the game paddle
        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        // 0 position= very left of the window
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight()); //use the get function to retrieve the width n height of the panel
        graphics = image.getGraphics(); //
        draw(graphics);// pass in the graphic created from image
        g.drawImage(image, 0, 0, this); //
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move() {
        ball.move();
    }

    public void checkCollision() {
        //stops paddle at window edges
        if (paddle1.y <= 0) {
            paddle1.y = 0;
        }
        if (paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT)) {
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
        }

        if (paddle2.y <= 0) {
            paddle2.y = 0;
        }
        if (paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT)) {
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
        }

        //bounce back the ball so it will not go off screen
        if (ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT- BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }

        //ball bounce off paddle
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity); //reverse velocity of the ball
            ++ball.xVelocity;
            if (ball.yVelocity > 0) {
                ++ball.yVelocity;
            } else {
                --ball.yVelocity; // increase upwards velocity
            }

            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ++ball.xVelocity; // increase the velocity
            if (ball.yVelocity > 0) {
                ++ball.yVelocity;
            } else {
                --ball.yVelocity;
            }

            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //give player a score 1 point and create new ball
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++; // score will be incremented by 1
            newPaddles();
            newBall();
            System.out.println("Player 1:" + score.player1);
        }

        if (ball.x <= 0) {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2:" + score.player2);
        }

    }

    public void run() { //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while(true) {
            do {
                long now = System.nanoTime();
                delta += (double)(now - lastTime) / ns;
                lastTime = now;
            } while(!(delta >= 1));

            move();
            checkCollision();
            repaint();
            --delta;
        }
    }

    public class AL extends KeyAdapter {
        public AL() {
        }

        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyRelease(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
