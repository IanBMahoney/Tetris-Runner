package tetrisrunner;

import java.util.Random;

public class Tetromino {

    //Random rNumber = new Random();
    Square[] squares = new Square[4];
    String shape = "";
    String orientation = "";
    int counter = 0;
    int xOffset = 4;
    int yOffset = 0;
    boolean hasStabilized = false;
    boolean hasTurned = false;

    public void pieceI() {
        squares[0].x = 0;
        squares[1].x = 0;
        squares[2].x = 0;
        squares[3].x = 0;
        squares[0].y = 0;
        squares[1].y = 1;
        squares[2].y = 2;
        squares[3].y = 3;
    }

    public void pieceI1() {
        squares[0].x = 0;
        squares[1].x = 1;
        squares[2].x = 2;
        squares[3].x = 3;
        squares[0].y = 0;
        squares[1].y = 0;
        squares[2].y = 0;
        squares[3].y = 0;
    }

    public void pieceJ() {
        squares[0].x = 1;
        squares[1].x = 1;
        squares[2].x = 1;
        squares[3].x = 0;
        squares[0].y = 0;
        squares[1].y = 1;
        squares[2].y = 2;
        squares[3].y = 2;
    }

    public void pieceJ1() {
        squares[0].x = 0;
        squares[1].x = 0;
        squares[2].x = 1;
        squares[3].x = 2;
        squares[0].y = 0;
        squares[1].y = 1;
        squares[2].y = 1;
        squares[3].y = 1;
    }

    public void pieceJ2() {
        squares[0].x = 0;
        squares[1].x = 0;
        squares[2].x = 0;
        squares[3].x = 0;
        squares[3].x = 1;
        squares[0].y = 2;
        squares[1].y = 1;
        squares[2].y = 0;
        squares[3].y = 0;
    }

    public void pieceJ3() {
        squares[0].x = 0;
        squares[1].x = 1;
        squares[2].x = 2;
        squares[3].x = 2;
        squares[0].y = 0;
        squares[1].y = 0;
        squares[2].y = 0;
        squares[3].y = 1;
    }

    public void pieceL() {
        squares[0].x = 0;
        squares[1].x = 0;
        squares[2].x = 0;
        squares[3].x = 1;
        squares[0].y = 0;
        squares[1].y = 1;
        squares[2].y = 2;
        squares[3].y = 2;
    }

    public void pieceL1() {
        squares[0].x = 0;
        squares[1].x = 0;
        squares[2].x = 1;
        squares[3].x = 2;
        squares[0].y = 1;
        squares[1].y = 0;
        squares[2].y = 0;
        squares[3].y = 0;
    }

    public void pieceL2() {
        squares[0].x = 0;
        squares[1].x = 1;
        squares[2].x = 1;
        squares[3].x = 1;
        squares[0].y = 0;
        squares[1].y = 0;
        squares[2].y = 1;
        squares[3].y = 2;
    }

    public void pieceL3() {
        squares[0].x = 0;
        squares[1].x = 1;
        squares[2].x = 2;
        squares[3].x = 2;
        squares[0].y = 1;
        squares[1].y = 1;
        squares[2].y = 1;
        squares[3].y = 0;
    }

    public void pieceO() {
        squares[0].x = 0;
        squares[1].x = 1;
        squares[2].x = 0;
        squares[3].x = 1;
        squares[0].y = 0;
        squares[1].y = 0;
        squares[2].y = 1;
        squares[3].y = 1;
    }

    public void pieceS() {
        squares[0].x = 0;
        squares[1].x = 1;
        squares[2].x = 1;
        squares[3].x = 2;
        squares[0].y = 1;
        squares[1].y = 1;
        squares[2].y = 0;
        squares[3].y = 0;
    }

    public void pieceS1() {
        squares[0].x = 0;
        squares[1].x = 0;
        squares[2].x = 1;
        squares[3].x = 1;
        squares[0].y = 0;
        squares[1].y = 1;
        squares[2].y = 1;
        squares[3].y = 2;
    }

