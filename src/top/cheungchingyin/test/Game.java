package top.cheungchingyin.test;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import top.cheungchingyin.controller.Controller;
import top.cheungchingyin.entties.Ground;
import top.cheungchingyin.entties.ScoreTotal;
import top.cheungchingyin.entties.ShapeFactory;
import top.cheungchingyin.view.GamePanel;

public class Game extends JFrame {
	
	public JLabel jl1;//用于显示分数：
	public static JLabel jl2;//声明一个静态变量，用于记录分数，在静态方法中需要用到
	JPanel jp1;//用于添加jl1和jl2

	public static void main(String[] args) {
		Game g=new Game();
	}
	
	public Game() {
		
		ShapeFactory shapeFactory =new ShapeFactory();
		Ground ground=new Ground();
		GamePanel gamePanel=new GamePanel();
		Controller controller=new Controller(shapeFactory, ground, gamePanel);
		jl1=new JLabel("分数：");//在标签中显示“分数：”字样
		jl2=new JLabel("0");//原始分数默认为0
		jp1=new JPanel();//jPanel用于承载两个JLabel
		jp1.add(jl1);//添加两个JLabel
		jp1.add(jl2);
		jp1.setLayout(new GridLayout(1, 2));//把JP1设置布局为1行2列
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(gamePanel.getSize().width+10+10,gamePanel.getSize().height+45+10);
		this.add(jp1,BorderLayout.SOUTH);
		this.add(gamePanel,BorderLayout.NORTH);
		gamePanel.addKeyListener(controller);
		this.setVisible(true);
		controller.newGame();
		this.add(gamePanel);//添加按键监听控制类
		gamePanel.addKeyListener(controller);
		this.addKeyListener(controller);
		this.setVisible(true);
	}
	
	public static void setJl2(String score){//设置Jlabel2的文本，方便及时更改分数。使用静态方法，方便在别的类引用
		jl2.setText(score);
	}
	
}
