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
        //将棋盘存入文件中
    public ArrayList<String> saveChessboardIntoFiles(){
       ArrayList<String> saveChessboard = new ArrayList<>() ;
        char[][] save = new char[9][7];
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(this.grid[i][j].getPiece()==null)
                    save[i][j] = '空';
                else if(this.grid[i][j].getPiece()!=null){
                    if(this.grid[i][j].getPiece().getOwner()==PlayerColor.RED) {
                        if(this.grid[i][j].getPiece().getDisPlayName()=="象")
                            save[i][j]='象';
                        else if(this.grid[i][j].getPiece().getDisPlayName()=="狮")
                            save[i][j]='狮';
                        else if(this.grid[i][j].getPiece().getDisPlayName()=="虎")
                            save[i][j]='虎';
                        else if(this.grid[i][j].getPiece().getDisPlayName()=="豹")
                            save[i][j]='豹';
                        else if(this.grid[i][j].getPiece().getDisPlayName()=="狼")
                            save[i][j]='狼';
                        else if(this.grid[i][j].getPiece().getDisPlayName()=="狗")
                            save[i][j]='狗';
                        else if(this.grid[i][j].getPiece().getDisPlayName()=="猫")
                            save[i][j]='猫';
                        else if(this.grid[i][j].getPiece().getDisPlayName()=="鼠")
                            save[i][j]='鼠';
                    }
                    else if(this.grid[i][j].getPiece().getOwner()==PlayerColor.BLUE){
                        if(this.grid[i][j].getPiece().getDisPlayName()=="象")
                            save[i][j]='相';
                        else if(this.grid[i][j].getPiece().getDisPlayName()=="狮")
                            save[i][j]='獅';
                        else if(this.grid[i][j].getPiece().getDisPlayName()=="虎")
                            save[i][j]='琥';
                        else if(this.grid[i][j].getPiece().getDisPlayName()=="豹")
                            save[i][j]='犳';
                        else if(this.grid[i][j].getPiece().getDisPlayName()=="狼")
                            save[i][j]='琅';
                        else if(this.grid[i][j].getPiece().getDisPlayName()=="狗")
                            save[i][j]='豿';
                        else if(this.grid[i][j].getPiece().getDisPlayName()=="猫")
                            save[i][j]='貓';
                        else if(this.grid[i][j].getPiece().getDisPlayName()=="鼠")
                            save[i][j]='黍';
                    }
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            saveChessboard.add(""+save[i][0]+save[i][1]+save[i][2]+save[i][3]+save[i][4]+save[i][5]+save[i][6]);
        }
        return saveChessboard;
    }

    //根据坐标得到棋子
     public ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }

    //得到当前棋子的坐标
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
        ChessboardPoint wantedPoint = new ChessboardPoint(a,b);
        return wantedPoint;
    }

    //根据坐标得到Cell
    private Cell getGridAt(ChessboardPoint point) {
        return grid[point.getRow()][point.getCol()];
    }


    //计算两个格子之间相差多少步
    private int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }


    //将某点的棋子移除掉，并返回这个坐标处的棋子
    private ChessPiece removeChessPiece(ChessboardPoint point) {
        ChessPiece chessPiece = getChessPieceAt(point);
        getGridAt(point).removePiece();
        return chessPiece;
    }


    //在确定坐标处放入确定棋子
    private void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        getGridAt(point).setPiece(chessPiece);
    }

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



    //判断河流中是否有棋子阻断跳河
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


    //判断是否是合法移动
    public boolean isValidMove(ChessboardPoint src, ChessboardPoint dest) {
        if (getChessPieceAt(src) == null || getChessPieceAt(dest) != null) {
            return false;
        }
        else if(getChessPieceAt(src)!=null&&getChessPieceOwner(src)==PlayerColor.RED&& dest.isRedDen())
            return false;
        else if(getChessPieceAt(src)!=null&&getChessPieceOwner(src)==PlayerColor.BLUE&& dest.isBlueDen())
            return false;
        else if(!getChessPieceAt(src).isValidMove(dest))
            return false;
        else if(getChessPieceAt(src).rank==7||getChessPieceAt(src).rank==6){//判断是否为能够跳河的动物
            if(src.isBesideRiver()){//判断是否在河边
                if(src.getCol()== dest.getCol()){
                    if(Math.abs(src.getRow()- dest.getRow())==4&&(!isBlocked(src,dest))&&isAllRiver(src, dest))
                        return true;
                }
                if(src.getRow()== dest.getRow()){
                    if(Math.abs(src.getCol()- dest.getCol())==3&&(!isBlocked(src,dest))&&isAllRiver(src, dest))
                        return true;
                }
            }
        }
        return calculateDistance(src, dest) == 1;
    }


    //判断是否是合法捕捉
    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest) {
        // TODO:Fix this method
        boolean b = false;
        boolean bb = false;//判断是否可移动
     if(getChessPieceAt(src).rank==7||getChessPieceAt(src).rank==6){//判断是否为能够跳河的动物
            if(src.isBesideRiver()){//判断是否在河边
                if(src.getCol()== dest.getCol()){
                    if(Math.abs(src.getRow()- dest.getRow())==4&&(!isBlocked(src,dest))&&isAllRiver(src, dest))
                        bb= true;
                }
                if(src.getRow()== dest.getRow()){
                    if(Math.abs(src.getCol()- dest.getCol())==3&&(!isBlocked(src,dest))&&isAllRiver(src, dest))
                        bb= true;
                }
            }
        }
     if (calculateDistance(src, dest) == 1) {
            bb=true;
        }


        if(getChessPieceOwner(src)!=getChessPieceOwner(dest)&&bb){
            if(getChessPieceAt(src).rank>=getChessPieceAt(dest).rank
                    &&(!(getChessPieceAt(src).rank==8&&getChessPieceAt(dest).rank==1))&&!dest.isRiver())
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


    public boolean isAllRiver(ChessboardPoint src, ChessboardPoint dest){
        boolean bb = true;
        if(src.getRow()==dest.getRow()){
            for (int i = Math.min(src.getCol(), dest.getCol())+1;
                 i < Math.max(src.getCol(), dest.getCol()); i++) {
                ChessboardPoint p =new ChessboardPoint(src.getRow(),i);
                if(!p.isRiver()){
                    bb = false;
                    break;
                }
            }
        }
        else if(src.getCol()==dest.getCol()){
            for (int i = Math.min(src.getRow(),dest.getRow())+1;
                 i < Math.max(src.getRow(),dest.getRow()); i++) {
                ChessboardPoint p = new ChessboardPoint(i, src.getCol());
                if(!p.isRiver())
                    bb = false;

            }
        }
        return bb;
    }

    //判断蓝方是否获胜
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

    //判断红方是否获胜
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
                if(getGrid()[i][j].getPiece()==null&&isValidMove(src,point)){
                    p.add(point);
                }
                else if(getGrid()[i][j].getPiece()!= null&&isValidCapture(src,point)){
                    p.add(point);
                }
            }
        }
        return p;
    }

    //得到当前玩家的所有棋子的坐标
    public List<ChessboardPoint> theValidPoints(PlayerColor color){
        List<ChessboardPoint> points = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                ChessboardPoint point = new ChessboardPoint(i, j);
                if (getChessPieceAt(point) != null && getChessPieceAt(point).getOwner() == color) {
                    points.add(point);
                }
            }
        }
        return points;
    }

    //得到所有合法移动的步
    public List<Step> getValidSteps(PlayerColor color,int turn){
        List<Step> allPossibleStep = new ArrayList<Step>();
        List<ChessboardPoint> points = theValidPoints(color);
        for (ChessboardPoint point : points) {
            List<ChessboardPoint> allPossibleMoves = isPossibleMove(point);
            for (ChessboardPoint destPoint : allPossibleMoves) {
                Step s = new Step(point, destPoint, getChessPieceAt(point),
                        getChessPieceAt(destPoint), color, turn);
                allPossibleStep.add(s);
            }
        }
        return allPossibleStep;
    }


    public List<Step> allLegalStepsIncludeValue(PlayerColor color){
        List<Step> allLegalSteps = new ArrayList<Step>();
        List<ChessboardPoint> allPossiblePoints = theValidPoints(color);
        for (ChessboardPoint point : allPossiblePoints) {
            List<ChessboardPoint> allPossibleMoves = isPossibleMove(point);
            for (ChessboardPoint destPoint : allPossibleMoves) {
                Step step =  new Step(point, destPoint, getChessPieceAt(point),getChessPieceAt(destPoint),
                        color, 0);

                //通过距离对方阵营和距离对方兽血的远近来量化value的大小
                if (color == PlayerColor.RED) {
                    step.setValue(destPoint.getRow() - point.getRow() + Math.abs(3 - point.getCol())
                            - Math.abs(3 - destPoint.getCol()));//距离对方阵营的远近, 距离对方兽血的远近
                } else {
                    step.setValue(point.getRow() - destPoint.getRow() + Math.abs(3 - point.getCol())
                            - Math.abs(3 - destPoint.getCol()));
                }
                //如果dest point里有棋子，value加dest棋子rank的平方
                if (getChessPieceAt(destPoint) != null) {
                    step.setValue(step.getValue() + getChessPieceAt(destPoint).getRank()*3);
                }
                //如果dest是蓝方的兽血，直接选择这一步
                if (destPoint.isBlueDen()) {
                    step.setValue(100);
                }
                //如果是蓝方的陷阱，则需要检查周围是否有蓝方棋子
                if (destPoint.isBlueTrap()) {
                    if (getChessPieceAt(destPoint) != null) {
                        step.setValue(getChessPieceAt(destPoint).getRank()*2);
                    } else {
                        boolean b = false;//检查周围棋子
                        ChessboardPoint p1 = new ChessboardPoint(destPoint.getRow()-1,destPoint.getCol());
                        ChessboardPoint p2 = new ChessboardPoint(destPoint.getRow()+1,destPoint.getCol());
                        ChessboardPoint p3 = new ChessboardPoint(destPoint.getRow(),destPoint.getCol()-1);
                        ChessboardPoint p4 = new ChessboardPoint(destPoint.getRow(),destPoint.getCol()+1);
                        if(p1.isOnChessboard()){
                            if(getChessPieceAt(p1)!=null&&getChessPieceOwner(p1)==PlayerColor.BLUE){
                                b = true;
                                step.setValue(-10);
                            }
                        }
                        if(p2.isOnChessboard()){
                            if(getChessPieceAt(p1)!=null&&getChessPieceOwner(p1)==PlayerColor.BLUE){
                                b = true;
                                step.setValue(-10);
                            }
                        }
                        if(p3.isOnChessboard()){
                            if(getChessPieceAt(p1)!=null&&getChessPieceOwner(p1)==PlayerColor.BLUE){
                                b = true;
                                step.setValue(-10);
                            }
                        }
                        if(p4.isOnChessboard()){
                            if(getChessPieceAt(p1)!=null&&getChessPieceOwner(p1)==PlayerColor.BLUE){
                                b = true;
                                step.setValue(-10);
                            }
                        }
                        if (!b) {
                            step.setValue(100);
                        }
                    }
                }
                allLegalSteps.add(step);
            }
        }
        return allLegalSteps;
    }



    public int valueFunction(ChessboardPoint target, ChessPiece srcPiece, PlayerColor destColor){
        int b = 0;
        b = -Math.abs(target.getRow()-8)-Math.abs(3-target.getCol())+20;//评估距离兽穴的远近
        b = b * srcPiece.getRank();//考虑当前棋子的rank

        return b;
    }





}