    public void pieceT() {
        squares[0].x = 0;
        squares[1].x = 1;
        squares[2].x = 1;
        squares[3].x = 2;
        squares[0].y = 0;
        squares[1].y = 0;
        squares[2].y = 1;
        squares[3].y = 0;
    }

    public void pieceT1() {

        squares[0].x = 0;
        squares[1].x = 1;
        squares[2].x = 1;
        squares[3].x = 1;
        squares[0].y = 1;
        squares[1].y = 0;
        squares[2].y = 1;
        squares[3].y = 2;

    }

    public void pieceT2() {
        squares[0].x = 0;
        squares[1].x = 1;
        squares[2].x = 1;
        squares[3].x = 2;
        squares[0].y = 1;
        squares[1].y = 1;
        squares[2].y = 0;
        squares[3].y = 1;
    }

    public void pieceT3() {
        squares[0].x = 0;
        squares[1].x = 0;
        squares[2].x = 0;
        squares[3].x = 1;
        squares[0].y = 0;
        squares[1].y = 1;
        squares[2].y = 2;
        squares[3].y = 1;
    }

    public void pieceZ() {
        squares[0].x = 0;
        squares[1].x = 1;
        squares[2].x = 1;
        squares[3].x = 2;
        squares[0].y = 0;
        squares[1].y = 0;
        squares[2].y = 1;
        squares[3].y = 1;
    }

    public void pieceZ1() {
        squares[0].x = 1;
        squares[1].x = 1;
        squares[2].x = 0;
        squares[3].x = 0;
        squares[0].y = 0;
        squares[1].y = 1;
        squares[2].y = 1;
        squares[3].y = 2;
    }

    public Tetromino(int spawnPiece) {
        for (int i = 0; i < squares.length; i++) {
            squares[i] = new Square(this);
        }

        //int spawnPiece = rNumber.nextInt(7);
        if (spawnPiece == 1) {
            pieceI();
            shape = "pieceI";
        } else if (spawnPiece == 2) {
            pieceJ();
            shape = "pieceJ";
        } else if (spawnPiece == 3) {
            pieceL();
            shape = "pieceL";
        } else if (spawnPiece == 4) {
            pieceO();
            shape = "pieceO";
        } else if (spawnPiece == 5) {
            pieceS();
            shape = "pieceS";
        } else if (spawnPiece == 6) {
            pieceT();
            shape = "pieceT";
        } else {
            pieceZ();
            shape = "pieceZ";
        }
    }

    public class Square {

        public int x = 0;
        public int y = 0;
        boolean isRemoved = false;
        public int yOffset = 0;
        public Tetromino tetromino;

        public Square(Tetromino tetromino) {
            this.tetromino = tetromino;
        }
    }

    public void rotateRight() {
        if (counter <= 4) {
            counter++;
        }
        if (counter == 1) {
            orientation = "orientationOne";
        } else if (counter == 2) {
            orientation = "orientationTwo";
        } else if (counter == 3) {
            orientation = "orientationThree";
        } else {
            orientation = "orientationFour";
        }
        if (shape.equals("pieceI")) {
            if (orientation.equals("orientationOne")) {
                pieceI();
            } else if (orientation.equals("orientationTwo")) {
                pieceI1();
            } else if (orientation.equals("orientationThree")) {
                pieceI();
            } else if (orientation.equals("orientationFour")) {
                pieceI1();
            }
        } else if (shape.equals("pieceJ")) {
            if (orientation.equals("orientationOne")) {
                pieceJ();
            } else if (orientation.equals("orientationTwo")) {
                pieceJ1();
            } else if (orientation.equals("orientationThree")) {
                pieceJ2();
            } else if (orientation.equals("orientationFour")) {
                pieceJ3();
            }
        } else if (shape.equals("pieceL")) {
            if (orientation.equals("orientationOne")) {
                pieceL();
            } else if (orientation.equals("orientationTwo")) {
                pieceL1();
            } else if (orientation.equals("orientationThree")) {
                pieceL2();
            } else if (orientation.equals("orientationFour")) {
                pieceL3();
            }
        } else if (shape.equals("pieceO")) {
            if (orientation.equals("orientationOne")) {
                pieceO();
            } else if (orientation.equals("orientationTwo")) {
                pieceO();
            } else if (orientation.equals("orientationThree")) {
                pieceO();
            } else if (orientation.equals("orientationFour")) {
                pieceO();
            }
        } else if (shape.equals("pieceS")) {
            if (orientation.equals("orientationOne")) {
                pieceS();
            } else if (orientation.equals("orientationTwo")) {
                pieceS1();
            } else if (orientation.equals("orientationThree")) {
                pieceS();
            } else if (orientation.equals("orientationFour")) {
                pieceS1();
            }
        } else if (shape.equals("pieceT")) {
            if (orientation.equals("orientationOne")) {
                pieceT();
            } else if (orientation.equals("orientationTwo")) {
                pieceT1();
            } else if (orientation.equals("orientationThree")) {
                pieceT2();
            } else if (orientation.equals("orientationFour")) {
                pieceT3();
            }
        } else if (shape.equals("pieceZ")) {
            if (orientation.equals("orientationOne")) {
                pieceZ();
            } else if (orientation.equals("orientationTwo")) {
                pieceZ1();
            } else if (orientation.equals("orientationThree")) {
                pieceZ();
            } else if (orientation.equals("orientationFour")) {
                pieceZ1();
            }
        }
        if (counter == 4) {
            counter = 0;
        }
    }

