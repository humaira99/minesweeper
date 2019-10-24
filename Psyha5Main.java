import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Psyha5Main extends Board {

	public static void main(String[] args) {

		new Psyha5Main();
	}


	private Psyha5Main() {

		JFrame guiFrame = new JFrame();

		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Minesweeper");

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(height, width));

		int i, j;

		for (i = 0; i < height; i++) {
			for (j = 0; j < width; j++) {
				buttons[i][j] = new JButton();
				JButton button = buttons[i][j];
				BoardSquareButton(button, i, j);
				panel.add(button);
				button.addMouseListener(new MyMouseListener());

			}
		}

		createMines();

		guiFrame.add(panel, BorderLayout.CENTER);
		guiFrame.pack();
		guiFrame.setVisible(true);

	}

	public class MyMouseListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {


			if (SwingUtilities.isLeftMouseButton(e)) {

				int i, j;

				for (i = 0; i < height; i++) {
					for (j = 0; j < width; j++) {
						if (getButton(i, j) == e.getSource()) {
							JButton button = getButton(i, j);

							if (checkMine(i, j)) {
								button.setText("X");
								button.setBackground(Color.ORANGE);
								finished();
								JOptionPane.showMessageDialog(null, "You lose, Press OK to restart");
								new Psyha5Main();
							}
							if (!checkMine(i, j)) {

								if (countSurrounding(i, j) == 0) {
									investigate(i, j);
								}
								button.setText(String.valueOf(countSurrounding(i, j)));
								button.setBackground(Color.GREEN);
								done[i][j] = true;
							}

							if (hasWon()) {
								for (i = 0; i < height; i++) {
									for (j = 0; j < width; j++) {
										if (mine[i][j]) {
											JButton b = buttons[i][j];
											b.setText("X");
											b.setBackground(Color.RED);
										}
									}
								}
								JOptionPane.showMessageDialog(null, "You win! Press OK to play again");
								new Psyha5Main();
							}
						}


					}
				}
			}

			if (SwingUtilities.isRightMouseButton(e)) {

				int i, j;

				for (i = 0; i < height; i++) {
					for (j = 0; j < width; j++) {
						if (getButton(i, j) == e.getSource()) {
							JButton button = getButton(i, j);

							if (!click[i][j]) {
								click[i][j] = true;
								button.setBackground(Color.RED);
							} else if (click[i][j]) {
								click[i][j] = false;
								button.setBackground(Color.GRAY);
							}


						}
					}
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}


	}
}
	
		
 