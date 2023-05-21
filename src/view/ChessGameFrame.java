package view;

import computerplayer.GameMode;
import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;

import model.PlayerColor;

import static view.MusicPlayer.clip;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame implements ActionListener {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;

    private final int ONE_CHESS_SIZE;
    private int count = 1;
    private int step = 1;
    private ChessboardComponent chessboardComponent;
    private GameController controller;
    private MusicPlayer music;
    private User nowUser;

    public void setNowUser(User nowUser) {
        this.nowUser = nowUser;
    }

    public void setMusic(MusicPlayer music) {
        this.music = music;
    }

    JButton restartButton = new JButton("悔棋");
    JLabel playerLabel = new JLabel("当前回合:蓝方");
    JLabel statusLabel = new JLabel("回合数: " + count);

    public ChessGameFrame(int width, int height) {
        setTitle("斗兽棋"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;
        //this.setAlwaysOnTop(true);

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        this.setResizable(false);

        //initialize the menu
        addChessboard();
        setStepLabel();
        addRestartButton();
        addLoadButton();
        addSaveButton();
        addJMenu();
        addCurrentPlayer();

        /**JLabel background = new JLabel(new ImageIcon("Imagine\\Background.jpg"));
         //background.setBounds(-15, -24, 1124, 751);
         background.setBounds(-15, -24, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
         this.getContentPane().add(background);
         Image GameBG = Toolkit.getDefaultToolkit().getImage("Imagine\\Background.jpg").getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
         JComponent imageComponent = new ImageComponent(GameBG);// create an instance of ImageComponent
         imageComponent.setSize(WIDTH,HEIGHT);
         imageComponent.setLocation(0, 0); // set absolute location
         this.add(imageComponent,JLayeredPane.FRAME_CONTENT_LAYER);
         **/
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

    public void viewCount() {
        ++step;
        count = (step + 1) / 2;
        statusLabel.setText(String.format("%s%d", "回合数: ", (step + 1) / 2));
    }

    public void redoStep() {
        --step;
        count = (step + 1) / 2;
        statusLabel.setText(String.format("%s%d", "回合数: ", (step + 1) / 2));
    }

    public void loadStep(int step) {
        this.step = step;
        this.count = (step + 1) / 2;
        statusLabel.setText(String.format("%s%d", "回合数: ", (step + 1) / 2));
    }

    public void setStepAndCount() {
        count = 1;
        step = 1;
        statusLabel.setText(String.format("%s%d", "回合数: ", (step + 1) / 2));
    }

    public void setGameController(GameController gameController) {
        this.controller = gameController;
    }

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
        restartButton.setFont(new Font("楷体", Font.BOLD, 20));
        add(restartButton);
    }


    public void moveHints() {
        JOptionPane.showMessageDialog(this, "步骤违规，请重试！");
    }

    public void restartHints() {
        JOptionPane.showMessageDialog(this, "请重新开始游戏！");
    }

    public void redoWrongHints() {
        JOptionPane.showMessageDialog(this, "已经是第一步了！");
        /**JDialog redoDialog = new JDialog();
         redoDialog.setSize(200, 100);
         //让弹框置顶
         redoDialog.setAlwaysOnTop(true);
         //让弹框居中
         redoDialog.setLocationRelativeTo(null);
         //弹框不关闭永远无法操作下面的界面
         redoDialog.setModal(true);

         JLabel redoWrongJLabel = new JLabel();


         redoWrongJLabel.setText("已经是第一步！");
         redoWrongJLabel.setFont(new Font("宋体", Font.BOLD, 5));
         redoWrongJLabel.setForeground(Color.BLACK);

         redoDialog.add(redoWrongJLabel);
         redoDialog.setVisible(true);**/
    }
    public void wrong101Hint(){
        JOptionPane.showMessageDialog(this, "文件格式错误，请重试！");
    }
    public void wrong102Hint(){
        JOptionPane.showMessageDialog(this, "棋盘大小错误，请重试！");
    }
    public void wrong103Hint(){
        JOptionPane.showMessageDialog(this, "棋子错误，请重试！");
    }
    public void wrong104Hint(){
        JOptionPane.showMessageDialog(this, "缺少回合数信息，请重试！");
    }

    private void addLoadButton() {
        JButton button = new JButton("读档");
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("楷体", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("读档");
            //String path = JOptionPane.showInputDialog(this,"Input Path here");
            try {
                Path path = Path.of(FileChooser.chooseFile());
                controller.loadGameFromFile(path.toString());
            //} catch (IOException e1) {
              //  e1.printStackTrace();
            } catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        });
    }

    private void addSaveButton() {
        JButton button = new JButton("存档");
        button.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("楷体", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("存档");
            String name = JOptionPane.showInputDialog(this, "Input name here");
            String path = "Saved/" + "Saved " + name + " " + nowUser.getUsername()+".txt";
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
        JMenuItem musicItem = new JMenuItem("暂停音乐");
        JMenuItem volumeItem = new JMenuItem("调整音量");
        JMenuItem modeItem = new JMenuItem("切换模式");

        JMenuItem ruleItem = new JMenuItem("规则");
        JMenuItem flagItem = new JMenuItem("标志说明");
        JMenuItem aboutItem = new JMenuItem("制作人员");

        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        functionJMenu.add(musicItem);
        functionJMenu.add(volumeItem);
        functionJMenu.add(modeItem);

        aboutJMenu.add(ruleItem);
        aboutJMenu.add(flagItem);
        aboutJMenu.add(aboutItem);

        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        this.setJMenuBar(jMenuBar);
        this.setVisible(true);

        replayItem.addActionListener((e) -> {
            System.out.println("重置游戏");
            controller.restartGame();
            JOptionPane.showMessageDialog(this, "游戏已重置！");
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
        //TODO:如何播放暂停
        musicItem.addActionListener((e) -> {
            System.out.println("播放或者暂停音乐");
            if (clip.isRunning()) {
                clip.stop();
                musicItem.setText("播放音乐");
            } else {
                clip.start();
                musicItem.setText("暂停音乐");
            }
        });
        //调整音量
        volumeItem.addActionListener((e) -> {
            System.out.println("调整音量");
            //this.setVolumeSlider();
            VolumeFrame volume = new VolumeFrame();
            volume.setVisible(true);
        });
        modeItem.addActionListener((e)->{
            System.out.println("切换游戏模式");
            int mod = JOptionPane.showOptionDialog(null,"选择游戏模式",
                    "请选择游戏模式",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,
                    null,new String[]{"双人","随机","贪心","剪枝"},"双人");

            GameMode gameMode=null;
            if(mod==0){
                System.out.println("双人");
                gameMode=GameMode.Normal;
            }else if(mod==1){
                System.out.println("随机");
                gameMode=GameMode.Random;
            }else if(mod==2){
                System.out.println("贪心");
                gameMode=GameMode.Greedy;
            }else if(mod==3){
                System.out.println("剪枝");
                //gameMode=GameMode.;
            }
            controller.setGameMode(gameMode);
        });

        ruleItem.addActionListener((e) -> {
            System.out.println("打开说明");
            showRules();
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
    public void showRules(){
        String Rules="游戏开始时，红方先走，然后轮流走棋。每次可走动一只兽，每只兽每次走一方格，除己方兽穴和小河以外，前后左右均可。但是，狮、虎、鼠还有不同走法：\n" +
                "狮虎跳河法：狮虎在小河边时，可以纵横对直跳过小河，且能把小河对岸的敌方较小的兽类吃掉，但是如果对方老鼠在河里，把跳的路线阻隔就不能跳，若对岸是对方比自己战斗力强的兽，也不可以跳过小河；\n" +
                "鼠游过河法：只有鼠是可以走入小河的兽，走法同陆地上一样，每次走一格，上下左右均可，而且，陆地上的其他兽不可以吃小河中的鼠，小河中的鼠也不能吃陆地上的象，鼠类互吃不受小河影响。" +
                "斗兽棋吃法分普通吃法和特殊吃法，普通吃法是按照兽的战斗力强弱，强者可以吃弱者。\n" +
                "特殊吃法如下：\n" +
                "1、鼠吃象法：八兽的吃法除按照战斗力强弱次序外，惟鼠能吃象，象不能吃鼠。\n" +
                "2、互吃法：凡同类相遇，可互相吃。\n" +
                "3、陷阱：棋盘设陷阱，专为限制敌兽的战斗力（自己的兽，不受限制），敌兽走入陷阱，即失去战斗力，本方的任意兽类都可以吃去陷阱里的兽类。\n" +
                "综合普通吃法和特殊吃法，将斗兽棋此法总结如下：\n" +
                "鼠可以吃鼠、象；\n" +
                "猫可以吃猫、鼠；\n" +
                "狗可以吃狗、猫、鼠；\n" +
                "狼可以吃狼、狗、猫、鼠；\n" +
                "豹可以吃豹、狼、狗、猫、鼠；\n" +
                "虎可以吃虎、豹、狼、狗、猫、鼠；\n" +
                "狮可以吃狮、虎、豹、狼、狗、猫、鼠；\n" +
                "象可以吃象、狮、虎、豹、狼、狗、猫；\n" +
                "【斗兽棋胜负判定】\n" +
                "1、任何一方的兽走入敌方的兽穴就算胜利（自己的兽类不可以走入自己的兽穴）；\n" +
                "2、任何一方的兽被吃光就算失败，对方获胜；\n" +
                "3、任何一方所有活着的兽被对方困住，均不可移动时，就算失败，对方获胜；\n" +
                "4、任何一方走棋时间用完，就算失败，对方获胜；\n" +
                "5、任何一方中途离开游戏，就算逃跑，对方获胜；\n" +
                "6、在双方同意的情况下可和棋；\n" +
                "7、在连续100回合内，双方均无动物被吃，就算和棋。";

        JTextArea text = new JTextArea(Rules);
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setEditable(false);
        text.setMargin(new Insets(10, 10, 10, 10));
        text.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setPreferredSize(new Dimension(350, 300));

        JOptionPane.showMessageDialog(null, scrollPane,
                "斗兽棋规则", JOptionPane.PLAIN_MESSAGE);
    }


    //监听是哪个按钮
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == restartButton) {
            System.out.println("悔棋");
            controller.withdraw();
            //JOptionPane.showMessageDialog(this, "游戏已重置！");
        }
    }
}
