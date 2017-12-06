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
	
	public JLabel jl1;//������ʾ������
	public static JLabel jl2;//����һ����̬���������ڼ�¼�������ھ�̬��������Ҫ�õ�
	JPanel jp1;//�������jl1��jl2

	public static void main(String[] args) {
		Game g=new Game();
	}
	
	public Game() {
		
		ShapeFactory shapeFactory =new ShapeFactory();
		Ground ground=new Ground();
		GamePanel gamePanel=new GamePanel();
		Controller controller=new Controller(shapeFactory, ground, gamePanel);
		jl1=new JLabel("������");//�ڱ�ǩ����ʾ��������������
		jl2=new JLabel("0");//ԭʼ����Ĭ��Ϊ0
		jp1=new JPanel();//jPanel���ڳ�������JLabel
		jp1.add(jl1);//�������JLabel
		jp1.add(jl2);
		jp1.setLayout(new GridLayout(1, 2));//��JP1���ò���Ϊ1��2��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(gamePanel.getSize().width+10+10,gamePanel.getSize().height+45+10);
		this.add(jp1,BorderLayout.SOUTH);
		this.add(gamePanel,BorderLayout.NORTH);
		gamePanel.addKeyListener(controller);
		this.setVisible(true);
		controller.newGame();
		this.add(gamePanel);//��Ӱ�������������
		gamePanel.addKeyListener(controller);
		this.addKeyListener(controller);
		this.setVisible(true);
	}
	
	public static void setJl2(String score){//����Jlabel2���ı������㼰ʱ���ķ�����ʹ�þ�̬�����������ڱ��������
		jl2.setText(score);
	}
	
}
