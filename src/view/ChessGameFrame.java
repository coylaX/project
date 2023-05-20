package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PlayerColor;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame implements ActionListener {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;

    private final int ONE_CHESS_SIZE;
    private int count=1;
    private int step=1;
    private ChessboardComponent chessboardComponent;
    private GameController controller;
    JButton restartButton = new JButton("Restart");
    JLabel playerLabel = new JLabel("当前回合:蓝方");
    JLabel statusLabel = new JLabel("回合数: " + count);

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
        addCurrentPlayer();
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
        chessboardComponent.setLocation(WIDTH / 5, HEIGTH / 10 - ONE_CHESS_SIZE / 2);
        add(chessboardComponent);
    }

    /**
     * 在游戏面板中添加标签
     */
    //保持数据一直更新？？？回合数和回合对象：调用一次view函数
    public void setStepLabel() {
        statusLabel.setLocation(HEIGTH + 40, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("楷体", Font.BOLD, 25));
        //每次增加回合了救引用这个方法
        add(statusLabel);
    }
    public void viewCount(){
        ++step;
        statusLabel.setText(String.format("%s%d", "回合数: ", (step+1)/2));
    }
    public void setStepAndCount(){
        count=1;
        step=1;
        statusLabel.setText(String.format("%s%d", "回合数: ", (step+1)/2));
    }

    //在controller中设置count回合数
    // 显示currentPlayer
    public void addCurrentPlayer() {
        playerLabel.setLocation(HEIGTH + 40, HEIGTH / 20);
        playerLabel.setSize(200, 60);
        playerLabel.setFont(new Font("楷体", Font.BOLD, 25));
        add(playerLabel);
    }

    public void viewCurrentPlayer() {
        if (controller.win()) {
            playerLabel.setText(String.format("%s", "游戏结束！"));
        } else if (!controller.win()) {
            if (controller.getCurrentPlayer() == PlayerColor.BLUE) {
                playerLabel.setText(String.format("%s%s", "当前回合：", "蓝方"));
            } else if (controller.getCurrentPlayer() == PlayerColor.RED) {
                playerLabel.setText(String.format("%s%s", "当前回合：", "红方"));
            }
        }
    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */
    private void addRestartButton() {
        restartButton.addActionListener(this);
        // button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));
        restartButton.setLocation(HEIGTH, HEIGTH / 10 + 120);
        restartButton.setSize(200, 60);
        restartButton.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(restartButton);
    }

    public void moveHints() {
        JOptionPane.showMessageDialog(this, "步骤违规，请重试！");
    }
    public void restartHints(){
        JOptionPane.showMessageDialog(this, "请重新开始游戏！");
    }

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

       button.addActionListener(e -> {
           System.out.println("Click load");
           String path = JOptionPane.showInputDialog(this,"Input Path here");
            controller.loadGameFromFile(path);
      });
    }

    private void addSaveButton() {
        JButton button = new JButton("Save");
        button.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click save");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            controller.saveGameIntoFile(path);
        });
    }

    private void addJMenu() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("说明");

        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登陆");
        JMenuItem closeItem = new JMenuItem("关闭游戏");
        JMenuItem musicItem = new JMenuItem("音乐播放");

        JMenuItem ruleItem = new JMenuItem("规则");
        JMenuItem flagItem = new JMenuItem("标志说明");
        JMenuItem aboutItem = new JMenuItem("制作人员");

        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        functionJMenu.add(musicItem);

        aboutJMenu.add(ruleItem);
        aboutJMenu.add(flagItem);
        aboutJMenu.add(aboutItem);

        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        this.setJMenuBar(jMenuBar);
        this.setVisible(true);

        replayItem.addActionListener((e) -> {
            System.out.println("重置游戏");
            JOptionPane.showMessageDialog(this, "游戏已重置！");
            controller.restartGame();
        });
        reLoginItem.addActionListener((e) -> {
            System.out.println("重新登陆");
            //关闭界面
            this.setVisible(false);
            //打开登陆界面
            new LoginJFrame();
        });
        closeItem.addActionListener((e) -> {
            System.out.println("关闭游戏");
            //直接关闭虚拟机
            System.exit(0);
        });

        ruleItem.addActionListener((e) -> {
            System.out.println("打开说明");
            //TODO：如何打开本地文件
        });
    }

    //在GameController中的Win（）执行
    public void blueWinDialog() {
        JDialog winDialog = new JDialog();
        winDialog.setSize(650, 350);
        //让弹框置顶
        winDialog.setAlwaysOnTop(true);
        //让弹框居中
        winDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        winDialog.setModal(true);

        JLabel winPlayerJLabel = new JLabel();


        winPlayerJLabel.setText("蓝方胜利！");
        winPlayerJLabel.setFont(new Font("宋体", Font.BOLD, 30));
        winPlayerJLabel.setForeground(Color.RED);

        Icon winJLabel = new ImageIcon("Imagine\\Victory.png");
        winPlayerJLabel.setIcon(winJLabel);

        winPlayerJLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        winPlayerJLabel.setVerticalTextPosition(SwingConstants.BOTTOM);

        winDialog.add(winPlayerJLabel);

        winDialog.setVisible(true);
        /**JOptionPane.showMessageDialog(this, "Imagine\\Victory.png");
         JLabel winJLabel = new JLabel(new ImageIcon("Imagine\\Victory.png"));
         winJLabel.setBounds(203, 183, 597, 373);
         this.getContentPane().add(winJLabel);**/
    }

    public void redWinDialog() {
            JDialog winDialog = new JDialog();
            winDialog.setSize(650, 350);
            //让弹框置顶
            winDialog.setAlwaysOnTop(true);
            //让弹框居中
            winDialog.setLocationRelativeTo(null);
            //弹框不关闭永远无法操作下面的界面
            winDialog.setModal(true);

            JLabel winPlayerJLabel = new JLabel();


            winPlayerJLabel.setText("红方胜利！");
            winPlayerJLabel.setFont(new Font("宋体", Font.BOLD, 30));
            winPlayerJLabel.setForeground(Color.RED);

            Icon winJLabel = new ImageIcon("Imagine\\Victory.png");
            winPlayerJLabel.setIcon(winJLabel);

            winPlayerJLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            winPlayerJLabel.setVerticalTextPosition(SwingConstants.BOTTOM);

            winDialog.add(winPlayerJLabel);

            winDialog.setVisible(true);
            /**JOptionPane.showMessageDialog(this, "Imagine\\Victory.png");
             JLabel winJLabel = new JLabel(new ImageIcon("Imagine\\Victory.png"));
             winJLabel.setBounds(203, 183, 597, 373);
             this.getContentPane().add(winJLabel);**/
        }

    public void setGameController(GameController gameController) {
        this.controller = gameController;
    }


    //监听是哪个按钮
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == restartButton) {
            System.out.println("重置游戏");
            controller.restartGame();
            JOptionPane.showMessageDialog(this, "游戏已重置！");
        }
    }
}
