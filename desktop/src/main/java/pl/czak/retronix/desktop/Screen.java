package pl.czak.retronix.desktop;

import pl.czak.retronix.Board;
import pl.czak.retronix.Board.Field;
import pl.czak.retronix.GameRenderer;
import pl.czak.retronix.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by czak on 24/02/16.
 */
public class Screen extends JPanel implements GameRenderer {
    private Board board;
    private Player player;

    public Screen() {
        setPreferredSize(new Dimension(500, 300));
        setBackground(Color.BLACK);
    }

    @Override
    public void render(Board board, Player player) {
        this.board = board;
        this.player = player;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Single field size
        final double FIELD_SIZE = Math.min((double) getWidth() / board.getWidth(),
                (double) getHeight() / board.getHeight());

        // Offset to center in window
        final double TX = ((double) getWidth() - FIELD_SIZE * board.getWidth()) / 2;
        final double TY = ((double) getHeight() - FIELD_SIZE * board.getHeight()) / 2;

        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D.Double rect = new Rectangle2D.Double(TX, TY, FIELD_SIZE, FIELD_SIZE);

        // Draw the board
        for (Field[] row : board.getFields()) {
            rect.x = TX;
            for (Field f : row) {
                g2.setColor(f == Field.LAND ? Color.GREEN : Color.BLUE);
                g2.fill(rect);
                rect.x += FIELD_SIZE;
            }
            rect.y += FIELD_SIZE;
        }

        // Draw the player
        rect.x = TX + player.getX() * FIELD_SIZE;
        rect.y = TY + player.getY() * FIELD_SIZE;
        g2.setColor(Color.MAGENTA);
        g2.fill(rect);
    }
}