package model;

public class CatChessPiece extends ChessPiece{
    public CatChessPiece(PlayerColor owner, String name){
        super(owner, name);
        disPlayName = "猫";
        rank = 2;
    }

    @Override
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
