package model;
// 代表一个小格子，是否有棋子有ChessPiece决定
//
import java.io.Serializable;
/**
 * This class describe the slot for Chess in Chessboard
 * */
public class Cell implements Serializable {
    // the position for chess
    private ChessPiece piece;


    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public void removePiece() {
        this.piece = null;
    }
}//1. 胜利之后停止 2.显示可走棋子（） 3.保存
