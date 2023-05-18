package model;

public class TigerChessPiece extends ChessPiece{
    public TigerChessPiece(PlayerColor owner, String name){
        super(owner, name);
        disPlayName = "虎";
        rank = 6;
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
