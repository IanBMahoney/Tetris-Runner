/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisrunner;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

/**
 *
 * @author Ian Mahoney, Alex Truman, Max Clausius, Billy Fong
 */
public class Game {

    Display display;
    Tetromino[] tetrominoes = new Tetromino[0];
    Player player;
    boolean running = true;
    boolean started = false;
    Input input;
    Timer timer;

    Timer dropTimer;

    int score = 0;
    int scoreCounter = 0;
    int nextTetromino;
    Random r = new Random();

    //public boolean isCoordinateBelowSafe() {
    //    return false;
    //}
    
    public Game(Display display) {
        this.display = display;
        display.setGame(this);

        nextTetromino = r.nextInt(7) + 1;
        player = new Player(this);

        timer = new Timer(17, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                update();
                scoreCounter++;
                
                if (started && scoreCounter == 60) {
                    scoreCounter = 0;
                    score++;
                }
                

                if (player.direction.equals("left")) {
                    if (player.velocityX > 0) {
                        player.velocityX = 0;
                    }

                    player.velocityX -= 0.01;

                    if (player.velocityX <= -0.2) {
                        player.velocityX = -0.2;
                    }
                } else if (player.direction.equals("right")) {
                    if (player.velocityX < 0) {
                        player.velocityX = 0;
                    }

                    //System.out.println("ddddd");
                    player.velocityX += 0.01;

                    if (player.velocityX >= 0.2) {
                        player.velocityX = 0.2;
                    }
                }
                
                if (player.standing && player.jumping) {
                    player.standing = false;
                    player.totalBlocksJumped = 0;
                }
                
                if (player.jumping && player.totalBlocksJumped < 3) {
                    
                    
                    if (player.velocityY == 0.1) {
                        player.velocityY = 0;
                    }
                    
                    player.velocityY -= 0.4;

                    player.totalBlocksJumped -= player.velocityY;
                }

                
                
                if (started) {
                    player.velocityY += 0.05;

                    if (player.velocityY < -0.4) {
                        player.velocityY = -0.4;
                    } else if (player.velocityY > 0.4) {
                        player.velocityY = 0.4;
                    }

                    
                    
                    
                    player.x += player.velocityX;
                    
                    if (player.standing) {
                        player.velocityY = 0;
                    }
                    player.y += player.velocityY;
                    
                    if (player.x > 9) {
                        player.x = 9;
                    } else if (player.x < 0) {
                        player.x = 0;
                    }
                    
                    if (player.y == 15.4) {
                        player.standing = true;
                        player.totalBlocksJumped = 0;
                    }
                    
                    
                    boolean blockBelow = false;
                    boolean blockAt = false;
                    Tetromino.Square below;
                    below = (new Tetromino(0).squares[0]);
                    Tetromino.Square at = (new Tetromino(0).squares[0]);;
                    int x;
                    int y;
                    
                    if (player.x % 1 > 0.5) {
                        x = (int) (player.x + 1);
                    } else {
                        x = (int) (player.x);
                    }
                    
                    if (player.y % 1 > 0.5) {
                        y = (int) (player.y);
                    } else {
                        y = (int) (player.y);
                    }
                    
                    for (int i = 0; i < tetrominoes.length; i++) {
                        for (int j = 0; j < tetrominoes[i].squares.length; j++) {
                            if (x == tetrominoes[i].squares[j].x + tetrominoes[i].xOffset &&
                                y == tetrominoes[i].squares[j].y + tetrominoes[i].yOffset + tetrominoes[i].squares[j].yOffset) {
                                blockAt = true;
                                System.out.println("blockAt");
                                at = tetrominoes[i].squares[j];
                                
                            } else if (x == tetrominoes[i].squares[j].x + tetrominoes[i].xOffset &&
                                y - 1 == tetrominoes[i].squares[j].y + tetrominoes[i].yOffset + tetrominoes[i].squares[j].yOffset) {
                                blockBelow = true;
                                System.out.println("blockBelow");
                                below = tetrominoes[i].squares[j];
                                
                            } 
                        }
                    }
                    System.out.println(player.x + " " + player.y + " " + player.jumping + " " + player.totalBlocksJumped);
                    
                    if (blockAt /*&& blockBelow*/) {
                        //endGame("player was squished");
                    }
                    
                    if (blockBelow) {
                        player.y = player.y + (player.y % 1);
                        player.standing = true;
                    }
                    
                    if (blockBelow) {
                        player.y = below.y + below.yOffset + below.tetromino.yOffset - 1;
                    } else {
                        //endGame("player was squished");
                        //return;
                    }
                    
                    
                    
                    
                    

                    if (player.y > 15) {
                        player.y = 15;
                    } else if (player.y < 0) {
                        player.y = 0;
                    }
                }
                
                
                
            }
        });

        dropTimer = new Timer(1500, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                attemptToMoveDown();
            }
        });

        timer.start();
    }

    public String getPiece(int spawnPiece) {
        switch (spawnPiece) {
            case 1:
                return "pieceI";
            case 2:
                return "pieceJ";
            case 3:
                return "pieceL";
            case 4:
                return "pieceO";
            case 5:
                return "pieceS";
            case 6:
                return "pieceT";
            default:
                return "pieceZ";
        }
    }
    
    public void attemptToMoveDown() {
        if (!tetrominoes[0].hasStabilized && canMoveDown() && started) {
            tetrominoes[0].yOffset += 1;
        } else if (timer.isRunning() && started) {
            checkGame();
        }
    }

    public void checkGame() {
        tetrominoes[0].hasStabilized = true;
        appendTetromino();

        int[] numbers = new int[16];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = 0;
        }

        for (int i = 0; i < tetrominoes.length; i++) {
            for (int j = 0; j < tetrominoes[i].squares.length; j++) {
                if (!tetrominoes[i].squares[j].isRemoved) {
                    numbers[tetrominoes[i].squares[j].y + tetrominoes[i].yOffset + tetrominoes[i].squares[j].yOffset]++;
                }
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 10) {
                for (int j = 0; j < tetrominoes.length; j++) {
                    for (int k = 0; k < tetrominoes[j].squares.length; k++) {
                        if (!tetrominoes[j].squares[k].isRemoved && tetrominoes[j].squares[k].y + tetrominoes[j].yOffset + tetrominoes[j].squares[k].yOffset == i) {
                            tetrominoes[j].squares[k].isRemoved = true;
                        }

                        if (tetrominoes[j].squares[k].y + tetrominoes[j].yOffset + tetrominoes[j].squares[k].yOffset < i) {
                            tetrominoes[j].squares[k].yOffset++;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < tetrominoes.length; i++) {
            int counter = 0;

            for (int j = 0; j < tetrominoes[i].squares.length; j++) {
                if (tetrominoes[i].squares[j].isRemoved) {
                    counter++;
                }
            }

            if (counter == 4) {
                removeTetromino(i);
            }

        }

        System.out.println("game checked, total tetrominoes: " + tetrominoes.length);
    }

    public void removeTetromino(int index) {
        Tetromino[] newArray = new Tetromino[tetrominoes.length - 1];

        int counter = 0;

        for (int i = 0; i < tetrominoes.length; i++) {
            if (i != index) {
                newArray[counter] = tetrominoes[i];
                counter++;

            } else {

            }
        }

        tetrominoes = newArray;
    }

    public void endGame(String reason) {
        //timer.stop();
        dropTimer.stop();
        started = false;
        
        System.out.println("GAME FINISHED, REASON: " + reason);
        System.out.println(player.x + " " + player.y + " " + player.jumping + " " + player.totalBlocksJumped);
    }

    public boolean rotateLeft() {
        if (!tetrominoes[0].hasTurned) {
            tetrominoes[0].rotateLeft();
            tetrominoes[0].hasTurned = true;
        }

        tetrominoes[0].rotateLeft();

        for (int i = 1; i < tetrominoes.length; i++) {
            for (int j = 0; j < tetrominoes[i].squares.length; j++) {
                for (int k = 0; k < tetrominoes[0].squares.length; k++) {
                    if (tetrominoes[i].squares[j].x + tetrominoes[i].xOffset == tetrominoes[0].squares[k].x + tetrominoes[0].xOffset
                            && tetrominoes[i].squares[j].y + tetrominoes[i].yOffset + tetrominoes[i].squares[j].yOffset == tetrominoes[0].squares[k].y + tetrominoes[0].yOffset + tetrominoes[0].squares[k].yOffset
                            && !tetrominoes[i].squares[j].isRemoved) {
                        rotateRight();
                        return false;
                    } else if (tetrominoes[i].squares[j].x < 0) {
                        rotateRight();
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean rotateRight() {
        if (!tetrominoes[0].hasTurned) {
            tetrominoes[0].rotateRight();
            tetrominoes[0].hasTurned = true;
        }

        tetrominoes[0].rotateRight();

        for (int i = 1; i < tetrominoes.length; i++) {
            for (int j = 0; j < tetrominoes[i].squares.length; j++) {
                for (int k = 0; k < tetrominoes[0].squares.length; k++) {
                    if (tetrominoes[i].squares[j].x + tetrominoes[i].xOffset == tetrominoes[0].squares[k].x + tetrominoes[0].xOffset
                            && tetrominoes[i].squares[j].y + tetrominoes[i].yOffset + tetrominoes[i].squares[j].yOffset == tetrominoes[0].squares[k].y + tetrominoes[0].yOffset + tetrominoes[0].squares[k].yOffset
                            && !tetrominoes[i].squares[j].isRemoved) {
                        rotateLeft();
                        return false;
                    } else if (tetrominoes[0].squares[k].x + tetrominoes[0].xOffset > 9) {
                        rotateLeft();
                        //System.out.println("wowoowowowowowowowowowowwowowowowowo");
                        return false;
                    }
                }
            }
        }

        for (int i = 0; i < tetrominoes[0].squares.length; i++) {
            if (tetrominoes[0].squares[i].x + tetrominoes[0].xOffset > 9) {
                //System.out.println("y0");

                if (!moveLeft()) {
                    rotateLeft();
                    return false;
                }
            }
        }

        return true;
    }

    public boolean moveLeft() {
        Tetromino tet = tetrominoes[0];

        for (int i = 0; i < tetrominoes.length; i++) {
            if (tetrominoes.length == 1) {
                //System.out.println("IT BROKE");
                break;
            } else if (i == 0) {
                i++;
            }

            for (int j = 0; j < tetrominoes[i].squares.length; j++) {
                for (int k = 0; k < tet.squares.length; k++) {
                    if (tetrominoes[i].squares[j].x + tetrominoes[i].xOffset == tet.squares[k].x + tet.xOffset - 1
                            && tetrominoes[i].squares[j].y + tetrominoes[i].yOffset + tetrominoes[i].squares[j].yOffset == tet.squares[k].y + tet.yOffset + tet.squares[k].yOffset
                            && !tetrominoes[i].squares[j].isRemoved) {
                        System.out.println(tetrominoes[i].squares[j].x + " " + tetrominoes[i].squares[j].y + ", " + tet.squares[k].x + " " + tet.squares[k].y);
                        return false;
                    }
                }
            }
        }

        if (tet.xOffset != 0) {
            tetrominoes[0].xOffset -= 1;
            return true;
        } else {
            return false;
        }
    }

    public boolean moveRight() {
        Tetromino tet = tetrominoes[0];

        for (int i = 0; i < tetrominoes.length; i++) {
            if (tetrominoes.length == 1) {
                //System.out.println("IT BROKE");
                break;
            } else if (i == 0) {
                i++;
            }

            for (int j = 0; j < tetrominoes[i].squares.length; j++) {
                for (int k = 0; k < tet.squares.length; k++) {
                    if (tetrominoes[i].squares[j].x + tetrominoes[i].xOffset == tet.squares[k].x + tet.xOffset + 1
                            && tetrominoes[i].squares[j].y + tetrominoes[i].yOffset + tetrominoes[i].squares[j].yOffset == tet.squares[k].y + tet.yOffset + tet.squares[k].yOffset
                            && !tetrominoes[i].squares[j].isRemoved) {
                        System.out.println(tetrominoes[i].squares[j].x + " " + tetrominoes[i].squares[j].y + ", " + tet.squares[k].x + " " + tet.squares[k].y);
                        return false;
                    }
                }
            }
        }

        for (int i = 0; i < tet.squares.length; i++) {
            if (tet.squares[i].x + tet.xOffset + 1 >= 10) {
                return false;
            }
        }

        tetrominoes[0].xOffset++;
        return true;
    }

    public boolean canMoveDown() {
        Tetromino tet = tetrominoes[0];

        for (int i = 1; i < tetrominoes.length; i++) {
            for (int j = 0; j < tetrominoes[i].squares.length; j++) {
                for (int k = 0; k < tet.squares.length; k++) {
                    if (tetrominoes[i].squares[j].x + tetrominoes[i].xOffset == tet.squares[k].x + tet.xOffset &&
                        tetrominoes[i].squares[j].y + tetrominoes[i].yOffset + tetrominoes[i].squares[j].yOffset == tet.squares[k].y + tet.yOffset + tet.squares[k].yOffset &&
                        !tetrominoes[i].squares[j].isRemoved) {
                        endGame("cap");
                        return false;
                    } else if (tetrominoes[i].squares[j].x + tetrominoes[i].xOffset == tet.squares[k].x + tet.xOffset &&
                        tetrominoes[i].squares[j].y + tetrominoes[i].yOffset + tetrominoes[i].squares[j].yOffset == tet.squares[k].y + tet.yOffset + tet.squares[k].yOffset + 1 &&
                        !tetrominoes[i].squares[j].isRemoved) {
                        return false;
                    }
                }
            }
        }
        
        /*
        for (int i = 1; i < tetrominoes.length; i++) {
            for (int j = 0; j < tetrominoes[i].squares.length; j++) {
                for (int k = 0; k < tet.squares.length; k++) {
                    if (tetrominoes[i].squares[j].x + tetrominoes[i].xOffset == tet.squares[k].x + tet.xOffset
                            && tetrominoes[i].squares[j].y + tetrominoes[i].yOffset + tetrominoes[i].squares[j].yOffset == tet.squares[k].y + tet.yOffset + tet.squares[k].yOffset
                            && !tetrominoes[i].squares[j].isRemoved) {
                        endGame("cap");
                        return false;
                    } else if (tetrominoes[i].squares[j].x + tetrominoes[i].xOffset == tet.squares[k].x + tet.xOffset
                            && tetrominoes[i].squares[j].y + tetrominoes[i].yOffset + tetrominoes[i].squares[j].yOffset == tet.squares[k].y + tet.yOffset + tet.squares[k].yOffset + 1
                            && !tetrominoes[i].squares[j].isRemoved) {
                        return false;
                    }
                }
            }
        }*/

        for (int i = 0; i < tet.squares.length; i++) {
            if (tet.squares[i].y + tet.yOffset + tet.squares[i].yOffset + 1 >= 16) {
                return false;
            }
        }

        return true;
    }

    public void update() {
        display.repaint();
    }

    public void start() {
        started = true;
        tetrominoes = new Tetromino[0];
        System.out.println("game started");
        dropTimer.start();
        appendTetromino();
        score = 0;
        scoreCounter = 0;
        //System.out.println("appended");
        //sound("tetris.wav");
    }

    public void appendTetromino() {
        Tetromino[] newArray = new Tetromino[tetrominoes.length + 1];
        for (int i = 0; i < tetrominoes.length; i++) {
            newArray[i + 1] = tetrominoes[i];
        }

        newArray[0] = new Tetromino(nextTetromino);
        nextTetromino = r.nextInt(7) + 1;
        tetrominoes = newArray;
    }

    public void sound(String fileName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(fileName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
