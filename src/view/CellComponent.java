package view;

import javax.swing.*;
import java.awt.*;

/**
 * This is the equivalent of the Cell class,
 * but this class only cares how to draw Cells on ChessboardComponent
 */

public class CellComponent extends JPanel {
    private Color background;
    private boolean validMove;
    private Color validMoveColor=Color.YELLOW;

    public CellComponent(Color background, Point location, int size) {
        setLayout(new GridLayout(1,1));
        setLocation(location);
        setSize(size, size);
        this.background = background;
    }

    public void setValidMove(boolean validMove) {
        this.validMove = validMove;
    }

    //绘制棋子
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        if(validMove){
            g.setColor(validMoveColor);
            g.fillRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);
        }else {
            g.setColor(background);
            g.fillRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);
        }
    }
}
