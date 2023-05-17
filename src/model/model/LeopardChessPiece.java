package model;

public class LeopardChessPiece extends ChessPiece{
    public LeopardChessPiece(PlayerColor owner, String name){
        super(owner, name);
        disPlayName = "豹";
        rank = 5;
    }
    public boolean isValidMove(ChessboardPoint target) {
        boolean b = false;
        if(!target.isRiver())
            b=true;

        return b;
    }
    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest){
        return false;
    }
}
