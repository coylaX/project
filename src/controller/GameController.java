package controller;


import listener.GameListener;
import model.*;
import view.CellComponent;
import view.ChessComponent;
import view.ChessGameFrame;
import view.ChessboardComponent;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and onPlayerClickChessPiece()]
 *
*/
public class GameController implements GameListener {


    private Chessboard model;
    private ChessboardComponent view;
    private PlayerColor currentPlayer;

    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;
    private ChessGameFrame frame;
    private int StepCount =1;
    private List<Step> PieceStep=new ArrayList<Step>();
    private List<ChessboardPoint> validMoves=new ArrayList<>();
    public GameController(ChessboardComponent view, Chessboard model) {
        this.view = view;
        this.model = model;
        this.currentPlayer = PlayerColor.BLUE;

        view.registerController(this);
        initialize();
        view.initiateChessComponent(model);
        view.repaint();
    }

    private void initialize() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {

            }
        }
    }

    public void setFrame(ChessGameFrame frame) {
        this.frame = frame;
    }

    // after a valid move swap the player
    private void swapColor() {
        currentPlayer = currentPlayer == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE;
        frame.viewCurrentPlayer();
    }
//改成了Public
    public boolean win() {
        // TODO: Check the board if there is a winner
        boolean b = false;
        if(model.isBLUEWin()|| model.isREDWin())
            b = true;
        return b;
    }


    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
        if(!win()){
            if (selectedPoint != null && model.isValidMove(selectedPoint, point)) {

                hideValidMoves();

                //controller里更新步数
                this.StepCount++;
                //步骤List里添加当前步骤
                Step step =new Step(selectedPoint,point,model.getChessPieceAt(selectedPoint),
                        null,getCurrentPlayer(),this.StepCount);
                this.PieceStep.add(step);
                //实体中移动棋子
                model.moveChessPiece(selectedPoint, point);
                //画面上移动棋子
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                //归零
                selectedPoint = null;
                //切换棋手
                swapColor();
                //重绘制
                view.repaint();
                // ？？？TODO: if the chess enter Dens or Traps and so on
                //更新回合数
                frame.viewCount();
                if(win()){
                    if(model.isREDWin())
                        frame.redWinDialog();
                    else if(model.isBLUEWin())
                        frame.blueWinDialog();
                }
            }else if(selectedPoint != null && !model.isValidMove(selectedPoint, point)){
                frame.moveHints();
            }
            //如果胜利弹出胜利窗口
        }else{
            if(win()){
                if(model.isREDWin())
                    frame.redWinDialog();
                else if(model.isBLUEWin())
                    frame.blueWinDialog();
                frame.restartHints();
            }
        }

    }

    // click a cell with a chess
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, ChessComponent component) {
        if(!win()){
            if (selectedPoint == null) {//当前所选的格子没有棋子
                if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                    selectedPoint = point;
                    component.setSelected(true);
                    component.repaint();

                    showValidMoves(point);
                }
            } else if (selectedPoint.equals(point)) {//当前所选格子与鼠标点击的格子相同
                hideValidMoves();

                selectedPoint = null;
                component.setSelected(false);
                component.repaint();
            }
            // TODO: Implement capture function
            else if(selectedPoint!=null&&selectedPoint!=point&&model.isValidCapture(selectedPoint,point)){
                hideValidMoves();

                //controller 里更新步数
                this.StepCount++;
                //步骤List里添加当前步骤
                Step step =new Step(selectedPoint,point,model.getChessPieceAt(selectedPoint),
                        model.getChessPieceAt(point), getCurrentPlayer(),this.StepCount);
                this.PieceStep.add(step);
                //model中移除棋子
                model.getGrid()[point.getRow()][point.getCol()].removePiece();
                //显示中移除棋子
                view.removeChessComponentAtGrid(point);
                //实体中移动棋子
                model.moveChessPiece(selectedPoint, point);
                //画面上移动棋子
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                //归零
                selectedPoint = null;
                //切换棋手
                swapColor();
                //重绘制
                view.repaint();
                //更新回合数
                frame.viewCount();
                if(win()){
                    if(model.isREDWin())
                        frame.redWinDialog();
                    else if(model.isBLUEWin())
                        frame.blueWinDialog();
                }
            }else if(selectedPoint!=null&&selectedPoint!=point&&!model.isValidCapture(selectedPoint,point)){
                frame.moveHints();
            }
        }//如果胜利弹出胜利窗口
        else{
            if(win()){
                if(model.isREDWin())
                    frame.redWinDialog();
                else if(model.isBLUEWin())
                    frame.blueWinDialog();
                frame.restartHints();
            }
        }
    }


    //展示可移动棋子
    public void showValidMoves(ChessboardPoint point) {
        //test:是否是显示的问题，如果不出错则是数列返回值的问题
        /**测试结果：返回数列的问题
        ChessboardPoint test=new ChessboardPoint(2,2);
        validMoves.add(test);
        **/
        validMoves = model.isPossibleMove(point);
        view.showValidMoves(validMoves);
    }
    public void hideValidMoves() {
        view.hideValidMoves(validMoves);
    }


    public void restartGame(){//view里有需要这个方法的地方
        model.removeAllPieces();//model中清除所有棋子
        model.initPieces();//model中添加初始化棋子
        view.removeAllPieces();//view中清除所有绘制过的棋子
        view.initiateChessComponent(model);//view中根据现阶段model的内容重新add棋子
        view.repaint();//
        this.currentPlayer = PlayerColor.BLUE;
        frame.viewCurrentPlayer();
        frame.setStepAndCount();
    }

    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }


    /*load方法
    * 1. 读取文件
    * 2. 将文件里的内容转化成棋盘
    * 3. model 清除所有棋子
    * 4. model 根据文件内容初始化棋子
    * 5. view中清除所有绘制棋子
    * 6. view中根据现阶段model的内容重新add棋子
    * 7. view.repaint()
    * */
    public void loadGameFromFile(String path){
        try{
            List<String> lines = Files.readAllLines(Path.of(path));
            //错误判断

            if(!isRightForm(path.toString())){
                //101报错

            }
            else if(!isRightChessboard(lines)){
                //102报错

            }
            else if(!isRightChessPiece(lines)){
                //103报错

            }
            else if (!hasCount(lines)) {
                //104报错
            }
            else {
                for (String s:lines) {
                    System.out.println(s);
                }

                model.removeAllPieces();
                model.initPiecesFromFiles(lines);
                view.removeAllPieces();
                view.initiateChessComponent(model);
                view.repaint();
                int a = Integer.parseInt(lines.get(lines.size()-1));
                if(a%2==0)
                    this.currentPlayer=PlayerColor.RED;
                else
                    this.currentPlayer=PlayerColor.BLUE;
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public boolean isRightForm(String in)
    {
        if(in.length()<4)
            return false;
        String tmp=in.substring(in.length()-4,in.length());
        if(!tmp.toLowerCase().equals(".txt"))
            return false;
        return true;
    }
    public boolean isRightChessboard(List<String> Lines){
        boolean b = true;
        List<String> lines = new ArrayList<String>();
        for (int i = 0; i < Lines.size(); i++) {
            String l = Lines.get(i);
            lines.add(l);
        }
        lines.remove(9);
        if(lines.size()!=9)
            b = false;
        else {
            for (int i = 0; i < 9; i++) {
                char[] test = lines.get(i).toCharArray();
                if(test.length!=7)
                    b = false;
            }
        }
        return b;
    }
    public boolean isRightChessPiece(List<String> lines){
        boolean b = true;
        for (int i = 0; i < 9; i++) {
            char[] test = lines.get(i).toCharArray();
            for (int j = 0; j < 7; j++) {
                if(test[j]!='空'&&test[j]!='相'&&test[j]!='象'&&test[j]!='獅'&&test[j]!='狮'&&test[j]!='琥'
                        &&test[j]!='虎'&&test[j]!='犳'&&test[j]!='豹'&&test[j]!='琅'&&test[j]!='狼'&&test[j]!='豿'
                        &&test[j]!='狗'&&test[j]!='貓'&&test[j]!='猫'&&test[j]!='黍'&&test[j]!='鼠')
                    b = false;
            }
        }
        return b;
    }
    public boolean hasCount(List<String> Lines){
        boolean b = true;
        if(Lines.size()==9){
            b = false;
        }

        else if (Lines.size()>9&&Lines.get(9)!=null) {
            int count = Integer.parseInt(Lines.get(9));
            if(count<1){
                b = false;
            }


        }
        return b;
    }

    //悔棋操作
    public void withdraw(){
        Step last = this.PieceStep.get(this.PieceStep.size()-1);
        //删除PieceStep List中的最后一个
        this.PieceStep.remove(this.PieceStep.size()-1);
        this.StepCount--;
        if(last.getEndChessPiece()==null){
            //model层面移回棋子
            model.moveChessPiece(last.getEnd(), last.getStart());
            //????????view层面移回棋子
            view.setChessComponentAtGrid(last.getStart(), view.removeChessComponentAtGrid(last.getEnd()));
        }
        else if(last.getEndChessPiece()!=null){
            //model层面移回棋子
            model.moveChessPiece(last.getEnd(), last.getStart());
            //????????view层面移回棋子
            view.setChessComponentAtGrid(last.getStart(), view.removeChessComponentAtGrid(last.getEnd()));
            //model层面将被吃棋子复原回去
            model.getGrid()[last.getEnd().getRow()][last.getEnd().getCol()].setPiece(last.getEndChessPiece());
            //?????????view层面将被吃棋子复原回去
            ChessComponent beCapturedChess =  new ChessComponent(last.getEndChessPiece().getOwner(),
                    view.getCHESS_SIZE(),last.getEndChessPiece().getDisPlayName());
            view.setChessComponentAtGrid(last.getEnd(), beCapturedChess);

        }
        this.currentPlayer=last.getCurrentPlayer();
    }



    /* save方法
     * 1.
     * 2. 读取棋盘，将棋盘的棋子转化成相应的汉字，放在一个String类型的ArrayList数组里
     * 3. 将步数加入到ArrayList中
     * 4. 把ArrayList存储到创建好的文件中
     * 5.
     * 6.
     * 7.
     * */
    public void saveGameIntoFile(String path){
        try {
            ArrayList<String> saveGame = new ArrayList<String>();
            saveGame = model.saveChessboardIntoFiles();
            String s = this.StepCount+"";
            saveGame.add(s);
            for (int i = 0; i < saveGame.size(); i++) {
                System.out.println(saveGame.get(i));
            }
            Files.write(Path.of(path),saveGame, Charset.defaultCharset());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }




}
