package tetrisrunner;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {

    boolean jumping = false;
    boolean standing = false;
    double x = 0;
    double y = 0;
    double velocityX = 0;
    double velocityY = 0;
    boolean isDead = false;
    Game game;
    double totalBlocksJumped = 0;
    String direction = "";
    String lastDirection = "left";
    

    public Player(Game game) {
        this.game = game;
    }

    /*
        public void wKeypressed(java.awt.event.KeyEvent e){
        boolean jumping = false;
        
	if(e.getKeyCode() == KeyEvent.VK_W) {
	    jumping = true;
	}
	while(jumping) {
	    man.y -= velocityY;
	}
	if(man.x >= 2) {
	    jumping = false;
	    man.y += 2; 
    }
}
     */
   
        
    
    public boolean isSquished() {
        for (Tetromino tet : game.tetrominoes) {
            for (Tetromino.Square square : tet.squares) {
                int value = 0;
                value = (int) x;

                if (x % 1 >= 0.5) {
                    value++;
                }

                if (value == square.x && (int) y == square.y) {
                    return true;
                }
            }
        }

        return false;
    }

}