    public void rotateLeft() {
        if (counter <= 4) {
            counter++;
        }
        if (counter == 1) {
            orientation = "orientationOne";
        } else if (counter == 2) {
            orientation = "orientationTwo";
        } else if (counter == 3) {
            orientation = "orientationThree";
        } else {
            orientation = "orientationFour";
        }
        if (shape.equals("pieceI")) {
            if (orientation.equals("orientationOne")) {
                pieceI();
            } else if (orientation.equals("orientationTwo")) {
                pieceI1();
            } else if (orientation.equals("orientationThree")) {
                pieceI();
            } else if (orientation.equals("orientationFour")) {
                pieceI1();
            }
        } else if (shape.equals("pieceJ")) {
            if (orientation.equals("orientationOne")) {
                pieceJ();
            } else if (orientation.equals("orientationTwo")) {
                pieceJ3();
            } else if (orientation.equals("orientationThree")) {
                pieceJ2();
            } else if (orientation.equals("orientationFour")) {
                pieceJ1();
            }
        } else if (shape.equals("pieceL")) {
            if (orientation.equals("orientationOne")) {
                pieceL();
            } else if (orientation.equals("orientationTwo")) {
                pieceL3();
            } else if (orientation.equals("orientationThree")) {
                pieceL2();
            } else if (orientation.equals("orientationFour")) {
                pieceL1();
            }
        } else if (shape.equals("pieceO")) {
            if (orientation.equals("orientationOne")) {
                pieceO();
            } else if (orientation.equals("orientationTwo")) {
                pieceO();
            } else if (orientation.equals("orientationThree")) {
                pieceO();
            } else if (orientation.equals("orientationFour")) {
                pieceO();
            }
        } else if (shape.equals("pieceS")) {
            if (orientation.equals("orientationOne")) {
                pieceS();
            } else if (orientation.equals("orientationTwo")) {
                pieceS1();
            } else if (orientation.equals("orientationThree")) {
                pieceS();
            } else if (orientation.equals("orientationFour")) {
                pieceS1();
            }
        } else if (shape.equals("pieceT")) {
            if (orientation.equals("orientationOne")) {
                pieceT();
            } else if (orientation.equals("orientationTwo")) {
                pieceT3();
            } else if (orientation.equals("orientationThree")) {
                pieceT2();
            } else if (orientation.equals("orientationFour")) {
                pieceT1();
            }
        } else if (shape.equals("pieceZ")) {
            if (orientation.equals("orientationOne")) {
                pieceZ();
            } else if (orientation.equals("orientationTwo")) {
                pieceZ1();
            } else if (orientation.equals("orientationThree")) {
                pieceZ();
            } else if (orientation.equals("orientationFour")) {
                pieceZ1();
            }
        }
        if (counter == 4) {
            counter = 0;
        }
    }
}
