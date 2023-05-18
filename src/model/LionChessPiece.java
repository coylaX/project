package model;

public class LionChessPiece extends ChessPiece {
    public LionChessPiece(PlayerColor owner, String name) {
        super(owner, name);
        disPlayName = "狮";
        rank = 7;
    }

    @Override
    public boolean isValidMove(ChessboardPoint target) {
        boolean b = false;
        if (!target.isRiver())
            b = true;

        return b;
    }

    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest) {
        return false;
    }
}
