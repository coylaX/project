package model;

public class WolfChessPiece extends ChessPiece{
    public WolfChessPiece(PlayerColor owner, String name){
        super(owner, name);
        disPlayName = "狼";
        rank = 4;
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
