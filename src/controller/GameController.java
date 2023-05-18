package controller;


import listener.GameListener;
import model.Constant;
import model.PlayerColor;
import model.Chessboard;
import model.ChessboardPoint;
import view.CellComponent;
import view.ChessComponent;
import view.ChessboardComponent;

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

    // after a valid move swap the player
    private void swapColor() {
        currentPlayer = currentPlayer == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE;
    }

    private boolean win() {
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
            }
            //如果胜利弹出胜利窗口
            if(win()){
                if(model.isREDWin()){}
                if(model.isBLUEWin()){}
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
                }
            } else if (selectedPoint.equals(point)) {//当前所选格子与鼠标点击的格子相同
                selectedPoint = null;
                component.setSelected(false);
                component.repaint();
            }
            // TODO: Implement capture function
            else if(selectedPoint!=null&&selectedPoint!=point&&model.isValidCapture(selectedPoint,point)){
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
            }
            //如果胜利弹出胜利窗口
            if(win()){
                if(model.isREDWin()){}
                if(model.isBLUEWin()){}
            }
        }


    }
    public void restartGame(){//view里有需要这个方法的地方
        model.removeAllPieces();//model中清除所有棋子
        model.initPieces();//model中添加初始化棋子
        view.removeAllPieces();//view中清除所有绘制过的棋子
        view.initiateChessComponent(model);//view中根据现阶段model的内容重新add棋子
        view.repaint();//
        this.currentPlayer = PlayerColor.BLUE;
    }

    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }
}
