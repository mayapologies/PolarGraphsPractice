import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PolarPractice extends JPanel implements KeyListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private static final double SCALE_FACTOR = 50.0;
    private static final double DELTA_THETA = 0.01;

    private boolean graphVisible;
    private PolarEquation equation;
    private PolarEquationGrapher grapher;

    public PolarPractice() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        equation = new PolarEquation();
        grapher = new PolarEquationGrapher(equation);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        if(!graphVisible) {
            g.drawString("Press space bar to see graph", 50, HEIGHT / 2);
            g.drawString("Equation: " + equation, 50, HEIGHT / 2 - 50);
        }

        if (graphVisible) {
            if (grapher != null) {
                grapher.paintComponent(g);
            } else {
                grapher = new PolarEquationGrapher(equation);
                grapher.paintComponent(g);
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            graphVisible = !graphVisible;
            repaint();
        }
        else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            equation.generateRandomEquation();
            graphVisible = false;
            repaint();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Polar Practice");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new PolarPractice());
        frame.pack();
        frame.setVisible(true);
    }
}