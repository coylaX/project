package model;

public class DogChessPiece extends ChessPiece{
    public DogChessPiece(PlayerColor owner, String name){
        super(owner, name);
        disPlayName = "狗";
        rank = 3;
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
