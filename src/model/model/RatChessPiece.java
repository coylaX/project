package model;

public class RatChessPiece extends ChessPiece{
    public RatChessPiece(PlayerColor owner, String name){
        super(owner, name);
        disPlayName = "鼠";
        rank = 1;
    }

    @Override
    public boolean isValidMove(ChessboardPoint target) {
        boolean b = true;


        return b;
    }
    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest){
        return false;
    }
}
