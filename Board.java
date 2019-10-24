import java.awt.Color;
import java.util.Random;

import javax.swing.JButton;

public class Board extends BoardSquareButton {

    final static int width = 10;
    final static int height = 10;
    final private static int num_mines = 6;
    static JButton[][] buttons = new JButton[height][width];

    JButton getButton(int i, int j) {

        return buttons[i][j];
    }

    public void storeButton(int i, int j, JButton button) {

        button = buttons[i][j];
    }

    public void initialiseAll() {

        int i, j;

        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                Initialise(buttons[i][j]);
            }
        }
    }

    void createMines() {
        int i = 0;

        while (i < num_mines) {
            Random r = new Random();
            int p = r.nextInt(height);
            int q = r.nextInt(width);

            if (!mine[p][q]) {
                mine[p][q] = true;
                i++;
            }

        }

    }


    void finished() {

        int i, j;

        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                JButton button = buttons[i][j];
                if (!click[i][j] && mine[i][j]) {        // if there is a mine there but not been clicked
                    button.setBackground(Color.ORANGE);
                    button.setText("X");
                }
                if (!click[i][j] && !mine[i][j] && !done[i][j]) {    // no mine, not right clicked or left clicked (unexplored
                    button.setText(String.valueOf(countSurrounding(i, j)));
                }
                if (click[i][j] && !mine[i][j]) {
                    button.setText(String.valueOf(countSurrounding(i, j)));
                }
                if(click[i][j] && mine[i][j]){
                    button.setText("X");
                }
            }
        }
    }

    boolean hasWon() {

        int i, j;


        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                if (!mine[i][j] && !done[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }


    int countSurrounding(int i, int j) {
        int count;

        count = 0;

        count += mineAt(i - 1, j - 1);
        count += mineAt(i - 1, j);
        count += mineAt(i - 1, j + 1);
        count += mineAt(i, j - 1);
        count += mineAt(i, j + 1);
        count += mineAt(i + 1, j - 1);
        count += mineAt(i + 1, j);
        count += mineAt(i + 1, j + 1);

        return count;
    }

    int mineAt(int i, int j) {
        if (i >= 0 && i < height && j >= 0 && j < width && mine[i][j]) {
            return 1;
        } else return 0;
    }

    void investigate(int i, int j) {

        int p, q;

        for (p = i - 1; p <= i + 1; p++) {
            for (q = j - 1; q <= j + 1; q++) {
                if (p < height && p >= 0 && q < width && q >= 0) {
                    JButton b = getButton(p, q);
                    if (countSurrounding(p, q) == 0 && !done[p][q] && getButton(p, q) != getButton(i, j)) {
                        investigate(p, q);
                    } else {
                        b.setText(String.valueOf(countSurrounding(p, q)));
                        b.setBackground(Color.GREEN);
                        done[p][q] = true;
                    }
                }
            }
        }
    }


}


