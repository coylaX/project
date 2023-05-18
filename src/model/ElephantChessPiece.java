package model;

public class ElephantChessPiece extends ChessPiece{
    public ElephantChessPiece(PlayerColor owner, String name){
        super(owner, name);
        disPlayName = "象";
        rank = 8;
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
