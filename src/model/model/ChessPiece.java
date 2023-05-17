package model;


public abstract class ChessPiece {
    // the owner of the chess
    private PlayerColor owner;

    // Elephant? Cat? Dog? ...
    private String name;
    protected String disPlayName;
    protected int rank;

    public ChessPiece(PlayerColor owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public boolean canCapture(ChessPiece target) {
        // TODO: Finish this method!
        return false;
    }

    public String getName() {
        return name;
    }

    public PlayerColor getOwner() {
        return owner;
    }

    public String getDisPlayName() {
        return disPlayName;
    }



    public abstract boolean isValidMove(ChessboardPoint target);//不同棋子怎么move

    public abstract boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest);//src自己本身，dest对方棋子
                                                                                        //传递棋子或者point
}
