package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class store the real chess information.
 * The Chessboard has 9*7 cells, and each cell has a position for chess
 */
public class Chessboard {
    public Cell[][] grid;

    public Chessboard() {
        this.grid =
                new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];//19X19

        initGrid();
        initPieces();
    }

    private void initGrid() {//初始化棋盘
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void removeAllPieces(){//清除所有的棋子
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(grid[i][j].getPiece()!=null)
                    grid[i][j].setPiece(null);
            }
        }
    }
    public void initPieces() {//初始化棋子
        grid[6][0].setPiece(new ElephantChessPiece(PlayerColor.BLUE, "Elephant"));
        grid[2][6].setPiece(new ElephantChessPiece(PlayerColor.RED, "Elephant"));
        grid[8][6].setPiece(new LionChessPiece(PlayerColor.BLUE, "Lion"));
        grid[0][0].setPiece(new LionChessPiece(PlayerColor.RED, "Lion"));
        grid[8][0].setPiece(new TigerChessPiece(PlayerColor.BLUE, "Tiger"));
        grid[0][6].setPiece(new TigerChessPiece(PlayerColor.RED, "Tiger"));
        grid[6][4].setPiece(new LeopardChessPiece(PlayerColor.BLUE, "Leopard"));
        grid[2][2].setPiece(new LeopardChessPiece(PlayerColor.RED, "Leopard"));
        grid[6][2].setPiece(new WolfChessPiece(PlayerColor.BLUE, "Wolf"));
        grid[2][4].setPiece(new WolfChessPiece(PlayerColor.RED, "Wolf"));
        grid[7][5].setPiece(new DogChessPiece(PlayerColor.BLUE, "Dog"));
        grid[1][1].setPiece(new DogChessPiece(PlayerColor.RED, "Dog"));
        grid[7][1].setPiece(new CatChessPiece(PlayerColor.BLUE, "Cat"));
        grid[1][5].setPiece(new CatChessPiece(PlayerColor.RED, "Cat"));
        grid[6][6].setPiece(new RatChessPiece(PlayerColor.BLUE, "Rat"));
        grid[2][0].setPiece(new RatChessPiece(PlayerColor.RED, "Rat"));
    }
        //从文件中初始化棋子
    public void initPiecesFromFiles(List<String> lines) {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
      if(lines.get(i).charAt(j)=='相')
          grid[i][j].setPiece(new ElephantChessPiece(PlayerColor.BLUE,"Elephant"));
      else if(lines.get(i).charAt(j)=='象')
          grid[i][j].setPiece(new ElephantChessPiece(PlayerColor.RED,"Elephant"));
      else if(lines.get(i).charAt(j)=='獅')
          grid[i][j].setPiece(new LionChessPiece(PlayerColor.BLUE, "Lion"));
      else if(lines.get(i).charAt(j)=='狮')
          grid[i][j].setPiece(new LionChessPiece(PlayerColor.RED, "Lion"));
      else if(lines.get(i).charAt(j)=='琥')
          grid[i][j].setPiece(new TigerChessPiece(PlayerColor.BLUE, "Tiger"));
      else if(lines.get(i).charAt(j)=='虎')
          grid[i][j].setPiece(new TigerChessPiece(PlayerColor.RED, "Tiger"));
      else if(lines.get(i).charAt(j)=='犳')
          grid[i][j].setPiece(new LeopardChessPiece(PlayerColor.BLUE, "Leopard"));
      else if(lines.get(i).charAt(j)=='豹')
          grid[i][j].setPiece(new LeopardChessPiece(PlayerColor.RED, "Leopard"));
      else if(lines.get(i).charAt(j)=='琅')
          grid[i][j].setPiece(new WolfChessPiece(PlayerColor.BLUE, "Wolf"));
      else if(lines.get(i).charAt(j)=='狼')
          grid[i][j].setPiece(new WolfChessPiece(PlayerColor.RED, "Wolf"));
      else if(lines.get(i).charAt(j)=='豿')
          grid[i][j].setPiece(new DogChessPiece(PlayerColor.BLUE, "Dog"));
      else if(lines.get(i).charAt(j)=='狗')
          grid[i][j].setPiece(new DogChessPiece(PlayerColor.RED, "Dog"));
      else if(lines.get(i).charAt(j)=='貓')
          grid[i][j].setPiece(new CatChessPiece(PlayerColor.BLUE, "Cat"));
      else if(lines.get(i).charAt(j)=='猫')
          grid[i][j].setPiece(new CatChessPiece(PlayerColor.RED, "Cat"));
      else if(lines.get(i).charAt(j)=='黍')
          grid[i][j].setPiece(new RatChessPiece(PlayerColor.BLUE, "Rat"));
      else if(lines.get(i).charAt(j)=='鼠')
          grid[i][j].setPiece(new RatChessPiece(PlayerColor.RED, "Rat"));
            }
        }
    }

     public ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }
                            //根据坐标得到棋子

  public ChessboardPoint getChessPiecePointofJump(ChessPiece chessPiece){//得到棋子(狮或虎)当前坐标
        int a = 0;
        int b = 0;
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(grid[i][j].getPiece()!=null&&(grid[i][j].getPiece().rank==7||grid[i][j].getPiece().rank==6)){
                    a = i; b = j;
                }
            }
        }
        ChessboardPoint wantedpoint = new ChessboardPoint(a,b);
        return wantedpoint;
    }
    private Cell getGridAt(ChessboardPoint point) {
        return grid[point.getRow()][point.getCol()];
    }
                //根据坐标得到Cell
    private int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }
                    //计算两个棋子之间还差多少步
    private ChessPiece removeChessPiece(ChessboardPoint point) {//将某点的棋子移除掉，并返回这个坐标处的棋子
        ChessPiece chessPiece = getChessPieceAt(point);
        getGridAt(point).removePiece();
        return chessPiece;
    }

    private void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        getGridAt(point).setPiece(chessPiece);
    }
                    //在确定坐标处放入确定棋子
    public void moveChessPiece(ChessboardPoint src, ChessboardPoint dest) {//移动给定坐标处的棋子
        if (!isValidMove(src,dest)) {
            throw new IllegalArgumentException("Illegal chess move!");
        }
        setChessPiece(dest, removeChessPiece(src));
    }

    public void captureChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!isValidCapture(src, dest)) {
            throw new IllegalArgumentException("Illegal chess capture!");
        }
        // TODO: Finish the method.
        setChessPiece(dest,removeChessPiece(src));
    }

    public Cell[][] getGrid() {
        return grid;
    }
    public PlayerColor getChessPieceOwner(ChessboardPoint point) {
        return getGridAt(point).getPiece().getOwner();
    }


    public boolean isBlocked(ChessboardPoint src, ChessboardPoint dest){
        boolean b = false;
        if(src.getCol()==dest.getCol()){
            if(src.getRow()> dest.getRow()){
                for (int i = dest.getRow()+1; i < src.getRow() ; i++) {
                    if(grid[i][src.getCol()].getPiece()!=null)
                        b = true;
                }
            }
            else {
                for (int i = src.getRow()+1; i <dest.getRow() ; i++) {
                    if(grid[i][src.getCol()].getPiece()!=null)
                        b=true;

                }
            }
        }
        if(src.getRow()==dest.getRow()){
            if(src.getCol()>dest.getCol()){
                for (int i = dest.getCol()+1; i < src.getCol(); i++) {
                    if(grid[src.getRow()][i].getPiece()!=null)
                        b=true;
                }
            }
            else {
                for (int i = src.getCol()+1; i <dest.getCol() ; i++) {
                    if(grid[src.getRow()][i].getPiece()!=null)
                        b=true;
                }
            }
        }
        return b;
    }

    public boolean isValidMove(ChessboardPoint src, ChessboardPoint dest) {
        if (getChessPieceAt(src) == null || getChessPieceAt(dest) != null) {
            return false;
        }
        else if(getChessPieceAt(src)!=null&&getChessPieceOwner(src)==PlayerColor.RED&& dest.isRedHen())
            return false;
        else if(getChessPieceAt(src)!=null&&getChessPieceOwner(src)==PlayerColor.BLUE&& dest.isBlueHen())
            return false;
        else if(!getChessPieceAt(src).isValidMove(dest))
            return false;
        else if(getChessPieceAt(src).rank==7||getChessPieceAt(src).rank==6){//判断是否为能够跳河的动物
            if(src.isBesideRiver()){//判断是否在河边
                if(src.getCol()== dest.getCol()){
                    if(Math.abs(src.getRow()- dest.getRow())==4&&(!isBlocked(src,dest)))
                        return true;
                }
                if(src.getRow()== dest.getRow()){
                    if(Math.abs(src.getCol()- dest.getCol())==3&&(!isBlocked(src,dest)))
                        return true;
                }
            }
        }
        return calculateDistance(src, dest) == 1;
    }

    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest) {
        // TODO:Fix this method
        boolean b = false;
        boolean bb = false;//判断是否可移动
     if(getChessPieceAt(src).rank==7||getChessPieceAt(src).rank==6){//判断是否为能够跳河的动物
            if(src.isBesideRiver()){//判断是否在河边
                if(src.getCol()== dest.getCol()){
                    if(Math.abs(src.getRow()- dest.getRow())==4&&(!isBlocked(src,dest)))
                        bb= true;
                }
                if(src.getRow()== dest.getRow()){
                    if(Math.abs(src.getCol()- dest.getCol())==3&&(!isBlocked(src,dest)))
                        bb= true;
                }
            }
        }
     if (calculateDistance(src, dest) == 1) {
            bb=true;
        }


        if(getChessPieceOwner(src)!=getChessPieceOwner(dest)&&bb){
            if(getChessPieceAt(src).rank>=getChessPieceAt(dest).rank
                    &&(!(getChessPieceAt(src).rank==8&&getChessPieceAt(dest).rank==1)))
                b = true;
            if(getChessPieceAt(src).rank==1&&getChessPieceAt(dest).rank==8&& !src.isRiver())
                b = true;
            if(getChessPieceOwner(dest)==PlayerColor.RED&& dest.isBlueTrap())
                b = true;
            if(getChessPieceOwner(dest)==PlayerColor.BLUE&&dest.isRedTrap())
                b = true;
        }


        return b;
    }

    public boolean isBLUEWin(){
        boolean b = true;
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(grid[i][j].getPiece()!=null&&grid[i][j].getPiece().getOwner()==PlayerColor.RED)
                    b = false;
            }
        }
        boolean bb = false;
        if(grid[0][3].getPiece()!=null&&grid[0][3].getPiece().getOwner()==PlayerColor.BLUE)
            bb = true;
        return b||bb;
    }
    public boolean isREDWin(){
        boolean b = true;
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(grid[i][j].getPiece()!=null&&grid[i][j].getPiece().getOwner()==PlayerColor.BLUE)
                    b = false;
            }
        }
        boolean bb = false;
        if(grid[8][3].getPiece()!=null&&grid[8][3].getPiece().getOwner()==PlayerColor.RED)
            bb = true;
        return b||bb;
    }


        //棋子合法移动的选择
    public ArrayList<ChessboardPoint> isPossibleMove(ChessboardPoint src){
        ArrayList p = new ArrayList<ChessboardPoint>();
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint point =new ChessboardPoint(i,j);
                if(isValidMove(src,point)||isValidCapture(src,point))
                    p.add(point);
            }
        }
        return p;
    }


}

