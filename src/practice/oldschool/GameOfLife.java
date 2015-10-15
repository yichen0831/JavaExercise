package practice.oldschool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by yichen on 10/7/15.
 *
 * Game of Life
 */
public class GameOfLife extends Canvas implements Runnable, ActionListener {

    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 10;

    private final int CELL_SIZE = 20;

    private float updateInterval;

    private JButton setButton;
    private JTextField updateIntervalText;

    private int[][] board = {
            {0, 1, 0, 1, 0, 1, 1, 0, 1, 1},
            {0, 1, 0, 1, 0, 1, 1, 0, 1, 1},
            {0, 1, 0, 1, 0, 0, 0, 0, 1, 1},
            {0, 1, 1, 1, 0, 0, 1, 0, 1, 1},
            {0, 1, 1, 1, 0, 1, 0, 0, 1, 1},
            {0, 1, 0, 1, 1, 0, 0, 1, 1, 1},
            {0, 1, 0, 1, 0, 1, 0, 0, 1, 1},
            {0, 1, 0, 1, 0, 0, 1, 0, 1, 1},
            {0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
            {0, 1, 0, 1, 1, 0, 0, 0, 1, 1}
    };

    public GameOfLife(JButton setButton, JTextField updateIntervalText) {
        this.setButton = setButton;
        this.setButton.addActionListener(this);
        this.updateIntervalText = updateIntervalText;
        this.updateIntervalText.setText("1.0");
        this.updateIntervalText.setColumns(5);
        this.updateIntervalText.addActionListener(this);
        updateInterval = 1.0f;

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.updateIntervalText || actionEvent.getSource() == this.setButton ) {
            try {
                updateInterval = Float.parseFloat(updateIntervalText.getText());

                if(updateInterval < 0.2f) {
                    updateInterval = 0.2f;
                    updateIntervalText.setText("0.2");
                }
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Not a number!", "Error", JOptionPane.ERROR_MESSAGE);
                updateIntervalText.grabFocus();
            }
        }
    }

    private int checkNext(int x, int y) {
        int result = 0;

        for (int i = x-1; i < x+2; i++) {
            for (int j = y-1; j < y+2; j++) {
                if (i < 0 || i >= BOARD_WIDTH) {
                    break;
                }

                if(j < 0 || j >= BOARD_HEIGHT) {
                    continue;
                }

                if (i == x && j == y) {
                    continue;
                }

                if (board[i][j] == 1) {
                    result++;
                }

            }
        }
        return result;
    }

    private void nextLife() {
        int[][] nextBoard = new int[BOARD_WIDTH][BOARD_HEIGHT];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int next = checkNext(i, j);
                if (next <= 1 || next >= 4) {
                    nextBoard[i][j] = 0;
                }
                else if (next == 2) {
                    nextBoard[i][j] = board[i][j];
                }
                else {
                    nextBoard[i][j] = 1;
                }

            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = nextBoard[i][j];
            }
        }


    }

    @Override
    public void run() {

        while(true) {
            try {
                Thread.sleep((long) (updateInterval * 1000));

                nextLife();

                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void paint(Graphics g) {

        int topPad = 10;
        int leftPad = WIDTH / 2 - BOARD_WIDTH * CELL_SIZE / 2;

        g.setColor(Color.BLACK);
        for (int i = 0; i <= BOARD_HEIGHT; i++) {
            g.drawLine(leftPad, topPad + CELL_SIZE * i, leftPad + BOARD_WIDTH * CELL_SIZE, topPad + CELL_SIZE * i);
        }

        for (int i = 0; i <= BOARD_WIDTH; i++) {
            g.drawLine(leftPad + CELL_SIZE * i, topPad, leftPad + CELL_SIZE * i, topPad + BOARD_HEIGHT * CELL_SIZE);
        }

        g.setColor(Color.BLUE);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    g.fillOval(j * CELL_SIZE + leftPad,  i * CELL_SIZE + topPad, CELL_SIZE, CELL_SIZE);
                }
            }
        }

    }

    public static void main(String[] args) {

        JFrame mainFrame = new JFrame("Game of Life");
        mainFrame.setTitle("Game of Life");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel updateIntervalLabel = new JLabel("Update interval: ");
        panel.add(updateIntervalLabel);

        JTextField updateIntervalText = new JTextField();
        panel.add(updateIntervalText);

        JButton setButton = new JButton("Set");
        panel.add(setButton);

        GameOfLife gameOfLife = new GameOfLife(setButton, updateIntervalText);
        Thread thread = new Thread(gameOfLife);

        mainFrame.add("North", panel);
        mainFrame.add("Center", gameOfLife);

        mainFrame.setVisible(true);

        thread.run();
    }

}
