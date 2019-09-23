package tetrisrunner;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import javax.swing.JDialog;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Main {

    public static JDialog win;
    
    public static void main(String[] args) {
        win = new JDialog();
        win.setResizable(false);
        win.setSize(800, 900);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        win.setUndecorated(true);
        
        Display display = new Display();
        Game game = new Game(display);
        win.add(display);
        win.add(display);
        win.setVisible(true);
        
        Input input = new Input(game);
        
        win.addKeyListener(input);
        win.addMouseListener(input);
    }
    
}
