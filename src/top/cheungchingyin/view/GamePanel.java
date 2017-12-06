package top.cheungchingyin.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import top.cheungchingyin.entties.Global;
import top.cheungchingyin.entties.Ground;
import top.cheungchingyin.entties.Shape;

public class GamePanel extends JPanel {

	private Ground ground;
	private Shape shape;
	
	public void display(Ground ground,Shape shape){
		System.out.println("Gamepanel's display");
		this.ground=ground;
		this.shape=shape;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//redrawing
		g.setColor(new Color(0xcfcfcf));//���û�����ɫΪ��ɫ
//		g.setColor(Color.GRAY);//���ñ�����ɫ
		g.fillRect(1, 0, Global.WIDTH*Global.CELL_SIZE, Global.HEIGHT*Global.CELL_SIZE);//�����Ӱ���⣬����˻�ɫ�뱳����ɫ��ͬ���Խ������Ӱ����
		if(shape!=null && ground!=null){
			shape.drawMe(g);
			ground.drawMe(g);
		}
	}
	public GamePanel(){
		this.setSize(Global.WIDTH*Global.CELL_SIZE,Global.HEIGHT*Global.CELL_SIZE);
		
	}
	
}
