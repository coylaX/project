package view;

import computerplayer.GameMode;
import controller.GameController;
import model.Chessboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class LoginJFrame extends JFrame implements MouseListener {
    static ArrayList<User> userList=new ArrayList<>();
    /**static{
        userList.add(new User("Administrator","123"));
        userList.add(new User("123","123"));
    }**/
    JTextField username = new JTextField();
    //JTextField password = new JTextField();
    JPasswordField password = new JPasswordField();
    JButton login = new JButton();
    JButton register = new JButton();
    public LoginJFrame() {
        initJFrame();
        initView();
        this.setResizable(false);
        this.setVisible(true);

        this.setArrayList();
    }

    public void initView(){
        //JLabel usernameText=new JLabel("用户名 "+":");
        JLabel usernameText = new JLabel(new ImageIcon("Imagine\\Login\\用户名.png"));
        usernameText.setBounds(33, 145, 47, 17);
        //getContentPane啥意思
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框
        username.setBounds(86, 139, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        /**JLabel passwordText=new JLabel("密码");
        Font font=new Font("",Font.PLAIN,25);
        passwordText.setFont(font);
        passwordText.setForeground(Color.PINK);**/
        JLabel passwordText = new JLabel(new ImageIcon("Imagine\\Login\\密码.png"));
        passwordText.setBounds(40, 205, 32, 16);
        this.getContentPane().add(passwordText);

        //4.密码输入框
        password.setBounds(85, 195, 200, 30);
        this.getContentPane().add(password);

        //5.添加登录按钮
        login.setBounds(23, 235, 128, 47);
        login.setIcon(new ImageIcon("Imagine\\Login\\登录按钮.png"));
        //去除按钮的边框
        login.setBorderPainted(false);
        //去除按钮的背景
        login.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        login.addMouseListener(this);
        this.getContentPane().add(login);

        //6.添加注册按钮
        register.setBounds(164, 235, 128, 47);
        register.setIcon(new ImageIcon("Imagine\\Login\\注册按钮.png"));
        //去除按钮的边框
        register.setBorderPainted(false);
        //去除按钮的背景
        register.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        register.addMouseListener(this);
        this.getContentPane().add(register);


        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("Imagine\\Login.jpg"));
        background.setBounds(0, -24, 600, 350);
        this.getContentPane().add(background);
    }
    public void initJFrame(){
        this.setSize(605,340);
        this.setTitle("斗兽棋登陆");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);//(啥意思)
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == login) {
            System.out.println("点击了登录按钮");
            //获取两个文本输入框中的内容
            String usernameInput = username.getText();
            String passwordInput = password.getText();

            //创建一个User对象
            User userInfo = new User(usernameInput, passwordInput);
            System.out.println("用户输入的用户名为" + usernameInput);
            System.out.println("用户输入的密码为" + passwordInput);

            if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                //校验用户名和密码是否为空
                System.out.println("用户名或者密码为空");

                //调用showJDialog方法并展示弹框
                showJDialog("用户名或者密码为空");

            } else if (contains(userInfo)) {
                System.out.println("登陆成功！游戏开始");
                //关闭当前登录界面
                this.setVisible(false);
                this.dispose();

                int mod = JOptionPane.showOptionDialog(null,"选择游戏模式",
                        "请选择游戏模式",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,
                        null,new String[]{"双人","随机","贪心"},"双人");

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
                }

                //打开游戏的主界面
                //需要把当前登录的用户名传递给游戏界面
                ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);

                //TODO:自主选择mode to play,传入mode
                GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard(),gameMode);
                mainFrame.setGameController(gameController);
                gameController.setFrame(mainFrame);
                mainFrame.setVisible(true);
                //以下是登录后放音乐，第一行是路径
                String filepath="Music/grassland.wav";
                MusicPlayer musicObject=new MusicPlayer();
                musicObject.playMusic(filepath);
                mainFrame.setMusic(musicObject);
                mainFrame.setNowUser(userInfo);

            } else{
                System.out.println("用户名或密码错误");
                showJDialog("用户名或密码错误");
            }
        } else if (e.getSource() == register) {
            System.out.println("点击了登录按钮");
            //获取两个文本输入框中的内容
            String usernameInput = username.getText();
            String passwordInput = password.getText();

            //创建一个User对象
            User userInfo = new User(usernameInput, passwordInput);
            System.out.println("用户输入的用户名为" + usernameInput);
            System.out.println("用户输入的密码为" + passwordInput);
            if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                //校验用户名和密码是否为空
                System.out.println("用户名或者密码为空");
                //调用showJDialog方法并展示弹框
                showJDialog("用户名或者密码为空");
            }else if(examName(userInfo)){
                System.out.println("此用户名已存在！请重新注册");
                showJDialog("此用户名已存在！请重新注册");
            }else{
                System.out.println("注册成功");
                showJDialog("注册成功！请重新登陆");
                //把注册的账号写入账号库中
                try {
                    registerAccount(usernameInput,passwordInput);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                /**try {
                    ArrayList<String> newAccount = new ArrayList<String>();
                    newAccount.add(usernameInput);
                    newAccount.add(passwordInput);
                    for (int i = 0; i < newAccount.size(); i++) {
                        System.out.println(newAccount.get(i));
                    }
                    Files.write(Path.of("/Account/account.txt"), newAccount, Charset.defaultCharset());
                } catch (IOException e0) {
                    throw new RuntimeException(e0);
                }**/

                //userList.add(userInfo);
            }
        }
    }
    public void registerAccount(String username,String password) throws FileNotFoundException {
        Scanner input=new Scanner(new File("Account/account.txt"));
        ArrayList<String> oldAccount=new ArrayList<>();
        StringBuilder oldString=new StringBuilder();
        while(input.hasNext()){
            oldAccount.add(input.next());
        }
        System.out.println("旧账户继承:");
        for (int i = 0; i <oldAccount.size(); i++) {
            System.out.printf(" %s ",oldAccount.get(i));
            oldString.append(oldAccount.get(i)+" ");
        }
        System.out.printf("新账户添加：%s %s ",username,password);
        input.close();
        Formatter output=new Formatter("Account/account.txt");
        output.format("%s%s %s ",oldString.toString(),username,password);
        output.close();
        this.setArrayList();
    }
    public void setArrayList(){
        try {
            Scanner input = new Scanner(new File("Account/account.txt"));
            while (input.hasNext()) {
                String userName = input.next();
                String passWord = input.next();
                User oldUser = new User(userName, passWord);
                userList.add(oldUser);
            }
            input.close();
        }catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean contains(User userInfo){
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUsername().equals(userInfo.getUsername()) && userList.get(i).getPassword().equals( userInfo.getPassword())) {
                System.out.println("用户名和密码正确！");
                return true;
            }
        }
        System.out.println("用户名或密码错误！");
        return false;
    }
    public boolean examName(User userInfo){
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUsername().equals(userInfo.getUsername())) {
                System.out.println("错误：重复的用户名");
                return true;
            }
        }
        System.out.println("新建用户名");
        return false;
    }
    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("Imagine\\Login\\登录按下.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("Imagine\\Login\\注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource()==login){
            login.setIcon(new ImageIcon("Imagine\\Login\\登录按钮.png"));
        }else if (e.getSource()==register){
            register.setIcon(new ImageIcon("Imagine\\Login\\注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
