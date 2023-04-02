import java.awt.*;
import javax.swing.JPanel;

public class PolarEquationGrapher extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final double THETA_MIN = 0;
    private static final double THETA_MAX = 2 * Math.PI;
    private static final double DELTA_THETA = 0.01;
    private static final double SCALE_FACTOR = 100;

    private PolarEquation equation;

    public PolarEquationGrapher(PolarEquation eq) {
        equation = eq;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set up the drawing area
        g2d.setColor(Color.WHITE);
        g2d.translate(WIDTH/2, HEIGHT/ 2);


        // Draw the polar equation
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(0.05f));

        g2d.fillOval(-3, -3, 6, 6);
        g2d.drawLine(0, 0, (int)SCALE_FACTOR, 0);

        double x1 = equation.getR(THETA_MIN)* Math.cos(THETA_MIN);
        double y1 = -equation.getR(THETA_MIN)* Math.sin(THETA_MIN);
        for (double theta = THETA_MIN; theta <= THETA_MAX; theta += DELTA_THETA) {
            double r = equation.getR(theta);
            double x2 = r * Math.cos(theta);
            double y2 = -r * Math.sin(theta);
            g2d.drawLine((int) (x1 * SCALE_FACTOR), (int) (y1 * SCALE_FACTOR),
                    (int) (x2 * SCALE_FACTOR), (int) (y2 * SCALE_FACTOR));
            x1 = x2;
            y1 = y2;
        }
    }

    /**public static void main(String[] args) {
        JFrame frame = new JFrame("Polar Equation Grapher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(grapher);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }**/
}
