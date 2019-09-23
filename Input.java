/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisrunner;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input extends KeyAdapter implements MouseListener {
    
    Game game;
    
    public Input(Game game) {
        this.game = game;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        
        if (e.getKeyCode() == KeyEvent.VK_A && game.started) {
            game.player.direction = game.player.lastDirection = "left";
        }
        
        if (e.getKeyCode() == KeyEvent.VK_D && game.started) {
            game.player.direction = game.player.lastDirection = "right";
        }
        
        if (e.getKeyCode() == KeyEvent.VK_W && game.started) {
            game.player.jumping = true;
        }
        
        
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !game.started) {
            game.start();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT && game.started) {
            game.moveLeft();
            //System.out.println("left");
        }
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && game.started) {
            game.moveRight();
            //System.out.println("right");
        }
        
        if (e.getKeyCode() == KeyEvent.VK_COMMA && game.started) {
            game.rotateLeft();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_PERIOD && game.started) {
            game.rotateRight();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_DOWN && game.started) {
            if (game.started) {
                game.attemptToMoveDown();
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            game.player.velocityX = 0;
            game.player.direction = "";
        }
        
        if (e.getKeyCode() == KeyEvent.VK_D) {
            game.player.velocityX = 0;
            game.player.direction = "";
        }
        
        if (e.getKeyCode() == KeyEvent.VK_W) {
            game.player.jumping = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("exited");
    }

    
    
}
