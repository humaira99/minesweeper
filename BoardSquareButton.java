import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class BoardSquareButton extends JButton {

    private static int x_co;                                                    // x coordinate on the board
    private static int y_co;                                                    // y coordinate on the board
    static boolean[][] mine = new boolean[Board.height][Board.width];            // whether its a mine
    static boolean[][] done = new boolean[Board.height][Board.width];                // whether its been investigated yet (green and shows number of mines adjacent to it)
    static boolean[][] click = new boolean[Board.height][Board.width];            // whether the user suggested it is a potential mine (right clicked and gone red)


    JButton BoardSquareButton(JButton button, int i, int j) {

        //JButton button = new JButton();
        // size of button in pixels
        int height = 100;
        int width = 100;
        button.setSize(width, height);
        x_co = i;
        y_co = j;
        button.setBorderPainted(true);
        button.setPreferredSize(new Dimension(100, 100));
        button.setMinimumSize(new Dimension(60, 60));
        button.setFont(new Font("Arial", Font.BOLD, 40));
        Initialise(button);

        return button;
    }

    void Initialise(JButton button) {

        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(Color.GRAY);
        button.setText("?");

        done[x_co][y_co] = false;
        mine[x_co][y_co] = false;
        click[x_co][y_co] = false;

    }

    public void setMine(int i, int j) {

        mine[i][j] = true;

    }

    Boolean checkMine(int i, int j) {

        if (mine[i][j]) {
            return true;
        } else return false;
    }
}
