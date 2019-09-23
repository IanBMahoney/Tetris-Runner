/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisrunner;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author  Ian Mahoney, Alex Truman, Max Clausius, Billy Fong
 */
public class Display extends JPanel {

    Game game;

    Rectangle bar = new Rectangle(0, 0, 700, 50);
    Rectangle min = new Rectangle(700, 0, 50, 50);
    Rectangle close = new Rectangle(750, 0, 50, 50);

    public Display() {

    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void paint(Graphics g) {
        //System.out.println("repaint");
        //g.fillRect(0, 0, 20, 20);
        //System.out.println("repainted");
        Color color = new Color(195, 195, 195);
        g.setColor(color);
        Font font = new Font("Small Fonts", Font.PLAIN, 20);
        g.setFont(font);
        try {
            Image test = ImageIO.read(new File("images/tetrisBackground.png"));
            g.drawImage(test, 0, 0, 800, 900, this);
            test = ImageIO.read(new File("images/man_" + game.player.lastDirection + ".png"));
            g.drawImage(test, (int) (game.player.x * 50 + 50), (int) (game.player.y * 50 + 50), this);
            test = ImageIO.read(new File("images/" + game.getPiece(game.nextTetromino) + "Model.png"));
            g.drawImage(test, 617, 264, this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        g.drawString("" + game.score, 650, 120);

        
        
        //System.out.println(game.tetrominoes.length);
        for (int i = 0; i < game.tetrominoes.length; i++) {
            for (int j = 0; j < game.tetrominoes[i].squares.length; j++) {
                try {
                    Image square = ImageIO.read(new File("images/" + game.tetrominoes[i].shape + ".png"));

                    if (!game.tetrominoes[i].squares[j].isRemoved) {
                        g.drawImage(square, game.tetrominoes[i].squares[j].x * 50 + game.tetrominoes[i].xOffset * 50 + 50, (game.tetrominoes[i].squares[j].y + game.tetrominoes[i].squares[j].yOffset) * 50 + game.tetrominoes[i].yOffset * 50 + 50, this);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


