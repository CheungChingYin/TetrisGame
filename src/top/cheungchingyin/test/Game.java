package top.cheungchingyin.test;

import javax.swing.JFrame;

import top.cheungchingyin.controller.Controller;
import top.cheungchingyin.entties.Ground;
import top.cheungchingyin.entties.ShapeFactory;
import top.cheungchingyin.view.GamePanel;

public class Game {

	public static void main(String[] args) {
		ShapeFactory shapeFactory =new ShapeFactory();
		Ground ground=new Ground();
		GamePanel gamePanel=new GamePanel();
		Controller controller=new Controller(shapeFactory, ground, gamePanel);
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(gamePanel.getSize().width+10,gamePanel.getSize().height+35);
		frame.add(gamePanel);
		gamePanel.addKeyListener(controller);
		frame.setVisible(true);
		controller.newGame();
		frame.add(gamePanel);//添加按键监听控制类
		gamePanel.addKeyListener(controller);
		frame.addKeyListener(controller);
		frame.setVisible(true);
	}
}
