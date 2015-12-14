package practice.oldschool;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Created by yichen on 10/4/15.
 */
public class Pascal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 7226798677259002855L;
    JPanel panel;
    JTextField textField;
    JButton button;
    ResultCanvas resultCanvas;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            int n = Integer.parseInt(textField.getText());

            resultCanvas.setR(n);

        } catch (NumberFormatException e) {
            JDialog dialog = new JDialog(this, "Error", true);
            JLabel errorLabel = new JLabel("Please enter a number.");
            dialog.add(errorLabel);
            dialog.pack();
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
            textField.setText("");
            textField.grabFocus();
        }

    }

    private class ResultCanvas extends Canvas {
        private static final long serialVersionUID = -6124101721065267501L;
        int r;
        int width;
//      int height;


        public ResultCanvas() {
            this(0, 640, 440);
        }

        public ResultCanvas(int r, int width, int height) {
            this.r = r;
            this.width = width;
//            this.height = height;
        }

        @Override
        public void paint(Graphics g) {
            if (r == 0) {
                return;
            }

            int fontSize = 16;
            int center = width / 2;
            int lineHeight = (int)(fontSize * 1.5);
            int space = fontSize * (r / (fontSize / 4)  + 1);

            g.setFont(new Font("Arial", Font.PLAIN, fontSize));

            g.drawString("1", center, lineHeight);

            for (int i = 1; i <= r; i++) {
                for (int j = 0; j <= i; j++) {
                    g.drawString(String.valueOf(nonRecursivePascal(i, j)), center - (int)((i/2.0-j)*space), (i+1) * lineHeight);
                }
            }
        }

        public void setR(int r) {
            this.r = r;
            this.repaint();
        }
    }

    static int recursivePascal(int r, int n) {
        if (n == 0 || n == r) {
            return 1;
        }
        if (n ==1 || n == r-1) {
            return r;
        }
        return recursivePascal(r, n-1) * (r - n + 1) / n;
    }

    static int nonRecursivePascal(int r, int n) {
        if (n == 0 || n == r) {
            return 1;
        }

        if (n == 1 || n == r-1) {
            return r;
        }

        int ans = 1;

        for (int i = 1; i <= n; i++) {
            ans = ans * (r- i + 1) / i;
        }

        return ans;
    }

    public Pascal() {

        this.setTitle("Pascal");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 480);

        this.setLayout(new BorderLayout());

        panel = new JPanel();
        this.add("North", panel);

        JLabel label = new JLabel("Enter r: ");
        panel.add(label);

        textField = new JTextField("", 10);
        panel.add(textField);

        button = new JButton("Go");
        button.addActionListener(this);
        panel.add(button);


        resultCanvas = new ResultCanvas();
        this.add("Center", resultCanvas);

        this.setVisible(true);
    }


    public static void main(String[] args) {
        new Pascal();

    }

}
