package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;

    private final int ONE_CHESS_SIZE;

    private int count;
    private ChessboardComponent chessboardComponent;
    public ChessGameFrame(int width, int height) {
        setTitle("斗兽棋"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;
        this.setAlwaysOnTop(true);

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        //initialize the menu

        addChessboard();
        setStepLabel();
        addRestartButton();
        addLoadButton();
        addSaveButton();
        addJMenu();
    }

    public ChessboardComponent getChessboardComponent() {
        return chessboardComponent;
    }

    public void setChessboardComponent(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        chessboardComponent = new ChessboardComponent(ONE_CHESS_SIZE);
        chessboardComponent.setLocation(WIDTH / 5, HEIGTH / 10-ONE_CHESS_SIZE/2);
        add(chessboardComponent);
    }

    /**
     * 在游戏面板中添加标签
     */
    public void setStepLabel() {
        JLabel statusLabel = new JLabel("回合数: "+count);
        statusLabel.setLocation(HEIGTH+40, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("楷体", Font.BOLD, 25));
        //每次增加回合了救引用这个方法
        statusLabel.setText(String.format("%s%d","回合数: ",++count));
        add(statusLabel);
    }

    //显示currentPlayer
    public void addPlayerLabel(){

    }
    //在controller中设置count回合数


    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */
    private void addRestartButton() {
        JButton button = new JButton("Restart");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    public void moveHints(){
        JOptionPane.showMessageDialog(this,"步骤违规，请重试！");
    }
    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
//
//        button.addActionListener(e -> {
//            System.out.println("Click load");
//            String path = JOptionPane.showInputDialog(this,"Input Path here");
//            gameController.loadGameFromFile(path);
//        });
   }
    private void addSaveButton() {
        JButton button = new JButton("Save");
        button.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addJMenu(){
        JMenuBar jMenuBar=new JMenuBar();

        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("说明");

        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登陆");
        JMenuItem closeItem = new JMenuItem("关闭游戏");

        JMenuItem ruleItem = new JMenuItem("规则");
        JMenuItem flagItem = new JMenuItem("标志说明");

        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(ruleItem);
        aboutJMenu.add(flagItem);

        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        this.setJMenuBar(jMenuBar);
        this.setVisible(true);
    }

    //在GameController中的Win（）执行
    private void addWinLabel(){
        JLabel winJLabel=new JLabel(new ImageIcon("CS109-2023-Sping-ChessDemo\\Imagine\\Victory.png"));
        winJLabel.setBounds(203,183,597,373);
        this.getContentPane().add(winJLabel);
    }
}
